<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>个人空间-教师</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>

    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>

    <![endif]-->

    <script>
        function deleteQuestion(chapter,que_id,teacher_id) {
            if(confirm("确定删除吗?")){
                location.href="${pageContext.request.contextPath}/deleteQuestionServlet?chapter=" + chapter + "&que_id=" + que_id + "&teacher_id=" + teacher_id;
            }
        }

        function deleteStudent(student_id,teacher_id) {
            if(confirm("确定删除吗?")){
                location.href="${pageContext.request.contextPath}/deleteTeacherIdServlet?student_id=" + student_id + "&teacher_id=" + teacher_id;
            }
        }

        function quitState(teacher_id) {
            location.href="${pageContext.request.contextPath}/teacherQuitServlet?teacher_id=" + teacher_id;
        }

        function selectSchedule(exam_name,stu_id,stu_name) {
            location.href="${pageContext.request.contextPath}/teacherSelectScheduleServlet?exam_name=" + exam_name + "&stu_id=" + stu_id + "&stu_name=" + stu_name;
        }

        function record() {
            location.href="${pageContext.request.contextPath}/recordVoiceServlet";
        }
        function deleteSchedule(exam_name,stu_id,stu_name,teacher_id) {
            if(confirm("确定删除吗?")){
                location.href="${pageContext.request.contextPath}/deleteScheduleServlet?exam_name=" + exam_name + "&stu_id=" + stu_id + "&stu_name=" + stu_name + "&teacher_id=" + teacher_id;
            }
        }

        function selectQuestion() {
            var chapter = document.getElementById("que_chapter").value;
            var type = document.getElementById("que_type").value;
            if(chapter === ""){
                chapter = 0;
            }
            if(type === ""){
                type = "type";
            }
            location.href="${pageContext.request.contextPath}/selectQuestionServlet?chapter=" + chapter + "&type=" + type + "&teacher_id=" + ${teacher.id};
        }

    </script>

    <link href="../css/form_self.css" rel="stylesheet">
    <style>
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
                    <li><a href="#searchTrigger">个人提交试题集</a></li>
                    <li><a href="#studentList">学生列表</a></li>
                    <li><a href="#organizeExam">组卷系统</a></li>
                    <li><a href="#examSchedule">答题进度</a></li>
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
                        <a href="#">${teacher.name}老师</a>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:quitState(${teacher.id})">注销</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<!--添加试题-->
<div class="container" style="margin-top: 10px" >
    <div style="font-size: 20px;" id="addTrigger">添加试题</div>
    <div id="addQuestion" align="center">
        <form action="${pageContext.request.contextPath}/addQuestionServlet" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td class="td_left"><label for="chapter">单元:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="chapter" id="chapter"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="que_id">题号:</label></td>
                    <td class="td_right"><input type="que_id" class="form-control" name="que_id" id="que_id"></td>
                </tr>

                <tr>
                    <td class="td_left"><label>类型:</label></td>
                    <td class="td_right">
                        <label>
                            <input type="radio" name="type" value="选择题" checked="checked">
                        </label> 选择题
                        <label>
                            <input type="radio" name="type" value="非选题">
                        </label> 非选题
                    </td>
                </tr>

                <tr>
                    <td class="td_left"><label for="que_describe">问题描述:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="que_describe" id="que_describe" value="${que_describe}"></td>
                    <td><input type="checkbox" id="fileCheckBox" hidden="hidden" checked="checked" name="fileCheckBox" value="unchecked"></td>
                    <td><a class="btn btn-default" style="margin-left: 20px" id="record">语音录入</a></td>
                    <td class="td_left"><button class="btn btn-default" type="button" id="fileLabel">添加附件</button></td>
                    <td class="td_right"><input type="file" class="form-control" name="file_path" id="file_path"></td>
                </tr>

                <tr id="form_answer_A">
                    <td class="td_left"><label for="answer_A">选项A:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="answer_A" id="answer_A"></td>
                </tr>

                <tr id="form_answer_B">
                    <td class="td_left"><label for="answer_B">选项B:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="answer_B" id="answer_B"></td>
                </tr>

                <tr id="form_answer_C">
                    <td class="td_left"><label for="answer_C">选项C:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="answer_C" id="answer_C"></td>
                </tr>

                <tr id="form_answer_D">
                    <td class="td_left"><label for="answer_D">选项D:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="answer_D" id="answer_D"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="correct_answer">正确答案:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="correct_answer" id="correct_answer"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="teacher_id">教师编号:</label></td>
                    <td class="td_right"><input type="text" class="form-control" name="teacher_id" id="teacher_id" value="${teacher.id}" readonly="readonly"></td>
                </tr>

                <tr align="center">
                    <td colspan="2"><button class="btn btn-default" style="margin-top: 30px" type="submit">添加</button>
                </tr>
            </table>
        </form>
    </div>
