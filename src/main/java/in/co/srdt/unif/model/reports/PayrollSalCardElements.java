package in.co.srdt.unif.model.reports;


public class PayrollSalCardElements {

	private String element_name;
	private String element_type;
	private String prev_amt;
	private String cur_amt;
	private String tot_amt;
	
	public PayrollSalCardElements() {
		super();
	}

	public PayrollSalCardElements(String element_name, String element_type, String prev_amt, String cur_amt,
			String tot_amt) {
		super();
		this.element_name = element_name;
		this.element_type = element_type;
		this.prev_amt = prev_amt;
		this.cur_amt = cur_amt;
		this.tot_amt = tot_amt;
	}

	public String getElement_name() {
		return element_name;
	}

	public void setElement_name(String element_name) {
		this.element_name = element_name;
	}

	public String getElement_type() {
		return element_type;
	}

	public void setElement_type(String element_type) {
		this.element_type = element_type;
	}

	public String getPrev_amt() {
		return prev_amt;
	}

	public void setPrev_amt(String prev_amt) {
		this.prev_amt = prev_amt;
	}

	public String getCur_amt() {
		return cur_amt;
	}

	public void setCur_amt(String cur_amt) {
		this.cur_amt = cur_amt;
	}

	public String getTot_amt() {
		return tot_amt;
	}

	public void setTot_amt(String tot_amt) {
		this.tot_amt = tot_amt;
	}

	@Override
	public String toString() {
		return "PayrollSalCardElements [element_name=" + element_name + ", element_type=" + element_type + ", prev_amt="
				+ prev_amt + ", cur_amt=" + cur_amt + ", tot_amt=" + tot_amt + "]";
	}
	
}
