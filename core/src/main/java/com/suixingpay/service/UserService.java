package com.suixingpay.service;


import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;

import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17:22
 *@descrpition 活动时间的业务逻辑服务
 */

public interface UserService {
    Response<CodeEnum> selectUserById(Users users);
}
