package org.iitbact.erp.services;

import java.util.List;

import org.iitbact.erp.constants.Constants;
import org.iitbact.erp.constants.TEST_STATUS;
import org.iitbact.erp.entities.Ward;
import org.iitbact.erp.entities.WardsHistory;
import org.iitbact.erp.repository.WardHistoryRepository;
import org.iitbact.erp.repository.WardRepository;
import org.iitbact.erp.requests.FacilityRequest;
import org.iitbact.erp.requests.WardRequestBean;
import org.iitbact.erp.response.BooleanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServices {

	@Autowired
	private WardRepository wardRepository;

	@Autowired
	private WardHistoryRepository wardHistoryRepository;

	private void authenticateUser(String authToken) {
		// validationService.verifyFirebaseIdToken(authToken);
		// userRepository.findByUserId(userId);
		// TODO: If user.facilityId == facilityId or user should be able to
		// access this
		// data then continue, else throw error
		// throw runtime hospital exception
	}

	public List<Ward> fetchAvailableWards(int facilityId, FacilityRequest request) {
		this.authenticateUser(request.getAuthToken());
		String covidStatus = getCovidStatus(request.getTestStatus().toString());

		return wardRepository.findByFacilityIdAndCovidStatusAndSeverity(facilityId, covidStatus,
				request.getSeverity().toString());
	}

	private String getCovidStatus(String testStatus) {
		if (TEST_STATUS.POSITIVE.toString().equalsIgnoreCase(testStatus)) {
			return Constants.CONFIRMED;
		} else {
			return Constants.SUSPECTED;
		}
	}

	public BooleanResponse addWardsToFacility(int facilityId, WardRequestBean request) {
		this.authenticateUser(request.getAuthToken());
		Ward ward = new Ward(request, facilityId);
		wardRepository.save(ward);
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}

	public BooleanResponse updateWardDetails(WardRequestBean request, int wardId) {
		this.authenticateUser(request.getAuthToken());
		Ward ward = wardRepository.getOne(wardId);
		ward.updateWard(request);
		wardHistoryRepository.save(new WardsHistory(wardRepository.save(ward)));
		BooleanResponse returnVal = new BooleanResponse(true);
		return returnVal;
	}
}