package org.iitbact.erp.controllers;

import org.iitbact.erp.beans.ResponseBean;
import org.iitbact.erp.beans.ResponseBuilder;
import org.iitbact.erp.entities.PatientLiveStatus;
import org.iitbact.erp.exceptions.HospitalErpError;
import org.iitbact.erp.exceptions.HospitalErpException;
import org.iitbact.erp.requests.BaseRequest;
import org.iitbact.erp.requests.PatientProfileRequestBean;
import org.iitbact.erp.requests.PatientRequestBean;
import org.iitbact.erp.requests.PatientTransferRequestBean;
import org.iitbact.erp.response.BooleanResponse;
import org.iitbact.erp.response.PatientLiveStatusResponse;
import org.iitbact.erp.response.PatientProfileResponse;
import org.iitbact.erp.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientServices patientServices;

	@PostMapping(path = "/patients/{patientId}/profile/get")
	@ApiOperation(response = PatientProfileResponse.class, value = "API to fetch patient's profile or biodata")
	public ResponseBean getPatientDetails(@PathVariable int patientId, @RequestBody BaseRequest request) {
		HospitalErpError error = null;
		PatientProfileResponse data = new PatientProfileResponse();
		try {
			data.setProfile(patientServices.getPatientProfile(patientId, request));
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data, error);
		return responseBuilder.build();
	}

	@PostMapping(path = "/patients/{patientId}/profile/post")
	@ApiOperation(response = PatientProfileResponse.class, value = "API to update patient's profile or biodata")
	public ResponseBean updatePateintDetails(@PathVariable int patientId, @RequestBody PatientProfileRequestBean request)  {
		HospitalErpError error = null;
		PatientProfileResponse data = new PatientProfileResponse();;
		try {
			data.setProfile(patientServices.updatePatientProfile(patientId,request));
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data, error);
		return responseBuilder.build();
	}
	
	@PostMapping(path = "/patients/new")
	@ApiOperation(response = BooleanResponse.class, value = "API request to add new patient's into database")
	public ResponseBean addPatient(@RequestBody PatientRequestBean request) {
		HospitalErpError error = null;
		BooleanResponse data = null;
		try {
			data = patientServices.addPatient(request);
		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data, error);
		return responseBuilder.build();
	}
	
	@PostMapping(path = "/patients/{patientId}/status/get")
	@ApiOperation(response = PatientLiveStatus.class, value = "API to fetch live status for patients")
	public ResponseBean fetchPatientStatusLive(@PathVariable int patientId, @RequestBody BaseRequest request){
		HospitalErpError error = null;
		PatientLiveStatusResponse data = null;
		try {
			data = (patientServices.fetchPatientStatusLive(patientId, request));

		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data, error);
		return responseBuilder.build();
	}
	
	@PostMapping(path = "/patient/status/update")
	@ApiOperation(response = BooleanResponse.class, value = "API to transfer facility/ Change Ward / or Update test and condition of a patient")
	public ResponseBean transferPatient(@RequestBody PatientTransferRequestBean request) {
		HospitalErpError error = null;
		BooleanResponse data = null;
		try {
			data = (patientServices.patientStatusUpdate(request));

		} catch (HospitalErpException e) {
			error = e.getError();
		}
		ResponseBuilder responseBuilder = new ResponseBuilder(data, error);
		return responseBuilder.build();
	}

}
