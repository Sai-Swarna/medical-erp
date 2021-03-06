package org.iitbact.erp.repository;

import java.util.List;

import org.iitbact.erp.entities.Facility;
import org.iitbact.erp.entities.FacilityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

	@Query(value = "Select facility_data.*,IF(m.mapped_facility is null,'false','true') as mapped from (SELECT f.facility_id facilityId,f.name,f.area,f.jurisdiction,f.institution_type institutionType,f.covid_facility_type covidFacilityType,f.telephone,IFNULL(SUM(w.available_beds),0) availability FROM facilities f INNER JOIN wards w ON f.facility_id=w.facility_id  WHERE (w.covid_status=?1 AND w.severity=?2) and w.active and f.region=?4  GROUP BY w.facility_id) facility_data left join hospitaldb.facility_mapping m on facility_data.facilityId = m.mapped_facility and m.source_facility = ?3  order by mapped desc",nativeQuery = true)
	List<FacilityDetails> getFacilities(String covidStatus, String severity,int facilityId,int region);

}