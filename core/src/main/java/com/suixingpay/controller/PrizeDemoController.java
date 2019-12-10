package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Manager;
import com.suixingpay.response.Response;
import com.suixingpay.service.ActiveService;
import com.suixingpay.service.ManagerService;
import com.suixingpay.service.PrizeDemoService;
import com.suixingpay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.activity.ActivityCompletedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kongjian
 * @Date: 2019/12/9
 */
@RestController
@RequestMapping("/prize-demo")
@Slf4j
public class PrizeDemoController {

    @Autowired
    private PrizeDemoService prizeDemoService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ActiveService activeService;

    @Autowired
    private UserService userService;

//    @RequestMapping("/rob")
//    @ResponseBody
//    public Response robPrize(@RequestParam("activityId") String activityId) {
//        Integer userId = 3001;
//        // Response userResponse = userService.selectUserById(3001);
//
//        List prizeResult = prizeDemoService.robPrizeDemo(1, 1);
////        String hello = "hello kongjian";
//        // int prizeId = Integer.parseInt(prizeResult);
//        // log.error(prizeResult);
//        Map<String, Object> result = new HashMap<>();
//        result.put("list", prizeResult);
//        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);
//        return response;
//    }

    @RequestMapping("/rob-demo")
    @ResponseBody
    public Response robPrizeDemo() {

        // 获取管家信息实体
        Manager manager = managerService.searchManagerById(1001);
        // 获取活动信息
        Active active = activeService.getOneById(460);
        // 获取当前系统时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = dateFormat.format(now);

        Map<String, Object> prizeResult = prizeDemoService.robPrizeDemo(active, manager, nowDate);

        Map<String, Object> result = new HashMap<>();
        result.put("manager", manager);
        result.put("active", active);
        result.put("nowDate", nowDate);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, prizeResult);
        return response;
    }
}
