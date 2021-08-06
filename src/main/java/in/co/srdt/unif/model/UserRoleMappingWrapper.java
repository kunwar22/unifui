package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

public class UserRoleMappingWrapper {
	private List<UserRoleMapping> createUserRole;

	public UserRoleMappingWrapper(List<UserRoleMapping> createUserRole) {
		createUserRole = new ArrayList<>();
		createUserRole.add(new UserRoleMapping());
	}

	public List<UserRoleMapping> getCreateUserRole() {
		return createUserRole;
	}

	public void setCreateUserRole(List<UserRoleMapping> createUserRole) {
		this.createUserRole = createUserRole;
	}

	@Override
	public String toString() {
		return "UserRoleMappingWrapper [createUserRole=" + createUserRole + "]";
	}
	
	 
}
