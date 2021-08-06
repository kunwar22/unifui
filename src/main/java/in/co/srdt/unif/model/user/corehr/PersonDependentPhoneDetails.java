package in.co.srdt.unif.model.user.corehr;


public class PersonDependentPhoneDetails {


	private long actionid;

	private long phonedetailsid;

	private long dependentdetailsid;

	private long phonetype;

	private String number;
	
	private String areacode;

	private long countrycode;

	private String primary="No";

	public PersonDependentPhoneDetails() {
		
	}

	public PersonDependentPhoneDetails(long actionid, long phonedetailsid, long dependentdetailsid, long phonetype,
			String number, String areacode, long countrycode, String primary) {
		
		this.actionid = actionid;
		this.phonedetailsid = phonedetailsid;
		this.dependentdetailsid = dependentdetailsid;
		this.phonetype = phonetype;
		this.number = number;
		this.areacode = areacode;
		this.countrycode = countrycode;
		this.primary = primary;
	}

	public long getactionid() {
		return actionid;
	}

	public void setactionid(long actionid) {
		this.actionid = actionid;
	}

	public long getPhonedetailsid() {
		return phonedetailsid;
	}

	public void setPhonedetailsid(long phonedetailsid) {
		this.phonedetailsid = phonedetailsid;
	}

	public long getDependentdetailsid() {
		return dependentdetailsid;
	}

	public void setDependentdetailsid(long dependentdetailsid) {
		this.dependentdetailsid = dependentdetailsid;
	}

	public long getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(long phonetype) {
		this.phonetype = phonetype;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
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
		return "PersonDependentPhoneDetails [actionid=" + actionid + ", phonedetailsid=" + phonedetailsid
				+ ", dependentdetailsid=" + dependentdetailsid + ", phonetype=" + phonetype + ", number=" + number
				+ ", areacode=" + areacode + ", countrycode=" + countrycode + ", primary=" + primary + "]";
	}
	
	
}
