window.onload = function () {

    toBanner();
    // foot_scoll();
};


function toBanner() {
    var oDiv = document.getElementById('box');
    var oUl1 = document.getElementById('ul1');
    var aLi1 = oUl1.getElementsByTagName("li");
    var oUl2 = document.getElementById('ul2');
    var aLi2 = oUl2.getElementsByTagName("li");
    var oPrev = getByClass(oDiv, 'prev')[0];
    var oNext = getByClass(oDiv, 'next')[0];
    var oPrevBg = getByClass(oDiv, 'prev_bg')[0];
    var oNextBg = getByClass(oDiv, 'next_bg')[0];
    var iNow = 0;
    var iPlayer = null;

    autoPlay();

    oPrevBg.onmouseover = oPrev.onmouseover = function () {
        oPrev.style.display = 'block';
        clearInterval(iPlayer);

    };
    oPrevBg.onmouseout = function () {
        oPrev.style.display = 'block';
        autoPlay();
    };
    oNextBg.onmouseover = oNext.onmouseover = function () {
        oNext.style.display = 'block';
        clearInterval(iPlayer);

    };
    oNextBg.onmouseout = function () {
        oNext.style.display = 'block';
        autoPlay();
    };

    oPrev.onclick = function () {
        goPrev();
    };
    oNext.onclick = function () {
        goNext();
    };
    function autoPlay() {
        iPlayer = setInterval(goNext, 3000);
    }


    function goNext() {
        if (iNow == aLi1.length - 1) {
            iNow = 0;
        }
        else {
            iNow++;
        }

        for (var i = 0; i < aLi1.length; i++) {
            fadeOut(aLi1[i]);
            aLi2[i].className = "";
        }
        fadeIn(aLi1[iNow]);
        aLi2[iNow].className = "current";

    }

    function goPrev() {
        if (iNow == 0) {
            iNow = aLi1.length - 1;
        }
        else {
            iNow--;
        }

        for (var i = 0; i < aLi1.length; i++) {
            fadeOut(aLi1[i]);
            aLi2[i].className = "";
        }

        fadeIn(aLi1[iNow]);
        aLi2[iNow].className = "current";
    }


}

// 页脚无缝滚动
function foot_scoll() {
    var oCf = document.getElementById('display1');

    var ocPrev = getByClass(oCf, 'cn_prev')[0];
    // 数组， 我们没有加下标

    var ocNext = getByClass(oCf, 'cn_next')[0];
    var oUl = oCf.getElementsByTagName('ul')[1];
    var aLi = oUl.getElementsByTagName('li');
    //alert(aLi.length);
    var iNow = 0;
    oUl.innerHTML += oUl.innerHTML;
    oUl.style.width = aLi.length * aLi[0].offsetWidth + 'px';
    ocPrev.onclick = function () {
        if (iNow == 0) {
            iNow = aLi.length / 2;
            oUl.style.left = -oUl.offsetWidth / 2 + 'px';
        }
        moveAll(oUl, -iNow * aLi[0].offsetWidth, -(iNow - 1) * aLi[0].offsetWidth);
        iNow--;
        return false;
    };

    ocNext.onclick = function () {
        if (iNow == aLi.length / 2) {
            iNow = 0;
            oUl.style.left = 0;
        }
        moveAll(oUl, -iNow * aLi[0].offsetWidth, -(iNow + 1) * aLi[0].offsetWidth);
        iNow++;
        return false;
    };
}

function moveAll(el, old, iTarget) {
    // 防止，你上一个动没有结束，又执行下一个动作
    clearInterval(el.timer);
    el.timer = setInterval(function () {

        var iSpeed = (iTarget - old) / 10;
        iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);

        if (iTarget == old) {
            clearInterval(el.timer);
        }
        else {
            old += iSpeed;
            el.style.left = old + 'px';
        }

    }, 30);

}