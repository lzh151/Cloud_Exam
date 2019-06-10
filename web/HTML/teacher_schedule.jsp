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
        
        function judge(exam_name,stu_id,chapter,que_id,teacher_id,index) {
            var Element = document.getElementsByName("judgeText")[index - 1];
            if (Element != null) {
                var text = Element.value;
                location.href="${pageContext.request.contextPath}/addJudgeTextServlet?exam_name=" + exam_name + "&stu_id=" + stu_id + "&chapter=" + chapter + "&que_id=" + que_id + "&teacher_id=" + teacher_id + "&text=" + text + "&stu_name=${stu_name}";
            }
            else{
                confirm("请输入数据!");
            }
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
                    <li><a href="teacher_operation.jsp">个人提交试题集</a></li>
                    <li><a href="teacher_operation.jsp">学生列表</a></li>
                    <li><a href="teacher_operation.jsp">组卷系统</a></li>
                    <li><a href="teacher_operation.jsp">答题进度</a></li>
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
                        <a href="teacher_operation.jsp">${teacher.name}老师</a>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:quitState(${teacher.id})">注销</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<%--答题进度--%>
<div class="container" style="margin-top: 10px">
    <div style="font-size: 20px; float: left;" id="examSchedule">答题进度</div>
    <form class="form-inline"  id="Schedule" action="${pageContext.request.contextPath}/createExamServlet" method="post">
        <div class="form-group" style="margin-top: 5px; float: right; margin-right: 60px;">
            <label>${stu_id} ${stu_name}同学</label>
        </div>
        <div id="ScheduleList">
            <table class="table table-hover" style="margin-top: 50px;">
                <thead class="font_size" style="align-content: center">
                <td>试卷名称</td>
                <td>单元</td>
                <td>题号</td>
                <td>作答答案</td>
                <td>参考答案</td>
                <td>批改记录</td>
                <td>批改</td>
                </thead>
                <tbody style="align-content: center" id="ScheduleTable">
                    <c:forEach items="${ScheduleList}" var="Schedules" varStatus="status">
                    <tr>
                    <td>${Schedules.exam_name}</td>
                    <td>${Schedules.sel_chapter}</td>
                    <td>${Schedules.sel_que_id}</td>
                    <td>${Schedules.answer}</td>
                    <td>${Schedules.answer_correct}</td>
                    <td>${Schedules.remark}</td>
                    <td>
                        <label><input type="text" name="judgeText"></label>
                        <a class="btn btn-default" href="javascript:judge(&quot ${Schedules.exam_name}&quot,${Schedules.stu_id},${Schedules.sel_chapter},${Schedules.sel_que_id},${Schedules.teacher_id},${status.count});">批改</a></td>
                    </tr>
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
<%--        $("#examSchedule").click(function(){--%>
<%--            $("#Schedule").fadeToggle();--%>
<%--        });--%>
<%--    });--%>
<%--    $("#Schedule").hide();--%>
<%--</script>--%>

<!--版权声明-->
<footer class="container-fluid">
    <div class="container-fluid">
        <div class="row p_footer">
            <p>Web技术</p>
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