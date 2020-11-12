<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn1").click(function () {
                var data = [1, 2, 3, 4, 5, 6];
                var requestData = JSON.stringify(data);
                $.ajax({
                    /*"url": "ajaxTest/get.json",*/
                    "url": "getAdminAll.json",
                    "data": requestData,
                    "type": "post",
                    "dataType": "text",
                   /* "contentType": "application/json;charset=UTF-8",*/
                    "success": function (response) {
                        alert(response);
                    },
                    "error": function (response) {
                        alert("相应失败！");
                    }
                });
            });

        });

        $(function () {
            $("#btn2").click(function () {
                var data = [1, 2, 3, 4, 5, 6];
                var requestData = JSON.stringify(data);
                $.ajax({
                    "url": "getAdminAll.json",
                    "data": requestData,
                    "type": "post",
                    "dataType": "json",
                    "contentType": "application/json;charset=UTF-8",
                    "success": function (response) {
                    },
                    "error": function (response) {
                    }
                });
            });
        });

    </script>

</head>
<body>

<button id="btn1">按钮</button>
<br/>
<br/>
<br/>
<button id="btn2">跳转出错页面</button>
</body>
</html>
