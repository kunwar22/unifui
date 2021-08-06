package in.co.srdt.unif.model.travelExpenseRemb;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class TravelingExpense {

    private long claimid;
    private boolean check=false;
	@NotBlank(message = "Please enter Traveling date.")
    private String travelingdatefrom;
	@NotBlank(message = "Please enter Start Time.")
    private String travelingstarttime;
	@NotBlank(message = "Please enter Return date.")
    private String travelingdateto;
	@NotBlank(message = "Please enter Return Time.")
    private String travelingendtime;
	@NotBlank(message = "Please enter country.")
    private String firstcountry;

    private String secondcountry;
	@NotBlank(message = "Please enter city.")
    private String firstcity;

    private String secondcity;

	private String thirdcity;
    private long traveladvance;
    private String personnumber;
	private String personid;
    private String createdby;
    private String modifiedby;
    private String tourentimationnumber;
    private long reimburseid;
	private String reimbursename;
	private String status;
	private double approvedamt;
	private double  claimamount;


	private List<TravelExpenseDetails> expensedetails;

    public TravelingExpense() {

		expensedetails = new ArrayList<>();
		expensedetails.add(new TravelExpenseDetails());
    }

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getTravelingdatefrom() {
		return travelingdatefrom;
	}

	public void setTravelingdatefrom(String travelingdatefrom) {
		this.travelingdatefrom = travelingdatefrom;
	}

	public String getTravelingstarttime() {
		return travelingstarttime;
	}

	public void setTravelingstarttime(String travelingstarttime) {
		this.travelingstarttime = travelingstarttime;
	}

	public String getTravelingdateto() {
		return travelingdateto;
	}

	public void setTravelingdateto(String travelingdateto) {
		this.travelingdateto = travelingdateto;
	}

	public String getTravelingendtime() {
		return travelingendtime;
	}

	public void setTravelingendtime(String travelingendtime) {
		this.travelingendtime = travelingendtime;
	}

	public String getFirstcountry() {
		return firstcountry;
	}

	public void setFirstcountry(String firstcountry) {
		this.firstcountry = firstcountry;
	}

	public String getSecondcountry() {
		return secondcountry;
	}

	public void setSecondcountry(String secondcountry) {
		this.secondcountry = secondcountry;
	}

	public String getFirstcity() {
		return firstcity;
	}

	public void setFirstcity(String firstcity) {
		this.firstcity = firstcity;
	}

	public String getSecondcity() {
		return secondcity;
	}

	public void setSecondcity(String secondcity) {
		this.secondcity = secondcity;
	}

	public String getThirdcity() {
		return thirdcity;
	}

	public void setThirdcity(String thirdcity) {
		this.thirdcity = thirdcity;
	}

	public long getTraveladvance() {
		return traveladvance;
	}

	public void setTraveladvance(long traveladvance) {
		this.traveladvance = traveladvance;
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getTourentimationnumber() {
		return tourentimationnumber;
	}

	public void setTourentimationnumber(String tourentimationnumber) {
		this.tourentimationnumber = tourentimationnumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}

	public double getClaimamount() {
		return claimamount;
	}

	public void setClaimamount(double claimamount) {
		this.claimamount = claimamount;
	}

	public List<TravelExpenseDetails> getExpensedetails() {
		return expensedetails;
	}

	public void setExpensedetails(List<TravelExpenseDetails> expensedetails) {
		this.expensedetails = expensedetails;
	}

	@Override
	public String toString() {
		return "TravelingExpense{" +
				"claimid=" + claimid +
				", check=" + check +
				", travelingdatefrom='" + travelingdatefrom + '\'' +
				", travelingstarttime='" + travelingstarttime + '\'' +
				", travelingdateto='" + travelingdateto + '\'' +
				", travelingendtime='" + travelingendtime + '\'' +
				", firstcountry='" + firstcountry + '\'' +
				", secondcountry='" + secondcountry + '\'' +
				", firstcity='" + firstcity + '\'' +
				", secondcity='" + secondcity + '\'' +
				", thirdcity='" + thirdcity + '\'' +
				", traveladvance=" + traveladvance +
				", personnumber='" + personnumber + '\'' +
				", personid='" + personid + '\'' +
				", createdby='" + createdby + '\'' +
				", modifiedby='" + modifiedby + '\'' +
				", tourentimationnumber='" + tourentimationnumber + '\'' +
				", reimburseid=" + reimburseid +
				", reimbursename='" + reimbursename + '\'' +
				", status='" + status + '\'' +
				", approvedamt=" + approvedamt +
				", claimamount=" + claimamount +
				", expensedetails=" + expensedetails +
				'}';
	}
}
