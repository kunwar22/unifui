package in.co.srdt.unif.model.search;

public class SalaryBasesSearchResult {
	
	private long actionid;

	private long salarybasesid;

	private String name;

	private String code;

	private String graderateid;

	private String status;

	
	public SalaryBasesSearchResult() {
	}


	public SalaryBasesSearchResult(long actionid, long salarybasesid, String name, String code, String graderateid,
			String status) {
		super();
		this.actionid = actionid;
		this.salarybasesid = salarybasesid;
		this.name = name;
		this.code = code;
		this.graderateid = graderateid;
		this.status = status;
	}


	@Override
	public String toString() {
		return "SalaryBasesSearchResult [actionid=" + actionid + ", salarybasesid=" + salarybasesid + ", name=" + name
				+ ", code=" + code + ", graderateid=" + graderateid + ", status=" + status + "]";
	}


	public long getActionid() {
		return actionid;
	}


	public void setActionid(long actionid) {
		this.actionid = actionid;
	}


	public long getSalarybasesid() {
		return salarybasesid;
	}


	public void setSalarybasesid(long salarybasesid) {
		this.salarybasesid = salarybasesid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getGraderateid() {
		return graderateid;
	}


	public void setGraderateid(String graderateid) {
		this.graderateid = graderateid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
