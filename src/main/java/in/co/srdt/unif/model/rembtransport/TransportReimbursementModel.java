package in.co.srdt.unif.model.rembtransport;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransportReimbursementModel {
	
	private long reimburseid;
    private String reimbursename;
    private long travelid;
    private String personid;
    private String personnumber;
    private double traveldistance;
    private double totalfuel;
    private double totalfuelexpense;
    private double salarychauffuer;
    private double repairmaintaince;
    private double otherexpense;
    private double carinsurance;
    private double roadtaxtotal;
    private double totalcost;
    @NotBlank(message= "Please select Vehicle Type.")
    private String vihicletype;
    @NotBlank(message= "Please fill Registration Number.")
    private String vihicleregnbr;
    @NotBlank(message= "Please select Insurance Validity Date.")
    private String insurancevalidity;
    private String attachments;
    private String comments;
    private String submiteddate;
    @NotBlank(message= "Please select OM/Project.")
    private String omproject;
    @NotBlank(message= "Please select Work Location.")
    private String worklocation;
    private int duration;
    private double requestedamt;
    @Min(value = 1, message = "Please fill Amount.")
    private double claimedamt;
    private String status;
    private String attachhidden;
    private String submitStatus;
    
    private List<TransportTravelHistory> histories;
    
    public TransportReimbursementModel() {
    	histories = new ArrayList<>();
    	histories.add(new TransportTravelHistory());
    	
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

	public long getTravelid() {
		return travelid;
	}

	public void setTravelid(long travelid) {
		this.travelid = travelid;
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

	public double getTraveldistance() {
		return traveldistance;
	}

	public void setTraveldistance(double traveldistance) {
		this.traveldistance = traveldistance;
	}

	public double getTotalfuel() {
		return totalfuel;
	}

	public void setTotalfuel(double totalfuel) {
		this.totalfuel = totalfuel;
	}

	public double getTotalfuelexpense() {
		return totalfuelexpense;
	}

	public void setTotalfuelexpense(double totalfuelexpense) {
		this.totalfuelexpense = totalfuelexpense;
	}

	public double getSalarychauffuer() {
		return salarychauffuer;
	}

	public void setSalarychauffuer(double salarychauffuer) {
		this.salarychauffuer = salarychauffuer;
	}

	public double getRepairmaintaince() {
		return repairmaintaince;
	}

	public void setRepairmaintaince(double repairmaintaince) {
		this.repairmaintaince = repairmaintaince;
	}

	public double getOtherexpense() {
		return otherexpense;
	}

	public void setOtherexpense(double otherexpense) {
		this.otherexpense = otherexpense;
	}

	public double getCarinsurance() {
		return carinsurance;
	}

	public void setCarinsurance(double carinsurance) {
		this.carinsurance = carinsurance;
	}

	public double getRoadtaxtotal() {
		return roadtaxtotal;
	}

	public void setRoadtaxtotal(double roadtaxtotal) {
		this.roadtaxtotal = roadtaxtotal;
	}

	public double getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}

	public String getVihicletype() {
		return vihicletype;
	}

	public void setVihicletype(String vihicletype) {
		this.vihicletype = vihicletype;
	}

	public String getVihicleregnbr() {
		return vihicleregnbr;
	}

	public void setVihicleregnbr(String vihicleregnbr) {
		this.vihicleregnbr = vihicleregnbr;
	}

	public String getInsurancevalidity() {
		return insurancevalidity;
	}

	public void setInsurancevalidity(String insurancevalidity) {
		this.insurancevalidity = insurancevalidity;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSubmiteddate() {
		return submiteddate;
	}

	public void setSubmiteddate(String submiteddate) {
		this.submiteddate = submiteddate;
	}

	public String getOmproject() {
		return omproject;
	}

	public void setOmproject(String omproject) {
		this.omproject = omproject;
	}

	public String getWorklocation() {
		return worklocation;
	}

	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getRequestedamt() {
		return requestedamt;
	}

	public void setRequestedamt(double requestedamt) {
		this.requestedamt = requestedamt;
	}

	public double getClaimedamt() {
		return claimedamt;
	}

	public void setClaimedamt(double claimedamt) {
		this.claimedamt = claimedamt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		attachhidden = attachhidden;
	}

	
	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public List<TransportTravelHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<TransportTravelHistory> histories) {
		this.histories = histories;
	}

	@Override
	public String toString() {
		return "TransportReimbursementModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", travelid=" + travelid + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", traveldistance=" + traveldistance + ", totalfuel=" + totalfuel + ", totalfuelexpense="
				+ totalfuelexpense + ", salarychauffuer=" + salarychauffuer + ", repairmaintaince=" + repairmaintaince
				+ ", otherexpense=" + otherexpense + ", carinsurance=" + carinsurance + ", roadtaxtotal=" + roadtaxtotal
				+ ", totalcost=" + totalcost + ", vihicletype=" + vihicletype + ", vihicleregnbr=" + vihicleregnbr
				+ ", insurancevalidity=" + insurancevalidity + ", attachments=" + attachments + ", comments=" + comments
				+ ", submiteddate=" + submiteddate + ", omproject=" + omproject + ", worklocation=" + worklocation
				+ ", duration=" + duration + ", requestedamt=" + requestedamt + ", claimedamt=" + claimedamt
				+ ", status=" + status + ", Attachhidden=" + attachhidden + ", submitStatus=" + submitStatus
				+ ", histories=" + histories + "]";
	}

	
	
}
