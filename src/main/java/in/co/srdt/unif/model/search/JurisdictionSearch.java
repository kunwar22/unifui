package in.co.srdt.unif.model.search;

public class JurisdictionSearch {
	
	private Long actionid;

	private Long legaljurisdictionid;

	private String name;

	private String country;

	private String legislativecategories;

	private String identifying;

	private String legalentityregncode;

	private String effectstartdate;

	private String effectenddate;

	private String status;

	public JurisdictionSearch() {
		super();
	}

	public JurisdictionSearch(Long actionid, Long legaljurisdictionid, String name, String country,
			String legislativecategories, String identifying, String legalentityregncode, String effectstartdate,
			String effectenddate, String status) {
		super();
		this.actionid = actionid;
		this.legaljurisdictionid = legaljurisdictionid;
		this.name = name;
		this.country = country;
		this.legislativecategories = legislativecategories;
		this.identifying = identifying;
		this.legalentityregncode = legalentityregncode;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.status = status;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public Long getLegaljurisdictionid() {
		return legaljurisdictionid;
	}

	public void setLegaljurisdictionid(Long legaljurisdictionid) {
		this.legaljurisdictionid = legaljurisdictionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLegislativecategories() {
		return legislativecategories;
	}

	public void setLegislativecategories(String legislativecategories) {
		this.legislativecategories = legislativecategories;
	}

	public String getIdentifying() {
		return identifying;
	}

	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}

	public String getLegalentityregncode() {
		return legalentityregncode;
	}

	public void setLegalentityregncode(String legalentityregncode) {
		this.legalentityregncode = legalentityregncode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
