package in.co.srdt.unif.model.personmanagement;



public class PersonalRecordMng {

	private long actionid;

	private String personid;

	private String personnumber;

	private long title;

	private String titledsc;

	private String firstname;

	private String middilname;

	private String lastname;

	private long gender;

	private String genderdsc;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;
	
	private String mode;
	
	private String fathername;

	public PersonalRecordMng() {
		
	}

	public PersonalRecordMng(long actionid, String personid, String personnumber, long title, String titledsc,
			String firstname, String middilname, String lastname, long gender, String genderdsc,
			String effectivestartdate, String effectiveenddate, String createdby, String updatedby, String mode,
			String fathername) {
		super();
		this.actionid = actionid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.title = title;
		this.titledsc = titledsc;
		this.firstname = firstname;
		this.middilname = middilname;
		this.lastname = lastname;
		this.gender = gender;
		this.genderdsc = genderdsc;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.mode = mode;
		this.fathername = fathername;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
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

	public long getTitle() {
		return title;
	}

	public void setTitle(long title) {
		this.title = title;
	}

	public String getTitledsc() {
		return titledsc;
	}

	public void setTitledsc(String titledsc) {
		this.titledsc = titledsc;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddilname() {
		return middilname;
	}

	public void setMiddilname(String middilname) {
		this.middilname = middilname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	public String getGenderdsc() {
		return genderdsc;
	}

	public void setGenderdsc(String genderdsc) {
		this.genderdsc = genderdsc;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	@Override
	public String toString() {
		return "PersonalRecordMng [actionid=" + actionid + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", title=" + title + ", titledsc=" + titledsc + ", firstname=" + firstname + ", middilname="
				+ middilname + ", lastname=" + lastname + ", gender=" + gender + ", genderdsc=" + genderdsc
				+ ", effectivestartdate=" + effectivestartdate + ", effectiveenddate=" + effectiveenddate
				+ ", createdby=" + createdby + ", updatedby=" + updatedby + ", mode=" + mode + ", fathername="
				+ fathername + "]";
	}
	
	
	
	
}