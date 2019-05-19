package com.project.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.bean.ResponseData;
import com.project.common.constants.CommonConstant.RESPONSE_STATUS;
import com.project.common.service.RunningNumberService;

@Controller
@RequestMapping("/api/running-number")
public class RunningNumberController {

	@Autowired
	private RunningNumberService runnresponseingNumberService;

	@GetMapping("/")
	@ResponseBody
	public ResponseData<String> getRunningNumber() {
		ResponseData<String> response = new ResponseData<String>();
		try {
			response.setData(runnresponseingNumberService.getRunningNumber());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
}
