package in.co.srdt.unif.model.newperson;


public class ManagerDetails {

	private long actionid;

	private long managertypeid;

	private String personid;

	private String personnumber;

	private long managertype;

	private String supervisorid;

	private String supervisorname;
	
	private String effectiveenddate="4712-12-31";

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getSupervisorname() {
		return supervisorname;
	}

	public void setSupervisorname(String supervisorname) {
		this.supervisorname = supervisorname;
	}

	public String getSupervisorid() {
		return supervisorid;
	}

	public void setSupervisorid(String supervisorid) {
		this.supervisorid = supervisorid;
	}

	public ManagerDetails() {
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

	

	public ManagerDetails(long actionid, long managertypeid, String personid, String personnumber, long managertype,
			String supervisorid, String supervisorname, String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.managertypeid = managertypeid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.managertype = managertype;
		this.supervisorid = supervisorid;
		this.supervisorname = supervisorname;
		this.effectiveenddate = effectiveenddate;
	}

	@Override
	public String toString() {
		return "ManagerDetails [actionid=" + actionid + ", managertypeid=" + managertypeid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", managertype=" + managertype + ", supervisorid=" + supervisorid
				+ ", supervisorname=" + supervisorname + ", effectiveenddate=" + effectiveenddate + "]";
	}

}
