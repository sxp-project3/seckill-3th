package com.suixingpay.controller;

import com.suixingpay.pojo.Active;
import com.suixingpay.service.StartAndEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    // 1.根据活动id,后台开始
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void start(@RequestBody Active active){
       //aid =1;
        //active.setId(1);
        startAndEndService.selectActiveByAid(active.getId());

    }
    //2.根据活动id,后台结束
    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public void end(@RequestBody Active active){
        //aid =1;
        //active.setId(1);
        startAndEndService.selectActiveByAid(active.getId());

    }
}
