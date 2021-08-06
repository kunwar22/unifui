package in.co.srdt.unif.model;

import in.co.srdt.unif.model.newperson.ContactDetails;
import in.co.srdt.unif.model.newperson.EmailDetails;

import java.util.ArrayList;
import java.util.List;

public class CreatePermissionWrapper {


	private List<CreatePermission> createpermission;
	public CreatePermissionWrapper() {
		createpermission = new ArrayList<>();
		createpermission.add(new CreatePermission());
	}

	public List<CreatePermission> getCreatepermission() {
		return createpermission;
	}

	public void setCreatepermission(List<CreatePermission> createpermission) {
		this.createpermission = createpermission;
	}

	public CreatePermissionWrapper(List<CreatePermission> createpermission) {
		this.createpermission = createpermission;
	}

	@Override
	public String toString() {
		return "CreatePermissionWrapper{" +
				"createpermission=" + createpermission +
				'}';
	}
}
