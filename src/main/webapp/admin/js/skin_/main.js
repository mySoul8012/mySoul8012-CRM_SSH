$(function () {
    //条目的宽度
    var itemWidth = 614;

    resizeItemWidth();

    //监听事件，当用户调整浏览器大小的时候，调用resizeItemWidth()方法
    $(window).resize(function () {
        resizeItemWidth();
    });

    //页面加载完成之后，画图表
    addVisitedChart();

    addClientPercent();

    /**
     * 添加用户访问量的图表
     */
    function addVisitedChart() {
        //找到画布，并准备画笔
        var ctx = document.getElementById("visitedCount").getContext("2d");

        var chart = new Chart(ctx,{
            type: "line",
            data: {
                labels: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
                datasets: [{
                    label: "会员访问量统计",
                    backgroundColor: "#FF0000",
                    borderColor: "#FF0000",
                    fill: false,
                    data: [500,300,350,400,420,520,500,600,650,550,580,700]
                },{
                    label: "总访问量统计",
                    backgroundColor: "#0000FF",
                    borderColor: "#0000FF",
                    fill: false,
                    data: [2000,1800,2100,2400,3000,3500,3200,3600,4000,3400,3600,4000]
                }]
            },
            options: {}
        });
    }

    /**
     * 添加浏览器占比的统计图表
     */
    function addClientPercent() {
        var ctx = document.getElementById("clientPercent").getContext("2d");

        var chart = new Chart(ctx,{
           type: "pie",
           data: {
               labels: ["谷歌浏览器","IE浏览器","火狐浏览器","360浏览器","其它浏览器"],
               datasets: [{
                   label: "浏览器访问统计",
                   backgroundColor: ["#ff0000","#00ff00","#0000ff","#0f0f0f","#f0f0f0"],
                   data: [60, 10, 20, 6, 4]
               }]
           },
            options:{}
        });
    }

    /**
     * 根据浏览器的宽度调整条目的宽度
     */
    function resizeItemWidth() {
        var mainWidth = $("#main-content").width();

        if(mainWidth < itemWidth){
            $(".content-item").width(itemWidth-20);
        }else if(mainWidth < itemWidth * 2){
            $(".content-item").width(mainWidth - 20);
        }else if(mainWidth < itemWidth * 3){
            $(".content-item").width(mainWidth/2 - 20);
        }else{
            $(".content-item").width(mainWidth/3 - 20);
        }
    }
});