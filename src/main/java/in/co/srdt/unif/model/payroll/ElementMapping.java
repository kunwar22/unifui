package in.co.srdt.unif.model.payroll;

public class ElementMapping {
   private long mappingid;
   private long elementid;
   private String startdt;
   private String enddt="4712--12-31";
   private String personnumber;
   private String optionlist;
   private double defaultamt=0;
   private String elementname;

    public ElementMapping() {
    }

	public ElementMapping(long mappingid, long elementid, String startdt, String enddt, String personnumber, String optionlist, double defaultamt, String elementname) {
		this.mappingid = mappingid;
		this.elementid = elementid;
		this.startdt = startdt;
		this.enddt = enddt;
		this.personnumber = personnumber;
		this.optionlist = optionlist;
		this.defaultamt = defaultamt;
		this.elementname = elementname;
	}

	public String getElementname() {
		return elementname;
	}

	public void setElementname(String elementname) {
		this.elementname = elementname;
	}

	public long getMappingid() {
		return mappingid;
	}

	public void setMappingid(long mappingid) {
		this.mappingid = mappingid;
	}

	public long getElementid() {
		return elementid;
	}

	public void setElementid(long elementid) {
		this.elementid = elementid;
	}

	public String getStartdt() {
		return startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getOptionlist() {
		return optionlist;
	}

	public void setOptionlist(String optionlist) {
		this.optionlist = optionlist;
	}

	public double getDefaultamt() {
		return defaultamt;
	}

	public void setDefaultamt(double defaultamt) {
		this.defaultamt = defaultamt;
	}

	@Override
	public String toString() {
		return "ElementMapping{" +
				"mappingid=" + mappingid +
				", elementid=" + elementid +
				", startdt='" + startdt + '\'' +
				", enddt='" + enddt + '\'' +
				", personnumber='" + personnumber + '\'' +
				", optionlist='" + optionlist + '\'' +
				", defaultamt=" + defaultamt +
				", elementname='" + elementname + '\'' +
				'}';
	}
}
