package com.suixingpay.mapper;


import com.suixingpay.pojo.Active;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * 功能描述: <活动Mapper>
 * 〈〉
 * @Author: luyun
 * @Date: 2019/12/10 9:36
 */
@Mapper
public interface ActiveMapper {
    /**
     * 功能描述: <添加活动>
     * 〈〉
     * @Param: [active] 活动类
     * @Return: int
     * @Author: luyun
     * @Date: 2019/12/10 9:35
     */
    int addActive(Active active);

    /**
     * 功能描述: <查询所有的活动>
     * 〈〉
     * @Param: []
     * @Return: java.util.List<com.suixingpay.pojo.Active>
     * @Author: luyun
     * @Date: 2019/12/10 9:35
     */
    List<Active> selectAll();

    /**
     * 功能描述: <根据城市添加整点活动>
     * 〈〉
     * @Param: [city]
     * @Return: int
     * @Author: luyun
     * @Date: 2019/12/11 10:35
     */

    Integer addData(Active active);


}
