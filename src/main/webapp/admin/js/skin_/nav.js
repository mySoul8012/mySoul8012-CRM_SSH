$(function () {

    $(".nav-container").height($(window).height() - $(".footer").height());
    $(".right-content-iframe").height($(".nav-container").height() - $(".right-content-tab").height() - 20);

    $(window).resize(function () {
        $(".nav-container").height($(window).height() - $(".footer").height());
        $(".right-content-iframe").height($(".nav-container").height() - $(".right-content-tab").height() - 20);
    });

    //在左侧的菜单中，点击隐藏按钮，隐藏左侧菜单
    $(".hidden-menu").click(function () {
        $(".left-nav").hide();

        $(".show-menu").show().css("left", 0);

        $(".right-content-header").css("padding-left", 0);
        $(".right-content-iframe").css("padding-left", 5);
    });

    //在右侧的头部分，点击显示按钮，显示左侧菜单
    $(".show-menu").click(function () {
        $(".left-nav").show();
        $(".show-menu").hide();

        $(".right-content-header").css("padding-left", 230);
        $(".right-content-iframe").css("padding-left", 235);
    });

});


/********************************实现菜单的功能**********************************************/
    //将菜单封装成一个对象，方便以后的使用
var Menu = function (options) {
        //保存已经打开过的菜单项
        this.cachedOpen = {};
        //一级菜单  二级菜单不需要，因为我们可以通过一级菜单找到相应的二级菜单
        this.nav = options.nav || $(".nav");
        //右侧的tab标签
        this.tab = options.tab || $(".right-content-tab");
        //右侧的iframe
        this.content = options.content || $(".right-content-iframe");
        //默认打开的菜单项
        this.defaultSelected = options.defaultSelected || $("li[data-id='1']");

        this._bindEvent();

        //第一次进来的时候，加载默认菜单
        //加载指定的页面
        this._select(this.defaultSelected);

        //用来保存浏览器上一次的宽度
        this.winWidth = $(window).width();
    };


//通过原型对象，绑定相应的事件和操作
Menu.prototype._bindEvent = function () {
    var self = this;

    //点击一级菜单，显示二级菜单,并且只显示一个
    this.nav.on("click","li.nav-li",function () {

        $(this).addClass("nav-current").siblings().removeClass("nav-current");
    });

    //点击二级菜单，显示相应的tab标签
    this.nav.on("click",".sub-nav li",function () {
        self._select($(this));
    });
    //点击tab标签，显示相应的二级菜单
    this.tab.on("click","li",function () {
        self._show($(this));
        return false;
    });
    //点击tab标签上的关闭图标，关闭当前tab标签
    this.tab.on("click","i.fa-times",function () {
        //关闭当前tab标签的时候，上一个tab标签应该被选中
        self._close($(this).parents("li"));
        return false;
    });
    //关闭所有的tab标签
    $("i.fa-times-circle").click(function () {
        self._closeAll();
    });
    //点击更多图标，显示下拉列表
    $("i.fa-caret-square-down").click(function () {
        if($(".tab-more").is(":hidden")){
            $(".tab-more").css("left",$(this).offset().left - $(".tab-more").width()/2).show();
        }else{
            $(".tab-more").hide();
        }
    });
    //点击更多菜单中的关闭图标
    $(".tab-more").on("click","i.fa-times",function () {
       self._close($(this).parents("li"));

       if(!$(".tab-more").find("li").length){
           $("i.fa-caret-square-down").hide();
       }

       $(".tab-more").hide();
       return false;
    });

    //点击更多菜单中选项的时候，与tab标签中最后一个进行切换
    $(".tab-more").on("click","li",function () {
       //思路：从更多菜单中移到tab项当中
        self._moveToTab($(this).attr("data-id"));

        $(".tab-more").hide();

        return false;
    });

    //用来监听用户浏览器是否变化
    $(window).resize(function () {
        //调整后，判断是否需要显示更多菜单
        var showMoreTab = self._checkTabTotalWidth();
        //调整后浏览器的宽度的差值
        var dValue = self.winWidth - $(window).width();

        $(".tab-more").hide();

        if(showMoreTab > 0 && dValue > 0){ //变窄，并且需要显示更多的菜单
            var $lastTab = self.tab.find("li").last();
            var lastTabId = $lastTab.attr("data-id");

            self._moveToMoreTab(lastTabId);

            $(window).resize();
        }else{ //变宽
            //将更多中的菜单移到tab项当中
            var $lastMore = $(".tab-more").find("li").last();
            var lastMoreId = $lastMore.attr("data-id");


            if(showMoreTab < $lastMore.width() && dValue < 0 && $lastMore.length == 1){

                self._moveToTab(lastMoreId,true);

                $(window).resize();
            }

            self.tab.find("li").removeClass("tab-current");
            self.tab.find("li[data-id='"+$("iframe").not(":hidden").attr("data-id")+"']").addClass("tab-current");
        }

        //每次更新宽度
        self.winWidth = $(window).width();

        if(!$(".tab-more").find("li").length){
            $("i.fa-caret-square-down").hide();
        }else {
            $("i.fa-caret-square-down").show();
        }
    });
};

