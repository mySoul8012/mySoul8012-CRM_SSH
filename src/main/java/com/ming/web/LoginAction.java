package com.ming.web;

import com.ming.bean.User;
import com.ming.exception.LoginException;
import com.ming.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    /**
     *  用户信息
     */
    private User sysUserEntity;
    /**
     *  验证码
     */
    private String validateCode;


    /**
     * service
     */
    private UserService userService;

    private String userCode;

    private String userPassword;


    /**
     *  model 填充
     * @return
     */
    public User getModel() {
        if(sysUserEntity != null){
            sysUserEntity = new User();
        }
        return sysUserEntity;
    }


    public User getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(User sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 业务逻辑   c
     * @return
     */
    public String checkLogin(){
        // 从session中取出
        String code = (String)ServletActionContext.getRequest().getSession().getAttribute("checkCode");

        // 判断验证码是否为空
        if(StringUtils.isEmpty(validateCode)){
            throw new LoginException("验证码为空");
        }

        // 判断验证码是否正
        if(!code.equalsIgnoreCase(validateCode)){
            throw new LoginException("验证码错误");
        }

        // 从servicecento获取用户对象
        this.sysUserEntity = new User();
        this.sysUserEntity.setUserCode(this.userCode);
        this.sysUserEntity.setUserPassword(this.userPassword);
        User sysUserEntity = userService.getUserByUserCode(this.sysUserEntity);

        // 保存进入session
        ServletActionContext.getRequest().getSession().setAttribute("user", sysUserEntity);

        return SUCCESS;



    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
