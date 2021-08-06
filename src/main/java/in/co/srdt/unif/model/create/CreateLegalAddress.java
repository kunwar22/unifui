package in.co.srdt.unif.model.create;



public class CreateLegalAddress {

	private long actionid;
	private long legaladdressid;
	private long country;
	private String countryname;
	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String citytown;
	private String pincode;
	private long state;
	private long timezone;
	private String status;
	private String statename;
	private String timezonename;
	
	
	
	
	public CreateLegalAddress() {}




	public CreateLegalAddress(long actionid, long legaladdressid, long country, String countryname,
			String addressline1, String addressline2, String addressline3, String citytown, String pincode,
			long state, long timezone, String status, String statename, String timezonename) {
		super();
		this.actionid = actionid;
		this.legaladdressid = legaladdressid;
		this.country = country;
		this.countryname = countryname;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressline3 = addressline3;
		this.citytown = citytown;
		this.pincode = pincode;
		this.state = state;
		this.timezone = timezone;
		this.status = status;
		this.statename = statename;
		this.timezonename = timezonename;
	}




	public long getActionid() {
		return actionid;
	}




	public void setActionid(long actionid) {
		this.actionid = actionid;
	}




	public long getLegaladdressid() {
		return legaladdressid != 0 ? legaladdressid:0;
	}




	public void setLegaladdressid(long legaladdressid) {
		this.legaladdressid = legaladdressid;
	}




	public long getCountry() {
		return country;
	}




	public void setCountry(long country) {
		this.country = country;
	}




	public String getCountryname() {
		return countryname;
	}




	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}




	public String getAddressline1() {
		return addressline1;
	}




	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}




	public String getAddressline2() {
		return addressline2;
	}




	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}




	public String getAddressline3() {
		return addressline3;
	}




	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}




	public String getCitytown() {
		return citytown;
	}




	public void setCitytown(String citytown) {
		this.citytown = citytown;
	}




	public String getPincode() {
		return pincode;
	}




	public void setPincode(String pincode) {
		this.pincode = pincode;
	}




	public long getState() {
		return state;
	}




	public void setState(long state) {
		this.state = state;
	}




	public long getTimezone() {
		return timezone;
	}




	public void setTimezone(long timezone) {
		this.timezone = timezone;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getStatename() {
		return statename;
	}




	public void setStatename(String statename) {
		this.statename = statename;
	}




	public String getTimezonename() {
		return timezonename;
	}




	public void setTimezonename(String timezonename) {
		this.timezonename = timezonename;
	}



	
}
