package com.neova.isainterface.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.neova.isainterface.entity.InternetSpeed;
import com.neova.isainterface.entity.ResponseData;

public class InternetSpeedDao extends HiveJdbcClient {
	private static final Logger logger = Logger.getLogger(InternetSpeedDao.class);

	// method to insert data in database.
	public int insert(InternetSpeed internetSpeed) {
		logger.info("calling insert() method to insert data.");
		String insertSql = "INSERT INTO TABLE internet_speed VALUES (?,?)";
		String timestamp = internetSpeed.getTimestamp();
		double speed = internetSpeed.getSpeed();
		int rowsAffected = 0;
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertSql);
			preparedStatement.setString(1, timestamp);
			preparedStatement.setDouble(2, speed);
			logger.info("insert query is ("+preparedStatement+").");
			rowsAffected = update(preparedStatement);
		} catch (SQLException e) {
			logger.error("Error while executing query for inserting data. ", e);
		} // End of try-catch block.
		return rowsAffected;
	}// End of insert() method.

	// method to select all data from database.
	public List<InternetSpeed> selectAll() {
		logger.info("calling selectAll() method to select data.");
		String selectAllSql = "SELECT * FROM internet_speed";
		String timestamp;
		double speed;
		InternetSpeed internetSpeed;
		List<InternetSpeed> internetSpeedList = new ArrayList<InternetSpeed>();
		logger.info("select all data query is ("+selectAllSql+").");
		ResultSet res = executeQuery(selectAllSql);
		try {
			while (res.next()) {
				timestamp = res.getString(1);
				speed = res.getDouble(2);
				internetSpeed = new InternetSpeed(timestamp, speed);
				internetSpeedList.add(internetSpeed);
				logger.info("Selected Data :- timestamp = " + String.valueOf(res.getString(1)) + " and speed = " + String.valueOf(res.getDouble(2)) + ".");
			} // End of while loop.
		} catch (SQLException e) {
			logger.error("Error while executing query for selecting data. ", e);
		} // End of try-catch block.
		return internetSpeedList;
	}// End of selectAll() method.

	// method to select max, min and avg data from database for specific time
	// interval
	public static ResponseData selectData(String startDate, String endDate) {
		logger.info("calling selectData() method to select data.");
		logger.info("Provided Data :- startDate = " + startDate + " endDate = " + endDate + ".");
		String selectDataSql = "SELECT MAX(speed),MIN(speed),AVG(speed) FROM internet_speed WHERE (timestamp BETWEEN '"
				+ startDate + "' AND '" + endDate + "') AND (speed < 6000)";
		double maxSpeed;
		double minSpeed;
		double avgSpeed;
		ResponseData responseData = null;
		logger.info("select specified data query is ("+selectDataSql+").");
		ResultSet res = executeQuery(selectDataSql);
		try {
			while (res.next()) {
				maxSpeed = res.getDouble(1);
				minSpeed = res.getDouble(2);
				avgSpeed = res.getDouble(3);
				responseData = new ResponseData(maxSpeed, minSpeed, avgSpeed);
			} // End of while loop.
		} catch (SQLException e) {
			logger.error("Error while executing query for selecting data. ", e);
		} // End of try-catch block.
		return responseData;
	}// End of selectData() method.
}// End of InternetSpeedDao Class
