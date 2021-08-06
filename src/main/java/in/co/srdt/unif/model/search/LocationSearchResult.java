package in.co.srdt.unif.model.search;

public class LocationSearchResult {

	private long locationid;
	private String name;
	private String code;
	private String effectstartdate;
	private String effectendDate;
	private String country;
	private String state;
	private String status;
	
	public LocationSearchResult() {}
	
	public LocationSearchResult(long locationid, String name, String code, String effectstartdate, String effectendDate,
			String country, String state, String status) {
		
		this.locationid = locationid;
		this.name = name;
		this.code = code;
		this.effectstartdate = effectstartdate;
		this.effectendDate = effectendDate;
		this.country = country;
		this.state = state;
		this.status = status;
	}
	public long getLocationid() {
		return locationid;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getEffectstartdate() {
		return effectstartdate;
	}
	public String getEffectendDate() {
		return effectendDate;
	}
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public String getStatus() {
		return status;
	}
	
	
	
}
