package com.suixingpay.controller;


import com.suixingpay.pojo.Dog;
import com.suixingpay.pojo.Prize;
import com.suixingpay.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("select")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;


    @RequestMapping("selectCount")
    public Integer selectCount(String territory){
        return prizeService.selectCount(territory);
    }

    @RequestMapping("selectLimit")
    public List<Integer> selectLimit(Dog dog){
        return prizeService.selectLimit(dog);
    }
}
