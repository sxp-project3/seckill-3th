package com.suixingpay.service;

import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:05
 */
public interface StartAndEndService {
    Response backGroundStart(Integer aId);

    Response backGroundEnd(Integer aId);

    Response updateStatus(Integer aId);

    void insertUser();

    Response getActivityResult(Integer aId);
}
