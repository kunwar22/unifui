package in.co.srdt.unif.model.personmanagement;

import java.util.List;

public class PersonalRecordManageMaster {

	
	private String personid;

	private String personnumber;

	private PersonalRecordMng personalrecord;

	private List<AddressDetails> addressdetails;

	private List<ContactDetails> contactdetails;

	private List<EmailDetails> emaildetails;

	private MaritalDetails maritaldetails;

	private List<NationalDetails> nationaldetails;

	private ReligionDetails religiondetails;

	private BiographicalInfo biographicalinfo;

	public PersonalRecordManageMaster() {
		super();
	}

	public PersonalRecordManageMaster(String personid, String personnumber, PersonalRecordMng personalrecord,
			List<AddressDetails> addressdetails, List<ContactDetails> contactdetails, List<EmailDetails> emaildetails,
			MaritalDetails maritaldetails, List<NationalDetails> nationaldetails, ReligionDetails religiondetails,
			BiographicalInfo biographicalinfo) {
		super();
		this.personid = personid;
		this.personnumber = personnumber;
		this.personalrecord = personalrecord;
		this.addressdetails = addressdetails;
		this.contactdetails = contactdetails;
		this.emaildetails = emaildetails;
		this.maritaldetails = maritaldetails;
		this.nationaldetails = nationaldetails;
		this.religiondetails = religiondetails;
		this.biographicalinfo = biographicalinfo;
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

	public PersonalRecordMng getPersonalrecord() {
		return personalrecord;
	}

	public void setPersonalrecord(PersonalRecordMng personalrecord) {
		this.personalrecord = personalrecord;
	}

	public List<AddressDetails> getAddressdetails() {
		return addressdetails;
	}

	public void setAddressdetails(List<AddressDetails> addressdetails) {
		this.addressdetails = addressdetails;
	}

	public List<ContactDetails> getContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(List<ContactDetails> contactdetails) {
		this.contactdetails = contactdetails;
	}

	public List<EmailDetails> getEmaildetails() {
		return emaildetails;
	}

	public void setEmaildetails(List<EmailDetails> emaildetails) {
		this.emaildetails = emaildetails;
	}

	public MaritalDetails getMaritaldetails() {
		return maritaldetails;
	}

	public void setMaritaldetails(MaritalDetails maritaldetails) {
		this.maritaldetails = maritaldetails;
	}

	public List<NationalDetails> getNationaldetails() {
		return nationaldetails;
	}

	public void setNationaldetails(List<NationalDetails> nationaldetails) {
		this.nationaldetails = nationaldetails;
	}

	public ReligionDetails getReligiondetails() {
		return religiondetails;
	}

	public void setReligiondetails(ReligionDetails religiondetails) {
		this.religiondetails = religiondetails;
	}

	public BiographicalInfo getBiographicalinfo() {
		return biographicalinfo;
	}

	public void setBiographicalinfo(BiographicalInfo biographicalinfo) {
		this.biographicalinfo = biographicalinfo;
	}

	@Override
	public String toString() {
		return "PersonalRecordManageMaster [personid=" + personid + ", personnumber=" + personnumber
				+ ", personalrecord=" + personalrecord + ", addressdetails=" + addressdetails + ", contactdetails="
				+ contactdetails + ", emaildetails=" + emaildetails + ", maritaldetails=" + maritaldetails
				+ ", nationaldetails=" + nationaldetails + ", religiondetails=" + religiondetails
				+ ", biographicalinfo=" + biographicalinfo + "]";
	}

	
}
