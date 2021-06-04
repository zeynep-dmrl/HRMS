package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerificationForEmployer;
import kodlamaio.hrms.entities.concretes.EmailVerificationForJobSeeker;

public interface EmailVerificationService {
	
	public String generateCode(EmailVerificationForJobSeeker activationCode,int id);
	public Result verifyJobSeeker(int id, String verificationCode);
	
	public String generateCodeEmployer(EmailVerificationForEmployer activationCode,int id);
	public Result verifyEmployer(int id, String verificationCode);

}
