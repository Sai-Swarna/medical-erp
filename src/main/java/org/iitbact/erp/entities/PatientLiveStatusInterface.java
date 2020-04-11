package org.iitbact.erp.entities;

public interface PatientLiveStatusInterface {

	public int getFacilityId();

	public String getFacilityName();

	public int getWardId();

	public String getWardName();

	public String getWardType();

	public String getPatientCondition();

	public String getTestOutcome();

	/*
	 * public String getAddress();
	 * 
	 * public int getAge();
	 * 
	 * public String getContactNumber();
	 * 
	 * public String getGender();
	 * 
	 * public String getName();
	 */

	public int getPatientId();

}
