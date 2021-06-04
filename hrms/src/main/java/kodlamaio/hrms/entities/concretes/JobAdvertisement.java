package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="job_advertisements")
@Entity 
@AllArgsConstructor
@NoArgsConstructor 
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	//@Column(name="job_position")
	//private int jobPosition;
	
	@Column(name="job_description")
	private String jobDescription;
	
	//@Column(name="city")
	//private int city;
	
	@Column(name="max_salary")
	private int maxSalary;
	
	@Column(name="min_salary")
	private int minSalary;
	
	@Column(name="amount_position")
	private int amount;
	
	@Column(name="company")
	private int companyName;
	
	@Column(name="release_date")
	private Date releaseDate;
	
	@Column(name="deadline")
	private Date deadlineAdvertisememt;
	
	@Column(name="advertisement_status")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "job_position")
	private JobPosition jobPosition;
	
	@ManyToOne
	@JoinColumn
	private City city;
	
	@ManyToOne
	@JoinColumn
	private Employer employer;
	

}
