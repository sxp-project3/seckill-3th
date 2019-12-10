package com.suixingpay.service;

import com.suixingpay.mapper.PrizeMapper;
import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService{

    @Autowired
    PrizeMapper prizeMapper;

    @Override
    public Integer selectCount(String territory) {
        return prizeMapper.selectCount(territory);
    }

    @Override
    public List<Integer> selectLimit(Dog dog) {
        return prizeMapper.selectLimit(dog);
    }


}
