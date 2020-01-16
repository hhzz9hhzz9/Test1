package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.woniuxy.entities.Shopping;
import com.woniuxy.tools.ConnectionManager;

public class ShoppingDao {
	public void addShop(Shopping shopping) throws SQLException {
		boolean tag = isExsit(shopping);
		Connection conn=null;
		try {
			conn= ConnectionManager.getConnection();
				String sql="";
				if (tag) {
				sql= "update shoppings set shoppingCount=shoppingCount+1,modifyTimes=now() where goodsId=? and cusId=?";
			}
				else {
				sql = "insert into shoppings(goodsId,cusId,shoppingCount,addTimes) values(?,?,1,now())";
			}
				PreparedStatement ps =conn.prepareStatement(sql);
				ps.setInt(1, shopping.getGid());
				ps.setInt(2, shopping.getCusId());
				ps.executeUpdate();
		} 	
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
		
	}

	private boolean isExsit(Shopping shopping) throws SQLException {
		Connection conn=null;
		try {
			conn=ConnectionManager.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from shoppings where goodsId=? and cusId=?");
			
			ps.setInt(1, shopping.getGid());
			ps.setInt(2, shopping.getCusId());
			
			ResultSet rs=ps.executeQuery();
			
			return rs.next();
			
		} 
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
	}

	public int getTotalShop(int cusId) throws SQLException {
		Connection conn=null;
		try {
			conn=ConnectionManager.getConnection();
			PreparedStatement ps=conn.prepareStatement("select count(cusId) as 'total' from shoppings where cusId=?");
			
			
			ps.setInt(1, cusId);
			
			ResultSet rs=ps.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("total");
			}
			return 0;
			
		} 
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
	}
	public List<Shopping> getShoppingByCusId(int cusId) throws Exception {
		Connection conn=null;
		try {
			conn=ConnectionManager.getConnection();
			PreparedStatement ps=conn.prepareStatement("select shoppingId,shoppings.goodsId,addTimes,modifyTimes,shoppingCount,cusId,goodsName,goodsPrice,goodsImg,goodsCount from shoppings LEFT JOIN goods on shoppings.goodsId=goods.goodsId where cusId=?");
			ps.setInt(1,cusId);
			
			ResultSet rs=ps.executeQuery();
			
			BaseDao<Shopping> baseDao = new BaseDao<Shopping>();
			List<Shopping> shList = baseDao.getResultList(rs, Shopping.class);
			
			return shList;
			
		} 
		finally{
			
			ConnectionManager.closeConnection(conn);
		}			
	}

	public void updShopping(Shopping shopping) throws SQLException {
		Connection conn=null;
		try {
			conn= ConnectionManager.getConnection();

				String sql= "update shoppings set shoppingCount=?,modifyTimes=now() where shoppingId=?";
				PreparedStatement ps =conn.prepareStatement(sql);
				ps.setInt(1, shopping.getShCount());
				ps.setInt(2, shopping.getShId());
				ps.executeUpdate();
		} 	
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
	}
	public List<Shopping> getSubShoppings(String shoppingIds) throws Exception {
		Connection conn=null;
		try {
			conn=ConnectionManager.getConnection();
			
			String str="("+shoppingIds+")";
			PreparedStatement ps=conn.prepareStatement("select shoppingId,shoppings.goodsId,addTimes,modifyTimes,shoppingCount,cusId,goodsName,goodsPrice,goodsImg,goodsCount from shoppings LEFT JOIN goods on shoppings.goodsId=goods.goodsId where shoppingId in "+str);
			
			ResultSet rs=ps.executeQuery();
			List<Shopping> shList= new BaseDao<Shopping>().getResultList(rs, Shopping.class);
			
			return shList;
			
		} 
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
	}
	
}
