package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="activations_codes_forJobSeekers")
public class EmailVerification{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int jobSeekers_id;
	
	@Column(name="is_confirmed")
	private boolean confirm;
	
	@Column(name ="activation_code")
	private String activationCode;
	
	@OneToOne()
	@JoinColumn(name="id")
	private JobSeeker jobSeeker;
	
}
