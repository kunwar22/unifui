package in.co.srdt.unif.model.rembcontingent;
import java.util.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AdhocListModel {
	
	private long travelclaimdetailsid;
	
	@NotBlank(message = "Please enter Date.")
	private String date;
	
	@NotBlank(message = "Please enter from location.")
	private String fromlocation;
	
	@NotBlank(message = "Please enter to location.")
	private String tolocation;
	
	@Min(value=1,message = "Please enter Transport Mode.")
	private long transportmodeid;
	
	private String transportmode;
	
	@Min(value=1, message = "Please enter Claim Amount.")
	private long claimamt;
	
	@NotBlank(message = "Please enter Travel Purpose.")
	private String travelpurpose;
	
	
	private long fromneareststationid;
	
	private String fromneareststation;
	
	private long toneareststationid;
	
	private String toneareststation;
	
	@Min(value=1,message = "Please enter Vehicle Used.")
	private long vehicleusedid;
	
	private String vehicleused;
	private String attachments;
	private double approveamt;
	private String attachhidden;
	private String filepres;
	
	public AdhocListModel() {
	}

	public AdhocListModel(long travelclaimdetailsid, @NotBlank(message = "Please enter Date.") String date,
			@NotBlank(message = "Please enter from location.") String fromlocation,
			@NotBlank(message = "Please enter to location.") String tolocation,
			@Min(value = 1, message = "Please enter Transport Mode.") long transportmodeid, String transportmode,
			@Min(value = 1, message = "Please enter Claim Amount.") long claimamt,
			@NotBlank(message = "Please enter Travel Purpose.") String travelpurpose, long fromneareststationid,
			String fromneareststation, long toneareststationid, String toneareststation,
			@Min(value = 1, message = "Please enter Vehicle Used.") long vehicleusedid, String vehicleused,
			String attachments, double approveamt, String attachhidden, String filepres) {
		super();
		this.travelclaimdetailsid = travelclaimdetailsid;
		this.date = date;
		this.fromlocation = fromlocation;
		this.tolocation = tolocation;
		this.transportmodeid = transportmodeid;
		this.transportmode = transportmode;
		this.claimamt = claimamt;
		this.travelpurpose = travelpurpose;
		this.fromneareststationid = fromneareststationid;
		this.fromneareststation = fromneareststation;
		this.toneareststationid = toneareststationid;
		this.toneareststation = toneareststation;
		this.vehicleusedid = vehicleusedid;
		this.vehicleused = vehicleused;
		this.attachments = attachments;
		this.approveamt = approveamt;
		this.attachhidden = attachhidden;
		this.filepres = filepres;
	}

	public long getTravelclaimdetailsid() {
		return travelclaimdetailsid;
	}

	public void setTravelclaimdetailsid(long travelclaimdetailsid) {
		this.travelclaimdetailsid = travelclaimdetailsid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFromlocation() {
		return fromlocation;
	}

	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}

	public String getTolocation() {
		return tolocation;
	}

	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}

	public long getTransportmodeid() {
		return transportmodeid;
	}

	public void setTransportmodeid(long transportmodeid) {
		this.transportmodeid = transportmodeid;
	}

	public String getTransportmode() {
		return transportmode;
	}

	public void setTransportmode(String transportmode) {
		this.transportmode = transportmode;
	}

	public long getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(long claimamt) {
		this.claimamt = claimamt;
	}

	public String getTravelpurpose() {
		return travelpurpose;
	}

	public void setTravelpurpose(String travelpurpose) {
		this.travelpurpose = travelpurpose;
	}

	public long getFromneareststationid() {
		return fromneareststationid;
	}

	public void setFromneareststationid(long fromneareststationid) {
		this.fromneareststationid = fromneareststationid;
	}

	public String getFromneareststation() {
		return fromneareststation;
	}

	public void setFromneareststation(String fromneareststation) {
		this.fromneareststation = fromneareststation;
	}

	public long getToneareststationid() {
		return toneareststationid;
	}

	public void setToneareststationid(long toneareststationid) {
		this.toneareststationid = toneareststationid;
	}

	public String getToneareststation() {
		return toneareststation;
	}

	public void setToneareststation(String toneareststation) {
		this.toneareststation = toneareststation;
	}

	public long getVehicleusedid() {
		return vehicleusedid;
	}

	public void setVehicleusedid(long vehicleusedid) {
		this.vehicleusedid = vehicleusedid;
	}

	public String getVehicleused() {
		return vehicleused;
	}

	public void setVehicleused(String vehicleused) {
		this.vehicleused = vehicleused;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public double getApproveamt() {
		return approveamt;
	}

	public void setApproveamt(double approveamt) {
		this.approveamt = approveamt;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public String getFilepres() {
		return filepres;
	}

	public void setFilepres(String filepres) {
		this.filepres = filepres;
	}
	
	
	
}
