package in.co.srdt.unif.model.user.corehr;

public class PersonalDetails {
	
	private long personaltblactionid;
	
	private String personid;

	private String personnumber;
	
	private long personaactionid;
	
	private long titleid;
	
	private String title;

	private String firstname;

	private String middilname;

	private String lastname;

	private String dateofbirth;
	
	private long birthcountryid;

	private String birthcountryname;
	
	private long genderid;

	private String gendername;
	
	
	
	private long maritalactionid;
	
	private long maritalid;
	
	private long maritalstatusid;

	private String maritalstatusname;

	private String marriagedate;
	
	
	
	private long religionid;                //new
	
	private String religionname;            //new

	private String religionasofdate;        //new
	
	private long nationalactionid;
	
	private long nationaldetailsid;
	
	private long nationalcountryid;

	private String nationalcountryname;
	
	private long nationaltypeid;

	private String nationaltypename;
	
	private String nationalid;
	
	public PersonalDetails() {
		
	}

	public PersonalDetails(long personaltblactionid, String personid, String personnumber, long personaactionid,
			long titleid, String title, String firstname, String middilname, String lastname, String dateofbirth,
			long birthcountryid, String birthcountryname, long genderid, String gendername, long maritalactionid,
			long maritalid, long maritalstatusid, String maritalstatusname, String marriagedate, long religionid,
			String religionname, String religionasofdate, long nationalactionid, long nationaldetailsid,
			long nationalcountryid, String nationalcountryname, long nationaltypeid, String nationaltypename,
			String nationalid) {
		super();
		this.personaltblactionid = personaltblactionid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.personaactionid = personaactionid;
		this.titleid = titleid;
		this.title = title;
		this.firstname = firstname;
		this.middilname = middilname;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.birthcountryid = birthcountryid;
		this.birthcountryname = birthcountryname;
		this.genderid = genderid;
		this.gendername = gendername;
		this.maritalactionid = maritalactionid;
		this.maritalid = maritalid;
		this.maritalstatusid = maritalstatusid;
		this.maritalstatusname = maritalstatusname;
		this.marriagedate = marriagedate;
		this.religionid = religionid;
		this.religionname = religionname;
		this.religionasofdate = religionasofdate;
		this.nationalactionid = nationalactionid;
		this.nationaldetailsid = nationaldetailsid;
		this.nationalcountryid = nationalcountryid;
		this.nationalcountryname = nationalcountryname;
		this.nationaltypeid = nationaltypeid;
		this.nationaltypename = nationaltypename;
		this.nationalid = nationalid;
	}

	@Override
	public String toString() {
		return "PersonalDetails [personaltblactionid=" + personaltblactionid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", personaactionid=" + personaactionid + ", titleid=" + titleid
				+ ", title=" + title + ", firstname=" + firstname + ", middilname=" + middilname + ", lastname="
				+ lastname + ", dateofbirth=" + dateofbirth + ", birthcountryid=" + birthcountryid
				+ ", birthcountryname=" + birthcountryname + ", genderid=" + genderid + ", gendername=" + gendername
				+ ", maritalactionid=" + maritalactionid + ", maritalid=" + maritalid + ", maritalstatusid="
				+ maritalstatusid + ", maritalstatusname=" + maritalstatusname + ", marriagedate=" + marriagedate
				+ ", religionid=" + religionid + ", religionname=" + religionname + ", religionasofdate="
				+ religionasofdate + ", nationalactionid=" + nationalactionid + ", nationaldetailsid="
				+ nationaldetailsid + ", nationalcountryid=" + nationalcountryid + ", nationalcountryname="
				+ nationalcountryname + ", nationaltypeid=" + nationaltypeid + ", nationaltypename=" + nationaltypename
				+ ", nationalid=" + nationalid + "]";
	}

	public long getPersonaltblactionid() {
		return personaltblactionid;
	}

	public void setPersonaltblactionid(long personaltblactionid) {
		this.personaltblactionid = personaltblactionid;
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

	public long getPersonaactionid() {
		return personaactionid;
	}

	public void setPersonaactionid(long personaactionid) {
		this.personaactionid = personaactionid;
	}

	public long getTitleid() {
		return titleid;
	}

	public void setTitleid(long titleid) {
		this.titleid = titleid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public long getBirthcountryid() {
		return birthcountryid;
	}

	public void setBirthcountryid(long birthcountryid) {
		this.birthcountryid = birthcountryid;
	}

	public String getBirthcountryname() {
		return birthcountryname;
	}

	public void setBirthcountryname(String birthcountryname) {
		this.birthcountryname = birthcountryname;
	}

	public long getGenderid() {
		return genderid;
	}

	public void setGenderid(long genderid) {
		this.genderid = genderid;
	}

	public String getGendername() {
		return gendername;
	}

	public void setGendername(String gendername) {
		this.gendername = gendername;
	}

	public long getMaritalactionid() {
		return maritalactionid;
	}

	public void setMaritalactionid(long maritalactionid) {
		this.maritalactionid = maritalactionid;
	}

	public long getMaritalid() {
		return maritalid;
	}

	public void setMaritalid(long maritalid) {
		this.maritalid = maritalid;
	}

	public long getMaritalstatusid() {
		return maritalstatusid;
	}

	public void setMaritalstatusid(long maritalstatusid) {
		this.maritalstatusid = maritalstatusid;
	}

	public String getMaritalstatusname() {
		return maritalstatusname;
	}

	public void setMaritalstatusname(String maritalstatusname) {
		this.maritalstatusname = maritalstatusname;
	}

	public String getMarriagedate() {
		return marriagedate;
	}

	public void setMarriagedate(String marriagedate) {
		this.marriagedate = marriagedate;
	}

	public long getReligionid() {
		return religionid;
	}

	public void setReligionid(long religionid) {
		this.religionid = religionid;
	}

	public String getReligionname() {
		return religionname;
	}

	public void setReligionname(String religionname) {
		this.religionname = religionname;
	}

	public String getReligionasofdate() {
		return religionasofdate;
	}

	public void setReligionasofdate(String religionasofdate) {
		this.religionasofdate = religionasofdate;
	}

	public long getNationalactionid() {
		return nationalactionid;
	}

	public void setNationalactionid(long nationalactionid) {
		this.nationalactionid = nationalactionid;
	}

	public long getNationaldetailsid() {
		return nationaldetailsid;
	}

	public void setNationaldetailsid(long nationaldetailsid) {
		this.nationaldetailsid = nationaldetailsid;
	}

	public long getNationalcountryid() {
		return nationalcountryid;
	}

	public void setNationalcountryid(long nationalcountryid) {
		this.nationalcountryid = nationalcountryid;
	}

	public String getNationalcountryname() {
		return nationalcountryname;
	}

	public void setNationalcountryname(String nationalcountryname) {
		this.nationalcountryname = nationalcountryname;
	}

	public long getNationaltypeid() {
		return nationaltypeid;
	}

	public void setNationaltypeid(long nationaltypeid) {
		this.nationaltypeid = nationaltypeid;
	}

	public String getNationaltypename() {
		return nationaltypename;
	}

	public void setNationaltypename(String nationaltypename) {
		this.nationaltypename = nationaltypename;
	}

	public String getNationalid() {
		return nationalid;
	}

	public void setNationalid(String nationalid) {
		this.nationalid = nationalid;
	}
	
	

}
