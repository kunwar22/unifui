package in.co.srdt.unif.model.rembchild;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChildModel {
	private int childid;

	@NotBlank(message = "Please Select Child's name")
	private String childname;

	private String dob;

	private String gender;

	@NotBlank(message = "Please select Child's class")
	private String childclass;

	@NotBlank(message = "Please enter School Name")
	private String schoolname;

	private String schooladdr;

	@DecimalMin(value= "1.0", message = "Please enter Claim Amount")
	private double fees;

	private double claimamt;

	private String feereceipt;

	private String istwins;

	private String disability;
	private String attachhidden;
	private String filepres;

	public ChildModel() {
		super();
	}

	public ChildModel(int childid, @NotBlank(message = "Please Select Child's name") String childname, String dob,
			String gender, @NotBlank(message = "Please select Child's Class") String childclass, String schoolname,
			String schooladdr, @DecimalMin(value= "1.0", message = "Please enter Claim Amount") double fees,
			double claimamt, String feereceipt, String istwins, String disability, String attachhidden,
			String filepres) {
		super();
		this.childid = childid;
		this.childname = childname;
		this.dob = dob;
		this.gender = gender;
		this.childclass = childclass;
		this.schoolname = schoolname;
		this.schooladdr = schooladdr;
		this.fees = fees;
		this.claimamt = claimamt;
		this.feereceipt = feereceipt;
		this.istwins = istwins;
		this.disability = disability;
		this.attachhidden = attachhidden;
		this.filepres = filepres;
	}

	public int getChildid() {
		return childid;
	}

	public void setChildid(int childid) {
		this.childid = childid;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getChildclass() {
		return childclass;
	}

	public void setChildclass(String childclass) {
		this.childclass = childclass;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getSchooladdr() {
		return schooladdr;
	}

	public void setSchooladdr(String schooladdr) {
		this.schooladdr = schooladdr;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(double claimamt) {
		this.claimamt = claimamt;
	}

	public String getFeereceipt() {
		return feereceipt;
	}

	public void setFeereceipt(String feereceipt) {
		this.feereceipt = feereceipt;
	}

	public String getIstwins() {
		return istwins;
	}

	public void setIstwins(String istwins) {
		this.istwins = istwins;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public String getFilepres() {
		return filepres;
	}

	public void setFilepres(String filepres) {
		this.filepres = filepres;
	}

	@Override
	public String toString() {
		return "ChildModel [childid=" + childid + ", childname=" + childname + ", dob=" + dob + ", gender=" + gender
				+ ", childclass=" + childclass + ", schoolname=" + schoolname + ", schooladdr=" + schooladdr + ", fees="
				+ fees + ", claimamt=" + claimamt + ", feereceipt=" + feereceipt + ", istwins=" + istwins
				+ ", disability=" + disability + ", attachhidden=" + attachhidden + ", filepres=" + filepres + "]";
	}
	
}
