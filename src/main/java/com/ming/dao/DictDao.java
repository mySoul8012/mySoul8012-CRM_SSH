package com.ming.dao;

import com.ming.bean.Dict;

import java.io.Serializable;
import java.util.List;

public interface DictDao extends BaseDao<Dict> {
    List<Dict> getFirstCategories();

    void deleteFirstCategoryByTypeCode(Serializable typeCode);
}
