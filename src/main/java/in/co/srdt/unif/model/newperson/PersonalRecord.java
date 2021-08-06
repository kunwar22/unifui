package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

public class PersonalRecord {

	private long actionid;

	private String personid;

	private String personnumber;
	
	@Min(value = 1, message = "Please select title.")
	private long title;
	
	@NotBlank(message="Please enter First Name.")
	private String firstname;

	private String middilname;

	private String lastname;

	//@Past(message="Date input invalid for a birth date.")
	@NotBlank(message="Please enter Date of birth")
	private String dateofbirth;

	private long birthcountry;//not available in page

	private long birthstate;//not available in page

	private String birthdistrict;//not available in page
	@Min(value = 1, message = "Please select gender.")
	private long gender;

	private long bloodtype;//not available in page
	
	private String effectiveenddate="4712-12-31";

	public PersonalRecord() {
		
	}


	

	public PersonalRecord(long actionid, String personid, String personnumber,
			@Min(value = 1, message = "Please select title.") long title,
			@NotBlank(message = "Please enter First Name.") String firstname, String middilname,
			String lastname,
			@NotBlank(message = "Please enter Date of birth") String dateofbirth, long birthcountry, long birthstate,
			String birthdistrict, @Min(value = 1, message = "Please select gender.") long gender, long bloodtype,
			String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.title = title;
		this.firstname = firstname;
		this.middilname = middilname;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.birthcountry = birthcountry;
		this.birthstate = birthstate;
		this.birthdistrict = birthdistrict;
		this.gender = gender;
		this.bloodtype = bloodtype;
		this.effectiveenddate = effectiveenddate;
	}




	@Override
	public String toString() {
		return "PersonalRecord [actionid=" + actionid + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", title=" + title + ", firstname=" + firstname + ", middilname=" + middilname + ", lastname="
				+ lastname + ", dateofbirth=" + dateofbirth + ", birthcountry=" + birthcountry + ", birthstate="
				+ birthstate + ", birthdistrict=" + birthdistrict + ", gender=" + gender + ", bloodtype=" + bloodtype
				+ ", effectiveenddate=" + effectiveenddate + "]";
	}
	
	

	public String getEffectiveenddate() {
		return effectiveenddate;
	}




	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}




	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public long getTitle() {
		return title;
	}

	public void setTitle(long title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddilname() {
		return middilname;
	}

	public void setMiddilname(String middilname) {
		this.middilname = middilname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public long getBirthcountry() {
		return birthcountry;
	}

	public void setBirthcountry(long birthcountry) {
		this.birthcountry = birthcountry;
	}

	public long getBirthstate() {
		return birthstate;
	}

	public void setBirthstate(long birthstate) {
		this.birthstate = birthstate;
	}

	public String getBirthdistrict() {
		return birthdistrict;
	}

	public void setBirthdistrict(String birthdistrict) {
		this.birthdistrict = birthdistrict;
	}

	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	public long getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(long bloodtype) {
		this.bloodtype = bloodtype;
	}
	
	
   
}