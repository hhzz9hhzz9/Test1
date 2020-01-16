package com.woniuxy.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.woniuxy.daos.ShoppingDao;

public class ConnectionManager {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static int maxActive;
	private static int minIdle;
	private static int maxWait;
	private static DruidDataSource dds;

	static{
		try {
			String path = ConnectionManager.class.getClassLoader().getResource("/").getPath();
			InputStream input = new FileInputStream(new File(path+File.separator+"jdbc.properties"));
			Properties prop = new Properties();
			prop.load(input);
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			driver = prop.getProperty("driver");
			password = prop.getProperty("password");
			maxActive = Integer.parseInt(prop.getProperty("maxActive"));
			minIdle = Integer.parseInt(prop.getProperty("minIdle"));
			maxWait = Integer.parseInt(prop.getProperty("maxWait"));
			
			dds = new DruidDataSource();
			dds.setDriverClassName(driver);
			dds.setUrl(url);
			dds.setUsername(user);
			dds.setPassword(password);
			
			dds.setMaxActive(maxActive);
			dds.setMinIdle(minIdle);
			dds.setMaxWait(maxWait);
			
			//活动连接数到达上限的补救措施，让连接池视图去回收超时的连接
			dds.setRemoveAbandonedTimeout(10);
			dds.setRemoveAbandoned(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			Connection conn = dds.getConnection();
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
