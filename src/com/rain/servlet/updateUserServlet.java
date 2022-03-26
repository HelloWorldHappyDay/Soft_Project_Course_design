package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rain.dao.AdminDao;

@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	public updateUserServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//修改读者的信息
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String img = request.getParameter("imagebase64");
		int lend_num = Integer.parseInt(request.getParameter("lend_num"));
		int max_num = Integer.parseInt(request.getParameter("max_num"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		AdminDao userdao = new AdminDao();
		try {
			int flag = userdao.adminUpdateUser(img,aid,username,password,name,email,phone,lend_num,max_num);
			if(flag == 0) {
				response.getWriter().print("0");
			}
			else if(flag == 1){
				response.getWriter().print("1");
			}
			else if(flag == 2) {
				response.getWriter().print("2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
