package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class EmailDetails {

	private long actionid;

	private long emaildetailsid;

	private String personid;

	private String personnumber;

	@Min(value = 1, message = "Please select type.")
	private long emailtype;


	@Email(message=" Enter a valid email addrress.")
	private String emailaddress;

	private String primary="No";
	
	private String effectiveenddate="4712-12-31";

	public EmailDetails() {
		
	}

	public EmailDetails(long actionid, long emaildetailsid, String personid, String personnumber,
			@Min(value = 1, message = "Please select type.") long emailtype,
			@Email(message = " Enter a valid email addrress.") String emailaddress, String primary,
			String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.emaildetailsid = emaildetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.emailtype = emailtype;
		this.emailaddress = emailaddress;
		this.primary = primary;
		this.effectiveenddate = effectiveenddate;
	}

	@Override
	public String toString() {
		return "EmailDetails [actionid=" + actionid + ", emaildetailsid=" + emaildetailsid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", emailtype=" + emailtype + ", emailaddress=" + emailaddress
				+ ", primary=" + primary + ", effectiveenddate=" + effectiveenddate + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getEmaildetailsid() {
		return emaildetailsid;
	}

	public void setEmaildetailsid(long emaildetailsid) {
		this.emaildetailsid = emaildetailsid;
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

	public long getEmailtype() {
		return emailtype;
	}

	public void setEmailtype(long emailtype) {
		this.emailtype = emailtype;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	
}
