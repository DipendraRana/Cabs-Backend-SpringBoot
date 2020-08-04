package org.codejudge.sb.service;

import org.codejudge.sb.Utility.StatusCode;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.model.Message;
import org.springframework.stereotype.Service;

@Service
public class LocationValidatorImpl implements LocationValidator {

	@Override
	public boolean isValidLocation(Location location, Message message) {
		message.setStatus(StatusCode.FAILURE);
		if(location.getLatitude() == 0.0d) {
			message.setReason("Latitude is Not Correct");
			return false;
		}else if (location.getLongitude() == 0.0) {
			message.setReason("Longitide is Not Correct");
			return false;
		}else {
			message.setStatus(StatusCode.SUCCESS);
			return true;
		}
	}

}
