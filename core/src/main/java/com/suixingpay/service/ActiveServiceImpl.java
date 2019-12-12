package com.suixingpay.service;

import com.suixingpay.mapper.ActiveMapper;
import com.suixingpay.pojo.Active;
import com.suixingpay.response.Response;
import com.suixingpay.util.MiaoShaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * 功能描述: <根据日期添加时间>
     * 〈〉
     * @Param: [day]
     * @Return: void
     * @Author: luyun
     * @Date: 2019/12/11 11:54
     */
    public   void addData(Date day,String city)  {
        try {
            Active active=new Active();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = df.format(day);
            Date date = df.parse(s);

            for (int i = 0; i < 23; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.HOUR, 1);
                date = cal.getTime();
                active.setStartTime(date);
                Date endTime=new Date(active.getStartTime().getTime()+180000);
                active.setEndTime(endTime);
                active.setCity(city);
                active.setTitle(active.getCity()+":"+String.valueOf(active.getStartTime() ));
                active.setMaxPrizeNum(500);
                activeMapper.addData(active);
            }

        }catch (Exception e){
            e.getMessage();
        }
    }

}
