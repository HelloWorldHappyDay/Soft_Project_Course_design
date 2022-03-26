package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public borrowServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BookDao bookdao = new BookDao();
		int tip = Integer.parseInt(request.getParameter("tip"));
		try{
			if(tip==1){
				int bid = Integer.parseInt(request.getParameter("bid"));
				HttpSession session = request.getSession();
				AdminBean admin = new AdminBean();
				String aid = (String)session.getAttribute("aid");
				AdminDao admindao = new AdminDao();
				admin = admindao.get_AidInfo2(aid);
				bookdao.borrowBook(bid,admin);
				response.sendRedirect("/books/select.jsp");
			}
			else{
				int hid = Integer.parseInt(request.getParameter("hid"));
				int bid = Integer.parseInt(request.getParameter("bid"));
				int show = Integer.parseInt(request.getParameter("show"));
				bookdao.returnBook2(hid,bid);
				if(show==1){
					response.sendRedirect("/books/borrow.jsp");
				}
				else{
					response.sendRedirect("/books/admin_borrow.jsp");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
