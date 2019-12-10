package com.suixingpay.service;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.Manager;

import java.util.List;
import java.util.Map;

/**
 * @Author: kongjian
 * @Date: 2019/12/9
 */

public interface PrizeDemoService {

    Map robPrizeDemo(Active active, Manager manager, String nowDate);

    List getList(Integer id);
}
