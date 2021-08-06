package in.co.srdt.unif.model.newperson;

public class SalaryBasisDetails {

	private long actionid;

	private long salarybasisid;

	private String personid;

	private String personnumber;

	private long frequency;

	private long salarytype;

	private String rateofpay;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private long grade;

	private long gradesteps;
	
	
	

	public SalaryBasisDetails() {
		
	}




	public SalaryBasisDetails(long actionid, long salarybasisid, String personid, String personnumber, long frequency,
			long salarytype, String rateofpay, String effectivestartdate, String effectiveenddate, long grade,
			long gradesteps) {
		super();
		this.actionid = actionid;
		this.salarybasisid = salarybasisid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.frequency = frequency;
		this.salarytype = salarytype;
		this.rateofpay = rateofpay;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.grade = grade;
		this.gradesteps = gradesteps;
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




	public String getRateofpay() {
		return rateofpay;
	}




	public void setRateofpay(String rateofpay) {
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




	@Override
	public String toString() {
		return "SalaryBasisDetails [actionid=" + actionid + ", salarybasisid=" + salarybasisid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", frequency=" + frequency + ", salarytype="
				+ salarytype + ", rateofpay=" + rateofpay + ", effectivestartdate=" + effectivestartdate
				+ ", effectiveenddate=" + effectiveenddate + ", grade=" + grade + ", gradesteps=" + gradesteps + "]";
	}

}
