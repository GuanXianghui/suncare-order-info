<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>申成-文件系统</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
    <script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/login.js"></script>
    <script type="text/javascript" src="scripts/md5.js"></script>
</head>
<body id="login" onkeypress="keyPress(event)">
<div id="login-wrapper" class="png_bg">
    <div id="login-top">
        <h1>申成-文件系统</h1>
        <img id="logo" src="images/suncare-files-logo.png" alt="申成-文件系统" />
    </div>
    <div id="login-content">
        <form>
            <div id="message_id" class="notification information png_bg" style="display: none;">
                <a href="#" class="close">
                    <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                </a>
                <div id="message_id_content"> 提示信息！ </div>
            </div>
            <p>
                <label>用户名</label>
                <input class="text-input" type="text" id="name" />
            </p>
            <div class="clear"></div>
            <p>
                <label>密码</label>
                <input class="text-input" type="password" id="password" />
            </p>
            <div class="clear"></div>
            <p>
                <label>短信验证码</label>
                <input class="text-input" type="text" id="validate_code" />
            </p>
            <div class="clear"></div>
            <p>
                <input class="button" type="button" onclick="login();" value="登录" style="margin-left: 5px;"/>
                <input class="button" type="button" onclick="sendValidateCode();" value="获取短信验证码"/>
            </p>
        </form>
    </div>
</div>
</body>
</html>
