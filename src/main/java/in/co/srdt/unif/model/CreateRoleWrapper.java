package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

public class CreateRoleWrapper {


	private List<CreateRole> createrole;
	public CreateRoleWrapper() {
		createrole= new ArrayList<>();
		createrole.add(new CreateRole());
	}

	public List<CreateRole> getCreaterole() {
		return createrole;
	}

	public void setCreaterole(List<CreateRole> createrole) {
		this.createrole = createrole;
	}

	public CreateRoleWrapper(List<CreateRole> createrole) {
		this.createrole = createrole;
	}
}
