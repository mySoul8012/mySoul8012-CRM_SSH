package com.ming.dao.impl;

import com.ming.bean.Customer;
import com.ming.dao.CustomerDao;

import java.util.List;

public class CustomerImpl  extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    public List<Object[]> getCustomerNames() {
        return this.getHibernateTemplate().execute(session -> {
            String sql = "select cust_id,cust_name from customer";
            return session.createNativeQuery(sql).getResultList();
        });
    }
}
