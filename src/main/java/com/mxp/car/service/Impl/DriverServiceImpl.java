package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.DriverInfoMapper;
import com.mxp.car.model.DriverInfo;
import com.mxp.car.service.BaseService;
import com.mxp.car.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/2
 * TIME: 13:19
 */
@Service
public class DriverServiceImpl extends BaseServiceImpl<DriverInfo,Long> implements DriverService {

    @Autowired
    private DriverInfoMapper driverInfoMapper;

    @Override
    public BaseMapper<DriverInfo, Long> getMapper() {
        return this.driverInfoMapper;
    }

    @Override
    public List<DriverInfo> findByCommId(Long commissionId) {
        return this.driverInfoMapper.selectByCommId(commissionId);
    }
}
