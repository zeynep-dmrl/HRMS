package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{
	
	private JobPositionDao jobPositionDao;

	@Autowired 
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),"İş pozisyonları listelendi");
	}

	@Override
	public DataResult<JobPosition> add(JobPosition jobPosition) {
		if(jobPositionDao.getByPositionName(jobPosition.getPositionName()) != null) {
			return new ErrorDataResult<JobPosition>(null,"Kayıtlı iş pozisyonu bulunmaktadır");
			
		}
		
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.save(jobPosition),"İş pozisyonu eklendi");
	}
	
}
