package com.mxp.car.mapper;

import com.mxp.car.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/1
 * TIME: 05:06
 */
@Mapper
public interface UserMapper extends BaseMapper<User,Long> {
    User selectUserByUsername(String username);
}