/**
 * 加载指定的页面，判断是否显示更多图标
 * @param $this 页面的标签
 * @private
 */
Menu.prototype._select = function ($this) {
    //分析：首先我们要得到点击二级菜单的data-id，判断是否已经打开过，如果已经打开过，则直接显示，否则重新打开
    var dataId = $this.attr("data-id");
    //判断是否已经打开过
    if(this.cachedOpen[dataId]){
        //如果已经打开过，则直接显示相应的tab标签即可
        this._show($this);
    }else{
        //如果没有打开过，则重新加载
        this._createTab($this);
    }

    //检查宽度，判断是否需要显示更多图标
    this._checkWidth();
};

/**
 * 显示相应的tab页
 * @param $this
 * @private
 */
Menu.prototype._show = function ($this) {
    //分析：如果已经打开过，则在tab对应的ul中是可以找到的
    var dataId = $this.attr("data-id");

    $tabli = this.tab.find("li[data-id='"+dataId+"']");

    //将当前的nav/tab中的li设置为选中的状态，同时取消其它li选中的状态
    //移除兄弟元素的选中状态
    this.nav.find("li").removeClass("subnav-current");
    //除了给二级菜单添加选中状态之外，我还需要给一级菜单添加选中
    this.nav.find("li[data-id='"+dataId+"']").parents(".nav-li").siblings().removeClass("nav-current").end().addClass("nav-current").end().addClass("subnav-current");
    this.tab.find("li").removeClass("tab-current");

    //在tab项中能够找到此tab
    if(!!$tabli.length){
        $tabli.addClass("tab-current");
    }else {
        //可能在更多当中
        if(this._isInMorePanel($this)){
            this._moveToTab(dataId);

            this.tab.find("li[data-id='"+dataId+"']").addClass("tab-current");
        }
    }

    //内容没有更新
    this.content.find("iframe").hide();

    this.content.find("iframe[data-id='"+dataId+"']").show();
};

/**
 * 加载相应的tab页
 * @param $this
 * @private
 */
Menu.prototype._createTab = function ($this) {
    //分析：1、得到data-id，加入到cachedOpen当中；
    //     2、如果点击的是默认的tab页，则加载默认的tab页；
    //     3、如果点击的是其它的tab页，则加载有关闭按钮的tab页；
    //     4、在iframe中加载相应的页面；
    //     5、添加到相应的tab和content当中
    var dataId = $this.attr("data-id");
    var subMenuText = $this.text();
    var dataHref = $this.attr("data-href");

    //2、如果点击的是默认的tab页，则加载默认的tab页；
    //3、如果点击的是其它的tab页，则加载有关闭按钮的tab页；
    if($this.is(this.defaultSelected)){
        var $tabItem = $("<li data-id='"+dataId+"' data-default='default'><a href='javascript:;'><span>"+subMenuText+"</span></a></li>");
    }else{
        var $tabItem = $("<li data-id='"+dataId+"'> <a href='javascript:;'><span>"+subMenuText+"</span><i class='fas fa-times'></i></a></li>")
    }

    //4、在iframe中加载相应的页面；
    var $iframe = $("<iframe data-id='"+dataId+"' width='100%' height='100%' frameborder='0' src='"+dataHref+"'></iframe>")

    //1、得到data-id，加入到cachedOpen当中；
    this.cachedOpen[dataId] = {
        nav:$this,
        tab: $tabItem,
        iframe:$iframe
    };

    //5、添加到相应的tab和content当中
    this.tab.append($tabItem);
    this.content.append($iframe);

    //显示当前tab
    this._show($this);
};

