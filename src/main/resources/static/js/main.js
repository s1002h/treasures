$(function () {
    /*屏幕字体大小适配*/
    var windowwidth = $(window).width();
    var fontsize = windowwidth / 320 * 10;
    if (fontsize >= 20) {
        fontsize = 20;
    };
    $('html').css('font-size', fontsize);

    // $('body,html').height(document.body.clientHeight);
});