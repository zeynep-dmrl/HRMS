package kodlamaio.hrms.api.concretes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employer") 
public class EmployerController {
	
	private EmployerService employerService;

	public EmployerController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
		
	}
	
	@PostMapping("/register")
	public DataResult<Employer> add(@RequestBody Employer employer){
		return this.employerService.add(employer);
		
	}
	

}