package in.co.srdt.unif.model;

public class CreateNavbar {


	private String navname;
	private String navdescr;
	private long parentid;
	private String isparent;
	private String requestaddr;
	private String createdby;
	private String isactive;

	public CreateNavbar() {

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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "CreateNavbar{" +
				"navname='" + navname + '\'' +
				", navdescr='" + navdescr + '\'' +
				", parentid=" + parentid +
				", isparent='" + isparent + '\'' +
				", requestaddr='" + requestaddr + '\'' +
				", createdby='" + createdby + '\'' +
				", isactive='" + isactive + '\'' +
				'}';
	}

	public CreateNavbar(String navname, String navdescr, long parentid, String isparent, String requestaddr, String createdby, String isactive) {
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
		this.isactive = isactive;
	}
}
