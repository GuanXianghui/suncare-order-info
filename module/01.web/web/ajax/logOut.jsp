<%@ page import="com.gxx.order.interfaces.BaseInterface"
        %><%@ page import="com.gxx.order.utils.BaseUtil"
        %><%@ page import="com.gxx.order.entities.User"
        %><%@ page import="com.gxx.order.interfaces.OperateLogInterface"
        %><%@ page import="com.gxx.order.utils.DateUtil"
        %><%@ page import="com.gxx.order.utils.IPAddressUtil"
        %><%@ page contentType="text/html;charset=UTF-8" language="java"
        %><%
    //如果处于登陆状态
    if(BaseUtil.isLogin(request)){
        User user = (User)request.getSession().getAttribute(BaseInterface.USER_KEY);
        //创建操作日志
        BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_LOG_OUT, "成功退出",
                DateUtil.getNowDate(), DateUtil.getNowTime(), IPAddressUtil.getIPAddress(request));
    }
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    request.getSession().setAttribute(BaseInterface.USER_KEY, null);
    String resp = "{isSuccess:true,message:'退出成功！',isRedirect:true,redirectUrl:'" + baseUrl + "login.jsp'}";
    response.getWriter().write(resp);
%>