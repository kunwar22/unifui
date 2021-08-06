package in.co.srdt.unif.model.employmentmanagement;


public class ManagerDetails {

	private long actionid;

	private long managertypeid;

	private String personid;

	private String personnumber;

	private long managertype;
	
	private String managertypedsc;

	private String supervisorid;
	
	private String managertypename;
	
	private String supervisorname;
	
	private String supervisoname;
	

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public ManagerDetails() {
		super();
	}

	public ManagerDetails(long actionid, long managertypeid, String personid, String personnumber, long managertype,
			String managertypedsc, String supervisorid, String effectivestartdate, String effectiveenddate,
			String createdby, String updatedby, String supervisorname, String managertypename, String supervisoname) {
		super();
		this.actionid = actionid;
		this.managertypeid = managertypeid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.managertype = managertype;
		this.managertypedsc = managertypedsc;
		this.supervisorid = supervisorid;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.supervisorname=supervisorname;
		this.managertypename=managertypename;
		this.supervisoname=supervisoname;
	}

	

	
	

	
	

	@Override
	public String toString() {
		return "ManagerDetails [actionid=" + actionid + ", managertypeid=" + managertypeid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", managertype=" + managertype + ", managertypedsc="
				+ managertypedsc + ", supervisorid=" + supervisorid + ", managertypename=" + managertypename
				+ ", supervisorname=" + supervisorname + ", supervisoname=" + supervisoname + ", effectivestartdate="
				+ effectivestartdate + ", effectiveenddate=" + effectiveenddate + ", createdby=" + createdby
				+ ", updatedby=" + updatedby + "]";
	}
	
	

	public String getSupervisoname() {
		return supervisoname;
	}

	public void setSupervisoname(String supervisoname) {
		this.supervisoname = supervisoname;
	}

	public String getManagertypename() {
		return managertypename;
	}

	public void setManagertypename(String managertypename) {
		this.managertypename = managertypename;
	}

	public String getSupervisorname() {
		return supervisorname;
	}

	public void setSupervisorname(String supervisorname) {
		this.supervisorname = supervisorname;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getManagertypeid() {
		return managertypeid;
	}

	public void setManagertypeid(long managertypeid) {
		this.managertypeid = managertypeid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public long getManagertype() {
		return managertype;
	}

	public void setManagertype(long managertype) {
		this.managertype = managertype;
	}

	public String getManagertypedsc() {
		return managertypedsc;
	}

	public void setManagertypedsc(String managertypedsc) {
		this.managertypedsc = managertypedsc;
	}

	public String getSupervisorid() {
		return supervisorid;
	}

	public void setSupervisorid(String supervisorid) {
		this.supervisorid = supervisorid;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	

}
