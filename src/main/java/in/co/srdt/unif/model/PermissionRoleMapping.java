package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionRoleMapping {

	private long roleid;

	private String createdby;

	private List<Long> permissionid=new ArrayList<>();

	public PermissionRoleMapping() {

	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public List<Long> getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(List<Long> permissionid) {
		this.permissionid = permissionid;
	}

	@Override
	public String toString() {
		return "PermissionRoleMapping{" +
				"roleid=" + roleid +
				", createdby='" + createdby + '\'' +
				", permissionid=" + permissionid +
				'}';
	}
}
