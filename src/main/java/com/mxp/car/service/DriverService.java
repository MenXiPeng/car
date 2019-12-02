package com.mxp.car.service;

import com.mxp.car.model.DriverInfo;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/2
 * TIME: 13:18
 */
public interface DriverService extends BaseService<DriverInfo,Long> {
    List<DriverInfo> findByCommId(Long commissionId);
}
