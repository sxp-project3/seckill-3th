package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;
import com.suixingpay.service.UserService;
import com.suixingpay.util.GetNextDay;
import com.suixingpay.vo.ActiveVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


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
    public Response selectUserById(@RequestBody Users users){
        LOGGER.info("接收的参数为[{}]", users);
        Map<String, Object> result = new HashMap<>();

        //传参判空
        Integer id = users.getid();
        if (id == null){
            return  new Response("1","当前管家id不存在",null);
        }


        //第一次查询，并拿出city字段用作下一个的查询条件
        Users user = userService.selectUserById(users);
        String info = user.getUserCity();
        Active active = new Active();
        active.setCity(info);


        //第二次查询，获取到第一次查询结果做参数
        LOGGER.info("第二次查询传入的参数为[{}]", active.getCity());


        //查询出当前可抢的活动信息
        List<Active> actLive = userService.selectActByCity(active);


        //查询当日可参加的活动信息
        // 获取第二天的时间，只获取到第二天的 00：00：00；
        Date nextTime = GetNextDay.getDayTime();


        //限制到最近的一个可参加的活动
        List<Active> actNext = userService.selectNextByCity(active.getCity(), nextTime);


        // 当前可立即参加的活动为空，也可以继续执行
        Active actLiveExac = null;
        if (actLive.size() == 0) {
            actLiveExac = new Active();
            result.put("validActInfo", null);
        } else {
            actLiveExac = actLive.get(0);
        }


        //今日即将参加的活动为空，也可以继续执行
        Active actNextExac = null;
        if (actNext.size() == 0){
            actNextExac = new Active();
            result.put("nextActInfo", null);
        }else {
            actNextExac = actNext.get(0);
        }


        // 初始化 isvalid 信息
        ActiveVo activeLiveVo = new ActiveVo(actLiveExac, 1);
        ActiveVo activeNextVo = new ActiveVo(actNextExac, 0);

        //装结果准备返回

        result.put("userName", user.getUserName());
        result.put("userCity", user.getUserCity());

        result.put("validActInfo", activeLiveVo);
        result.put("nextActInfo", activeNextVo);

        //获取系统当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        result.put("date",str);
        //将所有结果全部返回
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);
        return response;
    }
}
