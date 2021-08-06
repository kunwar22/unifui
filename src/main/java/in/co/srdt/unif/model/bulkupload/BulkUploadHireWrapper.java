package in.co.srdt.unif.model.bulkupload;

import java.util.ArrayList;
import java.util.List;

public class BulkUploadHireWrapper {
	

	private List<BulkUploadHire> bulkUploadHire;
	public BulkUploadHireWrapper() {
		bulkUploadHire = new ArrayList<>();
		bulkUploadHire.add(new BulkUploadHire());
	}
	public List<BulkUploadHire> getBulkUploadHire() {
		return bulkUploadHire;
	}
	public void setBulkUploadHire(List<BulkUploadHire> bulkUploadHire) {
		this.bulkUploadHire = bulkUploadHire;
	}
	@Override
	public String toString() {
		return "BulkUploadHireWrapper [bulkUploadHire=" + bulkUploadHire + "]";
	}

	
}
