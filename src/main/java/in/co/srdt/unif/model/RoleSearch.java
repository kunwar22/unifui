package in.co.srdt.unif.model;

public class RoleSearch {


	private long roleId;
	private String rolename;
	private String createdby;
	private String isactive;

	public RoleSearch() {
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
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

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "RoleSearch{" +
				"roleId=" + roleId +
				", rolename='" + rolename + '\'' +
				", createdby='" + createdby + '\'' +
				", isactive='" + isactive + '\'' +
				'}';
	}
}
