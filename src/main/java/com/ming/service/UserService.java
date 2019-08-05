package com.ming.service;

import com.ming.bean.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    //根据登录名查询用户信息
    User getUserByUserCode(User user);

    List<User> getAllUsers();

    User getUsersByUserId(Serializable userId);

    void updateUser(User user);

    void deleteUserById(Serializable userId);

    Serializable saveUser(User user);

    void saveOrUpdateUser(User user);

    List<String> queryAllUserNames();
}
