package in.co.srdt.unif.model.taxdeclaration.hra;

import java.util.List;


public class HouseRentModel {

	private List<HraOwnerDetails> owner;
	private List<HraRentDeclaration> rent;
	
	public HouseRentModel() {
	}

	public HouseRentModel(List<HraOwnerDetails> owner, List<HraRentDeclaration> rent) {
		this.owner = owner;
		this.rent = rent;
	}

	public List<HraOwnerDetails> getOwner() {
		return owner;
	}

	public void setOwner(List<HraOwnerDetails> owner) {
		this.owner = owner;
	}

	public List<HraRentDeclaration> getRent() {
		return rent;
	}

	public void setRent(List<HraRentDeclaration> rent) {
		this.rent = rent;
	}

	@Override
	public String toString() {
		return "HouseRentModel [owner=" + owner + ", rent=" + rent + "]";
	}
	
}
