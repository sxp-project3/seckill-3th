package com.suixingpay.mapper;

import com.suixingpay.pojo.Active;
import com.suixingpay.pojo.PrizeResult;
import com.suixingpay.pojo.Users;
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

    //修改奖品表activity_id字段为0
    Integer updatePrizeByActivityId(@Param("prizeIdResidue") List<Integer> prizeIdResidue);

    //插入一条得奖结果表
    Integer insertPrizeResult(PrizeResult prizeResult);

    Integer insertUser (Users user);

    //根据开始结束修改活动表的status
    Integer updateActiveliststatus (Active active);
}
