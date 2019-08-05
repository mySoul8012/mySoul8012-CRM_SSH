package com.ming.dao.impl;

import com.ming.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    //如何去获取泛型的类型
    public Class clazz;


    public BaseDaoImpl(){
        //获取当前类型的带有泛型类型的父类
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取运行期的泛型类型
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    public Serializable save(T t) {
        return this.getHibernateTemplate().save(t);
    }

    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    public void saveOrUpdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    public void deleteById(Serializable id) {
        this.getHibernateTemplate().delete(this.getById(id));
    }

    public T getById(Serializable id) {
        return (T) this.getHibernateTemplate().get(clazz, id);
    }

    public List<T> getTotalItems() {
        return this.getHibernateTemplate().loadAll(clazz);
    }

    public List<T> getByConditions(final Map<String, Object> conditions) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);

                Root<T> root = criteriaQuery.from(clazz);

                Set<String> keys = conditions.keySet();
                for(String key:keys){
                    criteriaQuery.where(criteriaBuilder.equal(root.get(key), conditions.get(key)));
                }

                return session.createQuery(criteriaQuery).getResultList();
            }
        });
    }

    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(dc, start, pageSize);
    }

    public Integer getTotalCount(DetachedCriteria dc) {
        // 设置查询聚合函数
        dc.setProjection(Projections.rowCount());
        // 查询总记录数
        List<Long> rows = (List<Long>)this.getHibernateTemplate().findByCriteria(dc);

        // 清空函数
        dc.setProjection(null);

        // 返回结果
        if(rows != null && rows.size() > 0){
            return rows.get(0).intValue();
        }

        return 0;
    }
}
