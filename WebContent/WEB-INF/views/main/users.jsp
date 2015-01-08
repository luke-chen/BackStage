<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <jsp:include page="../common/topbar.html" />
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">

    <!-- left menu starts -->
    <jsp:include page="../common/leftmenu.html" />
    <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">后台管理</a>
            </li>
            <li>
                <a href="#">用户管理</a>
            </li>
        </ul>
    </div>

    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 用户列表</h2>
    </div>
    <div class="box-content">
        <table class="table table-hover">
      <thead>
        <div class="alert alert-info">
          需要添加新用户点击: <a href="#" data-toggle="modal" data-target="#newUser">新增用户</a>
        </div>
            <tr>
              <th>用户名</th>
              <th>更新密码</th>
              <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>删除该用户</th>
              </sec:authorize>
            </tr>
        </thead>
        <tbody>
          <c:forEach items="${users}" var="user">
            <tr>
              <td>${user.username}</td>
              <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td><a href="#" data-toggle="modal" data-target="#changePwd" onclick="changePwdUser('${user.username}')">更新密码</a></td>
              </sec:authorize>
              <sec:authorize access="hasRole('ROLE_USER')">
                <c:choose>
                <c:when test="${user.username == username}">
                  <td><a href="#" data-toggle="modal" data-target="#changePwd" onclick="changePwdUser('${user.username}')">更新密码</a></td>
                </c:when>
                <c:otherwise>
                	<td></td>
                </c:otherwise>
                </c:choose>
              </sec:authorize>
              <c:choose>
                <c:when test="${user.username != username}">
                  <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="#" onclick="deleteUser('${user.username}')">删&nbsp;&nbsp;&nbsp;&nbsp;除</a></td>
                  </sec:authorize>
                  <sec:authorize access="hasRole('ROLE_ROLE')">
                    <td></td>
                  </sec:authorize>
                </c:when>
                <c:otherwise>
                	<td></td>
                </c:otherwise>
              </c:choose>
            </tr>
          </c:forEach>
        </tbody>
    </table>

  <!-- 新建用户 -->
  <div class="modal fade" id="newUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="myModalLabel">新建用户</h4>
        </div>
        <div class="modal-body">
          <table id="orders-table" class="table table-hover">
          <thead>
            <tr>
              <th>用户名</th>
              <th>密码</th>
              <th>权限</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><input id="username" type="text" class="form-control" placeholder="输入用户名"/></td>
              <td><input id="password" type="password" class="form-control" placeholder="输入密码"/></td>
              <td>
                <select id="authority" class="form-control">
              <option value="ROLE_USER">用户</option>
              <option value="ROLE_ADMIN">管理员</option>
              </select>
              </td>
            </tr>
          </tbody>
      </table>
        </div>
        <div class="modal-footer">
          <button id="addNewUser" type="button" class="btn btn-default" data-dismiss="modal">提交</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 修改密码 -->
  <div class="modal fade" id="changePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="myModalLabel">修改密码</h4>
        </div>
        <div class="modal-body">
          <table id="orders-table" class="table table-hover">
          <thead>
            <tr>
              <th>新密码</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <input id="c_password" type="password" class="form-control" placeholder="输入新密码"/>
              </td>
            </tr>
          </tbody>
      </table>
        </div>
        <div class="modal-footer">
          <button id="changeNewPwd" type="button" class="btn btn-default" data-dismiss="modal">提交</button>
        </div>
      </div>
    </div>
  </div>
    </div>
    </div>
    </div>
    <!--/span-->

    </div><!--/row-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    <hr>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">Ã</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
                </div>
            </div>
        </div>
    </div>

<!-- footer starts -->
<jsp:include page="../common/footer.html"/>
<!-- footer ends -->

</div><!--/.fluid-container-->
<script type="text/javascript">
  var selectedUsername = '';
  function changePwdUser(username) {
    selectedUsername = username;
  }
  
  function deleteUser(username) {
	  var url = 'user/delete?username='+username;
	  ajax_get(url,
			  function() {location.href="user";},
			  function() {});
  }

  $("#addNewUser").click(function(event) {
    var url = 'user/add?username='+$("#username").val()+'&password='+$("#password").val()+'&authority='+$("#authority").val();
    ajax_get(url,
        function() {location.href="user";},
        function() {});
  });

  $("#changeNewPwd").click(function(event) {
    var url = 'user/changePassword?username='+selectedUsername+'&password='+$("#c_password").val();
    ajax_get(url,
        function() {location.href="user";},
        function() {});
  });

  function ajax_get(url, success, error) {
    $.ajax({
      url : url,
      type : "get",
      cache: false,
      success : function(data) {
        success(data);
      },
      error : function() {
        error();
      }
    });
  }
</script>
<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>


</body>
</html>
