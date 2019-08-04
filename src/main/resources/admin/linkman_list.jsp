<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/25
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="css/iziModal.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/linkman_list.css">
</head>
<body>

<div id="main">
    <div id="search-area">
        <form action="lkm_list" method="get" id="searchForm">
            <label>联系人姓名：</label>
            <input type="text" id="lkmName" name="lkmName" value="<s:property value='#request.linkman.lkmName'/>">
            <label for="custId"><span class="important-info">*</span>所属客户：</label>
            <select id="custId" name="customer.custId">
                <option value>  ----请选择----  </option>
            </select>
            <input type="submit" id="search" value="搜索一下">
        </form>
    </div>

    <div id="data-table">
        <table width="100%" cellspacing="0" cellpadding="0" id="data">
            <thead>
            <tr>
                <th>联系人姓名</th>
                <th width="100px">联系人性别</th>
                <th>所属用户</th>
                <th>联系人座机</th>
                <th>联系人电话</th>
                <th>联系人邮箱</th>
                <th width="160px">联系人Q Q</th>
                <th width="300px">联系人地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="#request.linkmanPageBean.datas">
                <tr>
                    <td><s:property value="lkmName"/></td>
                    <td><s:property value="lkmGender=='1'?'男':'女'"/></td>
                    <td><s:property value="customer.custName"/></td>
                    <td><s:property value="lkmPhone"/></td>
                    <td><s:property value="lkmMobile"/></td>
                    <td><s:property value="lkmEmail"/></td>
                    <td><s:property value="lkmQq"/></td>
                    <td><s:property value="lkmPosition"/></td>
                    <td>
                        <a href="lkm_operate?lkmId=<s:property value='lkmId'/>" class="trigger"><i class="fas fa-eye"></i></a>
                        <a href="lkm_operate?operate=edit&lkmId=<s:property value='lkmId'/>&currentPage=<s:property value='#request.linkmanPageBean.currentPage'/>&searchLkmName=<s:property value='#request.linkman.lkmName'/>&searchCustId=<s:property value='#request.linkman.customer.custId'/>"><i class="fas fa-edit"></i></a>
                        <a href="javascript:;" onclick="deleteItem(<s:property value='lkmId'/>,<s:property value="#request.linkmanPageBean.currentPage"/>,'<s:property value="#request.linkman.lkmName"/>','<s:property value="#request.linkman.customer.custId"/>')"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
            </s:iterator>

            </tbody>
        </table>
    </div>

    <div id="pagination">
        <s:if test="#request.linkmanPageBean.currentPage == 1">
            <a href="javascript:;" class="prev"><&nbsp;上一页</a>
        </s:if>
        <s:else>
            <a href="lkm_list?currentPage=<s:property value="#request.linkmanPageBean.currentPage-1"/>&lkmName=<s:property value='#request.linkman.lkmName'/>&customer.custId=<s:property value='#request.linkman.customer.custId'/>" class="prev"><&nbsp;上一页</a>
        </s:else>


        <s:iterator begin="1" end="#request.linkmanPageBean.totalPages" var="pageIndex">
            <s:if test="#request.linkmanPageBean.currentPage == #pageIndex">
                <a href="lkm_list?currentPage=${pageIndex}&lkmName=<s:property value='#request.linkman.lkmName'/>&customer.custId=<s:property value='#request.linkman.customer.custId'/>" class="current">${pageIndex}</a>
            </s:if>
            <s:else>
                <a href="lkm_list?currentPage=${pageIndex}&lkmName=<s:property value='#request.linkman.lkmName'/>&customer.custId=<s:property value='#request.linkman.customer.custId'/>">${pageIndex}</a>
            </s:else>
        </s:iterator>

        <s:if test="#request.linkmanPageBean.currentPage == #request.linkmanPageBean.totalPages">
            <a href="javascript:;" class="next">下一页&nbsp;></a>
        </s:if>
        <s:else>
            <a href="lkm_list?currentPage=<s:property value="#request.linkmanPageBean.currentPage+1"/>&lkmName=<s:property value='#request.linkman.lkmName'/>&customer.custId=<s:property value='#request.linkman.customer.custId'/>" class="next">下一页&nbsp;></a>
        </s:else>
    </div>
    <%--<div id="pagination">
        <a href="javascript:;" class="prev"><&nbsp;上一页</a>
        <a href="javascript:;">1</a>
        <a href="javascript:;" class="current">2</a>
        <a href="javascript:;">3</a>
        <a href="javascript:;">4</a>
        <a href="javascript:;">5</a>
        <a href="javascript:;">6</a>
        <a href="javascript:;" class="next">下一页&nbsp;></a>
    </div>--%>
</div>

    <div id="modal-iframe"></div>
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/iziModal.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/linkman_list.js"></script>
    <script>
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

        function deleteItem(lkmId, currentPage, lkmName, custId) {
            var result = confirm("确认删除此联系人？");
            if(result){
                var link = "lkm_delete?lkmId=" + lkmId + "&currentPage=" + currentPage;

                if(lkmName){
                    link += "&lkmName=" + lkmName;
                }
                if(custId){
                    link += "&customer.custId=" + custId;
                }

                window.location.href = link;
            }
        }
    </script>
</body>
</html>
