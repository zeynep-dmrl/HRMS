package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data

@Entity
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User{
	 
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_site") 
	private String webSite;
	
	@Column(name="phone_number")
	private String phoneNo;
	
	@OneToOne(mappedBy= "employers")
	private EmailVerificationForEmployer verification;

}
/*
 * şirket adı, web sitesi, e-posta, telefon, şifre, şifre tekrarı
 * */
