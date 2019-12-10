package com.suixingpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 自定义配置线程池(基于 JDK)，针对 IO 密集型计算
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 */

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        // 获取 CPU 核心数
        int coreSize = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(
                coreSize * 2,
                coreSize * 2,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        return executorService;
    }

}
