package in.co.srdt.unif.model.search;

public class BUsearchresult {

	private Long actionid;

	private Long businessunitid;

	private String name;

	private String code;

	private String status;

	private String dataset;
	
	private long datasetid;

	public BUsearchresult() {
	}

	public BUsearchresult(Long actionid, Long businessunitid, String name, String code, String status, String dataset,
			long datasetid) {
		super();
		this.actionid = actionid;
		this.businessunitid = businessunitid;
		this.name = name;
		this.code = code;
		this.status = status;
		this.dataset = dataset;
		this.datasetid = datasetid;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public Long getBusinessunitid() {
		return businessunitid;
	}

	public void setBusinessunitid(Long businessunitid) {
		this.businessunitid = businessunitid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public long getDatasetid() {
		return datasetid;
	}

	public void setDatasetid(long datasetid) {
		this.datasetid = datasetid;
	}

	

}
