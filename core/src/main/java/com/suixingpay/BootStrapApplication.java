package com.suixingpay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 秒杀模块启动类
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 */

@SpringBootApplication
@MapperScan("com.suixingpay.mapper")
public class BootStrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}
