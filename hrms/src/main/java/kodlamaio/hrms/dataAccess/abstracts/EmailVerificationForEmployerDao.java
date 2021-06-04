package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmailVerificationForEmployer;

public interface EmailVerificationForEmployerDao extends JpaRepository<EmailVerificationForEmployer,Integer>{
	
	List<EmailVerificationForEmployer> getById(Integer id);

}

