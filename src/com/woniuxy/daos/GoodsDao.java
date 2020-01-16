package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import com.woniuxy.entities.*;
import com.woniuxy.tools.ConnectionManager;

public class GoodsDao {
	public List<Goods> getGoodsById(int gid) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from goods where goodsId=?");
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();
			BaseDao<Goods> baseDao = new BaseDao<Goods>();
			

			return baseDao.getResultList(rs, Goods.class);
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void addGoods(Goods g) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"insert into goods(typeId,supplierId,goodsCode,goodsName,goodsPrice,goodsImg)values(?,?,?,?,?,?)");
			ps.setInt(1, g.getGtid());
			ps.setInt(2, g.getSid());
			ps.setString(3, g.getGno());
			ps.setString(4, g.getGname());

			ps.setFloat(5, g.getPrice());
			ps.setString(6, g.getImg());
			ps.executeUpdate();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void updateGoods(Goods g) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			if (isExist(g.getGid())) {
				PreparedStatement ps = conn.prepareStatement(
						"update goods set typeId=?,supplierId=?,goodsCode=?,goodsName=?,goodsPrice=?,goodsImg=? where goodsId=?");

				ps.setInt(1, g.getGtid());
				ps.setInt(2, g.getSid());
				ps.setString(3, g.getGno());
				ps.setString(4, g.getGname());
				ps.setFloat(5, g.getPrice());
				ps.setString(6, g.getImg());
				ps.setInt(7, g.getGid());
				ps.executeUpdate();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public void deleteGoods(int gid) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		try {
			if (isExist(gid)) {
				PreparedStatement ps = conn.prepareStatement("delete from goods where gid=?");
				ps.setInt(1, gid);
				ps.executeUpdate();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public boolean isExist(int gid) {
		Connection conn = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from goods where goodsId=?");
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}


	public int getTotalRows(String gno, String gname, String gtname, String sname) throws SQLException {				
		Connection conn = ConnectionManager.getConnection();
		String sql = "SELECT count(goodsId) as tr FROM goods AS g LEFT JOIN types as gt ON g.typeId = gt.typeId LEFT JOIN supplier as s ON s.supplierId = g.supplierId where 1=1";
		if (gno != null && !gno.equals("")) {
			sql = sql + " and goodsCode like ?";
		}
		if (gname != null && !gname.equals("")) {
			sql = sql + " and goodsName like ?";
		}
		if (gtname != null && !gtname.equals("")) {
			sql = sql + " and typeName like ?";
		}
		if (sname != null && !sname.equals("")) {
			sql = sql + " and supplierName like ?";
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (gno != null && !gno.equals("")) {
				count++;
				ps.setString(count, "%" + gno + "%");
			}
			if (gname != null && !gname.equals("")) {
				count++;
				ps.setString(count, "%" + gname + "%");
			}
			if (gtname != null && !gtname.equals("")) {
				count++;
				ps.setString(count, "%" + gtname + "%");
			}
			if (sname != null && !sname.equals("")) {
				count++;
				ps.setString(count, "%" + sname + "%");
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("tr");
			}
			return 0;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}

	public List<Goods> getGoods(String gno, String gname, String gtname, String sname, PageBean<Goods> pb) throws Exception {
		List<Goods> l = new ArrayList<Goods>();

		
		Connection conn = ConnectionManager.getConnection();
		String sql = "SELECT g.typeId,g.supplierId,g.goodsId,goodsCode,typeName,supplierName,goodsName,goodsPrice,goodsCount,goodsImg FROM goods AS g LEFT JOIN types as gt ON g.typeId = gt.typeId LEFT JOIN supplier as s ON s.supplierId = g.supplierId where 1=1";
		if (gno != null && !gno.equals("")) {
			sql = sql + " and goodsCode like ?";
		}
		if (gname != null && !gname.equals("")) {
			sql = sql + " and goodsName like ?";
		}
		if (gtname != null && !gtname.equals("")) {
			sql = sql + " and typeName like ?";
		}
		if (sname != null && !sname.equals("")) {
			sql = sql + " and supplierName like ?";
		}
		sql = sql +" limit ?,?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (gno != null && !gno.equals("")) {
				count++;
				ps.setString(count, "%" + gno + "%");
			}
			if (gname != null && !gname.equals("")) {
				count++;
				ps.setString(count, "%" + gname + "%");
			}
			if (gtname != null && !gtname.equals("")) {
				count++;
				ps.setString(count, "%" + gtname + "%");
			}
			if (sname != null && !sname.equals("")) {
				count++;
				ps.setString(count, "%" + sname + "%");
			}
			ps.setInt(count+1, (pb.getCurrentPage()-1)*pb.getPageSize());
			ps.setInt(count+2, pb.getPageSize());
			ResultSet rs = ps.executeQuery();

			BaseDao<Goods> baseDao = new BaseDao<Goods>();
			l = baseDao.getResultList(rs, Goods.class);
			return l;
		} finally {
			ConnectionManager.closeConnection(conn);
		}
	}
}
