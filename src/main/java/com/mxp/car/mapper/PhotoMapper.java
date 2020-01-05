package com.mxp.car.mapper;

import com.mxp.car.model.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/25
 * TIME: 13:19
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo, Long> {
    int insertList(List<Photo> list);
    List<Photo> selectListByCarId(Long carId);
}
