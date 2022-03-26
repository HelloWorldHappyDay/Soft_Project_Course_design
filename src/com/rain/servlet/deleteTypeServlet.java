package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.TypeDao;

@WebServlet("/deleteTypeServlet")
public class deleteTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public deleteTypeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除图书分类信息
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			int tid = Integer.parseInt(request.getParameter("tid"));
			TypeDao typedao = new TypeDao();
			typedao.deleteBookType(tid);
			response.sendRedirect("/books/admin_booktype.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
