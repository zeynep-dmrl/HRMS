package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobPositionService {
	
	DataResult<JobPosition> add(JobPosition jobPosition);
	DataResult<List<JobPosition>> getAll();

}
