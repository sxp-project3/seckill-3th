package com.suixingpay.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 20:15
 */
@Mapper
public interface StartAndEndMapper {

    //根据活动id,查找该活动奖品数量
    Integer selectNumByAid(int aId);
}
