package com.suixingpay.service;

/**
 * @Author: kongjian
 * @Date: 2019/12/10
 */

import com.suixingpay.mapper.ManagerMapper;
import com.suixingpay.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    @Autowired
    private ExecutorService threadPool;


    @Override
    public Manager searchManagerById(int id) {
        AtomicReference<Manager> manager = new AtomicReference<>();
        threadPool.execute(() -> {
            manager.set(managerMapper.selectUserById(id));
        });
        return manager.get();
    }
}
