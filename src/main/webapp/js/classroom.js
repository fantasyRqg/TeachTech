/**
 * Created by wjy on 11/05/2017.
 */
$(document).ready(function () {
    function showComment(c, parent) {

        var userName = c.userName;
        var userId = c.userId;
        var createTime = c.createTime || c.timestamp;
        var content = c.content;
        var userImage = c.userImage || 'avator/default.jpg';

        var commentId = c.id;

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
                                        <a id="del" style="float: right;margin-top: 8px; cursor: pointer;">删除</a>\
                                                    <div class="timeline-comment-header-text">  \
                                            <strong>    \
                                            <a href="/Caij" class="author">' +
            userName
            + '</a> \
                                            </strong>   \
                                            commented at  \
                                            <a href="#issue-227924972" class="timestamp">   \
                                            <relative-time datetime="2017-05-11T09:07:01Z" title="May 11, 2017, 5:07 PM GMT+8"> \
                                              \
                                            '
            +
            new Date(createTime).toDateString()
            + ' </relative-time>    \
                                        </a>    \
                                        </div>\
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
        item.find("#del").click(function () {
            $.getJSON('comment/del', {
                commentId: commentId
            })
                .success(function (data) {
                    if (data.status === 'success') {
                        item.hide("normal", function () {
                            item.remove();
                        });
                    }
                })
        });

        if (userId !== parseInt(localStorage.userId)
            && userId !== localStorage.userId) {
            item.find("#del").hide();
        }

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
        c.createTime = Date.now();
        c.userId = localStorage.userId;
        c.courseId = classId;
        c.token = localStorage.userToken;
        c.userImage = localStorage.userImg;

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
                c.id = data.data;
                showComment(c, $('#comment'));
                $('#text').val("");
            }
        })
    });

    $("#buy").click(function () {
        if (!localStorage.userId) {
            alert("请先登陆");

        }

        window.location.href = 'buyCourse.html?id=' + classId;
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
                $.getJSON();
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