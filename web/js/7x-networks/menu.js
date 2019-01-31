var myConfirm = function (content, doneFn, failFn) {
    $(".ht_tk_zg").show();
    $("body").append($('<div id="confirm_dialog" class="ht_r_div1 ht_tk_dw ">\n' +
        '\t<ul>\n' +
        '\t\t<li class="ht_r_bt font_18 color_2 pa_dd text_center">警告</li>\n' +
        '\t\t<li class="pa_dd text_center">\n' +
        '\t\t\t<p><span class="ztcolor_h">' + content + '</span></p>\n' +
        '\t\t</li>\n' +
        '\t\t<li class="ht_r_aniu text_center">\n' +
        '\t\t\t<button id="confirm_ok" class="k-button k-primary mar_gin" id="get">确定</button>\n' +
        '\t\t\t<button id="confirm_cancel" class="k-button mar_gin">取消</button>\n' +
        '\t\t</li>\n' +
        '\t</ul>\n' +
        '</div>'));

    $("#confirm_ok").on("click", function () {
        $(".ht_tk_zg").hide();
        $("#confirm_dialog").remove()
        if (typeof doneFn === "function") {
            doneFn.call(this);
        }
    });

    $("#confirm_cancel").on("click", function () {
        $(".ht_tk_zg").hide();
        $("#confirm_dialog").remove()
        if (typeof failFn === "function") {
            failFn.call(this);
        }
    });
}