package com.rain.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.baidu.ai.aip.FaceAdd;
import com.baidu.ai.aip.FaceDelete;
import com.baidu.ai.aip.FaceSearch;
import com.rain.bean.AdminBean;
import com.rain.util.DBUtil;

public class AdminDao {
	/** 登录时账号密码匹配验证*/
	public boolean Login_verify(String username,String password) throws SQLException{
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username='"+username+"' and password='"+password+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}

	/**
	 * 注册账号
	 * 数据添加到数据库
	 *人脸信息上传到云端 */
	public int Register(String imag,String username, String password, String name, String email, String phone,int lend_num,int max_num) throws SQLException, IOException {
				Connection conn = DBUtil.getConnectDb();
				PreparedStatement stm = null;
				String result = "0";
				ResultSet rs = null;
				String select_sql = "select* from admin where username=?";
				stm = conn.prepareStatement(select_sql);
				stm.setString(1,username);
				rs = stm.executeQuery();
				if(rs.next() == true) {
					return 0;
				}
				result = FaceAdd.add(imag, name, username).split(":")[1].split(",")[0];
				if(result.equals("0")){
					String sql = "insert  into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
					stm = conn.prepareStatement(sql);
					stm.setInt(1, 1);
					stm.setString(2, username);
					stm.setString(3, password);
					stm.setString(4, name);
					stm.setString(5, email);
					stm.setString(6, phone);
					stm.setInt(7, lend_num);
					stm.setInt(8, max_num);
					stm.executeUpdate();
					return 1;
				}
				else {
					return 2;
				}
	}
	
	/**根据账号密码获取用户信息*/
	public AdminBean get_AdminInfo(String username, String password) throws SQLException {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username='"+username+"' and password='"+password+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		if(rs.next()){
			adminbean.setAid(rs.getInt("aid"));
			adminbean.setUsername(rs.getString("username"));
			adminbean.setName(rs.getString("name"));
			adminbean.setPassword(rs.getString("password"));
			adminbean.setEmail(rs.getString("email"));
			adminbean.setPhone(rs.getString("phone"));
			adminbean.setStatus(rs.getInt("status"));
			adminbean.setLend_num(rs.getInt("lend_num"));
			adminbean.setMax_num(rs.getInt("max_num"));
		}
		return adminbean;
	}

	/**管理员获取所有读者信息存入列表*/
	public ArrayList<AdminBean> get_ListInfo() throws SQLException{
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			AdminBean adminbean = new AdminBean();
			adminbean.setAid(rs.getInt("aid"));
			adminbean.setUsername(rs.getString("username"));
			adminbean.setName(rs.getString("name"));
			adminbean.setPassword(rs.getString("password"));
			adminbean.setEmail(rs.getString("email"));
			adminbean.setPhone(rs.getString("phone"));
			adminbean.setStatus(rs.getInt("status"));
			adminbean.setLend_num(rs.getInt("lend_num"));
			adminbean.setMax_num(rs.getInt("max_num"));
			tag_Array.add(adminbean);
		}
		return tag_Array;
	}

	/**根据用户id获取用户信息*/
	public AdminBean get_AidInfo(int aid) throws SQLException{
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid="+aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		if(rs.next()){
			adminbean.setAid(rs.getInt("aid"));
			adminbean.setUsername(rs.getString("username"));
			adminbean.setName(rs.getString("name"));
			adminbean.setPassword(rs.getString("password"));
			adminbean.setEmail(rs.getString("email"));
			adminbean.setPhone(rs.getString("phone"));
			adminbean.setStatus(rs.getInt("status"));
			adminbean.setLend_num(rs.getInt("lend_num"));
			adminbean.setMax_num(rs.getInt("max_num"));
		}
		return adminbean;
	}

	/**根据用户id获取用户信息*/
	public AdminBean get_AidInfo2(String aid) throws SQLException{
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid="+aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		if(rs.next()){
			adminbean.setAid(rs.getInt("aid"));
			adminbean.setUsername(rs.getString("username"));
			adminbean.setName(rs.getString("name"));
			adminbean.setPassword(rs.getString("password"));
			adminbean.setEmail(rs.getString("email"));
			adminbean.setPhone(rs.getString("phone"));
			adminbean.setStatus(rs.getInt("status"));
			adminbean.setLend_num(rs.getInt("lend_num"));
			adminbean.setMax_num(rs.getInt("max_num"));
		}
		return adminbean;
	}
	
	
	/**根据用户名得出读者未归还的书的数量*/
	public int getNotReturn(String username) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "select* from history where username=? and status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		int count = 0;
		stm = conn.prepareStatement(sql);
		stm.setString(1,username);
		rs = stm.executeQuery();
		while(rs.next()) {
			count++;
		}
		return count;
	}
	
	/**根据用户名获取读者已归还书的数量*/
	public int getReturn(String username) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "select* from history where username=? and status=0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		int count = 0;
		stm = conn.prepareStatement(sql);
		stm.setString(1,username);
		rs = stm.executeQuery();
		while(rs.next()) {
			count++;
		}
		return count;
	}
	
	/**读者修改个人信息*/
	public void updateUser(int aid, String username, String password, String name, String email, String phone,int lend_num, int max_num) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		stm = conn.prepareStatement(sql);
		stm.setString(1, username);
		stm.setString(2, name);
		stm.setString(3, email);
		stm.setString(4, phone);
		stm.setString(5, password);
		stm.setInt(6, lend_num);
		stm.setInt(7, max_num);
		stm.setInt(8, aid);
		stm.executeUpdate();
	}
	
	/**管理员修改读者信息*/
	public int adminUpdateUser(String img, int aid, String username, String password, String name, String email, String phone,int lend_num, int max_num) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		ResultSet rs = null;
		String select_sql = "select* from admin where username=?and aid<>?";
		stm = conn.prepareStatement(select_sql);
		stm.setString(1,username);
		stm.setInt(2,aid);
		rs = stm.executeQuery();
		if(rs.next() == true) {
			return 0;
		}
		AdminBean olduser = new AdminBean();
		AdminDao userdao = new AdminDao();
		olduser = userdao.get_AidInfo(aid);
		String result = FaceSearch.search(img,olduser.getUsername());
		if(!result.equals("false")) {
			FaceDelete.delete(olduser.getUsername());
			FaceAdd.add(img,name,username);
			String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, lend_num);
			stm.setInt(7, max_num);
			stm.setInt(8, aid);
			stm.executeUpdate();
			updateAdminHistory(name,username,aid);
			return 1;
		}
		else {
			return 2;
		}
		
	}
	
	
	/**更新历史表中的用户信息*/
	public void updateAdminHistory(String name, String username, int aid) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "update history set username=?,name=? where aid=?";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setString(1, username);
		stm.setString(2, name);
		stm.setInt(3, aid);
		stm.executeUpdate();
	}
	

	/**管理员删除书籍*/
	public void deleteUser(int aid) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setInt(1, aid);
		stm.executeUpdate();
	}
}
