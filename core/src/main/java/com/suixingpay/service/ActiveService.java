package com.suixingpay.service;

import com.suixingpay.pojo.Active;

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


}
