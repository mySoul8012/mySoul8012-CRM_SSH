package com.ming.dao.impl;

import com.ming.bean.Role;
import com.ming.dao.RoleDao;
import org.hibernate.SQLQuery;

import java.util.List;

public class RoleDaoImpl extends  BaseDaoImpl<Role> implements RoleDao {


    public List<Role> getTotalItems() {
        String sql = "select * from sys_role";
        return this.getHibernateTemplate().execute(session -> {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Role.class);
            List<Role> res = query.list();
            return res;
        });
    }
}
