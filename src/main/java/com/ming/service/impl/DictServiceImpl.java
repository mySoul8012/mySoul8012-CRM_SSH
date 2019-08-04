package com.ming.service.impl;

import com.ming.bean.Dict;
import com.ming.dao.DictDao;
import com.ming.service.DictService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictServiceImpl implements DictService {
    private DictDao dictDao;

    public DictDao getDictDao() {
        return dictDao;
    }

    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    @Override
    public List<Dict> getFirstCategories() {
        return dictDao.getFirstCategories();
    }

    @Override
    public List<Dict> getSecondCategoriesByTypeCode(Serializable typeCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("dictTypeCode", typeCode);
        return dictDao.getByConditions(params);
    }

    @Override
    public void saveOrUpdate(Dict dict) {
        dictDao.saveOrUpdate(dict);
    }

    @Override
    public void saveFirstCategory(Dict dict) {
        dictDao.save(dict);
    }

    @Override
    public void deleteFirstCategoryByTypeCode(Serializable typeCode) {
        dictDao.deleteFirstCategoryByTypeCode(typeCode);
    }

    @Override
    public void deleteDictById(Serializable dictId) {
        dictDao.deleteById(dictId);
    }

    @Override
    public Serializable saveDict(Dict dict) {
        return dictDao.save(dict);
    }


    @Override
    public List<Dict> getSelectAll() {
        return this.dictDao.getTotalItems();
    }
}
