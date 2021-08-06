package in.co.srdt.unif.model.taxdeclaration;


public class RFACommonModel {

	private String person_nbr;
	private String start_date;
	private String end_date;
	private double amount1;
	private double amount2;
	private String attribute1;
	
	
	public RFACommonModel() {
		super();
	}


	public String getPerson_nbr() {
		return person_nbr;
	}


	public void setPerson_nbr(String person_nbr) {
		this.person_nbr = person_nbr;
	}


	public String getStart_date() {
		return start_date;
	}


	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}


	public double getAmount1() {
		return amount1;
	}


	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}


	public double getAmount2() {
		return amount2;
	}


	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}


	public String getAttribute1() {
		return attribute1;
	}


	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	
}
