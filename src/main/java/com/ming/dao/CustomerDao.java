package com.ming.dao;

import com.ming.bean.Customer;

import java.util.List;

public interface CustomerDao  extends BaseDao<Customer>{
    List<Object[]> getCustomerNames();
}
