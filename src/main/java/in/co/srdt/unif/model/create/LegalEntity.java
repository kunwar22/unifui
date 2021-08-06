package in.co.srdt.unif.model.create;

public class LegalEntity {

	private String country;
	private String status;
	private String addressline1;
	private Long legaladdressid;
	private String citytown;
	private String pincode;
	private String state;
	
	public LegalEntity() {
		super();
	}

	public LegalEntity(String country, String status, String addressline1, Long legaladdressid, String citytown,
			String pincode, String state) {
		super();
		this.country = country;
		this.status = status;
		this.addressline1 = addressline1;
		this.legaladdressid = legaladdressid;
		this.citytown = citytown;
		this.pincode = pincode;
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public Long getLegaladdressid() {
		return legaladdressid;
	}

	public void setLegaladdressid(Long legaladdressid) {
		this.legaladdressid = legaladdressid;
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

	
	
}
