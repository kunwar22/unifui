package in.co.srdt.unif.model.search;

public class JobByBUSetResult {
	
	
	private long actionid;
	private long jobsid;
	private String name;
	private String code;
	private String dataset;
	private String effectstartdate;
	private String status;
	
	public JobByBUSetResult() {
		
	}

	public JobByBUSetResult(long actionid, long jobsid, String name, String code, String dataset,
			String effectstartdate, String status) {
		super();
		this.actionid = actionid;
		this.jobsid = jobsid;
		this.name = name;
		this.code = code;
		this.dataset = dataset;
		this.effectstartdate = effectstartdate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobByBUSetResult [actionid=" + actionid + ", jobsid=" + jobsid + ", name=" + name + ", code=" + code
				+ ", dataset=" + dataset + ", effectstartdate=" + effectstartdate + ", status=" + status + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getJobsid() {
		return jobsid;
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
