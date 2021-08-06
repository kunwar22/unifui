package in.co.srdt.unif.model.vendordetails;

import java.util.ArrayList;
import java.util.List;

public class VendorDetailsWrapper {


	private List<VendorElementMapping> vendorMapping;
	public VendorDetailsWrapper() {
		vendorMapping = new ArrayList<>();
		vendorMapping.add(new VendorElementMapping());
	}
	public List<VendorElementMapping> getVendorMapping() {
		return vendorMapping;
	}
	public void setVendorMapping(List<VendorElementMapping> vendorMapping) {
		this.vendorMapping = vendorMapping;
	}
	@Override
	public String toString() {
		return "VendorDetailsWrapper [vendorMapping=" + vendorMapping + "]";
	}

	
}
