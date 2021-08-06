package in.co.srdt.unif.model.salarycard;

import in.co.srdt.unif.model.salarycard.PayrollSalCardElements;

import java.util.ArrayList;
import java.util.List;

public class PayrollSalaryCard {
	
	private String person_number;
	private String person_name;
	private String joining_date;
	private String designation;
	private String cur_cal;
	private String pre_cal;
	List<PayrollSalCardElements> elem=new ArrayList<>();
	
	public PayrollSalaryCard() {
		super();
	}

	
	public PayrollSalaryCard(String person_number, String person_name, String joining_date, String designation,
			String cur_cal, String pre_cal) {
		super();
		this.person_number = person_number;
		this.person_name = person_name;
		this.joining_date = joining_date;
		this.designation = designation;
		this.cur_cal = cur_cal;
		this.pre_cal = pre_cal;
	}

	public PayrollSalaryCard(String person_number, String person_name, String joining_date, String designation,
			String cur_cal, String pre_cal, List<PayrollSalCardElements> elem) {
		super();
		this.person_number = person_number;
		this.person_name = person_name;
		this.joining_date = joining_date;
		this.designation = designation;
		this.cur_cal = cur_cal;
		this.pre_cal = pre_cal;
		this.elem = elem;
	}

	public String getPerson_number() {
		return person_number;
	}

	public void setPerson_number(String person_number) {
		this.person_number = person_number;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCur_cal() {
		return cur_cal;
	}

	public void setCur_cal(String cur_cal) {
		this.cur_cal = cur_cal;
	}

	public String getPre_cal() {
		return pre_cal;
	}

	public void setPre_cal(String pre_cal) {
		this.pre_cal = pre_cal;
	}

	public List<PayrollSalCardElements> getElem() {
		return elem;
	}

	public void setElem(List<PayrollSalCardElements> elem) {
		this.elem = elem;
	}

	@Override
	public String toString() {
		return "PayrollSalaryCard [person_number=" + person_number + ", person_name=" + person_name + ", joining_date="
				+ joining_date + ", designation=" + designation + ", cur_cal=" + cur_cal + ", pre_cal=" + pre_cal
				+ ", elem=" + elem + "]";
	}	
}
