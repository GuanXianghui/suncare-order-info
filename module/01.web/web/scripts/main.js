/**
 * 查询用户
 */
function queryUser() {
    var name = $("#name").val();
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    $("#userName").val(name);
    document.forms["userForm"].submit();
}

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    $("#pageNum").val(pageNum);
    document.forms["userForm"].submit();
}

/**
 * 重置密码
 *
 * @param userId
 */
function resetPassword(userId){
    if(!confirm("您确定要重置密码吗？")){
        return;
    }
    //重置密码
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "resetPassword.do",
        data:"userId=" + userId + "&token=" + token,
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

/**
 * 删除用户
 *
 * @param userId
 */
function deleteUser(userId){
    if(!confirm("您确定要删除用户吗？")){
        return;
    }
    //删除用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "deleteUser.do",
        data:"userId=" + userId + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功 跳到当前
                    jump2page(pageNum);
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