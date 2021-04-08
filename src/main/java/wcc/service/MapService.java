package wcc.service;

import wcc.repository.AuditRequestRepository;
import wcc.repository.PostcodeLocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wcc.domain.AuditRequest;
import wcc.domain.PostcodeLocation;
import wcc.dto.Location;
import wcc.dto.Postcode;
import wcc.util.JsonUtil;
import wcc.util.LocationUtil;
import wcc.util.SpringUtil;

@RestController
@RequestMapping("/map")
public class MapService {
	
	@Autowired
	PostcodeLocationRepository postcodeLocationRepo;
	
	@Autowired
	AuditRequestRepository auditRequestRepo;
	
	@Transactional
	@GetMapping(path = "/distance/{postcode1}/{postcode2}", produces = "application/json")
	public String index(@PathVariable String postcode1,@PathVariable String postcode2) {
		
		// TODO: replace with AOP
		auditRequested(postcode1,postcode2);
		
		//TODO: validate  postcode, check empty postcode and validate length
		
		Postcode postcode = new Postcode();
		postcode.setLocation1(new Location(postcode1));
		postcode.setLocation2(new Location(postcode2));
		
		
		postcode.setDistance(getDistance(postcode.getLocation1(),postcode.getLocation2()));
		
		return JsonUtil.getString(postcode);
	}
	
	
	private void auditRequested(String postcode1,String postcode2) {
		AuditRequest ar = new AuditRequest();
		ar.setPostcode1(postcode1);
		ar.setPostcode2(postcode2);
		auditRequestRepo.save(ar);
	}

	@GetMapping(path = "/update/{postcode}/{latitude}/{longitude}", produces = "application/json")
	@Transactional
	public String index(@PathVariable String postcode,@PathVariable Double latitude,@PathVariable Double longitude) {
		
		PostcodeLocation pl = postcodeLocationRepo.findByPostcode(postcode);
		if(pl!=null) {
			postcodeLocationRepo.updateByPostcode(postcode,latitude,longitude);
			return "{\"status\":\"ok\"}";
		}else {
			return "{\"status\":\"error\"}";
		}
	}
	
	private Double getDistance(Location location1, Location location2) {
		
		setLocation(location1);
		setLocation(location2);
		
		if(location1.getLatitude() != null &&  location1.getLongitude() != null  && location2.getLatitude() != null && location2.getLongitude() != null) {
			
			return LocationUtil.calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());
		}
		
		return null;
	}
	
	private Location setLocation(Location location) {
		PostcodeLocation postcodeLocation = postcodeLocationRepo.findByPostcode(location.getPostcode());
		location.setLatitude(postcodeLocation.getLatitude());
		location.setLongitude(postcodeLocation.getLongitude());
		return location;
	}
}