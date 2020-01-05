package com.mxp.car.service;

import com.mxp.car.model.Commission;

import java.util.Map;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 22:54
 */
public interface CommissionService extends BaseService<Commission, Long> {
    int saveAll(Map<String,Map<String,Object>> map);
    int updateAll(Map<String,Map<String,Object>> map);
    int deleteAll(Long commissionId);
}
