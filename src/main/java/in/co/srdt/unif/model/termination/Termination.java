package in.co.srdt.unif.model.termination;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Termination {

    private long actionid;

    private long jobdatatid;

    private String personid;

    private String personnumber;

    private long assignamentid;

    private String assignamentnumber;

    private double gradpay;

    private long hrstatus;

    private String hrstatusdsc;

    private String payrollstatus;

    @Min(value = 1,message = "Please select Action.")
    private long hireaction;

    private String hireactiondsc;

    @Min(value = 1,message = "Please select Reason.")
    private long hirereason;

    private String hirereasondsc;

    private long worktype;

    private String worktypedsc;

    private String hiredate;

    private String effectivestartdate;

    private String effectiveenddate="4712-12-31";

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

    private String departmentsdsc;

    private String worklocationdsc;

    private String baselocationdsc;

    private String jobcodedsc;

    private String fte;

    private String workinghours;

    private String workingasamanager;

    private int headcount;

    private long payrollname;

    private long regulartemporary;

    private String regulartemporarydsc;

    private long assignmentcategory;

    private long fulltimeparttime;

    private long workcategory;

    private long probationperiod;

    private String assignmentcategorydsc;

    private String fulltimeparttimedsc;

    private String workcategorydsc;

    private String probationperioddsc;

    private String probationuom;

    private long noticeperiodtype;

    private String noticeperiodtypedsc;

    private String noticeperiod;

    private String probationenddate;

    private String payrollgroupdsc;

    private long payrollgroupid;

    private String createdby;

    private String updatedby;

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

    private String mode;

    private long busetid;

    private long lmrcactionid;

    @NotBlank(message = "Please select Notification Date.")
    private String notifydate;

    @NotBlank(message = "Please select Actual Termination Date.")
    private String acttarminationdate;

    public Termination() {
    }

    public Termination(long actionid, long jobdatatid, String personid, String personnumber, long assignamentid, String assignamentnumber, double gradpay, long hrstatus, String hrstatusdsc, String payrollstatus, @Min(value = 1, message = "Please select Action.") long hireaction, String hireactiondsc, @Min(value = 1, message = "Please select Reason.") long hirereason, String hirereasondsc, long worktype, String worktypedsc, String hiredate, String effectivestartdate, String effectiveenddate, long positionnumber, long legalentity, long bussinessunit, long departments, long worklocation, long baselocation, long jobcode, String positionnumberdsc, String legalentitydsc, String bussinessunitdsc, String departmentsdsc, String worklocationdsc, String baselocationdsc, String jobcodedsc, String fte, String workinghours, String workingasamanager, int headcount, long payrollname, long regulartemporary, String regulartemporarydsc, long assignmentcategory, long fulltimeparttime, long workcategory, long probationperiod, String assignmentcategorydsc, String fulltimeparttimedsc, String workcategorydsc, String probationperioddsc, String probationuom, long noticeperiodtype, String noticeperiodtypedsc, String noticeperiod, String probationenddate, String payrollgroupdsc, long payrollgroupid, String createdby, String updatedby, long frequency, long salarytype, String frequencydsc, String salarytypedsc, double rateofpay, double oldrateofpay, long grade, long gradesteps, String gradedsc, String gradestepsdsc, String mode, long busetid, long lmrcactionid, @NotBlank(message = "Please select Notification Date.") String notifydate, @NotBlank(message = "Please select Actual Termination Date.") String acttarminationdate) {
        this.actionid = actionid;
        this.jobdatatid = jobdatatid;
        this.personid = personid;
        this.personnumber = personnumber;
        this.assignamentid = assignamentid;
        this.assignamentnumber = assignamentnumber;
        this.gradpay = gradpay;
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
        this.effectivestartdate = effectivestartdate;
        this.effectiveenddate = effectiveenddate;
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
        this.workcategory = workcategory;
        this.probationperiod = probationperiod;
        this.assignmentcategorydsc = assignmentcategorydsc;
        this.fulltimeparttimedsc = fulltimeparttimedsc;
        this.workcategorydsc = workcategorydsc;
        this.probationperioddsc = probationperioddsc;
        this.probationuom = probationuom;
        this.noticeperiodtype = noticeperiodtype;
        this.noticeperiodtypedsc = noticeperiodtypedsc;
        this.noticeperiod = noticeperiod;
        this.probationenddate = probationenddate;
        this.payrollgroupdsc = payrollgroupdsc;
        this.payrollgroupid = payrollgroupid;
        this.createdby = createdby;
        this.updatedby = updatedby;
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
        this.mode = mode;
        this.busetid = busetid;
        this.lmrcactionid = lmrcactionid;
        this.notifydate = notifydate;
        this.acttarminationdate = acttarminationdate;
    }

    public long getLmrcactionid() {
        return lmrcactionid;
    }

    public void setLmrcactionid(long lmrcactionid) {
        this.lmrcactionid = lmrcactionid;
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

    public double getGradpay() {
        return gradpay;
    }

    public void setGradpay(double gradpay) {
        this.gradpay = gradpay;
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

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
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

    public String getWorkcategorydsc() {
        return workcategorydsc;
    }

    public void setWorkcategorydsc(String workcategorydsc) {
        this.workcategorydsc = workcategorydsc;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getBusetid() {
        return busetid;
    }

    public void setBusetid(long busetid) {
        this.busetid = busetid;
    }

    public String getNotifydate() {
        return notifydate;
    }

    public void setNotifydate(String notifydate) {
        this.notifydate = notifydate;
    }

    public String getActtarminationdate() {
        return acttarminationdate;
    }

    public void setActtarminationdate(String acttarminationdate) {
        this.acttarminationdate = acttarminationdate;
    }

    @Override
    public String toString() {
        return "Termination{" +
                "actionid=" + actionid +
                ", jobdatatid=" + jobdatatid +
                ", personid='" + personid + '\'' +
                ", personnumber='" + personnumber + '\'' +
                ", assignamentid=" + assignamentid +
                ", assignamentnumber='" + assignamentnumber + '\'' +
                ", gradpay=" + gradpay +
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
                ", effectivestartdate='" + effectivestartdate + '\'' +
                ", effectiveenddate='" + effectiveenddate + '\'' +
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
                ", departmentsdsc='" + departmentsdsc + '\'' +
                ", worklocationdsc='" + worklocationdsc + '\'' +
                ", baselocationdsc='" + baselocationdsc + '\'' +
                ", jobcodedsc='" + jobcodedsc + '\'' +
                ", fte='" + fte + '\'' +
                ", workinghours='" + workinghours + '\'' +
                ", workingasamanager='" + workingasamanager + '\'' +
                ", headcount=" + headcount +
                ", payrollname=" + payrollname +
                ", regulartemporary=" + regulartemporary +
                ", regulartemporarydsc='" + regulartemporarydsc + '\'' +
                ", assignmentcategory=" + assignmentcategory +
                ", fulltimeparttime=" + fulltimeparttime +
                ", workcategory=" + workcategory +
                ", probationperiod=" + probationperiod +
                ", assignmentcategorydsc='" + assignmentcategorydsc + '\'' +
                ", fulltimeparttimedsc='" + fulltimeparttimedsc + '\'' +
                ", workcategorydsc='" + workcategorydsc + '\'' +
                ", probationperioddsc='" + probationperioddsc + '\'' +
                ", probationuom='" + probationuom + '\'' +
                ", noticeperiodtype=" + noticeperiodtype +
                ", noticeperiodtypedsc='" + noticeperiodtypedsc + '\'' +
                ", noticeperiod='" + noticeperiod + '\'' +
                ", probationenddate='" + probationenddate + '\'' +
                ", payrollgroupdsc='" + payrollgroupdsc + '\'' +
                ", payrollgroupid=" + payrollgroupid +
                ", createdby='" + createdby + '\'' +
                ", updatedby='" + updatedby + '\'' +
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
                ", mode='" + mode + '\'' +
                ", busetid=" + busetid +
                ", lmrcactionid=" + lmrcactionid +
                ", notifydate='" + notifydate + '\'' +
                ", acttarminationdate='" + acttarminationdate + '\'' +
                '}';
    }
}
