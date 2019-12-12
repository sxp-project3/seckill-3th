package com.suixingpay.controller;

import com.suixingpay.pojo.Active;
import com.suixingpay.response.Response;
import com.suixingpay.service.StartAndEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:00
 * 用于后台控制活动开始结束功能
 */
@RestController
@RequestMapping("/background")
public class StartAndEndController {
    @Autowired
    private StartAndEndService startAndEndService;

    /*1.根据活动id,后台开始功能
    后台管理者点击开始，活动才能开始，
    将一定数量的沉默用户插入到redis,并且修改奖品表所参与活动id字段为当前活动id*/
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public Response start(@RequestBody Active active) {
        return startAndEndService.backGroundStart(active.getId());

    }


    /*2.根据活动id,后台结束
     * 后台管理者点击结束，将活动结果表插入数据，并且在奖品表中修改活动中剩余的沉默用户id为0*/
    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public Response end(@RequestBody Active active) {
        return startAndEndService.backGroundEnd(active.getId());

    }

    /*循环插入数据到用户表，不作为功能性*/
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert() {
        startAndEndService.insertUser();
    }

//    @RequestMapping(value = "activity-result", method = RequestMethod.POST)
//    public Response activityResult(@RequestBody Map<String, String> param) {
//        return
//    }

}
