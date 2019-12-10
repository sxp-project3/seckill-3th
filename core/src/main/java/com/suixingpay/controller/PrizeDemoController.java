package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Manager;
import com.suixingpay.response.Response;
import com.suixingpay.service.ActiveService;
import com.suixingpay.service.ManagerService;
import com.suixingpay.service.PrizeDemoService;
import com.suixingpay.service.UserService;
import com.suixingpay.util.SecKillHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private PrizeDemoService prizeDemoService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ActiveService activeService;

    @Autowired
    private SecKillHttpUtil secKillHttpUtil;

    @RequestMapping("/rob-demo")
    @ResponseBody
    public Response robPrizeDemo(@RequestParam(value = "activityId") String activityStringId) {
        String userStringId = secKillHttpUtil.getToken("token");
        Integer userId = Integer.parseInt(userStringId);
        // log.info("userId:"+userId);
        Integer activityId = Integer.parseInt(activityStringId);
        // log.info("activityId:"+activityId);
        // 获取管家信息实体
        Manager manager = managerService.searchManagerById(userId);
        // 获取活动信息
        Active active = activeService.getOneById(activityId);
        // 获取当前系统时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = dateFormat.format(now);
        Map<String, Object> prizeResult = null;
        Response<Map<String, HashMap>> response = null;
        try {
            String prizeStringResult = prizeDemoService.robPrizeDemo(active, manager, nowDate);
            prizeResult.put("prizeResult", prizeStringResult);
            response = Response.getInstance(CodeEnum.SUCCESS, prizeResult);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            response = Response.getInstance(CodeEnum.FAIL, e.getMessage());
        }
        return response;
    }
}
