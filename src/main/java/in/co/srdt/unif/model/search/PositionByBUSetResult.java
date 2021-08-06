package in.co.srdt.unif.model.search;

public class PositionByBUSetResult {
	
	private long actionid;
	private long positionid;
	private String name;
	private String code;
	private String effectstartdate;
	private String businessunitid;
	private String status;
	
	public PositionByBUSetResult() {
		
	}

	public PositionByBUSetResult(long actionid, long positionid, String name, String code, String effectstartdate,
			String businessunitid, String status) {
		super();
		this.actionid = actionid;
		this.positionid = positionid;
		this.name = name;
		this.code = code;
		this.effectstartdate = effectstartdate;
		this.businessunitid = businessunitid;
		this.status = status;
	}

	@Override
	public String toString() {
		return "PositionByBUSetResult [actionid=" + actionid + ", positionid=" + positionid + ", name=" + name
				+ ", code=" + code + ", effectstartdate=" + effectstartdate + ", businessunitid=" + businessunitid
				+ ", status=" + status + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getPositionid() {
		return positionid;
	}

	public void setPositionid(long positionid) {
		this.positionid = positionid;
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

	public String getBusinessunitid() {
		return businessunitid;
	}

	public void setBusinessunitid(String businessunitid) {
		this.businessunitid = businessunitid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
}
