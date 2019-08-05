package com.ming.service.impl;

import com.ming.bean.Customer;
import com.ming.dao.CustomerDao;
import com.ming.service.CustomerService;
import com.ming.vo.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    @Override
    public Serializable saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public PageBean<Customer> getCustomerByPage(DetachedCriteria dc, Integer currentPage, Integer showItems) {
        //查询总记录数
        Integer totalItems = customerDao.getTotalCount(dc);
        //创建pageBean
        PageBean<Customer> customerPageBean = new PageBean<>(currentPage, showItems, totalItems);
        //查询分页数据
        List<Customer> customers = customerDao.getPageList(dc, customerPageBean.getStartIndex(), customerPageBean.getShowItems());
        //将结果设置到pageBean当中
        customerPageBean.setDatas(customers);
        return customerPageBean;    }

    @Override
    public Customer getCustomerById(Serializable custId) {
        return customerDao.getById(custId);
    }

    @Override
    public void deleteCustomerById(Serializable custId) {
        customerDao.deleteById(custId);
    }

    @Override
    public void updateCusomter(Customer customer) {
        customerDao.update(customer);
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getCustomerAll() {
        return this.customerDao.getTotalItems();
    }

    @Override
    public List<Object[]> getCustomerNames() {
        return customerDao.getCustomerNames();
    }
}
