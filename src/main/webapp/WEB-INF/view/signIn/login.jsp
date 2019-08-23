<%--
  Created by IntelliJ IDEA.
  User: Yupanpan
  Date: 2019/8/21
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <script src="${basePath}static/jquery-2.1.4.js"></script>
    <style>
        .msg {
            display: none;
            font-size: 14px;
            color: red;
        }

        .first {
            margin-top: 15%;
        }

        div {
            margin-top: 5px;
        }
        input:focus {
            outline:none; /*外边框颜色*/
            border: 1px solid #00FF00;
        }
    </style>
</head>
<body>
<center>
    <div class="first">
        <h1>欢迎来到登录页面</h1>
        <form>
            <div>
                <input type="hidden" name="ssss" value="sss"/>
            </div>
            <div>
                <input type="text" id="userName" name="userName" style="width:260px;height:40px;" placeholder="用户名"><br/>
            </div>
            <div>
                <input type="password" id="password" name="password" style="width:260px;height:40px;" placeholder="密码"><br/>
            </div>
            <div>
                <p class="msg"></p>
            </div>
            <div>
                <br><button type="button" id="sub" style="width:70px;height:30px;">提交</button>
            </div>
        </form>
    </div>
</center>
<script>
    $(function () {
        $("#sub").click(function () {
            var username = $("#userName").val();
            var password = $("#password").val();
            console.log(username + " <--> " + password);
            <%--$.post("${basePath}signIn", {--%>
            <%--    "userName": username,--%>
            <%--    "password": password--%>
            <%--}, function (result) {--%>
            <%--    var obj = eval('(' + result + ')');--%>
            <%--    $(".msg").show();--%>
            <%--    $(".msg").text(obj.message);--%>
            <%--    if (obj.ok != null) {--%>
            <%--        window.location.href = "${basePath}index";--%>
            <%--    }--%>
            <%--});--%>
        });
    })
</script>
</body>
</html>
