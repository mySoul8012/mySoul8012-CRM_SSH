package com.ming.web;

import com.google.gson.Gson;
import com.ming.bean.Role;
import com.ming.bean.User;
import com.ming.service.RoleService;
import com.ming.service.UserService;
import com.ming.vo.RoleItem;
import com.ming.vo.UserItem;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class RoleAction extends ActionSupport {
    private RoleService  roleService;

    private UserService userService;

    private List<Role> roles;

    private String receiveJsonData;

    private Role role;

    private Set<User> users;

    private RoleItem roleItem;

    private List<RoleItem> roleItems = new ArrayList<>();

    private UserItem userItem;

    /**
     * 获取全部
     * @return
     * @throws Exception
     */
    public String getToTalRoles()throws Exception{
        this.roles = roleService.getAllRoles();
        int i = 0;
        return SUCCESS;
    }


    public String updateRole(){
        role = new Gson().fromJson(receiveJsonData, Role.class);
        roleService.updateRole(role);
        return NONE;
    }


    /**
     * 删除
     * @return
     */
    public String deleteRoleById(){
        role = new Gson().fromJson(receiveJsonData, Role.class);
        roleService.deleteRoleById(role.getRoleId());
        return NONE;
    }

    /**
     * 保存
     * @return
     */
    public String saveRole(){
        role = new Gson().fromJson(receiveJsonData, Role.class);
        Integer roleId = (Integer)roleService.saveRole(role);
        role.setRoleId(roleId);
        return SUCCESS;
    }



    public String getUsersByRoleId(){
        Role role = roleService.getRoleById(Integer.parseInt(receiveJsonData));

        users = role.getUsers();

        users.stream().sorted(Comparator.comparing(User::getUserId))
                .forEach(user -> {
                    RoleItem roleItem = new RoleItem();
                    roleItem.setUserId(user.getUserId());
                    roleItem.setUserName(user.getUserName());
                    roleItem.setUserCode(user.getUserId());
                    roleItem.setUserPassword(user.getUserPassword());
                    roleItem.setUserStatus(user.getUserStatus()=='1'?true:false);

                    roleItems.add(roleItem);
                });

        return SUCCESS;
    }

    /**
     * 移除该权限对应的用户
     * @return
     */
    public String deleteUserByRole(){

        roleItem = new Gson().fromJson(receiveJsonData, RoleItem.class);

        Integer userId = roleItem.getUserId();
        User user = userService.getUsersByUserId(userId);

        Role role = roleService.getRoleById(roleItem.getRoleId());
        Set<User> roleUsers = role.getUsers();

        if(roleUsers.contains(user)){
            roleUsers.remove(user);
        }

        roleService.saveOrUpdateRole(role);

        return NONE;
    }






    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getReceiveJsonData() {
        return receiveJsonData;
    }

    public void setReceiveJsonData(String receiveJsonData) {
        this.receiveJsonData = receiveJsonData;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public RoleItem getRoleItem() {
        return roleItem;
    }

    public void setRoleItem(RoleItem roleItem) {
        this.roleItem = roleItem;
    }

    public List<RoleItem> getRoleItems() {
        return roleItems;
    }

    public void setRoleItems(List<RoleItem> roleItems) {
        this.roleItems = roleItems;
    }
}
