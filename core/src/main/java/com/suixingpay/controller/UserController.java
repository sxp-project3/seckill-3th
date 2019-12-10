package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;
import com.suixingpay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17:16
 *@description 在所在地相同的前提下判断当前时间是否可以参加活动
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public Response<CodeEnum> selectUserById(@RequestBody Users users){
//        String info = request.getHeader("token");
//        System.out.println(info);
        return userService.selectUserById(users);
    }
}
