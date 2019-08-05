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
    <link rel="stylesheet" href="css/skin_/cust_edit.css">
</head>
<body>
<div id="main">
    <div id="back">
        <i class="fas fa-arrow-circle-left"></i><span>返回查看</span>
    </div>


    <form action="updateCustomer" method="post" enctype="multipart/form-data" id="cust-add-form">
        <h2 class="subtitle">
            <span>基本信息</span>
        </h2>

        <input type="hidden" id="custId" name="custId" value="<s:property value="#request.customer.custId"/>">
        <input type="hidden" id="currentPage" name="currentPage" value="<s:property value='#request.currentPage'/>">
        <input type="hidden" id="searchKey" name="searchKey" value="<s:property value="#request.searchKey"/>">
        <div class="base-info">
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custName"><span class="important-info">*</span>客户姓名：</label>
                    <input type="text" id="custName" name="custName" value="<s:property value="#request.customer.custName"/>" required>
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
                        <option>  ----请选择----  </option>
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
                    <input type="text" id="custRegion" name="custRegion" value="<s:property value="#request.customer.custDetail.custRegion"/>" required>
                </div>
                <div class="form-item-right">
                    <label for="custZip"><span class="important-info">*</span>客户邮编：</label>
                    <input type="text" id="custZip" name="custZip" value="<s:property value="#request.customer.custDetail.custZip"/>" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custAddress"><span class="important-info">*</span>联系地址：</label>
                    <input type="text" id="custAddress" name="custAddress" value="<s:property value="#request.customer.custDetail.custAddress"/>" required>
                </div>
                <div class="form-item-right">
                    <label for="custFax"><span class="important-info">*</span>客户传真：</label>
                    <input type="text" id="custFax" name="custFax" value="<s:property value="#request.customer.custDetail.custFax"/>" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custWebsite"><span class="important-info">*</span>联系网址：</label>
                    <input type="text" id="custWebsite" name="custWebsite" value="<s:property value="#request.customer.custDetail.custWebsite"/>" required>
                </div>
                <div class="form-item-right">
                    <label for="custlicense"><span class="important-info">*</span>营业执照：</label>
                    <input type="text" id="custlicense" name="custlicense" value="<s:property value="#request.customer.custDetail.custLicense"/>" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custCorporation"><span class="important-info">*</span>企业法人：</label>
                    <input type="text" id="custCorporation" name="custCorporation" value="<s:property value="#request.customer.custDetail.custCorporation"/>" required>
                </div>
                <div class="form-item-right">
                    <label for="custCapital"><span class="important-info">*</span>注册资金：</label>
                    <input type="number" id="custCapital" name="custCapital" value="<s:property value="#request.customer.custDetail.custCapital"/>" required>
                </div>
            </div>
            <div class="form-line">
                <div class="form-item-left">
                    <label for="custBank"><span class="important-info">*</span>开户银行：</label>
                    <input type="text" id="custBank" name="custBank" value="<s:property value="#request.customer.custDetail.custBank.substring(0,#request.customer.custDetail.custBank.indexOf('#'))"/>" required>
                </div>
                <div class="form-item-right">
                    <label for="custAccountNumber"><span class="important-info">*</span>开户账号：</label>
                    <input type="number" id="custAccountNumber" name="custAccountNumber" value="<s:property value="#request.customer.custDetail.custBank.substring(#request.customer.custDetail.custBank.indexOf('#')+1)"/>" required>
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

        <input type="submit" value="修改客户" id="add-customer" style="margin-top:300px">
    </form>
</div>

    <script src="js/jquery-3.3.1.js"></script>
    <script src="webuploader/webuploader.min.js"></script>
    <script src="utf8-jsp/ueditor.config.js"></script>
    <script src="utf8-jsp/ueditor.all.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/cust_edit.js"></script>
    <script>
        //初始化ueditor编辑器
        var ue = UE.getEditor('custComment');

        ue.ready(function () {
           ue.setContent('<s:property value="#request.customer.custDetail.custComment" escapeHtml="false"/>');
        });

        //加载客户来源 F004
        loadSecondCategory("F004", "custSource", "<s:property value='#request.customer.custSource.dictId'/>");

        //客户行业 F002
        loadSecondCategory("F002", "custIndustry", "<s:property value='#request.customer.custIndustry.dictId'/>");

        //客户等级 F008
        loadSecondCategory("F008", "custLevel", "<s:property value='#request.customer.custLevel.dictId'/>");

        //使用ajax加载字典数据
        function loadSecondCategory(typeCode, element, selectedId) {
            $.get("getSecondCategories",
                {receiveData: typeCode},
                function (data) {
                    for(var i = 0;i < data.length;i++){
                        var itemDict = data[i];

                        if(itemDict.dictId == selectedId){
                            $("#" + element).append("<option value='"+itemDict.dictId+"' selected>&nbsp;&nbsp;&nbsp;&nbsp;"+itemDict.dictItemName+"</option>");
                        }else{
                            $("#" + element).append("<option value='"+itemDict.dictId+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+itemDict.dictItemName+"</option>");
                        }
                    }
                });
        }


        //使用ajax加载用户数据
        $.get("user",
            function (data) {
                for(var i = 0;i < data.length;i++){
                    var itemUser = data[i];

                    if(itemUser.userId == <s:property value="#request.customer.custUser.userId"/>){
                        $("#custUser").append("<option value='"+itemUser.userId+"' selected>&nbsp;&nbsp;&nbsp;&nbsp;"+itemUser.userCode+"</option>");
                    }else{
                        $("#custUser").append("<option value='"+itemUser.userId+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+itemUser.userCode+"</option>");
                    }
                }
            });


        loadCustDetailPic();

        //显示图片
        function loadCustDetailPic() {
            var $li = $('<div id="<s:property value='#request.customer.custId'/>" class="file-item thumbnail"><img src="${pageContext.request.contextPath}<s:property value='#request.customer.custDetail.custPic'/>" width="200px" height="130px"></div>'),
                $btns = $('<div class="file-panel">' +
                '<span class="cancel"><i class="fas fa-trash-alt"></i></span></div>').appendTo( $li );

            $li.append('<input type="hidden" id="custPic" name="custPic" value="<s:property value='#request.customer.custDetail.custPic'/>">');

            $("#fileList").append($li);

            $li.on("mouseenter", function () {
                $btns.stop().animate({height: 30});
            });

            $li.on("mouseleave",function () {
                $btns.stop().animate({height: 0});
            });

            $btns.on("click","span",function () {
                $("#fileList").empty();
            });
        }



    </script>
</body>
</html>