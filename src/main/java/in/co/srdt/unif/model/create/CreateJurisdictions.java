package in.co.srdt.unif.model.create;

public class CreateJurisdictions {

	private long actionid;
	private long legaljurisdictionid;
	private String name;
	private long country;
	private String countryname;
	private long legislativecategories;
	private String legislativecategoriesname;
	private String identifying;
	private long legalentityregncode;
	private String legalentityregncodename;
	private String effectstartdate;
	private String effectenddate;
	private String status;
	
	

	public CreateJurisdictions() {
		super();
	}



	public CreateJurisdictions(long actionid, long legaljurisdictionid, String name, long country, String countryname,
			long legislativecategories, String legislativecategoriesname, String identifying, long legalentityregncode,
			String legalentityregncodename, String effectstartdate, String effectenddate, String status) {
		super();
		this.actionid = actionid;
		this.legaljurisdictionid = legaljurisdictionid;
		this.name = name;
		this.country = country;
		this.countryname = countryname;
		this.legislativecategories = legislativecategories;
		this.legislativecategoriesname = legislativecategoriesname;
		this.identifying = identifying;
		this.legalentityregncode = legalentityregncode;
		this.legalentityregncodename = legalentityregncodename;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.status = status;
		
	}



	public long getActionid() {
		return actionid;
	}



	public void setActionid(long actionid) {
		this.actionid = actionid;
	}



	public long getLegaljurisdictionid() {
		return legaljurisdictionid != 0 ? legaljurisdictionid:0;
		
	}



	public void setLegaljurisdictionid(long legaljurisdictionid) {
		this.legaljurisdictionid = legaljurisdictionid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public long getLegislativecategories() {
		return legislativecategories;
	}



	public void setLegislativecategories(long legislativecategories) {
		this.legislativecategories = legislativecategories;
	}



	public String getLegislativecategoriesname() {
		return legislativecategoriesname;
	}



	public void setLegislativecategoriesname(String legislativecategoriesname) {
		this.legislativecategoriesname = legislativecategoriesname;
	}



	public String getIdentifying() {
		return identifying;
	}



	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}



	public long getLegalentityregncode() {
		return legalentityregncode;
	}



	public void setLegalentityregncode(long legalentityregncode) {
		this.legalentityregncode = legalentityregncode;
	}



	public String getLegalentityregncodename() {
		return legalentityregncodename;
	}



	public void setLegalentityregncodename(String legalentityregncodename) {
		this.legalentityregncodename = legalentityregncodename;
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
