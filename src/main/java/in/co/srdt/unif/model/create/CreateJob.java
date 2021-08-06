package in.co.srdt.unif.model.create;

public class CreateJob {
	
	private long actionid;
	private long jobsid;
	private String name;
	private String code;
	private long dataset;
	private String datasetname;
	private String effectstartdate;
	private long jobfunction;
	private String jobfunctionname;
	private String status;
	private String additionalatr;

	public CreateJob() {
		super();
	}

	public CreateJob(long actionid, long jobsid, String name, String code, long dataset, String datasetname,
			String effectstartdate, long jobfunction, String jobfunctionname, String status,String additionalatr) {
		super();
		this.actionid = actionid;
		this.jobsid = jobsid;
		this.name = name;
		this.code = code;
		this.dataset = dataset;
		this.datasetname = datasetname;
		this.effectstartdate = effectstartdate;
		this.jobfunction = jobfunction;
		this.jobfunctionname = jobfunctionname;
		this.status = status;
		this.additionalatr=additionalatr;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getJobsid() {
		return jobsid  != 0 ? jobsid:0;
	}

	public void setJobsid(long jobsid) {
		this.jobsid = jobsid;
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
		return dataset;
	}

	public void setDataset(long dataset) {
		this.dataset = dataset;
	}

	public String getDatasetname() {
		return datasetname;
	}

	public void setDatasetname(String datasetname) {
		this.datasetname = datasetname;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public long getJobfunction() {
		return jobfunction;
	}

	public void setJobfunction(long jobfunction) {
		this.jobfunction = jobfunction;
	}

	public String getJobfunctionname() {
		return jobfunctionname;
	}

	public void setJobfunctionname(String jobfunctionname) {
		this.jobfunctionname = jobfunctionname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getAddtionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}
	
	

}
