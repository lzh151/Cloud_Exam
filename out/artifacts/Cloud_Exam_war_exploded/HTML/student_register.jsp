<%@ page contentType="text/html;charset=UTF-8" %>

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
        body{
            background: url("../IMG/DSC_4236.jpg");
            background-size: 100%;
        }
        #login_theme{
            color: #46b8da;
            font-family: "Heiti SC", serif;
            font-size: 35px;
            border-bottom: #f5e79e solid;
        }
        #login_theme_e{
            color: #eea236;
            font-family: Cochin, serif;
            font-size: 25px;
        }
        #center{
            width: 900px;
            height: 600px;
            border: 5px solid #EEEEEE;
            background-color: white;
            margin: 150px auto auto;
        }
        .login_left{
            margin-top: 15px;
            padding: 30px;
            /*
            border: 1px red solid;
            */
        }
        .login_center{
            margin-top: 20px;
            padding: 30px;
        }
        .login_right{
            margin-top: 25px;
            padding: 30px;
        }

        .td_left{
            width: 100px;
            text-align: right;
            height: 45px;
        }
        .td_right{
            padding-left: 20px ;
        }
        #register{
            color: #adadad;
            font-family: "Heiti SC", serif;
            font-size: 12px;
        }
        .p_footer{
            text-align: center;
        }
    </style>
    <script>
        window.onload = function () {
            var img = document.getElementById("codeImg");
            img.onclick = function () {
                var date = new Date().getTime();
                img.src = "${pageContext.request.contextPath}/checkCodeServlet?" + date;
            }
        }
    </script>
</head>

<body>

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${pageContext.request.contextPath}/WEB-INF/libs/jquery-3.4.0/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/WEB-INF/libs/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <div class="container-fluid">
        <div class="row">
            <div id="center">
                <!--排版信息-->
                <div class="col-md-3 login_left">
                    <p id="login_theme">云课堂<span id="register">&nbsp;&nbsp;学生注册</span></p>
                    <p id="login_theme_e">Cloud Class</p>
                </div>
                <!--定义表单 form-->
                <div class="col-md-6 login_center">
                        <form action="${pageContext.request.contextPath}/addStudentServlet" method="post">
                            <table>
                                <tr>
                                    <td class="td_left"><label for="username">用户名:</label></td>
                                    <td class="td_right"><input type="text" class="form-control" name="username" id="username" placeholder="Username"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="password">密码:</label></td>
                                    <td class="td_right"><input type="password" class="form-control" name="password" id="password" placeholder="Password"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="email">邮箱:</label></td>
                                    <td class="td_right"><input type="email" class="form-control" name="email" id="email" placeholder="Email"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="id">学号:</label></td>
                                    <td class="td_right"><input type="text" class="form-control" name="id" id="id" placeholder="Id"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="name">姓名:</label></td>
                                    <td class="td_right"><input type="text" class="form-control" name="name" id="name" placeholder="Name"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label>性别:</label></td>
                                    <td class="td_right">
                                        <label>
                                            <input type="radio" name="gender" value="male">
                                        </label> 男
                                        <label>
                                            <input type="radio" name="gender" value="female">
                                        </label> 女
                                    </td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="birthday">出生日期:</label></td>
                                    <td class="td_right"><input type="date" class="form-control" name="birthday" id="birthday" placeholder="请输入出生日期"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="teacher_id">导师工号:</label></td>
                                    <td class="td_right"><input type="text" class="form-control" name="teacher_id" id="teacher_id" placeholder="TeacherId"></td>
                                </tr>

                                <tr>
                                    <td class="td_left"><label for="code">验证码:</label></td>
                                    <td class="td_right"><input type="text" class="form-control" name="code" id="code" style="width: 60%;float: left;"> <img src = "${pageContext.request.contextPath}/checkCodeServlet" class="img-rounded" alt="" style="width: 30%;float: right" id="codeImg"> </td>
                                </tr>

                                <tr align="center">
                                    <td colspan="2"><button class="btn btn-default" style="margin-top: 30px" type="submit">注册</button>
                                </tr>
                            </table>
                        </form>
                    <div class="alter alert-warning alert-dismissible" role="alert">
                        <strong>${register_msg}</strong>
                    </div>
                </div>
                <!--快捷登录-->
                <div class="col-md-3 login_right">
                    <p>已有账号？<a href="${pageContext.request.contextPath}/HTML/student_login.jsp">立即登录</a></p>
                    <p>教师<a href="${pageContext.request.contextPath}/HTML/teacher_register.jsp">注册？</a></p>
                    <p>返回<a href="${pageContext.request.contextPath}/index.jsp">首页</a></p>
                </div>
            </div>
        </div>
    </div>

    <footer class="container-fluid">
        <div class="container-fluid">
            <!--版权声明-->
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

</body>
</html>