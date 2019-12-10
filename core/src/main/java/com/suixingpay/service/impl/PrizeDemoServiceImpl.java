package com.suixingpay.service.impl;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Cat;
import com.suixingpay.pojo.Manager;
import com.suixingpay.service.PrizeDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public Map robPrizeDemo(Active active, Manager manager, String nowDate) {
        String prize_pool_key = PRIZE_POOL + active.getId();
        String prize_member_list = PRIZE_MEMBER_LIST + active.getId();
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

//        Date d = new Date();
//        System.out.println(d);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateNowStr = sdf.format(d);
        String prizeResult = String.valueOf(manager.getId()) + ";" + prizeId + ";" + nowDate;

        redisTemplate.opsForHash().put(prize_member_list, String.valueOf(manager.getId()), prizeResult);
        log.info(redisTemplate.opsForHash().get(prize_member_list, String.valueOf(manager.getId())).toString());

        List hlist = redisTemplate.opsForHash().values(prize_member_list);

        Map<String, Object> result = new HashMap<>();
//        result.put("prizeStatus", 0);
//        result.put("msg", "秒杀成功");
        result.put("prizeStatus", -1);
        result.put("msg", "执行失败，奖品不够");

        return result;
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
