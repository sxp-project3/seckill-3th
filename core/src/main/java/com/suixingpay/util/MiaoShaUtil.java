package com.suixingpay.util;

/**
 * ClassName MiaoShaUtil
 *
 * @Description TODO
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

}
