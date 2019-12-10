package com.suixingpay.pojo;

import java.util.Date;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:29
 */
public class PrizeResult {
    //奖品表id
    private Integer id;
    //活动id
    private Integer activityId;
    //鑫管家id
    private Integer manageId;
    //装机状态
    private Integer status;
    //得奖时间
    private String  prizeTime;
    //创建时间
    private Date createTime;
    //沉默用户id
    private Date prizeId;
    public PrizeResult() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(String prizeTime) {
        this.prizeTime = prizeTime;
    }

    public Date getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Date prizeId) {
        this.prizeId = prizeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
