package in.co.srdt.unif.model.rembtransport;

import java.util.ArrayList;
import java.util.List;

public class TransportSearchModel {
	private String reimburseid;
	private String reimbursename;
	private String travelid ;
	private String personid ;
	private String personnumber ;
	private String traveldistance ;
	private String totalfuel ;
	private String totalfuelexpense  ;
	private String salarychauffuer ;
	private String repairmaintaince ;
	private String otherexpense ;
	private String carinsurance ;
	private String roadtaxtotal ;
	private String totalcost ;
	private String vihicletype ;
	private String vihicleregnbr ;
	private String insurancevalidity ;
	private String attachments ;
	private String comments ;
	private String submiteddate ;
	private String omproject ;

	private String worklocation ;
	private String todate ;
	private String fromdate;
	private String duration ;
	private String requestedamt;
	private String claimedamt;
	private String status="Requested";
private List<TransportTravelHistory> histories;
    
    public void TransportReimbursementModel() {
    	histories = new ArrayList<>();
    	histories.add(new TransportTravelHistory());
    	
    }
	public TransportSearchModel() {
		
	}
	public String getReimburseid() {
		return reimburseid;
	}
	public void setReimburseid(String reimburseid) {
		this.reimburseid = reimburseid;
	}
	public String getReimbursename() {
		return reimbursename;
	}
	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}
	public String getTravelid() {
		return travelid;
	}
	public void setTravelid(String travelid) {
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
	public String getTraveldistance() {
		return traveldistance;
	}
	public void setTraveldistance(String traveldistance) {
		this.traveldistance = traveldistance;
	}
	public String getTotalfuel() {
		return totalfuel;
	}
	public void setTotalfuel(String totalfuel) {
		this.totalfuel = totalfuel;
	}
	public String getTotalfuelexpense() {
		return totalfuelexpense;
	}
	public void setTotalfuelexpense(String totalfuelexpense) {
		this.totalfuelexpense = totalfuelexpense;
	}
	public String getSalarychauffuer() {
		return salarychauffuer;
	}
	public void setSalarychauffuer(String salarychauffuer) {
		this.salarychauffuer = salarychauffuer;
	}
	public String getRepairmaintaince() {
		return repairmaintaince;
	}
	public void setRepairmaintaince(String repairmaintaince) {
		this.repairmaintaince = repairmaintaince;
	}
	public String getOtherexpense() {
		return otherexpense;
	}
	public void setOtherexpense(String otherexpense) {
		this.otherexpense = otherexpense;
	}
	public String getCarinsurance() {
		return carinsurance;
	}
	public void setCarinsurance(String carinsurance) {
		this.carinsurance = carinsurance;
	}
	public String getRoadtaxtotal() {
		return roadtaxtotal;
	}
	public void setRoadtaxtotal(String roadtaxtotal) {
		this.roadtaxtotal = roadtaxtotal;
	}
	public String getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(String totalcost) {
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
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRequestedamt() {
		return requestedamt;
	}
	public void setRequestedamt(String requestedamt) {
		this.requestedamt = requestedamt;
	}
	public String getClaimedamt() {
		return claimedamt;
	}
	public void setClaimedamt(String claimedamt) {
		this.claimedamt = claimedamt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TransportTravelHistory> getHistories() {
		return histories;
	}
	public void setHistories(List<TransportTravelHistory> histories) {
		this.histories = histories;
	}
	@Override
	public String toString() {
		return "TransportSearchModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", travelid="
				+ travelid + ", personid=" + personid + ", personnumber=" + personnumber + ", traveldistance="
				+ traveldistance + ", totalfuel=" + totalfuel + ", totalfuelexpense=" + totalfuelexpense
				+ ", salarychauffuer=" + salarychauffuer + ", repairmaintaince=" + repairmaintaince + ", otherexpense="
				+ otherexpense + ", carinsurance=" + carinsurance + ", roadtaxtotal=" + roadtaxtotal + ", totalcost="
				+ totalcost + ", vihicletype=" + vihicletype + ", vihicleregnbr=" + vihicleregnbr
				+ ", insurancevalidity=" + insurancevalidity + ", attachments=" + attachments + ", comments=" + comments
				+ ", submiteddate=" + submiteddate + ", omproject=" + omproject + ", worklocation=" + worklocation
				+ ", todate=" + todate + ", fromdate=" + fromdate + ", duration=" + duration + ", requestedamt="
				+ requestedamt + ", claimedamt=" + claimedamt + ", status=" + status + ", histories=" + histories + "]";
	}
	

}
