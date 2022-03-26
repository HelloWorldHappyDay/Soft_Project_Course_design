z<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/jQuery/jquery-3.1.1.min.js"></script>
    <script src="static/js/RegisterGetFace.js"></script>
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
.container {
	width:1400px;
	height:520px;
}
.form{
	float:left;
	margin-left:250px;
	margin-top:20px;
	height:480px;
	width:400px;
	border:outset;
	border-color:green;
	border-radius: 5px;
	text-align:center;
}
.input{
	margin:20px auto;
	height:40px;
	width:300px;
	color:white;
	font-weight:bold;
	font-size:30px;
	border:2px solid;
	border-color:blue;
	background-color:transparent;
}
.getface{
	float:left;
	margin-left:50px;
	margin-top:20px;
	width:400px;
	height:500px;
	text-align:center;
} 
.btn{
	margin-top:10px;
	width:100px;
	height:50px;
}
</style>
<body data-type="login" style="background: url(background.jpg);background-size:100% 100%;background-attachment: fixed;transform: scale(1.0);">
	<div class="alert alert-info">欢迎注册图书馆服务系统账号</div>
	<div class="container">
    	<form class="form" method="post" >
	        <div>
	       		<input type="text" class="input" id="user-username" name="username" required="required" placeholder="请输入账号">
	        </div>
			<div>
	        	<input type="password" class="input" id="user-password" name="password" required="required" placeholder="请输入密码">
	        </div>
			<div>
	            <input type="text" class="input" id="user-name" name="name" required="required" placeholder="请输入姓名">
	        </div>
			<div>
	            <input type="text" class="input" id="user-email" name="email" required="required" placeholder="请输入邮箱">
	        </div>
	        <div>
	            <input type="text" class="input" id="user-phone" name="phone" required="required" placeholder="请输入手机号">
	        </div>
			<div>
	            <button class="btn btn-success" type="button" onclick="takePhoto()" >提交</button>
	            <button class="btn btn-warning" type="button" onclick="location.href='/books/index.jsp'" >返回登录</button>
			</div>
        </form>
        <div class="getface">
			<video id="video" width="400px" height="400px" autoplay="autoplay"></video>
			<canvas id="canvas" width="400px" height="400px" style="display: none;"></canvas>
			<img id="imgTag" src="" alt="imgTag" style="display: none;">
			<p id="flag" style="width:400px;color:white;margin:5px auto;font-size:30px;"></p>
		</div>
	</div>
</body>
</html>