package com.ming.web;

import com.ming.bean.Customer;
import com.ming.bean.Linkman;
import com.ming.service.CustomerService;
import com.ming.service.LinkmanService;
import com.ming.vo.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
    private LinkmanService linkmanService;

    private CustomerService customerService;

    private Linkman linkman;

    private Integer currentPage;
    private Integer showItems;

    private String searchLkmName;
    private String searchCustId;

    private String operate;

    @Override
    public Linkman getModel() {
        if(linkman == null){
            linkman = new Linkman();
        }
        return linkman;
    }


    public String addLinkman(){
        Serializable lkmid = linkmanService.saveLinkman(linkman);

        ServletActionContext.getRequest().setAttribute("status", lkmid);

        return SUCCESS;
    }


    /**
     * 获取添加的用户
     * @return
     */
    public String get_linkman_add_show(){
        List<Customer> customerList = customerService.getCustomerAll();
        if(customerList.size() != 0){
            ServletActionContext.getRequest().setAttribute("customerList", customerList);
        }
        return SUCCESS;
    }


    public String getLinkmen(){
        DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);

        if(linkman != null){
            if(StringUtils.isNotBlank(linkman.getLkmName())){
                dc.add(Restrictions.like("likmName", "%" + linkman.getLkmName() + "%"));
            }

            if(linkman.getCustomer() != null && linkman.getCustomer().getCustId() != null){
                dc.add(Restrictions.eq("customer.custId", linkman.getCustomer().getCustId()));
            }

            ServletActionContext.getRequest().setAttribute("linkman", linkman);
        }

        PageBean<Linkman> linkmanPageBean = linkmanService.getLinkmanByPage(dc, currentPage, showItems);
        ServletActionContext.getRequest().setAttribute("linkmanPageBean", linkmanPageBean);

        return SUCCESS;

    }


    public String getLinkmanById(){

        if(linkman != null && linkman.getLkmId() != null){
            linkman = linkmanService.getLinkmanById(linkman.getLkmId());

            ServletActionContext.getRequest().setAttribute("linkman", linkman);

            if("edit".equals(operate)){
                if(org.apache.commons.lang3.StringUtils.isNoneBlank(searchLkmName)){
                    ServletActionContext.getRequest().setAttribute("searchLkmName", searchLkmName);
                }

                if(org.apache.commons.lang3.StringUtils.isNoneBlank(searchCustId)){
                    ServletActionContext.getRequest().setAttribute("searchCustId", searchCustId);
                }

                if(currentPage != null){
                    ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
                }

                return "edit";
            }
        }

        return "show";
    }

    public String updateLinkmanById(){

        if(linkman != null){
            linkmanService.saveOrUpdateLinkman(linkman);

            if(org.apache.commons.lang3.StringUtils.isNoneBlank(searchLkmName)){
                ServletActionContext.getRequest().setAttribute("searchLkmName", searchLkmName);
            }

            if(org.apache.commons.lang3.StringUtils.isNoneBlank(searchCustId)){
                ServletActionContext.getRequest().setAttribute("searchCustId", searchCustId);
            }

            if(currentPage != null){
                ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
            }
        }


        return SUCCESS;
    }


    public String deleteLinkmanById(){

        if(linkman != null && linkman.getLkmId() != null){
            linkmanService.deleteLinkmanById(linkman.getLkmId());

            if(org.apache.commons.lang3.StringUtils.isNoneBlank(linkman.getLkmName())){
                ServletActionContext.getRequest().setAttribute("lkmName", linkman.getLkmName());
            }

            if(linkman.getCustomer() != null && linkman.getCustomer().getCustId() != null){
                ServletActionContext.getRequest().setAttribute("custId", linkman.getCustomer().getCustId());
            }

            if(currentPage != null){
                ServletActionContext.getRequest().setAttribute("currentPage", currentPage);
            }
        }

        return SUCCESS;
    }

    public LinkmanService getLinkmanService() {
        return linkmanService;
    }

    public void setLinkmanService(LinkmanService linkmanService) {
        this.linkmanService = linkmanService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Linkman getLinkman() {
        return linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
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

    public String getSearchLkmName() {
        return searchLkmName;
    }

    public void setSearchLkmName(String searchLkmName) {
        this.searchLkmName = searchLkmName;
    }

    public String getSearchCustId() {
        return searchCustId;
    }

    public void setSearchCustId(String searchCustId) {
        this.searchCustId = searchCustId;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
