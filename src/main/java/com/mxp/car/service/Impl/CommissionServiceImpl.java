package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.CommissionMapper;
import com.mxp.car.model.Commission;
import com.mxp.car.service.BaseService;
import com.mxp.car.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 22:56
 */
@Service
public class CommissionServiceImpl extends BaseServiceImpl<Commission,Long>  implements CommissionService  {

    @Autowired
    private CommissionMapper commissionMapper;

    @Override
    public BaseMapper<Commission, Long> getMapper() {
        return this.commissionMapper;
    }

    @Override
    public int save(Commission commission) {
        return super.save(commission);
    }
}
