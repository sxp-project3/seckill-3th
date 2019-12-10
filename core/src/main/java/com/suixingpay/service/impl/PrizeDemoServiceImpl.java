package com.suixingpay.service.impl;

import com.suixingpay.pojo.Cat;
import com.suixingpay.service.PrizeDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: kongjian
 * @Date: 2019/12/9
 */
@Service
@Slf4j
public class PrizeDemoServiceImpl implements PrizeDemoService {

    // 奖池名
    private static final String PRIZE_POOL = "prize:pool";

    // 中奖名单
    private static final String PRIZE_MEMBER_LIST = "prize:member:list";

    private Cat cat;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public List robPrizeDemo(Integer activityId, Integer userId) {


        String prize_pool_key = PRIZE_POOL + activityId;
        String prize_member_list = PRIZE_MEMBER_LIST + activityId;
//
        redisTemplate.opsForSet().add(prize_pool_key, 1);
        redisTemplate.opsForSet().add(prize_pool_key, 2);
        redisTemplate.opsForSet().add(prize_pool_key, 3);
        redisTemplate.opsForSet().add(prize_pool_key, 4);
        redisTemplate.opsForSet().add(prize_pool_key, 5);
        redisTemplate.opsForSet().add(prize_pool_key, 6);
        redisTemplate.opsForSet().add(prize_pool_key, 7);
        redisTemplate.opsForSet().add(prize_pool_key, 8);
        redisTemplate.opsForSet().add(prize_pool_key, 9);
        redisTemplate.opsForSet().add(prize_pool_key, 10);

        // 从奖池获取奖品
        Object prizeId = redisTemplate.opsForSet().pop(prize_pool_key);
        log.info("prize id:" + prizeId.toString());
        // 记录中奖信息

        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        String prizeResult = String.valueOf(userId) + ";" + prizeId + ";" + dateNowStr;

        redisTemplate.opsForHash().put(prize_member_list, String.valueOf(userId), prizeResult);
        log.info(redisTemplate.opsForHash().get(prize_member_list, String.valueOf(userId)).toString());

        List hlist = redisTemplate.opsForHash().values(prize_member_list);

        return hlist;
    }


    @Override
    public List getList(Integer activityId){

        String prize_member_list = PRIZE_MEMBER_LIST + activityId;
        List hlist = redisTemplate.opsForHash().values(prize_member_list);

        List list = new ArrayList();
        Iterator it = hlist.iterator();
        while(it.hasNext()) {
            String next = (String)it.next();
            String[] split = next.split(";");
            // System.out.println("管家id:"+split[0]+" 奖品id:"+split[1]+" 中奖时间:"+split[2]);
            int Prize_id = Integer.parseInt(split[1]);
            int Manager_id = Integer.parseInt(split[0]);
            Cat cat = new Cat();
            cat.setPrize_id(Prize_id);
            cat.setManager_id(Manager_id);
            cat.setGet_prize_time(split[2]);
            list.add(cat);
        }

        return list;

    }
}
