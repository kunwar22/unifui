package in.co.srdt.unif.model.search;

public class LegalEntitySearch {
	
	private long actionid;
	private long legalentityid;
	private String country;
	private String name;
	private String legalentityidentifier;
	
	private String effectstartdate;
	
	private String effectenddate;
	private String ispayrollstatutoryunit;
	private String islegalemployer;
	private String identifyingjurisdictions;
	private String legaladdress;
	private String placeofregistration;
	private String tinpan;
	private String registrationnumber;
	
	public LegalEntitySearch() {
		super();
	}

	public LegalEntitySearch(long actionid, long legalentityid, String country, String name,
			String legalentityidentifier, String effectstartdate, String effectenddate, String ispayrollstatutoryunit,
			String islegalemployer, String identifyingjurisdictions, String legaladdress, String placeofregistration,
			String tinpan, String registrationnumber) {
		super();
		this.actionid = actionid;
		this.legalentityid = legalentityid;
		this.country = country;
		this.name = name;
		this.legalentityidentifier = legalentityidentifier;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.ispayrollstatutoryunit = ispayrollstatutoryunit;
		this.islegalemployer = islegalemployer;
		this.identifyingjurisdictions = identifyingjurisdictions;
		this.legaladdress = legaladdress;
		this.placeofregistration = placeofregistration;
		this.tinpan = tinpan;
		this.registrationnumber = registrationnumber;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getLegalentityid() {
		return legalentityid;
	}

	public void setLegalentityid(long legalentityid) {
		this.legalentityid = legalentityid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalentityidentifier() {
		return legalentityidentifier;
	}

	public void setLegalentityidentifier(String legalentityidentifier) {
		this.legalentityidentifier = legalentityidentifier;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getEffectenddate() {
		return effectenddate;
	}

	public void setEffectenddate(String effectenddate) {
		this.effectenddate = effectenddate;
	}

	public String getIspayrollstatutoryunit() {
		return ispayrollstatutoryunit;
	}

	public void setIspayrollstatutoryunit(String ispayrollstatutoryunit) {
		this.ispayrollstatutoryunit = ispayrollstatutoryunit;
	}

	public String getIslegalemployer() {
		return islegalemployer;
	}

	public void setIslegalemployer(String islegalemployer) {
		this.islegalemployer = islegalemployer;
	}

	public String getIdentifyingjurisdictions() {
		return identifyingjurisdictions;
	}

	public void setIdentifyingjurisdictions(String identifyingjurisdictions) {
		this.identifyingjurisdictions = identifyingjurisdictions;
	}

	public String getLegaladdress() {
		return legaladdress;
	}

	public void setLegaladdress(String legaladdress) {
		this.legaladdress = legaladdress;
	}

	public String getPlaceofregistration() {
		return placeofregistration;
	}

	public void setPlaceofregistration(String placeofregistration) {
		this.placeofregistration = placeofregistration;
	}

	public String getTinpan() {
		return tinpan;
	}

	public void setTinpan(String tinpan) {
		this.tinpan = tinpan;
	}

	public String getRegistrationnumber() {
		return registrationnumber;
	}

	public void setRegistrationnumber(String registrationnumber) {
		this.registrationnumber = registrationnumber;
	}

	
	
	
}
