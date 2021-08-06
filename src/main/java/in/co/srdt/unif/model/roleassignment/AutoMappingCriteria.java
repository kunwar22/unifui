package in.co.srdt.unif.model.roleassignment;

import javax.validation.constraints.NotBlank;

public class AutoMappingCriteria {

	
	private long criteriaid;

	@NotBlank(message = "Please Select Criteria")
	private String criterianame;

	private String criteriavalueid;

	@NotBlank(message = "Please Select Criteria Value")
	private String criteriavaluename;
	
	private String criteriacolumnname;

	public AutoMappingCriteria() {
		super();
	}	

	public AutoMappingCriteria(long criteriaid, @NotBlank(message = "Please Select Criteria") String criterianame,
			String criteriavalueid, @NotBlank(message = "Please Select Criteria Value") String criteriavaluename,
			String criteriacolumnname) {
		super();
		this.criteriaid = criteriaid;
		this.criterianame = criterianame;
		this.criteriavalueid = criteriavalueid;
		this.criteriavaluename = criteriavaluename;
		this.criteriacolumnname = criteriacolumnname;
	}



	public long getCriteriaid() {
		return criteriaid;
	}

	public void setCriteriaid(long criteriaid) {
		this.criteriaid = criteriaid;
	}

	public String getCriterianame() {
		return criterianame;
	}

	public void setCriterianame(String criterianame) {
		this.criterianame = criterianame;
	}

	public String getCriteriavalueid() {
		return criteriavalueid;
	}

	public void setCriteriavalueid(String criteriavalueid) {
		this.criteriavalueid = criteriavalueid;
	}

	public String getCriteriavaluename() {
		return criteriavaluename;
	}

	public void setCriteriavaluename(String criteriavaluename) {
		this.criteriavaluename = criteriavaluename;
	}

	public String getCriteriacolumnname() {
		return criteriacolumnname;
	}

	public void setCriteriacolumnname(String criteriacolumnname) {
		this.criteriacolumnname = criteriacolumnname;
	}

	@Override
	public String toString() {
		return "AutoMappingCriteria [criteriaid=" + criteriaid + ", criterianame=" + criterianame + ", criteriavalueid="
				+ criteriavalueid + ", criteriavaluename=" + criteriavaluename + "]";
	}
	
	
}
