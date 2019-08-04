$(function () {
    //调整中间区域
    $(".center").height($(window).height() - $(".top").height() - $(".footer").height());

    $(window).resize(function () {
        $(".center").height($(window).height() - $(".top").height() - $(".footer").height());
    });

    //调整导航条的宽度
    (function () {
        //思路：计算导航菜单的最小的宽度，判断调整后中间的宽度是否能容纳下最小宽度，如果不能，则隐藏多余的，否则显示
        //点击左右箭头实现导航的位移
        var navTotalWidth = 0;
        var currentNavItemIndex = 1;

        $.each($(".top-nav").find("li"),function () {
            navTotalWidth += $(this).outerWidth();
        });

        var overflowHiddenItems = Math.ceil((navTotalWidth - $(".nav-wrap").width())/86);

        //计算当前的偏移量（为了实现左右移动）
        function currentLeft() {
            return -(currentNavItemIndex - 1) * 86;
        }

        $(".top-nav-switch i").click(function () {
            //重新计算一个
            //var tempWidth = navTotalWidth - (Math.abs(parseInt($(".top-nav").css("left"))) + $(".nav-wrap").width());

            //向左
            if($(this).hasClass("fa-caret-square-left")){
                //向左
                if(currentNavItemIndex <= overflowHiddenItems){
                    currentNavItemIndex++;

                    //tempWidth > 86 ? $(".top-nav").animate({left:currentLeft()},200) : $(".top-nav").animate({left:$(".nav-wrap").width()-navTotalWidth},200);
                    $(".top-nav").animate({left:currentLeft()},200)
                }

            }else if($(this).hasClass("fa-caret-square-right")){
                //向右
                if(parseInt($(".top-nav").css("left")) < 0){
                    currentNavItemIndex--;

                    Math.abs(parseInt($(".top-nav").css("left"))) > 86 ? $(".top-nav").animate({left: currentLeft()},200) : $(".top-nav").animate({left:0},200);
                }
            }
        });


    })();

   
    //对话框
    $("#modal").iziModal({
        width: 420,
        closeOnEscape: false,
        overlayClose: false
    });

    $(".confirm").click(function () {
        window.location.href = "login.html";
    });
});