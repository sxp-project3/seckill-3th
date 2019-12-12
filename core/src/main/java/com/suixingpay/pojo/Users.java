package com.suixingpay.pojo;

import java.util.Date;

/**
 *数据库的管家信息的pojo映射
 * @author 段思宇
 * @time 2019-12-9
 */

public class Users {
    private Integer id;
    private String userName;
    private String userCity;



    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

}
