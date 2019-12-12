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
import org.springframework.web.bind.annotation.*;

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

            Map<String,String> result=new HashMap<>();
            String title=active.getTitle();
            String city=active.getCity();
            Date startTime=active.getStartTime();
            Date endTime=active.getEndTime();
            int maxPrizeNum=active.getMaxPrizeNum();
            active.setCreateTime(new Date());

            if (MiaoShaUtil.isBlank(title)){
                result.put("提示信息","标题不能为空");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (MiaoShaUtil.isBlank(city)){
                result.put("提示信息","城市不能为空");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (maxPrizeNum==0||maxPrizeNum<0){
                result.put("提示信息","数量不能为空");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (startTime==null){
                result.put("提示信息","开始时间不能为空");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (endTime==null){
                result.put("提示信息","结束时间不能为空");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if(startTime.equals(endTime)){
                result.put("提示信息","开始结束时间不能相同");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            if (startTime.after(endTime)){
                result.put("提示信息","开始时间不能大于结束时间");
                return Response.getInstance(CodeEnum.FAIL,result);
            }
            List<Active> activeList=activeService.selectAll();
            for (Active as:activeList){
                Date oldEndTime=as.getEndTime();
                Date oldStartTime=as.getStartTime();
                if(!((startTime.before(endTime)&&endTime.before(oldStartTime)) || (startTime.before(endTime)&&startTime.after(oldEndTime))))
                {
                    result.put("提示信息","时间冲突");
                    return Response.getInstance(CodeEnum.FAIL,result);
                }
            }
                activeService.addActive(active);
                result.put("提示信息", "添加成功");
                return Response.getInstance(CodeEnum.SUCCESS, result);
        }catch (Exception e){
            return Response.getInstance(CodeEnum.FAIL,e.getMessage());
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
            return Response.getInstance(CodeEnum.FAIL,e.getMessage());
        }
    }

    /**
     * 功能描述: <根据日期添加活动>
     * 〈〉
     * @Param: [date]
     * @Return: com.suixingpay.response.Response
     * @Author: luyun
     * @Date: 2019/12/11 11:22
     */
    @RequestMapping("/addData")
    @ResponseBody
    public Response addData(@RequestParam("date") Date date,@RequestParam("city") String city ){
        try {
            activeService.addData(date,city);
            return Response.getInstance(CodeEnum.SUCCESS);
        }catch (Exception e){
            return Response.getInstance(CodeEnum.FAIL);
        }
    }
}
