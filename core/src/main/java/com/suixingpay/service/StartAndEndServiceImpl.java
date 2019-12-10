package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.PrizeMapper;
import com.suixingpay.mapper.StartAndEndMapper;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Dog;
import com.suixingpay.response.Response;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:04
 */
@Service
public class StartAndEndServiceImpl implements StartAndEndService{
    @Autowired
    private StartAndEndMapper startAndEndMapper;
    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Response backGroundStart(int aId){

        List<Active> activeList= startAndEndMapper.selectActiveByAid(aId);
        List<Integer> prizeIdList = new ArrayList<Integer>();
        Integer prizeNum=activeList.get(0).getMaxPrizeNum();
        String city=activeList.get(0).getCity();
        Dog dog = new Dog();
        //调用黄的,查询可参加活动的默认用户
        Integer num=prizeMapper.selectCount(city);
        if(prizeNum<= num){
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
                redisTemplate.opsForSet().add("prize:pool"+aId, prizeId);
            }
            //修改奖品表所属活动的id
            Integer result =startAndEndMapper.updatePrizeActivityId(prizeIdList,aId);
            if(result>0){
                return Response.getInstance(CodeEnum.SUCCESS );
            }
            return Response.getInstance(CodeEnum.FAIL,"没有该活动");

        }
        else{
            //活动所需奖品大于所剩奖品
            //活动不能正常进行
            return Response.getInstance(CodeEnum.FAIL,"奖品数量不足，不能开始活动");
        }
    }



//    @Override
//    public Response backGroundEnd(int aId){
//
//        redisTemplate.
//    }
}
