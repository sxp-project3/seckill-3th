package com.suixingpay.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 测试项目启动构建
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 */
@RestController
public class HelloController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/hi")
    public String hello() {
        return "Hello, building success";
    }

    @RequestMapping("hi-redis")
    public Book helloR0() {

        Book book = new Book();
        book.setId(1);
        book.setName("价值998的入门教程！");
        book.setPrintDate(new Date());

        redisTemplate.opsForValue().set("2", book);
        return (Book) redisTemplate.opsForValue().get("2");
    }

    @RequestMapping("hi-redis-1")
    public boolean helloR1() {
        redisTemplate.opsForSet().add("id1", 1111);
        return redisTemplate.opsForSet().isMember("id1", 1111);
    }

}
