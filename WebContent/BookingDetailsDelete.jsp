<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>预约详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="">
<script type="application/x-javascript">
    
    
    
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    


</script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
    media="all">
<link href="css/slicebox.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome-icons -->
<link
    href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic"
    rel="stylesheet" type="text/css">
<link
    href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i"
    rel="stylesheet">
</head>
<body>

    <!-- banner-bottom -->
    <div class="banner-bottom">
        <div class="container">
            <div class="mid_agile_bannner_top_info">
                <h2>预约单详情</h2>
                <div class="heading-underline">
                    <div class="h-u1"></div>
                    <div class="h-u2"></div>
                    <div class="h-u3"></div>
                    <div class="clearfix"></div>
                </div>
                <!-- 两边留白 -->

                <div class="wrapper">
                    <div class="agileits_banner_bottom_left">
                        <h3>
                            <span>问题描述</span>
                            
                        </h3>
                        <div class="clearfix"></div>
                        <span id="error1">软件故障</span>&nbsp;<span id="error2">死机</span>
                        <p id="errorMessage">蓝屏就在开机时出现小圆圈转圈就迅速，不能进安全模式，机箱有很多灰尘。</p>
                    </div>
                    <div class="clearfix"></div>評價
                </div>

                <!-- 轮播 -->
                <div class="banner">
                    <ul id="sb-slider" class="sb-slider" style="max-width: 1680px;">
                        <li><img id="errorImg" src="images/banner2.jpg" alt="image2">
                            <div class="sb-description">
                                <h4>问题图片</h4>
                            </div></li>
                    </ul>
                </div>
                <!-- /wrapper -->
                <div class="clearfix"></div>

                <div class="agileits_banner_bottom_left">
                    
                    <button type="button" class="btn btn-primary"
                        data-toggle="collapse" data-target="#partsList">所需零件列表</button>
                    <div class="clearfix"></div>

                    <div id="partsList" class="container collapse">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>零件名称</th>
                                    <th>数量</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>零件1</td>
                                    <td>5</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>零件2</td>
                                    <td>2</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>零件3</td>
                                    <td>1</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row  login-center">
                    <p class="text-center">
											目前该订单维修状态为：<span id="repairStatus">已接受未处理</span>，您可以
                        <a href="single.html" class="view resw1">撤回该订单</a>
                    </p>
                </div>
            </div>
        </div>
        <!-- footer -->
        <div class="footer">
            <div class="container">
                <div class="agileinfo_copyright">
                    <p>
                        Copyright © 2017.Company name All rights reserved.More Templates <a
                            href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
                        - Collect from <a href="http://www.cssmoban.com/" title="网页模板"
                            target="_blank">网页模板</a>
                    </p>
                </div>
            </div>
        </div>
        <!-- //footer -->
        <!-- menu -->
        <!-- js -->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
        <!-- password-script -->
        <script type="text/javascript">
            window.onload = function() {
                document.getElementById("password1").onchange = validatePassword;
                document.getElementById("password2").onchange = validatePassword;
            }

            function validatePassword() {
                var pass2 = document.getElementById("password2").value;
                var pass1 = document.getElementById("password1").value;
                if (pass1 != pass2)
                    document.getElementById("password2").setCustomValidity(
                            "Passwords Don't Match");
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
            $(function() {

                var Page = (function() {

                    var $navArrows = $('#nav-arrows').hide(), $navDots = $(
                            '#nav-dots').hide(), $nav = $navDots
                            .children('span'), $shadow = $('#shadow').hide(), slicebox = $(
                            '#sb-slider').slicebox({
                        onReady : function() {

                            $navArrows.show();
                            $navDots.show();
                            $shadow.show();

                        },
                        onBeforeChange : function(pos) {

                            $nav.removeClass('nav-dot-current');
                            $nav.eq(pos).addClass('nav-dot-current');

                        }
                    }),

                    init = function() {

                        initEvents();

                    }, initEvents = function() {

                        // add navigation events
                        $navArrows.children(':first').on('click', function() {

                            slicebox.next();
                            return false;

                        });

                        $navArrows.children(':last').on('click', function() {

                            slicebox.previous();
                            return false;

                        });

                        $nav.each(function(i) {

                            $(this).on('click', function(event) {

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
                        init : init
                    };

                })();

                Page.init();

            });
        </script>
        <!-- Stats -->
        <script src="js/waypoints.min.js"></script>
        <script src="js/counterup.min.js"></script>
        <script>
            jQuery(document).ready(function($) {
                $('.counter').counterUp({
                    delay : 10,
                    time : 2000
                });
            });
        </script>
        <!-- //Stats -->

        <script type="text/javascript" src="js/jquery.flexisel.js"></script>
        <!-- flexisel -->
        <script type="text/javascript">
            $(window).load(function() {
                $("#flexiselDemo1").flexisel({
                    visibleItems : 4,
                    animationSpeed : 1000,
                    autoPlay : true,
                    autoPlaySpeed : 3000,
                    pauseOnHover : true,
                    enableResponsiveBreakpoints : true,
                    responsiveBreakpoints : {
                        portrait : {
                            changePoint : 480,
                            visibleItems : 1
                        },
                        landscape : {
                            changePoint : 640,
                            visibleItems : 2
                        },
                        tablet : {
                            changePoint : 768,
                            visibleItems : 2
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
            jQuery(document).ready(function($) {
                $(".scroll").click(function(event) {
                    event.preventDefault();
                    $('html,body').animate({
                        scrollTop : $(this.hash).offset().top
                    }, 1000);
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

                $().UItoTop({
                    easingType : 'easeOutQuart'
                });

            });
        </script>
        <!-- //here ends scrolling icon -->

        <a href="#" id="toTop" style="display: none;"><span
            id="toTopHover"></span>To Top</a>
</body>
</html>