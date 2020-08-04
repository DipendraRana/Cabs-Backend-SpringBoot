package org.codejudge.sb.service;

import org.codejudge.sb.model.Location;
import org.codejudge.sb.model.Message;

public interface LocationValidator {
	
	boolean isValidLocation(Location location,Message message);

}
