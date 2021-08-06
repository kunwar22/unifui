package in.co.srdt.unif.model.rembmedicalreimb;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import in.co.srdt.unif.model.rembcontingent.AdhocListModel;
import io.micrometer.core.lang.NonNull;

public class MedicalReimbursementModel {

	private long claimid;
	
	@Min(value=1, message="Select Claim Type")
	private long claimtype;
	private String claimtypevalue;
	
	@NotBlank(message="Enter Disease Name")
	private String diseasename;
	
	@Min(value=1, message="Select Hospital Type")
	private long hospitaltype;
	private String hospitaltypevalue;
	
	@NotBlank(message="Select Hospital Name")
	private String hospitalname;
	
	@NotBlank(message="Enter Period From")
	private String periodfrom;
	
	@NotBlank(message="Enter Period To")
	private String periodto;
	
	private String admissiondate;
	private String dischargedate;
	
	@Min(value=1, message = "Enter Claim Amount")
	private double claimamount;
	
	private double approvedamt;
	
	@NotBlank(message = "Enter Contact Number")
	private String contact;
	
	private long noofbills;
	private double totalamount;
	private String approvedstatus;
	
	@Min(value=1, message="Select Prescription Enclosed")
	private long prescriptionenclose;
	private String precriptionenclosename;
	private String emergencycerti;
	private String attachment;
	private String attachhidden;
	
	@NotBlank(message = "Enter Reason")
	private String reason;
	
	@Min(value=1,message="Select Illness Type")
	private long illnesstype;
	private String illnesstypevalues;
	private long reimburseid=10;
	private String reimbursename="Medical Reimbursement";
	private String personnumber;
	private String personid;
	private String comment;
	private String status="Saved";
	private String createdat;
	
	@NotBlank(message="Select Claimed For")
	private String selfordependent;
	
	List<MedicalDependentListModel> medicaldependent= new ArrayList<>();
	List<MedicalBillListModel> medicalbill=new ArrayList<>();
	
	public MedicalReimbursementModel() {
		super();
	}

