package com.ming.dao.impl;

import com.ming.bean.Dict;
import com.ming.dao.DictDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDao {
    public List<Dict> getFirstCategories() {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Dict>>() {
            public List<Dict> doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT DISTINCT dict_type_code,dict_type_name FROM data_dict";

                //NativeQuery
                List<Object[]> objects = session.createNativeQuery(sql).getResultList();
                //List<Object[]> ==> List<Dict>
                List<Dict> dicts = new ArrayList<>();
                Iterator iterator = objects.iterator();
                while(iterator.hasNext()){
                    Object[] objects1 = (Object[]) iterator.next();
                    Dict dict = new Dict();
                    if(objects1[0] != null){
                        dict.setDictTypeCode((String)objects1[0]);
                    }
                    if(objects1[1] != null){
                        dict.setDictTypeName((String)objects1[1]);
                    }
                    dicts.add(dict);
                }






                return dicts;
            }
        });
    }

    @Override
    public void deleteFirstCategoryByTypeCode(Serializable typeCode) {
        this.getHibernateTemplate().execute(session -> {
            String sql = "delete from Dict where dictTypeCode=?2";
            Query query = session.createQuery(sql);
            query.setParameter(2, typeCode);
            query.executeUpdate();
            return null;
        });
    }

}
