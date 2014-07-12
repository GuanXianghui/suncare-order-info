<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gxx.order.utils.*" %>
<%@ page import="com.gxx.order.entities.OrderInfo" %>
<%@ page import="com.gxx.order.dao.OrderInfoDao" %>
<%@ page import="com.gxx.order.interfaces.OrderInfoInterface" %>
<%@ include file="header.jsp" %>
<%
    String id = StringUtils.trimToEmpty(request.getParameter("id"));
    if(StringUtils.isBlank(id)){
        response.sendRedirect("orderInfo.jsp");
        return;
    }

    OrderInfo orderInfo;

    try{
        orderInfo = OrderInfoDao.getOrderInfoById(Integer.parseInt(id));
        if(orderInfo == null || orderInfo.getState() == OrderInfoInterface.STATE_DELETED){
            response.sendRedirect("orderInfo.jsp");
            return;
        }
    } catch (Exception e){
        response.sendRedirect("orderInfo.jsp");
        return;
    }

    String timeHour = orderInfo.getTime().substring(0, 2);
    String timeMinute = orderInfo.getTime().substring(2, 4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>申成-文件系统</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/updateOrderInfo.js"></script>
    <!-- 日期控件 -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#date" ).datepicker();
            $( "#date" ).datepicker( "option", "dateFormat", "yymmdd" );
            $( "#date" ).datepicker( "option", "showAnim", "drop" );
            $( "#date" ).datepicker( "option", "onSelect", function(dateText, inst ){
            });
            $( "#date").val("<%=orderInfo.getDate()%>");
            $( "#time_hour").val("<%=timeHour%>");
            $( "#time_minute").val("<%=timeMinute%>");
        });

        var id = "<%=id%>";
    </script>
</head>
<body>
<div id="body-wrapper">
<div id="sidebar">
    <div id="sidebar-wrapper">
        <h1 id="sidebar-title"><a href="#">申成-文件系统</a></h1>
        <img id="logo" src="images/suncare-files-logo.png" alt="Simpla Admin logo"/>

        <div id="profile-links"> Hello, [<%=user.getName()%>], <a href="http://www.suncarechina.com" target="_blank">申成</a>欢迎您！<br/>
            <br/>
            <a href="javascript: logOut()" title="Sign Out">退出</a></div>
        <ul id="main-nav">
            <li><a href="#" class="nav-top-item"> 用户管理 </a>
                <ul>
                    <li><a href="<%=baseUrl%>main.jsp">用户查询</a></li>
                    <%if(isSuperMan){%>
                    <li><a href="<%=baseUrl%>createUser.jsp">新增用户</a></li>
                    <%}%>
                    <li><a href="<%=baseUrl%>updateInfo.jsp">修改资料</a></li>
                </ul>
            </li>
            <li><a href="#" class="nav-top-item current"> 信息管理 </a>
                <ul>
                    <li><a href="<%=baseUrl%>orderInfo.jsp" class="current">查看信息</a></li>
                    <li><a href="<%=baseUrl%>createOrderInfo.jsp">新建信息</a></li>
                </ul>
            </li>
            <li><a href="#" class="nav-top-item"> 日志 </a>
                <ul>
                    <li><a href="<%=baseUrl%>operateLog.jsp">查看日志</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="main-content">
<div class="content-box">
<div class="content-box-header">
    <h3>修改信息</h3>
    <ul class="content-box-tabs">
        <li><a href="#tab1" class="default-tab">Forms</a></li>
    </ul>
    <div class="clear"></div>
</div>
<div class="content-box-content">
<div class="tab-content default-tab" id="tab1">
    <div id="message_id" class="notification information png_bg" style="display: none;">
        <a href="#" class="close">
            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
        </a>
        <div id="message_id_content"> 提示信息！ </div>
    </div>
    <form>
        <fieldset>
            <p>
                <span>客户名称</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="name" name="name" value="<%=orderInfo.getName()%>"/><br>
                <span>电话号码</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="phone" name="phone" value="<%=orderInfo.getPhone()%>"/><br>
                <span>订单类型</span>&nbsp;&nbsp;
                <select id="type" class="text-input small-input" name="type">
                    <option value="1"<%=1==orderInfo.getType()?" SELECTED":""%>>工程</option>
                    <option value="2"<%=2==orderInfo.getType()?" SELECTED":""%>>私单</option>
                    <option value="3"<%=3==orderInfo.getType()?" SELECTED":""%>>代理</option>
                </select>
                <br>
                <span>订单日期</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="date" name="date"/><br>
                <span>订单时间</span>&nbsp;&nbsp;
                <select id="time_hour" class="text-input very-small-input">
                    <%
                        for(int i=0;i<24;i++){
                    %>
                    <option value="<%=i<10?"0"+i:i%>"><%=i<10?"0"+i:i%></option>
                    <%
                        }
                    %>
                </select>
                :
                <select id="time_minute" class="text-input very-small-input">
                    <%
                        for(int i=0;i<60;i++){
                    %>
                    <option value="<%=i<10?"0"+i:i%>"><%=i<10?"0"+i:i%></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <span>备注信息</span>&nbsp;&nbsp;
                <input class="text-input medium-input" type="text" id="resv" name="resv" value="<%=orderInfo.getResv()%>"/><br>
                <input class="button" type="button" onclick="updateOrderInfo();" value="修改" />
            </p>
        </fieldset>
        <div class="clear"></div>
    </form>
</div>
</div>
</div>
<div id="footer">
    <small>
        &#169; Copyright 2014 Suncare | Powered by 关向辉
    </small>
</div>
</div>
</div>
</body>
</html>
