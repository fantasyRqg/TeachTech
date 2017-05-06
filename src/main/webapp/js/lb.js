//for header lb
var oHbanner = document.getElementById("header_banner");
var oUl = oHbanner.getElementsByTagName("ul");
var oImgs = oUl[0].getElementsByTagName("li");
var oNum = oUl[1].getElementsByTagName("li");
var index = 0;
var timer = play = null;


//切换按钮
for (var i = 0; i < oNum.length; i++) {
    oNum[i].index = i;
    oNum[i].onclick = function () {
        show(this.index)
    }

}

//show(this.index)
//调用show


//鼠标划过大Box关闭定时器

oHbanner.onmouseover = function () {
    clearInterval(play)
};

//鼠标离开大Box启动自动播放

oHbanner.onmouseout = function () {
    autoPlay()
};
//自动播放函数 autoplay


//调用来 show

//autoPlay();//应用

function autoPlay() {
    clearInterval(play);
    play = setInterval(function () {
        index++;
        index >= oImgs.length && (index = 0);
        show(index)
    }, 6000)

}

autoPlay();
//show()图片切换, 淡入淡出效果 核心代码

function show(a) {
    //alert(a);
    index = a;
    for (var i = 0; i < oNum.length; i++)
        oNum[i].className = "";
    oNum[index].className = " current ";

    for (var i = 0; i < oNum.length; i++) {
        oImgs[i].style.opacity = "0";
        oImgs[i].style.filter = "alpha(opacity=" + 0 + ")";
    }
    clearInterval(timer);
    var alpha = 0;
    timer = setInterval(function () {
        alpha += 2;
        alpha > 100 && (alpha = 100);
        oImgs[index].style.opacity = alpha / 100;
        oImgs[index].style.filter = "alpha(opacity=" + alpha + ")";
        alpha == 100 && clearInterval(timer)
    }, 50)
}


// 图片全部变成为透明







