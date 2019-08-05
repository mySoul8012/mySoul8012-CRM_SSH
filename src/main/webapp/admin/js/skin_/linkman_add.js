$(function () {
   var ue = UE.getEditor('lkmComment');

    $.get("customerNames",
        function (data) {
            for(var i = 0;i < data.length;i++){
                var custName = data[i];

                $("#custId").append("<option value='"+custName[0]+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+custName[1]+"</option>");
            }
        });


    //使用jqueryvalidation添加表单验证
    $("#lkm-add-form").validate({
        rules:{
            lkmName: "required",
            "customer.custId": "required"
        },
        messages:{
            lkmName: "请输入联系人的姓名",
            "customer.custId": "请选择所属客户"
        }
    });
});