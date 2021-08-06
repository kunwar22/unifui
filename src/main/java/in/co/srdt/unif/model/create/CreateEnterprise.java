package in.co.srdt.unif.model.create;

public class CreateEnterprise {

	private String additionalatr;
	private String locationcode;
	private Long actionid;
	private Long enterpriseid;
	private String effectstartdate;
	private String name;
	private String status;
	private String effectenddate;
	private String location;
	private String locationname;

	public CreateEnterprise() {
	}

	public CreateEnterprise(String additionalatr, String locationcode, Long actionid, Long enterpriseid,
			String effectstartdate, String name, String status, String effectenddate, String location,
			String locationname) {
		super();
		this.additionalatr = additionalatr;
		this.locationcode = locationcode;
		this.actionid = actionid;
		this.enterpriseid = enterpriseid;
		this.effectstartdate = effectstartdate;
		this.name = name;
		this.status = status;
		this.effectenddate = effectenddate;
		this.location = location;
		this.locationname = locationname;
	}

	@Override
	public String toString() {
		return "CreateEnterprise [additionalatr=" + additionalatr + ", locationcode=" + locationcode + ", actionid="
				+ actionid + ", enterpriseid=" + enterpriseid + ", effectstartdate=" + effectstartdate + ", name="
				+ name + ", status=" + status + ", effectenddate=" + effectenddate + ", location=" + location
				+ ", locationname=" + locationname + "]";
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	public String getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public Long getEnterpriseid() {
		return enterpriseid;
	}

	public void setEnterpriseid(Long enterpriseid) {
		this.enterpriseid = enterpriseid;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffectenddate() {
		return effectenddate;
	}

	public void setEffectenddate(String effectenddate) {
		this.effectenddate = effectenddate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}


}
