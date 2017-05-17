/**
 * Created by rqg on 15/05/2017.
 */
$(document).ready(function () {
    $("#buy").click(function () {

        if (!localStorage.userId) {
            alert("请先登陆");

        }

        $.post("course/buy", {
            courseId: classId,
            userId: localStorage.userId,
            token: localStorage.userToken
        })
            .success(function (data) {
                if (data.status === "success") {
                    window.location.href = 'buySuccess.html?id=' + classId;
                } else {
                    alert("购买失败");
                }
            })
            .fail(function () {
                alert("购买失败");
            });
    });


    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        return results[1] || 0;
    };


    classId = $.urlParam("id");

    $.getJSON("course/info", {
            id: classId
        },
        function (data) {
            $("#class_name").val(data.data.name);

            $("#class_img").attr("src", data.data.image);
            $('#price').val(data.data.price)

        });
});