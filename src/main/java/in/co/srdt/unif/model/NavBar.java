package in.co.srdt.unif.model;

public class NavBar {
	
	private long navbarid;
	private String navname;
	private String navdescr;
	private long parentid;
	private String isparent;
	private String requestaddr;
	private String createdby;
	private String isactive;
	
	public NavBar() {
		
	}
	
	
	public NavBar(long navbarid, String navname, String navdescr, long parentid, String isparent, String requestaddr,
			String createdby, String isactive) {
		super();
		this.navbarid = navbarid;
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
		this.isactive = isactive;
	}


	public NavBar(String navname, String navdescr, long parentid, String isparent, String requestaddr, String createdby) {
		
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
	}
	
	public NavBar(long navbarid, String navname, String navdescr, long parentid, String isparent, String requestaddr) {
		
		this.navbarid = navbarid;
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
	}
	
	public NavBar(long navbarid, String navdescr, String requestaddr, String createdby) {
		
		this.navbarid = navbarid;
		this.navdescr = navdescr;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public long getNavbarid() {
		return navbarid;
	}
	public void setNavbarid(long navbarid) {
		this.navbarid = navbarid;
	}
	public String getNavname() {
		return navname;
	}
	public void setNavname(String navname) {
		this.navname = navname;
	}
	public String getNavdescr() {
		return navdescr;
	}
	public void setNavdescr(String navdescr) {
		this.navdescr = navdescr;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public String getIsparent() {
		return isparent;
	}
	public void setIsparent(String isparent) {
		this.isparent = isparent;
	}
	public String getRequestaddr() {
		return requestaddr;
	}
	public void setRequestaddr(String requestaddr) {
		this.requestaddr = requestaddr;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}	
}
