package com.suixingpay.pojo;

public class Cat {

    private int Manager_id;
    private int Prize_id;
    private String Get_prize_time;

    public int getManager_id() {
        return Manager_id;
    }

    public void setManager_id(int manager_id) {
        Manager_id = manager_id;
    }

    public int getPrize_id() {
        return Prize_id;
    }

    public void setPrize_id(int prize_id) {
        Prize_id = prize_id;
    }

    public String getGet_prize_time() {
        return Get_prize_time;
    }

    public void setGet_prize_time(String get_prize_time) {
        Get_prize_time = get_prize_time;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "Manager_id=" + Manager_id +
                ", Prize_id=" + Prize_id +
                ", Get_prize_time='" + Get_prize_time + '\'' +
                '}';
    }
}
