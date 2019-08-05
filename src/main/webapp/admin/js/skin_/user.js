$(function () {

    //第一种方式  $.get(url,function(data){ $().jsGrid();   })

    //第二种方式 使用同步的方式
    var data = $.ajax({
       type: "post",
       url: "user",
       async: false,
       dataType: "json"
    }).responseJSON;


    $("#users").jsGrid({
        width: "100%",
        height: "auto",

        controller: {
            updateItem: function (item) {
                return $.ajax({
                    type: "post",
                    url: "updateUser",
                    data: {receiveData:JSON.stringify(item)}
                });
            },
            deleteItem: function(item) {
                return $.ajax({
                    type: "post",
                    url: "deleteUser",
                    data: {receiveData:JSON.stringify(item)}
                });
            },
            insertItem: function(item) {
                return $.ajax({
                    type: "post",
                    url: "saveUser",
                    data: {receiveData: JSON.stringify(item)}
                });
            }
        },

        deleteConfirm: "是否删除此用户",
        noDataContent: "没有找到您想要的数据",


        invalidMessage: "错误消息：",

        onItemDeleted: function(args){
            //清空右侧的内容
            $("#roles").empty();
        },

        rowClick: function(args){
            //在右侧加载所有的当前用户所属的角色
            loadCurrentUserRoles(args.item);

            return false;
        },

        inserting: true, //允许插入
        editing: true,  //允许编辑
        sorting: false,  //允许排序
        paging: true,   //允许分页

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
            { name: "userId", title: "用户编号", type: "number", width: 50, editing: false, inserting: false, validate: "required"},
            { name: "userName", title: "用户名称", type: "text", width: 150, validate: {
                    message: "用户名不能为空",
                    validator: "required"
                }},
            { name: "userCode", title: "登录名称", type: "text", validate:{
                    message: "用户登录名称不能重复",
                    validator: function (value, item, param) {
                        //查询所有用户名称，判断当前用户输入的登录名是否已经存在
                        var usernames = $.ajax({
                            type: "post",
                            url: "getAllUserNames",
                            async: false
                        }).responseText;
                        return usernames.indexOf(value) > 0?false:true;
                    }
                }},
            { name: "userPassword", title: "用户密码", type: "text", validate: {
                    message: "用户密码不能为空",
                    validator: "required"
                }},
            { name: "userStatus", title: "用户状态", type: "checkbox", validate: "required"},
            { type: "control" }
        ]
    });

    hiddenSuperManagerController();

    function hiddenSuperManagerController() {
        var userId = $("#users table tbody tr:first-child td:first-child").text();

        if(userId == 1){
            $("#users table tbody tr:first-child td:last-child input").hide();
        }
    }

    //点击用户条目，加载角色数据
    function loadCurrentUserRoles(userItem) {

        var rolesData = $.ajax({
            type: "post",
            url: "getRolesByUserId",
            data: {receiveData:userItem.userId},
            async: false,
            dataType: "json"
        }).responseJSON;

        console.log(rolesData);

        var roles = $.ajax({
            type: "post",
            url: "role",
            async: false,
            dataType: "json"
        }).responseJSON;


        $("#roles").jsGrid({
            width: "100%",
            height: "auto",

            controller: {
                insertItem: function(item) {
                    item.userId = userItem.userId;
                    return $.ajax({
                        type: "post",
                        url: "addRoleForUser",
                        data: {receiveData: JSON.stringify(item)}
                    });
                },
                updateItem: function (item) {
                    item.userId = userItem.userId;
                    return $.ajax({
                        type: "post",
                        url: "updateRoleByUser",
                        data: {receiveData:JSON.stringify(item)}
                    });
                },
                deleteItem: function(item) {
                    item.userId = userItem.userId;
                    return $.ajax({
                        type: "post",
                        url: "deleteRoleByUser",
                        data: {receiveData:JSON.stringify(item)}
                    });
                }
            },

            rowClick: function(args){
                return false;
            },

            inserting: true, //允许插入
            editing: true,  //允许编辑
            sorting: false,  //允许排序
            paging: true,   //允许分页

            //表格数据的来源
            data: rolesData,

            //分页修改
            pageSize: 18,
            pagerFormat: "{first} {prev} {pages} {next} {last}  {pageIndex} of {pageCount}",
            pagePrevText: "上一页",
            pageNextText: "下一页",
            pageFirstText: "首页",
            pageLastText: "最后一页",

            //单元格的属性
            fields: [
                { name: "roleId", title: "角色编号", type: "number", width: 30, editing: false, inserting: false},
                { name: "roleName", title: "角色名称", type: "select", items: roles, width: 50, valueField: "roleId", textField: "roleName" },
                { name: "roleComment", title: "角色备注", type: "text", editing: false, inserting: false},
                { type: "control" }
            ]
        });
    }
});