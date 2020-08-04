package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.codejudge.sb.Utility.ModelJSONUtility;
import org.codejudge.sb.Utility.StatusCode;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Message;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.service.DriverService;
import org.codejudge.sb.service.DriverValidator;
import org.codejudge.sb.service.LocationService;
import org.codejudge.sb.service.LocationValidator;
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
	
	@Autowired
	private DriverValidator driverValidator;
	
	@Autowired
	private LocationValidator locationValidator;
	
	@Autowired
	private Message message;
	
	@Autowired
	private ModelJSONUtility modelJSONUtility;
	
    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }
    
    @PostMapping(path= "/api/v1/driver/register/",consumes="application/json",produces="application/json")
    public ResponseEntity<Object> registerDriver(@RequestBody Driver driver) {
    	try {
    		if(driverValidator.validateDriver(driver, message)) {
    			driver = driverService.saveDriver(driver);
        		return new ResponseEntity<Object>(driver,HttpStatus.CREATED);
        	}else {
        		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        	}
    	}catch (Exception e) {
    		message.setStatus(StatusCode.FAILURE);
    		message.setReason("Exception Occured While registering the Driver!!!! Knidly try Again");
    		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    @PostMapping(path="/api/v1/driver/{id}/sendLocation/",consumes="application/json",produces="application/json")
    public ResponseEntity<Object> shareDriverLocation(@PathVariable(name="id") int driverID, @RequestBody Location location) {
    	try {
			if(locationValidator.isValidLocation(location, message)) {
				location = locationService.saveLocation(location,driverID);
				ObjectNode response = modelJSONUtility.messageJSONResponse(message);
				response.remove("reason");
				return new ResponseEntity<Object>(response,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			message.setStatus(StatusCode.FAILURE);
    		message.setReason("Exception Occured While registering the Driver Location!!!! Knidly try Again");
    		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
	}
    
    @PostMapping(path="/api/v1/passenger/available_cabs/",consumes="application/json",produces="application/json")
    public ResponseEntity<Object> getNearByCabs(@RequestBody Location location) {
    	try {
			if(locationValidator.isValidLocation(location, message)) {
				List<Driver> drivers = driverService.getAllNearestDriver(location);
				ObjectNode response = modelJSONUtility.nearByCabJSONResponse(drivers);
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			message.setStatus(StatusCode.FAILURE);
    		message.setReason("Exception Occured While registering the Driver Location!!!! Knidly try Again");
    		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
	}

}
