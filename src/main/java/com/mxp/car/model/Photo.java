package com.mxp.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/25
 * TIME: 13:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    private Integer id;
    private Long photoId;
    private Long carId;
    private String name;
    private String url;
    private Integer orderId;
}
