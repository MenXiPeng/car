package com.mxp.car.mapper;

import com.mxp.car.model.Commission;
import org.apache.ibatis.annotations.Mapper;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 23:27
 */
@Mapper
public interface CommissionMapper extends BaseMapper<Commission,Long> {
    int updateById(Commission commission);
}
