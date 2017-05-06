$(document).ready(function () {
    $(".menu").delegate("div[class^='pic']", "mouseenter", function () {
        if ($(this).attr("class") == "pic1") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue2.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }

        if ($(this).attr("class") == "pic2") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji2.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }

        if ($(this).attr("class") == "pic3") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi2.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');

        }


        if ($(this).attr("class") == "pic4") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang2.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }


        if ($(this).attr("class") == "pic5") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong2.png) no-repeat');


        }


        $(".all_info").css('display', 'none');
        $(".all_arrow").css('display', 'none');
        $(".menu_info1").css('display', 'none');
        $(this).next().next().next().css('display', 'block');
        $(this).next().next().css('display', 'block');


    });


    $(".menu").delegate("div[class^='pic']", "mouseleave", function () {


        if ($(this).attr("class") == "pic1") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue2.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }

        if ($(this).attr("class") == "pic2") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji2.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }

        if ($(this).attr("class") == "pic3") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi2.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');

        }


        if ($(this).attr("class") == "pic4") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang2.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong.png) no-repeat');


        }


        if ($(this).attr("class") == "pic5") {
            $(".pic1").css('background', 'url(img/jiaowujiaoxue.png) no-repeat');
            $(".pic2").css('background', 'url(img/caiwutongji.png) no-repeat');
            $(".pic3").css('background', 'url(img/shujufenxi.png) no-repeat');
            $(".pic4").css('background', 'url(img/zhaoshengtuiguang.png) no-repeat');
            $(".pic5").css('background', 'url(img/jiaxiaohutong2.png) no-repeat');


        }


        $("this").css('display', 'block');
        $("this").css('display', 'block');

    });


});


    
