package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.UserMapper;
import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    public Response<CodeEnum> selectUserById(Users users) {
        List<Users> list = userMapper.selectUserById(users);
        return Response.getInstance(CodeEnum.SUCCESS,list);
    }
}
