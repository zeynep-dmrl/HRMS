package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationForEmployer;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;
	private EmailVerificationService emailVerification; 

	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailVerificationService emailVerification) {
		super();
		this.employerDao = employerDao;
		this.emailVerification = emailVerification;
	}

	@Override
	public DataResult<Employer> add(Employer employer) {
		if(!(isCompanyNameNull(employer) 
				&& isWebSiteNull(employer)
				&& isEpostaNull(employer) 
				&& isPhoneNoNull(employer)
				&& isPasswordNull(employer))) {
				return new ErrorDataResult<Employer>(null, "Tüm alanlar zorunludur!");
			}
		if(employerDao.getByEmail(employer.getEmail()) != null) {
			return new ErrorDataResult<Employer>(null, "Mail adresine kayıtlı başka bir kullanıcı vardır!");
		}
		//email doğrulaması
		System.out.println("Dogrulama kodu:"+ this.emailVerification.generateCodeEmployer(new EmailVerificationForEmployer(), employer.getId()));
		return new SuccessDataResult<Employer>(this.employerDao.save(employer),"Şirket eklendi,aktivasyon kodu doğrulandı");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Kullanıcılar listelendi");
	}

	
	
	
	
	private boolean isCompanyNameNull(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.getCompanyName().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isWebSiteNull(Employer employer) {
		if(employer.getWebSite().isBlank() || employer.getWebSite().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isEpostaNull(Employer employer) {
		//web sitesi ile aynı domaine sahip e-posta?
		if(employer.getEmail().isBlank() || employer.getEmail().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isPhoneNoNull(Employer employer) {
		if(employer.getPhoneNo().isBlank() || employer.getPhoneNo().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isPasswordNull(Employer employer) {
		if(employer.getPassword().isBlank() || employer.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
}
