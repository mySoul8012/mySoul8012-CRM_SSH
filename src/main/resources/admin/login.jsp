<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/18
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page  isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理界面登录</title>

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/login.css">
</head>
<body>
<div class="login-container">
    <!--登录框-->
    <div class="login-frame">
        <i class="system-icon"></i>
        <i class="system-name"></i>

        <div class="login-input">
            <form method="post" action="${pageContext.request.contextPath}/admin/checkLogin">
                <div class="user-name">
                    <span>用户名：</span>
                    <div class="user-input-wrap">
                        <i class="fas fa-user"></i>
                        <input type="text" id="userCode" name="userCode" required>
                    </div>
                </div>
                <div class="user-pwd">
                    <span>密&nbsp;&nbsp;&nbsp;码：</span>
                    <div class="password-input-wrap">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="userPassword" name="userPassword" required>
                    </div>
                </div>
                <div class="validate-wrap">
                    <div class="validate">
                        <span>验证码：</span>
                        <div class="validate-input-wrap">
                            <input type="text" id="validateCode" name="validateCode" maxlength="4" required>
                            <span class="validate-code">
                                <img src="${pageContext.request.contextPath}/admin/validate" id="validateImg" name="validateImg">
                            </span>
                        </div>
                    </div>
                    <div class="submit-wrap">
                        <input type="submit" value="提  交">
                    </div>
                </div>
            </form>
        </div>

        <div class="error-msg">
            <span class="msg"><s:property value="exception.message"/></span>
        </div>
    </div>

    <!--底部的版权信息-->
    <footer>
        <span>Copyright&copy;2018 手把手课堂</span>
    </footer>
</div>

<script src="js/jquery-3.3.1.js"></script>
<script src="js/global.js"></script>
<script src="js/skin_/login.js"></script>
</body>
</html>
