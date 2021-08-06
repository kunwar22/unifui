package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

public class NavbarMapping {

	private long permissionid;

	private String createdby;

	private String loginid;

	private List<Long> navbarid=new ArrayList<>();

	public NavbarMapping() {
	}

	public long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public List<Long> getNavbarid() {
		return navbarid;
	}

	public void setNavbarid(List<Long> navbarid) {
		this.navbarid = navbarid;
	}

	@Override
	public String toString() {
		return "NavbarMapping{" +
				"permissionid=" + permissionid +
				", createdby='" + createdby + '\'' +
				", loginid='" + loginid + '\'' +
				", navbarid=" + navbarid +
				'}';
	}
}
