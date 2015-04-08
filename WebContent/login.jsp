<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link id="bs-css" href="ui/charisma/css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="ui/charisma/css/charisma-app.css" rel="stylesheet">
    <link href='ui/charisma/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='ui/charisma/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='ui/charisma/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='ui/charisma/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='ui/charisma/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='ui/charisma/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='ui/charisma/css/jquery.noty.css' rel='stylesheet'>
    <link href='ui/charisma/css/noty_theme_default.css' rel='stylesheet'>
    <link href='ui/charisma/css/elfinder.min.css' rel='stylesheet'>
    <link href='ui/charisma/css/elfinder.theme.css' rel='stylesheet'>
    <link href='ui/charisma/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='ui/charisma/css/uploadify.css' rel='stylesheet'>
    <link href='ui/charisma/css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="ui/charisma/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="ui/charisma/img/favicon.ico">

</head>

<body>
<div class="ch-container">
    <div class="row">
        
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2>Welcome to Charisma</h2>
        </div>
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
                Please login with your Username and Password.
            </div>
            <c:url value="/login" var="loginUrl"/>
            <form class="form-horizontal" action="${loginUrl}" method="post">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input name="username" type="text" class="form-control" placeholder="Username">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input name="password" type="password" class="form-control" placeholder="Password">
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="remember"> Remember me</label>
                    </div>
                    <div class="clearfix"></div>

                    <p class="center col-md-5">
                        <button name="submit" type="submit" class="btn btn-primary" value="Login">Login</button>
                    </p>
                </fieldset>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
    <div class="row">
    	<div align="center">
    		<a href="plain-login.jsp">简单登陆页面</a>
    	</div>
    </div>
</div><!--/fluid-row-->

</div><!--/.fluid-container-->

<!-- external javascript -->
<script src="ui/charisma/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- library for cookie management -->
<script src="ui/charisma/js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='ui/charisma/bower_components/moment/min/moment.min.js'></script>
<script src='ui/charisma/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='ui/charisma/js/jquery.dataTables.min.js'></script>
<!-- select or dropdown enhancer -->
<script src="ui/charisma/bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="ui/charisma/bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="ui/charisma/js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="ui/charisma/bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="ui/charisma/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="ui/charisma/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="ui/charisma/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="ui/charisma/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="ui/charisma/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="ui/charisma/js/jquery.history.js"></script>
</body>
</html>