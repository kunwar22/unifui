package in.co.srdt.unif.model.revisedreimbursements;

public class RemTemplate {

    private String personnumber; //
    private String hiredate;
    private String hrstatus;
    private String payrollstatus;
    private String name;
    private String designation;
    private String reimname;
    private long businessunitid;
    private long positionid;
    private long tempid;
    private long reimid;
    private double reimbursementamount;
    private String startdate;
    private String enddate;
    private long id;
    private double totalamt;
    private double vaarramt;
    private double arrearamt;
    private double recoveryamt;
    private double vaamt;
    private String description;
    private String attachment;
    private String status;
    private String appr1status;
    
    private String createdby;
	private String updatedby;


    public RemTemplate() {
    }


	public String getPersonnumber() {
		return personnumber;
	}


	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}


	public String getHiredate() {
		return hiredate;
	}


	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}


	public String getHrstatus() {
		return hrstatus;
	}


	public void setHrstatus(String hrstatus) {
		this.hrstatus = hrstatus;
	}


	public String getPayrollstatus() {
		return payrollstatus;
	}


	public void setPayrollstatus(String payrollstatus) {
		this.payrollstatus = payrollstatus;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getReimname() {
		return reimname;
	}


	public void setReimname(String reimname) {
		this.reimname = reimname;
	}


	public long getBusinessunitid() {
		return businessunitid;
	}


	public void setBusinessunitid(long businessunitid) {
		this.businessunitid = businessunitid;
	}


	public long getPositionid() {
		return positionid;
	}


	public void setPositionid(long positionid) {
		this.positionid = positionid;
	}


	public long getTempid() {
		return tempid;
	}


	public void setTempid(long tempid) {
		this.tempid = tempid;
	}


	public long getReimid() {
		return reimid;
	}


	public void setReimid(long reimid) {
		this.reimid = reimid;
	}


	public double getReimbursementamount() {
		return reimbursementamount;
	}


	public void setReimbursementamount(double reimbursementamount) {
		this.reimbursementamount = reimbursementamount;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public double getTotalamt() {
		return totalamt;
	}


	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}


	public double getVaarramt() {
		return vaarramt;
	}


	public void setVaarramt(double vaarramt) {
		this.vaarramt = vaarramt;
	}


	public double getArrearamt() {
		return arrearamt;
	}


	public void setArrearamt(double arrearamt) {
		this.arrearamt = arrearamt;
	}


	public double getRecoveryamt() {
		return recoveryamt;
	}


	public void setRecoveryamt(double recoveryamt) {
		this.recoveryamt = recoveryamt;
	}


	public double getVaamt() {
		return vaamt;
	}


	public void setVaamt(double vaamt) {
		this.vaamt = vaamt;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAttachment() {
		return attachment;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAppr1status() {
		return appr1status;
	}


	public void setAppr1status(String appr1status) {
		this.appr1status = appr1status;
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


	public RemTemplate(String personnumber, String hiredate, String hrstatus, String payrollstatus, String name,
			String designation, String reimname, long businessunitid, long positionid, long tempid, long reimid,
			double reimbursementamount, String startdate, String enddate, long id, double totalamt, double vaarramt,
			double arrearamt, double recoveryamt, double vaamt, String description, String attachment, String status,
			String appr1status, String createdby, String updatedby) {
		super();
		this.personnumber = personnumber;
		this.hiredate = hiredate;
		this.hrstatus = hrstatus;
		this.payrollstatus = payrollstatus;
		this.name = name;
		this.designation = designation;
		this.reimname = reimname;
		this.businessunitid = businessunitid;
		this.positionid = positionid;
		this.tempid = tempid;
		this.reimid = reimid;
		this.reimbursementamount = reimbursementamount;
		this.startdate = startdate;
		this.enddate = enddate;
		this.id = id;
		this.totalamt = totalamt;
		this.vaarramt = vaarramt;
		this.arrearamt = arrearamt;
		this.recoveryamt = recoveryamt;
		this.vaamt = vaamt;
		this.description = description;
		this.attachment = attachment;
		this.status = status;
		this.appr1status = appr1status;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}


	@Override
	public String toString() {
		return "RemTemplate [personnumber=" + personnumber + ", hiredate=" + hiredate + ", hrstatus=" + hrstatus
				+ ", payrollstatus=" + payrollstatus + ", name=" + name + ", designation=" + designation + ", reimname="
				+ reimname + ", businessunitid=" + businessunitid + ", positionid=" + positionid + ", tempid=" + tempid
				+ ", reimid=" + reimid + ", reimbursementamount=" + reimbursementamount + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", id=" + id + ", totalamt=" + totalamt + ", vaarramt=" + vaarramt
				+ ", arrearamt=" + arrearamt + ", recoveryamt=" + recoveryamt + ", vaamt=" + vaamt + ", description="
				+ description + ", attachment=" + attachment + ", status=" + status + ", appr1status=" + appr1status
				+ ", createdby=" + createdby + ", updatedby=" + updatedby + "]";
	}

    
    
    
}
