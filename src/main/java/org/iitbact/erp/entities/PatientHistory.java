package org.iitbact.erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.iitbact.erp.requests.PatientRequestBean;
import org.iitbact.erp.requests.PatientTransferRequestBean;


/**
 * The persistent class for the patient_history database table.
 * 
 */
@Entity
@Table(name="patient_history")
@NamedQuery(name="PatientHistory.findAll", query="SELECT p FROM PatientHistory p")
public class PatientHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="facility_id")
	private int facilityId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="severity")
	private String severity;

	@Column(name="test_status")
	private String testStatus;

	@Column(name="ward_id")
	private int wardId;
	
	@Column(name="patient_id")
	private int patientId;
	

	public PatientHistory() {
	}
	
	public PatientHistory(PatientRequestBean request, Patient patient) {
		this.severity=request.getSeverity().toString();
		this.facilityId=request.getFacilityId();
		this.wardId=request.getWardId();
		this.patientId=patient.getPatientId();
		this.testStatus=request.getTestStatus().toString();
	}
	
	public PatientHistory(PatientTransferRequestBean request) {
		this.severity=request.getSeverity().toString();
		this.facilityId=request.getFacilityId();
		this.wardId=request.getWardId();
	//	this.patientId=request.getPatientId();
		this.testStatus=request.getTestStatus().toString();
	}
	

	public int getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getWardId() {
		return this.wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

}