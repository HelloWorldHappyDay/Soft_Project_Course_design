package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rain.dao.TypeDao;

@WebServlet("/addBookTypeServlet")
public class addBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public addBookTypeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置编码类型
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			//获取图书分类的名称
			String name = request.getParameter("name");
			TypeDao typedao = new TypeDao();
			//调用函数存入图书分类信息
			boolean flag = typedao.addBookType(name);
			if(flag) {
				response.getWriter().print("true");
			}
			else {
				response.getWriter().print("false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
