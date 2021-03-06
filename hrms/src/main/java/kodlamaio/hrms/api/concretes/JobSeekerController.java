package kodlamaio.hrms.api.concretes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseeker") 
public class JobSeekerController {
	private JobSeekerService jobSeekerService;

	public JobSeekerController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<JobSeeker>> getAll(){
		return this.jobSeekerService.getAll();
		
	}
	
	@PostMapping("/register")
	public DataResult<JobSeeker> add(@RequestBody JobSeeker jobSeeker){
		return this.jobSeekerService.add(jobSeeker);
		
	}
	

}
