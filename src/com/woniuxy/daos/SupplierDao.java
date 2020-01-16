package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

import com.woniuxy.entities.Supplier;
import com.woniuxy.tools.ConnectionManager;

public class SupplierDao {

	public void addSupplier(Supplier s) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into supplier(supplierCode,supplierName,supplierTel)values(?,?,?)");
			ps.setString(1, s.getSno());
			ps.setString(2, s.getSname());
			ps.setString(3, s.getTel());
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void updateSupplier(Supplier s) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update supplier set supplierCode=?,supplierName=?,supplierTel=?where supplierId=?");
			ps.setString(1, s.getSno());
			ps.setString(2, s.getSname());
			ps.setString(3, s.getTel());
			ps.setInt(4, s.getSid());
			ps.executeUpdate();
		}  finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void delSupplier(int sid) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from supplier where supplierId=?");
			ps.setInt(1, sid);
			ps.executeUpdate();
		} finally {

			ConnectionManager.closeConnection(conn);

		}
	}

	public List<Supplier> getSupplier(String sno, String sname) throws Exception {
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			String sql = "select * from supplier where 1=1";
			if (sno != null && !sno.equals("")) {
				sql = sql + " and supplierCode like ?";
			}
			if (sname != null && !sname.equals("")) {
				sql = sql + " and supplierName like ?";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (sno != null && !sno.equals("")) {
				count++;
				ps.setString(count, "%"+sno+"%");
			}
			if (sname != null && !sname.equals("")) {
				count++;
				ps.setString(count, "%"+sname+"%");
			}
			ResultSet rs = ps.executeQuery();
			BaseDao<Supplier> baseDao = new BaseDao<Supplier>();
			return baseDao.getResultList(rs, Supplier.class);

		} finally {
			ConnectionManager.closeConnection(conn);
		}

	}
	
}
