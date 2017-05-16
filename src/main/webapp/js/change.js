/**
 * Created by wjy on 08/05/2017.
 */


$(document).ready(function () {
    $("#next").click(function () {
        var pwd = $("#password").val();
        var pwdC = $("#passwordConfirm").val();

        if (pwd !== pwdC) {
            showError("两次密码不一致");
            return;
        }

        function changePwd() {
            $.post('user/password', {
                id: localStorage.userId,
                token: localStorage.userToken,
                pwd: pwd
            })
                .success(function (data) {
                    if (data.status === 'success') {

                        window.location.href = "login.html";
                    } else {
                        showError("修改密码失败");
                    }

                }).fail(function () {
                showError("修改密码失败");

            });
        }

        changePwd();
    });


    function showError(msg) {
        $(".flash-error").text(msg).show("slow");
    }


});