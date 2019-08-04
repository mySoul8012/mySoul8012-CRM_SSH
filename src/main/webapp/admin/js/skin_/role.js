$(function () {
    //表格数据最张来源于哪里？
    //来自于action查询数据库返回的数据，下面数据在js中体现为对象数组，在java中体现为json数据
    //因此action查询数据库之后，需要将数据以json的形式传递到页面
    //action中如何将数据转换成json格式？ List<Role> ==>  json  (在struts中如何实现？)
    //                                 json ==> Role   (在struts中如何接收json数据，并将json数据转换成java对象)
    //通过ajax去获取数据（get/post/ajax）

    $.get("role",function (data) {
        //data为返回的json数据

        //初始化div，并加载相应的数据
        $("#roles").jsGrid({
            width: "100%",
            height: "auto",

            controller: {
                updateItem: function (item) {
                    return $.ajax({
                        type: "post",
                        url: "updateRole",
                        data: {receiveJsonData:JSON.stringify(item)}
                    });
                },
                deleteItem: function(item) {
                    return $.ajax({
                        type: "post",
                        url: "deleteRole",
                        data: {receiveJsonData:JSON.stringify(item)}
                    });
                },
                insertItem: function(item) {
                    return $.ajax({
                        type: "post",
                        url: "saveRole",
                        data: {receiveJsonData: JSON.stringify(item)}
                    });
                }
            },

            rowClick: function(args){

                loadUserByRoleId(args.item);

                return false;
            },


            onRefreshed: function(args) {
                hiddenSuperManagerController();
            },

            onItemDeleted: function(args){
                //清空右侧的内容
                $("#users").empty();
            },

            deleteConfirm: "是否删除此角色",
            noDataContent: "没有找到您想要的数据",


            invalidMessage: "错误消息：",

            inserting: true, //允许插入
            editing: true,  //允许编辑
            sorting: false,  //允许排序
            paging: true,   //允许分页
            filtering: true,

            //表格数据的来源
            data: data,

            //分页修改
            pageSize: 18,
            pagerFormat: "{first} {prev} {pages} {next} {last}  {pageIndex} of {pageCount}",
            pagePrevText: "上一页",
            pageNextText: "下一页",
            pageFirstText: "首页",
            pageLastText: "最后一页",

            //单元格的属性
            fields: [
                { name: "roleId", title: "角色编号", type: "number", width: 30, editing: false, inserting: false, validate: "required"},
                { name: "roleName", title: "角色名称", type: "text", width: 50, validate: "required"},
                { name: "roleComment", title: "角色备注", type: "text"},
                { type: "control" }
            ]
        });

        hiddenSuperManagerController();
        
        function hiddenSuperManagerController() {
            var roleId = $("#roles table tbody tr:first-child td:first-child").text();

            if(roleId == 1){
                $("#roles table tbody tr:first-child td:last-child input").hide();
            }
        }


        function loadUserByRoleId(roleItem) {

            var userData =$.ajax({
                type: "post",
                url: "getUsersByRoleId",
                data: {receiveJsonData:roleItem.roleId},
                async: false,
                dataType: "json"
            }).responseJSON;

            var allUsers = $.ajax({
                type: "post",
                url: "user",
                async: false,
                dataType: "json"
            }).responseJSON;


            $("#users").jsGrid({
                width: "100%",
                height: "auto",

                controller: {
                    deleteItem: function(item) {
                        item.roleId = roleItem.roleId;
                        return $.ajax({
                            type: "post",
                            url: "deleteUserByRole",
                            data: {receiveJsonData:JSON.stringify(item)}
                        });
                    },
                    insertItem: function(item) {
                        item.roleId = roleItem.roleId;
                        console.log(item);
                        return $.ajax({
                            type: "post",
                            url: "addUserByRole",
                            data: {receiveJsonData: JSON.stringify(item)}
                        });
                    }
                },

                deleteConfirm: "是否删除此用户",
                noDataContent: "没有找到您想要的数据",


                invalidMessage: "错误消息：",

                rowClick: function(args){
                    return false;
                },

                inserting: true, //允许插入
                editing: false,  //允许编辑
                sorting: false,  //允许排序
                paging: true,   //允许分页

                //表格数据的来源
                data: userData,

                //分页修改
                pageSize: 18,
                pagerFormat: "{first} {prev} {pages} {next} {last}  {pageIndex} of {pageCount}",
                pagePrevText: "上一页",
                pageNextText: "下一页",
                pageFirstText: "首页",
                pageLastText: "最后一页",

                //单元格的属性
                fields: [
                    { name: "userId", title: "用户编号", type: "number", width: 50, editing: false, inserting: false},
                    { name: "userName", title: "用户名称", type: "text", width: 150, editing: false, inserting: false},
                    { name: "userCode", title: "登录名称", type: "select", items: allUsers, valueField: "userId", textField: "userCode"},
                    { name: "userPassword", title: "用户密码", type: "text", editing: false, inserting: false},
                    { name: "userStatus", title: "用户状态", type: "checkbox", editing: false, inserting: false},
                    { type: "control" }
                ]
            });
        }
        
    });
});