package in.co.srdt.unif.model.reports;


import java.util.ArrayList;
import java.util.List;

public class ModPayBillComModel {
	
	private String cur_month;
	private String pre_month;	
	private String cur_emp;
	private String pre_emp;
	private String diff_emp;
	List<ModPayBillModel> paybill =new ArrayList<>();
	
	public ModPayBillComModel() {
		super();
	}

	public ModPayBillComModel(String cur_month, String pre_month, String cur_emp, String pre_emp, String diff_emp,
			List<ModPayBillModel> paybill) {
		super();
		this.cur_month = cur_month;
		this.pre_month = pre_month;
		this.cur_emp = cur_emp;
		this.pre_emp = pre_emp;
		this.diff_emp = diff_emp;
		this.paybill = paybill;
	}

	public String getCur_month() {
		return cur_month;
	}

	public void setCur_month(String cur_month) {
		this.cur_month = cur_month;
	}

	public String getPre_month() {
		return pre_month;
	}

	public void setPre_month(String pre_month) {
		this.pre_month = pre_month;
	}

	public String getCur_emp() {
		return cur_emp;
	}

	public void setCur_emp(String cur_emp) {
		this.cur_emp = cur_emp;
	}

	public String getPre_emp() {
		return pre_emp;
	}

	public void setPre_emp(String pre_emp) {
		this.pre_emp = pre_emp;
	}

	public String getDiff_emp() {
		return diff_emp;
	}

	public void setDiff_emp(String diff_emp) {
		this.diff_emp = diff_emp;
	}

	public List<ModPayBillModel> getPaybill() {
		return paybill;
	}

	public void setPaybill(List<ModPayBillModel> paybill) {
		this.paybill = paybill;
	}

	@Override
	public String toString() {
		return "ModPayBillComModel [cur_month=" + cur_month + ", pre_month=" + pre_month + ", cur_emp=" + cur_emp
				+ ", pre_emp=" + pre_emp + ", diff_emp=" + diff_emp + ", paybill=" + paybill + "]";
	}
}
