package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.PhotoMapper;
import com.mxp.car.model.Photo;
import com.mxp.car.service.PhotoService;
import com.mxp.car.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
            photoList.add(photo);
        });
        return this.saveList(photoList);
    }

    @Override
    public List<Photo> findListByCarId(Long carId) {
        return this.photoMapper.selectListByCarId(carId);
    }

    @Override
    public int remove(Photo photo) {
        int result = this.photoMapper.deleteById(photo.getPhotoId());
        if (result > 0){
            File file = new File(Utils.CarUtil.photoUrl(photo.getUrl()));
            if (file.delete()){
                return 1;
            }
            return result;
        }
        return 0;
    }

    /**
     * 修改排序位置
     */
    @Override
    public boolean modifyOrder(Photo photo) {
        boolean status;
        Photo orderPhoto = this.photoMapper.selectById(photo.getPhotoId());
        List<Photo> photoList = this.photoMapper.selectListByCarId(photo.getCarId());
        // 图片的原始位置
        var initOrder = orderPhoto.getOrderId();
        // 到达位置
        var endOrder = photo.getOrderId();
        if (initOrder < endOrder){
            // 大于原始位置之后都要剪
            status =  photoList.stream().filter(photo1 -> photo1.getOrderId() >= orderPhoto.getOrderId()).anyMatch(photo1 -> {
                 Photo photo2 = new Photo();
                 if (photo1.getOrderId() > endOrder) {
                    photo2.setPhotoId(photo.getPhotoId());
                    photo2.setOrderId(endOrder);
                    this.photoMapper.update(photo2);
                    return true;
                } else {
                     System.out.println(photo1.getPhotoId());
                     photo2.setPhotoId(photo1.getPhotoId());
                     photo2.setOrderId(photo1.getOrderId() - 1);
                    this.photoMapper.update(photo2);
                    return false;
                }
            });
        }else {
            status = photoList.stream().filter(photo1 -> photo1.getOrderId() >= endOrder).anyMatch(photo1 -> {
                Photo photo2 = new Photo();
                if (photo1.getOrderId().equals(initOrder)) {
                    photo2.setPhotoId(photo.getPhotoId());
                    photo2.setOrderId(endOrder);
                    this.photoMapper.update(photo2);
                    return true;
                } else {
                    System.out.println(photo1.getOrderId());
                    System.out.println(photo1.getPhotoId());
                    photo2.setPhotoId(photo1.getPhotoId());
                    photo2.setOrderId(photo1.getOrderId() + 1);
                    this.photoMapper.update(photo2);
                    return false;
                }
            });
        }
        return status;
    }

    /**
     * 插入排序
     */
    @Override
    public int modifyInsertOrder(Photo photo) {
        List<Photo> photoList = this.photoMapper.selectListByCarId(photo.getCarId());
        photoList.stream().filter(photo1 -> photo1.getOrderId() >= photo.getOrderId()).forEach(photo1 -> {
            photo1.setOrderId(photo1.getOrderId()+1);
            this.photoMapper.update(photo1);
        });
        return this.photoMapper.update(photo);
    }

}
