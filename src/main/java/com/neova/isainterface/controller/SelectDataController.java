package com.neova.isainterface.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neova.isainterface.dao.InternetSpeedDao;
import com.neova.isainterface.entity.ResponseData;

@RestController
public class SelectDataController {
	@RequestMapping(value = "/select")
	public @ResponseBody ResponseData selectData(@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate) {
		ResponseData responseData = InternetSpeedDao.selectData(startDate, endDate);
		return responseData;
	}// End of selectData() Method.
}// End of SelectDataController class.