package in.co.srdt.unif.model.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SalaryBases {
	
	private long actionid;

	private long salarybasesid;

	@NotBlank(message="Please enter Name.")
	private String name;

	@NotBlank(message="Please enter Code.")
	private String code;

	@Min(value = 1, message = "Please select Anual Factor.")
	private float annualizationfactor;

	@Min(value = 1, message = "Please select Element id.")
	private long elementid;

	@Min(value = 1, message = "Please select Input vale.")
	private long inputvalueid;

	private long frequency;

	@Min(value = 1, message = "Please select Range.")
	private int rangescale;

	@Min(value = 1, message = "Please select Grade Rate.")
	private long graderateid;
	
	@NotBlank(message="Please select Status.")
	private String status;
	
	private String frequencyname;
	
	private String graderatename;	
	
	private String additionalatr;
	
	private long salarytype;
	
		
	public SalaryBases() {
	}


	public SalaryBases(long actionid, long salarybasesid, @NotBlank(message = "Please enter Name.") String name,
			@NotBlank(message = "Please enter Code.") String code,
			@Min(value = 1, message = "Please select Anual Factor.") float annualizationfactor,
			@Min(value = 1, message = "Please select Element id.") long elementid,
			@Min(value = 1, message = "Please select Input vale.") long inputvalueid, long frequency,
			@Min(value = 1, message = "Please select Range.") int rangescale,
			@Min(value = 1, message = "Please select Grade Rate.") long graderateid,
			@NotBlank(message = "Please select Status.") String status, String frequencyname, String graderatename,
			String additionalatr, long salarytype) {
		super();
		this.actionid = actionid;
		this.salarybasesid = salarybasesid;
		this.name = name;
		this.code = code;
		this.annualizationfactor = annualizationfactor;
		this.elementid = elementid;
		this.inputvalueid = inputvalueid;
		this.frequency = frequency;
		this.rangescale = rangescale;
		this.graderateid = graderateid;
		this.status = status;
		this.frequencyname = frequencyname;
		this.graderatename = graderatename;
		this.additionalatr = additionalatr;
		this.salarytype = salarytype;
	}


	@Override
	public String toString() {
		return "SalaryBases [actionid=" + actionid + ", salarybasesid=" + salarybasesid + ", name=" + name + ", code="
				+ code + ", annualizationfactor=" + annualizationfactor + ", elementid=" + elementid + ", inputvalueid="
				+ inputvalueid + ", frequency=" + frequency + ", rangescale=" + rangescale + ", graderateid="
				+ graderateid + ", status=" + status + ", frequencyname=" + frequencyname + ", graderatename="
				+ graderatename + ", additionalatr=" + additionalatr + ", salarytype=" + salarytype + "]";
	}


	public long getActionid() {
		return actionid;
	}


	public void setActionid(long actionid) {
		this.actionid = actionid;
	}


	public long getSalarybasesid() {
		return salarybasesid;
	}


	public void setSalarybasesid(long salarybasesid) {
		this.salarybasesid = salarybasesid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public float getAnnualizationfactor() {
		return annualizationfactor;
	}


	public void setAnnualizationfactor(float annualizationfactor) {
		this.annualizationfactor = annualizationfactor;
	}


	public long getElementid() {
		return elementid;
	}


	public void setElementid(long elementid) {
		this.elementid = elementid;
	}


	public long getInputvalueid() {
		return inputvalueid;
	}


	public void setInputvalueid(long inputvalueid) {
		this.inputvalueid = inputvalueid;
	}


	public long getFrequency() {
		return frequency;
	}


	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}


	public int getRangescale() {
		return rangescale;
	}


	public void setRangescale(int rangescale) {
		this.rangescale = rangescale;
	}


	public long getGraderateid() {
		return graderateid;
	}


	public void setGraderateid(long graderateid) {
		this.graderateid = graderateid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFrequencyname() {
		return frequencyname;
	}


	public void setFrequencyname(String frequencyname) {
		this.frequencyname = frequencyname;
	}


	public String getGraderatename() {
		return graderatename;
	}


	public void setGraderatename(String graderatename) {
		this.graderatename = graderatename;
	}


	public String getAdditionalatr() {
		return additionalatr;
	}


	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}


	public long getSalarytype() {
		return salarytype;
	}


	public void setSalarytype(long salarytype) {
		this.salarytype = salarytype;
	}

		
	
}
