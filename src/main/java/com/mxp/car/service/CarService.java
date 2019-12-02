package com.mxp.car.service;

import com.mxp.car.model.Car;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 23:10
 */
public interface CarService extends BaseService<Car,Long> {
    List<Car> findByCommId(Long commissionId);
}
