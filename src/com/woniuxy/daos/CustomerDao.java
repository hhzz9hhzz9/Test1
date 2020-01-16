package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.woniuxy.entities.Customer;
import com.woniuxy.tools.ConnectionManager;

public class CustomerDao {
	public List<Customer> getList() throws Exception {
		Connection conn = ConnectionManager.getConnection();
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from customer");

			ResultSet rs = ps.executeQuery();
			BaseDao<Customer> baseDao = new BaseDao<Customer>();
			List<Customer> list = baseDao.getResultList(rs, Customer.class);
			
			return list;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void updStatus(int uid, String data) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update customer set cusStatus=? where cusId=?");
			ps.setString(1, data);
			ps.setInt(2, uid);
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}			
	}
	public void addCus(String cname,String ctel,String caddr) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into customer(cusName,cusTel,cusAddress) values(?,?,?)");
			
			ps.setString(1, cname);
			ps.setString(2, ctel);
			ps.setString(3, caddr);
			
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
	public void updCus(Customer cus) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update customer set cusName=?,cusTel=?,cusAddress=? where cusId=?");
			ps.setString(1, cus.getCname());
			ps.setString(2, cus.getCtel());
			ps.setString(3, cus.getCaddr());
			ps.setInt(4, cus.getCid());
			ps.executeUpdate();
			
		} finally {
			ConnectionManager.closeConnection(conn);
		}				
	}

	public int isExit(Customer cus) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			
			PreparedStatement ps = conn.prepareStatement("select cusId from customer where cusName=? and cusPwd=? and cusStatus='∆Ù”√'");
			ps.setString(1, cus.getCname());
			ps.setString(2, cus.getCpwd());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int uid = rs.getInt("cusId");
				return uid;
			}
			return 0;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
}
