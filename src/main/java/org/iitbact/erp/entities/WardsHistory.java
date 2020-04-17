package org.iitbact.erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.iitbact.erp.requests.WardRequestBean;


/**
 * The persistent class for the wards_history database table.
 * 
 */
@Entity
@Table(name="wards_history")
@NamedQuery(name="WardsHistory.findAll", query="SELECT w FROM WardsHistory w")
public class WardsHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="available_beds")
	private int availableBeds;

	@Column(name="building_name")
	private String buildingName;

	@Column(name="covid_status")
	private String covidStatus;

	@Column(name="extra_fields")
	private Object extraFields;

	@Column(name="facility_id")
	private int facilityId;

	private String floor;

	private String gender;

	@Column(name="icu_beds")
	private int icuBeds;

	@Column(name="is_covid_ward")
	private byte isCovidWard;

	private String name;

	private String severity;

	@Column(name="total_beds")
	private int totalBeds;

	private int ventilators;

	@Id
	@Column(name="ward_history_id")
	private int wardHistoryId;

	@Column(name="ward_id")
	private int wardId;

	@Column(name="ward_number")
	private String wardNumber;

	public WardsHistory() {
	}

	public WardsHistory(WardRequestBean request,int facilityId,int wardId) {
		this.setAvailableBeds(request.getTotalBeds()-request.getBedsOccupied());
		this.setTotalBeds(request.getTotalBeds());
		this.setCovidStatus(request.getPatientType().toString());
		this.setSeverity(request.getSeverity().toString());
		this.setIcuBeds(request.getIcuBeds());
		this.setVentilators(request.getVentilators());;
		
		this.setName(request.getName());
		this.setGender(request.getGender().toString());
		this.setWardNumber(request.getWardNumber());
		this.setBuildingName(request.getBuildingName());
		this.setExtraFields(request.getExtraFields());
		
		this.setWardId(wardId);
		this.setFacilityId(facilityId);
	}

	public int getAvailableBeds() {
		return this.availableBeds;
	}

	public void setAvailableBeds(int availableBeds) {
		this.availableBeds = availableBeds;
	}

	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCovidStatus() {
		return this.covidStatus;
	}

	public void setCovidStatus(String covidStatus) {
		this.covidStatus = covidStatus;
	}

	public Object getExtraFields() {
		return this.extraFields;
	}

	public void setExtraFields(Object extraFields) {
		this.extraFields = extraFields;
	}

	public int getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIcuBeds() {
		return this.icuBeds;
	}

	public void setIcuBeds(int icuBeds) {
		this.icuBeds = icuBeds;
	}

	public byte getIsCovidWard() {
		return this.isCovidWard;
	}

	public void setIsCovidWard(byte isCovidWard) {
		this.isCovidWard = isCovidWard;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public int getTotalBeds() {
		return this.totalBeds;
	}

	public void setTotalBeds(int totalBeds) {
		this.totalBeds = totalBeds;
	}

	public int getVentilators() {
		return this.ventilators;
	}

	public void setVentilators(int ventilators) {
		this.ventilators = ventilators;
	}

	public int getWardHistoryId() {
		return this.wardHistoryId;
	}

	public void setWardHistoryId(int wardHistoryId) {
		this.wardHistoryId = wardHistoryId;
	}

	public int getWardId() {
		return this.wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardNumber() {
		return this.wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

}