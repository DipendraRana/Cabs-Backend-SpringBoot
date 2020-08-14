package org.codejudge.sb.Utility;

import java.util.List;

import org.codejudge.sb.model.Driver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ModelJSONUtility {

	public ObjectNode nearByCabJSONResponse(List<Driver> available_cabs) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		if(available_cabs.size() > 0) {
			ArrayNode drivers = mapper.createArrayNode();
			for (Driver driver : available_cabs) {
				ObjectNode eachDriver = mapper.createObjectNode();
				eachDriver.put("name", driver.getName());
				eachDriver.put("car_number", driver.getCar_number());
				eachDriver.put("phone_number", driver.getPhone_number());
				drivers.add(eachDriver);
			}
			rootNode.replace("available_cabs", drivers);
		}else {
			rootNode.put("message", "No cabs available!");
		}
		return rootNode;
	}

	public ObjectNode messageJSONResponse(String status) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		((ObjectNode) rootNode).put("status", status);
		return (ObjectNode) rootNode;
	}
}
