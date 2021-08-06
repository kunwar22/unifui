package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ContactDetails {
	
	
	private long actionid;

	private long contactid;

	private String personid;

	private String personnumber;

	@Min(value = 1, message = "Please select Contact Type")
	private long contacttype;

	
	private String contactnumber;

	private long countrycode;

	private String primary="No";
	
	private String effectiveenddate="4712-12-31";

	public ContactDetails() {
	
	}


	public ContactDetails(long actionid, long contactid, String personid, String personnumber,
			@Min(value = 1, message = "Please select Contact Type") long contacttype, String contactnumber,
			long countrycode, String primary, String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.contactid = contactid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.contacttype = contacttype;
		this.contactnumber = contactnumber;
		this.countrycode = countrycode;
		this.primary = primary;
		this.effectiveenddate = effectiveenddate;
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

	public long getContactid() {
		return contactid;
	}

	public void setContactid(long contactid) {
		this.contactid = contactid;
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

	public long getContacttype() {
		return contacttype;
	}

	public void setContacttype(long contacttype) {
		this.contacttype = contacttype;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public long getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(long countrycode) {
		this.countrycode = countrycode;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}


	@Override
	public String toString() {
		return "ContactDetails [actionid=" + actionid + ", contactid=" + contactid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", contacttype=" + contacttype + ", contactnumber=" + contactnumber
				+ ", countrycode=" + countrycode + ", primary=" + primary + ", effectiveenddate=" + effectiveenddate
				+ "]";
	}

	
	
	
	

	
}
