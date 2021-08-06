package in.co.srdt.unif.model.user.corehr;

import java.util.List;

import in.co.srdt.unif.model.employmentmanagement.ManagerDetails;

public class EmploymentInfo {
	private long personaltblactionid; // use on update time

	private String personid;

	private String personnumber;

	private String assignamentnumber;

	private long jobdataactionid;// use on update time

	private String jobdatatid; // use in update time

	private String positionnumber; //have to delete

	private String positionname;
	
	private long legalentityid;

	private String legalentitycode;

	private long bussinessunitid;

	private String bussinessunitcode;

	private long departmentsid;

	private String departmentscode;

	private long locationid;

	private String locationcode;

	private long jobid;

	private String jobcode;

	private long reporttoid; /// manager id (Not confirm  have to confirm with ravi sir)

	private String reporttoname; /// manager name (Not confirm  have to confirm with ravi sir)

	private long superviorsid; /// manager id (Not confirm  have to confirm with ravi sir)

	private String superviorsname;/// manager name (Not confirm  have to confirm with ravi sir)

	private String marriagestatuschangedate;

	private String noticeperiod;

	private long noticeperiodtypeid;

	private String noticeperiodtypename;
	
	// following new fields added on 28/07/2020
	
	private String legalentityname;

	private String bussinessunitname;

	private String departmentsname;
	
	private String worklocationname;
	
	private String baselocationname;

	private String jobsname;
	
	private List<ManagerDetails> managerdetails;

	public EmploymentInfo() {
		
	}

	public EmploymentInfo(long personaltblactionid, String personid, String personnumber, String assignamentnumber,
			long jobdataactionid, String jobdatatid, String positionnumber, long legalentityid, String legalentitycode,
			long bussinessunitid, String bussinessunitcode, long departmentsid, String departmentscode, long locationid,
			String locationcode, long jobid, String jobcode, long reporttoid, String reporttoname, long superviorsid,
			String superviorsname, String marriagestatuschangedate, String noticeperiod, long noticeperiodtypeid,
			String noticeperiodtypename, String legalentityname, String bussinessunitname, String departmentsname,
			String locationname, String jobsname,String positionname, List<ManagerDetails> managerdetails, String baselocationname, String worklocationname) {
		super();
		this.personaltblactionid = personaltblactionid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.assignamentnumber = assignamentnumber;
		this.jobdataactionid = jobdataactionid;
		this.jobdatatid = jobdatatid;
		this.positionnumber = positionnumber;
		this.legalentityid = legalentityid;
		this.legalentitycode = legalentitycode;
		this.bussinessunitid = bussinessunitid;
		this.bussinessunitcode = bussinessunitcode;
		this.departmentsid = departmentsid;
		this.departmentscode = departmentscode;
		this.locationid = locationid;
		this.locationcode = locationcode;
		this.jobid = jobid;
		this.jobcode = jobcode;
		this.reporttoid = reporttoid;
		this.reporttoname = reporttoname;
		this.superviorsid = superviorsid;
		this.superviorsname = superviorsname;
		this.marriagestatuschangedate = marriagestatuschangedate;
		this.noticeperiod = noticeperiod;
		this.noticeperiodtypeid = noticeperiodtypeid;
		this.noticeperiodtypename = noticeperiodtypename;
		this.legalentityname = legalentityname;
		this.bussinessunitname = bussinessunitname;
		this.departmentsname = departmentsname;
		this.jobsname = jobsname;
		this.managerdetails = managerdetails;
		this.positionname=positionname;
		this.baselocationname=baselocationname;
		this.worklocationname=worklocationname;
	}

	
	
	public String getWorklocationname() {
		return worklocationname;
	}

	public void setWorklocationname(String worklocationname) {
		this.worklocationname = worklocationname;
	}

	public String getBaselocationname() {
		return baselocationname;
	}

	public void setBaselocationname(String baselocationname) {
		this.baselocationname = baselocationname;
	}

	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public long getPersonaltblactionid() {
		return personaltblactionid;
	}

