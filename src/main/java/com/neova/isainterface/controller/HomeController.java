package com.neova.isainterface.controller;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is  {" + locale + "}");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		// TODO: start date end date is hard coded for now.
		ResponseData responseData = InternetSpeedDao.selectData("2015-10-06 11:30:06.0", "2015-10-08 19:00:05.0");
		logger.info("Adding formattedDate " + formattedDate + " to attribute serverTime in model.");
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("responseData", responseData);
		return "home";
	}
}
