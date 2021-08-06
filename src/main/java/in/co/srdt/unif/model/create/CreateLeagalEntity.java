package in.co.srdt.unif.model.create;

public class CreateLeagalEntity {
	
	
	private long actionid;
	private long country;
	private String countryname;
	private String effectenddate;
	private String effectstartdate;
	private long identifyingjurisdictions;
	private String identifyingjurisdictionsname;
	private String islegalemployer;
	private String ispayrollstatutoryunit;
	private long legaladdress;
	private String legaladdressname;
	private String legalentityidentifier;
	private String name;
	private String placeofregistration;
	private String registrationnumber;
	private String tinpan;
	private long legalentityid;
	
	/*********** added new from here on 20-07-20 *********************/

	private String registrationdate;

	/// PF INFO TAB

	private String pfnumber;
	private String pfsignatoryname;
	private String pfregistrationdate;
	private String pfrulesforemployee;

	// ESI INFO TAB

	private String esinumber;
	private String esisignatoryname;
	private String esiregistrationdate;

	// PI INFO TAB
	private String pinumber;
	private String pisignatoryname;
	private String piregistrationdate;

	// IT INFO TAB
	

	private String tannumber;
	private String tancircle;
	private String citortds;

	// LABOUR LAW INFO TAB
	private String laboursignatoryname;
	private String labouraddress;
	private String additionalatr;
	
	public CreateLeagalEntity() {
	}

	public CreateLeagalEntity(long actionid, long country, String countryname, String effectenddate,
			String effectstartdate, long identifyingjurisdictions, String identifyingjurisdictionsname,
			String islegalemployer, String ispayrollstatutoryunit, long legaladdress, String legaladdressname,
			String legalentityidentifier, String name, String placeofregistration, String registrationnumber,
			String tinpan, long legalentityid, String registrationdate, String pfnumber, String pfsignatoryname,
			String pfregistrationdate, String pfrulesforemployee, String esinumber, String esisignatoryname,
			String esiregistrationdate, String pinumber, String pisignatoryname, String piregistrationdate,
			String tannumber, String tancircle, String citortds, String laboursignatoryname, String labouraddress,
			String additionalatr) {
		super();
		this.actionid = actionid;
		this.country = country;
		this.countryname = countryname;
		this.effectenddate = effectenddate;
		this.effectstartdate = effectstartdate;
		this.identifyingjurisdictions = identifyingjurisdictions;
		this.identifyingjurisdictionsname = identifyingjurisdictionsname;
		this.islegalemployer = islegalemployer;
		this.ispayrollstatutoryunit = ispayrollstatutoryunit;
		this.legaladdress = legaladdress;
		this.legaladdressname = legaladdressname;
		this.legalentityidentifier = legalentityidentifier;
		this.name = name;
		this.placeofregistration = placeofregistration;
		this.registrationnumber = registrationnumber;
		this.tinpan = tinpan;
		this.legalentityid = legalentityid;
		this.registrationdate = registrationdate;
		this.pfnumber = pfnumber;
		this.pfsignatoryname = pfsignatoryname;
		this.pfregistrationdate = pfregistrationdate;
		this.pfrulesforemployee = pfrulesforemployee;
		this.esinumber = esinumber;
		this.esisignatoryname = esisignatoryname;
		this.esiregistrationdate = esiregistrationdate;
		this.pinumber = pinumber;
		this.pisignatoryname = pisignatoryname;
		this.piregistrationdate = piregistrationdate;
		this.tannumber = tannumber;
		this.tancircle = tancircle;
		this.citortds = citortds;
		this.laboursignatoryname = laboursignatoryname;
		this.labouraddress = labouraddress;
		this.additionalatr = additionalatr;
	}

