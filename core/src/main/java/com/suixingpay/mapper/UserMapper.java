package com.suixingpay.mapper;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 段思宇
 * @date 2019/12/9 17：20
 */

public interface UserMapper {

    /**
     * 功能描述: 通过管家id查询管家信息
     * @Param: [User] 用户类类
     * @Return: User
     */
    Users selectUserById(Users users);


    /**
     * 功能描述: 通过相同地区查询出当前可以直接参加的活动信息
     * @Param: 活动类里的地区，当前时间
     * @Return: List<Active>
     */
    List<Active> selectActByCity(@Param("city") String city, @Param("date") Date date);


    /**
     * 功能描述: 通过相同地区查询出当日可以参加的活动信息
     * @Param: 活动类里的地区，第二天的00：00：00，当前时间
     * @Return: List<Active>
     */
    List<Active> selectNextByCity(@Param("city") String city, @Param("nextTime") Date nextTime, @Param("date") Date date);

}
