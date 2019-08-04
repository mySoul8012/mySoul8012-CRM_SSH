package com.ming.service.impl;

import com.ming.bean.Role;
import com.ming.dao.RoleDao;
import com.ming.service.RoleService;

import java.io.Serializable;
import java.util.List;

public class RoleServiceImpl implements RoleService  {
    private RoleDao roleDao;

    public List<Role> getAllRoles() {
        return roleDao.getTotalItems();
    }

    public void updateRole(Role role) {
        roleDao.update(role);
    }

    public void deleteRoleById(Serializable roleId) {
        roleDao.deleteById(roleId);
    }

    public Serializable saveRole(Role role) {
        return roleDao.save(role);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleById(Serializable roleId) {
        return roleDao.getById(roleId);
    }

    @Override
    public void saveOrUpdateRole(Role role) {
        roleDao.saveOrUpdate(role);
    }
}
