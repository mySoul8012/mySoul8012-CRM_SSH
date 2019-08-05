$(function () {
    $("#back").click(function () {
        var goBack = confirm("是否放弃修改？");
        if(goBack){
            window.history.go(-1);
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