	@Override
	public String toString() {
		return "CreateLeagalEntity [actionid=" + actionid + ", country=" + country + ", countryname=" + countryname
				+ ", effectenddate=" + effectenddate + ", effectstartdate=" + effectstartdate
				+ ", identifyingjurisdictions=" + identifyingjurisdictions + ", identifyingjurisdictionsname="
				+ identifyingjurisdictionsname + ", islegalemployer=" + islegalemployer + ", ispayrollstatutoryunit="
				+ ispayrollstatutoryunit + ", legaladdress=" + legaladdress + ", legaladdressname=" + legaladdressname
				+ ", legalentityidentifier=" + legalentityidentifier + ", name=" + name + ", placeofregistration="
				+ placeofregistration + ", registrationnumber=" + registrationnumber + ", tinpan=" + tinpan
				+ ", legalentityid=" + legalentityid + ", registrationdate=" + registrationdate + ", pfnumber="
				+ pfnumber + ", pfsignatoryname=" + pfsignatoryname + ", pfregistrationdate=" + pfregistrationdate
				+ ", pfrulesforemployee=" + pfrulesforemployee + ", esinumber=" + esinumber + ", esisignatoryname="
				+ esisignatoryname + ", esiregistrationdate=" + esiregistrationdate + ", pinumber=" + pinumber
				+ ", pisignatoryname=" + pisignatoryname + ", piregistrationdate=" + piregistrationdate + ", tannumber="
				+ tannumber + ", tancircle=" + tancircle + ", citortds=" + citortds + ", laboursignatoryname="
				+ laboursignatoryname + ", labouraddress=" + labouraddress + ", additionalatr=" + additionalatr + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
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

	public String getEffectenddate() {
		return effectenddate;
	}

	public void setEffectenddate(String effectenddate) {
		this.effectenddate = effectenddate;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public long getIdentifyingjurisdictions() {
		return identifyingjurisdictions;
	}

	public void setIdentifyingjurisdictions(long identifyingjurisdictions) {
		this.identifyingjurisdictions = identifyingjurisdictions;
	}

	public String getIdentifyingjurisdictionsname() {
		return identifyingjurisdictionsname;
	}

	public void setIdentifyingjurisdictionsname(String identifyingjurisdictionsname) {
		this.identifyingjurisdictionsname = identifyingjurisdictionsname;
	}

	public String getIslegalemployer() {
		return islegalemployer;
	}

	public void setIslegalemployer(String islegalemployer) {
		this.islegalemployer = islegalemployer;
	}

	public String getIspayrollstatutoryunit() {
		return ispayrollstatutoryunit;
	}

	public void setIspayrollstatutoryunit(String ispayrollstatutoryunit) {
		this.ispayrollstatutoryunit = ispayrollstatutoryunit;
	}

	public long getLegaladdress() {
		return legaladdress;
	}

	public void setLegaladdress(long legaladdress) {
		this.legaladdress = legaladdress;
	}

	public String getLegaladdressname() {
		return legaladdressname;
	}

	public void setLegaladdressname(String legaladdressname) {
		this.legaladdressname = legaladdressname;
	}

	public String getLegalentityidentifier() {
		return legalentityidentifier;
	}

	public void setLegalentityidentifier(String legalentityidentifier) {
		this.legalentityidentifier = legalentityidentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceofregistration() {
		return placeofregistration;
	}

	public void setPlaceofregistration(String placeofregistration) {
		this.placeofregistration = placeofregistration;
	}

	public String getRegistrationnumber() {
		return registrationnumber;
	}

	public void setRegistrationnumber(String registrationnumber) {
		this.registrationnumber = registrationnumber;
	}

	public String getTinpan() {
		return tinpan;
	}

	public void setTinpan(String tinpan) {
		this.tinpan = tinpan;
	}

	public long getLegalentityid() {
		return legalentityid;
	}

	public void setLegalentityid(long legalentityid) {
		this.legalentityid = legalentityid;
	}

	public String getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(String registrationdate) {
		this.registrationdate = registrationdate;
	}

	public String getPfnumber() {
		return pfnumber;
	}

	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}

	public String getPfsignatoryname() {
		return pfsignatoryname;
	}

	public void setPfsignatoryname(String pfsignatoryname) {
		this.pfsignatoryname = pfsignatoryname;
	}

	public String getPfregistrationdate() {
		return pfregistrationdate;
	}

	public void setPfregistrationdate(String pfregistrationdate) {
		this.pfregistrationdate = pfregistrationdate;
	}

	public String getPfrulesforemployee() {
		return pfrulesforemployee;
	}

	public void setPfrulesforemployee(String pfrulesforemployee) {
		this.pfrulesforemployee = pfrulesforemployee;
	}

	public String getEsinumber() {
		return esinumber;
	}

	public void setEsinumber(String esinumber) {
		this.esinumber = esinumber;
	}

	public String getEsisignatoryname() {
		return esisignatoryname;
	}

	public void setEsisignatoryname(String esisignatoryname) {
		this.esisignatoryname = esisignatoryname;
	}

	public String getEsiregistrationdate() {
		return esiregistrationdate;
	}

	public void setEsiregistrationdate(String esiregistrationdate) {
		this.esiregistrationdate = esiregistrationdate;
	}

	public String getPinumber() {
		return pinumber;
	}

	public void setPinumber(String pinumber) {
		this.pinumber = pinumber;
	}

	public String getPisignatoryname() {
		return pisignatoryname;
	}

	public void setPisignatoryname(String pisignatoryname) {
		this.pisignatoryname = pisignatoryname;
	}

	public String getPiregistrationdate() {
		return piregistrationdate;
	}

	public void setPiregistrationdate(String piregistrationdate) {
		this.piregistrationdate = piregistrationdate;
	}

	public String getTannumber() {
		return tannumber;
	}

	public void setTannumber(String tannumber) {
		this.tannumber = tannumber;
	}

	public String getTancircle() {
		return tancircle;
	}

	public void setTancircle(String tancircle) {
		this.tancircle = tancircle;
	}

	public String getCitortds() {
		return citortds;
	}

	public void setCitortds(String citortds) {
		this.citortds = citortds;
	}

	public String getLaboursignatoryname() {
		return laboursignatoryname;
	}

	public void setLaboursignatoryname(String laboursignatoryname) {
		this.laboursignatoryname = laboursignatoryname;
	}

	public String getLabouraddress() {
		return labouraddress;
	}

	public void setLabouraddress(String labouraddress) {
		this.labouraddress = labouraddress;
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

}
