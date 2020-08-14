package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.codejudge.sb.Utility.ModelJSONUtility;
import org.codejudge.sb.Utility.StatusCode;
import org.codejudge.sb.Utility.Validation;
import org.codejudge.sb.Utility.ValidationFailedException;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.service.DriverService;
import org.codejudge.sb.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping
public class AppController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private LocationService locationService;

	@ApiOperation("This is the hello world api")
	@GetMapping("/")
	public String hello() {
		return "Hello World!!";
	}

	@PostMapping(path = "/api/v1/driver/register/", consumes = "application/json")
	public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
		if (!Validation.isValidPhoneNumber(driver.getPhone_number()))
			throw new ValidationFailedException("Phone Number is not valid");
		driver = driverService.saveDriver(driver);
		return new ResponseEntity<Driver>(driver, HttpStatus.CREATED);
	}

	@PostMapping(path = "/api/v1/driver/{driverID}/sendLocation/", consumes = "application/json")
	public ResponseEntity<ObjectNode> shareDriverLocation(@PathVariable int driverID, @RequestBody Location location) {
			if (!Validation.isValidCoordinate(location.getLatitude()) || !Validation.isValidCoordinate(location.getLongitude()))
				throw new ValidationFailedException("Driver Location is not valid");
			location = locationService.saveLocation(location, driverID);
			ObjectNode response = new ModelJSONUtility().messageJSONResponse(StatusCode.SUCCESS);
			return new ResponseEntity<ObjectNode>(response, HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/api/v1/passenger/available_cabs/", consumes = "application/json")
	public ResponseEntity<ObjectNode> getNearByCabs(@RequestBody Location location) {
		if(!Validation.isValidCoordinate(location.getLatitude()) || !Validation.isValidCoordinate(location.getLongitude()))
			throw new ValidationFailedException("Required Location is not valid");
		List<Driver> drivers = driverService.getAllNearestDriver(location);
		ObjectNode response = new ModelJSONUtility().nearByCabJSONResponse(drivers);
		return new ResponseEntity<ObjectNode>(response, HttpStatus.OK);
	}

}
