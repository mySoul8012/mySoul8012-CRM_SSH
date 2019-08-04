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

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/linkman_edit.css">
</head>
<body>
<div id="main">
    <div id="back">
        <i class="fas fa-arrow-circle-left"></i><span>返回查看</span>
    </div>

    <form action="lkm_edit" method="post" enctype="multipart/form-data" id="lkm-add-form">
        <h2 class="subtitle">
            <span>基本信息</span>
        </h2>

        <input type="hidden" id="lkmId" name="lkmId" value="<s:property value='#request.linkman.lkmId'/>">
        <input type="hidden" id="currentPage" name="currentPage" value="<s:property value='#request.currentPage'/>">
        <input type="hidden" id="searchLkmName" name="searchLkmName" value="<s:property value="#request.searchLkmName"/>">
        <input type="hidden" id="searchCustId" name="searchCustId"  value="<s:property value='#request.searchCustId'/>">
        <div class="base-info">
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmName"><span class="important-info">*</span>联系人姓名：</label>
                    <input type="text" id="lkmName" name="lkmName" required value="<s:property value='#request.linkman.lkmName'/>">
                </div>
                <div class="form-item-right">
                    <label><span class="important-info">*</span>联系人性别：</label>
                    <input type="radio" name="lkmGender" value="1" <s:property value="#request.linkman.lkmGender=='1'?'checked':''"/>> 男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="lkmGender" value="0" <s:property value="#request.linkman.lkmGender=='0'?'checked':''"/>> 女
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPhone">&nbsp;&nbsp;联系人座机：</label>
                    <input type="text" id="lkmPhone" name="lkmPhone" value="<s:property value='#request.linkman.lkmPhone'/>">
                </div>
                <div class="form-item-right">
                    <label for="lkmMobile">&nbsp;&nbsp;联系人电话：</label>
                    <input type="text" id="lkmMobile" name="lkmMobile" value="<s:property value='#request.linkman.lkmMobile'/>">
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmEmail">&nbsp;&nbsp;联系人邮箱：</label>
                    <input type="text" id="lkmEmail" name="lkmEmail" value="<s:property value='#request.linkman.lkmEmail'/>">
                </div>
                <div class="form-item-right">
                    <label for="lkmQq">&nbsp;&nbsp;联系人Q&nbsp;Q：</label>
                    <input type="text" id="lkmQq" name="lkmQq" value="<s:property value='#request.linkman.lkmQq'/>">
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="lkmPosition">&nbsp;&nbsp;联系人位置：</label>
                    <input type="text" id="lkmPosition" name="lkmPosition" value="<s:property value='#request.linkman.lkmPosition'/>">
                </div>
                <div class="form-item-right">
                    <label for="custId"><span class="important-info">*</span>所&nbsp;属&nbsp;客&nbsp;户：</label>
                    <select id="custId" name="customer.custId" required>
                        <option value>  ----请选择----  </option>
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

        <input type="submit" value="确认修改" id="add-customer">
    </form>
</div>



    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="utf8-jsp/ueditor.config.js"></script>
    <script src="utf8-jsp/ueditor.all.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/linkman_edit.js"></script>
    <script>
        var ue = UE.getEditor('lkmComment');

        ue.ready(function () {
           ue.setContent('<s:property value="#request.linkman.lkmComment" escapeHtml="false"/>');
        });

        $.get("customerNames",
            function (data) {
                for(var i = 0;i < data.length;i++){
                    var custName = data[i];

                    if(custName[0] == '<s:property value="#request.linkman.customer.custId"/>'){
                        $("#custId").append("<option value='"+custName[0]+"' selected>&nbsp;&nbsp;&nbsp;&nbsp;"+custName[1]+"</option>");
                    }else{
                        $("#custId").append("<option value='"+custName[0]+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+custName[1]+"</option>");
                    }
                }
            });
    </script>
</body>
</html>