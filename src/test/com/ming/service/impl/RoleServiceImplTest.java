package com.ming.service.impl;

import com.ming.bean.Dict;
import com.ming.bean.Role;
import com.ming.dao.RoleDao;
import com.ming.dao.impl.RoleDaoImpl;
import com.ming.service.DictService;
import com.ming.service.RoleService;
import com.ming.service.UserService;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RoleServiceImplTest {
    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private DictService dictService;
    @Test
    public void ming(){
        List<Role> list = roleDao.getTotalItems();
                int i = 0;
    }
}