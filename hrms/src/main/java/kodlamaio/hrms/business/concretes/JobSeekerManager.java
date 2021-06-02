package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	private JobSeekerDao jobSeekerDao;
	private EmailVerificationService emailVerification;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,EmailVerificationService emailVerification) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.emailVerification = emailVerification;
		
	}

	@Override
	public DataResult<JobSeeker> add(JobSeeker jobSeeker) {
		// Mernis? geri bilgilendirme
		
		if(!(isFirstNameNull(jobSeeker) 
			&& isLastNameNull(jobSeeker)
			&& isEmailNull(jobSeeker) 
			&& isBirthDateNull(jobSeeker)
			&& isPasswordNull(jobSeeker))) {
			return new ErrorDataResult<JobSeeker>(null, "Tüm alanlar zorunludur!");
		}
		
		if(jobSeekerDao.getByEmailOrNationalityId(jobSeeker.getEmail(), jobSeeker.getNationalityId()) != null ) {
			return new ErrorDataResult<JobSeeker>(null, "Kayıtlı kullanıcı bulunmaktadır");
			 
		}
		//email doğrulaması
		System.out.println("Dogrulama kodu:"+ this.emailVerification.generateCode(new EmailVerification(), jobSeeker.getId()));
		
		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.save(jobSeeker), "Kullanıcı eklendi, aktivasyon kodu doğrulandı");
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(),"Kullanıcılar listelendi");
	}
	
	private boolean isFirstNameNull(JobSeeker jobSeeker) {
		if(jobSeeker.getFirstName().isBlank() || jobSeeker.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean isLastNameNull(JobSeeker jobSeeker) {
		if(jobSeeker.getLastName().isBlank() || jobSeeker.getLastName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean isBirthDateNull(JobSeeker jobSeeker) {
		if(jobSeeker.getDayOfBirth().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isEmailNull(JobSeeker jobSeeker) {
		if(jobSeeker.getEmail().isBlank() || jobSeeker.getEmail().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isPasswordNull(JobSeeker jobSeeker) {
		if(jobSeeker.getPassword().isBlank() || jobSeeker.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
	


	
	

}
