<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
// 获得项目完全路径(假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/)
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
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

	<!-- base需要放到head中 -->
	<base href="<%=basePath%>">
	
	<!-- include charimas css -->
	<jsp:include page="../common/css.html" />

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
    <!-- include topbar -->
    <jsp:include page="../common/topbar.html" />
<div class="ch-container">
    <div class="row">
        
        <!-- include left menu -->
		<jsp:include page="../common/leftmenu.html" />

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
			            <a href="#">首页</a>
			        </li>
			    </ul>
			</div>

<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i> Introduction</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>
            <div class="box-content row">
                <div class="col-lg-7 col-md-12">
                    <h1>Charisma <br>
                        <small>free, premium quality, responsive, multiple skin admin template.</small>
                    </h1>
                    <p>
                    用户状态:
					<sec:authorize access="isAuthenticated()">
						<b style="color:red">已登陆</b>
						<form action="logout" method="POST">
							<input type="submit" value="logout"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						<b style="color:red">未登陆</b>
					</sec:authorize>
					<p>
                   	This webapp is an sample about spring web, spring transaction, mybatis.<br>
					Test URL:<br>
					<h4>Data Formatter</h4>
					<ul>
						<li>test/json?name=kiki <a href="test/json?name=kiki">click</a></li>
						<li>test/jsp/grape <a href="test/jsp/grape">click</a></<li>
					</ul>
					<h4>User authority</h4>
					<ul>
						<li>/user/all</li>
						<li>/user/query</li>
						<li>/user/add?username=user2&password=111111&authority=ROLE_USER</li>
						<li>/user/update?username=user2&password=222222&authority=ROLE_USER</li>
						<li>/user/delete?username=user2</li>
						<li>/user/whoami</li>
					</ul>
					<h4>CSV</h4>
					test/downloadCSV <a href="test/downloadCSV">click</a><br>
					<h4>302 Redirect</h4>
					test/download302 <a href="test/download302">click</a><br>
					<h4>An tiny image which read from buffer</h4>
					test/image/tiny <a href="test/image/tiny">click</a>
					<h4>Transcation</h4>
					test/transaction <a href="test/transaction">click</a>
					<h4>Redis</h4>
					test/cache <a href="test/cache">click</a>
					<h4>Exit Application</h4>
					test/exit <a href="test/exit">click</a>
                </div>
            </div>
        </div>
    </div>
</div>

    <!-- content ends -->
</div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

<hr>
<!-- include footer -->
<jsp:include page="../common/footer.html"/>

</div><!--/.fluid-container-->

<!-- include js -->
<jsp:include page="../common/js.html"/>

</body>
</html>
