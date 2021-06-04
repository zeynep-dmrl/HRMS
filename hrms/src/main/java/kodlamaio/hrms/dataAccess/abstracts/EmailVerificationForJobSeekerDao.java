package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.EmailVerificationForJobSeeker;

public interface EmailVerificationForJobSeekerDao extends JpaRepository<EmailVerificationForJobSeeker,Integer>{
	
	List<EmailVerificationForJobSeeker> getById(Integer id);

}
