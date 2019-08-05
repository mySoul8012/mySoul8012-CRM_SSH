$(function () {

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
            $("#cust-add-form").append("<input type='hidden' id='custPic' name='custPic' value='"+ret.uploadPath+"'>")
        })
    });




    // 负责view的销毁
    function removeFile( file ) {
        var $li = $('#'+file.id);

        $li.off().find('.file-panel').off().end().remove();


    }

    $("#back").click(function () {
       var goBack = confirm("是否放弃修改？");
       if(goBack){
           window.history.go(-1);
       }
    });

});