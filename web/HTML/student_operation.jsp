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
        function alterAnswer(exam_name,stu_id,chapter,que_id,index) {
            var Text = document.getElementsByName("Text")[index];
            var option = "";
            for(var i = 0; i <= 3; i++){
                if(document.getElementsByName("selectQuestion")[(index) * 4 + i].checked){
                    switch (i) {
                        case 0:option = "A";break;
                        case 1:option = "B";break;
                        case 2:option = "C";break;
                        case 3:option = "D";break;
                    }
                }
            }
            var judge = "";
            for(i = 0; i <= 1; i++){
                if(document.getElementsByName("judgeQuestion")[(index) * 2 + i].checked){
                    switch (i) {
                        case 0:judge = "正确";break;
                        case 1:judge = "错误";break;
                    }
                }
            }
            if (Text.value != null || option !== "" || judge !== "") {
                var text = Text.value + option + judge;
                location.href="${pageContext.request.contextPath}/alterStudentAnswerServlet?exam_name=" + exam_name + "&stu_id=" + stu_id + "&chapter=" + chapter + "&que_id=" + que_id + "&text=" + text + "&stu_name=${name}";
            }
            else{
                confirm("请作答!");
            }
        }
        function intelligentCreate() {
            if(document.getElementById("number").value === ""){
                confirm("请输入数据!");
            }
            else {
                location.href="${pageContext.request.contextPath}/studentIntelligentCreateServlet?number=" + document.getElementById("number").value + "&student_id=${id}" + "&student_name=${name}";
            }
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
                    <li><a href="#examLabel">试卷集</a></li>
                    <li><a href="#mistakeSet">错题集</a></li>
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
                <c:forEach items="${examList}" var="exams">
                <tr>
                    <td>${exams.exam_name}</td>
                    <td><a class="btn btn-default" href="javascript:viewExam(&quot ${exams.exam_name}&quot);">查看</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
    <div class="input-group" style="width: 30%">
        <input type="text" class="form-control" id="number" placeholder="题量数">
        <span class="input-group-btn">
        <a class="btn btn-default" href="javascript:intelligentCreate()" id="create">智能生成试卷</a>
        </span>
    </div>
</div>
<hr>

<%--答题进度--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="mistakeSet">错题集</div>
    <form class="form-inline" id="mistakeForm" >
        <div id="mistakeDiv">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>试卷名称</td>
                <td>单元</td>
                <td>题号</td>
                <td>类型</td>
                <td>问题描述</td>
                <td>批改记录</td>
                <td>作答答案</td>
                <td>作答</td>
                </thead>
                <tbody style="align-content: center" id="mistakeTable">
                <tr>
                    <c:forEach items="${mistakeList}" var="mistakes" varStatus="status">
                    <td>${mistakes.exam_name}</td>
                    <td>${mistakes.chapter}</td>
                    <td>${mistakes.que_id}</td>
                    <td name="type">${mistakes.type}</td>
                    <td>
                        <p>${mistakes.que_describe}</p>
                        <object data="${mistakes.file_path}" style="height: 80px" alt=""></object>
                    </td>
                    <td>${mistakes.remark}</td>
                    <td>${mistakes.answer}</td>
                    <td><div name="answer_select"><input type="radio" name="selectQuestion" id="selectQuestion" style="margin-left: 12px;" value="A"> A. ${mistakes.answer_A}<br>
                            <input type="radio" name="selectQuestion" style="margin-left: 12px;" value="B"> B. ${mistakes.answer_B}<br>
                            <input type="radio" name="selectQuestion" style="margin-left: 12px;" value="C"> C. ${mistakes.answer_C}<br>
                            <input type="radio" name="selectQuestion" style="margin-left: 12px;" value="D"> D. ${mistakes.answer_D}<br>
                        </div>
                        <div name="answer_judge"><input type="radio" name="judgeQuestion" id="judgeQuestion" style="margin-left: 12px;" value="正确"> 正确 <br>
                            <input type="radio" name="judgeQuestion" style="margin-left: 12px;" value="错误"> 错误 <br>
                        </div>
                        <div name="answer_text"><input type="text" name="Text" id="Text" placeholder="请勿填写特殊字符"></div>
                        <a class="btn btn-default" type="submit" href="javascript:alterAnswer(&quot ${mistakes.exam_name}&quot,${mistakes.stu_id},${mistakes.chapter},${mistakes.que_id},${status.index});">提交</a>
                    </td>
                </tr>
                <script>
                    if(document.getElementsByName("type")[${status.index}].textContent === "选择题"){
                        document.getElementsByName("answer_text")[${status.index}].style.display = "none";
                        document.getElementsByName("answer_judge")[${status.index}].style.display = "none";
                        document.getElementsByName("answer_select")[${status.index}].style.display = "";
                    }
                    else if(document.getElementsByName("type")[${status.index}].textContent === "判断题"){
                        document.getElementsByName("answer_text")[${status.index}].style.display = "none";
                        document.getElementsByName("answer_judge")[${status.index}].style.display = "";
                        document.getElementsByName("answer_select")[${status.index}].style.display = "none";
                    }
                    else{
                        document.getElementsByName("answer_text")[${status.index}].style.display = "";
                        document.getElementsByName("answer_judge")[${status.index}].style.display = "none";
                        document.getElementsByName("answer_select")[${status.index}].style.display = "none";
                    }
                </script>
                </c:forEach>
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

<script type="text/javascript">
    //~ heart
    !function(c,d,g){
        function f()
        {
            for(var a=0;a<b.length;a++)
                0>=b[a].alpha?(d.body.removeChild(b[a].el),b.splice(a,1)):(b[a].y--,b[a].scale+=.004,b[a].alpha-=.013,b[a].el.style.cssText="left:"+b[a].x+"px;top:"+b[a].y+"px;opacity:"+b[a].alpha+";transform:scale("+b[a].scale+","+b[a].scale+") rotate(45deg);background:"+b[a].color+";z-index:99999");
            requestAnimationFrame(f)
        }
        var b=[];
        c.requestAnimationFrame=c.requestAnimationFrame||c.webkitRequestAnimationFrame||c.mozRequestAnimationFrame||c.oRequestAnimationFrame|| c.msRequestAnimationFrame||function(a){setTimeout(a,1E3/60)};
        (function(a)
        {
            var b=d.createElement("style");b.type="text/css";
            try{
                b.appendChild(d.createTextNode(a))
            }
            catch(e)
            {
                b.styleSheet.cssText=a
            }
            d.getElementsByTagName("head")[0].appendChild(b)
        })(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: fixed;}.heart:after{top: -5px;}.heart:before{left: -5px;}");
        (function()
        {
            var a="function"==typeof c.onclick&&c.onclick;c.onclick = function(c)
        {
            a&&a();
            var e=d.createElement("div");
            e.className="heart";
            b.push({el:e,x:c.clientX-5,y:c.clientY-5,scale:1,alpha:1,color:"rgb("+~~(255*Math.random())+","+~~(255*Math.random())+","+~~(255*Math.random())+")"});
            d.body.appendChild(e)}
        })();f()
    }(window,document);
</script>
<script async type="text/javascript" size="90" alpha="0.2" zIndex="0" src="../js/ribbon.js"></script>
</body>
</html>