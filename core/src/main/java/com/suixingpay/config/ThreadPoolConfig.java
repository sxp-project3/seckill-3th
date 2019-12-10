package com.suixingpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 *
 * @author 詹文良
 * @program: seckill-3th
 * @description: 自定义配置线程池(基于 JDK)，调优并发能力，最大可调度 1000 + 8
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 *
 */

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        ExecutorService executorService = new ThreadPoolExecutor(
                4,
                8,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        return executorService;
    }

}
