package com.rain.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.baidu.ai.aip.FaceSearch;
import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public loginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//登录的判断
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			//获取账号和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String img = request.getParameter("imagebase64");
			AdminDao userdao = new AdminDao();
			//对账号和密码进行判断
			boolean result = userdao.Login_verify(username, password);
			HttpSession session = request.getSession();
			String score = FaceSearch.search(img,username);
			//判断输入正确
			if(result){
				if(score.equals("false")) {
					response.getWriter().print("false"+",0"+",true");
				}
				else {
					AdminBean adminbean = new AdminBean();
					AdminDao admindao = new AdminDao();
					//更加账号和密码查找出读者的信息
					adminbean = admindao.get_AdminInfo(username,password);
					//将aid存入session中
					session.setAttribute("aid", ""+adminbean.getAid());
					//设置session的失效时间
					session.setMaxInactiveInterval(6000);
					//根据status的值来判断是管理员，还是读者，status=1为读者
					if(adminbean.getStatus() == 1){
						response.getWriter().print(score+",1"+",true");
					}
					else{
						response.getWriter().print(score+",2"+",true");
					}
				}
			}
			else{
				//没有找到对应的账号和密码，返回重新登录
				response.getWriter().print(score+",0"+",false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
