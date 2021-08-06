package in.co.srdt.unif.model;



public class StateDetails {


	//@JsonProperty("status")
	private Integer stateId;
	private String tinNumber;
	private String stateCode;
	private String stateName;
	private String countryCode;
	private String updateOn;
	private String stateStatus;
	private String country;
	
	
	public StateDetails() {}


	public StateDetails(Integer stateId, String tinNumber, String stateCode, String stateName, String countryCode,
			String updateOn, String stateStatus, String country) {
		super();
		this.stateId = stateId;
		this.tinNumber = tinNumber;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.countryCode = countryCode;
		this.updateOn = updateOn;
		this.stateStatus = stateStatus;
		this.country = country;
	}


	public Integer getStateId() {
		return stateId;
	}


	public String getTinNumber() {
		return tinNumber;
	}


	public String getStateCode() {
		return stateCode;
	}


	public String getStateName() {
		return stateName;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public String getUpdateOn() {
		return updateOn;
	}


	public String getStateStatus() {
		return stateStatus;
	}


	public String getCountry() {
		return country;
	}
	
	
	
	
	
	
	
}
