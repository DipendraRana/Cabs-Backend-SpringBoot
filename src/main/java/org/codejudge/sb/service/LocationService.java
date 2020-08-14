package org.codejudge.sb.service;

import org.codejudge.sb.model.Location;

public interface LocationService {
	
	Location saveLocation(Location location,int driverID);
	
}
