<%@ page import="org.apache.commons.lang.StringUtils"
        %><%@ page import="com.gxx.order.dao.UserDao"
        %><%@ page import="com.gxx.order.entities.User"
        %><%@ page import="com.gxx.order.interfaces.BaseInterface"
        %><%@ page import="com.gxx.order.utils.TokenUtil"
        %><%@ page import="com.gxx.order.utils.BaseUtil"
        %><%@ page import="com.gxx.order.interfaces.OperateLogInterface"
        %><%@ page import="com.gxx.order.utils.DateUtil"
        %><%@ page import="com.gxx.order.utils.IPAddressUtil"
        %><%@ page contentType="text/html;charset=UTF-8" language="java"
        %><%
    String resp;
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    String name = StringUtils.trimToEmpty(request.getParameter("name"));
    String password = StringUtils.trimToEmpty(request.getParameter("password"));
    String validateCode = StringUtils.trimToEmpty(request.getParameter("validateCode"));
    String token = StringUtils.trimToEmpty(request.getParameter("token"));
    System.out.println("name=" + name + ",password=" + password + ",token=" + token);
    if(!TokenUtil.checkToken(request, token)){
        resp = "{isSuccess:false,message:'您的提交失败，token已失效！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
    } else {
        if(!UserDao.isNameExist(name)){
            resp = "{isSuccess:false,message:'你输入的用户名不存在！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            User user = UserDao.getUserByName(name);
            if(!user.getPassword().equals(password)){
                resp = "{isSuccess:false,message:'你输入的密码错误！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
            } else if(StringUtils.isBlank(user.getValidateCode())){
                resp = "{isSuccess:false,message:'请先获取验证码！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
            } else if(!user.getValidateCode().equals(validateCode)){
                resp = "{isSuccess:false,message:'你输入的短信验证码错误！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
            } else {
                user.setValidateCode(StringUtils.EMPTY);
                UserDao.updateUser(user);
                request.getSession().setAttribute(BaseInterface.USER_KEY, user);
                resp = "{isSuccess:true,message:'登陆成功！',isRedirect:true,redirectUrl:'" + baseUrl + "main.jsp'}";
                //创建操作日志
                BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_LOG_IN, "成功登陆",
                        DateUtil.getNowDate(), DateUtil.getNowTime(), IPAddressUtil.getIPAddress(request));
            }
        }
    }
    response.getWriter().write(resp);
%>