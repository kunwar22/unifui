package in.co.srdt.unif.model.personmanagement;

public class AddressDetails {

	private Long actionid;

	private Long addressid;

	private String personid;

	private String personnumber;

	private long addresstype;

	private long country;

	private long state;

	private String addresstypedsc;

	private String countrydsc;

	private String statedsc;

	private String district;

	private String pinCode;

	private String addressline1;

	private String addressline2;

	private String citytown;

	private String primary;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";

	private String status;
	
	private String createdby;

	private String updatedby;
	
	

	public AddressDetails() {
	}



	public AddressDetails(Long actionid, Long addressid, String personid, String personnumber, long addresstype,
			long country, long state, String addresstypedsc, String countrydsc, String statedsc, String district,
			String pinCode, String addressline1, String addressline2, String citytown, String primary,
			String effectivestartdate, String effectiveenddate, String status, String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.addressid = addressid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.addresstype = addresstype;
		this.country = country;
		this.state = state;
		this.addresstypedsc = addresstypedsc;
		this.countrydsc = countrydsc;
		this.statedsc = statedsc;
		this.district = district;
		this.pinCode = pinCode;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.citytown = citytown;
		this.primary = primary;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.status = status;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}



	public Long getActionid() {
		return actionid;
	}



	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}



	public Long getAddressid() {
		return addressid;
	}



	public void setAddressid(Long addressid) {
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



	public String getAddresstypedsc() {
		return addresstypedsc;
	}



	public void setAddresstypedsc(String addresstypedsc) {
		this.addresstypedsc = addresstypedsc;
	}



	public String getCountrydsc() {
		return countrydsc;
	}



	public void setCountrydsc(String countrydsc) {
		this.countrydsc = countrydsc;
	}



	public String getStatedsc() {
		return statedsc;
	}



	public void setStatedsc(String statedsc) {
		this.statedsc = statedsc;
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



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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
		return "AddressDetails [actionid=" + actionid + ", addressid=" + addressid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", addresstype=" + addresstype + ", country=" + country
				+ ", state=" + state + ", addresstypedsc=" + addresstypedsc + ", countrydsc=" + countrydsc
				+ ", statedsc=" + statedsc + ", district=" + district + ", pinCode=" + pinCode + ", addressline1="
				+ addressline1 + ", addressline2=" + addressline2 + ", citytown=" + citytown + ", primary=" + primary
				+ ", effectivestartdate=" + effectivestartdate + ", effectiveenddate=" + effectiveenddate + ", status="
				+ status + ", createdby=" + createdby + ", updatedby=" + updatedby + "]";
	}

	
}
