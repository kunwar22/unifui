package in.co.srdt.unif.model.personmanagement;

public class NationalDetails {

	private long actionid;

	private long nationaldetailsid;

	private String personid;

	private String personnumber;

	private long countryid; // new add on 07-03

	private long nationaltype;

	private String countrydsc; // new add on 07-03

	private String nationaltypedsc;

	private String nationalid;

	private String primary="No";

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public NationalDetails() {
	}

	public NationalDetails(long actionid, long nationaldetailsid, String personid, String personnumber, long countryid,
			long nationaltype, String countrydsc, String nationaltypedsc, String nationalid, String primary,
			String effectivestartdate, String effectiveenddate, String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.nationaldetailsid = nationaldetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.countryid = countryid;
		this.nationaltype = nationaltype;
		this.countrydsc = countrydsc;
		this.nationaltypedsc = nationaltypedsc;
		this.nationalid = nationalid;
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

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	public long getNationaltype() {
		return nationaltype;
	}

	public void setNationaltype(long nationaltype) {
		this.nationaltype = nationaltype;
	}

	public String getCountrydsc() {
		return countrydsc;
	}

	public void setCountrydsc(String countrydsc) {
		this.countrydsc = countrydsc;
	}

	public String getNationaltypedsc() {
		return nationaltypedsc;
	}

	public void setNationaltypedsc(String nationaltypedsc) {
		this.nationaltypedsc = nationaltypedsc;
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
		return "NationalDetails [actionid=" + actionid + ", nationaldetailsid=" + nationaldetailsid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", countryid=" + countryid + ", nationaltype="
				+ nationaltype + ", countrydsc=" + countrydsc + ", nationaltypedsc=" + nationaltypedsc + ", nationalid="
				+ nationalid + ", primary=" + primary + ", effectivestartdate=" + effectivestartdate
				+ ", effectiveenddate=" + effectiveenddate + ", createdby=" + createdby + ", updatedby=" + updatedby
				+ "]";
	}

}
