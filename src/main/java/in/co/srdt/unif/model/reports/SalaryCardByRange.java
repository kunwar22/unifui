package in.co.srdt.unif.model.reports;

import java.util.ArrayList;
import java.util.List;

public class SalaryCardByRange {
	private String  personname;
	private String designation;
	private String joiningdate;
	private String personnumber;
	List<CalcodeModel> calcodemodel =new ArrayList<>();
		
	
	public SalaryCardByRange() {
		super();
	}

	public SalaryCardByRange(String personname, String designation, String joiningdate, String personnumber,
			List<CalcodeModel> calcodemodel) {
		super();
		this.personname = personname;
		this.designation = designation;
		this.joiningdate = joiningdate;
		this.personnumber = personnumber;
		this.calcodemodel = calcodemodel;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public List<CalcodeModel> getCalcodemodel() {
		return calcodemodel;
	}

	public void setCalcodemodel(List<CalcodeModel> calcodemodel) {
		this.calcodemodel = calcodemodel;
	}

	@Override
	public String toString() {
		return "SalaryCardByRange [personname=" + personname + ", designation=" + designation + ", joiningdate="
				+ joiningdate + ", personnumber=" + personnumber + ", calcodemodel=" + calcodemodel + "]";
	}
		
	
}
