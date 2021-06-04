package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	
	DataResult<JobAdvertisement> add(JobAdvertisement jobAdvertisement);
	DataResult<JobAdvertisement> changeStatuesAdvertisement(int employerId,int advertisementId);
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveTrue();
	DataResult<List<JobAdvertisement>> findAllByIsActiveTrueOrderByReleaseDate();
	DataResult<List<JobAdvertisement>> findByEmployer_EmployerId(int employerId);
	

}
