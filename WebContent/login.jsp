<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>密码登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link href="./密码登录_files/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <link href="./密码登录_files/axure_rp_page.css" type="text/css" rel="stylesheet">
    <link href="./密码登录_files/styles.css" type="text/css" rel="stylesheet">
    <link href="./密码登录_files/styles(1).css" type="text/css" rel="stylesheet">
    <script src="./密码登录_files/jquery-1.7.1.min.js"></script>
    <script src="./密码登录_files/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="./密码登录_files/prototypePre.js"></script>
    <script src="./密码登录_files/document.js"></script>
    <script src="./密码登录_files/prototypePost.js"></script>
    <script src="./密码登录_files/data.js"></script>
    <script type="text/javascript">
      $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
      $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
      $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
    </script>
  </head>
<body>
    <div id="base" class="">
    <!-- Unnamed (其余控件) -->
      <div id="u991" class="ax_default" data-left="0" data-top="0" data-width="375" data-height="667">

        <!-- Unnamed (矩形) -->
        <div id="u992" class="ax_default box_2">
          <div id="u992_div" class=""></div>
        </div>

        <!-- Unnamed (修咻咻维修平台) -->
        <div id="u993" class="ax_default" data-left="0" data-top="58" data-width="370" data-height="399">
            <!-- Unnamed (矩形) -->
            <div id="u995" class="ax_default label">
              <div id="u995_div" class=""></div>
              <div id="u995_text" class="text ">
                
                <p><span>修咻咻维修平台</span></p>
                <%
                String err = request.getParameter("err");
                if (err != null) out.print(err);
                %>
              </div>
            </div>
          </div>

          <!-- Unnamed (登录方式) -->
          <div id="u996" class="ax_default" data-left="30" data-top="132" data-width="324" data-height="70">

            <!-- Unnamed (帐户密码登录) -->
            <div id="u997" class="ax_default box_1" style="cursor: pointer;">
              <div id="u997_div" class="" tabindex="0"></div>
              <div id="u997_text" class="text ">
                <p><span>帐户密码登录</span></p>
              </div>
            </div>

            <!-- Unnamed (手机号登录) -->
            <div id="u998" class="ax_default box_1" style="cursor: pointer;">
              <div id="u998_div" class="" tabindex="0"></div>
              <div id="u998_text" class="text ">
                <p><span>手机号登录</span></p>
              </div>
            </div>
          </div>

          <!-- Unnamed (组 合) -->
          <div id="u1001" class="ax_default" data-left="0" data-top="328" data-width="370" data-height="22">

            <!-- Unnamed (自动登录) -->
            <div id="u1003" class="ax_default label">
              <div id="u1003_div" class=""></div>
              <div id="u1003_text" class="text ">
                <p><span>自动登录</span></p>
              </div>
            </div>

            <!-- Unnamed (忘记密码) -->
            <div id="u1004" class="ax_default label">
              <div id="u1004_div" class=""></div>
              <div id="u1004_text" class="text ">
                <p><span>忘记密码</span></p>
              </div>
            </div>
          </div>


          <!-- Unnamed (注册账户) -->
          <div id="u1006" class="ax_default" data-left="311" data-top="439" data-width="59" data-height="18">

            <!-- Unnamed (组 合) -->
            <div id="u1007" class="ax_default" data-left="0" data-top="0" data-width="0" data-height="0">

              <!-- Unnamed (组 合) -->
              <div id="u1008" class="ax_default" data-left="0" data-top="0" data-width="0" data-height="0">
              </div>
            </div>

            <!-- Unnamed (注册账户) -->
            <div id="u1009" class="ax_default label" style="cursor: pointer;">
              <div id="u1009_div" class="" tabindex="0"></div>
              <div id="u1009_text" class="text ">
                <p><span>注册账户</span></p>
              </div>
            </div>
          </div>
        

        <!-- Unnamed (帮助隐私条款) -->
        <div id="u1011" class="ax_default" data-left="110" data-top="580" data-width="168" data-height="21">

            <!-- Unnamed (帮助) -->
            <div id="u1012" class="ax_default heading_1">
              <div id="u1012_div" class=""></div>
              <div id="u1012_text" class="text ">
                <p><span>帮助</span></p>
              </div>
            </div>

            <!-- Unnamed (隐私) -->
            <div id="u1013" class="ax_default heading_1">
              <div id="u1013_div" class=""></div>
              <div id="u1013_text" class="text ">
                <p><span>隐私</span></p>
              </div>
            </div>

            <!-- Unnamed (条款) -->
            <div id="u1014" class="ax_default heading_1">
              <div id="u1014_div" class=""></div>
              <div id="u1014_text" class="text ">
                <p><span>条款</span></p>
              </div>
            </div>
          </div>
      </div>
      
      <form class="loginForm" action="UserServlet?method=login" method="post">
      
      
      <!-- Unnamed (手机号) -->
      <div id="u1020" class="ax_default" data-left="0" data-top="202" data-width="368" data-height="40">

        <!-- Unnamed (矩形) -->
        <div id="u1021" class="ax_default shape">
          <div id="u1021_div" class=""></div>
        </div>

        <!-- Unnamed (图片) -->
        <div id="u1022" class="ax_default image1">
          <img id="u1022_img" class="img " src="./密码登录_files/u92.png">
        </div>

        <!-- phone_number (手机号文本框) -->
        <div id="u1023" class="ax_default text_field" data-label="phone_number">
          <input id="phoneNumber" name="phoneNumber" type="text" value="手机号">
        </div>
      </div>

      <!-- Unnamed (密码) -->
      <div id="u1024" class="ax_default" data-left="0" data-top="265" data-width="368" data-height="40">

        <!-- Unnamed (矩形) -->
        <div id="u1025" class="ax_default shape">
          <div id="u1025_div" class=""></div>
        </div>

        <!-- Unnamed (图片) -->
        <div id="u1026" class="ax_default image1">
          <img id="u1026_img" class="img " src="./密码登录_files/u98.png">
        </div>

        <!-- password (密码文本框) -->
        <div id="u1027" class="ax_default text_field" data-label="password">
          <input id="password" name="password" type="text" value="密码">
        </div>
      </div>
      
      <!-- Unnamed (登录按钮) -->
      <div id="u1005" class="ax_default shape" style="cursor: pointer;">
            <div id="u1005_div" class="" tabindex="0"></div>
            <div id="u1005_text" class="text ">
            <input type="submit" value="登录" />
            </div>
          </div>
      </form>
      
    </div>
</body>
</html>