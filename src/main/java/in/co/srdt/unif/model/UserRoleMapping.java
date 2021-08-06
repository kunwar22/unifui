package in.co.srdt.unif.model;

public class UserRoleMapping {

	private long rolemapid;
	private String loginid;
	private long roleid;
	private String rolename;
	private String createdby;
	
	public UserRoleMapping() {
		super();
	}

	public UserRoleMapping(long rolemapid, String loginid, long roleid, String rolename, String createdby) {
		super();
		this.rolemapid = rolemapid;
		this.loginid = loginid;
		this.roleid = roleid;
		this.rolename = rolename;
		this.createdby = createdby;
	}

	public long getRolemapid() {
		return rolemapid;
	}

	public void setRolemapid(long rolemapid) {
		this.rolemapid = rolemapid;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
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

	@Override
	public String toString() {
		return "UserRoleMapping [rolemapid=" + rolemapid + ", loginid=" + loginid + ", roleid=" + roleid + ", rolename="
				+ rolename + ", createdby=" + createdby + "]";
	}
	
	
}
