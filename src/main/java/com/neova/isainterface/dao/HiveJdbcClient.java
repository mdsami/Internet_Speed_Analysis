package com.neova.isainterface.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class HiveJdbcClient {
	private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	private static final String HIVE_URL = "jdbc:hive2://192.168.1.211:10000/isanalyser";
	private static final String HIVE_UNAME = "scott";
	private static final String HIVE_PWD = "tiger";
	private static Connection con;
	private static final Logger logger = Logger.getLogger(HiveJdbcClient.class);

	public HiveJdbcClient() {
	}

	public static Connection getConnection() {
		try {
			if (con == null) {
				synchronized (HiveJdbcClient.class) {
					if (con == null) {
						Class.forName(DRIVER_NAME);
						con = DriverManager.getConnection(HIVE_URL, HIVE_UNAME, HIVE_PWD);
						logger.info("Successfuly Connected to database...!");
					}
				} // End of synchronized block.
			} // End of if block.
		} catch (SQLException e) {
			logger.error("Can Not able to connect to database please check your settings. ", e);
		} catch (ClassNotFoundException e) {
			logger.error("Can Not able to connect to database please check your settings. ", e);
		} // End of try-catch block.
		return con;
	}// End of getConnection() Method.

	public static ResultSet executeQuery(String sql) {
		ResultSet set = null;
		try {
			if (sql != null) {
				set = getConnection().createStatement().executeQuery(sql);
			}
		} catch (SQLException e) {
			logger.error("Error while executing query. ", e);
		} // End of try-catch block.
		return set;
	}// End of executeQuery() Method.

	public static Integer executeUpdate(String sql) {
		int rows = 0;
		try {
			rows = getConnection().createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("Error while executing query. ", e);
		} // End of try-catch block.
		return rows;
	}// End of executeUpdate() Method.

	public int update(PreparedStatement preparedStatement) {
		int rowsAffected = 0;
		try {
			rowsAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while executing query. ", e);
		} // End of try-catch block.
		return rowsAffected;
	}// End of update() Method.

}// End of HiveJdbcClient Class.
