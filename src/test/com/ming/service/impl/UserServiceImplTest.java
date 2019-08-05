package com.ming.service.impl;

import com.ming.bean.Role;
import com.ming.bean.User;
import com.ming.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class UserServiceImplTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void ming(){
        List<User> users = userDao.getTotalItems();
        User user = users.get(0);
        Set<Role> roles = user.getRoles();
        System.out.println(roles.size());
        System.out.println(23333333);
    }
}