/**
 * 查询操作日志
 */
function queryOperateLogs() {
    var userName = $("#userName").val();
    var type = $("#type").val();
    var date = $("#date").val();

    // 判断字符串是否含有非法字符
    var result = checkStr(userName, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户姓名包含非法字符:" + result["symbol"]);
        return;
    }

    //判type为空置成0，这样就不会带入作为条件
    if(type == EMPTY){
        type = parseInt("0");
    } else {
        //判type是否数字
        if(!isNum(type)){
            showAttention("type不是整数");
            return;
        }
    }

    // 判断字符串是否含有非法字符
    result = checkStr(date, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("日期包含非法字符:" + result["symbol"]);
        return;
    }

    $("#operateLogUserName").val(userName);
    $("#operateLogType").val(type);
    $("#operateLogDate").val(date);
    document.forms["operateLogForm"].submit();
}

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    $("#pageNum").val(pageNum);
    document.forms["operateLogForm"].submit();
}