package com.suixingpay.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.suixingpay.pojo.Active;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 詹文良
 * @description 满足特定需求，与前端交互的实体
 */
public class ActiveVo {

    private int id;
    private String title;
    private int status;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private int maxPrizeNum;
    private Date createTime;
    private Date updateTime;
    private Integer isValid;

    public ActiveVo(Active active,Integer isValid) {
        this.id = active.getId();
        this.title = active.getTitle();
        this.status = active.getStatus();
        this.city = active.getCity();
        this.startTime = active.getStartTime();
        this.endTime = active.getEndTime();
        this.maxPrizeNum = active.getMaxPrizeNum();
        this.createTime = active.getCreateTime();
        this.updateTime = active.getUpdateTime();
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMaxPrizeNum() {
        return maxPrizeNum;
    }

    public void setMaxPrizeNum(int maxPrizeNum) {
        this.maxPrizeNum = maxPrizeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
