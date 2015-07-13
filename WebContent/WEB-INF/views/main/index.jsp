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
	<title>Luke's Backstage Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

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
<jsp:include page="../common/topbar.jsp" />

<div class="ch-container">
<div class="row">
<!-- include left menu -->
<jsp:include page="../common/leftmenu.html" />
<noscript>
	<div class="alert alert-block col-md-12">
		<h4 class="alert-heading">Warning!</h4>
		<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>enabled to use this site.
		</p>
	</div>
</noscript>

<!-- content start -->
<div id="content" class="col-lg-10 col-sm-10">
	<div>
		<ul class="breadcrumb">
			<li><a href="#">后台管理</a></li>
			<li><a href="#">首页</a></li>
		</ul>
	</div>

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
					<h1>Backstage Framework<br>
						<small>Charisma + SpringMVC SpringSecurity + MyBatis + LogBack</small>
					</h1>
					<hr/>

					<h1>Test Example</h1><br>
					<h4>JSON API & JSP</h4>
					<h5>GET的中文UrlEncode编码，取决于不同的浏览器，Chrome对URL中出现的中文使用utf-8进行UrlEncode</h5>
					<h5>Spring MVC的默认Character是ISO-8859-1[Latin-1],需要使用Spring实现的Servlet Filter. PS:详细见web.xml中的Filter</h5>
					<ul>
						<li>test/xml   <a href="test/xml">click</a> 根据accept：application/xml 或 application/json 来区分返回xml还是json</<li>
						<li>test/jsp/grape (RESTFUL) <a href="test/jsp/grape">click</a></<li>
						<li>test/json/fruit/grape/chinese/葡萄 (RESTFUL) <a href="test/json/fruit/grape/chinese/葡萄">click</a></<li>
						<li>test/json?name=kiki&chinese_name=陈 (JSON) <a href="test/json?name=kiki&chinese_name=陈">click</a></li>
						<li>test/json/post_json (Ajax Post Chinese ) <a href="#" id="postJson">click</a></li>
						<li>test/json/form_post ( Form Post )
						<form class="form-horizontal" action="test/json/form_post" method="post">
							<input name="username" type="text" class="form-control" placeholder="Username">
							<button name="submit" type="submit" class="btn btn-primary" value="submit">Submit</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
						</li>
					</ul>
					<h4>User Authority</h4>
					<h5>必须是认证用户才能访问User接口</h5>
					<ul>
						<li>用户状态:
						<sec:authorize access="isAuthenticated()">
							<b style="color:red">已登陆</b>
							<form action="logout" method="POST">
								<input type="submit" value="logout" title="登出"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</form>
						</sec:authorize>
						<sec:authorize access="!isAuthenticated()">
							<b style="color:red">未登陆</b>
						</sec:authorize>
						</li>
						<li>user/query/all (只限Admin权限访问) <a href="user/query/all">click</a></li>
						<li>user/whoami <a href="user/whoami">click</a></li>
					</ul>
					<h4>CSV</h4>
					<ul>
						<li>test/downloadCSV <a href="test/downloadCSV">click</a></li>
					</ul>
					<h4>302 Redirect</h4>
					<ul>
						<li>test/download302 <a href="test/download302">click</a></li>
					</ul>
					<h4>An tiny image which read from buffer</h4>
					<ul>
						<li>test/image/tiny <a href="test/image/tiny">click</a></li>
					</ul>
					<h4>Transcation</h4>
					<ul>
						<li>test/transaction/readonly <a href="test/transaction/readonly">click</a></li>
						<li>test/transaction/rollback <a href="test/transaction/rollback">click</a></li>
						<li>test/transaction/success <a href="test/transaction/success">click</a></li>
						<li>test/non-transaction <a href="test/non-transaction">click</a></li>
					</ul>
					<h4>Redis</h4>
					<ul>
						<li>test/cache <a href="test/cache">click</a></li>
					</ul>
					<h4>print log</h4>
					<ul>
						<li>test/log <a href="test/log">click</a></li>
					</ul>
					<h4>Exit Application</h4>
					<ul>
						<li>test/exit <a href="test/exit">click</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$('#postJson').click(function() {
	post('test/json/post_json', '{"id":25, "name":"中文名"}',
	function (data) {
		if(data['status'])
			alert('数据处理成功');
		else
			alert('数据处理失败');
	},
	function () {
		alert('请求失败，请检查网络环境');
	});
});
function post(url, data, success, error) {
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	$.ajax({
		type: 'POST', url: url, data: data, success: success, error: error,
		headers: {'X-CSRF-TOKEN': csrfToken}
	});
}
</script>
</div><!-- content ends-->
</div><!-- row end -->
</div><!-- ch-content ends -->
<hr>

<!-- include footer -->
<jsp:include page="../common/footer.html"/>

<!-- include js -->
<jsp:include page="../common/js.html"/>

</body>
</html>
