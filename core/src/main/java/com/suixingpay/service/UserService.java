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

    Users selectUserById(Users users);

    List<Active> selectActByCity(Active active);

    List<Active> selectNextByCity(String city, Date nextTime);

}
