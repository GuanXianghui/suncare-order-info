<%@ page import="org.apache.commons.lang.StringUtils"
        %><%@ page import="com.gxx.order.dao.UserDao"
        %><%@ page import="com.gxx.order.entities.User"
        %>
<%@ page import="com.gxx.order.interfaces.OperateLogInterface"
        %>
<%@ page import="com.gxx.order.utils.*" %>
<%@ page import="org.apache.commons.lang.math.RandomUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        %><%
    String resp;
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    String name = StringUtils.trimToEmpty(request.getParameter("name"));
    String password = StringUtils.trimToEmpty(request.getParameter("password"));
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
            } else {
                String temp = "0000" + RandomUtils.nextInt(9999);
                String validateCode = temp.substring(temp.length() - 4);
                String content = "*" + user.getName().substring(1) + "尝试登陆获取登陆短信验证码：" +
                        validateCode + "，" + "如非本人操作请立即告知管理员。";
                boolean isSuccess = SMSUtil.checkSuccess(SMSUtil.sendSMS(user.getPhone(), content));
                String message;
                if(!isSuccess){
                    message = "发送短信验证码失败！";
                    resp = "{isSuccess:false,message:'" + message + "',hasNewToken:true,token:'" +
                            TokenUtil.createToken(request) + "'}";
                } else {
                    user.setValidateCode(validateCode);
                    UserDao.updateUser(user);
                    message = "发送短信验证码成功（一般会在2分钟内收到）！";
                    resp = "{isSuccess:true,message:'" + message + "',hasNewToken:true,token:'" +
                            TokenUtil.createToken(request) + "'}";
                }
                //创建操作日志
                BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_LOG_SMS, message,
                        DateUtil.getNowDate(), DateUtil.getNowTime(), IPAddressUtil.getIPAddress(request));
            }
        }
    }
    response.getWriter().write(resp);
%>