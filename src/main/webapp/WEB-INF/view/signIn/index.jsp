<%--
  Created by IntelliJ IDEA.
  User: yupanpan
  Date: 2019/8/21
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>主页面</title>
    <style type="text/css">
        #iframe {
            margin-top: 50px;
            margin-left: 100px;
            border: 1px solid black;
            width: 80%;
            height: 600px;
        }

        #iframe iframe {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<h2>模块</h2>
欢迎您！<shiro:principal/>。
<p>
    <button onclick="logout()">退出</button>
</p>
<c:forEach items="${zdh_default:modules()}" var="m">
    <li>
        <a onclick="getClicks('${basePath }module/click?moduleId=${m.id }')" data-toggle="sidenav"
           data-id-key="targetid">${m.name }</a>
    </li>
</c:forEach>
<ul class="modules"></ul>
<div id="iframe">
    <iframe src="" frameborder="0"></iframe>
</div>
<script src="${basePath}/static/jquery-2.1.4.js"></script>
<script src="${basePath}/static/ajaxStatus.js"></script>
<script>
    /* 获取所有一级菜单 */
    function getClicks(url) {
        $(".modules").html("");
        $.post(url, {}, function (data) {
            var lists = eval(data);
            var liText = $(".modules").html();
            for (var i = 0; i < lists.length; i++) {
                liText = liText + '<li><a onclick="getMenus(\'${basePath }module/menu?parentId=' + lists[i].id + '\',this)">' + lists[i].name + '</a><ul></ul></li>';
            }
            $(".modules").html(liText);
        });
    }

    /* 获取所有二级菜单 */
    function getMenus(url, thi) {
        $.post(url, {}, function (data) {

            $(thi).next("ul").html("");
            var lists = eval(data);
            var liText = $(thi).next("ul").html();
            for (var i = 0; i < lists.length; i++) {
                liText = liText + '<li><a data-url="' + lists[i].url + '" onclick="setFrame(this)">' + lists[i].name + '</a></li>';
            }
            $(thi).next("ul").html(liText);
        }, 'json');
    }

    /* 设置 iframe 的加载窗口路径 */
    function setFrame(thi) {
        var url = $(thi).attr("data-url");
        var path = "${basePath }".substr(0, "${basePath }".length - 1) + url;
        $("#iframe iframe").attr("src", "${basePath}" + url);
        $("#iframe iframe").load();
    }

    /* 退出 */
    function logout() {
        //POST方式请求
        $.post("${basePath}logout", null, function (result) {
            var obj = eval('(' + result + ')');
            if (obj.ok != null) {
                alert(obj.message);
                window.location.href = "${basePath}signIn";
            }
        });

        //GET方式请求
        // $.ajax({
        //     url: "/logout",                 <!--get方式请求,这里使用相对路径拼接url-->
        //     type: "GET",
        //     dataType: "json",               <!--服务器响应的数据类型为json串-->
        //     data: null,                     <!--等价于URL后面拼接参数-->
        //     success: function (result) {
        //         console.log(">>> 退出操作成功 <<<");
        //         alert(result.message)   	//请求成功时运行弹窗，取出json里面的value数据提示
        //         window.location.href = "/signIn";
        //     },
        //     error: function (result) {
        //         console.log(">>> 退出操作失败 <<<");
        //         alert(result.message)       //打印错误信息
        //     }
        // });
    }
</script>
</body>
</html>
