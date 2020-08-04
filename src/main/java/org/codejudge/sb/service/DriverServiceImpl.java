package org.codejudge.sb.service;

import java.util.LinkedList;
import java.util.List;

import org.codejudge.sb.Utility.HaversineDistanceFinder;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.repository.DriverRepository;
import org.codejudge.sb.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Driver saveDriver(Driver driver) throws Exception {
		return driverRepository.save(driver);
	}

	@Override
	public List<Driver> getAllNearestDriver(Location userLocation) throws Exception {
		List<Driver> nearestDrivers = new LinkedList<>();
		List<Location> allDriverLocation = locationRepository.findAll();
		for (Location driverLocation : allDriverLocation) {
			double distance = HaversineDistanceFinder.getHaversineDistance(userLocation.getLatitude(),
					userLocation.getLongitude(), driverLocation.getLatitude(), driverLocation.getLongitude());
			if (distance <= 4) {
				Driver driver = driverLocation.getDriver();
				nearestDrivers.add(driver);
			}
		}
		return nearestDrivers;
	}

}
