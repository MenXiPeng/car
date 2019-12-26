package com.mxp.car.mapper;

import com.mxp.car.model.Travel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 23:21
 */
@Mapper
public interface TravelMapper extends BaseMapper<Travel,Long> {
    List<Travel> selectByCommId(Long commissionId);
    int updateByCommId(Travel travel);
    int updateById(Travel travel);
    int deleteByCommId(Long commissionId);
}
