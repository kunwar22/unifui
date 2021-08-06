package in.co.srdt.unif.model.absence.create;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class AbsenceTypeMaster {

	private long actionid;// ato generated id

	private long absencetypeid;// ato generated id
	
	private String Addtionalatr;

	//private AbsenceType absencetype;
		
	private List<AbsenceType> absence;
	 
	
	private List<AbsenceProration> absenceproration;
	
	public AbsenceTypeMaster() {
		
		absence = new ArrayList<>();
		absence.add(new AbsenceType());
		
		absenceproration = new ArrayList<>();
		absenceproration.add(new AbsenceProration());
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getAbsencetypeid() {
		return absencetypeid;
	}

	public void setAbsencetypeid(long absencetypeid) {
		this.absencetypeid = absencetypeid;
	}

	public List<AbsenceType> getAbsence() {
		return absence;
	}

	public void setAbsence(List<AbsenceType> absence) {
		this.absence = absence;
	}

	public List<AbsenceProration> getAbsenceproration() {
		return absenceproration;
	}

	public void setAbsenceproration(List<AbsenceProration> absenceproration) {
		this.absenceproration = absenceproration;
	}

	
	public String getAddtionalatr() {
		return Addtionalatr;
	}

	public void setAddtionalatr(String addtionalatr) {
		Addtionalatr = addtionalatr;
	}

	@Override
	public String toString() {
		return "AbsenceTypeMaster [actionid=" + actionid + ", absencetypeid=" + absencetypeid + ", Addtionalatr="
				+ Addtionalatr + ", absence=" + absence + ", absenceproration=" + absenceproration + "]";
	}

	
	
	
	
}
