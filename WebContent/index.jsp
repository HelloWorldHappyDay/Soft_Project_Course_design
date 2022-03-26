<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="bootstrap-admin-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>图书馆服务系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<script type="text/javascript" src="static/jQuery/jquery-3.1.1.min.js"></script>
	<script src = "static/js/LoginGetFace.js"></script>
</head>

<style>
.alert{
	margin:30px auto;
	text-align:center;
	height:120px;
	width:1000px;
	font-size:70px;
	font-family:"隶书";
}
.container{
	width:1000px;
	height:500px;
}
.form{
	float:left;
	margin-left:350px;
	margin-top:50px;
	height:450px;
	width:500px;
	background-color:transparent;
	border:outset;
	border-color:green;
	text-align:center;
}
.getface{
	padding-top:0px auto;
	float:left;
 	margin-top:50px;
 	margin-left:50px; 
	height:500px;
	width:495px;
	text-align:center;
}
.input{
	height:50px;
	width:300px;
	color:white;
	font-size:40px;
	font-weight:bold;
	border:2px solid;
	border-color:blue;
	background-color:transparent;
	margin:30px auto;
}
.btn{
	background-color:transparent;
	width:150px;
	height:70px;
	font-size:40px;
	background-color:transparent;
	margin-top:20px;
	margin-bottom:20px;
}
</style>


<body data-type="login" style="background: url(background.jpg);background-size:100% 100%;background-attachment: fixed;transform: scale(1.0);">
	<div class="alert alert-info">欢迎登录图书馆服务系统</div>
	<div class="container"> 					
		<form class="form" method="post">
	       	<div>
	          		<label class="control-label" style="color:white;font-family:'楷书';font-size:40px;" for="username">账&nbsp;号</label>
	               <input type="text" class="input" id="user-username" name="username" required="required" placeholder="请输入账号">
	           </div>
			<div>
				<label class="control-label" style="color:white;font-family:'楷书';font-size:40px;" for="username">密&nbsp;码</label>
	               <input type="password" class="input" id="user-password" name="password" required="required" placeholder="请输入密码">
	           </div>
			<div>
	           	<button class="btn btn-success" type="button" onclick="takePhoto()" >登录</button>
			</div>
			<div>
				<label class="control-label" style="color:white;font-family:'楷书';font-size:30px;" for="password">没有账号请
				<a href="/books/register.jsp" style="color: red;font-size:30px;">注册</a></label> <br>
			</div>
		</form>
			<div class="getface">
				<video id="video" width="400px" height="400px" autoplay="autoplay"></video>
				<canvas id="canvas" width="495px" height="500px"  style="display: none;"></canvas>
				<img id="imgTag" src="" alt="imgTag" style="display: none;">
				<p id="flag" style="width:400x;color:white;margin:5px auto;font-size:30px;"></p>
			</div>		
	</div>
</body>
</html>