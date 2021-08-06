package in.co.srdt.unif.model.medicaladvance;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MedicalAdvanceModel {

	private long claimid;
	private long reimburseid=9;
	
	@Min(value=1,message = "Please Select Hospital Type")
	private long hospitaltypeid;
	
	private long hospitalnameid;
	private long patientnameid;
	private long relationshipid;
	
	@Min(value=1,message = "Please Select Treatment Start Date and End Date")
	private long hospitalizationdays;
	
	@Min(value=1,message = "Please Enter Approximate Claim Amount")
	private double claimamt;
	private double approvedamt;
	private String reimbursename="Medical Advance";
	private String personnumber;
	private String personid;
	private String status;
	private String submitdate;
	private String hospitaltypename;
	
	@NotBlank(message="Please fill Hospital Name")
	private String hospitalname;
	private String patientname;
	
	@NotBlank(message = "Please Enter Relation with Patient")
	private String relationship;
	
	@NotBlank(message = "Please Select Type of Illness")
	private String illnessdescription;
	
	@NotBlank(message = "Please Select Treatment Start Date")
	private String treatmentfrom;
	
	@NotBlank(message = "Please Select Treatment End Date")
	private String treatmentto;
	
	@NotBlank(message = "Please Select Whom to Give Advance To")
	private String payadvanceto;
	private String attachments;
	private String attachhidden;
	
	@NotBlank(message = "Please Select Claimed for")
	private String selfordependent;

	private String submitStatus;
	
	public MedicalAdvanceModel() {
		super();
	}

	public MedicalAdvanceModel(long claimid, long reimburseid, @Min(value = 1, message = "Please Select Hospital Type") long hospitaltypeid, long hospitalnameid, long patientnameid, long relationshipid, @Min(value = 1, message = "Please Select Treatment Start Date and End Date") long hospitalizationdays, @Min(value = 1, message = "Please Enter Approximate Claim Amount") double claimamt, double approvedamt, String reimbursename, String personnumber, String personid, String status, String submitdate, String hospitaltypename, @NotBlank(message = "Please fill Hospital Name") String hospitalname, String patientname, @NotBlank(message = "Please Enter Relation with Patient") String relationship, @NotBlank(message = "Please Select Type of Illness") String illnessdescription, @NotBlank(message = "Please Select Treatment Start Date") String treatmentfrom, @NotBlank(message = "Please Select Treatment End Date") String treatmentto, @NotBlank(message = "Please Select Whom to Give Advance To") String payadvanceto, String attachments, String attachhidden, @NotBlank(message = "Please Select Claimed for") String selfordependent, String submitStatus) {
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
		this.attachhidden = attachhidden;
		this.selfordependent = selfordependent;
		this.submitStatus = submitStatus;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
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

	public double getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(double claimamt) {
		this.claimamt = claimamt;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
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

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public String getSelfordependent() {
		return selfordependent;
	}

	public void setSelfordependent(String selfordependent) {
		this.selfordependent = selfordependent;
	}

	@Override
	public String toString() {
		return "MedicalAdvanceModel{" +
				"claimid=" + claimid +
				", reimburseid=" + reimburseid +
				", hospitaltypeid=" + hospitaltypeid +
				", hospitalnameid=" + hospitalnameid +
				", patientnameid=" + patientnameid +
				", relationshipid=" + relationshipid +
				", hospitalizationdays=" + hospitalizationdays +
				", claimamt=" + claimamt +
				", approvedamt=" + approvedamt +
				", reimbursename='" + reimbursename + '\'' +
				", personnumber='" + personnumber + '\'' +
				", personid='" + personid + '\'' +
				", status='" + status + '\'' +
				", submitdate='" + submitdate + '\'' +
				", hospitaltypename='" + hospitaltypename + '\'' +
				", hospitalname='" + hospitalname + '\'' +
				", patientname='" + patientname + '\'' +
				", relationship='" + relationship + '\'' +
				", illnessdescription='" + illnessdescription + '\'' +
				", treatmentfrom='" + treatmentfrom + '\'' +
				", treatmentto='" + treatmentto + '\'' +
				", payadvanceto='" + payadvanceto + '\'' +
				", attachments='" + attachments + '\'' +
				", attachhidden='" + attachhidden + '\'' +
				", selfordependent='" + selfordependent + '\'' +
				", submitStatus='" + submitStatus + '\'' +
				'}';
	}
}
