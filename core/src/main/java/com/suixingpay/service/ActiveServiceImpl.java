package com.suixingpay.service;

import com.suixingpay.mapper.ActiveMapper;
import com.suixingpay.pojo.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName ActiveService
 *
 * @Description TODO
 * @Author luyun
 * @Date 2019/12/9 14:11
 * @Version 1.0
 **/
@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;

    @Autowired
    private ExecutorService executorService;

    /**
     * 功能描述: <添加活动>
     * 〈〉
     *
     * @Param: [active]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/9 15:13
     */
    public int addActive(Active active) {

        return activeMapper.addActive(active);

    }

    /**
     * 功能描述: <br>
     * 〈〉
     *
     * @return
     * @Param: [active]
     * @Return: java.util.List<com.suixingpay.pojo.Active>
     * @Author: luyun
     * @Date: 2019/12/9 16:22
     */
    public List<Active> selectAll() {
        List<Active> activeList = activeMapper.selectAll();
        return activeList;
    }

    @Override
    public Active getOneById(int id) {
        AtomicReference<Active> active = new AtomicReference<>();
        executorService.execute(() -> {
            active.set(activeMapper.selectOneById(id));
        });
        return active.get();
    }


}
