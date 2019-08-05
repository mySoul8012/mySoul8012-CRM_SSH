package com.ming.service;

import com.ming.bean.Customer;
import com.ming.vo.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface CustomerService {

    Serializable saveCustomer(Customer customer);

    PageBean<Customer> getCustomerByPage(DetachedCriteria dc, Integer currentPage, Integer showItems);

    Customer getCustomerById(Serializable custId);

    void deleteCustomerById(Serializable custId);

    void updateCusomter(Customer customer);

    List<Customer> getCustomerAll();

    List<Object[]> getCustomerNames();
}
