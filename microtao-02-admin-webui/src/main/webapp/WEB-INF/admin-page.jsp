<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/include-head.jsp" %>
<%--引入分页css与js--%>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">

    $(function () {
        //执行初始化分页导航
        initPageination();
    });

    function initPageination() {
        //获取所有数据的条数
        var totalNum = ${requestScope.pageInfo.total};
        //声明Pagination设置属性的JSON对象
        var properties = {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            prev_text: "上一页",
            next_text: "下一页",
            callback: pageSelectCallback,
            current_page:${requestScope.pageInfo.pageNum-1},//每页显示的条数减1是因为:PageHelper框架的pageNum是从第1页开始的，而这里默认是从0开始的，表第一页
            items_per_page:${requestScope.pageInfo.pageSize} //每页显示的数目
        };
        $("#Pagination").pagination(totalNum, properties);
    }

    //调用分页导航条对应的jQuery对象的pagination()方法生成导航条
    //翻页过程中执行的回调函数
    // 点击“上一页”、“下一页”或“数字页码”都会触发翻页动作，从而导致当前函数被调用
    // pageIndex是用户在页面上点击的页码数值
    function pageSelectCallback(pageIndex, jQuery) {
        //pageIndex是当前页页码的索引，相对于pageNum来说，pageIndex比pageNum小1
        var pageNum = pageIndex + 1;//执行页面跳转也就是实现“翻页”
        window.location.href = "admin/get/page.html?pageNum=" + pageNum+"&keyword=${param.keyword}";
        //取消当前超链接的默认行为
        return false;
    }
</script>
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/get/page.html" method="post" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="6">没有查询到数据！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs"><i
                                                    class=" glyphicon glyphicon-check"></i></button>
                                            <button type="button" class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></button>
                                            <%--执行删除操作带上页码与关键字更合理--%>
                                            <a href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-danger">
                                                <i class="glyphicon glyphicon-remove"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>