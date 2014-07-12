<%@ page import="com.gxx.order.interfaces.BaseInterface" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.gxx.order.entities.User" %>
<%@ page import="com.gxx.order.utils.PropertyUtil" %>
<%@ page import="com.gxx.order.utils.TokenUtil" %>
<%@ page import="com.gxx.order.utils.BaseUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //域名链接
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    //md5 key
    String md5Key = PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY);
    //token串
    String token = TokenUtil.createToken(request);
    //session中获取user
    User user = (User)request.getSession().getAttribute(BaseInterface.USER_KEY);
    //是否已经登录
    boolean isLogin = user != null;
    //消息
    String message = StringUtils.trimToEmpty((String)request.getAttribute("message"));
    //是否超级管理员
    boolean isSuperMan = false;
    if(isLogin){
        isSuperMan = BaseUtil.isSuperMan(user.getName());
    }
%>
<script type="text/javascript">
    //域名链接
    var baseUrl = "<%=baseUrl%>";
    //md5 key
    var md5Key = "<%=md5Key%>";
    //token穿
    var token = "<%=token%>";
    //是否已经登录
    var isLogin = <%=isLogin%>;
    //弹出消息框
    var message = '<%=message%>';
</script>
<!-- 图标 -->
<link rel="shortcut icon" type="image/x-icon" href="images/suncare-file-little-logo.png" />