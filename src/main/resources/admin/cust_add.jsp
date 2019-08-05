<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/22
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增客户</title>

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="webuploader/webuploader.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/cust_add.css">

    <script>
        <s:if test="#request.status != null">
            alert("保存成功！");
        </s:if>
    </script>
</head>
<body>
<div id="main">
    <form action="addCustomer" method="post" enctype="multipart/form-data" id="cust-add-form">
        <h2 class="subtitle">
            <span>基本信息</span>
        </h2>

        <div class="base-info">
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custName"><span class="important-info">*</span>客户姓名：</label>
                    <input type="text" id="custName" name="custName" required>
                </div>
                <div class="form-item-right">
                    <label for="custSource"><span class="important-info">*</span>客户来源：</label>
                    <select id="custSource" name="custSource.dictId" required>
                        <c:forEach items="${dicts}" var="dict">
                            <option name="${dict.dictId}" value="${dict.dictId}">${dict.dictTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custIndustry"><span class="important-info">*</span>客户行业：</label>
                    <select id="custIndustry" name="custIndustry.dictId" required>
                        <c:forEach items="${dicts}" var="dict">
                            <option name="${dict.dictId}" value="${dict.dictId}">${dict.dictTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-item-right">
                    <label for="custLevel"><span class="important-info">*</span>客户等级：</label>
                    <select id="custLevel" name="custLevel.dictId" required>
                        <c:forEach items="${dicts}" var="dict">
                            <option name="${dict.dictId}" value="${dict.dictId}">${dict.dictTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-line">
                <div class="form-item-left">
                    <label for="custUser"><span class="important-info">*</span>所属用户：</label>
                    <select id="custUser" name="custUser.userId" required>

                    </select>
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
                    <input type="text" id="custRegion" name="custRegion" required>
                </div>
                <div class="form-item-right">
                    <label for="custZip"><span class="important-info">*</span>客户邮编：</label>
                    <input type="text" id="custZip" name="custZip" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custAddress"><span class="important-info">*</span>联系地址：</label>
                    <input type="text" id="custAddress" name="custAddress" required>
                </div>
                <div class="form-item-right">
                    <label for="custFax"><span class="important-info">*</span>客户传真：</label>
                    <input type="text" id="custFax" name="custFax" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custWebsite"><span class="important-info">*</span>联系网址：</label>
                    <input type="text" id="custWebsite" name="custWebsite" required>
                </div>
                <div class="form-item-right">
                    <label for="custlicense"><span class="important-info">*</span>营业执照：</label>
                    <input type="text" id="custlicense" name="custlicense" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custCorporation"><span class="important-info">*</span>企业法人：</label>
                    <input type="text" id="custCorporation" name="custCorporation" required>
                </div>
                <div class="form-item-right">
                    <label for="custCapital"><span class="important-info">*</span>注册资金：</label>
                    <input type="number" id="custCapital" name="custCapital" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custBank"><span class="important-info">*</span>开户银行：</label>
                    <input type="text" id="custBank" name="custBank" required>
                </div>
                <div class="form-item-right">
                    <label for="custAccountNumber"><span class="important-info">*</span>开户账号：</label>
                    <input type="number" id="custAccountNumber" name="custAccountNumber" required>
                </div>
            </div>

            <div class="form-line-pic">
                <div class="form-item-pic">
                    <label>客户资质：</label>
                    <!--dom结构部分-->
                    <div id="uploader-pic">
                        <!--用来存放item-->
                        <div id="fileList" class="uploader-list"></div>
                        <div id="filePicker"></div>
                    </div>
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
                <script id="custComment" name="custComment" type="text/plain"></script>
            </div>
        </div>

        <input type="submit" value="添加客户" id="add-customer" style="margin-top:300px">
    </form>
</div>

    <script src="js/jquery-3.3.1.js"></script>
    <script src="webuploader/webuploader.min.js"></script>
    <script src="utf8-jsp/ueditor.config.js"></script>
    <script src="utf8-jsp/ueditor.all.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/cust_add.js"></script>
</body>
</html>