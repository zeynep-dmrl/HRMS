package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationForEmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationForJobSeekerDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationForEmployer;
import kodlamaio.hrms.entities.concretes.EmailVerificationForJobSeeker;


public class EmailVerificationManager  implements EmailVerificationService{
	
	private EmailVerificationForJobSeekerDao emailVerificationJobSeekerDao;
	private EmailVerificationForEmployerDao emailVerificationEmployerDao;
	
	@Autowired 
	public EmailVerificationManager(EmailVerificationForJobSeekerDao emailVerificationJobSeekerDao,
			EmailVerificationForEmployerDao emailVerificationEmployerDao) {
		super();
		this.emailVerificationJobSeekerDao = emailVerificationJobSeekerDao;
		this.emailVerificationEmployerDao = emailVerificationEmployerDao;
	}

	
	
	
	@Override
	public String generateCode(EmailVerificationForJobSeeker activationCode,int id) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                					+ "0123456789"
                					+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(6);
		for(int i=0;i<6;i++) {
			int index = (int)(alphaNumericString.length()*Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		
		EmailVerificationForJobSeeker activationCode_ = activationCode;
		activationCode.setActivationCode(sb.toString());
		activationCode.setId(id);
		activationCode.setConfirm(false);
		emailVerificationJobSeekerDao.save(activationCode); 
		return sb.toString();
	}
	@Override
	public Result verifyJobSeeker(int id, String verificationCode) {
		EmailVerificationForJobSeeker temp = emailVerificationJobSeekerDao.getById(id).stream().findFirst().get();
		if(temp.getActivationCode().equals(verificationCode) && temp.isConfirm() != true) {
			temp.setConfirm(true);
			return new SuccessDataResult<EmailVerificationForJobSeeker>
			(this.emailVerificationJobSeekerDao.save(temp),"Başarılı");
		}
		
		return new ErrorDataResult<EmailVerificationForJobSeeker>
		(null,"Dogrulamam kodu gecersiz");
		
	}



	@Override
	public String generateCodeEmployer(EmailVerificationForEmployer activationCode, int id) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(6);
		for(int i=0;i<6;i++) {
		int index = (int)(alphaNumericString.length()*Math.random());
		sb.append(alphaNumericString.charAt(index));
		}
		
		EmailVerificationForEmployer activationCode_ = activationCode;
		activationCode.setActivationCode(sb.toString());
		activationCode.setId(id);
		activationCode.setConfirm(false);
		emailVerificationEmployerDao.save(activationCode); 
		return sb.toString();
	}


	@Override
	public Result verifyEmployer(int id, String verificationCode) {
		EmailVerificationForEmployer temp = emailVerificationEmployerDao.getById(id).stream().findFirst().get();
		if(temp.getActivationCode().equals(verificationCode) && temp.isConfirm() != true) {
			temp.setConfirm(true);
			return new SuccessDataResult<EmailVerificationForEmployer>
			(this.emailVerificationEmployerDao.save(temp),"Başarılı");
		}
			
		return new ErrorDataResult<EmailVerificationForEmployer>
		(null,"Dogrulamam kodu gecersiz");
			
		

	}


	



	
	

}
