package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rain.dao.AdminDao;

@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public addUserServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		AdminDao userdao = new AdminDao();
		int flag = 0;
		try {
			flag = userdao.Register(img,username,password,name,email,phone,lend_num,max_num);
			if(flag == 0) {
				response.getWriter().print("0");
			}		
			else if(flag == 1) {
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
