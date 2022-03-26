function updateBookType(){ 
	let tid =document.getElementById("updatetid").value;
	let name =document.getElementById("updatename").value;
	if(tid==""||name ==""){
			alert("输入不能为空,请重新输入")
	}
	else{
		 $.ajax({//把编号带到后台去
	        type : 'post',
	        url : "http://localhost:8080/books/updateBookTypeServlet",
			async:false,
	         data:{"tid":tid,"name":name},
	         success : function(message){//成功的事件
	         	if(message == "true"){
					alert("分类修改成功！");
					window.location.href = "admin_booktype.jsp";
				}
				else if(message == "false"){
					alert("分类修改失败！分类名已存在！");
					window.location.href = "admin_booktype.jsp";
				}	
			}
	     });
	}
}

