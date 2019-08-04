package com.ming.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    //保存
    Serializable save(T t);

    //修改
    void update(T t);

    //保存或者修改
    void saveOrUpdate(T t);

    //删除对象
    void delete(T t);

    //根据id进行删除
    void deleteById(Serializable id);

    //根据id查询
    T getById(Serializable id);

    //查询所有对象
    List<T> getTotalItems();

    //按条件查询
    List<T> getByConditions(Map<String,Object> conditions);

    //分页查询
    List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize);

    //查询总的记录数
    Integer getTotalCount(DetachedCriteria dc);
}
