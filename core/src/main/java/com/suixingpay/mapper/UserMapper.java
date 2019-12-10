package com.suixingpay.mapper;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Users;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17：20
 */

public interface UserMapper {

    Users selectUserById(Users users);

    Active selectActByCity(Active active);

    Active selectNextByCity(@Param("city") String city, @Param("nextTime") Date nextTime);

}
