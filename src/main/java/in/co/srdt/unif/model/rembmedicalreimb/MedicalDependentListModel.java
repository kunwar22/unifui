package in.co.srdt.unif.model.rembmedicalreimb;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MedicalDependentListModel {

	private long dependentid;
	
	private long dependentnameid;
	private String dependentname;
	private String dateofbirth;
	private String age;
	private long relationshipid;
	private String relationship;
	private String gender;
	
	@NotBlank(message = "Enter Marital Status")
	private String maritalstatus;
	
	public MedicalDependentListModel() {
		super();
	}

	public MedicalDependentListModel(long dependentid, long dependentnameid, String dependentname, String dateofbirth,
			String age, long relationshipid, String relationship, String gender,
			@NotBlank(message = "Enter Marital Status") String maritalstatus) {
		super();
		this.dependentid = dependentid;
		this.dependentnameid = dependentnameid;
		this.dependentname = dependentname;
		this.dateofbirth = dateofbirth;
		this.age = age;
		this.relationshipid = relationshipid;
		this.relationship = relationship;
		this.gender = gender;
		this.maritalstatus = maritalstatus;
	}

	public long getDependentid() {
		return dependentid;
	}

	public void setDependentid(long dependentid) {
		this.dependentid = dependentid;
	}

	public long getDependentnameid() {
		return dependentnameid;
	}

	public void setDependentnameid(long dependentnameid) {
		this.dependentnameid = dependentnameid;
	}

	public String getDependentname() {
		return dependentname;
	}

	public void setDependentname(String dependentname) {
		this.dependentname = dependentname;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public long getRelationshipid() {
		return relationshipid;
	}

	public void setRelationshipid(long relationshipid) {
		this.relationshipid = relationshipid;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	@Override
	public String toString() {
		return "MedicalDependentListModel [dependentid=" + dependentid + ", dependentnameid=" + dependentnameid
				+ ", dependentname=" + dependentname + ", dateofbirth=" + dateofbirth + ", age=" + age
				+ ", relationshipid=" + relationshipid + ", relationship=" + relationship + ", gender=" + gender
				+ ", maritalstatus=" + maritalstatus + "]";
	}
		
}	
	