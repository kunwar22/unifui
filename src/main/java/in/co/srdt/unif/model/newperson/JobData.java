package in.co.srdt.unif.model.newperson;

//import java.util.Date;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class JobData {

	private long actionid;

	private long jobdatatid;

	private String personid;

	private String personnumber;

	private Integer assignamentid;

	private String assignamentnumber;
	
	private double gradpay;

	@Min(value = 1, message = "Please select HR Status.")
	private long hrstatus;

	@NotBlank( message = "Please select Payroll Status.")
	private String payrollstatus;

	private long hireaction;

	private long hirereason;

	private long worktype;

	private String hiredate; // new add on 08-07

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31"; // new add on 10/07

	
	// private String payrollstatus; 
	
	// Position Setup Start
	@Min(value = 1, message = "Please select Position.")
	private long positionnumber;

	private long legalentity;

	@Min(value = 1, message = "Please select Business Unit.")
	private long bussinessunit;

	@Min(value = 1, message = "Please select Department.")
	private long departments;

	@Min(value = 1, message = "Please select Work Location.")
	private long worklocation;

	private long baselocation;

	@Min(value = 1, message = "Please select Job.")
	private long jobcode;


	private String fte;

	private Float workinghours;


	//private long managername; // report to

	//private long managertype;

	@NotBlank(message = "Please select Working as a Manager.")
	private String workingasamanager;
	// private long superviorsid;// 

	// private Date projectendlong;

	private int headcount;

	//private long payrollname;

	// private long holidayschedule; // have doubt in this

	// Position Setup Start

	private long salarybases;// don't use



	private long ratecodevalue; // don't use

	// Position Setup End

	// private String amount;

	private long regulartemporary;

	//// new added from here ******************

	private long assignmentcategory;

	private long fulltimeparttime;

	private long workcategory;

	private long probationperiod;

	private String probationuom;

	private long noticeperiodtype;

	private String noticeperiod;

	private String probationenddate;

	@NotBlank(message = "Please select Paygroup.")
	private String payrollgroupdsc;

	@Min(value = 1, message = "Please select Paygroup.")
	private long payrollgroupid;
	
	private String createdby;

	private String updatedby;

	public JobData() {
			}

	public JobData(long actionid, long jobdatatid, String personid, String personnumber, Integer assignamentid, String assignamentnumber, double gradpay, @Min(value = 1, message = "Please select HR Status.") long hrstatus, @NotBlank(message = "Please select Payroll Status.") String payrollstatus, long hireaction, long hirereason, long worktype, String hiredate, String effectivestartdate, String effectiveenddate, @Min(value = 1, message = "Please select Position.") long positionnumber, long legalentity, @Min(value = 1, message = "Please select Business Unit.") long bussinessunit, @Min(value = 1, message = "Please select Department.") long departments, long worklocation, long baselocation, @Min(value = 1, message = "Please select Job.") long jobcode, String fte, Float workinghours, @NotBlank(message = "Please select Working as a Manager.") String workingasamanager, int headcount, long salarybases, long ratecodevalue, long regulartemporary, long assignmentcategory, long fulltimeparttime, long workcategory, long probationperiod, String probationuom, long noticeperiodtype, String noticeperiod, String probationenddate, @NotBlank(message = "Please select Paygroup.") String payrollgroupdsc, @Min(value = 1, message = "Please select Paygroup.") long payrollgroupid, String createdby, String updatedby) {
		this.actionid = actionid;
		this.jobdatatid = jobdatatid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.assignamentid = assignamentid;
		this.assignamentnumber = assignamentnumber;
		this.gradpay = gradpay;
		this.hrstatus = hrstatus;
		this.payrollstatus = payrollstatus;
		this.hireaction = hireaction;
		this.hirereason = hirereason;
		this.worktype = worktype;
		this.hiredate = hiredate;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.positionnumber = positionnumber;
		this.legalentity = legalentity;
		this.bussinessunit = bussinessunit;
		this.departments = departments;
		this.worklocation = worklocation;
		this.baselocation = baselocation;
		this.jobcode = jobcode;
		this.fte = fte;
		this.workinghours = workinghours;
		this.workingasamanager = workingasamanager;
		this.headcount = headcount;
		this.salarybases = salarybases;
		this.ratecodevalue = ratecodevalue;
		this.regulartemporary = regulartemporary;
		this.assignmentcategory = assignmentcategory;
		this.fulltimeparttime = fulltimeparttime;
		this.workcategory = workcategory;
		this.probationperiod = probationperiod;
		this.probationuom = probationuom;
		this.noticeperiodtype = noticeperiodtype;
		this.noticeperiod = noticeperiod;
		this.probationenddate = probationenddate;
		this.payrollgroupdsc = payrollgroupdsc;
		this.payrollgroupid = payrollgroupid;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}

	public double getGradpay() {
		return gradpay;
	}

	public void setGradpay(double gradpay) {
		this.gradpay = gradpay;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getJobdatatid() {
		return jobdatatid;
	}

	public void setJobdatatid(long jobdatatid) {
		this.jobdatatid = jobdatatid;
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

	public Integer getAssignamentid() {
		return assignamentid;
	}

	public void setAssignamentid(Integer assignamentid) {
		this.assignamentid = assignamentid;
	}

	public String getAssignamentnumber() {
		return assignamentnumber;
	}

	public void setAssignamentnumber(String assignamentnumber) {
		this.assignamentnumber = assignamentnumber;
	}

	public long getHrstatus() {
		return hrstatus;
	}

	public void setHrstatus(long hrstatus) {
		this.hrstatus = hrstatus;
	}

	public String getPayrollstatus() {
		return payrollstatus;
	}

	public void setPayrollstatus(String payrollstatus) {
		this.payrollstatus = payrollstatus;
	}

	public long getHireaction() {
		return hireaction;
	}

	public void setHireaction(long hireaction) {
		this.hireaction = hireaction;
	}

	public long getHirereason() {
		return hirereason;
	}

	public void setHirereason(long hirereason) {
		this.hirereason = hirereason;
	}

	public long getWorktype() {
		return worktype;
	}

	public void setWorktype(long worktype) {
		this.worktype = worktype;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
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

	public long getPositionnumber() {
		return positionnumber;
	}

	public void setPositionnumber(long positionnumber) {
		this.positionnumber = positionnumber;
	}

	public long getLegalentity() {
		return legalentity;
	}

	public void setLegalentity(long legalentity) {
		this.legalentity = legalentity;
	}

	public long getBussinessunit() {
		return bussinessunit;
	}

	public void setBussinessunit(long bussinessunit) {
		this.bussinessunit = bussinessunit;
	}

	public long getDepartments() {
		return departments;
	}

	public void setDepartments(long departments) {
		this.departments = departments;
	}

	public long getWorklocation() {
		return worklocation;
	}

	public void setWorklocation(long worklocation) {
		this.worklocation = worklocation;
	}

	public long getBaselocation() {
		return baselocation;
	}

	public void setBaselocation(long baselocation) {
		this.baselocation = baselocation;
	}

	public long getJobcode() {
		return jobcode;
	}

	public void setJobcode(long jobcode) {
		this.jobcode = jobcode;
	}

	public String getFte() {
		return fte;
	}

	public void setFte(String fte) {
		this.fte = fte;
	}

	public Float getWorkinghours() {
		return workinghours;
	}

	public void setWorkinghours(Float workinghours) {
		this.workinghours = workinghours;
	}

	public String getWorkingasamanager() {
		return workingasamanager;
	}

	public void setWorkingasamanager(String workingasamanager) {
		this.workingasamanager = workingasamanager;
	}

	public int getHeadcount() {
		return headcount;
	}

	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}

	public long getSalarybases() {
		return salarybases;
	}

	public void setSalarybases(long salarybases) {
		this.salarybases = salarybases;
	}

	public long getRatecodevalue() {
		return ratecodevalue;
	}

	public void setRatecodevalue(long ratecodevalue) {
		this.ratecodevalue = ratecodevalue;
	}

	public long getRegulartemporary() {
		return regulartemporary;
	}

	public void setRegulartemporary(long regulartemporary) {
		this.regulartemporary = regulartemporary;
	}

	public long getAssignmentcategory() {
		return assignmentcategory;
	}

	public void setAssignmentcategory(long assignmentcategory) {
		this.assignmentcategory = assignmentcategory;
	}

	public long getFulltimeparttime() {
		return fulltimeparttime;
	}

	public void setFulltimeparttime(long fulltimeparttime) {
		this.fulltimeparttime = fulltimeparttime;
	}

	public long getWorkcategory() {
		return workcategory;
	}

	public void setWorkcategory(long workcategory) {
		this.workcategory = workcategory;
	}

	public long getProbationperiod() {
		return probationperiod;
	}

	public void setProbationperiod(long probationperiod) {
		this.probationperiod = probationperiod;
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

	public String getNoticeperiod() {
		return noticeperiod;
	}

	public void setNoticeperiod(String noticeperiod) {
		this.noticeperiod = noticeperiod;
	}

	public String getProbationenddate() {
		return probationenddate;
	}

	public void setProbationenddate(String probationenddate) {
		this.probationenddate = probationenddate;
	}

	public String getPayrollgroupdsc() {
		return payrollgroupdsc;
	}

	public void setPayrollgroupdsc(String payrollgroupdsc) {
		this.payrollgroupdsc = payrollgroupdsc;
	}

	public long getPayrollgroupid() {
		return payrollgroupid;
	}

	public void setPayrollgroupid(long payrollgroupid) {
		this.payrollgroupid = payrollgroupid;
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

	@Override
	public String toString() {
		return "JobData [actionid=" + actionid + ", jobdatatid=" + jobdatatid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", assignamentid=" + assignamentid + ", assignamentnumber="
				+ assignamentnumber + ", gradpay=" + gradpay + ", hrstatus=" + hrstatus + ", payrollstatus="
				+ payrollstatus + ", hireaction=" + hireaction + ", hirereason=" + hirereason + ", worktype=" + worktype
				+ ", hiredate=" + hiredate + ", effectivestartdate=" + effectivestartdate + ", effectiveenddate="
				+ effectiveenddate + ", positionnumber=" + positionnumber + ", legalentity=" + legalentity
				+ ", bussinessunit=" + bussinessunit + ", departments=" + departments + ", worklocation=" + worklocation
				+ ", baselocation=" + baselocation + ", jobcode=" + jobcode + ", fte=" + fte + ", workinghours="
				+ workinghours + ", workingasamanager=" + workingasamanager + ", headcount=" + headcount
				+ ", salarybases=" + salarybases + ", ratecodevalue=" + ratecodevalue + ", regulartemporary="
				+ regulartemporary + ", assignmentcategory=" + assignmentcategory + ", fulltimeparttime="
				+ fulltimeparttime + ", workcategory=" + workcategory + ", probationperiod=" + probationperiod
				+ ", probationuom=" + probationuom + ", noticeperiodtype=" + noticeperiodtype + ", noticeperiod="
				+ noticeperiod + ", probationenddate=" + probationenddate + ", payrollgroupdsc=" + payrollgroupdsc
				+ ", payrollgroupid=" + payrollgroupid + ", createdby=" + createdby + ", updatedby=" + updatedby + "]";
	}

	

	
}
