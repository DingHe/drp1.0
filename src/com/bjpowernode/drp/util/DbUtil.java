package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 封装数据常用操作
 * @author Administrator
 *
 */
public class DbUtil {

	/**
	 * 取得Connection
	 * @return
	 */
	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@localhost:1521:bjpowern";
//			String username = "drp1";
//			String password = "drp1";
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
		Connection conn = null;
		try {
			JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
			Class.forName(jdbcConfig.getDriverName());
			conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUserName(), jdbcConfig.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(DbUtil.getConnection());
	}
}
