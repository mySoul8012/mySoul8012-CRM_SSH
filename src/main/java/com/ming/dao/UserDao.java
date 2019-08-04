package com.ming.dao;

import com.ming.bean.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    List<String> queryAllUserNames();

}
