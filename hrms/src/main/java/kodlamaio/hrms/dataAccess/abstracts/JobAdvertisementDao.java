package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;


public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer>{
	
	List<JobAdvertisement> findAllByIsActiveTrue();
	List<JobAdvertisement> findAllByIsActiveTrueOrderByReleaseDateDesc();
	
	@Query("From JobAdvertisement where isActive =:True and employer.employerId =:employerId")
	List<JobAdvertisement> findByIsActiveTrueAndEmployer_EmployerId(int employerId);
	
	JobAdvertisement getByIdAndEmployer_EmployerId(int id, int employerId);

}
