package in.co.srdt.unif.model.create;

public class BussinessUnit {

	private long actionid;

	private long businessunitid;

	private String name;

	private String code;

	private String statusbu;

	private long dataset;
	
	private String datasetname;
	private String effectstartdate;
	private String additionalatr;

	public BussinessUnit() {
		super();
	}

	
	public BussinessUnit(String effectstartdate) {
		super();
		this.effectstartdate = effectstartdate;
	}


	public BussinessUnit(long actionid, long businessunitid, String name, String code, String statusbu, long dataset,
			String datasetname,String additionalatr) {
		super();
		this.actionid = actionid;
		this.businessunitid = businessunitid;
		this.name = name;
		this.code = code;
		this.statusbu = statusbu;
		this.dataset = dataset;
		this.datasetname = datasetname;
		this.additionalatr=additionalatr;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getBusinessunitid() {
		return businessunitid  != 0 ? businessunitid:0;
	}

	public void setBusinessunitid(long businessunitid) {
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

	public String getStatusbu() {
		return statusbu;
	}

	public void setStatusbu(String statusbu) {
		this.statusbu = statusbu;
	}

	public long getDataset() {
		return dataset!= 0 ? dataset:0;
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

	
	public String getAddtionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}
	
	
	
}
