package org.codejudge.sb.service;

import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Message;

public interface DriverValidator {

	boolean validateDriver(Driver driver, Message failureMessage);

}
