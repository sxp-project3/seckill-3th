package com.suixingpay.mapper;

import com.suixingpay.pojo.Active;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 20:15
 */
@Mapper
public interface StartAndEndMapper {

    //根据活动id,查找该活动奖品数量
    List<Active> selectNumByAid(int aId);
}
