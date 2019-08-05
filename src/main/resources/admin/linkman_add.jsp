<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/25
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增客户</title>

    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/linkman_add.css">
    <script>
        <s:if test="#request.status != null">
            alert("保存成功！");
        </s:if>
    </script>
</head>
<body>
<div id="main">
    <form action="lkm_add" method="post" enctype="multipart/form-data" id="lkm-add-form">
        <h2 class="subtitle">
            <span>基本信息</span>
        </h2>

        <div class="base-info">
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmName"><span class="important-info">*</span>联系人姓名：</label>
                    <input type="text" id="lkmName" name="lkmName" required>
                </div>
                <div class="form-item-right">
                    <label><span class="important-info">*</span>联系人性别：</label>
                    <input type="radio" name="lkmGender" value="1" checked> 男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="lkmGender" value="0"> 女
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPhone">&nbsp;&nbsp;联系人座机：</label>
                    <input type="text" id="lkmPhone" name="lkmPhone">
                </div>
                <div class="form-item-right">
                    <label for="lkmMobile">&nbsp;&nbsp;联系人电话：</label>
                    <input type="text" id="lkmMobile" name="lkmMobile">
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmEmail">&nbsp;&nbsp;联系人邮箱：</label>
                    <input type="text" id="lkmEmail" name="lkmEmail">
                </div>
                <div class="form-item-right">
                    <label for="lkmQq">&nbsp;&nbsp;联系人Q&nbsp;Q：</label>
                    <input type="text" id="lkmQq" name="lkmQq">
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPosition">&nbsp;&nbsp;联系人位置：</label>
                    <input type="text" id="lkmPosition" name="lkmPosition">
                </div>

                <div class="form-item-right">
                    <label for="custId"><span class="important-info">*</span>所&nbsp;属&nbsp;客&nbsp;户：</label>
                    <select id="custId" name="customer.custId" required>

                    </select>
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
                <script id="lkmComment" name="lkmComment" type="text/plain"></script>
            </div>
        </div>

        <input type="submit" value="添加联系人" id="add-customer" style="margin-top:300px">
    </form>
</div>



    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="utf8-jsp/ueditor.config.js"></script>
    <script src="utf8-jsp/ueditor.all.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/linkman_add.js"></script>
</body>
</html>