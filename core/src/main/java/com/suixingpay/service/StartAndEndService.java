package com.suixingpay.service;

import com.suixingpay.pojo.Users;
import com.suixingpay.response.Response;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:05
 */
@Validated
public interface StartAndEndService {
    Response backGroundStart(@NotBlank(message = "活动 id 为空！")Integer aId);

    Response backGroundEnd(@NotBlank(message = "活动 id 为空！")Integer aId);

    Response updateStatus(Integer aId);

    void insertUser();

    Response getActivityResult(Integer aId);
}
