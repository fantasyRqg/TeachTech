/**
 * Created by wjy on 07/05/2017.
 */

$(document).ready(function () {
    function getChildrenInputVal(id) {
        return $("#" + id).children("input").val();
    }

    function isInputEmpty(id) {

        return !getChildrenInputVal(id).trim();
    }

    function showLoginError() {
        var msg = "error: ";

        var i;
        for (i = 0; i < arguments.length; i++) {
            msg += arguments[i];
        }

        $("#error_info").text(msg).show("slow");
    }


    function checkInfo() {
        if (isInputEmpty("username")
            || isInputEmpty("nick_name")
            || isInputEmpty("phone")
            || isInputEmpty("password")
            || isInputEmpty("password_confirm")
            || isInputEmpty("verify")
        ) {
            showLoginError("信息填写不完整");
            return null;
        }

        if (getChildrenInputVal("password") !== getChildrenInputVal("password_confirm")) {

            showLoginError("密码不一致");

            return null;
        }


        if (getChildrenInputVal("verify").toUpperCase() !== $('#verify_img').attr('name').toUpperCase()) {
            showLoginError("验证码错误");

            return null;
        }

        return {
            loginName: getChildrenInputVal("username"),
            password: getChildrenInputVal("password"),
            nickName: getChildrenInputVal("nick_name"),
            phone: getChildrenInputVal("phone")
        }
    }


    $('#register').click(function () {
        var info = checkInfo();

        if (info) {

            $.ajax({
                type: "POST",
                url: "/user/register",
                // The key needs to match your method's input parameter (case-sensitive).
                data: JSON.stringify(info),
                processData: false,
                contentType: "application/json",
                dataType: "json"
            })
                .done(function (data) {
                    localStorage.userNick = data.data.nickName;
                    localStorage.userToken = data.data.token;
                    localStorage.userName = data.data.loginName;
                    localStorage.userId = data.data.id;
                    localStorage.userImg = data.data.protrait;

                    window.location.href = "index.html";
                })
                .fail(function (data) {
                    console.log(data.status);
                    showLoginError(data.message);
                });
        }
    });

    function getNewVerfication() {
        $.getJSON('user/verify')
            .success(function (data) {
                if (data.status === 'success') {

                    var vi = $('#verify_img');
                    vi.attr("src", 'vcode/' + data.data.picture);
                    vi.attr('name', data.data.code);
                }

            });
    }


    getNewVerfication();
    $('#new_verify').click(function () {
        getNewVerfication();

    })

});