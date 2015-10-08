package com.neova.isainterface.quartz.task;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.neova.isainterface.dao.InternetSpeedDao;
import com.neova.isainterface.entity.InternetSpeed;

public class PushHourlyDataTask {
	private static final String downloadLink = "http://www.us.apache.org/dist//activemq/5.12.0/apache-activemq-5.12.0-bin.zip";
	double kbPerSec;
	private static final Logger logger = Logger.getLogger(PushHourlyDataTask.class);

	public void pushHourlyData() throws MalformedURLException, IOException {

		BufferedInputStream in = new BufferedInputStream(new URL(downloadLink).openStream());
		int i = 0;
		final int cycles = 10;
		try {
			long totalDownload = 0; // total bytes downloaded
			final int BUFFER_SIZE = 1024; // size of the buffer
			byte[] data = new byte[BUFFER_SIZE]; // buffer
			int dataRead = 0; // data read in each try
			long startTime = System.nanoTime(); // starting time of download
			int readLength = in.read(data, 0, BUFFER_SIZE);

			while ((dataRead = readLength) > 0 && i <= cycles) {
				i++;
				totalDownload += dataRead; // adding data downloaded to total
											// data
				readLength = in.read(data, 0, BUFFER_SIZE);
			}

			/* download rate in bytes per second */
			long timeElapsed = (System.nanoTime() - startTime);
			float bytesPerSec = totalDownload * 1000000000 / (timeElapsed);
			logger.info(timeElapsed + " Nano Seconds Time Elapsed in downloading.");
			/* download rate in kilobits per second */
			kbPerSec = bytesPerSec * 8 / (BUFFER_SIZE);
			logger.info("Downloading speed in Kbps = " + kbPerSec + " Kbps");
			// Insert this data in Hadoop
			// using quartz in every 10 minutes.
		} catch (Exception e) {
			logger.error(" Exception while downloading data,", e);
		} finally {
			in.close();
		} // End of try-catch-finally Method.

		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(currentDate);

		InternetSpeed internetSpeed = new InternetSpeed(currentTime, kbPerSec);
		InternetSpeedDao internetSpeedDao = new InternetSpeedDao();
		int rowsAffected = internetSpeedDao.insert(internetSpeed);
		logger.info("No. of rows Inserted.." + rowsAffected);
		logger.info("Calling Push Hourly Data...");
	}// End of pushHourlyData() Method.
}// End of PushHourlyDataTask Class.
