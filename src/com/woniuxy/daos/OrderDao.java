package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.woniuxy.entities.Order;
import com.woniuxy.tools.ConnectionManager;

public class OrderDao {

	public String addOrder(Order order, String sids) throws SQLException {
		Connection conn=null;
		String orderId=generateOrderId();
		try {
			conn=ConnectionManager.getConnection();
			//关闭自动提交事务的模式
			conn.setAutoCommit(false);
		
			PreparedStatement ps=conn.prepareStatement("insert into orders(ordersId,cusId,ordersPrice,orderStatus,ordersTime,ordersRemark)values(?,?,?,'未支付',now(),?) ");
			ps.setString(1,orderId);
			ps.setInt(2, order.getCus_id());
			ps.setFloat(3, order.getOrders_price());
			ps.setString(4, order.getOrders_remark());
			ps.executeUpdate();
			
			String sql="insert into ordersdetail(ordersId,goodsId,ordersDetailPrice,ordersDetailCount)"
						+"select "+orderId+",shoppings.goodsId,goodsPrice,shoppingCount from shoppings" 
						+" LEFT JOIN goods on shoppings.goodsId=goods.goodsId where shoppingId in ("+sids+")";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			ps=conn.prepareStatement("delete from shoppings where shoppingId in("+sids+")");
			
			ps.executeUpdate();
			
			conn.commit();//提交事务
			return orderId;
			
		} 
		finally{
			
			ConnectionManager.closeConnection(conn);
		}
		
	}

	private String generateOrderId() {
		// TODO Auto-generated method stub
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String orderIdTime=sdf.format(now);
		Random random=new Random();
		int randomNumer=random.nextInt(100000);
		return orderIdTime+randomNumer;
	}
	
	

}
