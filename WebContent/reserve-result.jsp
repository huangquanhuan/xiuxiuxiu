<%--
    reserve-result.jsp
    预约提交成功后的页面
    author: 刘忠燏
 --%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>预约结果</title>
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
        <div class="alert alert-info">
          <p>${message}</p>
        </div>
        <a href="预约.jsp" class="btn btn-lg btn-primary">返回</a>
      </div>
    </div>
    <!-- 主体内容：结束 -->
    

    	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
	<%@include file="错误提示窗口.jsp"%>
  </body>
</html>