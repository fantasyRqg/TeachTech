/**
 * Created by rqg on 23/05/2017.
 */


$(document).ready(function () {
    var item_html = ' <tr>         \
        <td><img id="user_img" src="avator/default.jpg" style="width: 64px; height: 64px;"></td>         \
        <td id="id">1</td>         \
        <td id="login">fad</td>         \
        <td id="pwd">fa</td>         \
            <td id="nick">fa</td>         \
            <td id="phone">adf</td>         \
        <td id="remain">adsf</td>         \
        <td>         \
        <a style="cursor: pointer" id="del">删除</a>         \
        </td>         \
        </tr>';


    function addUserInfo(userTable, user) {
        var item = $(item_html);
        item.hide();


        item.find('#user_img').attr('src', user.protrait || 'avator/default.jpg');
        item.find('#id').text(user.id);
        item.find('#login').text(user.loginName);
        item.find('#pwd').text(user.password);
        item.find('#nick').text(user.nickName);
        item.find('#phone').text(user.phone);
        item.find('#remain').text(user.remaining || 0);
        item.find('#del').click(function () {
            $.getJSON("user/del", {
                id: user.id
            }, function (data) {
                if (data.status === 'success') {
                    item.hide("normal", function () {
                        item.remove();
                    });
                }
            })
        });

        userTable.append(item);

        item.show('normal');
    }

    $.getJSON('user/all', {}, function (data) {
        if (data.status === 'success') {
            var tt = $('.table');
            for (var i = 0; i < data.data.length; i++) {
                addUserInfo(tt, data.data[i]);
            }
        }
    });

});
