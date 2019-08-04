<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/24
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page  isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户查看</title>

    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/cust_show.css">
</head>
<body>
<div id="main">
    <h2 class="subtitle">
        <span>基本信息</span>
    </h2>

    <div class="base-info">
        <div class="form-line">
            <div class="form-item-left">
                <label for="custName"><span class="important-info">*</span>客户姓名：</label>
                <span id="custName"><s:property value="#request.customer.custName"/></span>
            </div>
            <div class="form-item-right">
                <label for="custSource"><span class="important-info">*</span>客户来源：</label>
                <span id="custSource"><s:property value="#request.customer.custSource.dictItemName"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custIndustry"><span class="important-info">*</span>客户行业：</label>
                <span id="custIndustry"><s:property value="#request.customer.custIndustry.dictItemName"/></span>
            </div>
            <div class="form-item-right">
                <label for="custLevel"><span class="important-info">*</span>客户等级：</label>
                <span id="custLevel"><s:property value="#request.customer.custLevel.dictItemName"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custUser"><span class="important-info">*</span>所属用户：</label>
                <span id="custUser"><s:property value="#request.customer.custUser.userCode"/></span>
            </div>
            <div class="form-item-right">

            </div>
        </div>
    </div>


    <h2 class="subtitle">
        <span>详细信息</span>
    </h2>

    <div class="detail-info">
        <div class="form-line">
            <div class="form-item-left">
                <label for="custRegion"><span class="important-info">*</span>客户区域：</label>
                <span id="custRegion"><s:property value="#request.customer.custDetail.custRegion"/></span>
            </div>
            <div class="form-item-right">
                <label for="custZip"><span class="important-info">*</span>客户邮编：</label>
                <span id="custZip"><s:property value="#request.customer.custDetail.custZip"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custAddress"><span class="important-info">*</span>联系地址：</label>
                <span id="custAddress"><s:property value="#request.customer.custDetail.custAddress"/></span>
            </div>
            <div class="form-item-right">
                <label for="custFax"><span class="important-info">*</span>客户传真：</label>
                <span id="custFax"><s:property value="#request.customer.custDetail.custFax"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custWebsite"><span class="important-info">*</span>联系网址：</label>
                <span id="custWebsite"><s:property value="#request.customer.custDetail.custWebsite"/></span>
            </div>
            <div class="form-item-right">
                <label for="custLicense"><span class="important-info">*</span>营业执照：</label>
                <span id="custLicense"><s:property value="#request.customer.custDetail.custLicense"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custCorporation"><span class="important-info">*</span>企业法人：</label>
                <span id="custCorporation"><s:property value="#request.customer.custDetail.custCorporation"/></span>
            </div>
            <div class="form-item-right">
                <label for="custCapital"><span class="important-info">*</span>注册资金：</label>
                <span id="custCapital"><s:property value="#request.customer.custDetail.custCapital"/></span>
            </div>
        </div>
        <div class="form-line">
            <div class="form-item-left">
                <label for="custBank"><span class="important-info">*</span>开户银行：</label>
                <span id="custBank"><s:property value="#request.customer.custDetail.custBank.substring(0,#request.customer.custDetail.custBank.indexOf('#'))"/></span>
            </div>
            <div class="form-item-right">
                <label for="custAccountNumber"><span class="important-info">*</span>开户账号：</label>
                <span id="custAccountNumber"><s:property value="#request.customer.custDetail.custBank.substring(#request.customer.custDetail.custBank.indexOf('#')+1)"/></span>
            </div>
        </div>

        <div class="form-line-pic">
            <div class="form-item-pic">
                <label>客户资质：</label>
                <!--dom结构部分-->
                <img src="${pageContext.request.contextPath}<s:property value="#request.customer.custDetail.custPic"/>" width="200px" height="130px">
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
            <div id="custComment" name="custComment">
                <s:property value="#request.customer.custDetail.custComment" escapeHtml="false"/>
            </div>
        </div>
    </div>
</div>



<script src="js/jquery-3.3.1.js"></script>
<script src="js/global.js"></script>
<script src="js/skin_/cust_show.js"></script>
</body>
</html>
