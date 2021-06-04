package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
	
	List<JobSeeker> getByEmailOrNationalityId(String email,String nationalityId);
	
	//DataResult<List<JobSeeker>> getByEmailOrNationalityId(String email,String nationalityId);// aynı mail veya tcno var mı?

}
