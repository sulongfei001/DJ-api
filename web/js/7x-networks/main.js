var setIntervals = new Array();
var clearIntervals = function () {
    // 释放上一张页面的定时任务
    for (var i = 0; i < setIntervals.length; i++) {
        clearInterval(setIntervals[i]);
    }
    setIntervals.splice(0, setIntervals.length);
};

var logout = function () {
    clearCookie();
    clearIntervals();
    location.href = "login.html";
};

var verifyLogin = function () {
    var oauth = $.cookie(OAUTH);
    var accessToken = oauth ? oauth.access_token : "";
    if (!accessToken) {
        alert("跳")
        location.href = "login.html";
    }
};

window.alert = function (message) {
    layer.msg(message);
};
String.prototype.getBytesLength = function () {
    var totalLength = 0;
    var charCode;
    for (var i = 0; i < this.length; i++) {
        charCode = this.charCodeAt(i);
        if (charCode < 0x007f) {
            totalLength++;
        } else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
            totalLength += 2;
        } else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
            totalLength += 3;
        } else {
            totalLength += 4;
        }
    }
    return totalLength;
};

var loading=function (display) {
    if(display==true){
        $(".ht_tk_zg").show();
        $("#ht_progressbar").show();
    }else{
        $(".ht_tk_zg").hide();
        $("#ht_progressbar").hide();
    }
};
var loadPage = function (path) {
    loading(true);
    clearIntervals();
    // load 新页面
    $("#content").load(path, null, function () {
        // 关闭loading框
        loading(false);
    });
};

