package org.codejudge.sb.service;

import org.codejudge.sb.model.Location;
import org.codejudge.sb.repository.DriverRepository;
import org.codejudge.sb.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired 
	DriverRepository driverRepository;

	@Override
	public Location saveLocation(Location location,int driverID) {
		location.setDriver(driverRepository.getOne(driverID));
		return locationRepository.save(location);
	}

}
