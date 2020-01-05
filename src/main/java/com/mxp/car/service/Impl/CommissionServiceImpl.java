package com.mxp.car.service.Impl;

import com.mxp.car.mapper.*;
import com.mxp.car.model.*;
import com.mxp.car.service.CommissionService;
import com.mxp.car.service.PhotoService;
import com.mxp.car.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 22:56
 */
@Log4j2
@Service
public class CommissionServiceImpl extends BaseServiceImpl<Commission,Long>  implements CommissionService  {

    @Autowired
    private CommissionMapper commissionMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private DriverInfoMapper driverInfoMapper;
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private PhotoService photoService;

    @Override
    public BaseMapper<Commission, Long> getMapper() {
        return this.commissionMapper;
    }




    @Override
    public int saveAll(Map<String, Map<String,Object>> map) {
        //var mainPhoto = map.get("mainPhoto");
        //var threePhoto = map.get("threePhoto");
        var comMap = Utils.CarUtil.mapTo(map.get("commission"));
        var mainCar = Utils.CarUtil.mapTo(map.get("mainCar"));
        var threeCarMap = Utils.CarUtil.mapTo(map.get("threeCar"));
        var mainDriver = Utils.CarUtil.mapTo(map.get("mainDriver"));
        var threeDriver = Utils.CarUtil.mapTo(map.get("threeDriver"));
        var mainTravel = Utils.CarUtil.mapTo(map.get("mainTravel"));
        var threeTravel = Utils.CarUtil.mapTo(map.get("threeTravel"));
        var commission = new Commission();
        var isCar = new Car();
        var isDriverInfo = new DriverInfo();
        var isTravel = new Travel();
        try {
            var commissionId = Utils.CarUtil.getId();
            var usrId = 1L;
            comMap.put("commissionId",commissionId);
            comMap.put("userId",usrId);
            comMap.put("createTime",LocalDateTime.now());
            BeanUtils.populate(commission, comMap);
            log.info(commission);
            var com = this.commissionMapper.insert(commission);
            // 主车
            Long mainCarId = Utils.CarUtil.getId();
            mainCar.put("carId", mainCarId);
            mainCar.put("userId", usrId);
            mainCar.put("commissionId",commissionId);
            mainCar.put("createTime",LocalDateTime.now());
            BeanUtils.populate(isCar,mainCar);
            // 绑定photo和car
            List<Photo> mainPhotos = new ArrayList<>();
            isCar.getPhoto().forEach(photoId -> {
                Photo photo = new Photo();
                photo.setCarId(mainCarId);
                photo.setPhotoId(Long.valueOf(photoId));
                mainPhotos.add(photo);
            });
            log.info(mainPhotos);
            var car1 = this.carMapper.insert(isCar);
            this.photoService.modifyByList(mainPhotos);
            // 三车
            Long threeCarId = Utils.CarUtil.getId();
            threeCarMap.put("carId", threeCarId);
            threeCarMap.put("userId", usrId);
            threeCarMap.put("commissionId",commissionId);
            threeCarMap.put("createTime",LocalDateTime.now());
            BeanUtils.populate(isCar,threeCarMap);
            // 绑定photo和car
            List<Photo> threePhotos = new ArrayList<>();
            isCar.getPhoto().forEach(photoId -> {
                Photo photo = new Photo();
                photo.setCarId(threeCarId);
                photo.setPhotoId(Long.valueOf(photoId));
                threePhotos.add(photo);
            });
            log.info(threePhotos);
            var car2 = this.carMapper.insert(isCar);
            this.photoService.modifyByList(threePhotos);
            // 主车驾驶证
            mainDriver.put("createTime",LocalDateTime.now());
            mainDriver.put("driverId",Utils.CarUtil.getId());
            mainDriver.put("commissionId",commissionId);
            BeanUtils.populate(isDriverInfo,mainDriver);
            log.info(isDriverInfo);
            var d1 = this.driverInfoMapper.insert(isDriverInfo);
            // 三车驾驶证
            threeDriver.put("driverId",Utils.CarUtil.getId());
            threeDriver.put("commissionId",commissionId);
            threeDriver.put("createTime",LocalDateTime.now());
            BeanUtils.populate(isDriverInfo,threeDriver);
            log.info(isDriverInfo);
            var d2 = this.driverInfoMapper.insert(isDriverInfo);
            // 主车行驶证
            mainTravel.put("createTime",LocalDateTime.now());
            mainTravel.put("travelId",Utils.CarUtil.getId());
            mainTravel.put("commissionId",commissionId);
            BeanUtils.populate(isTravel,mainTravel);
            log.info(isTravel);
            var t1 = this.travelMapper.insert(isTravel);
            // 三车行驶证
            threeTravel.put("createTime",LocalDateTime.now());
            threeTravel.put("travelId",Utils.CarUtil.getId());
            threeTravel.put("commissionId",commissionId);
            BeanUtils.populate(isTravel,threeTravel);
            log.info(isTravel);
            var t2= this.travelMapper.insert(isTravel);
            if (com > 0 && car1 > 0 && car2 > 0 && d1 > 0 && d2 > 0 && t1 > 0 && t2 > 0){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("-==添加异常回滚数据==-",e);
        }
        return 0;
    }

    @Override
    public int updateAll(Map<String, Map<String, Object>> map) {
        var comMap = Utils.CarUtil.mapTo(map.get("commission"));
        var mainCar = Utils.CarUtil.mapTo(map.get("mainCar"));
        var threeCarMap = Utils.CarUtil.mapTo(map.get("threeCar"));
        var mainDriver = Utils.CarUtil.mapTo(map.get("mainDriver"));
        var threeDriver = Utils.CarUtil.mapTo(map.get("threeDriver"));
        var mainTravel = Utils.CarUtil.mapTo(map.get("mainTravel"));
        var threeTravel = Utils.CarUtil.mapTo(map.get("threeTravel"));
        var commissionId = comMap.get("commissionId");
        var commission = new Commission();
        var isCar = new Car();
        var isDriverInfo = new DriverInfo();
        var isTravel = new Travel();
        try {
            var usrId = 1L;
            comMap.put("commissionId",commissionId);
            BeanUtils.populate(commission, comMap);
            log.info(commission);
            var com = this.commissionMapper.updateById(commission);
            // 主车
            BeanUtils.populate(isCar,mainCar);
            log.info(isCar);
            var car1 = this.carMapper.updateById(isCar);
            // 三车
            BeanUtils.populate(isCar,threeCarMap);
            log.info(isCar);
            var car2 = this.carMapper.updateById(isCar);
            // 主车驾驶证
            BeanUtils.populate(isDriverInfo,mainDriver);
            log.info(isDriverInfo);
            var d1 = this.driverInfoMapper.updateById(isDriverInfo);
            // 三车驾驶证
            BeanUtils.populate(isDriverInfo,threeDriver);
            log.info(isDriverInfo);
            var d2 = this.driverInfoMapper.updateById(isDriverInfo);
            // 主车行驶证
            BeanUtils.populate(isTravel,mainTravel);
            log.info(isTravel);
            var t1 = this.travelMapper.updateById(isTravel);
            // 三车行驶证
            BeanUtils.populate(isTravel,threeTravel);
            log.info(isTravel);
            var t2= this.travelMapper.updateById(isTravel);
            if (com > 0 && car1 > 0 && car2 > 0 && d1 > 0 && d2 > 0 && t1 > 0 && t2 > 0){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("-==修改异常回滚数据==-",e);
        }
        return 0;
    }

    @Override
    public int deleteAll(Long commissionId) {
        var com =this.commissionMapper.deleteById(commissionId);
        var car  = this.carMapper.deleteByCommId(commissionId);
        var driver = this.driverInfoMapper.deleteByCommId(commissionId);
        var travel = this.travelMapper.deleteByCommId(commissionId);
        if (com > 0 && car > 0  && driver > 0 && travel > 0){
            return 1;
        }else {
            return 0;
        }
    }
}
