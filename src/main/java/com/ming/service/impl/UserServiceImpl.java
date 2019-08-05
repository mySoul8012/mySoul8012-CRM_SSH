package com.ming.service.impl;

import com.ming.bean.User;
import com.ming.dao.UserDao;
import com.ming.exception.LoginException;
import com.ming.service.UserService;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    public UserDao userDao;


    public User getUserByUserCode(User sysUserEntity) {
        // 条件
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("userCode", sysUserEntity.getUserCode());


        // 执行查询
        List<User> res = userDao.getByConditions(conditions);

        // 进行结果判断
        if(res == null || res.size() == 0){
            throw new LoginException("没有找到此用户");
        }

        // 密码判断
        User sysUserEntity1 = res.get(0);
        if(StringUtils.isEmpty(sysUserEntity.getUserPassword())){
            throw new LoginException("密码不正确");
        }

        if(!sysUserEntity1.getUserPassword().equals(sysUserEntity.getUserPassword())){
            throw new LoginException("密码不正确");
        }

        // 判断用户状态
        if(sysUserEntity1.getUserStatus() == Character.DIRECTIONALITY_LEFT_TO_RIGHT){
            throw new LoginException("用户已经锁定");
        }

        // 返回用户
        return sysUserEntity1;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getTotalItems();
    }

    @Override
    public User getUsersByUserId(Serializable userId) {
        return userDao.getById(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserById(Serializable userId) {
        userDao.deleteById(userId);
    }

    @Override
    public Serializable saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public List<String> queryAllUserNames() {
        return userDao.queryAllUserNames();
    }
}


