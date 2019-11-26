package com.mxp.car.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 21:12
 */
public interface BaseMapper<T,ID extends Serializable> {
    /* Insert */
    int insert(T t);

    /* Insert by list */
    int insertByList(@Param("param") List<T> param);

    /* Insert by map */
    int insertByMap(@Param("param") Map<String, Object> param);

    /* Delete by the obj */
    int delete(T t);

    /* Delete by id */
    int deleteById(ID id);

    /* Delete by list */
    int deleteByList(@Param("param") List<T> param);

    /* Delete by map */
    int deleteByMap(@Param("param") Map<String, Object> param);

    /* Update by obj */
    int update(T t);

    /* Update by list */
    int updateByList(@Param("param") List<T> param);

    /* Update by map */
    int updateByMap(@Param("param") Map<String, Object> param);

    /*
     * Select all the infos
     */
    List<T> selectAll();
    /**
     * @param t obj you need to query
     * @return Details result
     */
    T select(T t);

    /**
     * @param id ID you need to query
     * @return Details result
     */
    T selectById(ID id);

    /**
     * @param t A object you need to query
     * @return The collection associated with the object
     */
    List<T> selectList(T t);

    /**
     * @param param The parameter map you need to query
     * @return Pagination list information for related records
     */
    Page<T> selectListByPage(@Param("param") Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return Pagination map information for related records
     */
    Page<Map<String, Object>> selectMapByPage(@Param("param") Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return The collection for related records
     */
    List<T> selectListByMap(@Param("param") Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return The map for related records
     */
    Map<String, Object> selectMapByMap(@Param("param") Map<String, Object> param);
}
