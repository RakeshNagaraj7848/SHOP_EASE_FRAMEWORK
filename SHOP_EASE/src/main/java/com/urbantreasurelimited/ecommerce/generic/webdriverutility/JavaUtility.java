package com.urbantreasurelimited.ecommerce.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		return randomNumber;
	}

	public String getSystemDateyyyymmdd() {
		// today date capture in yyyy-mm-dd format
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String systemDate = sim.format(dateObj);
		return systemDate;
	}

	public String getRequiredDateyyyymmdd(int days) {
		// today date capture in yyyy-mm-dd format
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String systemDate = sim.format(dateObj);

		// capture date after 30 days from today

		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String dateRequired = sim.format(cal.getTime());
		return dateRequired;

	}

	public String getSystemDateWithoutFormat() {
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		return date;
	}

}