	public MedicalReimbursementModel(long claimid, @Min(value = 1, message = "Select Claim Type") long claimtype,
			String claimtypevalue, @NotBlank(message = "Enter Disease Name") String diseasename,
			@Min(value = 1, message = "Select Hospital Type") long hospitaltype, String hospitaltypevalue,
			@NotBlank(message = "Select Hospital Name") String hospitalname,
			@NotBlank(message = "Enter Period From") String periodfrom,
			@NotBlank(message = "Enter Period To") String periodto, String admissiondate, String dischargedate,
			@Min(value = 1, message = "Enter Claim Amount") double claimamount, double approvedamt,
			@NotBlank(message = "Enter Contact Number") String contact, long noofbills, double totalamount,
			String approvedstatus, @Min(value = 1, message = "Select Prescription Enclosed") long prescriptionenclose,
			String precriptionenclosename, String emergencycerti, String attachment, String attachhidden,
			@NotBlank(message = "Enter Reason") String reason,
			@Min(value = 1, message = "Select Illness Type") long illnesstype, String illnesstypevalues,
			long reimburseid, String reimbursename, String personnumber, String personid, String comment, String status,
			String createdat, @NotBlank(message = "Select Claimed For") String selfordependent,
			List<MedicalDependentListModel> medicaldependent, List<MedicalBillListModel> medicalbill) {
		super();
		this.claimid = claimid;
		this.claimtype = claimtype;
		this.claimtypevalue = claimtypevalue;
		this.diseasename = diseasename;
		this.hospitaltype = hospitaltype;
		this.hospitaltypevalue = hospitaltypevalue;
		this.hospitalname = hospitalname;
		this.periodfrom = periodfrom;
		this.periodto = periodto;
		this.admissiondate = admissiondate;
		this.dischargedate = dischargedate;
		this.claimamount = claimamount;
		this.approvedamt = approvedamt;
		this.contact = contact;
		this.noofbills = noofbills;
		this.totalamount = totalamount;
		this.approvedstatus = approvedstatus;
		this.prescriptionenclose = prescriptionenclose;
		this.precriptionenclosename = precriptionenclosename;
		this.emergencycerti = emergencycerti;
		this.attachment = attachment;
		this.attachhidden = attachhidden;
		this.reason = reason;
		this.illnesstype = illnesstype;
		this.illnesstypevalues = illnesstypevalues;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.personnumber = personnumber;
		this.personid = personid;
		this.comment = comment;
		this.status = status;
		this.createdat = createdat;
		this.selfordependent = selfordependent;
		this.medicaldependent = medicaldependent;
		this.medicalbill = medicalbill;
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public long getClaimtype() {
		return claimtype;
	}

	public void setClaimtype(long claimtype) {
		this.claimtype = claimtype;
	}

	public String getClaimtypevalue() {
		return claimtypevalue;
	}

	public void setClaimtypevalue(String claimtypevalue) {
		this.claimtypevalue = claimtypevalue;
	}

	public String getDiseasename() {
		return diseasename;
	}

	public void setDiseasename(String diseasename) {
		this.diseasename = diseasename;
	}

	public long getHospitaltype() {
		return hospitaltype;
	}

	public void setHospitaltype(long hospitaltype) {
		this.hospitaltype = hospitaltype;
	}

	public String getHospitaltypevalue() {
		return hospitaltypevalue;
	}

	public void setHospitaltypevalue(String hospitaltypevalue) {
		this.hospitaltypevalue = hospitaltypevalue;
	}

	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getPeriodfrom() {
		return periodfrom;
	}

	public void setPeriodfrom(String periodfrom) {
		this.periodfrom = periodfrom;
	}

	public String getPeriodto() {
		return periodto;
	}

	public void setPeriodto(String periodto) {
		this.periodto = periodto;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public double getClaimamount() {
		return claimamount;
	}

	public void setClaimamount(double claimamount) {
		this.claimamount = claimamount;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public long getNoofbills() {
		return noofbills;
	}

	public void setNoofbills(long noofbills) {
		this.noofbills = noofbills;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getApprovedstatus() {
		return approvedstatus;
	}

	public void setApprovedstatus(String approvedstatus) {
		this.approvedstatus = approvedstatus;
	}

	public long getPrescriptionenclose() {
		return prescriptionenclose;
	}

	public void setPrescriptionenclose(long prescriptionenclose) {
		this.prescriptionenclose = prescriptionenclose;
	}

	public String getPrecriptionenclosename() {
		return precriptionenclosename;
	}

	public void setPrecriptionenclosename(String precriptionenclosename) {
		this.precriptionenclosename = precriptionenclosename;
	}

	public String getEmergencycerti() {
		return emergencycerti;
	}

	public void setEmergencycerti(String emergencycerti) {
		this.emergencycerti = emergencycerti;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public long getIllnesstype() {
		return illnesstype;
	}

	public void setIllnesstype(long illnesstype) {
		this.illnesstype = illnesstype;
	}

	public String getIllnesstypevalues() {
		return illnesstypevalues;
	}

	public void setIllnesstypevalues(String illnesstypevalues) {
		this.illnesstypevalues = illnesstypevalues;
	}

	public long getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(long reimburseid) {
		this.reimburseid = reimburseid;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getSelfordependent() {
		return selfordependent;
	}

	public void setSelfordependent(String selfordependent) {
		this.selfordependent = selfordependent;
	}

	public List<MedicalDependentListModel> getMedicaldependent() {
		return medicaldependent;
	}

	public void setMedicaldependent(List<MedicalDependentListModel> medicaldependent) {
		this.medicaldependent = medicaldependent;
	}

	public List<MedicalBillListModel> getMedicalbill() {
		return medicalbill;
	}

	public void setMedicalbill(List<MedicalBillListModel> medicalbill) {
		this.medicalbill = medicalbill;
	}

	@Override
	public String toString() {
		return "MedicalReimbursementModel [claimid=" + claimid + ", claimtype=" + claimtype + ", claimtypevalue="
				+ claimtypevalue + ", diseasename=" + diseasename + ", hospitaltype=" + hospitaltype
				+ ", hospitaltypevalue=" + hospitaltypevalue + ", hospitalname=" + hospitalname + ", periodfrom="
				+ periodfrom + ", periodto=" + periodto + ", admissiondate=" + admissiondate + ", dischargedate="
				+ dischargedate + ", claimamount=" + claimamount + ", approvedamt=" + approvedamt + ", contact="
				+ contact + ", noofbills=" + noofbills + ", totalamount=" + totalamount + ", approvedstatus="
				+ approvedstatus + ", prescriptionenclose=" + prescriptionenclose + ", precriptionenclosename="
				+ precriptionenclosename + ", emergencycerti=" + emergencycerti + ", attachment=" + attachment
				+ ", attachhidden=" + attachhidden + ", reason=" + reason + ", illnesstype=" + illnesstype
				+ ", illnesstypevalues=" + illnesstypevalues + ", reimburseid=" + reimburseid + ", reimbursename="
				+ reimbursename + ", personnumber=" + personnumber + ", personid=" + personid + ", comment=" + comment
				+ ", status=" + status + ", createdat=" + createdat + ", selfordependent=" + selfordependent
				+ ", medicaldependent=" + medicaldependent + ", medicalbill=" + medicalbill + "]";
	}

}
