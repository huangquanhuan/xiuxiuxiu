<%--
    make-a-reservation-2.jsp
    用户端预约界面（第二步，填写预约信息）
    author：刘忠燏
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <title>维修预约 - 第二步</title>
    
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
    <div class="agileits_w3layouts_banner_nav">
      <nav class="navbar navbar-default">
        <div class="navbar-header navbar-left">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          <h1><a class="navbar-brand" href="index.html"><i class="fa fa-crosshairs" aria-hidden="true"></i> 修！咻咻！</a></h1>

        </div>
        <ul class="agile_forms">
          <li><a class="active" href="#" data-toggle="modal" data-target="#myModal2"> Sign In</a> </li>
          <li><a href="#" data-toggle="modal" data-target="#myModal3"> Sign Up</a> </li>
        </ul>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
          <nav>
            <ul class="nav navbar-nav">
              <li class="active"><a href="index.html" class="hvr-underline-from-center">主页</a></li>
              <li><a href="#" class="hvr-underline-from-center">预约</a></li>
              <li><a href="#" class="hvr-underline-from-center">文章 &amp; 通知</a></li>
              <li><a href="#" class="hvr-underline-from-center">我的</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle hvr-underline-from-center" data-toggle="dropdown">Short Codes <b class="fa fa-caret-down"></b></a>
                <ul class="dropdown-menu agile_short_dropdown">
                  <li><a href="icons.html">Web Icons</a></li>
                  <li><a href="typography.html">Typography</a></li>
                </ul>
              </li>
              <li><a href="contact.html" class="hvr-underline-from-center">Contact</a></li>
            </ul>
          </nav>

        </div>
      </nav>

      <div class="clearfix"> </div>
    </div>
    
    <!-- 主体内容：开始 -->
    <div class="services">
      <div class="container">
        <ul id="formTab" class="nav nav-tabs" role="tablist">
          <li class="nav-item">
            <a class="hvr-underline-from-center" data-toggle="tab" href="#field-service">活动预约</a>
          </li>
          <li>
            <a class="hvr-underline-from-center" data-toggle="tab" href="#door-to-door-service">上门服务</a>
          </li>
        </ul>
        
        <div class="tab-content">
          <!-- 申请现场维修的表单：开始 -->
          <div id="field-service" class="container tab-pane active"><br />
            <form action="makeReservation?method=addFieldService" method="post" enctype="multipart/form-data">
              <input type="hidden" name="userId" value="1" />
              <!-- 场次选择 -->
              <div class="form-group">
                <label for="session-select" class="col-sm-3">预约场次</label>
                <div class="col-sm-9">
                  <select id="session-select" class="form-control" name="activity">
                   <!-- 这里装入场次时间信息 -->
                   <c:forEach items="${activities}" var="activity">
                     <option value="${activity.ID}">${activity.time}，地点：${activity.place}</option>
                   </c:forEach>
                  </select>
                </div>
              </div>

              <!-- 设备选择 -->
              <div class="form-group">
                <div class="col-sm-3">
                  <label for="device-select">设备</label>
                  <a href="#" class="btn btn-link">添加设备</a>
                </div>
                <div class="col-sm-9">
                  <select id="device-select" class="form-control" name="device">
                    <!-- 这里装入用户的设备列表 -->
                    <c:forEach items="${equipments}" var="equipment">
                      <option value="${equipment.id}">${equipment.equipmentName}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <!-- 需求零件选择 -->
              <div class="form-group">
                <label for="component-select" class="col-sm-3">需求零件</label>
                <div id="accordion" class="col-sm-9">
                  <div class="card">
                    <div class="bg-info card-header">
                      <a class="card-link text-muted" data-toggle="collapse" href="#component-list">可选零件</a>
                    </div>
                    <div id="component-list" class="collapse fade" data-parent="#accordion">
                      <!-- 这里装入零件列表，插入格式见下面的样例 -->
                      <c:forEach items="${components}" var="component">
                        <div class="card-body">
                          <label><input type="checkbox" name="neededComponents" value="${component.id}">${component.name}</label>
                        </div>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
              
              <%-- 
              此部分内容暂缓实现，原因如下：
                1. 凭借个人所学知识，暂时无法解决下拉框动态刷新问题
                  （即在你选择了硬件或软件问题后，另一个下拉框自动更新二级选项）
                2. 即使改成一级菜单，数据库那边的衔接也有问题，数据表里一个 id 同时对应一个硬件问题种类和一个软件问题种类，
                   导致后台无法处理（除非修改这个表的结构，那样代价太大）
              故暂时不用，等待解决之后重新实现
              by 刘忠燏 2019-05-03
              <!-- 问题类型 -->
              <div class="form-group">
                <label for="issue-type-select" class="col-sm-3">问题种类</label>
                <div id="issue-type-select" class="col-sm-9">
                  <select class="form-control">
                    <option>硬件问题</option>
                    <option>软件问题</option>
                  </select>
                  <select class="form-control">
                    <!-- 这里根据前一个选项框的选择，显示对应的子分类 -->
                    <option>子分类 1</option>
                    <option>子分类 2</option>
                  </select>
                </div>
              </div>
              --%>
              
              <!-- 详细描述问题 -->
              <div class="form-group">
                <label for="issue-detail" class="col-sm-3">问题描述</label>
                <div id="issue-detail" name="issueDetail" class="col-sm-9">
                  <textarea class="form-control" rows="3"></textarea>
                </div>
              </div>
              
              <!-- 图片上传 -->
              <div class="form-group">
                <label for="issue-image" class="col-sm-3">为问题描述提供图片</label>
                <div class="col-sm-9">
                  <input id="issue-image" name="issueImage" type="file" class="form-control" />
                </div>
              </div>

              <!-- 备注 -->
              <div class="form-group">
                <label for="issue-comment" class="col-sm-3">备注</label>
                <div class="col-sm-9">
                  <textarea id="issue-comment" class="form-control" rows="3"></textarea>
                </div>
              </div>
              
              <!-- 提交按钮 -->
              <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                  <input type="submit" class="btn btn-primary" value="提交" />
                </div>
              </div>
            </form>
          </div>
          <!-- 申请现场维修的表单：结束 -->
          
          <!-- 申请上门服务的表单：开始 -->
          <div id="door-to-door-service" class="container tab-pane fade"><br />
            <form action="makeReservation?method=addDoorToDoorService" method="post" enctype="multipart/form-data">
              <input type="hidden" name="userId" value="1" />
              <!-- 时间选择 -->
              <div class="form-group">
                <label for="datetime-select" class="col-sm-3">预约时间</label>
                <div class="col-sm-9">
                  <input class="form-control" type="datetime-local" name="requiredTime" />
                </div>
              </div>

              <!-- 设备选择 -->
              <div class="form-group">
                <div class="col-sm-3">
                  <label for="device-select-1">设备</label>
                  <a href="#" class="btn btn-link">添加设备</a>
                </div>
                <div class="col-sm-9">
                  <select id="device-select-1" class="form-control" name="device">
                    <!-- 这里装入用户的设备列表 -->
                    <c:forEach items="${equipments}" var="equipment">
                      <!-- 用户设备信息 -->
                      <option value="${equipment.id}">${equipment.equipmentName}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <!-- 需求零件选择 -->
              <div class="form-group">
                <label for="component-select-1" class="col-sm-3">需求零件</label>
                <div id="accordion-1" class="col-sm-9">
                  <div class="card">
                    <div class="bg-info card-header">
                      <a class="card-link text-muted" data-toggle="collapse" href="#component-list-1">可选零件</a>
                    </div>
                    <div id="component-list-1" class="collapse fade" data-parent="#accordion-1">
                      <!-- 这里装入零件列表，插入格式见下面的样例 -->
                      <c:forEach items="${components}" var="component">
                        <div class="card-body">
                          <label><input type="checkbox" name="neededComponents" value="${component.id}">${component.name}</label>
                        </div>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
              
              <%-- 
              此部分内容暂缓实现，原因如下：
                1. 凭借个人所学知识，暂时无法解决下拉框动态刷新问题
                  （即在你选择了硬件或软件问题后，另一个下拉框自动更新二级选项）
                2. 即使改成一级菜单，数据库那边的衔接也有问题，数据表里一个 id 同时对应一个硬件问题种类和一个软件问题种类，
                   导致后台无法处理（除非修改这个表的结构，那样代价太大）
              故暂时不用，等待解决之后重新实现
              by 刘忠燏 2019-05-03
              
              <!-- 问题类型 -->
              <div class="form-group">
                <label for="issue-type-select-1" class="col-sm-3">问题种类</label>
                <div id="issue-type-select-1" class="col-sm-9">
                  <select class="form-control">
                    <option>硬件问题</option>
                    <option>软件问题</option>
                  </select>
                  <select class="form-control">
                    <!-- 这里根据前一个选项框的选择，显示对应的子分类 -->
                    <option>子分类 1</option>
                    <option>子分类 2</option>
                  </select>
                </div>
              </div>
              --%>
              
              <!-- 详细描述问题 -->
              <div class="form-group">
                <label for="issue-detail-1" class="col-sm-3">问题描述</label>
                <div id="issue-detail-1" class="col-sm-9">
                  <textarea class="form-control" name="issueDetail" rows="3"></textarea>
                </div>
              </div>
              
              <!-- 图片上传 -->
              <div class="form-group">
                <label for="issue-image-1" class="col-sm-3">为问题描述提供图片</label>
                <div class="col-sm-9">
                  <input id="issue-image-1" name="issueImage" type="file" class="form-control" />
                </div>
              </div>

              <!-- 备注 -->
              <div class="form-group">
                <label for="issue-comment-1" class="col-sm-3">备注</label>
                <div class="col-sm-9">
                  <textarea id="issue-comment-1" class="form-control" rows="3"></textarea>
                </div>
              </div>
              
              <!-- 提交按钮 -->
              <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                  <input type="submit" class="btn btn-primary" value="提交" />
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
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