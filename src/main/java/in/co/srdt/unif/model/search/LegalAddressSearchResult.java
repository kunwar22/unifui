package in.co.srdt.unif.model.search;



public class LegalAddressSearchResult {

	
	private long actionid;
	
	private long legaladdressid;
	
	private String country;
	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String citytown;
	private String pincode;
	
	private String state;
	
	private String timezone;
	private String status;
	
	
    private String statename;
	
	private String timezonename;
	
	
	
	
	public LegalAddressSearchResult() {}




	public LegalAddressSearchResult(long actionid, long legaladdressid, String country, String addressline1,
			String addressline2, String addressline3, String citytown, String pincode, String state, String timezone,
			String status, String statename, String timezonename) {
		super();
		this.actionid = actionid;
		this.legaladdressid = legaladdressid;
		this.country = country;
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
		return legaladdressid;
	}




	public void setLegaladdressid(long legaladdressid) {
		this.legaladdressid = legaladdressid;
	}




	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		this.country = country;
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




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getTimezone() {
		return timezone;
	}




	public void setTimezone(String timezone) {
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
