package com.suixingpay.service.impl;

import com.suixingpay.service.PrizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: kongjian
 * @Date: 2019/12/9
 */
@Service
@Slf4j
public class PrizeServiceImpl implements PrizeService {

    // 奖池名
    private static final String PRIZE_POOL = "prize:pool";

    // 中奖名单
    private static final String PRIZE_MEMBER_LIST = "prize:member:list";


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String robPrizeDemo(Integer activityId, Integer userId) {
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
        // System.out.println("格式化后的日期：" + dateNowStr);
        String prizeResult = prizeId + ";" + dateNowStr;
        redisTemplate.opsForHash().put(prize_member_list, userId, prizeResult);
        return prizeResult;
        // return prizeId.toString();
    }
}
