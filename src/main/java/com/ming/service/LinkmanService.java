package com.ming.service;

import com.ming.bean.Linkman;
import com.ming.vo.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;

public interface LinkmanService {
    Serializable saveLinkman(Linkman linkman);

    PageBean<Linkman> getLinkmanByPage(DetachedCriteria dc, Integer currentPage, Integer showItems);

    Linkman getLinkmanById(Serializable lkmId);

    void deleteLinkmanById(Serializable lkmId);

    void saveOrUpdateLinkman(Linkman linkman);
}
