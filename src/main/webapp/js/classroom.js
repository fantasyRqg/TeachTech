/**
 * Created by wjy on 11/05/2017.
 */
$(document).ready(function () {
    function showComment(c, parent) {

        var userName = c.userName;
        var userId = c.userId;
        var createTime = c.createTime;
        var content = c.content;
        var userImage = c.userImage || 'avator/default.jpg';

        var commentItem = '        <div class="timeline-comment-wrapper js-comment-container">  \
                                    <div class="avatar-parent-child timeline-comment-avatar">   \
                                            <a href="#">    \
                                            <img alt="" class="avatar rounded-1" height="44"   \
                                        src="' +
            userImage
            + '" width="44">    \
                                            </a>    \
                                            </div>  \
                                            <div id="" class="comment previewable-edit timeline-comment js-comment js-task-list-container    \
                                        js-reorderable-task-lists reorderable-task-lists">  \
                                        <div class="timeline-comment-header">   \
                                            <div class="timeline-comment-header-text">  \
                                            <strong>    \
                                            <a href="/Caij" class="author">' +
            userName
            + '</a> \
                                            </strong>   \
                                            commented at  \
                                            <a href="#issue-227924972" class="timestamp">   \
                                            <relative-time datetime="2017-05-11T09:07:01Z" title="May 11, 2017, 5:07 PM GMT+8"> '
            +
            new Date(createTime).toDateString()
            + ' </relative-time>    \
                                        </a>    \
                                        </div>  \
                                        </div>  \
                                        <div class="edit-comment-hide"> \
                                            <table class="d-block"> \
                                            <tbody class="d-block"> \
                                            <tr class="d-block">    \
                                            <td class="d-block comment-body markdown-body  js-comment-body">'
            +
            content.replace(/\n/g, "<br>")
            + '</td>   \
                                        </tr>   \
                                        </tbody>    \
                                        </table>    \
                                        <div class="comment-reactions  js-reactions-container js-socket-channel js-updatable-content"   \
                                        data-channel="reaction:issue:227924972" \
                                        data-url="/_render_node/MDU6SXNzdWUyMjc5MjQ5NzI=/comments/reactions">   \
                                            </div>  \
                                            </div>  \
                                            </div>  \
                                            </div>   ';

        var item = $(commentItem);
        item.hide();
        parent.prepend(item);

        item.show('normal');
    }


    $("#ipt").click(function () {
        if (!localStorage.userId) {
            alert("请先登陆");
            return;
        }


        var c = {};
        c.userName = localStorage.userName;
        c.content = $("#text").val();
        c.createTime = new Date().getMilliseconds();
        c.userId = localStorage.userId;
        c.courseId = classId;
        c.token = localStorage.userToken;

        if (!c.content || c.content.trim() === "") {
            return;
        }


        $.post("comment/add", {
            userId: c.userId,
            token: c.token,
            courseId: c.courseId,
            comment: c.content
        }, function (data) {
            if (data.status === "success") {
                showComment(c, $('#comment'));
                $('#text').val("");
            }
        })
    });

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
                    // alert("购买成功");
                    $("#buy").hide("normal");
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
            $("#class_name").text(data.data.name);
        });

    $.getJSON("comment/course", {
        courseId: classId
    }, function (data) {
        if (data.status === "success") {
            var commentList = data.data;
            var cl = $('#comment');
            for (var i = 0; i < commentList.length; i++) {
                showComment(commentList[i], cl);
            }
        }
    });


    if (localStorage.userId) {
        $.getJSON("course/hasbuy", {
                userId: localStorage.userId,
                courseId: classId
            },
            function (data) {
                if (data.data === true) {
                    $("#buy").hide();
                }
            });
    }
});