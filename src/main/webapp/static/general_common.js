$(function () {
//获取访问地址绝对路径
    function getRealPath() {
        //获取当前网址，如： http://localhost:8083/shiro/signIn/login.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： shiro/signIn/login.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8080
        var localhostPah = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/login
        // var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        //得到了 服务器名称和项目名称
        return localhostPah;
    }

    var contextPath = getRealPath();
    $("#sub").click(function () {
        console.log("路径：" + contextPath);
        var name = $("#name").val();
        var available = $("#available").val();
        var url = $("#url").val();
        var permission = $("#permission").val();
        var moduleId = $("#moduleId").val();
        var type = $("#type").val();
        var parentId = $("#parentId").val();
        if (valid(name)) {
            $("#errMsg").show();
            $("#errMsg").text("请输入权限名称");
            return;
        } else if (valid(url)) {
            $("#errMsg").show();
            $("#errMsg").text("请输入资源路径");
            return;
        } else if (valid(permission)) {
            $("#errMsg").show();
            $("#errMsg").text("请输入权限字符串");
            return;
        } else if (valid(moduleId)) {
            $("#errMsg").show();
            $("#errMsg").text("请选择模块");
            return;
        } else if (valid(type)) {
            $("#errMsg").show();
            $("#errMsg").text("请选择权限类型");
            return;
        }
        //发送请求
        $.post(contextPath + "/permission/add", {
            "name": name,
            "available": available,
            "url": url,
            "permission": permission,
            "moduleId": moduleId,
            "type": type,
            "parentId": parentId
        }, function (data) {
            var obj = eval(data);
            alert(obj.message);
            if (obj.ok != null) {
                $(".close").click();
            }
        }, 'json');
    });

    //校验参数
    function valid(obj) {
        if (obj == null || !obj.length > 0) {
            return true;
        }
        return false;
    }
});