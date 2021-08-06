package in.co.srdt.unif.model.vendordetails;

import java.util.ArrayList;
import java.util.List;

public class VendorPersonMappingWrapper {


	private List<VendorPersonMapping> vendorPersonMapping;
	public VendorPersonMappingWrapper() {
		vendorPersonMapping = new ArrayList<>();
		vendorPersonMapping.add(new VendorPersonMapping());
	}
	public List<VendorPersonMapping> getVendorPersonMapping() {
		return vendorPersonMapping;
	}
	public void setVendorPersonMapping(List<VendorPersonMapping> vendorPersonMapping) {
		this.vendorPersonMapping = vendorPersonMapping;
	}
	

	
}
