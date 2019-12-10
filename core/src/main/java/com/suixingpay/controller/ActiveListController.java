package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.handler.GlobalExceptionHandler;
import com.suixingpay.pojo.Active;
import com.suixingpay.response.Response;
import com.suixingpay.service.ActiveService;
import com.suixingpay.util.MiaoShaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName ActiveListController
 *
 * @Description 活动Controller
 * @Author luyun
 * @Date 2019/12/9 11:20
 * @Version 1.0
 **/
@Slf4j
@Controller
public class ActiveListController {

    /**
     * 活动信息
     */
    @Autowired
    ActiveService activeService;

    /**
     * 全局处理异常
     */
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;


    /**
     * 功能描述: <br>
     * 〈〉
     * @Param: [active] 实体类
     * @Return: com.suixingpay.pojo.Response
     * @Author: luyun
     * @Date: 2019/12/9 15:12
     */
    @RequestMapping("/add")
    @ResponseBody
    public Response addActive(@RequestBody Active active) {
        log.info("#######################"+active);
        try {
            String msg="";
            activeService.addActive(active);
            Map<String,String> result=new HashMap<>();
            String title=active.getTitle();
            String city=active.getCity();
            Date startTime=active.getStartTime();
            Date endTime=active.getEndTime();
            active.setCreateTime(new Date());

            if (MiaoShaUtil.isBlank(title)){
                result.put("标题不能为空",msg);
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (MiaoShaUtil.isBlank(city)){
                result.put("城市不能为空",msg);
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (startTime==null){
                result.put("开始时间不能为空",msg);
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (endTime==null){
                result.put("结束时间不能为空",msg);
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            result.put("添加成功",msg);

            return Response.getInstance(CodeEnum.SUCCESS,result);
        }catch (Exception e){
            return globalExceptionHandler.exceptionErrorHandler(e) ;
        }
    }
    
    /**
     * 功能描述: <列表显示>
     * 〈〉
     * @Param: 无
     * @Return: com.suixingpay.response.Response
     * @Author: luyun
     * @Date: 2019/12/9 19:11
     */
    @RequestMapping("/list")
    @ResponseBody
    public Response activeList(){
        try {
            List<Active> active1 = activeService.selectAll();
            Map<String,Object> map=new HashMap<>();
            map.put("list",active1);
            return Response.getInstance(CodeEnum.SUCCESS, map);
        }catch (Exception e){

            return globalExceptionHandler.exceptionErrorHandler(e);
        }
    }


}
