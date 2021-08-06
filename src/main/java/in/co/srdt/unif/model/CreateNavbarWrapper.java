package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

public class CreateNavbarWrapper {


	private List<CreateNavbar> createnavbar;
	public CreateNavbarWrapper() {
		createnavbar = new ArrayList<>();
		createnavbar.add(new CreateNavbar());
	}

	public List<CreateNavbar> getCreatenavbar() {
		return createnavbar;
	}

	public void setCreatenavbar(List<CreateNavbar> createnavbar) {
		this.createnavbar = createnavbar;
	}

	public CreateNavbarWrapper(List<CreateNavbar> createnavbar) {
		this.createnavbar = createnavbar;
	}

	@Override
	public String toString() {
		return "CreateNavbarWrapper{" +
				"createnavbar=" + createnavbar +
				'}';
	}
}
