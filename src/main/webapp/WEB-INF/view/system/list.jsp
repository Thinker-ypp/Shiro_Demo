<%--
  Created by IntelliJ IDEA.
  User: Yupanpan
  Date: 2019/8/29
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>权限管理列表</title>
    <link href="${basePath}/static/public.css" rel="stylesheet" type="text/css">
    <style>
        body {
            position: relative;
            z-index: 1;
        }

        .toolBar {
            margin-top: 20px;
        }

        tr, td, th {
            border: 0px solid black;
            text-align: left;
        }

        td {
            width: 200px;
        }

        .childWin {
            display: none;
            position: absolute;
            top: 25%;
            margin-left: 35%;
            width: 400px;
            height: 350px;
            z-index: 2;
            background: #ffffff;
            border: 3px solid #d8d62b;
        }

        .childWin iframe {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

<form>
    <fieldset>
        <input type="text" id="name"/> 名称<br/>
        <input type="button" id="sub" class="" value="搜索"/>
        <div class="toolBar"></div>
    </fieldset>
    <table>
        <thead>
        <th>编号</th>
        <th>名称</th>
        <th>类型</th>
        <th>路径</th>
        <th>权限字符串</th>
        <th>所属模块</th>
        <th>更新时间</th>
        </thead>
        <tbody class="tbody">
        </tbody>
    </table>
</form>
<div class="childWin">
    <iframe src="" frameborder="0"></iframe>
</div>
<script src="${basePath}/static/jquery-2.1.4.js"></script>
<script>
    var primaryKey = "";
    /* 初始化加载数据 */
    $(function () {
        getResult("");
        setToolBar();
        $("#sub").click(function () {
            $(".tbody").html("");
            getResult($("#name").val());
        });
    });

    /* ajax 获取数据 */
    function getResult(parms) {
        $.post("${basePath}permission/index?name=" + parms, {}, function (data) {
            $(".tbody").html("");
            var obj = eval(data);
            var listText = $(".tbody").html();
            for (var i = 0; i < obj.length; i++) {
                listText = listText + "<tr  class='primarykey'>";
                listText = listText + "<td>" + obj[i].id + "</td>";
                listText = listText + "<td>" + obj[i].name + "</td>";
                listText = listText + "<td>" + obj[i].type + "</td>";
                listText = listText + "<td>" + obj[i].url + "</td>";
                listText = listText + "<td>" + obj[i].permission + "</td>";
                listText = listText + "<td>" + obj[i].moduleId + "</td>";
                listText = listText + "<td>" + obj[i].updateTime + "</td>";
                listText = listText + "<td><input type='button' onclick='setPrimaryKey(this)' value='选中'></td>";
                listText = listText + "</tr>";
            }
            $(".tbody").html(listText);
        })
    }

    /* 设置功能按钮 */
    function setToolBar() {
        var toolText = $(".toolBar").html();
        toolText = toolText + '<shiro:hasPermission name="system:permissions:add"><input type="button" onclick="add()" class="btn" value="新增权限"/>&nbsp;</shiro:hasPermission>';
        toolText = toolText + '<shiro:hasPermission name="system:permissions:update"><input type="button" onclick="update()" class="btn" value="修改权限"/>&nbsp;</shiro:hasPermission>';
        toolText = toolText + '<shiro:hasPermission name="system:permissions:delete"><input type="button" class="btn" value="删除权限"/></shiro:hasPermission>';
        $(".toolBar").html(toolText);
    }

    /* 新增权限 */
    function add() {
        $(".childWin").show();
        $(".childWin iframe").attr("src", "${basePath}permission/add?id=" + "");
        $(".childWin iframe").load();
    }

    /* 修改权限 */
    function update() {
        console.log("编辑权限Id ：" + primaryKey);
        if (primaryKey != '' && primaryKey != null) {
            $(".childWin").show();
            $(".childWin iframe").attr("src", "${basePath}permission/update?id=" + primaryKey);
            $(".childWin iframe").load();
        } else {
            alert("请选择要编辑的数据！");
        }
    }

    //选中某一行数据
    function setPrimaryKey(obj) {
       var x = $(obj).parent().parent().find("td");
       console.log(x);
       primaryKey = x.eq(0).text();
       console.log(primaryKey);
    }
</script>
</body>
</html>
