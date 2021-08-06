package in.co.srdt.unif.model.employmentmanagement;

public class JobData {

	private long actionid;

	private long jobdatatid;

	private String personid;

	private String personnumber;

	private long assignamentid;

	private String assignamentnumber;

	private long hrstatus;

	private String hrstatusdsc;

	private String payrollstatus;

	private long hireaction;

	private String hireactiondsc;

	private long hirereason;

	private String hirereasondsc;

	private long worktype;

	private String worktypedsc;

	private String hiredate;

	private long positionnumber;

	private long legalentity;

	private long bussinessunit;

	private long departments;

	private long worklocation;

	private long baselocation;

	private long jobcode;

	private String positionnumberdsc;

	private String legalentitydsc;

	private String bussinessunitdsc;

	private long busetid;

	private String departmentsdsc;

	private String worklocationdsc;

	private String baselocationdsc;

	private String jobcodedsc;

	private String fte;

	private Float workinghours;

	private String workingasamanager;

	private int headcount;

	private long payrollname;

	private long regulartemporary;

	private String regulartemporarydsc;

	//// new added from here ******************

	private long assignmentcategory;

	private long fulltimeparttime;

	private long probationperiod;

	private String assignmentcategorydsc;

	private String fulltimeparttimedsc;

	private String probationperioddsc;

	private String probationuom;

	private long noticeperiodtype;

	private String noticeperiodtypedsc;

	private String noticeperiod;

	private String probationenddate;

	private String payrollgroupdsc;

	private long payrollgroupid;

	private long frequency;

	private long salarytype;

	private String frequencydsc;

	private String salarytypedsc;

	private double rateofpay;

	private double oldrateofpay;

	private long grade;

	private long gradesteps;

	private String gradedsc;

	private String gradestepsdsc;

	private double gradpay;

	private String acttarminationdate;

	private String notifydate;

	/*********************
	 *    LMRC DETAILS   *
	 *********************/

	private long lmrcactionid; /// change

	private long lmrcbasicdetailsid;

	private long natureofemployement;

	private long employeecategory;

	private long staff;

	private long projectom;

	private String natureofemployementdsc;

	private String employeecategorydsc;

	private String staffdsc;

	private String projectomdsc;

	private String houseonleave; // Yes/ No

	private double prevorgbasicsalary;

	private String prevorglocation;

	private String prevorgcompanyname;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	/* Start New Field add on 25-11-20 */
	private String pfnumber;

	private String pfeffectivestartdate;

	private String prannumber;

	private String esinumber;

	private String uannumber;

	private String monthofincrement;

	private String effectiveenddate="4712-12-31";

	private String effectivestartdate;

	private String createdby;

	private String updatedby;

	private String mode;

	public JobData() {
	}

