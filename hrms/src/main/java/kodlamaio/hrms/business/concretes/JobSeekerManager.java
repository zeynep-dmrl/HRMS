package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		// Mernis-tc ve email var mı?-eposta dogrulaması
		
		if(jobSeekerDao.findByEmailOrNationalityId(jobSeeker.getEmail(), jobSeeker.getNationalityId()) != null ) {
			 
		}
		
		
		
		
		
		this.jobSeekerDao.save(jobSeeker);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
