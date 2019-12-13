package com.suixingpay.service;

/**
 * @author hyx
 */

import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.Prize;

import java.util.List;

public interface PrizeService {

    Integer selectCount(String territory);

    List<Integer> selectLimit(Dog dog);

}
