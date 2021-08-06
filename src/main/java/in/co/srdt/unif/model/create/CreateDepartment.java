package in.co.srdt.unif.model.create;


public class CreateDepartment {
	
	private long actionid;

	private long departmentsid;

	private String name;

	private String code;

	private long dataset;

	private String effectstartdate;

	private String status;
	
	private String datasetname;
	
	private String additionalatr;
	
	public CreateDepartment() {
		super();
	}

	public CreateDepartment(long actionid, long departmentsid, String name, String code, long dataset,
			String effectstartdate, String status, String datasetname, String additionalatr) {
		super();
		this.actionid = actionid;
		this.departmentsid = departmentsid;
		this.name = name;
		this.code = code;
		this.dataset = dataset;
		this.effectstartdate = effectstartdate;
		this.status = status;
		this.datasetname = datasetname;
		this.additionalatr = additionalatr;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getDepartmentsid() {
		return departmentsid;
	}

	public void setDepartmentsid(long departmentsid) {
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

	public long getDataset() {
		return dataset != 0 ? dataset:0;
	}

	public void setDataset(long dataset) {
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

	public String getDatasetname() {
		return datasetname;
	}

	public void setDatasetname(String datasetname) {
		this.datasetname = datasetname;
	}
	
	public String getAddtionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}
	
	
}
