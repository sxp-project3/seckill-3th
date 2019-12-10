package com.suixingpay.service;

import com.suixingpay.mapper.StartAndEndMapper;
import com.suixingpay.pojo.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void selectActiveByAid(int aId){

        List<Active> activeList= startAndEndMapper.selectActiveByAid(aId);
        List<Integer> list = new ArrayList<Integer>();
        Integer prizeNum=activeList.get(0).getMaxPrizeNum();
        String city=activeList.get(0).getCity();
        //String key="prize:pool"+aId;
        //调用黄的
        Integer num=100;
        if(prizeNum<= num){
            //活动所需奖品小于等于所剩奖品
            //活动能正常进行
            //调用黄的奖品List
            List pList = new ArrayList();
            //把pList循环插入redis的value
            //修改奖品表所属活动的id
            for (int i = 0; i < pList.size(); i++) {
                //String sCode = pList.get(i).getProcessCode();
                //int nCode = Integer.parseInt(sCode);
                //list.add(nCode);
                redisTemplate.opsForSet().add("prize:pool"+aId, 1111);
            }
        }
        else{
            //活动所需奖品大于所剩奖品
            //活动不能正常进行
        }
    }
}
