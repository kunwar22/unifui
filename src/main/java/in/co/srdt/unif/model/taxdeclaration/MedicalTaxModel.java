package in.co.srdt.unif.model.taxdeclaration;


public class MedicalTaxModel {

	private String person_nbr;
	private String request_date;
	private String apprival_date;
	private double approved_amt;
	private double claimed_amt;
	private String attribute1;
	
	
	public MedicalTaxModel() {
		
	}


	public String getPerson_nbr() {
		return person_nbr;
	}


	public void setPerson_nbr(String person_nbr) {
		this.person_nbr = person_nbr;
	}


	public String getRequest_date() {
		return request_date;
	}


	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}


	public String getApprival_date() {
		return apprival_date;
	}


	public void setApprival_date(String apprival_date) {
		this.apprival_date = apprival_date;
	}


	public double getApproved_amt() {
		return approved_amt;
	}


	public void setApproved_amt(double approved_amt) {
		this.approved_amt = approved_amt;
	}


	public double getClaimed_amt() {
		return claimed_amt;
	}


	public void setClaimed_amt(double claimed_amt) {
		this.claimed_amt = claimed_amt;
	}


	public String getAttribute1() {
		return attribute1;
	}


	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}


}
