$(document).ready(function () {

    $("#wechat").mouseenter(function () {

        $("#li4").css('display', 'block');


    });

    $("#wechat").mouseleave(function () {

        $("#li4").css('display', 'none');


    });


    $("#tel").mouseenter(function () {

        $("#tel_pic").css('display', 'block');


    });

    $("#tel").mouseleave(function () {

        $("#tel_pic").css('display', 'none');


        $(".password").click(function () {

            $(".hide").css('display', 'block');


        });

        $(".password").mouseleave(function () {

            $(".hide").css('display', 'none');


        });


    });


});