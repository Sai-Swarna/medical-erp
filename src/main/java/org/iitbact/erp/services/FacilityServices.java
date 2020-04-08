package org.iitbact.erp.services;

import org.iitbact.erp.constants.Constants;
import org.iitbact.erp.entities.Facility;
import org.iitbact.erp.repository.FacilityRepository;
import org.iitbact.erp.repository.UserRepository;
import org.iitbact.erp.requests.BaseRequest;
import org.iitbact.erp.requests.FlexibleRequest;
import org.iitbact.erp.response.BooleanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FacilityServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private ApiValidationService validationService;

	private void authenticateUser(String authToken) {
		String userId = validationService.verifyFirebaseIdToken(authToken);
		userRepository.findByUserId(userId);
		// TODO: If user.facilityId == facilityId or user should be able to access this
		// data then continue, else throw error
		// throw runtime hospital exception
	}

	public BooleanResponse addFacilityProfileData(int facilityId, FlexibleRequest request)
			throws JsonProcessingException {
		this.authenticateUser(request.getAuthToken());

		Facility facility = facilityRepository.findByFacilityId(facilityId);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.convertValue(request.getData(), JsonNode.class);
		facility.updateProfileData(data.get(Constants.FACILITY_DATA));
		if (data != null) {
			facility.getFacilityContact().setData(data.get(Constants.CONTACT_DATA));
		}
		facilityRepository.save(facility);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	public BooleanResponse addFacilityAssets(int facilityId, FlexibleRequest request) throws JsonProcessingException {
		this.authenticateUser(request.getAuthToken());
		Facility facility = facilityRepository.findByFacilityId(facilityId);
		Object data = request.getData();
		if (data != null) {
			facility.getFacilityAsset().setData(data);
		}
		facilityRepository.save(facility);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	public BooleanResponse addFacilityInventory(int facilityId, FlexibleRequest request)
			throws JsonProcessingException {
		this.authenticateUser(request.getAuthToken());
		Facility facility = facilityRepository.findByFacilityId(facilityId);
		Object data = request.getData();
		if (data != null) {
			facility.getFacilityInventory().setData(data);
		}
		facilityRepository.save(facility);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	public BooleanResponse addFacilityMedstaff(int facilityId, FlexibleRequest request) throws JsonProcessingException {
		this.authenticateUser(request.getAuthToken());
		Facility facility = facilityRepository.findByFacilityId(facilityId);
		Object data = request.getData();
		if (data != null) {
			facility.getFacilityMedstaff().setData(data);
		}
		facilityRepository.save(facility);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	public BooleanResponse addFacilityChecklist(int facilityId, FlexibleRequest request)
			throws JsonProcessingException {
		this.authenticateUser(request.getAuthToken());
		Facility facility = facilityRepository.findByFacilityId(facilityId);
		Object data = request.getData();
		if (data != null) {
			facility.getFacilityChecklist().setData(data);
		}
		facilityRepository.save(facility);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	/*
	 * public BooleanResponse addFacilityContacts(int facilityId, FacilityContact
	 * request) throws JsonProcessingException {
	 * this.authenticateUser(request.getAuthToken()); Facility facility =
	 * facilityRepository.findByFacilityId(facilityId); Object data =
	 * request.getData(); if (data != null) {
	 * facility.getFacilityContact().setData(data); }
	 * facilityRepository.save(facility); BooleanResponse returnVal=new
	 * BooleanResponse(true); return returnVal; }
	 */

	public Facility fetchFacilityData(int facilityId, BaseRequest request) {
		this.authenticateUser(request.getAuthToken());
		Facility facility = facilityRepository.findByFacilityId(facilityId);
		return facility;
	}
}