//关闭tab标签
Menu.prototype._close = function ($this) {
    //分析：不管你关闭的时不是当前窗口，我们总需要做一件事：把tab和iframe移除；
    //如果关闭的是当前标签，则显示上一个标签页
    var dataId = $this.attr("data-id");
    var current = this.cachedOpen[dataId];
    var $preTab = this.tab.find("li").eq($this.index() - 1);

    current.tab.remove();
    current.iframe.remove();

    //从更多中移除
    current.more && current.more.remove();

    delete this.cachedOpen[dataId];


    if(current && current.nav.hasClass("subnav-current")){
        this._show($preTab);
    }

    this._checkWidth();
};

//关闭所有的tab标签
Menu.prototype._closeAll = function () {
    var self = this;
    //关闭所有的tab标签
    $.each(this.tab.find("li"),function () {
       if(!$(this).attr("data-default")){
           //如果不是默认的tab标签，则直接关闭
           self._close($(this));
       }else{
           self._select($(this));
       }
    });

    //关闭更多中的下拉菜单
    $.each($(".tab-more").find("li"),function () {
       self._close($(this));
    });
};

//检查宽度，判断是否显示更多图标
Menu.prototype._checkWidth = function () {
    //分析：总是把倒数第二个加到更多的下拉列表中
    var toMoreTab = this.tab.find("li").eq(this.tab.find("li").length - 2);
    var toMoreTabId = toMoreTab.attr("data-id");
    var $tabMore = this.tab.next("i.fa-caret-square-down");

    //判断：是否需要将tab添加到更多的下拉列表当中，当tab的总宽度超过可显示的宽度的时候，则显示更多菜单图标，并且将倒数第二个tab项添加到更多的下拉列表当中
    //否则，隐藏更多菜单的图标与下拉列表
    if(this._checkTabTotalWidth()>0){
        //显示更多菜单
        $tabMore.show();
        //同时，将相应的tab添加到更多的下拉列表当中
        this._moveToMoreTab(toMoreTabId);
    }else{
        $tabMore.hide();
    }

    //判断如果更多菜单中有菜单项，更多菜单隐藏，否则显示
    if(!$(".tab-more").find("li").length){
        $(".tab-more").hide();
    }else{
        $tabMore.show();
    }
};

/**
 * tab的总宽度-可显示宽度>0 说明需要显示更多的菜单图标
 * @private
 */
Menu.prototype._checkTabTotalWidth = function () {
    //可显示宽度
    var availableWidth = $(".right-content-header").width() - 280;
    //tab的总宽度
    var tabTotalWidth = 0;

    $.each(this.tab.find("li"),function () {
        tabTotalWidth += $(this).outerWidth();
    });

    return tabTotalWidth - availableWidth;
};

/**
 * 将指定的tab项添加至更多的下拉列表当中
 * @param toMoreTabId
 * @private
 */
Menu.prototype._moveToMoreTab = function (toMoreTabId) {
    if(!toMoreTabId){
        return false;
    }
    //分析：将倒数第二个移到更多当中
    var target = this.cachedOpen[toMoreTabId];
    var targetText = target.tab.text();

    //从tab标签中脱离出来
    target.tab.detach();

    var $moreTab = $("<li data-id='"+toMoreTabId+"'><a href='javascript:;'><span>"+targetText+"</span></a><i class='fas fa-times'></i></li>");

    this.cachedOpen[toMoreTabId].more = $moreTab;

    $(".tab-more").append($moreTab);
};

/**
 * 从更多菜单中移到tab项当中
 * @param toTabId
 * @private
 */
Menu.prototype._moveToTab = function (toTabId,flag) {
    //分析：找到tab项中最后一个的id，然后进行切换
    if(!toTabId){
        return false;
    }

    var toMoreTabId = this.tab.find("li").eq(this.tab.find("li").length - 1).attr("data-id");
    var tabLi = this.cachedOpen[toTabId].tab;
    var moreLi = this.cachedOpen[toTabId].more;


    this.tab.append(tabLi);
    moreLi.detach();

    //将最后一个移到更多当中
    if(!flag){
        this._moveToMoreTab(toMoreTabId);
        this._select(tabLi);
    }
};

/**
 * 判断是否在更多当中
 * @param $this
 * @private
 */
Menu.prototype._isInMorePanel = function ($this) {
    //分析：判断是否打开过，如果打开过，那么不在tab当中，就会在更多当中
    var dataId = $this.attr("data-id");
    var opened = this.cachedOpen[dataId];

    if(!opened){
        return false;
    }

    if(!!$(".tab-more").find("li[data-id='"+dataId+"']")){
        return true;
    }
};