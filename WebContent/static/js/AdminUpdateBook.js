function updateBook(){ 
	let bid = document.getElementById("updateBookId").value;
	let card =document.getElementById("updateISBN").value;
	let name =document.getElementById("updateBookName").value;
	let type =document.getElementById("updateBookType").value;
	let autho =document.getElementById("updateAutho").value;
	let press =document.getElementById("updatePress").value;
	let num = document.getElementById("updateNum").value;
	if(bid == ""||card == ""||name == ""||type == ""||autho == ""||press ==""||num == ""){
			alert("输入不能为空！请重新输入！")
	}
	else{
		 $.ajax({//把编号带到后台去
	        type : 'post',
	        url : "http://localhost:8080/books/updateBookServlet",
			async:false,
	         data:{"bid":bid,"card":card,"name":name,"type":type,"autho":autho,"press":press,"num":num},
	         success : function(message){//成功的事件
	         	if(message == "true"){
					alert("图书修改成功！");
					window.location.href = "admin_book.jsp";
				}
				else if(message == "false"){
					alert("修改失败！图书号已存在！");
					window.location.href = "admin_book.jsp";
				}	
			}
	     });
	}
}

