$(function () {
    var wow = new WOW({
        offset: 50,
    });
    wow.init();

    var hallSwiper = new Swiper('.swiper-container-teacher', {
        nextButton: '.custom-next',
        prevButton: '.custom-prev',
        slidesPerView: 4,
        slidesPerColumn: 2,
        spaceBetween: 20
    });
});