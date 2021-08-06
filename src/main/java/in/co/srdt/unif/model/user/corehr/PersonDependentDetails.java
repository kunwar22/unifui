package in.co.srdt.unif.model.user.corehr;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class PersonDependentDetails {
	
	   private long actionid;
	   private String personid;
	   private String emergencycontact;
	   
	   private long addresstype;
	   private String addresstypedsc;
	   private String age;
	   private String citytown;
	   private long countryid;
	   private String  countryiddsc;
	   @NotBlank(message = "Please enter date of birth.")
	   private String dateofbirth;
	   private long dependentdetailsid;
	   private String disability;
	   @NotBlank(message = "Please enter Start Date.")
	   private String effectivestartdate;
	   private String emailaddress;
	   private String emergencycontac;
	   private String fulladdress;
	   @NotBlank(message = "Please enter Full Name.")
	   private String fullname;
	   @Min(value = 1, message = "Please enter Gender.")
	   private long gender;
	   private String genderdsc;
	   private String  guardianaddress;
	   private String guardianbirthdate;
	   private String guardianname;
	   private String  guardianphone;
	   private String personnumber;
	   private String phonenumber;
	   private String pincode;
	   @Min(value = 1, message = "Please enter Relationship.")
	   private long relationship;
	   private String relationshipdsc;
	   private long sameaspersonaddress;
	   private String sameaspersonaddressdsc;
	   private long state;
	   private String statedsc;
	  
	   private long title;
	   private String titledsc;
	   private String twins="No";

	   public PersonDependentDetails() {
		super();
	   }

	public PersonDependentDetails(long actionid, String personid, String emergencycontact, long addresstype,
			String addresstypedsc, String age, String citytown, long countryid, String countryiddsc,
			@NotBlank(message = "Please enter date of birth.") String dateofbirth, long dependentdetailsid,
			String disability, @NotBlank(message = "Please enter Start Date.") String effectivestartdate,
			String emailaddress, String emergencycontac, String fulladdress,
			@NotBlank(message = "Please enter Full Name.") String fullname,
			@Min(value = 1, message = "Please enter Gender.") long gender, String genderdsc, String guardianaddress,
			String guardianbirthdate, String guardianname, String guardianphone, String personnumber,
			String phonenumber, String pincode,
			@Min(value = 1, message = "Please enter Relationship.") long relationship, String relationshipdsc,
			long sameaspersonaddress, String sameaspersonaddressdsc, long state, String statedsc, long title,
			String titledsc, String twins) {
		super();
		this.actionid = actionid;
		this.personid = personid;
		this.emergencycontact = emergencycontact;
		this.addresstype = addresstype;
		this.addresstypedsc = addresstypedsc;
		this.age = age;
		this.citytown = citytown;
		this.countryid = countryid;
		this.countryiddsc = countryiddsc;
		this.dateofbirth = dateofbirth;
		this.dependentdetailsid = dependentdetailsid;
		this.disability = disability;
		this.effectivestartdate = effectivestartdate;
		this.emailaddress = emailaddress;
		this.emergencycontac = emergencycontac;
		this.fulladdress = fulladdress;
		this.fullname = fullname;
		this.gender = gender;
		this.genderdsc = genderdsc;
		this.guardianaddress = guardianaddress;
		this.guardianbirthdate = guardianbirthdate;
		this.guardianname = guardianname;
		this.guardianphone = guardianphone;
		this.personnumber = personnumber;
		this.phonenumber = phonenumber;
		this.pincode = pincode;
		this.relationship = relationship;
		this.relationshipdsc = relationshipdsc;
		this.sameaspersonaddress = sameaspersonaddress;
		this.sameaspersonaddressdsc = sameaspersonaddressdsc;
		this.state = state;
		this.statedsc = statedsc;
		this.title = title;
		this.titledsc = titledsc;
		this.twins = twins;
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

	public String getEmergencycontact() {
		return emergencycontact;
	}

	public void setEmergencycontact(String emergencycontact) {
		this.emergencycontact = emergencycontact;
	}

	public long getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(long addresstype) {
		this.addresstype = addresstype;
	}

	public String getAddresstypedsc() {
		return addresstypedsc;
	}

	public void setAddresstypedsc(String addresstypedsc) {
		this.addresstypedsc = addresstypedsc;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCitytown() {
		return citytown;
	}

	public void setCitytown(String citytown) {
		this.citytown = citytown;
	}

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	public String getCountryiddsc() {
		return countryiddsc;
	}

	public void setCountryiddsc(String countryiddsc) {
		this.countryiddsc = countryiddsc;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public long getDependentdetailsid() {
		return dependentdetailsid;
	}

	public void setDependentdetailsid(long dependentdetailsid) {
		this.dependentdetailsid = dependentdetailsid;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmergencycontac() {
		return emergencycontac;
	}

	public void setEmergencycontac(String emergencycontac) {
		this.emergencycontac = emergencycontac;
	}

	public String getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getGuardianaddress() {
		return guardianaddress;
	}

	public void setGuardianaddress(String guardianaddress) {
		this.guardianaddress = guardianaddress;
	}

	public String getGuardianbirthdate() {
		return guardianbirthdate;
	}

	public void setGuardianbirthdate(String guardianbirthdate) {
		this.guardianbirthdate = guardianbirthdate;
	}

	public String getGuardianname() {
		return guardianname;
	}

	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}

	public String getGuardianphone() {
		return guardianphone;
	}

	public void setGuardianphone(String guardianphone) {
		this.guardianphone = guardianphone;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public long getRelationship() {
		return relationship;
	}

	public void setRelationship(long relationship) {
		this.relationship = relationship;
	}

	public String getRelationshipdsc() {
		return relationshipdsc;
	}

	public void setRelationshipdsc(String relationshipdsc) {
		this.relationshipdsc = relationshipdsc;
	}

	public long getSameaspersonaddress() {
		return sameaspersonaddress;
	}

	public void setSameaspersonaddress(long sameaspersonaddress) {
		this.sameaspersonaddress = sameaspersonaddress;
	}

	public String getSameaspersonaddressdsc() {
		return sameaspersonaddressdsc;
	}

	public void setSameaspersonaddressdsc(String sameaspersonaddressdsc) {
		this.sameaspersonaddressdsc = sameaspersonaddressdsc;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public String getStatedsc() {
		return statedsc;
	}

	public void setStatedsc(String statedsc) {
		this.statedsc = statedsc;
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

	public String getTwins() {
		return twins;
	}

	public void setTwins(String twins) {
		this.twins = twins;
	}

	@Override
	public String toString() {
		return "PersonDependentDetails [actionid=" + actionid + ", personid=" + personid + ", emergencycontact="
				+ emergencycontact + ", addresstype=" + addresstype + ", addresstypedsc=" + addresstypedsc + ", age="
				+ age + ", citytown=" + citytown + ", countryid=" + countryid + ", countryiddsc=" + countryiddsc
				+ ", dateofbirth=" + dateofbirth + ", dependentdetailsid=" + dependentdetailsid + ", disability="
				+ disability + ", effectivestartdate=" + effectivestartdate + ", emailaddress=" + emailaddress
				+ ", emergencycontac=" + emergencycontac + ", fulladdress=" + fulladdress + ", fullname=" + fullname
				+ ", gender=" + gender + ", genderdsc=" + genderdsc + ", guardianaddress=" + guardianaddress
				+ ", guardianbirthdate=" + guardianbirthdate + ", guardianname=" + guardianname + ", guardianphone="
				+ guardianphone + ", personnumber=" + personnumber + ", phonenumber=" + phonenumber + ", pincode="
				+ pincode + ", relationship=" + relationship + ", relationshipdsc=" + relationshipdsc
				+ ", sameaspersonaddress=" + sameaspersonaddress + ", sameaspersonaddressdsc=" + sameaspersonaddressdsc
				+ ", state=" + state + ", statedsc=" + statedsc + ", title=" + title + ", titledsc=" + titledsc
				+ ", twins=" + twins + "]";
	}

	
}
