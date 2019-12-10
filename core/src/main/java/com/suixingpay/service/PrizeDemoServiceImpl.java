package com.suixingpay.service;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Cat;
import com.suixingpay.pojo.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    public String robPrizeDemo(Active active, Manager manager, String nowDate) {

        String prize_pool_key = PRIZE_POOL + active.getId();
        String prize_member_list = PRIZE_MEMBER_LIST + active.getId();

        // 判断城市相同
        if (active.getCity() != manager.getUserCity()) {
            throw new RuntimeException("鑫管家不能参加此城市的活动");
        }
        // 判断活动时间
        long startAt = active.getStartTime().getTime();
        long endAt = active.getEndTime().getTime();
        // log.info("startAt:" + startAt);
        // log.info("endAt:" + endAt);
        Date now = new Date();
        long nowAt = now.getTime();
        // log.info("nowAt:" + nowAt);
        // log.info("nowAt:" + nowAt);
        if (startAt > nowAt) {
            throw new RuntimeException("秒杀活动尚未开始，请稍后再来。");
        }
        if (endAt < nowAt) {
            throw new RuntimeException("秒杀活动已结束，请关注下次活动。");
        }
        // 判断活动剩余奖品数量
        if (redisTemplate.opsForSet().members(prize_pool_key).size() == 0) {
            throw new RuntimeException("秒杀奖品已被抢完，请下次再来。");
        }
        // 判断用户是否参与过抽奖
        if (redisTemplate.opsForHash().hasKey(prize_member_list, String.valueOf(manager.getId()))) {
            throw new RuntimeException("您已经抢到了奖品，请等待app消息通知。");
        }

        // 从奖池获取奖品
        Object prizeId = redisTemplate.opsForSet().pop(prize_pool_key);
        log.info("prize id:" + prizeId.toString());
        // 记录中奖信息
        String prizeResult = String.valueOf(manager.getId()) + ";" + prizeId + ";" + nowDate;
        if (!redisTemplate.opsForHash().putIfAbsent(prize_member_list, String.valueOf(manager.getId()), prizeResult)) {
            // 奖品放回奖池
            redisTemplate.opsForSet().add(prize_pool_key, Integer.parseInt(prizeId.toString()));
            throw new RuntimeException("您已经抢到了奖品，请等待app消息通知。");
        }

        String result = "秒杀成功";
//        Map<String, Object> result = new HashMap<>();
//        result.put("prizeStatus", 0);
//        result.put("msg", "秒杀成功");
//        result.put("prizeStatus", -1);
//        result.put("msg", "执行失败，奖品不够");

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

    private void demo() {
//        String prize_pool_key = PRIZE_POOL + active.getId();

//        String prize_member_list = PRIZE_MEMBER_LIST + active.getId();
//
//        redisTemplate.opsForSet().add(prize_pool_key, 1);
//        redisTemplate.opsForSet().add(prize_pool_key, 2);
//        redisTemplate.opsForSet().add(prize_pool_key, 3);
//        redisTemplate.opsForSet().add(prize_pool_key, 4);
//        redisTemplate.opsForSet().add(prize_pool_key, 5);
//        redisTemplate.opsForSet().add(prize_pool_key, 6);
//        redisTemplate.opsForSet().add(prize_pool_key, 7);
//        redisTemplate.opsForSet().add(prize_pool_key, 8);
//        redisTemplate.opsForSet().add(prize_pool_key, 9);
//        redisTemplate.opsForSet().add(prize_pool_key, 10);
    }
}
