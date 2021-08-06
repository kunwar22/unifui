package in.co.srdt.unif.model.employmentmanagement;


public class SalaryBasisDetails {

	private long actionid;

	private long salarybasisid;

	private String personid;

	private String personnumber;

	private long frequency;

	private long salarytype;

	private String frequencydsc;

	private String salarytypedsc;

	private double rateofpay;

	private String effectivestartdate;

	private String effectiveenddate;

	private long grade;

	private long gradesteps;

	private String gradedsc;

	private String gradestepsdsc;
	
	private String createdby;

	private String updatedby;

	public SalaryBasisDetails() {
		super();
	}

	public SalaryBasisDetails(long actionid, long salarybasisid, String personid, String personnumber, long frequency,
			long salarytype, String frequencydsc, String salarytypedsc, double rateofpay, String effectivestartdate,
			String effectiveenddate, long grade, long gradesteps, String gradedsc, String gradestepsdsc,
			String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.salarybasisid = salarybasisid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.frequency = frequency;
		this.salarytype = salarytype;
		this.frequencydsc = frequencydsc;
		this.salarytypedsc = salarytypedsc;
		this.rateofpay = rateofpay;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.grade = grade;
		this.gradesteps = gradesteps;
		this.gradedsc = gradedsc;
		this.gradestepsdsc = gradestepsdsc;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}

	@Override
	public String toString() {
		return "SalaryBasisDetails [actionid=" + actionid + ", salarybasisid=" + salarybasisid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", frequency=" + frequency + ", salarytype="
				+ salarytype + ", frequencydsc=" + frequencydsc + ", salarytypedsc=" + salarytypedsc + ", rateofpay="
				+ rateofpay + ", effectivestartdate=" + effectivestartdate + ", effectiveenddate=" + effectiveenddate
				+ ", grade=" + grade + ", gradesteps=" + gradesteps + ", gradedsc=" + gradedsc + ", gradestepsdsc="
				+ gradestepsdsc + ", createdby=" + createdby + ", updatedby=" + updatedby + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getSalarybasisid() {
		return salarybasisid;
	}

	public void setSalarybasisid(long salarybasisid) {
		this.salarybasisid = salarybasisid;
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

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public long getSalarytype() {
		return salarytype;
	}

	public void setSalarytype(long salarytype) {
		this.salarytype = salarytype;
	}

	public String getFrequencydsc() {
		return frequencydsc;
	}

	public void setFrequencydsc(String frequencydsc) {
		this.frequencydsc = frequencydsc;
	}

	public String getSalarytypedsc() {
		return salarytypedsc;
	}

	public void setSalarytypedsc(String salarytypedsc) {
		this.salarytypedsc = salarytypedsc;
	}

	public double getRateofPay() {
		return rateofpay;
	}

	public void setRateofpay(double rateofpay) {
		this.rateofpay = rateofpay;
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

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public long getGradesteps() {
		return gradesteps;
	}

	public void setGradesteps(long gradesteps) {
		this.gradesteps = gradesteps;
	}

	public String getGradedsc() {
		return gradedsc;
	}

	public void setGradedsc(String gradedsc) {
		this.gradedsc = gradedsc;
	}

	public String getGradestepsdsc() {
		return gradestepsdsc;
	}

	public void setGradestepsdsc(String gradestepsdsc) {
		this.gradestepsdsc = gradestepsdsc;
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
	
	


}
