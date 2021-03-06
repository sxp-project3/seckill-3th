package com.suixingpay.service;

import com.suixingpay.pojo.Active;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName ActiveService
 *
 * @Description 活动service
 * @Author luyun
 * @Date 2019/12/9 14:11
 * @Version 1.0
 **/

public interface ActiveService {
    /**
     * 功能描述: <添加活动>
     * 〈〉
     * @Param: [active]
     * @Return: com.suixingpay.pojo.Active
     * @Author: luyun
     * @Date: 2019/12/9 19:33
     */
    int addActive(Active active);

    /**
     * 功能描述: <查询活动列表>
     * 〈〉
     * @Param: []
     * @Return: java.util.List<com.suixingpay.pojo.Active>
     * @Author: luyun
     * @Date: 2019/12/9 19:33
     */
    List<Active> selectAll();
    /**
     * 功能描述: <根据城市添加一个整点活动>
     * 〈〉
     * @Param: [city]
     * @Author: luyun
     * @Date: 2019/12/11 10:36
     */
    void addData(Date date,String city);

    /**
     * 功能描述: <根据id获得单条活动信息>
     * 〈〉
     * @Param: [id]
     * @Return: com.suixingpay.pojo.Active
     * @Author: kongjian
     * @Date: 2019/12/12 10:53
     */
    Active getOneById(int id);
}
