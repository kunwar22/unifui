package in.co.srdt.unif.model.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayrollSalaryCards {

	private String person_number;
	private String person_name;
	private String joining_date;
	private String designation;
	private String cur_cal;
	private String pre_cal;
	private String department;
	List<Map<String,String>> element_amt_list = new ArrayList();

	public PayrollSalaryCards() {
		super();
	}

	public PayrollSalaryCards(String person_number, String person_name, String joining_date, String designation,
			String cur_cal, String pre_cal, String department, List<Map<String, String>> element_amt_list) {
		super();
		this.person_number = person_number;
		this.person_name = person_name;
		this.joining_date = joining_date;
		this.designation = designation;
		this.cur_cal = cur_cal;
		this.pre_cal = pre_cal;
		this.department = department;
		this.element_amt_list = element_amt_list;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Map<String, String>> getElement_amt_list() {
		return element_amt_list;
	}

	public void setElement_amt_list(List<Map<String, String>> element_amt_list) {
		this.element_amt_list = element_amt_list;
	}

	
}
