
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.*,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="注册弹窗.jsp"%>
<%@include file="个人信息修改弹窗.jsp"%>
<%@include file="登录弹窗.jsp"%>
<%@include file="退出登录弹窗.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <title>预约人员</title>

    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //custom-theme -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/slicebox.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- font-awesome-icons -->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- //font-awesome-icons -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css' />
    <link href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i" rel="stylesheet" />
</head>
<body>
<%@include file="导航栏.jsp"%>

<!-- 主体内容：开始 -->
<div class="services">
    <div class="container">
        <ul id="formTab" class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="hvr-underline-from-center" data-toggle="tab" href="#field-service">检索预约人员名单</a>
            </li>
            <li>
                <a class="hvr-underline-from-center" data-toggle="tab" href="#door-to-door-service">导入预约人员名单</a>
            </li>
        </ul>

        <div class="tab-content">
            <!-- 预约人员的表单：开始 -->

            <!-- 搜索框 -->
            <div id="field-search" class="container tab-pane active"><br />
                <form action="ViewUserServlet?method=list" method="post">

                    <div class="input-group col-md-12"
                         style="margin-top:0px;positon:relative; padding-left:1em;padding-right:1em;">
                        <input type="text" class="form-control"placeholder="请输入要搜索的用户名"  >

                        <span class="input-group-btn">

                        <button class="btn btn-info btn-search" style="margin-left:3px">搜索</button>
                        </span>
                    </div>


                    <!-- 类型选择 -->
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="type-select">预约类型</label>

                        </div>
                        <div class="col-sm-9">
                            <select id="type-select" class="form-control">
                               <!-- 这里装入预约类型列表 -->
                                        <option value="0">场次预约</option>
                                        <option value="1">上门服务</option>
                                        <option value="2" selected="selected">全部</option>
                            </select>
                        </div>
                    </div>

                    <!-- 场次选择 -->
                    <div class="form-group">
                        <label for="session-select" class="col-sm-3">预约场次</label>
                        <div class="col-sm-9">
                            <select id="session-select" class="form-control">
                               <!-- 这里装入场次时间信息 -->
                                        <c:forEach items="${activities}" var="activity">
                                            <option value="${activity.id}">${activity.time}，地点：${activity.place}</option>
                                        </c:forEach>
                                        <option value="-1">全部预约场次</option>
                                        <option value="-2">上门服务</option>
                            </select>
                        </div>
                    </div>



                    <!-- 是否已完成 -->
                    <div class="form-group">
                        <label for="finished-select" class="col-sm-3">是否已完成</label>
                        <div class="col-sm-9">
                            <select id="finished-select" class="form-control">
                               <option value="0">未受理</option>
                                        <option value="1">已受理未完成</option>
                                        <option value="2">已完成</option>
                                        <option value="3" selected="selected">全部</option>
                            </select>
                        </div>
                    </div>





                    <!-- 搜索按钮 -->
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <input type="submit" class="btn btn-info btn-search" style="margin-top:1em" value="搜索" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div></div>
            <!-- 预约人员的表单：结束 -->

            <!-- 主体内容：结束 -->

            <!-- js -->
            <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
            <script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
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
            </script>
            <!-- //password-script -->

            <!-- //js -->
            <script src="js/bars.js"></script>

            <script type="text/javascript" src="js/jquery.slicebox.js"></script>
            <script type="text/javascript">
                $(function () {
                    var Page = (function () {
                        var $navArrows = $('#nav-arrows').hide(),
                            $navDots = $('#nav-dots').hide(),
                            $nav = $navDots.children('span'),
                            $shadow = $('#shadow').hide(),
                            slicebox = $('#sb-slider').slicebox({
                                onReady: function () {
                                    $navArrows.show();
                                    $navDots.show();
                                    $shadow.show();
                                },
                                onBeforeChange: function (pos) {
                                    $nav.removeClass('nav-dot-current');
                                    $nav.eq(pos).addClass('nav-dot-current');
                                }
                            }),
                            init = function () {
                                initEvents();
                            },
                            initEvents = function () {
                                // add navigation events
                                $navArrows.children(':first').on('click', function () {
                                    slicebox.next();
                                    return false;
                                });
                                $navArrows.children(':last').on('click', function () {
                                    slicebox.previous();
                                    return false;
                                });
                                $nav.each(function (i) {
                                    $(this).on('click', function (event) {
                                        var $dot = $(this);
                                        if (!slicebox.isActive()) {
                                            $nav.removeClass('nav-dot-current');
                                            $dot.addClass('nav-dot-current');
                                        }
                                        slicebox.jump(i + 1);
                                        return false;
                                    });
                                });
                            };
                        return {
                            init: init
                        };
                    })();
                    Page.init();
                });
            </script>
            <!-- Stats -->
            <script src="js/waypoints.min.js"></script>
            <script src="js/counterup.min.js"></script>
            <script>
                jQuery(document).ready(function ($) {
                    $('.counter').counterUp({
                        delay: 10,
                        time: 2000
                    });
                });
            </script>
            <!-- //Stats -->

            <script type="text/javascript" src="js/jquery.flexisel.js"></script>
            <!-- flexisel -->
            <script type="text/javascript">
                $(window).load(function () {
                    $("#flexiselDemo1").flexisel({
                        visibleItems: 4,
                        animationSpeed: 1000,
                        autoPlay: true,
                        autoPlaySpeed: 3000,
                        pauseOnHover: true,
                        enableResponsiveBreakpoints: true,
                        responsiveBreakpoints: {
                            portrait: {
                                changePoint: 480,
                                visibleItems: 1
                            },
                            landscape: {
                                changePoint: 640,
                                visibleItems: 2
                            },
                            tablet: {
                                changePoint: 768,
                                visibleItems: 2
                            }
                        }
                    });
                });
            </script>
            <!-- //flexisel -->
            <!-- //flexisel -->
            <!-- js for portfolio lightbox -->
            <script src="js/jquery.chocolat.js "></script>
            <!-- //menu -->
            <!-- for bootstrap working -->
            <script src="js/bootstrap.js"></script>
            <!-- //for bootstrap working -->
            <!-- start-smoth-scrolling -->
            <script type="text/javascript" src="js/move-top.js"></script>
            <script type="text/javascript" src="js/easing.js"></script>
            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    $(".scroll").click(function (event) {
                        event.preventDefault();
                        $('html,body').animate({
                            scrollTop: $(this.hash).offset().top
                        }, 1000);
                    });
                });
            </script>
            <!-- start-smoth-scrolling -->
            <!-- here stars scrolling icon -->
            <script type="text/javascript">
                $(document).ready(function () {
                    /*
                      var defaults = {
                      containerID: 'toTop', // fading element id
                      containerHoverID: 'toTopHover', // fading element hover id
                      scrollSpeed: 1200,
                      easingType: 'linear'
                      };
                    */
                    $().UItoTop({
                        easingType: 'easeOutQuart'
                    });
                });
            </script>
            <!-- //here ends scrolling icon -->

</body>

</html>