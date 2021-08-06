package in.co.srdt.unif.model.reports;

public class BirthDetailsModel {

	private String personnumber;
	private String firstname;
	private String lastname;
	private String positionname;
	private String dateofbirth;
	private int amount = 1000;
	
	public BirthDetailsModel() {
		super();
	}


	public BirthDetailsModel(String personnumber, String firstname, String lastname, String positionname,
			String dateofbirth, int amount) {
		super();
		this.personnumber = personnumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.positionname = positionname;
		this.dateofbirth = dateofbirth;
		this.amount = amount;
	}


	public String getPersonnumber() {
		return personnumber;
	}


	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPositionname() {
		return positionname;
	}


	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}


	public String getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "BirthDetailsModel [personnumber=" + personnumber + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", positionname=" + positionname + ", dateofbirth=" + dateofbirth + ", amount=" + amount + "]";
	}

	
		
}
