/**
 * 新建信息
 */
function createOrderInfo(){
    var name = $("#name").val();
    if(name == EMPTY){
        showAttention("请输入姓名");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if(result["isSuccess"] == false) {
        showAttention("用户名包含非法字符："  + result["symbol"]);
        return;
    }
    var phone = $("#phone").val();
    if(phone == EMPTY){
        showAttention("请输入电话号码");
        return;
    }
    // 判断字符串是否含有非法字符
    result = checkStr(phone, SYMBOL_ARRAY_1);
    if(result["isSuccess"] == false) {
        showAttention("电话号码包含非法字符："  + result["symbol"]);
        return;
    }

    var type = $("#type").val();

    var date = $("#date").val();
    if(date == EMPTY){
        showAttention("请输入订单日期");
        return;
    }

    var time = $("#time_hour").val() + $("#time_minute").val() + "00";

    var resv = $("#resv").val();
    result = checkStr(resv, SYMBOL_ARRAY_1);
    if(result["isSuccess"] == false) {
        showAttention("备注信息包含非法字符："  + result["symbol"]);
        return;
    }

    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "createOrderInfo.do",
        data:"name=" + filterStr(name) + "&phone=" + filterStr(phone) + "&type=" + type + "&date=" + date + "&time=" + time + "&resv=" + filterStr(resv) + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功
                    showSuccess(data["message"]);
                    $("#name").val(EMPTY);
                    $("#phone").val(EMPTY);
                    $("#resv").val(EMPTY);
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