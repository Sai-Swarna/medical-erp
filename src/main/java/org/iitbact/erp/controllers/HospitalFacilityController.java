package org.iitbact.erp.controllers;

import javax.websocket.server.PathParam;

import org.iitbact.erp.beans.ResponseBean;
import org.iitbact.erp.beans.ResponseBuilder;
import org.iitbact.erp.exceptions.HospitalErpError;
import org.iitbact.erp.exceptions.HospitalErpException;
import org.iitbact.erp.requests.BaseRequest;
import org.iitbact.erp.requests.FlexibleRequest;
import org.iitbact.erp.response.BooleanResponse;
import org.iitbact.erp.response.FacilityProfile;
import org.iitbact.erp.services.HospitalFacilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class HospitalFacilityController {
	
	@Autowired
	private HospitalFacilityServices facilityServices;
	
	
	@PostMapping(path = "/register/facility")
	@ApiOperation(response = BooleanResponse.class,value = "Api to add new data under facility")
	public ResponseBean addFacilityData(@RequestBody FlexibleRequest request) throws JsonProcessingException {
		HospitalErpError error = null;
		BooleanResponse data=null;
		try {
			data=facilityServices.addFacilityData(request);
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data,error);
		return responseBuilder.build();
	}
	
	@PostMapping(path = "/facility/profile/{facilityId}")
	@ApiOperation(response = FacilityProfile.class,value = "Api to add new data under facility")
	public ResponseBean facilityProfile(@RequestBody BaseRequest request,@PathParam("facilityId") long facilityId) throws JsonProcessingException {
		HospitalErpError error = null;
		FacilityProfile data=null;
		try {
			data=facilityServices.facilityProfile(request);
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data,error);
		return responseBuilder.build();
	}
	
	/*@PostMapping(path = "/fetch/facility/data")
	@ApiOperation(response = ErpUserProfile.class,value = "Api to add new data under facility")
	public ResponseBean fetchFacilityData(@RequestBody FlexibleRequest request) {
		HospitalErpError error = null;
		ErpUserProfile data= new ErpUserProfile();
		try {
			data=facilityServices.fetchFacilityData(request);
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data,error);
		return responseBuilder.build();
	}
*/
}
