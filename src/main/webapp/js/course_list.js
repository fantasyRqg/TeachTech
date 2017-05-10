/**
 * Created by rqg on 10/05/2017.
 */
$(document).ready(function () {


    var html = '<li class="uc-course-list_itm f-ib"> \
        <div class="uc-coursecard uc-ykt-coursecard f-fl"> \
        <a target="_blank" class="j-href" \
    href="http://study.163.com/course/introduction/1003164006.htm"> \
        <div class="uc-ykt-coursecard-wrap f-cb f-pr"> \
        <div class="uc-ykt-coursecard-wrap_box"> \
        <div class="uc-ykt-coursecard-wrap_picbox f-pr"> \
        <img class="imgPic j-img" src="../image/c_image_1.jpg" \
    data-src="" alt="课程图片" id="auto-id-1494418196754"> \
        </div> \
        <div class="uc-ykt-coursecard-wrap_tit"> \
        <h3 class="">Unity5.x 创造 3D VR游戏</h3> \
    </div> \
    <div class="uc-ykt-coursecard-wrap_orgName f-fs0 f-thide"> \
        InsideRIA \
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
        <span class="uc-starrating_score">4.8</span> \
        </div> \
        </div> \
        <div class="m-hot f-fl"> \
        <!--Regular if11--> \
    (651人学过) \
    </div> \
    </div> \
    <div class="uc-ykt-coursecard-wrap_price f-pa"> \
        <!--Regular if12--><!--Regular if13--> \
    <span class="u-discount">¥ 268.00</span> \
    <span class="u-normal z-discount">¥ 498.00</span> \
    </div> \
    </div> \
    </div> \
    </a> \
    </div> \
    </li>';


    var list = $(".uc-course-list_ul");

    for (var i = 0; i < 10; i++) {
        list.append(html);
    }

});