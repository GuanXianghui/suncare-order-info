<%@ page import="com.gxx.order.utils.BaseUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    //判登录
    if(!BaseUtil.isLogin(request))
    {
        //域名链接
        response.sendRedirect(baseUrl + "login.jsp");
        return;
    }
%>