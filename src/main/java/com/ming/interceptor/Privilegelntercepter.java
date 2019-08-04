package com.ming.interceptor;

import com.ming.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * 检测用户是否放行  用于struts2
 */
public class Privilegelntercepter extends MethodFilterInterceptor {


    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        // 获取session
        HttpSession session = ServletActionContext.getRequest().getSession();
        // 获取用户
        User sysUserEntity = (User)session.getAttribute("user");
        if(sysUserEntity != null){
            // 放行
            return actionInvocation.invoke();
        }
        // 跳转登录
        return "toLogin";
    }
}
