package in.co.srdt.unif.model.search;

public class GradeSearchResult {
	
	private long actionid;

	private long gradesid;

	private String datasets;

	private String name;

	private String code;

	private String effectstartdate;
	
	private String status;
	
	public GradeSearchResult() {
	}

	public GradeSearchResult(long actionid, long gradesid, String datasets, String name, String code,
			String effectstartdate, String status) {
		super();
		this.actionid = actionid;
		this.gradesid = gradesid;
		this.datasets = datasets;
		this.name = name;
		this.code = code;
		this.effectstartdate = effectstartdate;
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return "GradeSearchResult [actionid=" + actionid + ", gradesid=" + gradesid + ", datasets=" + datasets
				+ ", name=" + name + ", code=" + code + ", effectstartdate=" + effectstartdate + ", status=" + status
				+ "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getGradesid() {
		return gradesid;
	}

	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
	}

	public String getDatasets() {
		return datasets;
	}

	public void setDatasets(String datasets) {
		this.datasets = datasets;
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
