/**
 * Created by wjy on 16/05/2017.
 */


$(document).ready(function () {


    $.getJSON("user/info", {
        id: localStorage.userId,
        token: localStorage.userToken
    }, function (data) {
        if (data.status === 'success') {
            var user = data.data;
            $("#img").attr('src', user.protrait || $("#img").attr('src'));
            $("#username").find("input").val(user.loginName);
            $("#nickname").find("input").val(user.nickName);
            $("#phone").find("input").val(user.phone);
            $("#money").find("input").val(user.remaining);

        }
    });


    $('#logout').click(function () {
        localStorage.clear();
        window.location.href = "login.html";
    });
    $("#password").find("input").click(function () {
        window.location.href = 'changePwd.htm'
    });


    $('#charge_action').click(function () {
        var charge = $('#charge_val').val();

        if (!charge) {
            return;
        }

        $.post('user/recharge', {
            id: localStorage.userId,
            token: localStorage.userToken,
            charge: charge
        }, function (data) {
            if (data.status === 'success') {
                alert('更改成功');
            } else {
                alert('更改失败');
            }
        });
    });


    $('#img_uplaod').on('submit', (function (e) {
        e.preventDefault();
        var formData = new FormData(this);

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log("success");
                console.log(data);
                if (data.status === 'success') {
                    $("#img").attr('src', '../avator/' + data.data || $("#img").attr('src'));
                }

            },
            error: function (data) {
                console.log("error");
                console.log(data);
            }
        });
    }));

    $("#img_select").on("change", function () {
        $("#img_uplaod").submit();
    });


    $("#save_info").find("input").click(function () {
        var img = $("#img").attr('src');
        var nickName = $("#nickname").find("input").val();
        var phone = $("#phone").find("input").val();


        $.post('user/modify',
            {
                id: localStorage.userId,
                token: localStorage.userToken,
                avator: img,
                nick: nickName,
                phone: phone
            }
        )
            .success(function (data) {
                if (data.status === 'success') {
                    localStorage.userNick = nickName;
                    localStorage.userImg = img;
                    alert("修改成功");
                } else {
                    alert("修改失败");
                }


            })
            .fail(function () {
                alert("修改失败");
            })
    });

});