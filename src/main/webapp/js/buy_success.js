/**
 * Created by rqg on 17/05/2017.
 */


$(document).ready(function () {

    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        return results[1] || 0;
    };


    classId = $.urlParam("id");

    window.setInterval(function () {
        window.location.href = "classroom.html?id=" + classId;
    }, 5000);
});