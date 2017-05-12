/**
 * Created by rqg on 10/05/2017.
 */
$(document).ready(function () {

    function showCourse(course, list) {

        var name = course.name;
        var price = course.price;
        var img = course.image;
        var courseId = course.id;

        var html = '<li class="uc-course-list_itm f-ib"> \
                        <div class="uc-coursecard uc-ykt-coursecard f-fl"> \
                        <a target="_blank" class="j-href" \
                    href="classroom.html?id=' +
            courseId
            + '"> \
                        <div class="uc-ykt-coursecard-wrap f-cb f-pr"> \
                        <div class="uc-ykt-coursecard-wrap_box"> \
                        <div class="uc-ykt-coursecard-wrap_picbox f-pr"> \
                        <img class="imgPic j-img" src="../' +
            img
            + '" \
                    data-src="" alt="课程图片" id="auto-id-1494418196754"> \
                        </div> \
                        <div class="uc-ykt-coursecard-wrap_tit"> \
                        <h3 class="">' +
            name
            + '</h3> \
                    </div> \
                    <div class="uc-ykt-coursecard-wrap_orgName f-fs0 f-thide"> \
                         \
                        </div> \
                        <div class="uc-ykt-coursecard-wrap_scorewrap f-thide f-cb f-pa"> \
                        <div class="m-scorecnt f-fl f-cb"> \
                        <div class="uc-starrating"> \
                        <div class="uc-starrating_wrap f-cb f-ib"> \
                        <span class="m-star z-on"></span> \
                        <span class="m-star z-on"></span> \
                        <span class="m-star z-on"></span> \
                        <span class="m-star z-on"></span> \
                        <span class="m-star z-on"></span> \
                        </div> \
                        <span class="uc-starrating_score">' +
            (Math.random() + 4).toFixed(1)
            + '</span> \
                        </div> \
                        </div> \
                        <div class="m-hot f-fl"> \
                        <!--Regular if11--> \
                    (' +
            Math.floor(Math.random() * 1000)
            + '人学过) \
                    </div> \
                    </div> \
                    <div class="uc-ykt-coursecard-wrap_price f-pa"> \
                        <!--Regular if12--><!--Regular if13--> \
                    <span class="u-discount">¥ ' +
            price
            + '</span> \
                    <span class="u-normal z-discount">¥ ' +
            Math.floor(price * 1.2)
            + '</span> \
                    </div> \
                    </div> \
                    </div> \
                    </a> \
                    </div> \
                    </li>';

        list.append(html);

    }


    $.getJSON('course/all', {}, function (data) {
        var cList = data.data;
        var list = $(".uc-course-list_ul");

        for (var i = 0; i < cList.length; i++) {
            var c = cList[i];
            showCourse(c, list);
        }
    })


});