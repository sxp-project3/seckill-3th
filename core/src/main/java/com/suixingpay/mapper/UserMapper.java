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
     * @Param: [User] 活动类
     * @Return: User
     * @Author: 段思宇
     * @Date: 2019/12/10 9:35
     */
    Users selectUserById(Users users);


    /**
     * 功能描述: 通过相同地区查询出当前可以直接参加的活动信息
     * @Param: [active] 活动类
     * @Return: List<Active>
     * @Author: 段思宇
     * @Date: 2019/12/10 10:35
     */
    List<Active> selectActByCity(Active active);


    /**
     * 功能描述: 通过相同地区查询出当日可以参加的活动信息
     * @Param: [active] 活动类
     * @Return: List<Active>
     * @Author: 段思宇
     * @Date: 2019/12/10 11:35
     */
    List<Active> selectNextByCity(@Param("city") String city, @Param("nextTime") Date nextTime);

}
