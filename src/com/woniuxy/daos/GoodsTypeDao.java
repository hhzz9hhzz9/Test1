package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import com.woniuxy.entities.GoodsType;
import com.woniuxy.tools.ConnectionManager;

public class GoodsTypeDao {
	

	public void addType(GoodsType gt) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into types(typeCode,typeName)values(?,?)");
			ps.setString(1, gt.getGtno());
			ps.setString(2, gt.getGtname());
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void updType(GoodsType gt) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update types set typeCode=?,typeName=?where typeId=?");
			ps.setString(1, gt.getGtno());
			ps.setString(2, gt.getGtname());
			ps.setInt(3, gt.getGtid());
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
	
	public void delType(int gtid) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			
				PreparedStatement ps = conn.prepareStatement("delete from types where typeId=?");
				ps.setInt(1, gtid);
				ps.executeUpdate();
			
		} finally {

			ConnectionManager.closeConnection(conn);

		}
	}
	public List<GoodsType> getGoodsType() throws Exception {

		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from types");
			ResultSet rs = ps.executeQuery();
			BaseDao<GoodsType> baseDao = new BaseDao<GoodsType>();
			return baseDao.getResultList(rs, GoodsType.class);

		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public List<GoodsType> getGoodsType(String gtno, String gtname) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "select * from types where 1=1";
			if (gtno != null && !gtno.equals("")) {
				sql = sql + " and typeCode like ?";
			}
			if (gtname != null && !gtname.equals("")) {
				sql = sql + " and typeName like ?";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (gtno != null && !gtno.equals("")) {
				count++;
				ps.setString(count, "%"+gtno+"%");
			}
			if (gtname != null && !gtname.equals("")) {
				count++;
				ps.setString(count, "%"+gtname+"%");
			}
			ResultSet rs = ps.executeQuery();
			BaseDao<GoodsType> baseDao = new BaseDao<GoodsType>();
			return baseDao.getResultList(rs, GoodsType.class);

		} finally {
			ConnectionManager.closeConnection(conn);
		}

	}
}
