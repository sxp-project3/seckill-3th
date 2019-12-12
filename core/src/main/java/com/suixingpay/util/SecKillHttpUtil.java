package com.suixingpay.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 提供项目相关的 http 服务
 * <p>
 * Created by Jalr4ever on 2019/12/10.
 */

@Component
public class SecKillHttpUtil {

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 根据 token 的名字获取 token 值
     *
     * @param tokenName token 名字
     * @return token 值
     */
    public String getToken(String tokenName) {
        String token = httpServletRequest.getHeader(tokenName);
        if (token == null) {
            throw new RuntimeException("Token 为空，用户为登录!");
        } else {
            return token;
        }
    }

}
