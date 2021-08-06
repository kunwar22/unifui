package in.co.srdt.unif.model.personmanagement;




public class ContactDetails {

	private long actionid;

	private long contactid;

	private String personid;

	private String personnumber;

	private long contacttype;

	private String contacttypedsc;

	private String contactnumber;

	private long countrycode;

	private String countrycodedsc;

	private String primary="No";

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public ContactDetails() {
	}

	public ContactDetails(long actionid, long contactid, String personid, String personnumber, long contacttype,
			String contacttypedsc, String contactnumber, long countrycode, String countrycodedsc, String primary,
			String effectivestartdate, String effectiveenddate, String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.contactid = contactid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.contacttype = contacttype;
		this.contacttypedsc = contacttypedsc;
		this.contactnumber = contactnumber;
		this.countrycode = countrycode;
		this.countrycodedsc = countrycodedsc;
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

	public String getContacttypedsc() {
		return contacttypedsc;
	}

	public void setContacttypedsc(String contacttypedsc) {
		this.contacttypedsc = contacttypedsc;
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

	public String getCountrycodedsc() {
		return countrycodedsc;
	}

	public void setCountrycodedsc(String countrycodedsc) {
		this.countrycodedsc = countrycodedsc;
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
		return "ContactDetails [actionid=" + actionid + ", contactid=" + contactid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", contacttype=" + contacttype + ", contacttypedsc="
				+ contacttypedsc + ", contactnumber=" + contactnumber + ", countrycode=" + countrycode
				+ ", countrycodedsc=" + countrycodedsc + ", primary=" + primary + ", effectivestartdate="
				+ effectivestartdate + ", effectiveenddate=" + effectiveenddate + ", createdby=" + createdby
				+ ", updatedby=" + updatedby + "]";
	}
	
	
}
