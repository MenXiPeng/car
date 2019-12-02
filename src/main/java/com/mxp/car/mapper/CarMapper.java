package com.mxp.car.mapper;

import com.mxp.car.model.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 23:01
 */
@Mapper
public interface CarMapper extends BaseMapper<Car,Long> {
    List<Car> selectByCommId(Long commissionId);
}
