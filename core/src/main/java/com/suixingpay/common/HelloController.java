package com.suixingpay.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 测试项目启动构建
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 */
@RestController
public class HelloController {

    @RequestMapping("/hi")
    public String hello(){
        return "Hello, building success";
    }
}
