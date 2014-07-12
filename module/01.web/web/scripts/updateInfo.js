/**
 * 修改资料
 */
function updateInfo(){
    var password = $("#password").val();
    if(password == EMPTY){
        showAttention("请输入密码");
        return;
    }
    //md5加密
    var md5Pwd = MD5(password + md5Key);
    //手机号码
    var phone = $("#phone").val();
    if(phone == EMPTY){
        showAttention("请输入电话号码");
        return;
    }
    //判电话号码合法性
    if(phone.length != 11 || !isNum(phone)){
        showAttention("电话号码不合法");
        return;
    }
    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "updateInfo.do",
        data:"password=" + md5Pwd + "&phone=" + phone + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功
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