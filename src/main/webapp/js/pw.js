$(document).ready(function () {

    var pwd = $("#password");
    pwd.click(function () {

        $(".hide").css('display', 'block');


    });

    pwd.mouseleave(function () {

        $(".hide").css('display', 'none');


    });

    function showLoginError() {
        var msg = "error: ";

        var i;
        for (i = 0; i < arguments.length; i++) {
            msg += arguments[i];
        }

        $("#error_info").text(msg).show("slow");
    }

    $('#login').click(function () {
        var loginName = $('#name');

        $.post("user/login", {
                name: loginName.val(),
                pwd: pwd.val()
            },
            function (data) {
                if (data.status === "success") {
                    localStorage.userNick = data.data.nickName;
                    localStorage.userToken = data.data.token;
                    localStorage.userName = data.data.loginName;
                    localStorage.userId = data.data.id;
                    localStorage.userImg = data.data.protrait;
                    window.location.href = "index.html";
                } else {
                    showLoginError(data.message);
                }
            }
        );
    });


});


















