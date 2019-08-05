package com.ming.service;

import com.ming.bean.Dict;

import java.io.Serializable;
import java.util.List;

public interface DictService {

    List<Dict> getFirstCategories();

    List<Dict> getSelectAll();

    List<Dict> getSecondCategoriesByTypeCode(Serializable typeCode);

    void saveOrUpdate(Dict dict);

    void saveFirstCategory(Dict dict);

    void deleteFirstCategoryByTypeCode(Serializable typeCode);

    void deleteDictById(Serializable dictId);

    Serializable saveDict(Dict dict);



}
