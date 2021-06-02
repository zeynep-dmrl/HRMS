package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;


public class EmailVerificationManager  implements EmailVerificationService{
	
	private EmailVerificationDao emailVerificationDao;
	
	@Autowired 
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
	}
	
	@Override
	public String generateCode(EmailVerification activationCode,int id) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                					+ "0123456789"
                					+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(6);
		for(int i=0;i<6;i++) {
			int index = (int)(alphaNumericString.length()*Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		
		EmailVerification activationCode_ = activationCode;
		activationCode.setActivationCode(sb.toString());
		activationCode.setId(id);
		activationCode.setConfirm(false);
		emailVerificationDao.save(activationCode); 
		return sb.toString();
	}
	
	@Override
	public Result verify(int id, String verificationCode) {
		EmailVerification temp = emailVerificationDao.getById(id).stream().findFirst().get();
		if(temp.getActivationCode().equals(verificationCode) && temp.isConfirm() != true) {
			temp.setConfirm(true);
			return new SuccessDataResult<EmailVerification>
			(this.emailVerificationDao.save(temp),"Başarılı");
		}
		
		return new ErrorDataResult<EmailVerification>
		(null,"Dogrulamam kodu gecersiz");
	}


	



	
	

}
