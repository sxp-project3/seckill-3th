package com.suixingpay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetNextDay {
    public static Date getDayTime() {
        Calendar cal = Calendar.getInstance();
        //这里改为1 - 获取到第二天的时间
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time = cal.getTime();
//        String nextDay = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(time);
//        return nextDay;
        return time;
    }
}
