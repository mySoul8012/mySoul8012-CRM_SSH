$(function () {

    //查询一级分类
    var data = $.ajax({
        type: "post",
        url: "getFirstCategories",
        async: false,
        dataType: "json"
    }).responseJSON;

    console.log(data);

    $("#firstCategories").jsGrid({
        width: "100%",
        height: "auto",

        controller: {
            updateItem: function (item) {
                return $.ajax({
                    type: "post",
                    url: "updateFirstCategory",
                    data: {receiveData:JSON.stringify(item)}
                });
            },
            deleteItem: function(item) {
                return $.ajax({
                    type: "post",
                    url: "deleteFirstCategory",
                    data: {receiveData:JSON.stringify(item)}
                });
            },
            insertItem: function(item) {
                return $.ajax({
                    type: "post",
                    url: "addFirstCategory",
                    data: {receiveData: JSON.stringify(item)}
                });
            }
        },

        onItemDeleted: function(args){
            //清空右侧的内容
            $("#secondCategories").empty();
        },

        rowClick: function(args){
            //在右侧加载所有的当前用户所属的角色
            loadCurrentSecondCategories(args.item);

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
            { name: "dictTypeCode", title: "字典类型编码", type: "text", width: 80, editing: false},
            { name: "dictTypeName", title: "字典类型名称", type: "text"},
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
    function loadCurrentSecondCategories(categoryItem) {

        var secondCategoryData = $.ajax({
            type: "post",
            url: "getSecondCategories",
            data: {receiveData:categoryItem.dictTypeCode},
            async: false,
            dataType: "json"
        }).responseJSON;

        $("#secondCategories").jsGrid({
            width: "100%",
            height: "auto",

            controller: {
                insertItem: function(item) {
                    item.dictTypeCode = categoryItem.dictTypeCode;
                    item.dictTypeName = categoryItem.dictTypeName;
                    return $.ajax({
                        type: "post",
                        url: "addSecondCategory",
                        data: {receiveData: JSON.stringify(item)}
                    });
                },
                updateItem: function (item) {
                    console.log(item);
                    return $.ajax({
                        type: "post",
                        url: "updateSecondCategory",
                        data: {receiveData:JSON.stringify(item)}
                    });
                },
                deleteItem: function(item) {
                    return $.ajax({
                        type: "post",
                        url: "deleteSecondCategory",
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
            data: secondCategoryData,

            //分页修改
            pageSize: 18,
            pagerFormat: "{first} {prev} {pages} {next} {last}  {pageIndex} of {pageCount}",
            pagePrevText: "上一页",
            pageNextText: "下一页",
            pageFirstText: "首页",
            pageLastText: "最后一页",

            //单元格的属性
            fields: [
                { name: "dictItemCode", title: "字典条目编号", type: "text"},
                { name: "dictItemName", title: "字典条目名称", type: "text"},
                { name: "dictSort", title: "字典条目排序", type: "number"},
                { name: "dictComment", title: "字典条目备注", type: "text"},
                { type: "control" }
            ]
        });
    }
});