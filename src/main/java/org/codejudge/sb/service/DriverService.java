package org.codejudge.sb.service;

import java.util.List;

import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Location;

public interface DriverService {
	
	Driver saveDriver(Driver driver);
	
	List<Driver> getAllNearestDriver(Location location);

}
