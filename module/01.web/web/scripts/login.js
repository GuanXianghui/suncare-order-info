/**
 * 加载好页面做的事儿
 */
$(document).ready(function(){
    /**
     * 如果不是chrome浏览器，建议使用chrome浏览器
     */
    var msg="";
    msg+="浏览器名称：" + navigator.appName+"<br>";
    msg+="浏览器版本：" + navigator.appVersion+"<br>";
    msg+="浏览器代码：" + navigator.appCodeName+"<br>";
    if(msg.indexOf("chrome")==-1 && msg.indexOf("Chrome")==-1){
        showInformation("想要更佳的显示效果请使用谷歌Chrome浏览器，从服务器<a href='" + baseUrl + "images/Chrome.exe'>下载</a>");
    }
});

/**
 * 按钮监听
 */
function keyPress(e){
    if( 13 == e.keyCode){
        login();
    }
}

/**
 * 登陆
 */
function login() {
    var name = $("#name").val();
    var password = $("#password").val();
    var validateCode = $("#validate_code").val();
    //判非空
    if (name == EMPTY) {
        showAttention("请输入用户名!");
        return;
    }
    if (password == EMPTY) {
        showAttention("请输入密码!");
        return;
    }
    if(validateCode == EMPTY){
        showAttention("请输入短信验证码!");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    result = checkStr(password, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("密码包含非法字符:" + result["symbol"]);
        return;
    }

    //md5签名
    var md5Pwd = MD5(password + md5Key);

    //ajax登陆
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "ajax/login.jsp",
        data:"name=" + filterStr(name) + "&password=" + md5Pwd + "&validateCode=" + validateCode + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判登陆是否成功
                if (false == data["isSuccess"]) {
                    showAttention(data["message"]);
                    document.getElementById("password").value = EMPTY;
                    //判是否有新token
                    if (data["hasNewToken"]) {
                        token = data["token"];
                    }
                    return;
                } else {
                    //登陆成功
                    showSuccess(data["message"]);
                }
                //是否跳转页面
                if (data["isRedirect"]) {
                    var redirectUrl = data["redirectUrl"];
                    location.href = redirectUrl;
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}

/**
 * 获取短信验证码
 */
function sendValidateCode(){
    var name = $("#name").val();
    var password = $("#password").val();
    //判非空
    if (name == EMPTY) {
        showAttention("请输入用户名!");
        return;
    }
    if (password == EMPTY) {
        showAttention("请输入密码!");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    result = checkStr(password, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("密码包含非法字符:" + result["symbol"]);
        return;
    }

    //md5签名
    var md5Pwd = MD5(password + md5Key);

    //ajax登陆
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "ajax/sendValidateCode.jsp",
        data:"name=" + filterStr(name) + "&password=" + md5Pwd + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判登陆是否成功
                if (false == data["isSuccess"]) {
                    showAttention(data["message"]);
                    document.getElementById("password").value = EMPTY;
                } else {
                    //获取短信验证码成功
                    showSuccess(data["message"]);
                }
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}