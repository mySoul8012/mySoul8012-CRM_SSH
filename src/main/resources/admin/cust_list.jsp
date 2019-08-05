<%--
  Created by IntelliJ IDEA.
  User: WaterLifer
  Date: 2018/8/23
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page  isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="css/fontawesome-all.css">
    <link rel="stylesheet" href="css/iziModal.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/skin_/cust_list.css">
</head>
<body>

<div id="main">
    <div id="search-area">
        <form action="custList" method="get" id="searchForm">
            <label>客户名称：</label>
            <input type="text" id="searchKey" name="searchKey" value="<s:property value="#request.searchKey"/>">
            <input type="submit" id="search" value="搜索一下">
        </form>
    </div>

    <div id="data-table">
        <table width="100%" cellspacing="0" cellpadding="0" id="data">
            <thead>
            <tr>
                <th>客户名称</th>
                <th>客户来源</th>
                <th>客户行业</th>
                <th>客户等级</th>
                <th>所属用户</th>
                <th>企业网址</th>
                <th>企业法人</th>
                <th>营业执照</th>
                <th>联系地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <s:iterator value="#request.customers.datas" var="customer">
                    <tr>
                        <td>${customer.custName}</td>
                        <td>${customer.custSource.dictItemName}</td>
                        <td>${customer.custIndustry.dictItemName}</td>
                        <td>${customer.custLevel.dictItemName}</td>
                        <td>${customer.custUser.userCode}</td>
                        <td>${customer.custDetail.custWebsite}</td>
                        <td>${customer.custDetail.custCorporation}</td>
                        <td>${customer.custDetail.custlicense}</td>
                        <td>${customer.custDetail.custAddress}</td>
                        <td>
                            <a href="showCustomer?custId=${customer.custId}" class="trigger"><i class="fas fa-eye"></i></a>
                            <a href="showCustomer?&operate=edit&custId=${customer.custId}&currentPage=<s:property value='#request.customers.currentPage'/>&searchKey=<s:property value='#request.searchKey' />"><i class="fas fa-edit"></i></a>
                            <a href="javascript:;" onclick="deleteItem(${customer.custId},'<s:property value="#request.customers.currentPage"/>','<s:property value="#request.searchKey"/>')"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>

    <div id="pagination">
        <s:if test="#request.customers.currentPage == 1">
            <a href="javascript:;" class="prev"><&nbsp;上一页</a>
        </s:if>
        <s:else>
            <a href="custList?currentPage=<s:property value="#request.customers.currentPage-1"/>&searchKey=<s:property value="#request.searchKey"/>" class="prev"><&nbsp;上一页</a>
        </s:else>


        <s:iterator begin="1" end="#request.customers.totalPages" var="pageIndex">
            <s:if test="#request.customers.currentPage == #pageIndex">
                <a href="custList?currentPage=${pageIndex}&searchKey=<s:property value="#request.searchKey"/>" class="current">${pageIndex}</a>
            </s:if>
            <s:else>
                <a href="custList?currentPage=${pageIndex}&searchKey=<s:property value="#request.searchKey"/>">${pageIndex}</a>
            </s:else>
        </s:iterator>

        <%--<a href="javascript:;" class="prev"><&nbsp;上一页</a>
        <a href="javascript:;">1</a>
        <a href="javascript:;" class="current">2</a>
        <a href="javascript:;">3</a>
        <a href="javascript:;">4</a>
        <a href="javascript:;">5</a>
        <a href="javascript:;">6</a>
        <a href="javascript:;" class="next">下一页&nbsp;></a>--%>

        <s:if test="#request.customers.currentPage == #request.customers.totalPages">
            <a href="javascript:;" class="next">下一页&nbsp;></a>
        </s:if>
        <s:else>
            <a href="custList?currentPage=<s:property value="#request.customers.currentPage+1"/>&searchKey=<s:property value="#request.searchKey"/>" class="next">下一页&nbsp;></a>
        </s:else>
    </div>
</div>

    <div id="modal-iframe"></div>
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/iziModal.js"></script>
    <script src="js/global.js"></script>
    <script src="js/skin_/cust_list.js"></script>
    <script>
        function deleteItem(custId, currentPage, searchKey) {
            var result = confirm("确认删除此客户？");
            if(result){
                window.location.href = "deleteCustomer?custId=" + custId + "&currentPage=" + currentPage + "&searchKey=" + searchKey;
            }
        }
    </script>
</body>
</html>