	public JobData(long actionid, long jobdatatid, String personid, String personnumber, long assignamentid, String assignamentnumber, long hrstatus, String hrstatusdsc, String payrollstatus, long hireaction, String hireactiondsc, long hirereason, String hirereasondsc, long worktype, String worktypedsc, String hiredate, long positionnumber, long legalentity, long bussinessunit, long departments, long worklocation, long baselocation, long jobcode, String positionnumberdsc, String legalentitydsc, String bussinessunitdsc, long busetid, String departmentsdsc, String worklocationdsc, String baselocationdsc, String jobcodedsc, String fte, Float workinghours, String workingasamanager, int headcount, long payrollname, long regulartemporary, String regulartemporarydsc, long assignmentcategory, long fulltimeparttime, long probationperiod, String assignmentcategorydsc, String fulltimeparttimedsc, String probationperioddsc, String probationuom, long noticeperiodtype, String noticeperiodtypedsc, String noticeperiod, String probationenddate, String payrollgroupdsc, long payrollgroupid, long frequency, long salarytype, String frequencydsc, String salarytypedsc, double rateofpay, double oldrateofpay, long grade, long gradesteps, String gradedsc, String gradestepsdsc, double gradpay, String acttarminationdate, String notifydate, long lmrcactionid, long lmrcbasicdetailsid, long natureofemployement, long employeecategory, long staff, long projectom, String natureofemployementdsc, String employeecategorydsc, String staffdsc, String projectomdsc, String houseonleave, double prevorgbasicsalary, String prevorglocation, String prevorgcompanyname, String attribute2, String attribute3, String attribute4, String pfnumber, String pfeffectivestartdate, String prannumber, String esinumber, String uannumber, String monthofincrement, String effectiveenddate, String effectivestartdate, String createdby, String updatedby, String mode) {
		this.actionid = actionid;
		this.jobdatatid = jobdatatid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.assignamentid = assignamentid;
		this.assignamentnumber = assignamentnumber;
		this.hrstatus = hrstatus;
		this.hrstatusdsc = hrstatusdsc;
		this.payrollstatus = payrollstatus;
		this.hireaction = hireaction;
		this.hireactiondsc = hireactiondsc;
		this.hirereason = hirereason;
		this.hirereasondsc = hirereasondsc;
		this.worktype = worktype;
		this.worktypedsc = worktypedsc;
		this.hiredate = hiredate;
		this.positionnumber = positionnumber;
		this.legalentity = legalentity;
		this.bussinessunit = bussinessunit;
		this.departments = departments;
		this.worklocation = worklocation;
		this.baselocation = baselocation;
		this.jobcode = jobcode;
		this.positionnumberdsc = positionnumberdsc;
		this.legalentitydsc = legalentitydsc;
		this.bussinessunitdsc = bussinessunitdsc;
		this.busetid = busetid;
		this.departmentsdsc = departmentsdsc;
		this.worklocationdsc = worklocationdsc;
		this.baselocationdsc = baselocationdsc;
		this.jobcodedsc = jobcodedsc;
		this.fte = fte;
		this.workinghours = workinghours;
		this.workingasamanager = workingasamanager;
		this.headcount = headcount;
		this.payrollname = payrollname;
		this.regulartemporary = regulartemporary;
		this.regulartemporarydsc = regulartemporarydsc;
		this.assignmentcategory = assignmentcategory;
		this.fulltimeparttime = fulltimeparttime;
		this.probationperiod = probationperiod;
		this.assignmentcategorydsc = assignmentcategorydsc;
		this.fulltimeparttimedsc = fulltimeparttimedsc;
		this.probationperioddsc = probationperioddsc;
		this.probationuom = probationuom;
		this.noticeperiodtype = noticeperiodtype;
		this.noticeperiodtypedsc = noticeperiodtypedsc;
		this.noticeperiod = noticeperiod;
		this.probationenddate = probationenddate;
		this.payrollgroupdsc = payrollgroupdsc;
		this.payrollgroupid = payrollgroupid;
		this.frequency = frequency;
		this.salarytype = salarytype;
		this.frequencydsc = frequencydsc;
		this.salarytypedsc = salarytypedsc;
		this.rateofpay = rateofpay;
		this.oldrateofpay = oldrateofpay;
		this.grade = grade;
		this.gradesteps = gradesteps;
		this.gradedsc = gradedsc;
		this.gradestepsdsc = gradestepsdsc;
		this.gradpay = gradpay;
		this.acttarminationdate = acttarminationdate;
		this.notifydate = notifydate;
		this.lmrcactionid = lmrcactionid;
		this.lmrcbasicdetailsid = lmrcbasicdetailsid;
		this.natureofemployement = natureofemployement;
		this.employeecategory = employeecategory;
		this.staff = staff;
		this.projectom = projectom;
		this.natureofemployementdsc = natureofemployementdsc;
		this.employeecategorydsc = employeecategorydsc;
		this.staffdsc = staffdsc;
		this.projectomdsc = projectomdsc;
		this.houseonleave = houseonleave;
		this.prevorgbasicsalary = prevorgbasicsalary;
		this.prevorglocation = prevorglocation;
		this.prevorgcompanyname = prevorgcompanyname;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.pfnumber = pfnumber;
		this.pfeffectivestartdate = pfeffectivestartdate;
		this.prannumber = prannumber;
		this.esinumber = esinumber;
		this.uannumber = uannumber;
		this.monthofincrement = monthofincrement;
		this.effectiveenddate = effectiveenddate;
		this.effectivestartdate = effectivestartdate;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "JobData{" +
				"actionid=" + actionid +
				", jobdatatid=" + jobdatatid +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", assignamentid=" + assignamentid +
				", assignamentnumber='" + assignamentnumber + '\'' +
				", hrstatus=" + hrstatus +
				", hrstatusdsc='" + hrstatusdsc + '\'' +
				", payrollstatus='" + payrollstatus + '\'' +
				", hireaction=" + hireaction +
				", hireactiondsc='" + hireactiondsc + '\'' +
				", hirereason=" + hirereason +
				", hirereasondsc='" + hirereasondsc + '\'' +
				", worktype=" + worktype +
				", worktypedsc='" + worktypedsc + '\'' +
				", hiredate='" + hiredate + '\'' +
				", positionnumber=" + positionnumber +
				", legalentity=" + legalentity +
				", bussinessunit=" + bussinessunit +
				", departments=" + departments +
				", worklocation=" + worklocation +
				", baselocation=" + baselocation +
				", jobcode=" + jobcode +
				", positionnumberdsc='" + positionnumberdsc + '\'' +
				", legalentitydsc='" + legalentitydsc + '\'' +
				", bussinessunitdsc='" + bussinessunitdsc + '\'' +
				", busetid=" + busetid +
				", departmentsdsc='" + departmentsdsc + '\'' +
				", worklocationdsc='" + worklocationdsc + '\'' +
				", baselocationdsc='" + baselocationdsc + '\'' +
				", jobcodedsc='" + jobcodedsc + '\'' +
				", fte='" + fte + '\'' +
				", workinghours=" + workinghours +
				", workingasamanager='" + workingasamanager + '\'' +
				", headcount=" + headcount +
				", payrollname=" + payrollname +
				", regulartemporary=" + regulartemporary +
				", regulartemporarydsc='" + regulartemporarydsc + '\'' +
				", assignmentcategory=" + assignmentcategory +
				", fulltimeparttime=" + fulltimeparttime +
				", probationperiod=" + probationperiod +
				", assignmentcategorydsc='" + assignmentcategorydsc + '\'' +
				", fulltimeparttimedsc='" + fulltimeparttimedsc + '\'' +
				", probationperioddsc='" + probationperioddsc + '\'' +
				", probationuom='" + probationuom + '\'' +
				", noticeperiodtype=" + noticeperiodtype +
				", noticeperiodtypedsc='" + noticeperiodtypedsc + '\'' +
				", noticeperiod='" + noticeperiod + '\'' +
				", probationenddate='" + probationenddate + '\'' +
				", payrollgroupdsc='" + payrollgroupdsc + '\'' +
				", payrollgroupid=" + payrollgroupid +
				", frequency=" + frequency +
				", salarytype=" + salarytype +
				", frequencydsc='" + frequencydsc + '\'' +
				", salarytypedsc='" + salarytypedsc + '\'' +
				", rateofpay=" + rateofpay +
				", oldrateofpay=" + oldrateofpay +
				", grade=" + grade +
				", gradesteps=" + gradesteps +
				", gradedsc='" + gradedsc + '\'' +
				", gradestepsdsc='" + gradestepsdsc + '\'' +
				", gradpay=" + gradpay +
				", acttarminationdate='" + acttarminationdate + '\'' +
				", notifydate='" + notifydate + '\'' +
				", lmrcactionid=" + lmrcactionid +
				", lmrcbasicdetailsid=" + lmrcbasicdetailsid +
				", natureofemployement=" + natureofemployement +
				", employeecategory=" + employeecategory +
				", staff=" + staff +
				", projectom=" + projectom +
				", natureofemployementdsc='" + natureofemployementdsc + '\'' +
				", employeecategorydsc='" + employeecategorydsc + '\'' +
				", staffdsc='" + staffdsc + '\'' +
				", projectomdsc='" + projectomdsc + '\'' +
				", houseonleave='" + houseonleave + '\'' +
				", prevorgbasicsalary=" + prevorgbasicsalary +
				", prevorglocation='" + prevorglocation + '\'' +
				", prevorgcompanyname='" + prevorgcompanyname + '\'' +
				", attribute2='" + attribute2 + '\'' +
				", attribute3='" + attribute3 + '\'' +
				", attribute4='" + attribute4 + '\'' +
				", pfnumber='" + pfnumber + '\'' +
				", pfeffectivestartdate='" + pfeffectivestartdate + '\'' +
				", prannumber='" + prannumber + '\'' +
				", esinumber='" + esinumber + '\'' +
				", uannumber='" + uannumber + '\'' +
				", monthofincrement='" + monthofincrement + '\'' +
				", effectiveenddate='" + effectiveenddate + '\'' +
				", effectivestartdate='" + effectivestartdate + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				", mode='" + mode + '\'' +
				'}';
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public long getAssignamentid() {
		return assignamentid;
	}

	public void setAssignamentid(long assignamentid) {
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

	public String getHrstatusdsc() {
		return hrstatusdsc;
	}

	public void setHrstatusdsc(String hrstatusdsc) {
		this.hrstatusdsc = hrstatusdsc;
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

	public String getHireactiondsc() {
		return hireactiondsc;
	}

	public void setHireactiondsc(String hireactiondsc) {
		this.hireactiondsc = hireactiondsc;
	}

	public long getHirereason() {
		return hirereason;
	}

	public void setHirereason(long hirereason) {
		this.hirereason = hirereason;
	}

	public String getHirereasondsc() {
		return hirereasondsc;
	}

	public void setHirereasondsc(String hirereasondsc) {
		this.hirereasondsc = hirereasondsc;
	}

	public long getWorktype() {
		return worktype;
	}

	public void setWorktype(long worktype) {
		this.worktype = worktype;
	}

	public String getWorktypedsc() {
		return worktypedsc;
	}

	public void setWorktypedsc(String worktypedsc) {
		this.worktypedsc = worktypedsc;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
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

	public String getPositionnumberdsc() {
		return positionnumberdsc;
	}

	public void setPositionnumberdsc(String positionnumberdsc) {
		this.positionnumberdsc = positionnumberdsc;
	}

	public String getLegalentitydsc() {
		return legalentitydsc;
	}

	public void setLegalentitydsc(String legalentitydsc) {
		this.legalentitydsc = legalentitydsc;
	}

	public String getBussinessunitdsc() {
		return bussinessunitdsc;
	}

	public void setBussinessunitdsc(String bussinessunitdsc) {
		this.bussinessunitdsc = bussinessunitdsc;
	}

	public long getBusetid() {
		return busetid;
	}

	public void setBusetid(long busetid) {
		this.busetid = busetid;
	}

	public String getDepartmentsdsc() {
		return departmentsdsc;
	}

	public void setDepartmentsdsc(String departmentsdsc) {
		this.departmentsdsc = departmentsdsc;
	}

	public String getWorklocationdsc() {
		return worklocationdsc;
	}

	public void setWorklocationdsc(String worklocationdsc) {
		this.worklocationdsc = worklocationdsc;
	}

	public String getBaselocationdsc() {
		return baselocationdsc;
	}

	public void setBaselocationdsc(String baselocationdsc) {
		this.baselocationdsc = baselocationdsc;
	}

	public String getJobcodedsc() {
		return jobcodedsc;
	}

	public void setJobcodedsc(String jobcodedsc) {
		this.jobcodedsc = jobcodedsc;
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

	public long getPayrollname() {
		return payrollname;
	}

	public void setPayrollname(long payrollname) {
		this.payrollname = payrollname;
	}

	public long getRegulartemporary() {
		return regulartemporary;
	}

	public void setRegulartemporary(long regulartemporary) {
		this.regulartemporary = regulartemporary;
	}

	public String getRegulartemporarydsc() {
		return regulartemporarydsc;
	}

	public void setRegulartemporarydsc(String regulartemporarydsc) {
		this.regulartemporarydsc = regulartemporarydsc;
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

	public long getProbationperiod() {
		return probationperiod;
	}

	public void setProbationperiod(long probationperiod) {
		this.probationperiod = probationperiod;
	}

	public String getAssignmentcategorydsc() {
		return assignmentcategorydsc;
	}

	public void setAssignmentcategorydsc(String assignmentcategorydsc) {
		this.assignmentcategorydsc = assignmentcategorydsc;
	}

	public String getFulltimeparttimedsc() {
		return fulltimeparttimedsc;
	}

	public void setFulltimeparttimedsc(String fulltimeparttimedsc) {
		this.fulltimeparttimedsc = fulltimeparttimedsc;
	}

	public String getProbationperioddsc() {
		return probationperioddsc;
	}

	public void setProbationperioddsc(String probationperioddsc) {
		this.probationperioddsc = probationperioddsc;
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

	public String getNoticeperiodtypedsc() {
		return noticeperiodtypedsc;
	}

	public void setNoticeperiodtypedsc(String noticeperiodtypedsc) {
		this.noticeperiodtypedsc = noticeperiodtypedsc;
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

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public long getSalarytype() {
		return salarytype;
	}

	public void setSalarytype(long salarytype) {
		this.salarytype = salarytype;
	}

	public String getFrequencydsc() {
		return frequencydsc;
	}

	public void setFrequencydsc(String frequencydsc) {
		this.frequencydsc = frequencydsc;
	}

	public String getSalarytypedsc() {
		return salarytypedsc;
	}

	public void setSalarytypedsc(String salarytypedsc) {
		this.salarytypedsc = salarytypedsc;
	}

	public double getRateofpay() {
		return rateofpay;
	}

	public void setRateofpay(double rateofpay) {
		this.rateofpay = rateofpay;
	}

	public double getOldrateofpay() {
		return oldrateofpay;
	}

	public void setOldrateofpay(double oldrateofpay) {
		this.oldrateofpay = oldrateofpay;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public long getGradesteps() {
		return gradesteps;
	}

	public void setGradesteps(long gradesteps) {
		this.gradesteps = gradesteps;
	}

	public String getGradedsc() {
		return gradedsc;
	}

	public void setGradedsc(String gradedsc) {
		this.gradedsc = gradedsc;
	}

	public String getGradestepsdsc() {
		return gradestepsdsc;
	}

	public void setGradestepsdsc(String gradestepsdsc) {
		this.gradestepsdsc = gradestepsdsc;
	}

	public double getGradpay() {
		return gradpay;
	}

	public void setGradpay(double gradpay) {
		this.gradpay = gradpay;
	}

	public String getActtarminationdate() {
		return acttarminationdate;
	}

	public void setActtarminationdate(String acttarminationdate) {
		this.acttarminationdate = acttarminationdate;
	}

	public String getNotifydate() {
		return notifydate;
	}

	public void setNotifydate(String notifydate) {
		this.notifydate = notifydate;
	}

	public long getLmrcactionid() {
		return lmrcactionid;
	}

	public void setLmrcactionid(long lmrcactionid) {
		this.lmrcactionid = lmrcactionid;
	}

	public long getLmrcbasicdetailsid() {
		return lmrcbasicdetailsid;
	}

	public void setLmrcbasicdetailsid(long lmrcbasicdetailsid) {
		this.lmrcbasicdetailsid = lmrcbasicdetailsid;
	}

	public long getNatureofemployement() {
		return natureofemployement;
	}

	public void setNatureofemployement(long natureofemployement) {
		this.natureofemployement = natureofemployement;
	}

	public long getEmployeecategory() {
		return employeecategory;
	}

	public void setEmployeecategory(long employeecategory) {
		this.employeecategory = employeecategory;
	}

	public long getStaff() {
		return staff;
	}

	public void setStaff(long staff) {
		this.staff = staff;
	}

	public long getProjectom() {
		return projectom;
	}

	public void setProjectom(long projectom) {
		this.projectom = projectom;
	}

	public String getNatureofemployementdsc() {
		return natureofemployementdsc;
	}

	public void setNatureofemployementdsc(String natureofemployementdsc) {
		this.natureofemployementdsc = natureofemployementdsc;
	}

	public String getEmployeecategorydsc() {
		return employeecategorydsc;
	}

	public void setEmployeecategorydsc(String employeecategorydsc) {
		this.employeecategorydsc = employeecategorydsc;
	}

	public String getStaffdsc() {
		return staffdsc;
	}

	public void setStaffdsc(String staffdsc) {
		this.staffdsc = staffdsc;
	}

	public String getProjectomdsc() {
		return projectomdsc;
	}

	public void setProjectomdsc(String projectomdsc) {
		this.projectomdsc = projectomdsc;
	}

	public String getHouseonleave() {
		return houseonleave;
	}

	public void setHouseonleave(String houseonleave) {
		this.houseonleave = houseonleave;
	}

	public double getPrevorgbasicsalary() {
		return prevorgbasicsalary;
	}

	public void setPrevorgbasicsalary(double prevorgbasicsalary) {
		this.prevorgbasicsalary = prevorgbasicsalary;
	}

	public String getPrevorglocation() {
		return prevorglocation;
	}

	public void setPrevorglocation(String prevorglocation) {
		this.prevorglocation = prevorglocation;
	}

	public String getPrevorgcompanyname() {
		return prevorgcompanyname;
	}

	public void setPrevorgcompanyname(String prevorgcompanyname) {
		this.prevorgcompanyname = prevorgcompanyname;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getPfnumber() {
		return pfnumber;
	}

	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}

	public String getPfeffectivestartdate() {
		return pfeffectivestartdate;
	}

	public void setPfeffectivestartdate(String pfeffectivestartdate) {
		this.pfeffectivestartdate = pfeffectivestartdate;
	}

	public String getPrannumber() {
		return prannumber;
	}

	public void setPrannumber(String prannumber) {
		this.prannumber = prannumber;
	}

	public String getEsinumber() {
		return esinumber;
	}

	public void setEsinumber(String esinumber) {
		this.esinumber = esinumber;
	}

	public String getUannumber() {
		return uannumber;
	}

	public void setUannumber(String uannumber) {
		this.uannumber = uannumber;
	}

	public String getMonthofincrement() {
		return monthofincrement;
	}

	public void setMonthofincrement(String monthofincrement) {
		this.monthofincrement = monthofincrement;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
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
