package com.suixingpay.mapper;

import com.suixingpay.pojo.Users;

import java.util.Date;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17：20
 */

public interface UserMapper {

    List<Users> selectUserById(Users users);
}
