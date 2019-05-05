<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<title>人员管理</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome-icons -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link
	href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i"
	rel="stylesheet">

</head>

<body>
    <!-- banner -->
    <div class="agileits_w3layouts_banner_nav">
        <%@include file="导航栏-管理.jsp"%>

        <div class="clearfix"></div>
    </div>
    <!-- Modal1 -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">登录</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <input type="email" name="email" placeholder="E-mail"
                                    required=""> <input type="password" name="password"
                                    placeholder="Password" required="">
                                <div class="tp">
                                    <input type="submit" value="Sign In">
                                </div>
                            </form>
                        </div>
                        <div class="login-social-grids">
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-rss"></i></a></li>
                            </ul>
                        </div>
                        <p>
                            <a href="#" data-toggle="modal" data-target="#myModal3">
                                没有账号?</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //Modal1 -->
    <!-- Modal2 -->
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">注册</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <input type="text" name="name" placeholder="Username"
                                    required=""> <input type="email" name="email"
                                    placeholder="Email" required=""> <input type="password"
                                    name="password" id="password1" placeholder="Password"
                                    required=""> <input type="password" name="password"
                                    id="password2" placeholder="Confirm Password" required="">

                                <input type="submit" value="Sign Up">
                            </form>
                        </div>
                        <p>
                            <a href="#"> By clicking register, I agree to your terms</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //Modal2 -->
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">增加维修人员</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <input type="text" name="name" placeholder="姓名"
                                    required="required"> <input type="text" name="tel"
                                    placeholder="号码" required="required"> <input
                                    type="password" name="password" id="password1" placeholder="密码"
                                    required="required"> <input type="password"
                                    name="password" id="password2" placeholder="确认密码"
                                    required="required"> <input type="text" name="addr"
                                    placeholder="地址" required="required">

                                <!--<input id="access_level" type="text" list="accesslist" placeholder="权限等级">
                                                                                    <datalist id="accesslist">
                                                                                    <option>维修人员</option>
                                                                                    <option>女</option>
                                                                                    </datalist> -->

                                <input type="submit" value="确定">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">修改维修人员资料</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <input type="text" name="name" placeholder="姓名"
                                    required="required"> <input type="text" name="tel"
                                    placeholder="号码" required="required"> <input
                                    type="password" name="password" id="password1" placeholder="密码"
                                    required="required"> <input type="password"
                                    name="password" id="password2" placeholder="确认密码"
                                    required="required"> <input type="text" name="addr"
                                    placeholder="地址" required="required">

                                <!--<input id="access_level" type="text" list="accesslist" placeholder="权限等级">
                                                                                    <datalist id="accesslist">
                                                                                    <option>维修人员</option>
                                                                                    <option>女</option>
                                                                                    </datalist> -->

                                <input type="submit" value="确定">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal6" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">选择筛选条件</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <label><input name="Never" type="radio" value="" />从未预约用户
                                </label> <label><input name="Long" type="radio" value="" />上次预约在2年前用户</label>
                                <label for="inputText" class="col-sm-2 control-label">清理指定账户</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="id"
                                        placeholder="请输入id">
                                </div>
                                <input type="submit" value="检索">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal5" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                    <div class="signin-form profile">
                        <h3 class="agileinfo_sign">是否删除该资料？</h3>
                        <div class="login-form">
                            <form action="#" method="post">
                                <input type="submit" value="确定">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /agile_inner_banner_info -->
    <div class="agile_inner_banner_info">
        <h2>维修人员</h2>
    </div>
    <a href="addrepair_list" data-toggle="modal" class="view resw3" data-target="#myModal1">增加维修人员</a>
    <a href="addrepair_list" data-toggle="modal" class="view resw3" data-target="#myModal6">清理账户</a>
    <!-- //agile_inner_banner_info -->
    <!-- typo -->
    <div id="fh5co-pricing">
        <div class="container">
            <div class="row">
                <div class="pricing">
                    <div class="col-md-3 animate-box">
                        <div class="price-box">
                            <h2 class="pricing-plan">维修人员</h2>

                            <ul class="classes">
                                <li>姓名：</li>
                                <li class="color">张三</li>
                                <li>号码：</li>
                                <li class="color">16547382345</li>
                                <li>地址：</li>
                                <li class="color">6#532</li>
                                <li>邮箱：</li>
                                <li class="color">yhsng@gmail.com</li>
                            </ul>
                            <a href="#" class="btn btn-select-plan btn-sm"
                                data-toggle="modal" data-target="#myModal4">修改</a> <a href="#"
                                class="btn btn-select-plan btn-sm" data-toggle="modal"
                                data-target="#myModal5">删除</a>
                        </div>
                    </div>

                    <div class="col-md-3 animate-box">
                        <div class="price-box">
                            <h2 class="pricing-plan">维修人员</h2>

                            <ul class="classes">
                                <li>姓名：</li>
                                <li class="color">张三</li>
                                <li>号码：</li>
                                <li class="color">16547382345</li>
                                <li>地址：</li>
                                <li class="color">6#532</li>
                                <li>邮箱：</li>
                                <li class="color">yhsng@gmail.com</li>
                            </ul>
                            <a href="#" class="btn btn-select-plan btn-sm">修改</a> <a href="#"
                                class="btn btn-select-plan btn-sm">删除</a>
                        </div>
                    </div>

                    <div class="col-md-3 animate-box">
                        <div class="price-box">
                            <h2 class="pricing-plan">维修人员</h2>

                            <ul class="classes">
                                <li>姓名：</li>
                                <li class="color">张三</li>
                                <li>号码：</li>
                                <li class="color">16547382345</li>
                                <li>地址：</li>
                                <li class="color">6#532</li>
                                <li>邮箱：</li>
                                <li class="color">yhsng@gmail.com</li>
                            </ul>
                            <a href="#" class="btn btn-select-plan btn-sm">修改</a> <a href="#"
                                class="btn btn-select-plan btn-sm">删除</a>
                        </div>
                    </div>

                    <div class="col-md-3 animate-box">
                        <div class="price-box">
                            <h2 class="pricing-plan">维修人员</h2>

                            <ul class="classes">
                                <li>姓名：</li>
                                <li class="color">张三</li>
                                <li>号码：</li>
                                <li class="color">16547382345</li>
                                <li>地址：</li>
                                <li class="color">6#532</li>
                                <li>邮箱：</li>
                                <li class="color">yhsng@gmail.com</li>
                            </ul>
                            <a href="#" class="btn btn-select-plan btn-sm">修改</a> <a href="#"
                                class="btn btn-select-plan btn-sm">删除</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //typo -->
    <!-- footer -->
    <!-- //footer -->
    <!-- menu -->
    <!-- js -->
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
    <!-- //js -->
    <!-- password-script -->
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }
        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
            //empty string means no validation error
        }
        //管理员删除时弹出警告
        function showDelMessage(){
        }
    </script>
    <!-- //password-script -->

    <!-- for bootstrap working -->
    <script src="js/bootstrap.js"></script>
    <!-- //for bootstrap working -->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){     
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
    <!-- start-smoth-scrolling -->
    <!-- here stars scrolling icon -->
    <script type="text/javascript">
        $(document).ready(function() {
            /*
                var defaults = {
                containerID: 'toTop', // fading element id
                containerHoverID: 'toTopHover', // fading element hover id
                scrollSpeed: 1200,
                easingType: 'linear' 
                };
            */
                                
            $().UItoTop({ easingType: 'easeOutQuart' });
                                
            });
    </script>
    <!-- //here ends scrolling icon -->
<%@include file="动态js代码.jsp"%>
<%@include file="注册弹窗.jsp"%>
<%@include file="个人信息修改弹窗.jsp"%>
<%@include file="登录弹窗.jsp"%>
<%@include file="退出登录弹窗.jsp"%>
</body>
</html>