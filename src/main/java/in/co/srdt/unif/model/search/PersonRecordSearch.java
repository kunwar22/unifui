package in.co.srdt.unif.model.search;

public class PersonRecordSearch {
	
	private String persondepartment ;
	private String personemail ;
	private String personid ;
	private String personlegalentity ;
	private String personlocation ;
	private String personname ;
	private String personnumber ;
	private String status;

	public PersonRecordSearch() {
		
	}

	public PersonRecordSearch(String persondepartment, String personemail, String personid, String personlegalentity, String personlocation, String personname, String personnumber, String status) {
		this.persondepartment = persondepartment;
		this.personemail = personemail;
		this.personid = personid;
		this.personlegalentity = personlegalentity;
		this.personlocation = personlocation;
		this.personname = personname;
		this.personnumber = personnumber;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersondepartment() {
		return persondepartment;
	}

	public void setPersondepartment(String persondepartment) {
		this.persondepartment = persondepartment;
	}

	public String getPersonemail() {
		return personemail;
	}

	public void setPersonemail(String personemail) {
		this.personemail = personemail;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonlegalentity() {
		return personlegalentity;
	}

	public void setPersonlegalentity(String personlegalentity) {
		this.personlegalentity = personlegalentity;
	}

	public String getPersonlocation() {
		return personlocation;
	}

	public void setPersonlocation(String personlocation) {
		this.personlocation = personlocation;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	@Override
	public String toString() {
		return "PersonRecordSearch{" +
				"persondepartment='" + persondepartment + '\'' +
				", personemail='" + personemail + '\'' +
				", personid='" + personid + '\'' +
				", personlegalentity='" + personlegalentity + '\'' +
				", personlocation='" + personlocation + '\'' +
				", personname='" + personname + '\'' +
				", personnumber='" + personnumber + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
