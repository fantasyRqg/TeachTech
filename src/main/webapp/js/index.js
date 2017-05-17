/**
 * Created by wjy on 07/05/2017.
 */
$(document).ready(function () {
    if (localStorage.userNick) {
        var uinfo = $("#userInfo");
        uinfo.children("a").attr("href", null);
        uinfo.children("a").html('<span class="span3">' + localStorage.userNick + '</span><img src="img/login.png" alt="">');

        uinfo.click(function () {
            // localStorage.clear();
            // window.location.href = "login.html";
            window.location.href = "information.html";
        })
    }
});