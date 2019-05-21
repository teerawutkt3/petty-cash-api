package com.project.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.persistence.entities.RunningNumber;
import com.project.common.persistence.repository.RunningNumberRepository;
import com.project.common.utils.ConvertDateUtils;

@Service
public class RunningNumberService {

	@Autowired
	private RunningNumberRepository runningNumberRepository;

	public String getRunningNumber() {
		RunningNumber runningNumber = null;
		String year = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN);
		runningNumber = runningNumberRepository.findByYear(year);

		if (runningNumber != null) {
			Integer number = Integer.valueOf(runningNumber.getNumber());
			number += 1;

			String str = number.toString();
			int length = str.length();

			StringBuilder add = new StringBuilder();
			for (int i = length; i < 4; i++) {
				add.append("0");
			}
			add.append(str);

			runningNumber.setNumber(add.toString());
			

		} else {
			runningNumber = new RunningNumber();

			runningNumber.setYear(year);
			runningNumber.setNumber("0000");
		}
		runningNumberRepository.save(runningNumber);
		String result = "PC-" + runningNumber.getYear() + "-" + runningNumber.getNumber();
		return result;

	}
}
