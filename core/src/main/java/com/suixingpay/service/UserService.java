package com.suixingpay.service;


import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Users;

import java.util.Date;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17:22
 *@descrpition 活动时间的业务逻辑服务
 */

public interface UserService {


    /**
     * 功能描述: <查询管家信息>
     * @Return: com.suixingpay.pojo.Users
     * @Author: 段思宇
     * @Date: 2019/12/10 19:33
     */
    Users selectUserById(Users users);


    /**
     * 功能描述: <查询可立即参加的活动信息>
     * @Return: com.suixingpay.pojo.Active
     * @Author: 段思宇
     * @Date: 2019/12/10 20:15
     */
    List<Active> selectActByCity(Active active);


    /**
     * 功能描述: <查询当日可参加的活动信息>
     * @Return: com.suixingpay.pojo.Active
     * @Author: 段思宇
     * @Date: 2019/12/10 20:33
     */
    List<Active> selectNextByCity(String city, Date nextTime);

}
