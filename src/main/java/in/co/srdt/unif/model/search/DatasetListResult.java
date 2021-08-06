package in.co.srdt.unif.model.search;

public class DatasetListResult {

	private long actionid;

	private long datasetsid;

	private String code;

	private String name;

	private String description;

	public DatasetListResult() {
	}

	public DatasetListResult(long actionid, long datasetsid, String code, String name, String description) {
		super();
		this.actionid = actionid;
		this.datasetsid = datasetsid;
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getDatasetsid() {
		return datasetsid;
	}

	public void setDatasetsid(long datasetsid) {
		this.datasetsid = datasetsid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