</div>

<!--选择题判断-->
<script>
    var count = 10;
    var element = document.getElementById("record");
    function time(){
        var interval = setInterval(
            function () {
                if (count !== 0){
                    element.innerText = (count).toString();
                    count--;
                }
                else{
                    clearInterval(interval);
                    count = 10;
                    element.innerText = "语音录入";
                }
            }, 1000);
    }

    document.getElementById("record").onclick = function () {
        if(confirm("点击确认开始录制(10秒)")){
            document.getElementById("que_describe").value = "";
            record();
            time();
        }
    };

    $(document).ready(function(){
        $("#fileLabel").click(function(){
            $("#file_path").fadeToggle();
        });
    });
    $("#file_path").hide();

    document.getElementById("fileLabel").onclick = function () {
        if (document.getElementById("fileCheckBox").value === "unchecked"){
            document.getElementById("fileCheckBox").value = "checked";
        }
        else {
            document.getElementById("fileCheckBox").value = "unchecked";
        }
    }

    var elementsByNameElement = document.getElementsByName("type");
    window.onload = function() {
        if(elementsByNameElement[0].checked === true){
            document.getElementById("form_answer_A").style.display = "";
            document.getElementById("form_answer_B").style.display = "";
            document.getElementById("form_answer_C").style.display = "";
            document.getElementById("form_answer_D").style.display = "";
        }
        else{
            document.getElementById("form_answer_A").style.display = "none";
            document.getElementById("form_answer_B").style.display = "none";
            document.getElementById("form_answer_C").style.display = "none";
            document.getElementById("form_answer_D").style.display = "none";
        }
    };
    elementsByNameElement[0].onclick = function () {
        document.getElementById("form_answer_A").style.display = "";
        document.getElementById("form_answer_B").style.display = "";
        document.getElementById("form_answer_C").style.display = "";
        document.getElementById("form_answer_D").style.display = "";
    };
    elementsByNameElement[1].onclick = function () {
        document.getElementById("form_answer_A").style.display = "none";
        document.getElementById("form_answer_B").style.display = "none";
        document.getElementById("form_answer_C").style.display = "none";
        document.getElementById("form_answer_D").style.display = "none";
    }
</script>

<hr>

<!--查询试题-->
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="searchTrigger">个人提交试题集</div>
    <div id="searchQuestion">
        <!-- 筛选栏 -->
        <form class="form-inline" style="margin-top: 5px; float: right; margin-right: 60px;">
            <div class="form-group">
                <label for="que_chapter">单元</label>
                <input type="text" class="form-control" id="que_chapter">
            </div>
            <div class="form-group">
                <label for="que_type">类型</label>
                <input type="text" class="form-control" id="que_type">
            </div>
            <a class="btn btn-default" href="javascript:selectQuestion()">筛选</a>
        </form>

        <form action="${pageContext.request.contextPath}/addQueToSysServlet" method="post">
            <!-- 试题集表格 -->
            <table class="table table-hover" style="margin-top: 5px; align-content: center">
                <thead class="font_size" style="align-content: center">
                <td><label for="questionList"></label><input type="checkbox" id="questionList"></td>
                <td>单元</td>
                <td>题号</td>
                <td>类型</td>
                <td>问题描述</td>
                <td>选项A</td>
                <td>选项B</td>
                <td>选项C</td>
                <td>选项D</td>
                <td>正确答案</td>
                <td>操作</td>
                </thead>
                <tbody style="align-content: center">
                    <c:forEach items="${question}" var="questions">
                        <tr>
                            <td><label>
                                <input type="checkbox" name="questionId" value="${questions.chapter},${questions.que_id}">
                            </label></td>
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
                            <td>${questions.correct_answer}</td>
                            <td><a class="btn btn-default btn-sm" href="#">修改</a> <a class="btn btn-default btn-sm" href="javascript:deleteQuestion(${questions.chapter},${questions.que_id},${questions.teacher_id});">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-default btn-sm">添加被选中试题至组卷系统</button>
        </form>
    </div>
</div>
<hr>

<script>
    document.getElementById("questionList").onclick = function(){
        var cbs = document.getElementsByName("questionId");
        for (var i = 0; i < cbs.length; i++) {
            cbs[i].checked = this.checked;
        }
    }
</script>

