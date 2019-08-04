package com.ming.web;

import com.google.gson.Gson;
import com.ming.bean.Role;
import com.ming.bean.User;
import com.ming.service.RoleService;
import com.ming.service.UserService;
import com.ming.vo.UserItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static org.apache.struts2.interceptor.MessageStoreInterceptor.NONE;

public class UserAction {

    private UserService userService;

    private RoleService roleService;

    private List<UserItem> userItems = new ArrayList<>();

    //用来接收客户端发送过来的数据
    private String receiveData;

    private UserItem userItem;

    private List<String> allUserNames;

    public String getAllUsers(){

        List<User> users = userService.getAllUsers();

        users.forEach(user -> {
            UserItem userItem = new UserItem();
            userItem.setUserId(user.getUserId());
            userItem.setUserName(user.getUserName());
            userItem.setUserCode(user.getUserCode());
            userItem.setUserPassword(user.getUserPassword());
            userItem.setUserStatus(user.getUserStatus() == '1'?true:false);

            userItems.add(userItem);
        });

        return SUCCESS;
    }


    public String getRolesByUserId(){

        User user = userService.getUsersByUserId(Integer.parseInt(receiveData));

        Set<Role> roles = user.getRoles();

        roles.stream().sorted(Comparator.comparing(Role::getRoleId))
                .forEach(role -> {
                    UserItem item = new UserItem();
                    item.setRoleId(role.getRoleId());
                    item.setRoleName(role.getRoleId());
                    item.setRoleComment(role.getRoleComment());

                    userItems.add(item);
                });

        return SUCCESS;
    }

    public String updateUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);


        //userItem ==> User
        User user = userService.getUsersByUserId(userItem.getUserId());
        user.setUserName(userItem.getUserName());
        user.setUserCode(userItem.getUserCode());
        user.setUserPassword(userItem.getUserPassword());
        user.setUserStatus(userItem.getUserStatus()?'1':'0');

        userService.updateUser(user);

        return NONE;
    }

    public String deleteUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);

        userService.deleteUserById(userItem.getUserId());

        return NONE;
    }

    public String saveUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);

        //userItem ==> User
        User user = new User();
        user.setUserName(userItem.getUserName());
        user.setUserCode(userItem.getUserCode());
        user.setUserPassword(userItem.getUserPassword());
        user.setUserStatus(userItem.getUserStatus()?'1':'0');

        Integer userId = (Integer) userService.saveUser(user);
        userItem.setUserId(userId);

        return SUCCESS;
    }

    public String addRolesForUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);

        Integer roleId = userItem.getRoleName();
        Role role = roleService.getRoleById(roleId);

        User user = userService.getUsersByUserId(userItem.getUserId());
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);

        //user.setRoles(roles);  问题出在这里

        //userService.updateUser(user);

        userItem.setRoleId(roleId);
        userItem.setRoleName(roleId);
        userItem.setRoleComment(role.getRoleComment());

        userService.saveOrUpdateUser(user);

        return SUCCESS;
    }

    public String updateRoleByUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);

        Integer oldRoleId = userItem.getRoleId();
        Role oldRole = roleService.getRoleById(oldRoleId);

        Integer roleId = userItem.getRoleName();
        Role role = roleService.getRoleById(roleId);

        User user = userService.getUsersByUserId(userItem.getUserId());
        Set<Role> userRoles = user.getRoles();

        if(userRoles.contains(oldRole)){
            userRoles.remove(oldRole);
        }

        userRoles.add(role);

        userService.saveOrUpdateUser(user);

        userItem.setRoleId(roleId);
        userItem.setRoleName(roleId);
        userItem.setRoleComment(role.getRoleComment());

        return SUCCESS;
    }

    public String deleteRoleByUser(){

        userItem = new Gson().fromJson(receiveData, UserItem.class);

        Integer roleId = userItem.getRoleName();
        Role role = roleService.getRoleById(roleId);

        User user = userService.getUsersByUserId(userItem.getUserId());
        Set<Role> userRoles = user.getRoles();

        if(userRoles.contains(role)){
            userRoles.remove(role);
        }

        userService.saveOrUpdateUser(user);

        return NONE;
    }

    public String queryAllUserNames(){

        allUserNames = userService.queryAllUserNames();

        return SUCCESS;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<UserItem> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<UserItem> userItems) {
        this.userItems = userItems;
    }

    public String getReceiveData() {
        return receiveData;
    }

    public void setReceiveData(String receiveData) {
        this.receiveData = receiveData;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public List<String> getAllUserNames() {
        return allUserNames;
    }

    public void setAllUserNames(List<String> allUserNames) {
        this.allUserNames = allUserNames;
    }

}
