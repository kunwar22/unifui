package in.co.srdt.unif.model.personmanagement;


public class BiographicalInfo {

	private long actionid;

	private long biographicalinfoid;

	private String personid;

	private String personnumber;

	private String dateofbirth;

	private long birthcountry;

	private String birthcountrydsc;

	private String birthregion;

	private String birthtown;

	private long bloodtype;

	private String bloodtypedsc;

	private String correspondncelanguage;

	private String photopathname;

	private String photofiletype;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public BiographicalInfo() {
	}

	public BiographicalInfo(long actionid, long biographicalinfoid, String personid, String personnumber, String dateofbirth, long birthcountry, String birthcountrydsc, String birthregion, String birthtown, long bloodtype, String bloodtypedsc, String correspondncelanguage, String photopathname, String photofiletype, String effectivestartdate, String effectiveenddate, String createdby, String updatedby) {
		this.actionid = actionid;
		this.biographicalinfoid = biographicalinfoid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.dateofbirth = dateofbirth;
		this.birthcountry = birthcountry;
		this.birthcountrydsc = birthcountrydsc;
		this.birthregion = birthregion;
		this.birthtown = birthtown;
		this.bloodtype = bloodtype;
		this.bloodtypedsc = bloodtypedsc;
		this.correspondncelanguage = correspondncelanguage;
		this.photopathname = photopathname;
		this.photofiletype = photofiletype;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getBiographicalinfoid() {
		return biographicalinfoid;
	}

	public void setBiographicalinfoid(long biographicalinfoid) {
		this.biographicalinfoid = biographicalinfoid;
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

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public long getBirthcountry() {
		return birthcountry;
	}

	public void setBirthcountry(long birthcountry) {
		this.birthcountry = birthcountry;
	}

	public String getBirthcountrydsc() {
		return birthcountrydsc;
	}

	public void setBirthcountrydsc(String birthcountrydsc) {
		this.birthcountrydsc = birthcountrydsc;
	}

	public String getBirthregion() {
		return birthregion;
	}

	public void setBirthregion(String birthregion) {
		this.birthregion = birthregion;
	}

	public String getBirthtown() {
		return birthtown;
	}

	public void setBirthtown(String birthtown) {
		this.birthtown = birthtown;
	}

	public long getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(long bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getBloodtypedsc() {
		return bloodtypedsc;
	}

	public void setBloodtypedsc(String bloodtypedsc) {
		this.bloodtypedsc = bloodtypedsc;
	}

	public String getCorrespondncelanguage() {
		return correspondncelanguage;
	}

	public void setCorrespondncelanguage(String correspondncelanguage) {
		this.correspondncelanguage = correspondncelanguage;
	}

	public String getPhotopathname() {
		return photopathname;
	}

	public void setPhotopathname(String photopathname) {
		this.photopathname = photopathname;
	}

	public String getPhotofiletype() {
		return photofiletype;
	}

	public void setPhotofiletype(String photofiletype) {
		this.photofiletype = photofiletype;
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
		return "BiographicalInfo{" +
				"actionid=" + actionid +
				", biographicalinfoid=" + biographicalinfoid +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", dateofbirth='" + dateofbirth + '\'' +
				", birthcountry=" + birthcountry +
				", birthcountrydsc='" + birthcountrydsc + '\'' +
				", birthregion='" + birthregion + '\'' +
				", birthtown='" + birthtown + '\'' +
				", bloodtype=" + bloodtype +
				", bloodtypedsc='" + bloodtypedsc + '\'' +
				", correspondncelanguage='" + correspondncelanguage + '\'' +
				", photopathname='" + photopathname + '\'' +
				", photofiletype='" + photofiletype + '\'' +
				", effectivestartdate='" + effectivestartdate + '\'' +
				", effectiveenddate='" + effectiveenddate + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				'}';
	}
}
