package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;

public interface EmailVerificationService {
	
	public String generateCode(EmailVerification activationCode,int id);
	public Result verify(int id, String verificationCode);

}
