package com.mxp.car.service;

import com.mxp.car.model.Travel;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/2
 * TIME: 13:21
 */
public interface TravelService extends BaseService<Travel,Long> {
    List<Travel> findByCommId(Long commissionId);
}
