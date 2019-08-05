package com.ming.utils;

import com.ming.bean.CustDetail;
import com.ming.bean.Customer;
import com.ming.service.CustomerService;
import com.ming.vo.CustInfo;

import java.util.UUID;

public class CommonUtils {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }


    /**
     * vo 转 bean
     * @param custInfo
     * @return
     */
    public static Customer convertCustInfoToCustomer(CustInfo custInfo){
        Customer customer = new Customer();
        customer.setCustName(custInfo.getCustName());
        customer.setCustSource(custInfo.getCustSource());
        customer.setCustIndustry(custInfo.getCustIndustry());
        customer.setCustLevel(custInfo.getCustLevel());
        customer.setCustUser(custInfo.getCustUser());


        CustDetail custDetail = new CustDetail();
        custDetail.setCustRegion(custInfo.getCustRegion());
        custDetail.setCustZip(custInfo.getCustZip());
        custDetail.setCustAddress(custInfo.getCustAddress());
        custDetail.setCustFax(custInfo.getCustFax());
        custDetail.setCustWebsite(custInfo.getCustWebsite());
        custDetail.setCustlicense(custInfo.getCustlicense());
        custDetail.setCustCorporation(custInfo.getCustCorporation());
        custDetail.setCustCapital(custInfo.getCustCapital());
        custDetail.setCustBank(custInfo.getCustBank() + "#" + custInfo.getCustAccountNumber());
        custDetail.setCustPic(custInfo.getCustPic().replace("\\","\\\\"));
        custDetail.setCustComment(custInfo.getCustComment());
        custDetail.setCustomer(customer);

        customer.setCustDetail(custDetail);

        return customer;
    }


    /**
     * vo 转 bean
     * @param custInfo
     * @param customerService
     * @return
     */
    public static Customer convertCustInfoToCustomer(CustInfo custInfo, CustomerService customerService){
        Customer customer = customerService.getCustomerById(custInfo.getCustId());
        customer.setCustName(custInfo.getCustName());
        customer.setCustSource(custInfo.getCustSource());
        customer.setCustIndustry(custInfo.getCustIndustry());
        customer.setCustLevel(custInfo.getCustLevel());
        customer.setCustUser(custInfo.getCustUser());


        CustDetail custDetail = customer.getCustDetail();
        custDetail.setCustRegion(custInfo.getCustRegion());
        custDetail.setCustZip(custInfo.getCustZip());
        custDetail.setCustAddress(custInfo.getCustAddress());
        custDetail.setCustFax(custInfo.getCustFax());
        custDetail.setCustWebsite(custInfo.getCustWebsite());
        custDetail.setCustlicense(custInfo.getCustlicense());
        custDetail.setCustCorporation(custInfo.getCustCorporation());
        custDetail.setCustCapital(custInfo.getCustCapital());
        custDetail.setCustBank(custInfo.getCustBank() + "#" + custInfo.getCustAccountNumber());
        custDetail.setCustPic(custInfo.getCustPic().replace("\\","\\\\"));
        custDetail.setCustComment(custInfo.getCustComment());
        custDetail.setCustomer(customer);

        customer.setCustDetail(custDetail);

        return customer;
    }
}
