package com.suixingpay.service;

import com.suixingpay.mapper.UserMapper;
import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17:25
 * @description 活动时间的业务逻辑服务具体实现
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users selectUserById(Users users) {

        Users user = userMapper.selectUserById(users);
        return user;
    }

    @Override
    public List<Active> selectActByCity(Active active) {

        List<Active> act = userMapper.selectActByCity(active);
        return act;
    }

    @Override
    public List<Active> selectNextByCity(String city, Date nextTime) {

        List<Active> acti = userMapper.selectNextByCity(city,nextTime);
        return acti;
    }
}
