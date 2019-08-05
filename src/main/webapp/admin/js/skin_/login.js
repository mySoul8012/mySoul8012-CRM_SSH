$(function () {
   $("#validateImg").click(function () {
       $(this).attr("src","${pageContext.request.contextPath}/admin/validate?random=" + new Date().getTime());
   });
});