package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.PhotoMapper;
import com.mxp.car.model.DriverInfo;
import com.mxp.car.model.Photo;
import com.mxp.car.service.PhotoService;
import com.mxp.car.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/27
 * TIME: 17:57
 */
@Log4j2
@Service
public class PhotoServiceImpl extends BaseServiceImpl<Photo,Long> implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public BaseMapper<Photo, Long> getMapper() {
        return this.photoMapper;
    }

    @Override
    public int saveList(List<Photo> list) {
        return this.photoMapper.insertList(list);
    }

    @Override
    public int photoList(Map<String,String> photoMap, Long carId){
        var photoList = new ArrayList<Photo>();
        photoMap.forEach((key,value) -> {
            Photo photo = new Photo();
            photo.setPhotoId(Utils.CarUtil.getId());
            photo.setOrderId(Integer.valueOf(key));
            photo.setUrl(value);
            photo.setCarId(carId);
            photo.setName("");
            photoList.add(photo);
        });
        return this.saveList(photoList);
    }
}
