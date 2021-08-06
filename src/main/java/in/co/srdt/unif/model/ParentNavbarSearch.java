package in.co.srdt.unif.model;

public class ParentNavbarSearch {


	private long navbarid;
	private String navname;
	private String navdescr;
	private long parentid;
	private String isparent;
	private String requestaddr;

	public ParentNavbarSearch() {

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

	@Override
	public String toString() {
		return "ParentNavbarSearch{" +
				"navbarid=" + navbarid +
				", navname='" + navname + '\'' +
				", navdescr='" + navdescr + '\'' +
				", parentid=" + parentid +
				", isparent='" + isparent + '\'' +
				", requestaddr='" + requestaddr + '\'' +
				'}';
	}

	public ParentNavbarSearch(long navbarid, String navname, String navdescr, long parentid, String isparent, String requestaddr) {
		this.navbarid = navbarid;
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
	}
}
