$(function () {
    //初始化ueditor编辑器
    var ue = UE.getEditor('custComment');

    //加载客户来源 F004
    loadSecondCategory("F004", "custSource");

    //客户行业 F002
    loadSecondCategory("F002", "custIndustry");

    //客户等级 F008
    loadSecondCategory("F008", "custLevel");

    //使用ajax加载字典数据
    function loadSecondCategory(typeCode, element) {
        $.get("getSecondCategories",
            {receiveData: typeCode},
            function (data) {
                for(var i = 0;i < data.length;i++){
                    var itemDict = data[i];

                    $("#" + element).append("<option value='"+itemDict.dictId+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+itemDict.dictItemName+"</option>");
                }
            });
    }


    //使用ajax加载用户数据
    $.get("user",
        function (data) {
            for(var i = 0;i < data.length;i++){
                var itemUser = data[i];

                $("#custUser").append("<option value='"+itemUser.userId+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+itemUser.userCode+"</option>");
            }
        });



    //创建webuploader实例
   var uploader = WebUploader.create({

       // 选完文件后，是否自动上传。
       auto: true,

       // swf文件路径
       swf: '../../webuploader/Uploader.swf',

       // 文件接收服务端。
       server: 'upload',

       // 选择文件的按钮。可选。
       // 内部根据当前运行是创建，可能是input元素，也可能是flash.
       pick: {
           id: "#filePicker",
           innerHTML: "选择图片",
           multiple: false
       },

       fileNumLimit: 1,

       // 只允许选择图片文件。
       accept: {
           title: 'Images',
           extensions: 'gif,jpg,jpeg,bmp,png',
           mimeTypes: 'image/*'
       }
   });

    //回显
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img></div>'
            ),
            $btns = $('<div class="file-panel">' +
                '<span class="cancel"><i class="fas fa-trash-alt"></i></span></div>').appendTo( $li ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $("#fileList").append( $li );


        if(file.getStatus() == 'invalid'){
            //略

        }else{
            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr( 'src', src );
            }, 200, 130 );
        }

        $li.on("mouseenter", function () {
            $btns.stop().animate({height: 30});
        });

        $li.on("mouseleave",function () {
            $btns.stop().animate({height: 0});
        });



        $btns.on("click","span",function () {
            //从uploader中移除，并没有将回显的图片移除
            uploader.removeFile(file);
        });

        //当用户从uploader中删除图片时，会触发此方法
        uploader.onFileDequeued = function( file ) {
            //同时还需要将回显的图片移除
            removeFile(file);

            $("#custPic").remove();
        };

        //因为此表单需要发送到后台接收数据，因此我们需要为它添加一个隐藏域
        //<input type="hidden" id="custPic" name="custPic" value="path"/>
        uploader.on("uploadAccept",function (object,ret) {
            console.log(ret);
            $("#cust-add-form").append("<input type='hidden' id='custPic' name='custPic' value='"+ret.uploadPath+"'>")
        })
    });




    // 负责view的销毁
    function removeFile( file ) {
        var $li = $('#'+file.id);

        $li.off().find('.file-panel').off().end().remove();


    }


    //使用jqueryvalidation添加表单验证
    $("#cust-add-form").validate({
       rules:{
           custName: "required",
           "custSource.dictId": "required",
           "custIndustry.dictId": "required",
           "custLevel.dictId": "required",
           "custUser.userId": "required"
       },
        messages:{
            custName: "请输入客户名称",
            "custSource.dictId": "请选择客户来源",
            "custIndustry.dictId": "请选择客户行业",
            "custLevel.dictId": "请选择客户等级",
            "custUser.userId": "请选择客户所属用户"
        }
    });

});