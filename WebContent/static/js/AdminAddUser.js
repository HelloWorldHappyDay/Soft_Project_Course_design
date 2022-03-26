function addUser(){
	let file =document.getElementById("adduserimg").files[0];//获取图片文件
	console.log(file);  
	let reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(){
    let img = reader.result.split("base64,")[1];
	let name =document.getElementById("addname").value;
	let username =document.getElementById("addusername").value;
	let email =document.getElementById("addemail").value;
	let phone =document.getElementById("addphone").value;
	let password =document.getElementById("addpassword").value;
	let lend_num = document.getElementById("addlend_num").value;
	let max_num = document.getElementById("addmax_num").value;
	if(img == ""||username == ""||name == ""||email == ""||phone == ""||password == ""||lend_num == ""||max_num == ""){
		alert("输入不能为空！");
	}
	else if(lend_num<0 || max_num<0){
		alert("借阅天数和借阅数量均不能小于0！");		
	}
	else{
		 $.ajax({//把编号带到后台去
	        type : 'post',
	        url : "http://localhost:8080/books/addUserServlet",
			async:false,
	        data:{"imagebase64": img,"name":name,"username":username,"email":email,
				"phone":phone,"password":password,"lend_num":lend_num,"max_num":max_num},
	         success : function(message){//成功的事件
	         	if(message == "1"){
					alert("添加成功！");
					window.location.href = "admin_user.jsp";
				}
				else if(message == "2"){
					alert("未识别到人脸！");
					window.location.href = "admin_user.jsp";
				}
				else if(message == "0"){
					alert("该用户名已存在！");
					window.location.href = "admin_user.jsp";
				}
	         }
	     });
	}
    };
	
}

