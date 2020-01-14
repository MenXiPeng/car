package com.mxp.car.controller;

import com.mxp.car.config.Config;
import com.mxp.car.model.Photo;
import com.mxp.car.service.PhotoService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/31
 * TIME: 10:58
 */
@Log4j2
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/{pageNum}/{pageSize}")
    public DeferredResult<ResultRtn> demo(@PathVariable int pageNum,
                                          @PathVariable int pageSize,
                                          @RequestBody Map<String, Object> param){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var listByPage = photoService.findListByPage(pageNum,pageSize,param);
            if(listByPage.isEmpty()) {
                log.info("----=== 获取图片列表失败 ===----");
                result.setResult(ResultRtn.of(StatusCode.NULL_RESULT));
            } else {
                var pageInfo = ResultRtn.PageInfo.of(listByPage.getPageNum(), listByPage.getPageSize(),listByPage.getTotal());
                result.setResult(ResultRtn.of(StatusCode.SUCCESS, pageInfo, listByPage));
            }
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==获取图片列表异常或超时==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return "Error";
                });
        return result;
    }

    @PostMapping("/remove")
    public DeferredResult<ResultRtn> remove(@RequestBody Photo photo){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            int status = this.photoService.remove(photo);
            if (status > 0){
                log.info("-==删除成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==删除失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("删除图片超时或发生异常",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return "ERROR";
                });
        return result;
    }

    @PostMapping("/modify")
    public DeferredResult<ResultRtn> modifyPhoto(@RequestBody List<Photo> photos){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var status = this.photoService.modifyByList(photos);
            if (status > 0){
                log.info("-==修改图片成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==修改图片失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==修改图片超时或异常==-",e);
                    result.setResult(ResultRtn.of(StatusCode.TIMEOUT_OR_ERROR));
                    return "Error";
                });
        return result;
    }

    @PostMapping("/order")
    public DeferredResult<ResultRtn> order(@RequestBody Photo photo){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var status = this.photoService.modifyOrder(photo);
            if (status){
                log.info("-==修改排序成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==修改排序失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==图片排序出现异常==-",e);
                    return "Error";
                });
        return result;
    }

    @PostMapping("/insertOrder")
    public DeferredResult<ResultRtn> insertOrder(@RequestBody Photo photo){
        var result = new DeferredResult<ResultRtn>();
        CompletableFuture.supplyAsync(() -> {
            var status = this.photoService.modifyInsertOrder(photo);
            if (status > 0){
                log.info("-==插入修改排序成功==-");
                result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            }else {
                log.warn("-==插入修改排序失败==-");
                result.setResult(ResultRtn.of(StatusCode.ERROR));
            }
            result.setResult(ResultRtn.of(StatusCode.SUCCESS));
            return "Success";
        }).orTimeout(Config.TIMEOUT_TIME,Config.TIME_UNIT).
                exceptionally(e -> {
                    log.error("-==插入图片排序出现异常==-",e);
                    return "Error";
                });
        return result;
    }
}
