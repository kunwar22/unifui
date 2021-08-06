package in.co.srdt.unif.model.search;

public class JobSearchResult {

	private Long actionid;

	private Long jobsid;

	private String name;

	private String code;

	private String dataset;

	private String effectstartdate;

	private String jobfunction;

	private String status;

	public JobSearchResult() {
		super();
	}

	public JobSearchResult(Long actionid, Long jobsid, String name, String code, String dataset, String effectstartdate,
			String jobfunction, String status) {
		super();
		this.actionid = actionid;
		this.jobsid = jobsid;
		this.name = name;
		this.code = code;
		this.dataset = dataset;
		this.effectstartdate = effectstartdate;
		this.jobfunction = jobfunction;
		this.status = status;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public Long getJobsid() {
		return jobsid;
	}

	public void setJobsid(Long jobsid) {
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

	public String getJobfunction() {
		return jobfunction;
	}

	public void setJobfunction(String jobfunction) {
		this.jobfunction = jobfunction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
