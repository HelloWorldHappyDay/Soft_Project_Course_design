function addBook(){ 
	let card =document.getElementById("addISBN").value;
	let name =document.getElementById("addBookName").value;
	let type =document.getElementById("addBookType").value;
	let autho =document.getElementById("addAutho").value;
	let press =document.getElementById("addPress").value;
	let num = document.getElementById("addNum").value;
	if(card == ""||name == ""||type == "无分类"||autho == ""||press == ""||num == ""){
		alert("输入不能为空！")
	}
	else if(num<0){
		alert("图书数量不能小于零！");
	}
	else{
		 $.ajax({//把编号带到后台去
	        type : 'post',
	        url : "http://localhost:8080/books/addBookServlet",
			async:false,
	         data:{"card":card,"name":name,"type":type,"autho":autho,"press":press,"num":num},
	         success : function(message){//成功的事件
	         	if(message == "true"){
					alert("图书添加成功！");
					window.location.href = "admin_book.jsp";
				}
				else if(message == "false"){
					alert("添加失败！图书号已存在！");
					window.location.href = "admin_book.jsp";
				}	
			}
	     });
	}
}

