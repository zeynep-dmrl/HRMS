package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data

@Entity
@Table(name="jobSeekers")
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker extends User{
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="nationality_id")
	private String nationalityId;
	
	@Column(name="date_of_birth")
	private Date dayOfBirth;
	
	//@OneToOne(mappedBy=)
	

}
/*
 * ad, soyad, tcno, doğum yılı, e-Posta, şifre, şifre tekrarı 
*/