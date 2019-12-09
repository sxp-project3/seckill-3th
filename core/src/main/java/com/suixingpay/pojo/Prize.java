package com.suixingpay.pojo;

import java.util.Date;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 17:29
 */
public class Prize {
    //奖品表id
    private Integer id;
    //活动id
    private Integer activityId;
    //鑫管家id
    private Integer manageId;
    //装机状态
    private Integer status;
    //得奖时间
    private Date prizeTime;
    //创建时间
    private Date createTime;

    public Prize() {
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

    public Date getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(Date prizeTime) {
        this.prizeTime = prizeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
