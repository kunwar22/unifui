package in.co.srdt.unif.model;

public class CreateRole {

	private String rolename;

	private String createdby;

	public CreateRole() {

	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public CreateRole(String rolename, String createdby) {
		this.rolename = rolename;
		this.createdby = createdby;
	}

	@Override
	public String toString() {
		return "CreateRole{" +
				"rolename='" + rolename + '\'' +
				", createdby='" + createdby + '\'' +
				'}';
	}
}
