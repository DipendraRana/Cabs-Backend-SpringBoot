package org.codejudge.sb.service;

import org.codejudge.sb.Utility.StatusCode;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Message;
import org.springframework.stereotype.Service;

@Service
public class DriverValidatorImpl implements DriverValidator {

	@Override
	public boolean validateDriver(Driver driver, Message message) {
		message.setStatus(StatusCode.FAILURE);
		if (!isValidName(driver.getName())) {
			message.setReason("Name Validation Failed");
			return false;
		} else if (!isValidEmail(driver.getEmail())) {
			message.setReason("Email is Not Correct");
			return false;
		} else if (!isValidPhoneNumer(driver.getPhone_number())) {
			message.setReason("Issue in Phone Number");
			return false;
		} else if (!isValidlicenseNumber(driver.getLicense_number())) {
			message.setReason("License is not correct");
			return false;
		} else if (!isValidCarNumber(driver.getCar_number())) {
			message.setReason("Car Number is not Correct");
			return false;
		} else {
			message.setStatus(StatusCode.SUCCESS);
			return true;
		}
	}

	private boolean isValidName(String name) {
		return name != null && name != "" ? true : false;
	}

	private boolean isValidEmail(String email) {
		return email != null && email != "" ? true : false;
	}

	private boolean isValidPhoneNumer(int phonenumber) {
		return String.valueOf(phonenumber).length() == 10 && phonenumber > 0 ? true : false;
	}

	private boolean isValidlicenseNumber(String licenseNumber) {
		return licenseNumber != null && licenseNumber != "" ? true : false;
	}

	private boolean isValidCarNumber(String carNumber) {
		return carNumber != null && carNumber != "" ? true : false;
	}

}
