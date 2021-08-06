package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NationalDetails {
	

	private long actionid;

	private long nationaldetailsid;

	private String personid;

	private String personnumber;

	@Min(value = 1, message = "Please select National ID Type.")
	private long nationaltype;

	@NotBlank(message="Please enter National Id.")
	private String nationalid;


	private String primary="No";

	@Min(value = 1, message = "Please select country")
	//@Min(value = 1, message = "Please select Country.")
	private long countryid;
	
	private String effectiveenddate="4712-12-31";
	
	
	public NationalDetails(long actionid, long nationaldetailsid, String personid, String personnumber,
			@Min(value = 1, message = "Please select National ID Type.") long nationaltype,
			@NotBlank(message = "Please enter National Id.") String nationalid, String primary,
			@Min(value = 1, message = "Please select country") long countryid, String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.nationaldetailsid = nationaldetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.nationaltype = nationaltype;
		this.nationalid = nationalid;
		this.primary = primary;
		this.countryid = countryid;
		this.effectiveenddate = effectiveenddate;
	}
	
	

	@Override
	public String toString() {
		return "NationalDetails [actionid=" + actionid + ", nationaldetailsid=" + nationaldetailsid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", nationaltype=" + nationaltype + ", nationalid="
				+ nationalid + ", primary=" + primary + ", countryid=" + countryid + ", effectiveenddate="
				+ effectiveenddate + "]";
	}



	public NationalDetails() {
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getNationaldetailsid() {
		return nationaldetailsid;
	}

	public void setNationaldetailsid(long nationaldetailsid) {
		this.nationaldetailsid = nationaldetailsid;
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

	public long getNationaltype() {
		return nationaltype;
	}

	public void setNationaltype(long nationaltype) {
		this.nationaltype = nationaltype;
	}

	public String getNationalid() {
		return nationalid;
	}

	public void setNationalid(String nationalid) {
		this.nationalid = nationalid;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	

	


	
	
	
}
