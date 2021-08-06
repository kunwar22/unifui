package in.co.srdt.unif.model.user.corehr;

public class PublicInfo {
	private String personid;

	private String personnumber;

	private String workmail;

	private String countryname;

	private String addressline1;

	private String addressline2;

	private String addressline3;

	private String citytown;

	private int pincode;

	private String statename;

	private String timezonname;
	
	private String completeaddress;
	
	public PublicInfo() {
		
	}

	public PublicInfo(String personid, String personnumber, String workmail, String countryname, String addressline1,
			String addressline2, String addressline3, String citytown, int pincode, String statename,
			String timezonname,String completeaddress) {
		super();
		this.personid = personid;
		this.personnumber = personnumber;
		this.workmail = workmail;
		this.countryname = countryname;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressline3 = addressline3;
		this.citytown = citytown;
		this.pincode = pincode;
		this.statename = statename;
		this.timezonname = timezonname;
		this.completeaddress=completeaddress;
	}

	

	@Override
	public String toString() {
		return "PublicInfo [personid=" + personid + ", personnumber=" + personnumber + ", workmail=" + workmail
				+ ", countryname=" + countryname + ", addressline1=" + addressline1 + ", addressline2=" + addressline2
				+ ", addressline3=" + addressline3 + ", citytown=" + citytown + ", pincode=" + pincode + ", statename="
				+ statename + ", timezonname=" + timezonname + ", completeaddress=" + completeaddress + "]";
	}

	

	public String getCompleteaddress() {
		return completeaddress;
	}



	public void setCompleteaddress(String completeaddress) {
		this.completeaddress = completeaddress;
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

	public String getWorkmail() {
		return workmail;
	}

	public void setWorkmail(String workmail) {
		this.workmail = workmail;
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

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getTimezonname() {
		return timezonname;
	}

	public void setTimezonname(String timezonname) {
		this.timezonname = timezonname;
	}
	
	

}
