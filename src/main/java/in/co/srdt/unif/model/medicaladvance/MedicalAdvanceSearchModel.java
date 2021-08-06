package in.co.srdt.unif.model.medicaladvance;

public class MedicalAdvanceSearchModel {

	private long claimid;
	private long reimburseid;	
	private long hospitaltypeid;
	private long hospitalnameid;
	private long patientnameid;
	private long relationshipid;
	private long hospitalizationdays;
	private long claimamt;
	private long approvedamt;
	private String reimbursename;
	private String personnumber;
	private String personid;
	private String status;
	private String submitdate;
	private String hospitaltypename;
	private String hospitalname;
	private String patientname;
	private String relationship;
	private String illnessdescription;
	private String treatmentfrom;
	private String treatmentto;	
	private String payadvanceto;
	private String attachments;
	
	public MedicalAdvanceSearchModel() {
		super();
	}
	
	
	public MedicalAdvanceSearchModel(long claimid, long reimburseid, long hospitaltypeid, long hospitalnameid,
			long patientnameid, long relationshipid, long hospitalizationdays, long claimamt, long approvedamt,
			String reimbursename, String personnumber, String personid, String status, String submitdate,
			String hospitaltypename, String hospitalname, String patientname, String relationship,
			String illnessdescription, String treatmentfrom, String treatmentto, String payadvanceto,
			String attachments) {
		super();
		this.claimid = claimid;
		this.reimburseid = reimburseid;
		this.hospitaltypeid = hospitaltypeid;
		this.hospitalnameid = hospitalnameid;
		this.patientnameid = patientnameid;
		this.relationshipid = relationshipid;
		this.hospitalizationdays = hospitalizationdays;
		this.claimamt = claimamt;
		this.approvedamt = approvedamt;
		this.reimbursename = reimbursename;
		this.personnumber = personnumber;
		this.personid = personid;
		this.status = status;
		this.submitdate = submitdate;
		this.hospitaltypename = hospitaltypename;
		this.hospitalname = hospitalname;
		this.patientname = patientname;
		this.relationship = relationship;
		this.illnessdescription = illnessdescription;
		this.treatmentfrom = treatmentfrom;
		this.treatmentto = treatmentto;
		this.payadvanceto = payadvanceto;
		this.attachments = attachments;
	}



	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public long getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(long reimburseid) {
		this.reimburseid = reimburseid;
	}

	public long getHospitaltypeid() {
		return hospitaltypeid;
	}

	public void setHospitaltypeid(long hospitaltypeid) {
		this.hospitaltypeid = hospitaltypeid;
	}

	public long getHospitalnameid() {
		return hospitalnameid;
	}

	public void setHospitalnameid(long hospitalnameid) {
		this.hospitalnameid = hospitalnameid;
	}

	public long getPatientnameid() {
		return patientnameid;
	}

	public void setPatientnameid(long patientnameid) {
		this.patientnameid = patientnameid;
	}

	public long getRelationshipid() {
		return relationshipid;
	}

	public void setRelationshipid(long relationshipid) {
		this.relationshipid = relationshipid;
	}

	public long getHospitalizationdays() {
		return hospitalizationdays;
	}

	public void setHospitalizationdays(long hospitalizationdays) {
		this.hospitalizationdays = hospitalizationdays;
	}

	public long getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(long claimamt) {
		this.claimamt = claimamt;
	}

	public long getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(long approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getReimbursename() {
		return reimbursename;
	}

	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getHospitaltypename() {
		return hospitaltypename;
	}

	public void setHospitaltypename(String hospitaltypename) {
		this.hospitaltypename = hospitaltypename;
	}

	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getIllnessdescription() {
		return illnessdescription;
	}

	public void setIllnessdescription(String illnessdescription) {
		this.illnessdescription = illnessdescription;
	}

	public String getTreatmentfrom() {
		return treatmentfrom;
	}

	public void setTreatmentfrom(String treatmentfrom) {
		this.treatmentfrom = treatmentfrom;
	}

	public String getTreatmentto() {
		return treatmentto;
	}

	public void setTreatmentto(String treatmentto) {
		this.treatmentto = treatmentto;
	}

	/*
	 * public String getApprovedby() { return approvedby; }
	 * 
	 * public void setApprovedby(String approvedby) { this.approvedby = approvedby;
	 * }
	 */

	public String getPayadvanceto() {
		return payadvanceto;
	}

	public void setPayadvanceto(String payadvanceto) {
		this.payadvanceto = payadvanceto;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "MedicalAdvanceModel [claimid=" + claimid + ", reimburseid=" + reimburseid + ", hospitaltypeid="
				+ hospitaltypeid + ", hospitalnameid=" + hospitalnameid + ", patientnameid=" + patientnameid
				+ ", relationshipid=" + relationshipid + ", hospitalizationdays=" + hospitalizationdays + ", claimamt="
				+ claimamt + ", approvedamt=" + approvedamt + ", reimbursename=" + reimbursename + ", personnumber="
				+ personnumber + ", personid=" + personid + ", status=" + status + ", submitdate=" + submitdate
				+ ", hospitaltypename=" + hospitaltypename + ", hospitalname=" + hospitalname + ", patientname="
				+ patientname + ", relationship=" + relationship + ", illnessdescription=" + illnessdescription
				+ ", treatmentfrom=" + treatmentfrom + ", treatmentto=" + treatmentto + ", payadvanceto=" + payadvanceto
				+ ", attachments=" + attachments + "]";
	}

	
}
