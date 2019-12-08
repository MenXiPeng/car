package com.mxp.car.service;


import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 21:05
 */
public interface BaseService<T,ID extends Serializable> {
    /* Save */
    int save(T t);

    /* Insert by list */
    int saveByList(List<T> param);

    /* Insert by map */
    int saveByMap(Map<String, Object> param);

    /* Remove by the obj */
    int remove(T t);

    /* Remove by id */
    int removeById(ID id);

    /* Remove by list */
    int removeByList(List<T> param);

    /* Remove by map */
    int removeByMap(Map<String, Object> param);

    /* Modify by obj */
    int modify(T t);

    /* Modify by list */
    int modifyByList(List<T> param);

    /* Modify by map */
    int modifyByMap(Map<String, Object> param);

    /*
     * Find all the infos
     */
    List<T> findAll();

    /**
     * @param t obj you need to query
     * @return Details result
     */
    Optional<T> find(T t);

    /**
     * @param id ID you need to query
     * @return Details result
     */
    Optional<T> findById(ID id);

    /**
     * @param t A object you need to query
     * @return The collection associated with the object
     */
    List<T> findList(T t);

    /**
     * @param param The parameter map you need to query
     * @return Pagination list information for related records
     */
    Page<T> findListByPage(int pageNum, int pageSize, Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return Pagination map information for related records
     */
    Page<Map<String, Object>> findMapByPage(int pageNum, int pageSize, Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return The collection for related records
     */
    List<T> findListByMap(Map<String, Object> param);

    /**
     * @param param The parameter map you need to query
     * @return The map for related records
     */
    Optional<Map<String, Object>> findMapByMap(Map<String, Object> param);
}
