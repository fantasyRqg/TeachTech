/**
 * Created by wyj on 08/05/2017.
 */


$(document).ready(function () {
    $("#next").click(function () {
        var phoneNum = $("#phone").val();
        var pwd = $("#password").val();
        var pwdC = $("#passwordConfirm").val();

        if (!phoneNum) {
            showError("电话号码不能为空");
            return;
        }


        if (pwd !== pwdC) {
            showError("两次密码不一致");
            return;
        }

        $.getJSON("user/phone/right",
            {
                phone: phoneNum,
                pwd: pwd
            })
            .success(function (data) {
                if (data.status === "success") {
                    window.location.href = "login.html";
                } else {
                    showError("电话号码错误");
                }
            })
            .fail(function () {
                showError("验证失败");
            });
    });


    function showError(msg) {
        $(".flash-error").text(msg).show("slow");
    }
});