package in.co.srdt.unif.model.personmanagement;


public class EmailDetails {

	private long actionid;

	private long emaildetailsid;

	private String personid;

	private String personnumber;

	private long emailtype;

	private String emailtypedsc;

	private String emailaddress;

	private String primary="No";


	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public EmailDetails() {
	}

	public EmailDetails(long actionid, long emaildetailsid, String personid, String personnumber, long emailtype,
			String emailtypedsc, String emailaddress, String primary, String effectivestartdate, String effectiveenddate,
			String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.emaildetailsid = emaildetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.emailtype = emailtype;
		this.emailtypedsc = emailtypedsc;
		this.emailaddress = emailaddress;
		this.primary = primary;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.createdby = createdby;
		this.updatedby = updatedby;
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

	public String getEmailtypedsc() {
		return emailtypedsc;
	}

	public void setEmailtypedsc(String emailtypedsc) {
		this.emailtypedsc = emailtypedsc;
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

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Override
	public String toString() {
		return "EmailDetails [actionid=" + actionid + ", emaildetailsid=" + emaildetailsid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", emailtype=" + emailtype + ", emailtypedsc=" + emailtypedsc
				+ ", emailaddress=" + emailaddress + ", primary=" + primary + ", effectivestartdate="
				+ effectivestartdate + ", effectiveenddate=" + effectiveenddate + ", createdby=" + createdby
				+ ", updatedby=" + updatedby + "]";
	}
	


}
