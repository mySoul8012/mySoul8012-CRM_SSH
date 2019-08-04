$(function () {
   //一、初始化izimodal
    $("#modal-iframe").iziModal({
        iframe: true,
        width: '70%',
        iframeHeight: 680,
        title: '联系人信息详情',
        theme: 'light',
        headerColor: "#0F8CC7",
        iconColor: "#fff",
        background: "#fff",
        fullscreen: true,
        overlayClose: false
    });

    //二、打开对话框
    $(document).on('click', '.trigger', function (event) {
        event.preventDefault();
        // $('#modal').iziModal('setZindex', 99999);
        // $('#modal').iziModal('open', { zindex: 99999 });
        $('#modal-iframe').iziModal('open',event);
    });

});