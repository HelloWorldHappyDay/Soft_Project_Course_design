package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.BookDao;

@WebServlet("/addBookServlet")
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public addBookServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取要添加图书的信息
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		BookDao bookdao = new BookDao();
		//调用函数，存入图书
		try {
			boolean flag = bookdao.addBook(card,name,type,autho,press,num);
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
