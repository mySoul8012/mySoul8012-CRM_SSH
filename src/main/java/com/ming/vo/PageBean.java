package com.ming.vo;

import java.util.List;

public class PageBean<T> implements java.io.Serializable {
    //对象集合：每嶚的记录数
    private List<T> datas;
    //当前页
    private Integer currentPage;
    //总页数
    private Integer totalPages;
    //每页显示的记录数
    private Integer showItems;
    //总记录数
    private Integer totalItems;

    //在创建对象的时候，在pageBean内部进行相关的逻辑判断
    public PageBean(Integer currentPage, Integer showItems, Integer totalItems){
        this.currentPage = currentPage;
        this.showItems = showItems;
        this.totalItems = totalItems;

        //相关的逻辑判断
        //判断每嶚显示的记录数
        if(showItems == null){
            this.showItems = 12;
        }

        //计算总页数
        this.totalPages = (this.totalItems + this.showItems - 1) / this.showItems;

        //判断当前页
        if(currentPage == null || currentPage < 1){
            this.currentPage = 1;
        }
        if(this.currentPage > this.totalPages){
            this.currentPage = this.totalPages;
        }
    }

    //mysql 查询分页使用的是limit xxx,yyy,需要传入起始索引
    public Integer getStartIndex(){
        return (this.currentPage - 1) * this.showItems;
    }


    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getShowItems() {
        return showItems;
    }

    public void setShowItems(Integer showItems) {
        this.showItems = showItems;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
