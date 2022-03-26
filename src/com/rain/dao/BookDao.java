package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.bean.HistoryBean;
import com.rain.util.DBUtil;

public class BookDao {
	/**添加书籍*/
	public boolean addBook(String card, String name, String type, String autho, String press, int num) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		String select_sql = "select* from book where card=?";
		stm = conn.prepareStatement(select_sql);
		stm.setString(1,card);
		ResultSet rs = stm.executeQuery();
		if(rs.next() == true) {
			return false;
		}
		String sql = "insert  into book(card,name,type,autho,press,num) values(?,?,?,?,?,?)";
		stm = conn.prepareStatement(sql);
		stm.setString(1, card);
		stm.setString(2, name);
		stm.setString(3, type);
		stm.setString(4, autho);
		stm.setString(5, press);
		stm.setInt(6, num);
		stm.executeUpdate();
		return true;
	}
	
	/**获取所有书籍信息存入列表*/
	public ArrayList<BookBean> get_ListInfo() throws SQLException{
		ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from book";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			BookBean tag = new BookBean();
			tag.setBid(rs.getInt("bid"));
			tag.setName(rs.getString("name"));
			tag.setCard(rs.getString("card"));
			tag.setType(rs.getString("type"));
			tag.setAutho(rs.getString("autho"));
			tag.setPress(rs.getString("press"));
			tag.setNum(rs.getInt("num"));
			tag_Array.add(tag);
		}		
		return tag_Array;
	}

	/**获取读者指定状态的书籍信息存入列表*/
	public ArrayList<HistoryBean> get_HistoryListInfo(int status,String aid) throws SQLException{
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from history where aid='"+aid+"' and status='"+status+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			HistoryBean tag = new HistoryBean();
			tag.setHid(rs.getInt("hid"));
			tag.setAid(rs.getInt("aid"));
			tag.setBid(rs.getInt("bid"));
			tag.setBookname(rs.getString("bookname"));
			tag.setCard(rs.getString("card"));
			tag.setUsername(rs.getString("username"));
			tag.setName(rs.getString("name"));
			tag.setBegintime(rs.getString("begintime"));
			tag.setEndtime(rs.getString("endtime"));
			tag.setStatus(rs.getInt("status"));
			tag_Array.add(tag);
		}
		return tag_Array;
	}
	
	/**管理员获取指定状态的书籍信息存入列表 */
	public ArrayList<HistoryBean> get_HistoryListInfo2(int status) throws SQLException{
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from history where status='"+status+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			HistoryBean tag = new HistoryBean();
			tag.setHid(rs.getInt("hid"));
			tag.setAid(rs.getInt("aid"));
			tag.setBid(rs.getInt("bid"));
			tag.setBookname(rs.getString("bookname"));
			tag.setCard(rs.getString("card"));
			tag.setUsername(rs.getString("username"));
			tag.setName(rs.getString("name"));
			tag.setBegintime(rs.getString("begintime"));
			tag.setEndtime(rs.getString("endtime"));
			tag.setStatus(rs.getInt("status"));
			tag_Array.add(tag);
		}
		return tag_Array;
	}

	/**获取指定书籍的信息存入列表*/
	public BookBean get_BookInfo(int bid) throws SQLException{
		BookBean tag = new BookBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from book where bid='"+bid+"'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			tag.setBid(rs.getInt("bid"));
			tag.setName(rs.getString("name"));
			tag.setCard(rs.getString("card"));
			tag.setType(rs.getString("type"));
			tag.setAutho(rs.getString("autho"));
			tag.setPress(rs.getString("press"));
			tag.setNum(rs.getInt("num"));
		}
		return tag;
	}
	
	/**管理员更新图书信息*/
	public boolean updateBook(int bid, String card, String name, String type, String autho, String press, int num) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		String select_sql = "select* from book where card=? and bid<>?";
		stm = conn.prepareStatement(select_sql);
		stm.setString(1,card);
		stm.setInt(2,bid);
		ResultSet rs = stm.executeQuery();
		if(rs.next() == true) {
			return false;
		}
		String sql = "update book set name=?,card=?,type=?,autho=?,press=?,num=? where bid=?";
		stm = conn.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, card);
		stm.setString(3, type);
		stm.setString(4, autho);
		stm.setString(5, press);
		stm.setInt(6, num);
		stm.setInt(7, bid);
		stm.executeUpdate();
		updateBookHistory(card,name,bid);
		return true;
	}
	
	/**管理员删除指定书籍*/
	public void deleteBook(int bid) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from book where bid=?";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setInt(1, bid);
		stm.executeUpdate();
	}
	
	/**图书查询*/
	public ArrayList<BookBean> getLikeList(String name) throws SQLException {
		ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from book where name like '%"+name+"%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		stm = conn.prepareStatement(sql);
		rs = stm.executeQuery();
		while(rs.next()){
			BookBean tag = new BookBean();
			tag.setBid(rs.getInt("bid"));
			tag.setName(rs.getString("name"));
			tag.setCard(rs.getString("card"));
			tag.setType(rs.getString("type"));
			tag.setAutho(rs.getString("autho"));
			tag.setPress(rs.getString("press"));
			tag.setNum(rs.getInt("num"));
			tag_Array.add(tag);
		}
		return tag_Array;
	}
	
	/**更新历史表中的图书信息*/
	public void updateBookHistory(String card, String bookname, int bid) throws SQLException {
		Connection conn = DBUtil.getConnectDb();
		String sql = "update history set card=?,bookname=? where bid=?";
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setString(1, card);
		stm.setString(2, bookname);
		stm.setInt(3, bid);
		stm.executeUpdate();
	}
	
	/**读者借书*/
	public void borrowBook(int bid, AdminBean adminbean) throws SQLException {
		BookBean bookbean = new BookBean();
		bookbean = this.get_BookInfo(bid);
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH)+1;   
		int day = c.get(Calendar.DATE);  
		String begintime = ""+year+"-"+month+"-"+day;
		month = month + 1;
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert  into history(aid,bid,card,bookname,username,name,begintime,endtime,status) values(?,?,?,?,?,?,?,?,?)";
		String subNum = "update book set num=num-1 where bid=?";
		PreparedStatement subNumstm = null;
		subNumstm = conn.prepareStatement(subNum);
		subNumstm.setInt(1, bid);
		subNumstm.executeUpdate();
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setInt(1, adminbean.getAid());
		stm.setInt(2, bookbean.getBid());
		stm.setString(3, bookbean.getCard());
		stm.setString(4, bookbean.getName());
		stm.setString(5, adminbean.getUsername());
		stm.setString(6, adminbean.getName());
		stm.setString(7, begintime);
		stm.setString(8, endtime);
		stm.setInt(9, 1);
		stm.executeUpdate();
	}
	
	/**还书*/
	public void returnBook2(int hid,int bid) throws SQLException {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR)+1;  
		int month = c.get(Calendar.MONTH)+1;   
		int day = c.get(Calendar.DATE)+1; 
		String endtime = ""+year+"-"+month+"-"+day;
		Connection conn = DBUtil.getConnectDb();
		String sql = "update history set endtime=?,status=? where hid=?";
		String addBookNum = "update book set num=num+1 where bid=?";
		PreparedStatement addStm = null;
		addStm = conn.prepareStatement(addBookNum);
		addStm.setInt(1, bid);
		addStm.executeUpdate();
		PreparedStatement stm = null;
		stm = conn.prepareStatement(sql);
		stm.setString(1, endtime);
		stm.setInt(2, 0);
		stm.setInt(3, hid);
		stm.executeUpdate();
	}
}
