package com.neova.isainterface.quartz.job;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.neova.isainterface.quartz.task.PushHourlyDataTask;

public class PushHourlyDataJob extends QuartzJobBean {
	
	private PushHourlyDataTask pushHourlyDataTask;
	private static final Logger logger = Logger.getLogger(PushHourlyDataJob.class);
	public void setPushHourlyDataTask(PushHourlyDataTask pushHourlyDataTask) {
		this.pushHourlyDataTask = pushHourlyDataTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			pushHourlyDataTask.pushHourlyData();
		} catch (MalformedURLException e) {
			logger.error("Error while executing pushHourlyData(). ", e);
		} catch (IOException e) {
			logger.error("Error while executing pushHourlyData(). ", e);
		}
	}//End of executeInternal() Method.
}//End of PushHourlyDataJob Class.
