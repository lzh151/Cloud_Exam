<%@ page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>答题进度</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>

    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>

    <![endif]-->

    <script>
        function quitState(student_id) {
            location.href="${pageContext.request.contextPath}/studentQuitServlet?student_id=" + student_id;
        }

        function viewExam(exam_name) {
            location.href="${pageContext.request.contextPath}/viewExamServlet?exam_name=" + exam_name + "&stu_id=${id}";
        }
    </script>

    <link href="../css/form_self.css" rel="stylesheet">
    <style>
        .menu{
            border-bottom: 1px solid #cccccc;
            padding-left: 35px;
            padding-right: 35px;
            margin-bottom: 0px
        }
        .p_footer{
            background: #f9f9f9;
            text-align: center;
        }
        .font_size{
            font-size: 16px;
        }
    </style>
</head>

<body>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<!--导航栏-->
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default menu">
            <!-- Logo -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">云课堂</a>
            </div>

            <!--导航条选项-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <!--选项-->
                <ul class="nav navbar-nav">
                    <li><a href="../index.jsp">首页</a></li>
                    <li><a href="#">手动组卷</a></li>
                    <li><a href="#">智能组卷</a></li>
                    <!--下拉列表-->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">年级 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">高一</a></li>
                            <li><a href="#">高二</a></li>
                            <li><a href="#">高三</a></li>
                        </ul>
                    </li>
                </ul>

                <!--搜索栏-->
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>

                <!--登录注册-->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="student_operation.jsp">${name}同学</a>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:quitState(${id})">注销</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<%--答题进度--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="examLabel">试卷集</div>
    <form class="form-inline"  id="examForm">
        <div id="examDiv">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>试卷名称</td>
                <td>操作</td>
                </thead>
                <tbody style="align-content: center" id="examTable">
                <tr>
                    <c:forEach items="${examList}" var="exams">
                    <td>${exams.exam_name}</td>
                    <td><a class="btn btn-default" href="javascript:viewExam(&quot ${exams.exam_name}&quot);">查看</a></td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<hr>

<%--模块隐藏--%>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        $("#examLabel").click(function(){--%>
<%--            $("#examForm").fadeToggle();--%>
<%--        });--%>
<%--    });--%>
<%--    $("#examForm").hide();--%>
<%--</script>--%>

<!--版权声明-->
<footer class="container-fluid">
    <div class="container-fluid">
        <div class="row p_footer">
            <p>Java程序设计</p>
            <p>版权所有&nbsp;Copyright&nbsp;&nbsp;2019-2020&nbsp;All Rights Reserved</p>
        </div>
    </div>
</footer>

</body>
</html>