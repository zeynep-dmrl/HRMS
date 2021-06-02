package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	DataResult<JobSeeker> add(JobSeeker jobSeeker); //uye kayıt
	DataResult<List<JobSeeker>> getAll();
	
	
	
	

}
