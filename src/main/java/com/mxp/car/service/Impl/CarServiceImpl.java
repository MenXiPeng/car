package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.CarMapper;
import com.mxp.car.model.Car;
import com.mxp.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 23:11
 */
@Service
public class CarServiceImpl extends BaseServiceImpl<Car,Long> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public BaseMapper<Car, Long> getMapper() {
        return this.carMapper;
    }

    @Override
    public List<Car> findByCommId(Long commissionId) {
        return this.carMapper.selectByCommId(commissionId);
    }
}
