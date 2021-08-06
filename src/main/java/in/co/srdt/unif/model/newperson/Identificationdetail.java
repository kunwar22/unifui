package in.co.srdt.unif.model.newperson;


public class Identificationdetail {

	private long actionid;

	private String personid;

	private String personnumber;

	private long title;

	private String firstname;

	private String middilname;

	private String lastname;

	private String dateofbirth;

	private long birthcountry;

	private long birthstate;

	private String birthdistrict;

	private long gender;

	private long bloodtype;

    
   
    
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

    public long getBirthcountry() {
        return birthcountry;
    }

    public void setBirthcountry(long birthcountry) {
        this.birthcountry = birthcountry;
    }

    public long getBirthstate() {
        return birthstate;
    }

    public void setBirthstate(long birthstate) {
        this.birthstate = birthstate;
    }

    public String getBirthdistrict() {
        return birthdistrict;
    }

    public void setBirthdistrict(String birthdistrict) {
        this.birthdistrict = birthdistrict;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public long getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(long bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Identificationdetail() {
	}
    public Identificationdetail(long actionid, String personid, String personnumber, long title, String firstname,
            String middilname, String lastname, String dateofbirth, long birthcountry, long birthstate,
            String birthdistrict, long gender, long bloodtype) {
               
        this.actionid = actionid;
        this.personid = personid;
        this.personnumber = personnumber;
        this.title = title;
        this.firstname = firstname;
        this.middilname = middilname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.birthcountry = birthcountry;
        this.birthstate = birthstate;
        this.birthdistrict = birthdistrict;
        this.gender = gender;
        this.bloodtype = bloodtype;
    }

   

	

}
