package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.TravelMapper;
import com.mxp.car.model.Travel;
import com.mxp.car.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/2
 * TIME: 13:22
 */
@Service
public class TravelServiceImpl extends BaseServiceImpl<Travel,Long> implements TravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Override
    public BaseMapper<Travel, Long> getMapper() {
        return this.travelMapper;
    }

    @Override
    public List<Travel> findByCommId(Long commissionId) {
        return this.travelMapper.selectByCommId(commissionId);
    }
}
