$(document).ready(function () {

    $("#wechat").mouseenter(function () {

        $("#pic1").css('display', 'block');

        $("#pic1").css('color', '#ffffff');

    });

    $("#wechat").mouseleave(function () {

        $("#pic1").css('display', 'none');


    });


    $("#mail").mouseenter(function () {

        $("#pic2").css('display', 'block');
        $(".a1").css('color', '#ffffff');


    });

    $("#mail").mouseleave(function () {

        $("#pic2").css('display', 'none');
        $(".a1").css('color', ' #868686');


    });

    $(".year_span2").mouseenter(function () {

        $(".year_span6").css('display', 'block');


    });

    $(".year_span2").mouseleave(function () {

        $(".year_span6").css('display', 'none');


    });
    $(".year_span3").mouseenter(function () {

        $(".year_span7").css('display', 'block');


    });

    $(".year_span3").mouseleave(function () {

        $(".year_span7").css('display', 'none');


    });

    $(".year_span4").mouseenter(function () {

        $(".year_span8").css('display', 'block');


    });

    $(".year_span4").mouseleave(function () {

        $(".year_span8").css('display', 'none');


    });


    $(".others").mouseenter(function () {

        $(".current").css('border', 'none');


    });

    $(".hv").mouseleave(function () {

        $(".current").css('border-bottom', '2px solid #33d6f4');


    });


});