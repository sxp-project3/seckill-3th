package com.suixingpay.service;

/**
 * @Author: kongjian
 * @Date: 2019/12/10
 */

import com.suixingpay.mapper.ManagerMapper;
import com.suixingpay.pojo.Manager;
import com.suixingpay.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;


    @Override
    public Manager searchManagerById (int id) {
        return managerMapper.selectUserById(id);
    }
}
