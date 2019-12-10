package com.suixingpay.service;

import com.suixingpay.mapper.ActiveMapper;
import com.suixingpay.pojo.Active;
import com.suixingpay.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 功能描述: <添加活动>
     * 〈〉
     * @Param: [active]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/9 15:13
     */
    public int addActive(Active active){

        return activeMapper.addActive(active);

    }
    /**
     * 功能描述: <br>
     * 〈〉
     * @Param: [active]
     * @Return: java.util.List<com.suixingpay.pojo.Active>
     * @Author: luyun
     * @Date: 2019/12/9 16:22
     * @return
     */
    public List<Active> selectAll(){
        List<Active> activeList=activeMapper.selectAll();
        return activeList;
    }




}
