<%@ page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>云课堂</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>

    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>

    <![endif]-->

    <style>
        .picture{
            height: 250px;
            border-bottom: 1px solid #cccccc;
        }
        .menu{
            border-bottom: 1px solid #cccccc;
            padding-left: 35px;
            padding-right: 35px;
            margin-bottom: 0
        }
        .p_footer{
            background: #f9f9f9;
            text-align: center;
        }
        td{
            width: 10%;
        }
    </style>
</head>

<body>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <div class="container-fluid">
        <!--导航栏-->
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
<%--                                <li><a href="#">首页</a></li>--%>
<%--                                <li><a href="#">手动组卷</a></li>--%>
<%--                                <li><a href="#">智能组卷</a></li>--%>
<!--                                &lt;!&ndash;下拉列表&ndash;&gt;-->
<!--                                <li class="dropdown">-->
<!--                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">单元 <span class="caret"></span></a>-->
<!--                                    <ul class="dropdown-menu">-->
<!--                                        <li><a href="#">第一单元</a></li>-->
<!--                                        <li><a href="#">高二</a></li>-->
<!--                                        <li><a href="#">高三</a></li>-->
<!--                                    </ul>-->
<!--                                </li>-->
                            </ul>

                            <!--搜索栏-->
                            <form class="navbar-form navbar-right">
                                <div class="form-group">
                                    <label>
                                        <input type="text" class="form-control" placeholder="Search">
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-default">搜索</button>
                            </form>

                            <!--登录注册-->
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">登录 <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="HTML/student_login.jsp">学生</a></li>
                                        <li><a href="HTML/teacher_login.jsp">教师</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">注册 <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="HTML/student_register.jsp">学生</a></li>
                                        <li><a href="HTML/teacher_register.jsp">教师</a></li>
                                    </ul>
                                </li>

                            </ul>
                        </div>
                </nav>
        </div>

        <!--幻灯片-->
        <div class="row">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- 图片 -->
                <div class="carousel-inner  picture" role="listbox">
                    <div class="item active">
                        <img src="IMG/back1.jpg" alt="">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="IMG/back2.jpg" alt="">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="IMG/back3.jpg" alt="">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <!-- 控制按钮 -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>

    <div class="container-fluid" style="margin-top: 10px">
        <div style="font-size: 20px; float: left;" id="QuestionSet">试题集</div>
        <form class="form-inline"  id="QuestionForm" >
            <div id="QuestionDiv">
                <table class="table table-hover" style="margin-top: 50px;">
                    <thead class="font_size" style="align-content: center">
                    <td>单元</td>
                    <td>题号</td>
                    <td>类型</td>
                    <td>问题描述</td>
                    <td>选项A</td>
                    <td>选项B</td>
                    <td>选项C</td>
                    <td>选项D</td>
                    <td>教师编号</td>
                    </thead>
                    <tbody style="align-content: center" id="QuestionTable">
                    <tr>
                        <c:forEach items="${questionList}" var="questions" varStatus="status">
                        <td>${questions.chapter}</td>
                        <td>${questions.que_id}</td>
                        <td>${questions.type}</td>
                        <td>
                            <p>${questions.que_describe}</p>
                            <object data="${questions.file_path}" style="height: 80px" alt=""></object>
                        </td>
                        <td>${questions.answer_A}</td>
                        <td>${questions.answer_B}</td>
                        <td>${questions.answer_C}</td>
                        <td>${questions.answer_D}</td>
                        <td>${questions.teacher_id}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    <hr>

    <footer class="container-fluid">
        <div class="container-fluid">
            <!--版权声明-->
            <div class="row p_footer">
                <p>Java程序设计</p>
                <p>版权所有&nbsp;Copyright&nbsp;&nbsp;2019-2020&nbsp;All Rights Reserved</p>
            </div>
        </div>
    </footer>
</body>
</html>

