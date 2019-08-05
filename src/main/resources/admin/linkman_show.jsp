<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/25
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增客户</title>

    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/linkman_show.css">
</head>
<body>
<div id="main">
    <form action="#" method="post" enctype="multipart/form-data" id="lkm-add-form">
        <h2 class="subtitle">
            <span>基本信息</span>
        </h2>

        <div class="base-info">
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmName"><span class="important-info">*</span>联系人姓名：</label>
                    <span id="lkmName"><s:property value="#request.linkman.lkmName"/></span>
                </div>
                <div class="form-item-right">
                    <label><span class="important-info">*</span>联系人性别：</label>
                    <span id="lkmGender"><s:property value="#request.linkman.lkmGender"/></span>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPhone">&nbsp;&nbsp;联系人座机：</label>
                    <span id="lkmPhone"><s:property value="#request.linkman.lkmPhone"/></span>
                </div>
                <div class="form-item-right">
                    <label for="lkmMobile">&nbsp;&nbsp;联系人电话：</label>
                    <span id="lkmMobile"><s:property value="#request.linkman.lkmMobile"/></span>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmEmail">&nbsp;&nbsp;联系人邮箱：</label>
                    <span id="lkmEmail"><s:property value="#request.linkman.lkmEmail"/></span>
                </div>
                <div class="form-item-right">
                    <label for="lkmQq">&nbsp;&nbsp;联系人Q&nbsp;Q：</label>
                    <span id="lkmQq"><s:property value="#request.linkman.lkmQq"/></span>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPosition">&nbsp;&nbsp;联系人位置：</label>
                    <span id="lkmPosition"><s:property value="#request.linkman.lkmPosition"/></span>
                </div>
                <div class="form-item-right">
                    <label for="custId"><span class="important-info">*</span>所&nbsp;属&nbsp;客&nbsp;户：</label>
                    <span id="custId"><s:property value="#request.linkman.customer.custName"/></span>
                </div>
            </div>
        </div>

        <h2 class="subtitle">
            <span>备注资料</span>
        </h2>

        <div class="form-line-comment">
            <div class="form-item-comment">
                <label>备注内容：</label>
                <!-- 加载编辑器的容器 -->
                <div id="lkmComment" name="lkmComment" type="text/plain">
                    <s:property value="#request.linkman.lkmComment" escapeHtml="false"/>
                </div>
            </div>
        </div>
    </form>
</div>



    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/linkman_show.js"></script>
</body>
</html>