package com.suixingpay.mapper;

import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface PrizeMapper {
    int deleteByPrimaryKey(Integer id);

    List<Prize> selectByPrimaryKey(Integer id);


    Integer selectCount(String territory);

    List<Integer> selectLimit(Dog dog);

}