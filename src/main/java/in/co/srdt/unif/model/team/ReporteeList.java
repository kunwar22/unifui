package in.co.srdt.unif.model.team;

public class ReporteeList {

	public String personname;
	public String personnumber;
	public String designation;
	public String job;
	public String department;
	public String contact;
	public String location;
	
	public ReporteeList() {
		super();
	}

	public ReporteeList(String personname, String personnumber, String designation, String job, String department,
			String contact, String location) {
		super();
		this.personname = personname;
		this.personnumber = personnumber;
		this.designation = designation;
		this.job = job;
		this.department = department;
		this.contact = contact;
		this.location = location;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ReporteeList [personname=" + personname + ", personnumber=" + personnumber + ", designation="
				+ designation + ", job=" + job + ", department=" + department + ", contact=" + contact + ", location="
				+ location + "]";
	}


	
		
}
