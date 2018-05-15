/**
 * Created by Administrator on 2017/7/10/010.
 */
$(function() {
    $.cxMenu = function (obj, settings) {
        if (obj.length < 1) {
            return
        };

        settings = $.extend({}, $.cxMenu.defaults, settings);

        obj.on(settings.events, 'a', function (e) {
            //剔除导出时触发的点击事件 start add by xinye
            if($(e.target).prop('tagName')=='SPAN'){
                return false;
            }
            //end
            e.stopPropagation();
            var ali = $(this).closest('li');
            var li = ali.closest('li');

            var liSiblings = li.siblings();

            var childUl = li.children('ul');
            if (childUl.length > 0) {

                if (li.hasClass(settings.selectedClass)) {
                    li.removeClass(settings.selectedClass);
                    li.find('ul').slideUp(settings.speed);
                    li.find('li').removeClass(settings.selectedClass);
                    return false;
                };

                childUl.slideToggle(settings.speed);

                //如果没有子类，则将前面的三角去掉
                var childLi = childUl.children('li');
                childLi.each(function () {
                    if ($(this).children('ul').length == 0) {
                        $(this).find('i').addClass('end');
                        $(this).find('h6').css({ "margin-left": "33px" });
                    }
                })
            }

            if (settings.only) {
                liSiblings.removeClass(settings.selectedClass);
                liSiblings.find('li').removeClass(settings.selectedClass);
                liSiblings.find('ul').slideUp(settings.speed);
            };

            ali.addClass(settings.selectedClass);
            if (ali.children('ul').length == 0) {
                $("#left_nav_ul li a").removeClass('end');
                ali.children('a').addClass('end');
            }
            ali.siblings('li').children('a').removeClass('end');
        });
        $("#left_nav_ul li a i").each(function(){
            var count = $(this).attr("count");
            if(count=="1"){
                $(this).css("margin-left",count*20+"px");
            }else{
                $(this).css("margin-left",20+(count-1)*20+"px");
            }
        })
    };

    // 默认值
    $.cxMenu.defaults = {
        events: 'click', // 按钮事件
        selectedClass: 'selected', // 展开时增加的 Class
        speed: 500, // 切换速度
        only: true // 同时只展开一个导航
    };

    $.fn.cxMenu = function (settings) {
        if (this.length === 1) {
            $.cxMenu(this, settings);
        } else if (this.length > 1) {
            this.each(function (i) {
                $.cxMenu($(this), settings);
            });
        };
        return this;
    };
})

