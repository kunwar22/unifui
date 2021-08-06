package in.co.srdt.unif.model.create;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Grades {

	private long actionid;

	private long gradesid;

	private long datasets;

	private String name;

	private String code;

	
	private String effectstartdate;
	
	private String status;
	
	private String additionalatr;

	private List<GradeSteps> gradesteps;
	
	
	

	public Grades() {
		gradesteps=new ArrayList<>();
		gradesteps.add(new GradeSteps());
	}





	/*
	 * public Grades(long actionid, long gradesid, long datasets, String name,
	 * String code, String effectstartdate, String status, String additionalatr,
	 * List<GradeSteps> gradesteps) { super(); this.actionid = actionid;
	 * this.gradesid = gradesid; this.datasets = datasets; this.name = name;
	 * this.code = code; this.effectstartdate = effectstartdate; this.status =
	 * status; this.additionalatr = additionalatr; this.gradesteps = gradesteps; }
	 */
	
	@Override
	public String toString() {
		return "Grades [actionid=" + actionid + ", gradesid=" + gradesid + ", datasets=" + datasets + ", name=" + name
				+ ", code=" + code + ", effectstartdate=" + effectstartdate + ", status=" + status + ", additionalatr="
				+ additionalatr + ", gradesteps=" + gradesteps + "]";
	}





	public long getActionid() {
		return actionid;
	}





	public void setActionid(long actionid) {
		this.actionid = actionid;
	}





	public long getGradesid() {
		return gradesid;
	}





	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
	}





	public long getDatasets() {
		return datasets;
	}





	public void setDatasets(long datasets) {
		this.datasets = datasets;
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





	public String getEffectstartdate() {
		return effectstartdate;
	}





	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String getAdditionalatr() {
		return additionalatr;
	}





	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}





	public List<GradeSteps> getGradesteps() {
		return gradesteps;
	}





	public void setGradesteps(List<GradeSteps> gradesteps) {
		this.gradesteps = gradesteps;
	}




	
}
