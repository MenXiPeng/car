package com.mxp.car.service;

import com.mxp.car.model.Photo;

import java.util.List;
import java.util.Map;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/7
 * TIME: 14:28
 */
public interface PhotoService extends BaseService<Photo,Long> {
    int saveList(List<Photo> list);
    int photoList(Map<String,String> photoMap, Long carId);
    List<Photo> findListByCarId(Long carId);
    int remove(Photo photo);
    boolean modifyOrder(Photo photo);
    int modifyInsertOrder(Photo photo);
}
