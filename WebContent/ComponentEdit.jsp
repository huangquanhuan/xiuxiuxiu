<%@page import="xiuxiuxiu.dao.*"%>
<%@page import="xiuxiuxiu.pojo.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="jquery-3.2.0.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="mid_agile_bannner_top_info">
				<h2>零件库编辑</h2>
				<div class="heading-underline">
					<div class="h-u1"></div>
					<div class="h-u2"></div>
					<div class="h-u3"></div>
					<div class="clearfix"></div>
				</div>
				<!-- 两边留白 -->

				<div class="wrapper">
					<div class="table-responsive">
						<table class="table table-bordered table-striped">
							<caption>零件列表</caption>
							<caption><a href="ComponentServlet?method=list" class="view resw3">刷新</a></caption>
							<thead>
								<tr>
									<th>id</th>
									<th>零件名称</th>
									<th>零件类型</th>
									<th>数量</th>
									<th>价格</th>
									<th>修改</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${components}" var="components">
									<tr>
										<td>${components.id}</td>
										<td>${components.name}</td>
										<td>${components.type}</td>
										<td>${components.quantity}</td>
										<td>${components.price}</td>
										<td><button type="button" class="btn btn-primary btn-lg"
												onclick="updateFormID(${components.id})" data-toggle="modal"
												data-target="#myModal2">修改</button></td>
										<td><a
											href="ComponentServlet?method=delete&id=${components.id}"
											class="view resw3">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 按钮触发模态框 -->
						<button class="btn btn-primary btn-lg" data-toggle="modal"
							data-target="#myModal">新增零件</button>
						<!-- 模态框（Modal） -->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content agileits_banner_bottom_left">
									<form action="ComponentServlet?method=add" method="post">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h3>
												<span>新增零件</span>
											</h3>
										</div>
										<div class="modal-body">

											<p>
												<span>零件名称</span> <input type="text" class="form-control"
													name="name" />
											</p>
											<p>
												<span>零件数量</span> <input type="text" class="form-control"
													name="quantity" />
											</p>
											<p>
												<span>零件价格</span> <input type="text" class="form-control"
													name="price" />
											</p>
											<p>
												<span>零件类型</span> <input type="text" class="form-control"
													name="type" />
											</p>

										</div>
										<div class="modal-footer">

											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary" name="submit">提交</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- 模态框（Modal2） -->
						<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content agileits_banner_bottom_left">
									<form action="ComponentServlet?method=update" method="post">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h3>
												<span>修改零件</span>
											</h3>
										</div>
										<div class="modal-body">
											<input type="hidden" name="id" id="id" value="5" />
											<p>
												<span>零件名称</span> <input type="text" class="form-control"
													name="name" />
											</p>
											<p>
												<span>零件数量</span> <input type="text" class="form-control"
													name="quantity" />
											</p>
											<p>
												<span>零件价格</span> <input type="text" class="form-control"
													name="price" />
											</p>
											<p>
												<span>零件类型</span> <input type="text" class="form-control"
													name="type" />
											</p>
										</div>
										<div class="modal-footer">

											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary" name="submit">提交</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
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
	<!-- updateFormID -->
	<script type="text/javascript">
		function updateFormID(a) {
	        document.getElementById("id").value = a;
	    }
	</script>
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

	<a href="#" id="toTop" style="display: none;"><span id="toTopHover"></span>To
		Top</a>
</body>
</html>