package in.co.srdt.unif.model;

public class UserRoles {

	private String rolename;
	private String createdby;
	private String userid;
	private String roleId;
	private String isactive;
	
	public UserRoles() {}

	public UserRoles(String rolename, String createdby, String userid, String roleId, String isactive) {
		this.rolename = rolename;
		this.createdby = createdby;
		this.userid = userid;
		this.roleId = roleId;
		this.isactive = isactive;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
	
	
}
