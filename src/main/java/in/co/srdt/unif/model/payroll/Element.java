package in.co.srdt.unif.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class Element {
    long elementid;
    String name;
    String abbreviation;
    String resulationseq;
    String createby = "admin";
    String code;
    String effdt;
    List<ElementDetails> elementdetails;

    public Element() {
    	
    	elementdetails = new ArrayList<>();
    	elementdetails.add(new ElementDetails());
    }

	public Element(long elementid, String name, String abbreviation, String resulationseq, String createby, String code,
			String effdt, List<ElementDetails> elementdetails) {
		super();
		this.elementid = elementid;
		this.name = name;
		this.abbreviation = abbreviation;
		this.resulationseq = resulationseq;
		this.createby = createby;
		this.code = code;
		this.effdt = effdt;
		this.elementdetails = elementdetails;
	}

	public long getElementid() {
		return elementid;
	}

	public void setElementid(long elementid) {
		this.elementid = elementid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getResulationseq() {
		return resulationseq;
	}

	public void setResulationseq(String resulationseq) {
		this.resulationseq = resulationseq;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEffdt() {
		return effdt;
	}

	public void setEffdt(String effdt) {
		this.effdt = effdt;
	}

	public List<ElementDetails> getElementdetails() {
		return elementdetails;
	}

	public void setElementdetails(List<ElementDetails> elementdetails) {
		this.elementdetails = elementdetails;
	}

	@Override
	public String toString() {
		return "Element [elementid=" + elementid + ", name=" + name + ", abbreviation=" + abbreviation
				+ ", resulationseq=" + resulationseq + ", createby=" + createby + ", code=" + code + ", effdt=" + effdt
				+ ", elementdetails=" + elementdetails + "]";
	}

	
    
}
