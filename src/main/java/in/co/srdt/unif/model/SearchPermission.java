package in.co.srdt.unif.model;

public class SearchPermission {

	private String permissionname;

	private String createdby;

	private long permissionid;

	private String isactive;

	public SearchPermission() {

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

	public long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "CreatePermission{" +
				"permissionname='" + permissionname + '\'' +
				", createdby='" + createdby + '\'' +
				", permissionid=" + permissionid +
				", isactive='" + isactive + '\'' +
				'}';
	}
}
