package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.PrizeMapper;
import com.suixingpay.mapper.StartAndEndMapper;
import com.suixingpay.pojo.*;
import com.suixingpay.response.Response;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:04
 */
@Service
public class StartAndEndServiceImpl implements StartAndEndService {
    @Autowired
    private StartAndEndMapper startAndEndMapper;
    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private PrizeDemoService prizeDemoService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 后台管理者点击开始，活动才能开始，
     * 将一定数量的沉默用户插入到redis,
     * 并且修改奖品表所参与活动id字段为当前活动id
     *
     * @param aId 活动id
     * @return
     */
    @Override
    @Transactional
    public Response backGroundStart(Integer aId) {
        Active active = new Active();
        List<Active> activeList = startAndEndMapper.selectActiveByAid(aId);
        List<Integer> prizeIdList = new ArrayList<Integer>();
        Integer prizeNum = activeList.get(0).getMaxPrizeNum();
        String city = activeList.get(0).getCity();
        Dog dog = new Dog();
        //调用黄的,查询可参加活动的默认用户
        Integer num = prizeMapper.selectCount(city);
        if (prizeNum <= num) {
            dog.setData(prizeNum);
            dog.setTerritory(city);
            //活动所需奖品小于等于所剩奖品
            //活动能正常进行
            //调用黄的奖品List
            List<Integer> pList = prizeMapper.selectLimit(dog);
            //把pList循环插入redis的value
            for (int i = 0; i < pList.size(); i++) {
                Integer prizeId = pList.get(i);
                prizeIdList.add(prizeId);
                redisTemplate.opsForSet().add("prize:pool" + aId, prizeId);
            }
            //修改奖品表所属活动的id
            Integer result = startAndEndMapper.updatePrizeActivityId(prizeIdList, aId);
            //修改活动表status为1
            active.setId(aId);
            active.setStatus(1);
            startAndEndMapper.updateActiveliststatus(active);
            //返回修改值为0时
            if (result == 0) {
                return Response.getInstance(CodeEnum.FAIL, "无该场活动！");
            }
            return Response.getInstance(CodeEnum.SUCCESS);


        } else {
            //活动所需奖品大于所剩奖品
            //活动不能正常进行
            return Response.getInstance(CodeEnum.FAIL, "奖品数量不足，不能开始活动");
        }
    }


    /**
     * 后台管理者点击结束，将活动结果表插入数据，
     * 并且在奖品表中修改活动中剩余的沉默用户id为0
     *
     * @param aId 活动id
     * @return
     */
    @Override
    @Transactional
    public Response backGroundEnd( Integer aId) {

        try {
            if (aId == null) {
                return Response.getInstance(CodeEnum.FAIL, "未传参数！");
            }
            //Active active= new Active();
            //获取redis里面的获奖结果
            List<Cat> prizeResultList = prizeDemoService.getList(aId);
            //redis里面没有值，那么只执行改变奖品表所属活动id恢复为0，不执行添加功能
            if (prizeResultList.size() == 0) {
                updateStatus(aId);
            } else {
                List<PrizeResult> prizeResultlist1 = new ArrayList();

                for (int i = 0; i < prizeResultList.size(); i++) {
                    Cat cat = prizeResultList.get(i);
                    PrizeResult prizeResult = new PrizeResult();
                    prizeResult.setPrizeTime(cat.getGet_prize_time());
                    prizeResult.setActivityId(aId);
                    prizeResult.setManageId(cat.getManager_id());
                    prizeResult.setPrizeId(cat.getPrize_id());
                    prizeResult.setCreateTime(new Date());
                    prizeResultlist1.add(prizeResult);
                }
                startAndEndMapper.insertPrizeResultNew(prizeResultlist1);
                updateStatus(aId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.getInstance(CodeEnum.FAIL);
        }
        return Response.getInstance(CodeEnum.SUCCESS);
    }


    //循环插入数据到用户表，不作为功能性
    @Override
    public void insertUser() {
        for (Integer i = 12001; i <= 13000; i++) {
            Users user = new Users();
            user.setid(i);
            user.setUserCity("shandong");
            user.setUserName("shandong" + i);
            startAndEndMapper.insertUser(user);
        }
    }


    @Override
    public Response getActivityResult(Integer aId) {
        PrizeResult prizeResult = new PrizeResult();
        prizeResult.setActivityId(aId);
        List<PrizeResult> result = startAndEndMapper.selectActivityResult(prizeResult);
        return Response.getInstance(CodeEnum.SUCCESS, result);
    }


    /**
     * 查出活动结束后，redis中是否有有剩余奖品
     * 改变活动结束状态，改变剩余奖品所属活动id为0
     *
     * @param aId 活动id
     * @return
     */
    @Override
    public Response updateStatus(Integer aId) {
        Active active = new Active();
        Set myObjectListRedis = redisTemplate.opsForSet().members("prize:pool" + aId);
        //该场活动奖品无剩余无该场活动,直接修改活动表status
        if (myObjectListRedis.isEmpty()) {
            //修改活动表status为0
            active.setId(aId);
            active.setStatus(0);
            startAndEndMapper.updateActiveliststatus(active);
            return Response.getInstance(CodeEnum.SUCCESS);
        }
        //活动结束后，剩余奖品返回奖品池
        List prizeIdResidue = new ArrayList(myObjectListRedis);
        Integer result = startAndEndMapper.updatePrizeByActivityId(prizeIdResidue);
        active.setId(aId);
        active.setStatus(0);
        startAndEndMapper.updateActiveliststatus(active);
        return Response.getInstance(CodeEnum.SUCCESS);
    }
}
