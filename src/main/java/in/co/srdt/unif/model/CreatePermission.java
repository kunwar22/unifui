package in.co.srdt.unif.model;

public class CreatePermission {

	private String permissionname;

	private String createdby;


	public CreatePermission() {

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




	@Override
	public String toString() {
		return "CreatePermission{" +
				"permissionname='" + permissionname + '\'' +
				", createdby='" + createdby + '\'' +
				'}';
	}
}
