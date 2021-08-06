package in.co.srdt.unif.model.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Position {

	private long actionid;

	private long positionid;

	@NotBlank(message = "Name must not be empty.")
	private String name;
	
	@NotBlank(message = "Code must not be empty.")
	private String code;
	
	@NotBlank(message = "Please select Start Date.")
	private String effectstartdate;

	private String effectenddate;

	@Min(value = 1, message = "Please select the Business Unit.")
	private long businessunitid;

	private String businessunitname;

	private String businessunitcode;

	@Min(value = 1, message = "Please select the Department.")
	private long departmentsid;

	private String departmentsname;

	private String departmentscode;

	@Min(value = 1, message = "Please select the Job.")
	private long jobsid;

	private String jobsname;

	private String jobscode;

	//@Min(value = 1, message = "Please select the Location.")
	private long locationid;

	private String locationname;

	private String locationcode;

	@Min(value = 1, message = "Please select the Assignment Category.")
	private long assignmentcategory;

	private String assignmentcategoryname;

	
	private long regulartemporary;

	private String regulartemporaryname;

	
	private long fulltimeparttime;

	private String fulltimeparttimename;

	@Min(value = 1, message = "Please select Type.")
	private long type;

	private String typename;

	@Min(value = 1, message = "Please enter FTE.")
	private int fte;

	@Min(value = 1, message = "Please enter Head Count.")
	private int headcount;

	private long probationperiod;

	private String probationperiodname;

	private String probationuom;

	private long noticeperiodtype;

	private String noticeperiodtypename;

	private String noticeperiod;

	//@Min(value = 1, message = "Please select the Entry Grade.")
	private long gradesid;

	private String gradesname;

	private String gradescode;

	private long gradestepsid;

	private String gradestepsname;

	private String gradestepsno;

	private String status;

	private String additionalatr;
	
	private long businessunitsetid;

	public Position() {
	}

	public Position(long actionid, long positionid, @NotBlank(message = "Name must not be empty.") String name,
			@NotBlank(message = "Code must not be empty.") String code,
			@NotBlank(message = "Please select Start Date.") String effectstartdate, String effectenddate,
			@Min(value = 1, message = "Please select the Business Unit.") long businessunitid, String businessunitname,
			String businessunitcode, @Min(value = 1, message = "Please select the Department.") long departmentsid,
			String departmentsname, String departmentscode,
			@Min(value = 1, message = "Please select the Job.") long jobsid, String jobsname, String jobscode,
			long locationid, String locationname, String locationcode,
			@Min(value = 1, message = "Please select the Assignment Category.") long assignmentcategory,
			String assignmentcategoryname, long regulartemporary, String regulartemporaryname, long fulltimeparttime,
			String fulltimeparttimename, @Min(value = 1, message = "Please select Type.") long type, String typename,
			@Min(value = 1, message = "Please enter FTE.") int fte,
			@Min(value = 1, message = "Please enter Head Count.") int headcount, long probationperiod,
			String probationperiodname, String probationuom, long noticeperiodtype, String noticeperiodtypename,
			String noticeperiod, long gradesid, String gradesname, String gradescode, long gradestepsid,
			String gradestepsname, String gradestepsno, String status, String additionalatr, long businessunitsetid) {
		super();
		this.actionid = actionid;
		this.positionid = positionid;
		this.name = name;
		this.code = code;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.businessunitid = businessunitid;
		this.businessunitname = businessunitname;
		this.businessunitcode = businessunitcode;
		this.departmentsid = departmentsid;
		this.departmentsname = departmentsname;
		this.departmentscode = departmentscode;
		this.jobsid = jobsid;
		this.jobsname = jobsname;
		this.jobscode = jobscode;
		this.locationid = locationid;
		this.locationname = locationname;
		this.locationcode = locationcode;
		this.assignmentcategory = assignmentcategory;
		this.assignmentcategoryname = assignmentcategoryname;
		this.regulartemporary = regulartemporary;
		this.regulartemporaryname = regulartemporaryname;
		this.fulltimeparttime = fulltimeparttime;
		this.fulltimeparttimename = fulltimeparttimename;
		this.type = type;
		this.typename = typename;
		this.fte = fte;
		this.headcount = headcount;
		this.probationperiod = probationperiod;
		this.probationperiodname = probationperiodname;
		this.probationuom = probationuom;
		this.noticeperiodtype = noticeperiodtype;
		this.noticeperiodtypename = noticeperiodtypename;
		this.noticeperiod = noticeperiod;
		this.gradesid = gradesid;
		this.gradesname = gradesname;
		this.gradescode = gradescode;
		this.gradestepsid = gradestepsid;
		this.gradestepsname = gradestepsname;
		this.gradestepsno = gradestepsno;
		this.status = status;
		this.additionalatr = additionalatr;
		this.businessunitsetid = businessunitsetid;
	}

	@Override
	public String toString() {
		return "Position [actionid=" + actionid + ", positionid=" + positionid + ", name=" + name + ", code=" + code
				+ ", effectstartdate=" + effectstartdate + ", effectenddate=" + effectenddate + ", businessunitid="
				+ businessunitid + ", businessunitname=" + businessunitname + ", businessunitcode=" + businessunitcode
				+ ", departmentsid=" + departmentsid + ", departmentsname=" + departmentsname + ", departmentscode="
				+ departmentscode + ", jobsid=" + jobsid + ", jobsname=" + jobsname + ", jobscode=" + jobscode
				+ ", locationid=" + locationid + ", locationname=" + locationname + ", locationcode=" + locationcode
				+ ", assignmentcategory=" + assignmentcategory + ", assignmentcategoryname=" + assignmentcategoryname
				+ ", regulartemporary=" + regulartemporary + ", regulartemporaryname=" + regulartemporaryname
				+ ", fulltimeparttime=" + fulltimeparttime + ", fulltimeparttimename=" + fulltimeparttimename
				+ ", type=" + type + ", typename=" + typename + ", fte=" + fte + ", headcount=" + headcount
				+ ", probationperiod=" + probationperiod + ", probationperiodname=" + probationperiodname
				+ ", probationuom=" + probationuom + ", noticeperiodtype=" + noticeperiodtype
				+ ", noticeperiodtypename=" + noticeperiodtypename + ", noticeperiod=" + noticeperiod + ", gradesid="
				+ gradesid + ", gradesname=" + gradesname + ", gradescode=" + gradescode + ", gradestepsid="
				+ gradestepsid + ", gradestepsname=" + gradestepsname + ", gradestepsno=" + gradestepsno + ", status="
				+ status + ", additionalatr=" + additionalatr + ", businessunitsetid=" + businessunitsetid + "]";
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

	public String getEffectenddate() {
		return effectenddate;
	}

	public void setEffectenddate(String effectenddate) {
		this.effectenddate = effectenddate;
	}

	public long getBusinessunitid() {
		return businessunitid;
	}

	public void setBusinessunitid(long businessunitid) {
		this.businessunitid = businessunitid;
	}

	public String getBusinessunitname() {
		return businessunitname;
	}

	public void setBusinessunitname(String businessunitname) {
		this.businessunitname = businessunitname;
	}

	public String getBusinessunitcode() {
		return businessunitcode;
	}

	public void setBusinessunitcode(String businessunitcode) {
		this.businessunitcode = businessunitcode;
	}

	public long getDepartmentsid() {
		return departmentsid;
	}

	public void setDepartmentsid(long departmentsid) {
		this.departmentsid = departmentsid;
	}

	public String getDepartmentsname() {
		return departmentsname;
	}

	public void setDepartmentsname(String departmentsname) {
		this.departmentsname = departmentsname;
	}

	public String getDepartmentscode() {
		return departmentscode;
	}

	public void setDepartmentscode(String departmentscode) {
		this.departmentscode = departmentscode;
	}

	public long getJobsid() {
		return jobsid;
	}

	public void setJobsid(long jobsid) {
		this.jobsid = jobsid;
	}

	public String getJobsname() {
		return jobsname;
	}

	public void setJobsname(String jobsname) {
		this.jobsname = jobsname;
	}

	public String getJobscode() {
		return jobscode;
	}

	public void setJobscode(String jobscode) {
		this.jobscode = jobscode;
	}

	public long getLocationid() {
		return locationid;
	}

	public void setLocationid(long locationid) {
		this.locationid = locationid;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public long getAssignmentcategory() {
		return assignmentcategory;
	}

	public void setAssignmentcategory(long assignmentcategory) {
		this.assignmentcategory = assignmentcategory;
	}

	public String getAssignmentcategoryname() {
		return assignmentcategoryname;
	}

	public void setAssignmentcategoryname(String assignmentcategoryname) {
		this.assignmentcategoryname = assignmentcategoryname;
	}

	public long getRegulartemporary() {
		return regulartemporary;
	}

	public void setRegulartemporary(long regulartemporary) {
		this.regulartemporary = regulartemporary;
	}

	public String getRegulartemporaryname() {
		return regulartemporaryname;
	}

	public void setRegulartemporaryname(String regulartemporaryname) {
		this.regulartemporaryname = regulartemporaryname;
	}

	public long getFulltimeparttime() {
		return fulltimeparttime;
	}

	public void setFulltimeparttime(long fulltimeparttime) {
		this.fulltimeparttime = fulltimeparttime;
	}

	public String getFulltimeparttimename() {
		return fulltimeparttimename;
	}

	public void setFulltimeparttimename(String fulltimeparttimename) {
		this.fulltimeparttimename = fulltimeparttimename;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getFte() {
		return fte;
	}

	public void setFte(int fte) {
		this.fte = fte;
	}

	public int getHeadcount() {
		return headcount;
	}

	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}

	public long getProbationperiod() {
		return probationperiod;
	}

	public void setProbationperiod(long probationperiod) {
		this.probationperiod = probationperiod;
	}

	public String getProbationperiodname() {
		return probationperiodname;
	}

	public void setProbationperiodname(String probationperiodname) {
		this.probationperiodname = probationperiodname;
	}

	public String getProbationuom() {
		return probationuom;
	}

	public void setProbationuom(String probationuom) {
		this.probationuom = probationuom;
	}

	public long getNoticeperiodtype() {
		return noticeperiodtype;
	}

	public void setNoticeperiodtype(long noticeperiodtype) {
		this.noticeperiodtype = noticeperiodtype;
	}

	public String getNoticeperiodtypename() {
		return noticeperiodtypename;
	}

	public void setNoticeperiodtypename(String noticeperiodtypename) {
		this.noticeperiodtypename = noticeperiodtypename;
	}

	public String getNoticeperiod() {
		return noticeperiod;
	}

	public void setNoticeperiod(String noticeperiod) {
		this.noticeperiod = noticeperiod;
	}

	public long getGradesid() {
		return gradesid;
	}

	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
	}

	public String getGradesname() {
		return gradesname;
	}

	public void setGradesname(String gradesname) {
		this.gradesname = gradesname;
	}

	public String getGradescode() {
		return gradescode;
	}

	public void setGradescode(String gradescode) {
		this.gradescode = gradescode;
	}

	public long getGradestepsid() {
		return gradestepsid;
	}

	public void setGradestepsid(long gradestepsid) {
		this.gradestepsid = gradestepsid;
	}

	public String getGradestepsname() {
		return gradestepsname;
	}

	public void setGradestepsname(String gradestepsname) {
		this.gradestepsname = gradestepsname;
	}

	public String getGradestepsno() {
		return gradestepsno;
	}

	public void setGradestepsno(String gradestepsno) {
		this.gradestepsno = gradestepsno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	public long getBusinessunitsetid() {
		return businessunitsetid;
	}

	public void setBusinessunitsetid(long businessunitsetid) {
		this.businessunitsetid = businessunitsetid;
	}

	

}
