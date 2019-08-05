package com.ming.service;

import com.ming.bean.Role;

import java.io.Serializable;
import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    void updateRole(Role role);

    void deleteRoleById(Serializable roleId);

    Serializable saveRole(Role role);

    Role getRoleById(Serializable roleId);

    void saveOrUpdateRole(Role role);
}
