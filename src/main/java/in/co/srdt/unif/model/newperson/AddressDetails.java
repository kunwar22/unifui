package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AddressDetails {

	private long actionid;

	private long addressid;

	private String personid;

	private String personnumber;

	private long addresstype;

	@Min(value = 1, message = "Please select Country.")
	private long country;

	@Min(value = 1, message = "Please select State.")
	private long state;

	private String district;

	private String pinCode;

	@NotBlank(message="Please enter Address Line 1")
	private String addressline1;

	private String addressline2;

	private String citytown;

	private String primary;
	
	private String effectiveenddate="4712-12-31";

	public AddressDetails() {
		
	}

	public AddressDetails(long actionid, long addressid, String personid, String personnumber, long addresstype,
			@Min(value = 1, message = "Please select country.") long country,
			@Min(value = 1, message = "Please select state.") long state, String district, String pinCode,
			@NotBlank(message = "Please enter address") String addressline1, String addressline2, String citytown,
			String primary, String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.addressid = addressid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.addresstype = addresstype;
		this.country = country;
		this.state = state;
		this.district = district;
		this.pinCode = pinCode;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.citytown = citytown;
		this.primary = primary;
		this.effectiveenddate = effectiveenddate;
	}

	@Override
	public String toString() {
		return "AddressDetails [actionid=" + actionid + ", addressid=" + addressid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", addresstype=" + addresstype + ", country=" + country
				+ ", state=" + state + ", district=" + district + ", pinCode=" + pinCode + ", addressline1="
				+ addressline1 + ", addressline2=" + addressline2 + ", citytown=" + citytown + ", primary=" + primary
				+ ", effectiveenddate=" + effectiveenddate + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
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

	public long getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(long addresstype) {
		this.addresstype = addresstype;
	}

	public long getCountry() {
		return country;
	}

	public void setCountry(long country) {
		this.country = country;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public String getCitytown() {
		return citytown;
	}

	public void setCitytown(String citytown) {
		this.citytown = citytown;
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
