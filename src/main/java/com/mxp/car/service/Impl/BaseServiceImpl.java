package com.mxp.car.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 21:06
 */
@Service
public abstract class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID> {

    // Sub class of this need to implements this method
    public abstract BaseMapper<T, ID> getMapper();

    @Override
    public int save(T t) {
        return this.getMapper().insert(t);
    }

    @Override
    public int saveByList(List<T> param) {
        return this.getMapper().insertByList(param);
    }

    @Override
    public int saveByMap(Map<String, Object> param) {
        return this.getMapper().insertByMap(param);
    }

    @Override
    public int remove(T t) {
        return this.getMapper().delete(t);
    }

    @Override
    public int removeById(ID id) {
        return this.getMapper().deleteById(id);
    }

    @Override
    public int removeByList(List<T> param) {
        return this.getMapper().deleteByList(param);
    }

    @Override
    public int removeByMap(Map<String, Object> param) {
        return this.getMapper().deleteByMap(param);
    }

    @Override
    public int modify(T t) {
        return this.getMapper().update(t);
    }

    @Override
    public int modifyByList(List<T> param) {
        return this.getMapper().updateByList(param);
    }

    @Override
    public int modifyByMap(Map<String, Object> param) {
        return this.getMapper().updateByMap(param);
    }

    @Override
    public Optional<T> find(T t) {
        return Optional.ofNullable(this.getMapper().select(t));
    }

    @Override
    public List<T> findAll() {
        return this.getMapper().selectAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(this.getMapper().selectById(id));
    }

    @Override
    public List<T> findList(T t) {
        return this.getMapper().selectList(t);
    }

    @Override
    public Page<T> findListByPage(int pageNum, int pageSize, Map<String, Object> param) {
        PageHelper.startPage(pageNum, pageSize);
        return this.getMapper().selectListByPage(param);
    }

    @Override
    public Page<Map<String, Object>> findMapByPage(int pageNum, int pageSize, Map<String, Object> param) {
        PageHelper.startPage(pageNum, pageSize);
        return this.getMapper().selectMapByPage(param);
    }

    @Override
    public List<T> findListByMap(Map<String, Object> param) {
        return this.getMapper().selectListByMap(param);
    }

    @Override
    public Optional<Map<String, Object>> findMapByMap(Map<String, Object> param) {
        return Optional.ofNullable(this.getMapper().selectMapByMap(param));
    }
}
