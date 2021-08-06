package in.co.srdt.unif.model.taxdeclaration.chapter6A;

public class Chapter6A {

	private long employeechapter6aid;

	private long chapter6aid;
	//////////////////////////////////
	private String section;

	private String description;

	private String yearlimit;
	//////////////////////////////

	private String personnumber;

	private String years;

	private double damount;

	private double vamount;

	private String status;

	private String remarks;

	private String attachment;
	
	private String modifiedby;
	
	private String createdby;
	

	public Chapter6A() {
		super();
	}

	public Chapter6A(long employeechapter6aid, long chapter6aid, String section, String description,
			String yearlimit, String personnumber, String years, double damount, double vamount, String status,
			String remarks, String attachment) {
		this.employeechapter6aid = employeechapter6aid;
		this.chapter6aid = chapter6aid;
		this.section = section;
		this.description = description;
		this.yearlimit = yearlimit;
		this.personnumber = personnumber;
		this.years = years;
		this.damount = damount;
		this.vamount = vamount;
		this.status = status;
		this.remarks = remarks;
		this.attachment = attachment;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public long getEmployeechapter6aid() {
		return employeechapter6aid;
	}

	public void setEmployeechapter6aid(long employeechapter6aid) {
		this.employeechapter6aid = employeechapter6aid;
	}

	public long getChapter6aid() {
		return chapter6aid;
	}

	public void setChapter6aid(long chapter6aid) {
		this.chapter6aid = chapter6aid;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYearlimit() {
		return yearlimit;
	}

	public void setYearlimit(String yearlimit) {
		this.yearlimit = yearlimit;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public double getDamount() {
		return damount;
	}

	public void setDamount(double damount) {
		this.damount = damount;
	}

	public double getVamount() {
		return vamount;
	}

	public void setVamount(double vamount) {
		this.vamount = vamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "Chapter6A [employeechapter6aid=" + employeechapter6aid + ", chapter6aid=" + chapter6aid + ", section="
				+ section + ", description=" + description + ", yearlimit=" + yearlimit + ", personnumber="
				+ personnumber + ", years=" + years + ", damount=" + damount + ", vamount=" + vamount + ", status="
				+ status + ", remarks=" + remarks + ", attachment=" + attachment + ", modifiedby=" + modifiedby
				+ ", createdby=" + createdby + "]";
	}

}
