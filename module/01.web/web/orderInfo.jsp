<%@ page import="java.util.List" %>
<%@ page import="com.gxx.order.dao.OrderInfoDao" %>
<%@ page import="com.gxx.order.entities.OrderInfo" %>
<%@ page import="com.gxx.order.utils.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%
    //用户姓名
    String name = StringUtils.trimToEmpty(request.getParameter("name"));
    //电话号码
    String phone = StringUtils.trimToEmpty(request.getParameter("phone"));
    //类型
    String type = StringUtils.trimToEmpty(request.getParameter("type"));
    //订单日期
    String date = StringUtils.trimToEmpty(request.getParameter("date"));
    //备注
    String resv = StringUtils.trimToEmpty(request.getParameter("resv"));
    //当前页数
    String pageStr = StringUtils.trimToEmpty(request.getParameter("pageNum"));
    //当前页数
    int pageNum;
    try {
        pageNum = Integer.parseInt(pageStr);
    } catch (Exception e) {
        pageNum = 1;
    }
    //用户列表每页大小
    int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.USER_PAGE_SIZE));
    //用户总数
    int count = OrderInfoDao.countOrderInfoByConditions(name, phone, type, date, resv);
    //是否为空
    boolean isEmpty = count == 0;
    //总页数
    int pageCount = (count - 1) / pageSize + 1;
    //删除最后一条，可能会少掉一页
    if(pageNum > pageCount){
        pageNum = pageCount;
    }
    //订单信息列表
    List<OrderInfo> orderInfoList = OrderInfoDao.queryOrderInfoByConditions(name, phone, type, date, resv, pageNum);
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
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
    <script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
    <script type="text/javascript" src="scripts/jquery.date.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/orderInfo.js"></script>
    <!-- 日期控件 -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <script type="text/javascript">
        //当前页数
        var pageNum = <%=pageNum%>;
        //初始订单日期
        var date = "<%=date%>";
    </script>
    <script>
        $(function() {
            $( "#date" ).datepicker();
            $( "#date" ).datepicker( "option", "dateFormat", "yymmdd" );
            $( "#date" ).datepicker( "option", "showAnim", "drop" );
            $( "#date" ).datepicker( "option", "onSelect", function(dateText, inst ){
            });
            $( "#date").val(date)
        });
    </script>
</head>
<body>
<div id="body-wrapper">
    <div id="sidebar">
        <div id="sidebar-wrapper">
            <h1 id="sidebar-title"><a href="#">申成-文件系统</a></h1>
            <img id="logo" src="images/suncare-files-logo.png" alt="Simpla Admin logo"/>

            <div id="profile-links"> Hello, [<%=user.getName()%>], <a href="http://www.suncarechina.com"
                                                                      target="_blank">申成</a>欢迎您！<br/>
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
        <form action="<%=baseUrl%>orderInfo.jsp" name="orderInfoForm" method="post" style="display: none;">
            <input type="hidden" name="name" id="orderName" value="<%=name%>">
            <input type="hidden" name="phone" id="orderPhone" value="<%=phone%>">
            <input type="hidden" name="type" id="orderType" value="<%=type%>">
            <input type="hidden" name="date" id="orderDate" value="<%=date%>">
            <input type="hidden" name="resv" id="orderResv" value="<%=resv%>">
            <input type="hidden" name="pageNum" id="pageNum" value="1">
        </form>
        <form>
            <span>用户名</span>&nbsp;&nbsp;<input class="text-input" type="text" id="name" value="<%=name%>"/>
            <span>电话号码</span>&nbsp;&nbsp;<input class="text-input" type="text" id="phone" value="<%=phone%>"/>
            <span>订单类型</span>&nbsp;&nbsp;
            <select class="text-input" id="type">
                <option value="">全部</option>
                <option value="1"<%=StringUtils.equals("1", type)?" SELECTED":""%>>工程</option>
                <option value="2"<%=StringUtils.equals("2", type)?" SELECTED":""%>>私单</option>
                <option value="3"<%=StringUtils.equals("3", type)?" SELECTED":""%>>代理</option>
            </select>
            <span>订单日期</span>&nbsp;&nbsp;<input class="text-input" type="text" id="date" value="<%=date%>"/>
            <span>备注信息</span>&nbsp;&nbsp;<input class="text-input" type="text" id="resv" value="<%=resv%>"/>
            <input class="button" type="button" onclick="queryOrderInfo();" value="查询"/>
            <br/>
            <br/>
        </form>
        <div class="content-box">
            <div class="content-box-header">
                <h3>用户列表</h3>
                <ul class="content-box-tabs">
                    <li><a href="#tab1" class="default-tab">Table</a></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab" id="tab1">
                    <div id="message_id" class="notification information png_bg" style="display: none;">
                        <a href="#" class="close">
                            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                        </a>

                        <div id="message_id_content"> 提示信息！</div>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>电话号码</th>
                            <th>订单类型</th>
                            <th>订单日期</th>
                            <th>订单时间</th>
                            <th>备注信息</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="8">
                                <div class="pagination">
                                    <a href="javascript: jump2page(1)" title="首页">&laquo; 首页</a>
                                    <%
                                        if(pageNum > 1){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum-1%>)" title="上一页">&laquo; 上一页</a>
                                    <%
                                        }
                                    %>
                                    <%
                                        //显示前2页，本页，后2页
                                        for(int i=pageNum-2;i<pageNum+3;i++){
                                            if(i >= 1 && i <= pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=i%>)" class="number<%=(i==pageNum)?" current":""%>" title="<%=i%>"><%=i%></a>
                                    <%
                                            }
                                        }
                                    %>
                                    <%
                                        if(pageNum < pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum+1%>)" title="下一页">下一页 &raquo;</a>
                                    <%
                                        }
                                    %>
                                    <a href="javascript: jump2page(<%=pageCount%>)" title="尾页">尾页 &raquo;</a>
                                </div>
                                <div class="clear"></div>
                            </td>
                        </tr>
                        </tfoot>
                        <tbody>
                        <%
                            //判是否为空
                            if (isEmpty) {
                        %>
                        <tr>
                            <td colspan="8">
                                没找到符合条件的用户
                            </td>
                        </tr>
                        <%
                        } else {//非空
                            for (OrderInfo tempOrderInfo : orderInfoList) {
                        %>
                        <tr>
                            <td><%=tempOrderInfo.getId()%></td>
                            <td><%=tempOrderInfo.getName()%></td>
                            <td><%=tempOrderInfo.getPhone()%></td>
                            <td><%=tempOrderInfo.getTypeDesc()%></td>
                            <td><%=DateUtil.getLongDate(DateUtil.getDate(tempOrderInfo.getDate()))%></td>
                            <td><%=DateUtil.getLongTime(DateUtil.getDateTime(tempOrderInfo.getDate(), tempOrderInfo.getTime()))%></td>
                            <td><%=tempOrderInfo.getResv()%></td>
                            <td>
                                <a href="updateOrderInfo.jsp?id=<%=tempOrderInfo.getId()%>" title="修改"><img
                                        src="images/icons/hammer_screwdriver.png" alt="修改"/></a>
                                <a href="javascript: deleteOrderInfo(<%=tempOrderInfo.getId()%>);" title="删除"><img
                                        src="images/icons/cross.png" alt="删除"/></a>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
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
