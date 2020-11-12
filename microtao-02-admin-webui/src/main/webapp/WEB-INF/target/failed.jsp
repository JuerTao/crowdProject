<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--路径前缀--%>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#btn1").click(function () {
                window.history.back();
            });
        });

    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container" style="text-align:center;">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">糟糕</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="form-signin-heading" style="text-align: center"><i class="glyphicon glyphicon-log-in"></i> 出错了</h2>
    <h3 style="text-align: center">${requestScope.get("exception").getMessage()}</h3>
    <button id="btn1" class="btn btn-lg btn-success btn-block" style="width:150px;margin: 50px auto 0px auto">返回上一步</button>
</div>
</body>
</html>