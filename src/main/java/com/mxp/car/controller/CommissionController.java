package com.mxp.car.controller;

import com.mxp.car.config.Config;
import com.mxp.car.model.Car;
import com.mxp.car.model.Commission;
import com.mxp.car.model.DriverInfo;
import com.mxp.car.model.Travel;
import com.mxp.car.service.CarService;
import com.mxp.car.service.CommissionService;
import com.mxp.car.service.DriverService;
import com.mxp.car.service.TravelService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 20:57
 */
@Log4j2
@RestController
public class CommissionController {

    @Autowired
    private Executor asyncExecutor;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private TravelService travelService;

    @PostMapping("/{pageNum}/{pageSize}")
    public DeferredResult<ResultRtn> getComInfo(@PathVariable int pageNum,
                                                @PathVariable int pageSize,
                                                @RequestBody Map<String, Object> param){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var listByPage = commissionService.findListByPage(pageNum,pageSize,param);
            if(listByPage.isEmpty()) {
                log.info("----=== 获取委托单列表失败 ===----");
                result.setResult(ResultRtn.of(StatusCode.NULL_RESULT));
            } else {
                var pageInfo = ResultRtn.PageInfo.of(listByPage.getPageNum(), listByPage.getPageSize(),listByPage.getTotal());
                result.setResult(ResultRtn.of(StatusCode.SUCCESS, pageInfo, listByPage));
            }
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==获取委托单列表异常或超时==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return "Error";
                });
        return result;
    }

    @PostMapping("/addCom")
    public DeferredResult<ResultRtn> addCommission(@RequestBody Map<String,Map<String,String>> param){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var status = commissionService.saveAll(param);
            if (status > 0){
                log.info("-==添加委托单成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==添加委托单失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            return Config.SUCCESS;
        },asyncExecutor).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==插入委托单超时或发生异常==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return Config.ERROR;
                });
        return result;
    }

    @PostMapping("/modifyCom")
    public DeferredResult<ResultRtn> modifyCommission(@RequestBody Map<String,Map<String,String>> param){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var status = commissionService.updateAll(param);
            if (status > 0){
                log.info("-==修改委托单成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==修改委托单失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            return Config.SUCCESS;
        },asyncExecutor).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==修改委托单超时或发生异常==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return Config.ERROR;
                });
        return result;
    }

    @PostMapping("/deleteCom")
    public DeferredResult<ResultRtn> deleteCom(@RequestBody Commission commission){
        var result = new DeferredResult<ResultRtn>();
        var status = commissionService.deleteAll(commission.getCommissionId());
        if (status > 0){
            log.info("-==删除委托单成功==-");
            result.setResult(ResultRtn.of(StatusCode.SUCCESS));
        }else {
            log.warn("-==删除委托单失败==-");
            result.setResult(ResultRtn.of(StatusCode.ERROR));
        }
        return result;
    }

    @PostMapping("/details")
    public DeferredResult<ResultRtn> details(@RequestBody Commission commission){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> this.commissionService.findById(commission.getCommissionId())).thenCombineAsync(CompletableFuture.supplyAsync(() -> this.carService.findByCommId(commission.getCommissionId())),(commissions, carList) -> {
            var map = new HashMap<String,Object>();
            var mainCar = carList.stream().filter(car -> car.getIsMainCar() == 1).findAny().orElse(new Car());
            var threeCar = carList.stream().filter(car -> car.getIsMainCar() == 2).findAny().orElse(new Car());
            map.put("commission",commissions);
            map.put("mainCar",mainCar);
            map.put("threeCar",threeCar);
            return map;
        },asyncExecutor).thenCombineAsync(CompletableFuture.supplyAsync(() -> this.driverService.findByCommId(commission.getCommissionId())),(map, driverInfoList) -> {
            var mainDriver = driverInfoList.stream().filter(driverInfo -> driverInfo.getIsMainDriver() == 1).findAny().orElse(new DriverInfo());
            var threeDriver = driverInfoList.stream().filter(driverInfo -> driverInfo.getIsMainDriver() == 2).findAny().orElse(new DriverInfo());
            map.put("mainDriver",mainDriver);
            map.put("threeDriver",threeDriver);
            return map;
        },asyncExecutor).thenCombineAsync(CompletableFuture.supplyAsync(() -> this.travelService.findByCommId(commission.getCommissionId())),(map,travelList) -> {
            var mainTravel = travelList.stream().filter(travel -> travel.getIsMainTravel() == 1).findAny().orElse(new Travel());
            var threeTravel = travelList.stream().filter(travel -> travel.getIsMainTravel() == 2).findAny().orElse(new Travel());
            map.put("mainTravel",mainTravel);
            map.put("threeTravel",threeTravel);
            return map;
        },asyncExecutor).whenCompleteAsync((map, throwable) -> {
            if (map.isEmpty()){
                result.setResult(ResultRtn.of(StatusCode.NULL_RESULT));
            }else {
                log.info("-==查看详情成功{}==-",map);
                result.setResult(ResultRtn.of(StatusCode.SUCCESS,map));
            }
        },asyncExecutor).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==查看详情委托单超时或发生异常==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return null;
                });
        return result;
    }

}
