package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Manager;
import com.suixingpay.response.Response;
import com.suixingpay.service.ActiveService;
import com.suixingpay.service.ManagerService;
import com.suixingpay.service.PrizeDemoService;
import com.suixingpay.util.SecKillHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    private SecKillHttpUtil secKillHttpUtil;

    @RequestMapping(value = "/rob-demo", method = RequestMethod.POST)
    public Response robPrizeDemo(@RequestBody Map<String, String> param) {
        // Map<String, String> values = (LinkedHashMap<String, String>) param;
        String activityStringId = param.get("activityId");
        String userStringId = secKillHttpUtil.getToken("token");
        Integer userId = Integer.parseInt(userStringId);
         log.info("userId:"+userId);
        Integer activityId = Integer.parseInt(activityStringId);
         log.info("activityId:"+activityId);
        // 获取管家信息实体
        Manager manager = managerService.searchManagerById(userId);
        // 获取活动信息
        Active active = activeService.getOneById(activityId);
        // 获取当前系统时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = dateFormat.format(now);
        // Map<String, Object> prizeResult = null;
        Response<Map<String, HashMap>> response = null;
        try {
            String prizeStringResult = prizeDemoService.robPrizeDemo(active, manager, nowDate);
            response = Response.getInstance(CodeEnum.SUCCESS, prizeStringResult);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            response = Response.getInstance(CodeEnum.FAIL, e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/login-demo")
    public Response fakeLogin() {
        String result = prizeDemoService.fakeLogin();
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);
        return response;
    }
}
