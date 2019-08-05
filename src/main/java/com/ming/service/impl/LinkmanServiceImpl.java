package com.ming.service.impl;

import com.ming.bean.Linkman;
import com.ming.dao.LinkmanDao;
import com.ming.service.LinkmanService;
import com.ming.vo.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class LinkmanServiceImpl implements LinkmanService {
    private LinkmanDao linkmanDao;


    @Override
    public Serializable saveLinkman(Linkman linkman) {
        return linkmanDao.save(linkman);
    }

    @Override
    public PageBean<Linkman> getLinkmanByPage(DetachedCriteria dc, Integer currentPage, Integer showItems) {
        Integer totalItems = linkmanDao.getTotalCount(dc);
        PageBean<Linkman> linkmanPageBean = new PageBean<>(currentPage, showItems, totalItems);
        List<Linkman> linkmen = linkmanDao.getPageList(dc, linkmanPageBean.getStartIndex(), linkmanPageBean.getShowItems() );
        linkmanPageBean.setDatas(linkmen);
        return linkmanPageBean;
    }

    @Override
    public Linkman getLinkmanById(Serializable lkmId) {
        return linkmanDao.getById(lkmId);
    }

    @Override
    public void deleteLinkmanById(Serializable lkmId) {
        linkmanDao.deleteById(lkmId);
    }

    @Override
    public void saveOrUpdateLinkman(Linkman linkman) {
        linkmanDao.saveOrUpdate(linkman);
    }

    public LinkmanDao getLinkmanDao() {
        return linkmanDao;
    }

    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }
}