	public void setPersonaltblactionid(long personaltblactionid) {
		this.personaltblactionid = personaltblactionid;
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

	public String getAssignamentnumber() {
		return assignamentnumber;
	}

	public void setAssignamentnumber(String assignamentnumber) {
		this.assignamentnumber = assignamentnumber;
	}

	public long getJobdataactionid() {
		return jobdataactionid;
	}

	public void setJobdataactionid(long jobdataactionid) {
		this.jobdataactionid = jobdataactionid;
	}

	public String getJobdatatid() {
		return jobdatatid;
	}

	public void setJobdatatid(String jobdatatid) {
		this.jobdatatid = jobdatatid;
	}

	public String getPositionnumber() {
		return positionnumber;
	}

	public void setPositionnumber(String positionnumber) {
		this.positionnumber = positionnumber;
	}

	public long getLegalentityid() {
		return legalentityid;
	}

	public void setLegalentityid(long legalentityid) {
		this.legalentityid = legalentityid;
	}

	public String getLegalentitycode() {
		return legalentitycode;
	}

	public void setLegalentitycode(String legalentitycode) {
		this.legalentitycode = legalentitycode;
	}

	public long getBussinessunitid() {
		return bussinessunitid;
	}

	public void setBussinessunitid(long bussinessunitid) {
		this.bussinessunitid = bussinessunitid;
	}

	public String getBussinessunitcode() {
		return bussinessunitcode;
	}

	public void setBussinessunitcode(String bussinessunitcode) {
		this.bussinessunitcode = bussinessunitcode;
	}

	public long getDepartmentsid() {
		return departmentsid;
	}

	public void setDepartmentsid(long departmentsid) {
		this.departmentsid = departmentsid;
	}

	public String getDepartmentscode() {
		return departmentscode;
	}

	public void setDepartmentscode(String departmentscode) {
		this.departmentscode = departmentscode;
	}

	public long getLocationid() {
		return locationid;
	}

	public void setLocationid(long locationid) {
		this.locationid = locationid;
	}

	public String getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public long getJobid() {
		return jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public String getJobcode() {
		return jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public long getReporttoid() {
		return reporttoid;
	}

	public void setReporttoid(long reporttoid) {
		this.reporttoid = reporttoid;
	}

	public String getReporttoname() {
		return reporttoname;
	}

	public void setReporttoname(String reporttoname) {
		this.reporttoname = reporttoname;
	}

	public long getSuperviorsid() {
		return superviorsid;
	}

	public void setSuperviorsid(long superviorsid) {
		this.superviorsid = superviorsid;
	}

	public String getSuperviorsname() {
		return superviorsname;
	}

	public void setSuperviorsname(String superviorsname) {
		this.superviorsname = superviorsname;
	}

	public String getMarriagestatuschangedate() {
		return marriagestatuschangedate;
	}

	public void setMarriagestatuschangedate(String marriagestatuschangedate) {
		this.marriagestatuschangedate = marriagestatuschangedate;
	}

	public String getNoticeperiod() {
		return noticeperiod;
	}

	public void setNoticeperiod(String noticeperiod) {
		this.noticeperiod = noticeperiod;
	}

	public long getNoticeperiodtypeid() {
		return noticeperiodtypeid;
	}

	public void setNoticeperiodtypeid(long noticeperiodtypeid) {
		this.noticeperiodtypeid = noticeperiodtypeid;
	}

	public String getNoticeperiodtypename() {
		return noticeperiodtypename;
	}

	public void setNoticeperiodtypename(String noticeperiodtypename) {
		this.noticeperiodtypename = noticeperiodtypename;
	}

	public String getLegalentityname() {
		return legalentityname;
	}

	public void setLegalentityname(String legalentityname) {
		this.legalentityname = legalentityname;
	}

	public String getBussinessunitname() {
		return bussinessunitname;
	}

	public void setBussinessunitname(String bussinessunitname) {
		this.bussinessunitname = bussinessunitname;
	}

	public String getDepartmentsname() {
		return departmentsname;
	}

	public void setDepartmentsname(String departmentsname) {
		this.departmentsname = departmentsname;
	}

	

	public String getJobsname() {
		return jobsname;
	}

	public void setJobsname(String jobsname) {
		this.jobsname = jobsname;
	}

	public List<ManagerDetails> getManagerdetails() {
		return managerdetails;
	}

	public void setManagerdetails(List<ManagerDetails> managerdetails) {
		this.managerdetails = managerdetails;
	}

	@Override
	public String toString() {
		return "EmploymentInfo [personaltblactionid=" + personaltblactionid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", assignamentnumber=" + assignamentnumber + ", jobdataactionid="
				+ jobdataactionid + ", jobdatatid=" + jobdatatid + ", positionnumber=" + positionnumber
				+ ", positionname=" + positionname + ", legalentityid=" + legalentityid + ", legalentitycode="
				+ legalentitycode + ", bussinessunitid=" + bussinessunitid + ", bussinessunitcode=" + bussinessunitcode
				+ ", departmentsid=" + departmentsid + ", departmentscode=" + departmentscode + ", locationid="
				+ locationid + ", locationcode=" + locationcode + ", jobid=" + jobid + ", jobcode=" + jobcode
				+ ", reporttoid=" + reporttoid + ", reporttoname=" + reporttoname + ", superviorsid=" + superviorsid
				+ ", superviorsname=" + superviorsname + ", marriagestatuschangedate=" + marriagestatuschangedate
				+ ", noticeperiod=" + noticeperiod + ", noticeperiodtypeid=" + noticeperiodtypeid
				+ ", noticeperiodtypename=" + noticeperiodtypename + ", legalentityname=" + legalentityname
				+ ", bussinessunitname=" + bussinessunitname + ", departmentsname=" + departmentsname
				+ ", worklocationname=" + worklocationname + ", baselocationname=" + baselocationname + ", jobsname="
				+ jobsname + ", managerdetails=" + managerdetails + "]";
	}


	
	
}
