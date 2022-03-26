package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rain.dao.TypeDao;

@WebServlet("/updateBookTypeServlet")
public class updateBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public updateBookTypeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String name = request.getParameter("name");
			int tid = Integer.parseInt(request.getParameter("tid"));
			TypeDao typedao = new TypeDao();
			boolean flag = typedao.updateTypeBook(tid,name);
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
