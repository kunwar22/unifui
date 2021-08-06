package in.co.srdt.unif.model.search;

public class DepartmentSearch {
	
	private Long actionid;

	private Long departmentsid;

	private String name;

	private String code;

	private String dataset;

	private String effectstartdate;

	private String status;

	public DepartmentSearch() {
		super();
	}
	
	public DepartmentSearch(Long actionid, Long departmentsid, String name, String code, String dataset,
			String effectstartdate, String status) {
		super();
		this.actionid = actionid;
		this.departmentsid = departmentsid;
		this.name = name;
		this.code = code;
		this.dataset = dataset;
		this.effectstartdate = effectstartdate;
		this.status = status;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public Long getDepartmentsid() {
		return departmentsid;
	}

	public void setDepartmentsid(Long departmentsid) {
		this.departmentsid = departmentsid;
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

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
