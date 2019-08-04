package com.ming.web;

import com.ming.bean.Customer;
import com.ming.bean.Dict;
import com.ming.service.CustomerService;
import com.ming.service.DictService;
import com.ming.utils.CommonUtils;
import com.ming.vo.CustInfo;
import com.ming.vo.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<CustInfo> {
    private CustomerService customerService;
    private List<Object[]> customerNames;
    private DictService dictService;

    public List<Object[]> getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(List<Object[]> customerNames) {
        this.customerNames = customerNames;
    }

    public DictService getDictService() {
        return dictService;
    }

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    public String getAllCustomerNames(){
        customerNames = customerService.getCustomerNames();

        return SUCCESS;
    }

    private CustInfo custInfo;

    private Integer currentPage;
    private Integer showItems;
    private String searchKey;

    private String operate;

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfo custInfo) {
        this.custInfo = custInfo;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getShowItems() {
        return showItems;
    }

    public void setShowItems(Integer showItems) {
        this.showItems = showItems;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Override
    public CustInfo getModel() {
        if(custInfo == null){
            custInfo = new CustInfo();
        }
        return custInfo;
    }


    /**
     * 展示客户添加客户界面
     * @return
     */
    public String addCustomerShow(){
        List<Dict> dictList = dictService.getSelectAll();
        ServletActionContext.getRequest().setAttribute("dicts", dictList);
        return SUCCESS;
    }


    /**
     * 保存当前对象
     * @return
     */
    public String saveCustomer(){
        Customer customer = CommonUtils.convertCustInfoToCustomer(custInfo);

        Serializable custId = customerService.saveCustomer(customer);

        ServletActionContext.getRequest().setAttribute("status", custId);

        return SUCCESS;
    }


    /**
     * 客户分页
     * @return
     */
    public String getCustomersByPage(){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);

        // 拼接条件
        if(StringUtils.isNotBlank(searchKey)){
            dc.add(Restrictions.like("custName", "%" + searchKey + "%"));
        }

        PageBean<Customer> customerPageBean = customerService.getCustomerByPage(dc, currentPage, showItems);

        ServletActionContext.getRequest().setAttribute("customers", customerPageBean);
        ServletActionContext.getRequest().setAttribute("searchKey", searchKey);


        return SUCCESS;


    }

    /**
     * 获取客户id
     * @return
     */
    public String getCustomerById(){






        if(custInfo != null && custInfo.getCustId() != null){
            Customer customer = customerService.getCustomerById(custInfo.getCustId());
            ServletActionContext.getRequest().setAttribute("customer", customer);
        }


        // 判断当前操作是edit还是show
        if("edit".equals(operate)){
            ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
            ServletActionContext.getRequest().setAttribute("searchkey", searchKey);

            List<Dict> dictList = dictService.getSelectAll();
            ServletActionContext.getRequest().setAttribute("dicts", dictList);



            return "edit";
        }

        return "show";

    }

    /**
     * 删除客户
     * @return
     */
    public String deleteCustomerById(){

        if(custInfo != null && custInfo.getCustId() != null){
            customerService.deleteCustomerById(custInfo.getCustId());
        }

        ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
        ServletActionContext.getRequest().setAttribute("searchKey", searchKey);
        return SUCCESS;
    }


    /**
     *
     * 更新客户
     * @return
     */
    public String updateCustomer(){

        Customer customer = CommonUtils.convertCustInfoToCustomer(custInfo, customerService);

        customerService.updateCusomter(customer);

        ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
        ServletActionContext.getRequest().setAttribute("searchKey", searchKey);

        return SUCCESS;
    }


}
