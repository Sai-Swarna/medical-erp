package org.iitbact.erp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.iitbact.erp.beans.BaseBean;
import org.iitbact.erp.constants.Constants;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * The persistent class for the facilities database table.
 * 
 */
@Entity
@Table(name="facilities")
@NamedQuery(name="Facility.findAll", query="SELECT f FROM Facility f")
public class Facility implements Serializable, BaseBean {
	private static final long serialVersionUID = 1L;

	private String area;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="facility_id")
	private int facilityId;
	
	private String email;

	@Column(name="institution_type")
	private String institutionType;
	
	@Column(name="covid_facility_type")
	private String covidFacilityType;

	@Column(name="government_hospital")
	private boolean governmentHospital;

	private String jurisdiction;

	private String name;

	private String telephone;
	
	private int region;
	
	@Column(name="facility_status")
	private String facilityStatus;
	
	@Column(name="hospital_category")
	private String hospitalCategory;

	@Column(name="agreement_status")
	private String agreementStatus;
	
	@Column(name="is_seperate_entry_exit_available")
	private boolean isSeperateEntryExitAvailable ;
	
	@Column(name="is_fever_clinic_available")
	private boolean isFeverClinicAvailable ;

	//bi-directional one-to-one association to FacilityAsset
	@OneToOne(mappedBy="facility", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private FacilityAsset facilityAsset;

	//bi-directional one-to-one association to FacilityChecklist
	@OneToOne(mappedBy="facility", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private FacilityChecklist facilityChecklist;

	//bi-directional one-to-one association to FacilityContact
	@OneToOne(mappedBy="facility", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private FacilityContact facilityContact;

	//bi-directional one-to-one association to FacilityInventory
	@OneToOne(mappedBy="facility", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private FacilityInventory facilityInventory;

	//bi-directional one-to-one association to FacilityMedstaff
	@OneToOne(mappedBy="facility", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private FacilityMedstaff facilityMedstaff;

	public Facility() {
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getGovernmentHospital() {
		return this.governmentHospital;
	}

	public void setGovernmentHospital(boolean governmentHospital) {
		this.governmentHospital = governmentHospital;
	}

	public String getJurisdiction() {
		return this.jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public FacilityAsset getFacilityAsset() {
		if(this.facilityAsset == null) {
			this.facilityAsset = new FacilityAsset(this);
		}
		return this.facilityAsset;
	}

	public void setFacilityAsset(FacilityAsset facilityAsset) {
		this.facilityAsset = facilityAsset;
	}

	public FacilityChecklist getFacilityChecklist() {
		if(this.facilityChecklist == null) {
			this.facilityChecklist = new FacilityChecklist(this);
		}
		return this.facilityChecklist;
	}

	public void setFacilityChecklist(FacilityChecklist facilityChecklist) {
		this.facilityChecklist = facilityChecklist;
	}

	public FacilityContact getFacilityContact() {
		if(this.facilityContact == null) {
			this.facilityContact = new FacilityContact(this);
		}
		return this.facilityContact;
	}

	public void setFacilityContact(FacilityContact facilityContact) {
		this.facilityContact = facilityContact;
	}

	public FacilityInventory getFacilityInventory() {
		if(this.facilityInventory == null) {
			this.facilityInventory = new FacilityInventory(this);
		}
		return this.facilityInventory;
	}

	public void setFacilityInventory(FacilityInventory facilityInventory) {
		this.facilityInventory = facilityInventory;
	}

	public FacilityMedstaff getFacilityMedstaff() {
		if(this.facilityMedstaff == null) {
			this.facilityMedstaff = new FacilityMedstaff(this);
		}
		return this.facilityMedstaff;
	}

	public void setFacilityMedstaff(FacilityMedstaff facilityMedstaff) {
		this.facilityMedstaff = facilityMedstaff;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public void updateProfileData(JsonNode facilityData) {
		this.setName(facilityData.get(Constants.NAME).asText());
		this.setArea(facilityData.get(Constants.AREA).asText());
		this.setJurisdiction(facilityData.get(Constants.JURISDICTION).asText());
		this.setInstitutionType(facilityData.get(Constants.TYPE).asText());
		this.setGovernmentHospital(facilityData.get(Constants.IS_GOVERNMENT_FACILITY).asBoolean());
		this.setTelephone(facilityData.get(Constants.TELEPHONE).asText());
		this.setEmail(facilityData.get(Constants.EMAIL).asText());
		if(facilityData.get(Constants.COVID_FACILITY_TYPE)!=null) {
			this.setCovidFacilityType(facilityData.get(Constants.COVID_FACILITY_TYPE).asText());	
		}
		
		if(facilityData.get(Constants.SEPERATE_ENTRY_EXIT)!=null) {
			this.setSeperateEntryExitAvailable(facilityData.get(Constants.SEPERATE_ENTRY_EXIT).asBoolean());	
		}
		
		if(facilityData.get(Constants.FEVER_CLINIC)!=null) {
			this.setFeverClinicAvailable(facilityData.get(Constants.FEVER_CLINIC).asBoolean());	
		}
	}

	public String getCovidFacilityType() {
		return covidFacilityType;
	}

	public void setCovidFacilityType(String covidFacilityType) {
		this.covidFacilityType = covidFacilityType;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getHospitalCategory() {
		return hospitalCategory;
	}

	public void setHospitalCategory(String hospitalCategory) {
		this.hospitalCategory = hospitalCategory;
	}

	public String getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

	public boolean isSeperateEntryExitAvailable() {
		return isSeperateEntryExitAvailable;
	}

	public void setSeperateEntryExitAvailable(boolean isSeperateEntryExitAvailable) {
		this.isSeperateEntryExitAvailable = isSeperateEntryExitAvailable;
	}

	public boolean isFeverClinicAvailable() {
		return isFeverClinicAvailable;
	}

	public void setFeverClinicAvailable(boolean isFeverClinicAvailable) {
		this.isFeverClinicAvailable = isFeverClinicAvailable;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

}