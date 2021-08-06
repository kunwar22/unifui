package in.co.srdt.unif.model.reports;

public class ModPayBillModel {
	
	private String element_id;
	private String abbreviation;
	private String element_type;
	private String busid;
	private String busname;
	private String cur_pay;
	private String pre_pay;
	private String diff_pay;
	
	

	public ModPayBillModel() {
		super();	
	}

	public ModPayBillModel(String element_id, String abbreviation, String element_type, String busid, String busname,
			String cur_pay, String pre_pay, String diff_pay) {
		super();
		this.element_id = element_id;
		this.abbreviation = abbreviation;
		this.element_type = element_type;
		this.busid = busid;
		this.busname = busname;
		this.cur_pay = cur_pay;
		this.pre_pay = pre_pay;
		this.diff_pay = diff_pay;
	}

	public String getElement_id() {
		return element_id;
	}

	public void setElement_id(String element_id) {
		this.element_id = element_id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getElement_type() {
		return element_type;
	}

	public void setElement_type(String element_type) {
		this.element_type = element_type;
	}

	public String getBusid() {
		return busid;
	}

	public void setBusid(String busid) {
		this.busid = busid;
	}

	public String getBusname() {
		return busname;
	}

	public void setBusname(String busname) {
		this.busname = busname;
	}

	public String getCur_pay() {
		return cur_pay;
	}

	public void setCur_pay(String cur_pay) {
		this.cur_pay = cur_pay;
	}

	public String getPre_pay() {
		return pre_pay;
	}

	public void setPre_pay(String pre_pay) {
		this.pre_pay = pre_pay;
	}

	public String getDiff_pay() {
		return diff_pay;
	}

	public void setDiff_pay(String diff_pay) {
		this.diff_pay = diff_pay;
	}

	@Override
	public String toString() {
		return "ModPayBillModel [element_id=" + element_id + ", abbreviation=" + abbreviation + ", element_type="
				+ element_type + ", busid=" + busid + ", busname=" + busname + ", cur_pay=" + cur_pay + ", pre_pay="
				+ pre_pay + ", diff_pay=" + diff_pay + "]";
	}
	
	

}
