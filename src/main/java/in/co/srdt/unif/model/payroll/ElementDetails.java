package in.co.srdt.unif.model.payroll;

public class ElementDetails {
	private long attributeid;
    private long elementid;
    private String startdate;
    private String enddate="4712-12-31";
    private String elementtype;
    private String componenttype;
    private String ispaidcomponent = "false";
    private String isprorated = "false";
    private String isctcpart = "false";
    private String createdependent = "false";
    private String isactive = "N";
    private String taxability;
    private String istaxdistribution = "false";
    private String maintainby = "CUS";
    private long dependentid;
    private String isarr = "false";
    private String formula;
    private String caltype;

    public ElementDetails() {
    }
    
	public ElementDetails(long attributeid, long elementid, String startdate, String enddate, String elementtype,
			String componenttype, String ispaidcomponent, String isprorated, String isctcpart, String createdependent,
			String isactive, String taxability, String istaxdistribution, String maintainby, long dependentid,
			String isarr, String formula, String caltype) {
		super();
		this.attributeid = attributeid;
		this.elementid = elementid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.elementtype = elementtype;
		this.componenttype = componenttype;
		this.ispaidcomponent = ispaidcomponent;
		this.isprorated = isprorated;
		this.isctcpart = isctcpart;
		this.createdependent = createdependent;
		this.isactive = isactive;
		this.taxability = taxability;
		this.istaxdistribution = istaxdistribution;
		this.maintainby = maintainby;
		this.dependentid = dependentid;
		this.isarr = isarr;
		this.formula = formula;
		this.caltype = caltype;
	}

	public long getAttributeid() {
		return attributeid;
	}

	public void setAttributeid(long attributeid) {
		this.attributeid = attributeid;
	}

	public long getElementid() {
		return elementid;
	}

	public void setElementid(long elementid) {
		this.elementid = elementid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getElementtype() {
		return elementtype;
	}

	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}

	public String getComponenttype() {
		return componenttype;
	}

	public void setComponenttype(String componenttype) {
		this.componenttype = componenttype;
	}

	public String getIspaidcomponent() {
		return ispaidcomponent;
	}

	public void setIspaidcomponent(String ispaidcomponent) {
		this.ispaidcomponent = ispaidcomponent;
	}

	public String getIsprorated() {
		return isprorated;
	}

	public void setIsprorated(String isprorated) {
		this.isprorated = isprorated;
	}

	public String getIsctcpart() {
		return isctcpart;
	}

	public void setIsctcpart(String isctcpart) {
		this.isctcpart = isctcpart;
	}

	public String getCreatedependent() {
		return createdependent;
	}

	public void setCreatedependent(String createdependent) {
		this.createdependent = createdependent;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getTaxability() {
		return taxability;
	}

	public void setTaxability(String taxability) {
		this.taxability = taxability;
	}

	public String getIstaxdistribution() {
		return istaxdistribution;
	}

	public void setIstaxdistribution(String istaxdistribution) {
		this.istaxdistribution = istaxdistribution;
	}

	public String getMaintainby() {
		return maintainby;
	}

	public void setMaintainby(String maintainby) {
		this.maintainby = maintainby;
	}

	public long getDependentid() {
		return dependentid;
	}

	public void setDependentid(long dependentid) {
		this.dependentid = dependentid;
	}

	public String getIsarr() {
		return isarr;
	}

	public void setIsarr(String isarr) {
		this.isarr = isarr;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getCaltype() {
		return caltype;
	}

	public void setCaltype(String caltype) {
		this.caltype = caltype;
	}

	@Override
	public String toString() {
		return "ElementDetails [attributeid=" + attributeid + ", elementid=" + elementid + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", elementtype=" + elementtype + ", componenttype=" + componenttype
				+ ", ispaidcomponent=" + ispaidcomponent + ", isprorated=" + isprorated + ", isctcpart=" + isctcpart
				+ ", createdependent=" + createdependent + ", isactive=" + isactive + ", taxability=" + taxability
				+ ", istaxdistribution=" + istaxdistribution + ", maintainby=" + maintainby + ", dependentid="
				+ dependentid + ", isarr=" + isarr + ", formula=" + formula + ", caltype=" + caltype + "]";
	}
    
   
}
