package in.co.srdt.unif.model.employmentmanagement;

import in.co.srdt.unif.model.newperson.BankDetails;

import java.util.List;

public class EmployementInfoManageMaster {

	//private Long actionid;

	private String personid;

	private String personnumber;

	private JobData jobdata;

	private List<ManagerDetails> managerdetails;

	private LmrcAdditionalDetails upmrcaddon;

	private SalaryBasisDetails salarybasis;

	private List<BankDetails> bankdetails;

	public EmployementInfoManageMaster() {
		super();
	}

	public EmployementInfoManageMaster(String personid, String personnumber, JobData jobdata, List<ManagerDetails> managerdetails, LmrcAdditionalDetails upmrcaddon, SalaryBasisDetails salarybasis, List<BankDetails> bankdetails) {
		this.personid = personid;
		this.personnumber = personnumber;
		this.jobdata = jobdata;
		this.managerdetails = managerdetails;
		this.upmrcaddon = upmrcaddon;
		this.salarybasis = salarybasis;
		this.bankdetails = bankdetails;
	}

	@Override
	public String toString() {
		return "EmployementInfoManageMaster{" +
				"personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", jobdata=" + jobdata +
				", managerdetails=" + managerdetails +
				", upmrcaddon=" + upmrcaddon +
				", salarybasis=" + salarybasis +
				", bankdetails=" + bankdetails +
				'}';
	}

	public List<BankDetails> getBankdetails() {
		return bankdetails;
	}

	public void setBankdetails(List<BankDetails> bankdetails) {
		this.bankdetails = bankdetails;
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

	public JobData getJobdata() {
		return jobdata;
	}

	public void setJobdata(JobData jobdata) {
		this.jobdata = jobdata;
	}

	public List<ManagerDetails> getManagerdetails() {
		return managerdetails;
	}

	public void setManagerdetails(List<ManagerDetails> managerdetails) {
		this.managerdetails = managerdetails;
	}

	public LmrcAdditionalDetails getUpmrcaddon() {
		return upmrcaddon;
	}

	public void setUpmrcaddon(LmrcAdditionalDetails upmrcaddon) {
		this.upmrcaddon = upmrcaddon;
	}

	public SalaryBasisDetails getSalarybasis() {
		return salarybasis;
	}

	public void setSalarybasis(SalaryBasisDetails salarybasis) {
		this.salarybasis = salarybasis;
	}

	
}
