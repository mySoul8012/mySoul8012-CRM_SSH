package com.ming.dao.impl;

import com.ming.bean.User;
import com.ming.dao.UserDao;
import org.hibernate.query.Query;


import java.io.Serializable;
import java.util.List;


public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public Serializable save(User user) {
        return this.getHibernateTemplate().save(user);
    }

    @Override
    public List<String> queryAllUserNames() {
        return this.getHibernateTemplate().execute(session -> {
            String sql = "select user_code from sys_user";

            Query<String> query = session.createQuery(sql, String.class);

            return query.getResultList();
        });
    }
}
