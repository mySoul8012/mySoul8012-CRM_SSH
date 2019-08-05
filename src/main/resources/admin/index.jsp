<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/18
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理首页</title>

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="css/iziModal.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/index.css">
</head>
<body>
<div class="container">
    <div class="top">
        <header>
            <div class="header-left">
                <img src="images/skin_/systemlogo.png">

                <a href="javascript:;"><img src="images/skin_/userface.png"></a>
                <span>admin</span>
            </div>

            <div class="header-right">
                <a href="javascript:;" class="exist" data-izimodal-open="#modal" data-izimodal-transitionin="bounceInDown" data-izimodal-transitionOut="bounceOutDown" data-izimodal-preventclose=""><span>退出</span><i class="fas fa-power-off"></i></a>
            </div>
        </header>

        <nav>
            <i class="fas fa-home"></i>
            <div class="nav-wrap">
                <ul class="top-nav">
                    <li><a href="javascript:;">平台管理</a></li>
                    <li><a href="javascript:;">系统管理</a></li>
                    <li><a href="javascript:;">安全管理</a></li>
                    <li><a href="javascript:;">数据中心</a></li>
                    <li><a href="javascript:;">表单管理</a></li>
                    <li><a href="javascript:;">流程管理</a></li>
                    <li><a href="javascript:;">交流中心</a></li>
                    <li><a href="javascript:;">企业邮箱</a></li>
                    <li><a href="javascript:;">个人设置</a></li>
                    <li><a href="javascript:;">其它设置</a></li>
                    <li><a href="javascript:;">企业交流</a></li>
                    <li><a href="javascript:;">其它管理</a></li>
                    <li><a href="javascript:;">中心管理</a></li>
                    <li><a href="javascript:;">邮箱管理</a></li>
                    <li><a href="javascript:;">哈哈管理</a></li>
                </ul>
            </div>
            <div class="top-nav-switch">
                <i class="fas fa-caret-square-left"></i><i class="fas fa-caret-square-right"></i>
            </div>
        </nav>
    </div>

    <div class="center">
        <iframe width="100%" height="100%" id="mainIframe" frameborder="0" src="${pageContext.request.contextPath}/admin/nav"></iframe>
    </div>

    <div class="footer">
        <footer>
            <div class="footer-left">
                <i class="fas fa-info-circle"></i>
                <span>CRM管理系统</span>
                <em>CRM Manager System</em>
            </div>
            <div class="footer-right">
                <span>Manager System</span>
                <em>V1.0 2018</em>
                <i class="fas fa-caret-right"></i>
            </div>
        </footer>
    </div>
</div>

<div id="modal">
    <div class="modal-head">
        <span>提示信息</span>
        <i class="fas fa-times" data-izimodal-close></i>
    </div>
    <div class="modal-body">
        <div class="modal-body-left">
            <img src="images/skin_/right.png">
        </div>

        <div class="modal-body-right">
            <h2>你确定要退出系统？</h2>
            <p>如果是请点击“确定”，否则请点击“取消” </p>
            <div class="btns">
                <button class="confirm">确定</button>
                <button class="cancel" data-izimodal-close>取消</button>
            </div>
        </div>
    </div>
</div>

<script src="js/global.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/iziModal.js"></script>
<script src="js/skin_/index.js"></script>
</body>
</html>
