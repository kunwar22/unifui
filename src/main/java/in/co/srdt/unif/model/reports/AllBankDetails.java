package in.co.srdt.unif.model.reports;
public class AllBankDetails {
	
	private String currentaccounttype;
	private String currentbankaccountid;
	private String currentbankaccountnumber;
	private String currentbankname;
	private String currentbranchifsccode;
	private String name;
	private String natureofemployement;
	private String personnumber;
	private String prevaccounttype;
	private String prevbankaccountid;
	private String prevbankname;
	private String prevbranchifsccode;
	private String prevtbankaccountnumber;
	public String getCurrentaccounttype() {
		return currentaccounttype;
	}
	public void setCurrentaccounttype(String currentaccounttype) {
		this.currentaccounttype = currentaccounttype;
	}
	public String getCurrentbankaccountid() {
		return currentbankaccountid;
	}
	public void setCurrentbankaccountid(String currentbankaccountid) {
		this.currentbankaccountid = currentbankaccountid;
	}
	public String getCurrentbankaccountnumber() {
		return currentbankaccountnumber;
	}
	public void setCurrentbankaccountnumber(String currentbankaccountnumber) {
		this.currentbankaccountnumber = currentbankaccountnumber;
	}
	public String getCurrentbankname() {
		return currentbankname;
	}
	public void setCurrentbankname(String currentbankname) {
		this.currentbankname = currentbankname;
	}
	public String getCurrentbranchifsccode() {
		return currentbranchifsccode;
	}
	public void setCurrentbranchifsccode(String currentbranchifsccode) {
		this.currentbranchifsccode = currentbranchifsccode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNatureofemployement() {
		return natureofemployement;
	}
	public void setNatureofemployement(String natureofemployement) {
		this.natureofemployement = natureofemployement;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	public String getPrevaccounttype() {
		return prevaccounttype;
	}
	public void setPrevaccounttype(String prevaccounttype) {
		this.prevaccounttype = prevaccounttype;
	}
	public String getPrevbankaccountid() {
		return prevbankaccountid;
	}
	public void setPrevbankaccountid(String prevbankaccountid) {
		this.prevbankaccountid = prevbankaccountid;
	}
	public String getPrevbankname() {
		return prevbankname;
	}
	public void setPrevbankname(String prevbankname) {
		this.prevbankname = prevbankname;
	}
	public String getPrevbranchifsccode() {
		return prevbranchifsccode;
	}
	public void setPrevbranchifsccode(String prevbranchifsccode) {
		this.prevbranchifsccode = prevbranchifsccode;
	}
	public String getPrevtbankaccountnumber() {
		return prevtbankaccountnumber;
	}
	public void setPrevtbankaccountnumber(String prevtbankaccountnumber) {
		this.prevtbankaccountnumber = prevtbankaccountnumber;
	}
	@Override
	public String toString() {
		return "AllBankDetails [currentaccounttype=" + currentaccounttype + ", currentbankaccountid="
				+ currentbankaccountid + ", currentbankaccountnumber=" + currentbankaccountnumber + ", currentbankname="
				+ currentbankname + ", currentbranchifsccode=" + currentbranchifsccode + ", name=" + name
				+ ", natureofemployement=" + natureofemployement + ", personnumber=" + personnumber
				+ ", prevaccounttype=" + prevaccounttype + ", prevbankaccountid=" + prevbankaccountid
				+ ", prevbankname=" + prevbankname + ", prevbranchifsccode=" + prevbranchifsccode
				+ ", prevtbankaccountnumber=" + prevtbankaccountnumber + "]";
	}
	public AllBankDetails(String currentaccounttype, String currentbankaccountid, String currentbankaccountnumber,
			String currentbankname, String currentbranchifsccode, String name, String natureofemployement,
			String personnumber, String prevaccounttype, String prevbankaccountid, String prevbankname,
			String prevbranchifsccode, String prevtbankaccountnumber) {
		super();
		this.currentaccounttype = currentaccounttype;
		this.currentbankaccountid = currentbankaccountid;
		this.currentbankaccountnumber = currentbankaccountnumber;
		this.currentbankname = currentbankname;
		this.currentbranchifsccode = currentbranchifsccode;
		this.name = name;
		this.natureofemployement = natureofemployement;
		this.personnumber = personnumber;
		this.prevaccounttype = prevaccounttype;
		this.prevbankaccountid = prevbankaccountid;
		this.prevbankname = prevbankname;
		this.prevbranchifsccode = prevbranchifsccode;
		this.prevtbankaccountnumber = prevtbankaccountnumber;
	}
	public AllBankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
