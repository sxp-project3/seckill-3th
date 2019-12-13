package com.suixingpay.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * ClassName MiaoShaUtil
 *
 * @Description 工具类
 * @Author luyun
 * @Date 2019/12/9 15:14
 * @Version 1.0
 **/

public class MiaoShaUtil {

    /**
     * 功能描述: <检查是否为空>
     * 〈〉
     * @Param: [str]
     * @Return: boolean
     * @Author: luyun
     * @Date: 2019/12/9 15:15
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }


    /**
     * 功能描述: <获取月份>
     * 〈〉
     * @Param: [date]
     * @Return: void
     * @Author: luyun
     * @Date: 2019/12/11 11:28
     */
    public static String   getMonth(Date date){
        DateFormat dateFormat=DateFormat.getDateInstance();
        return dateFormat.format(date);
    }

}
