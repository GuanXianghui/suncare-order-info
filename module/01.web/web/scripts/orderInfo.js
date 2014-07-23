/**
 * 查询订单信息
 */
function queryOrderInfo() {
    var name = $("#name").val();
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    $("#orderName").val(name);

    var phone = $("#phone").val();
    // 判断字符串是否含有非法字符
    result = checkStr(phone, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    $("#orderPhone").val(phone);

    var type = $("#type").val();
    $("#orderType").val(type);

    var date = $("#date").val();
    $("#orderDate").val(date);

    var resv = $("#resv").val();
    // 判断字符串是否含有非法字符
    result = checkStr(resv, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    $("#orderResv").val(resv);

    document.forms["orderInfoForm"].submit();
}

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    $("#pageNum").val(pageNum);
    document.forms["orderInfoForm"].submit();
}

/**
 * 删除订单
 * @param id
 */
function deleteOrderInfo(id){
    if(!confirm("您确定要删除订单信息吗？")){
        return;
    }
    //删除用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "deleteOrderInfo.do",
        data:"id=" + id + "&token=" + token,
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

/**
 * 按月导出
 */
function exportByMonth(){
    //删除用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "exportByMonth.do",
        data:"year=" + $("#year").val() + "&month=" + $("#month").val() + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功 跳到当前
                    showSuccess(data["message"]);
                    window.open("download.jsp?fileRoute=" + data["fileRoute"] + "&newName=业务登记汇总" + $("#year").val() + "-" + $("#month").val() + ".xls");
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