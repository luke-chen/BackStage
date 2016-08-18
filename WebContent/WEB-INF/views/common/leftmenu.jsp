<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="ui/AdminLTE-2.3.6/dist/img/user2-160x160.jpg"
				class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>Alexander Pierce</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
		<li class="header">MAIN NAVIGATION</li>
		<li><a href="home"> <i class="fa fa-pie-chart"></i> <span>首页</span></a></li>
		<li class="treeview"><a href="#"><i class="fa fa-files-o"></i><span>用户管理</span></a>
			<ul class="treeview-menu">
				<li><a href="user"><i class="fa fa-circle-o"></i> 用户管理</a></li>
			</ul>
		</li>
		<li class="active treeview">
			<a href="#">
				<i class="fa fa-dashboard"></i><span>Dashboard</span>
				<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
				<li class="active"><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
			</ul>
		</li>
		<li>
			<a href="pages/widgets.html">
				<i class="fa fa-th"></i> <span>Widgets</span><span class="pull-right-container"> <small class="label pull-right bg-green">new</small></span>
			</a>
		</li>
		<li>
			<a href="pages/mailbox/mailbox.html"> 
			<i class="fa fa-envelope"></i> <span>Mailbox</span> <span class="pull-right-container"> <small class="label pull-right bg-yellow">12</small>
			<small class="label pull-right bg-green">16</small> <small class="label pull-right bg-red">5</small></span>
			</a>
		</li>
		<li class="treeview">
			<a href="#"> <i class="fa fa-share"></i>
			<span>Multilevel</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span>
			</a>
			<ul class="treeview-menu">
				<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
				<li><a href="#"><i class="fa fa-circle-o"></i> Level One <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a>
				<ul class="treeview-menu">
				<li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
				<li><a href="#"><i class="fa fa-circle-o"></i> Level Two <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a>
				<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
					<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
				</ul>
				</li>
			</ul>
			</li>
			<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
			</ul>
		</li>
	<li class="header">LABELS</li>
	<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
	<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
	<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
</ul>
</section>
<!-- /.sidebar -->
</aside>