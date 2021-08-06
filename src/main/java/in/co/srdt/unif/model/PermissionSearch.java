package in.co.srdt.unif.model;

public class PermissionSearch {


	private long permissionid;
	private String permissionname;
	private String createdby;
	private String isactive;

	public PermissionSearch() {

	}

	public long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
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

	@Override
	public String toString() {
		return "PermissionSearch{" +
				"permissionid=" + permissionid +
				", permissionname='" + permissionname + '\'' +
				", createdby='" + createdby + '\'' +
				", isactive='" + isactive + '\'' +
				'}';
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public PermissionSearch(long permissionid, String permissionname, String createdby, String isactive) {
		this.permissionid = permissionid;
		this.permissionname = permissionname;
		this.createdby = createdby;
		this.isactive = isactive;
	}
}
