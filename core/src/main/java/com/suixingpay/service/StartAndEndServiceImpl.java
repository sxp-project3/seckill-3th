package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.PrizeMapper;
import com.suixingpay.mapper.StartAndEndMapper;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Cat;
import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.PrizeResult;
import com.suixingpay.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    public Response backGroundStart(Integer aId) {
        if (aId == null) {
            return Response.getInstance(CodeEnum.FAIL, "未传参数！");
        }
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
    public Response backGroundEnd(Integer aId) {

        try {
            if (aId == null) {
                return Response.getInstance(CodeEnum.FAIL, "未传参数！");
            }
            List prizeResultList = prizeDemoService.getList(aId);
            PrizeResult prizeResult = new PrizeResult();
            Iterator it = prizeResultList.iterator();
            while (it.hasNext()) {
                Date date = new Date();
                Cat cat = (Cat) it.next();
                prizeResult.setPrizeTime(cat.getGet_prize_time());
                prizeResult.setActivityId(aId);
                prizeResult.setManageId(cat.getManager_id());
                prizeResult.setPrizeId(cat.getPrize_id());
                prizeResult.setCreateTime(date);
                startAndEndMapper.insertPrizeResult(prizeResult);
            }
            Set myObjectListRedis = redisTemplate.opsForSet().members("prize:pool" + aId);
            //该场活动奖品无剩余无该场活动或
            if (myObjectListRedis.isEmpty()) {
                return Response.getInstance(CodeEnum.FAIL, "该场活动奖品无剩余无该场活动或！");
            }
            //活动结束后，剩余奖品返回奖品池
            List prizeIdResidue = new ArrayList(myObjectListRedis);
            Integer result = startAndEndMapper.updatePrizeByActivityId(prizeIdResidue);
            return Response.getInstance(CodeEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.getInstance(CodeEnum.FAIL);
    }
}
