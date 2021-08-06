package in.co.srdt.unif.model.roleassignment;

import javax.validation.constraints.NotBlank;

public class AutoMappingRole {
	
	private long automapproleid;

	private long rolesid;

	@NotBlank(message = "Please Select Role")
	private String rolename;

	public AutoMappingRole() {
		super();
	}	

	public AutoMappingRole(long automapproleid, long rolesid,
			@NotBlank(message = "Please Select Role") String rolename) {
		super();
		this.automapproleid = automapproleid;
		this.rolesid = rolesid;
		this.rolename = rolename;
	}

	public long getAutomapproleid() {
		return automapproleid;
	}

	public void setAutomapproleid(long automapproleid) {
		this.automapproleid = automapproleid;
	}

	public String getRolesid() {
		return String.valueOf(rolesid);
	}

	public void setRolesid(long rolesid) {
		this.rolesid = rolesid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "AutoMappingRole [automapproleid=" + automapproleid + ", rolesid=" + rolesid + ", rolename=" + rolename
				+ "]";
	}
	
	

}
