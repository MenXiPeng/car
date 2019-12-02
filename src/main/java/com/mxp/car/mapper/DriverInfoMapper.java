package com.mxp.car.mapper;

import com.mxp.car.model.Car;
import com.mxp.car.model.DriverInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 23:15
 */
@Mapper
public interface DriverInfoMapper extends BaseMapper<DriverInfo,Long> {
    List<DriverInfo> selectByCommId(Long commissionId);
}
