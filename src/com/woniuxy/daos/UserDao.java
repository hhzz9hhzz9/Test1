package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.woniuxy.entities.User;
import com.woniuxy.tools.ConnectionManager;

public class UserDao {
	public int isExist(User u) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			
			PreparedStatement ps = conn.prepareStatement("select userid from users where username=? and userpwd=? and userStatus='∆Ù”√'");
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getUserPwd());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int uid = rs.getInt("userid");
				return uid;
			}
			return 0;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
	public void updPwd(int uid,String pwd) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update users set userPwd=? where userId=?");
			ps.setString(1, pwd);
			ps.setInt(2, uid);
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}		
	}
	public List<User> getUsers() throws Exception {
		
		Connection conn = ConnectionManager.getConnection();
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from users");

			ResultSet rs = ps.executeQuery();
			BaseDao<User> baseDao = new BaseDao<User>();
			List<User> list = baseDao.getResultList(rs, User.class);
			
			return list;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
	public void updOthers(int uid,String data) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update users set userStatus=? where userId=?");
			ps.setString(1, data);
			ps.setInt(2, uid);
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}				
	}
	public void addUser(String userName,String userRole) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into users(userName,userRole) values(?,?)");
			ps.setString(1, userName);
			ps.setString(2, userRole);
			
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
	public void updUser(String userName, String userRole,int uid) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update users set userName=?,userRole=? where userId=?");
			ps.setString(1, userName);
			ps.setString(2, userRole);
			ps.setInt(3, uid);
			ps.executeUpdate();
			
		} finally {
			ConnectionManager.closeConnection(conn);
		}				
	}
}
