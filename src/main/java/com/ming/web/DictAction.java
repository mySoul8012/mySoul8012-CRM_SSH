package com.ming.web;

import com.google.gson.Gson;
import com.ming.bean.Dict;
import com.ming.service.DictService;
import com.ming.utils.CommonUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DictAction extends ActionSupport {
    private DictService dictService;

    private List<Dict> dicts;

    private String receiveData;

    private Dict dict;

    public String getFirstCategories(){
        dicts = dictService.getFirstCategories();

        return SUCCESS;
    }

    public String deleteSecondCategory(){
        dict = new Gson().fromJson(receiveData, Dict.class);

        List<Dict> myDicts = dictService.getSecondCategoriesByTypeCode(dict.getDictTypeCode());

        Dict queryDict = myDicts.get(0);

        if(myDicts.size() == 1 && StringUtils.isNotEmpty(queryDict.getDictItemName())){
            queryDict.setDictItemCode(null);
            queryDict.setDictItemName(null);
            queryDict.setDictSort(null);
            queryDict.setDictComment(null);


            dictService.saveOrUpdate(queryDict);
        }else{
            dictService.deleteDictById(dict.getDictId());
        }

        return NONE;


    }


    public String updateSecondCategory(){

        dict = new Gson().fromJson(receiveData, Dict.class);

        dictService.saveOrUpdate(dict);

        return NONE;
    }

    // 根据字典内心查询二级分类
    public String getSecondCategoriesByTypeCode(){

        dicts = dictService.getSecondCategoriesByTypeCode(receiveData)
                .stream()
                .filter(dict1 ->
                    dict1.getDictItemName() != null
                ).collect(Collectors.toList());

        return SUCCESS;
    }

    //添加一级分类，此时二级分类为空
    public String addFirstCategory(){

        dict = new Gson().fromJson(receiveData, Dict.class);
        dict.setDictId(CommonUtils.generateUUID());

        dictService.saveFirstCategory(dict);

        return NONE;
    }

    //一级分类的删除
    public String deleteFirstCategory(){

        dict = new Gson().fromJson(receiveData, Dict.class);

        dictService.deleteFirstCategoryByTypeCode(dict.getDictTypeCode());

        return NONE;
    }

    public String updateFirstCategory(){

        dict = new Gson().fromJson(receiveData, Dict.class);

        List<Dict> myDicts = dictService.getSecondCategoriesByTypeCode(dict.getDictTypeCode());

        myDicts.forEach(dict1 -> {
            dict1.setDictTypeName(dict.getDictTypeName());

            dictService.saveOrUpdate(dict1);
        });

        return NONE;
    }

    public String addSecondCategory(){

        dict = new Gson().fromJson(receiveData, Dict.class);

        List<Dict> myDicts = dictService.getSecondCategoriesByTypeCode(dict.getDictTypeCode());

        Dict queryDict = myDicts.get(0);

        if(myDicts.size() == 1 && StringUtils.isEmpty(queryDict.getDictItemName())){
            queryDict.setDictItemCode(dict.getDictItemCode());
            queryDict.setDictItemName(dict.getDictItemName());
            queryDict.setDictSort(dict.getDictSort());
            queryDict.setDictComment(dict.getDictComment());

            dict = queryDict;

            System.out.println(dict);

            dictService.saveOrUpdate(queryDict);
        }else{
            Serializable dictId = dictService.saveDict(dict);
            dict.setDictId(dictId.toString());
        }

        return SUCCESS;
    }




    public DictService getDictService() {
        return dictService;
    }

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    public List<Dict> getDicts() {
        return dicts;
    }

    public void setDicts(List<Dict> dicts) {
        this.dicts = dicts;
    }

    public String getReceiveData() {
        return receiveData;
    }

    public void setReceiveData(String receiveData) {
        this.receiveData = receiveData;
    }

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }
}
