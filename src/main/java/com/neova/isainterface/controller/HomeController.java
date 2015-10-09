package com.neova.isainterface.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neova.isainterface.dao.InternetSpeedDao;
import com.neova.isainterface.entity.ResponseData;
import com.neova.isainterface.quartz.task.PushHourlyDataTask;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws MalformedURLException, IOException {

		logger.info("Welcome home! The client locale is  {" + locale + "}");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		// TODO: start date end date is hard coded for now.
		ResponseData responseData = InternetSpeedDao.selectData("2015-10-06 11:30:06.0", "2015-10-08 19:00:05.0");
		PushHourlyDataTask pushHourlyDataTask = new PushHourlyDataTask();
		double currentDownloadSpeed = pushHourlyDataTask.downloadingSpeedInKbps();
		logger.info("Adding serverTime to  model.");
		model.addAttribute("serverTime", formattedDate);
		logger.info("Adding responseData object to  model.");
		model.addAttribute("responseData", responseData);
		logger.info("Adding currentDownloadSpeed to model.");
		model.addAttribute("currentDownloadSpeed", currentDownloadSpeed);
		return "home";
	}
}
