package kodlamaio.hrms.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveTrue() {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAllByIsActiveTrue(),"Aktif iş ilanları listelendi.") ;
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveTrueOrderByReleaseDate() {
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAllByIsActiveTrueOrderByReleaseDateDesc(),"Aktif iş ilanları tarihe göre listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByEmployer_EmployerId(int employerId) {
		
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_EmployerId(employerId),"Aktif iş ilanları firmaya göre listelendi.");
	}

	@Override
	public DataResult<JobAdvertisement> add(JobAdvertisement jobAdvertisement) {
		if(!(isJobPositionNull(jobAdvertisement) 
				&& isJobDescriptionNull(jobAdvertisement)
				&& isCityNull(jobAdvertisement)
				&& isAmountNull(jobAdvertisement))) {
			return new ErrorDataResult<JobAdvertisement>(null,"Zorunlu alanlar boş bırakmayınız.");
		}
		if(!isDeadlineTrue(jobAdvertisement).isSuccess()) {
			return new ErrorDataResult<JobAdvertisement>(null,"Son basvuru tarihini kontrol ediniz.");
		}
		return new SuccessDataResult<JobAdvertisement>
		(this.jobAdvertisementDao.save(jobAdvertisement),"İş ilanı oluşturuldu.");
		
	}
	
	@Override
	public DataResult<JobAdvertisement> changeStatuesAdvertisement(int employerId, int advertisementId) {
		JobAdvertisement temp = jobAdvertisementDao.getByIdAndEmployer_EmployerId(advertisementId, employerId);
		if(temp == null) {
			return new ErrorDataResult<JobAdvertisement>(null,"İşlem gerçekleştirilemedi.");
		}
		
		temp.setActive(false);
		return new SuccessDataResult<JobAdvertisement>(jobAdvertisementDao.save(temp),"İş ilanı kapatıldı");
	}
	
	
	
	
	
	private boolean isJobPositionNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getJobPosition().equals(null)) {
			new ErrorResult("Genel iş pozisyonu zorunludur.");
			return false;
		}
		return true;
	}
	private boolean isJobDescriptionNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getJobDescription().equals(null) || jobAdvertisement.getJobDescription().isBlank()) {
			new ErrorResult("iş tanımı zorunludur.");
			return false;
		}
		return true;
	}
	private boolean isCityNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getCity().equals(null)) {
			new ErrorResult("Şehir bilgisi zorunludur.");
			return false;
		}
		return true;
	}
	private boolean isAmountNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getAmount() < 1) {
			new ErrorResult("Açık iş pozisyonu değeri giriniz");
			return false;
		}
		return true;
	}
	private Result isDeadlineTrue(JobAdvertisement jobAdvertisement) {
		if(new Date().compareTo(jobAdvertisement.getDeadlineAdvertisememt()) > 0 ) {
			return new ErrorResult("Son basvuru tarihi yanlıştır.");
		}
		return new SuccessResult();
	}

	

	
	
	
	
	
	
	
	
	
	
}
