package com.suixingpay.mapper;

import com.suixingpay.pojo.Active;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/9 20:15
 */
@Mapper
public interface StartAndEndMapper {

    //根据活动id,查找该活动信息
    List<Active> selectActiveByAid(int aId);
   //修改奖品表activity_id为当前活动id
    Integer updatePrizeActivityId(@Param("prizeIdList") List<Integer> prizeIdList,
                                              @Param("activityId") Integer activityId);
}
