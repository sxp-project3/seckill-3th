package com.suixingpay.service;


import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.Prize;

import java.util.List;

public interface PrizeService {

    Integer selectCount(String territory);

    List<Integer> selectLimit(Dog dog);

}