<%--学生列表--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="studentList">学生列表</div>
    <div id="List">
        <form action="${pageContext.request.contextPath}/addStuToSysServlet" method="post">
            <!-- 试题集表格 -->
            <table class="table table-hover" style="margin-top: 5px; align-content: center">
                <thead class="font_size" style="align-content: center">
                <td><label for="totalStudentList"></label><input type="checkbox" id="totalStudentList"></td>
                <td>学号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>邮箱</td>
                <td>操作</td>
                </thead>
                <tbody style="align-content: center">
                    <c:forEach items="${student}" var="students">
                    <tr>
                        <td><label>
                            <input type="checkbox" name="totalStudent" value="${students.id},${students.name}">
                        </label>
                        </td>
                        <td>${students.id}</td>
                        <td>${students.name}</td>
                        <td>${students.gender}</td>
                        <td>${students.email}</td>
                        <td><a class="btn btn-default btn-sm" href="javascript:deleteStudent(${students.id},${students.teacher_id});">删除</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-default btn-sm">添加被选中学生至组卷系统</button>
        </form>
    </div>
</div>
<hr>

<script>
    document.getElementById("totalStudentList").onclick = function(){
        var cbs = document.getElementsByName("totalStudent");
        for (var i = 0; i < cbs.length; i++) {
            cbs[i].checked = this.checked;
        }
    }
</script>

<%--组卷模块--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="organizeExam">组卷系统</div>
    <form class="form-inline"  id="intelligentProduce" action="${pageContext.request.contextPath}/createExamServlet" method="post">
        <div class="form-group" style="margin-top: 5px; float: right; margin-right: 60px;">
            <label for="exampleInput">试卷名称</label>
            <input type="text" class="form-control" id="exampleInput" name="exam_name">
            <input type="hidden" name="teacherId" value="${teacher.id}">
            <input type="submit" class="btn btn-default" value="确认组卷">
        </div>
        <div id="ExamList" style="float: left">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>单元</td>
                <td>题号</td>
                </thead>
                <tbody style="align-content: center" id="ExamTable">
                <tr>
                    <c:forEach items="${QueTable}" var="QueTables">
                    <tr>
                        <input type="hidden" name="que_info" value="${QueTables.chapter},${QueTables.que_id}">
                        <td>${QueTables.chapter}</td>
                        <td>${QueTables.que_id}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="StuList" style="float: right;">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>学号</td>
                <td>姓名</td>
                </thead>
                <tbody style="align-content: center" id="StuTable">
                <tr>
                    <c:forEach items="${StuTable}" var="StuTables">
                    <tr>
                        <input type="hidden" name="stu_info" value="${StuTables.id},${StuTables.name}">
                        <td>${StuTables.id}</td>
                        <td>${StuTables.name}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</div>
<hr>

<%--模块隐藏--%>
<script>
    $(document).ready(function(){
        $("#addTrigger").click(function(){
            $("#addQuestion").fadeToggle();
        });
        $("#searchTrigger").click(function(){
            $("#searchQuestion").fadeToggle();
        });
        $("#studentList").click(function(){
            $("#List").fadeToggle();
        });
        $("#organizeExam").click(function(){
            $("#intelligentProduce").fadeToggle();
        });
    });
    // $("#searchQuestion").hide();
    // $("#List").hide();
    // $("#intelligentProduce").hide();
</script>

<%--答题进度--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="examSchedule">答题进度</div>
    <form class="form-inline" action="${pageContext.request.contextPath}/teacherSelectScheduleServlet" id="Schedule">
        <div id="ScheduleList">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>试卷名称</td>
                <td>学号</td>
                <td>姓名</td>
                <td>操作</td>
                </thead>
                <tbody style="align-content: center" id="ScheduleTable">
                    <c:forEach items="${Schedule}" var="Schedules">
                    <tr>
                    <td>${Schedules.exam_name}</td>
                    <td>${Schedules.stu_id}</td>
                    <td>${Schedules.stu_name}</td>
                    <td><a class="btn btn-default btn-sm" href="javascript:selectSchedule(&quot${Schedules.exam_name}&quot,${Schedules.stu_id},&quot ${Schedules.stu_name}&quot);">查看</a><a class="btn btn-default btn-sm" href="javascript:deleteSchedule(&quot${Schedules.exam_name}&quot,${Schedules.stu_id},&quot ${Schedules.stu_name}&quot,${teacher.id});">删除</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</div>
<hr>

<%--模块隐藏--%>
<script>
    $(document).ready(function(){
        $("#examSchedule").click(function(){
            $("#Schedule").fadeToggle();
        });
    });
    // $("#Schedule").hide();
</script>

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