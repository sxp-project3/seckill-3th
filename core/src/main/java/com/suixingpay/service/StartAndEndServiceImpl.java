package com.suixingpay.service;

import com.suixingpay.mapper.StartAndEndMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:04
 */
@Service
public class StartAndEndServiceImpl implements StartAndEndService{
    @Autowired
    private StartAndEndMapper startAndEndMapper;
    @Override
    public Integer selectNumByAid(int aId){
        return startAndEndMapper.selectNumByAid(aId);
    }
}
