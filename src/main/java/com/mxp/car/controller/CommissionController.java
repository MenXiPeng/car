package com.mxp.car.controller;

import com.mxp.car.config.Config;
import com.mxp.car.service.CommissionService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
    private CommissionService commissionService;

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
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==插入委托单超时或发生异常==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return Config.ERROR;
                });
        return result;
    }

}
