package in.co.srdt.unif.model.rembtransport;

import javax.validation.constraints.NotBlank;

public class TransportTravelHistory {
	
	private long historyid;
	@NotBlank(message= "Please select To Date.")
	private String todate;
	@NotBlank(message= "Please select From Date.")
	private String fromdate;
	@NotBlank(message= "Please enter Visited Place.")
    private String visitedplace;
    
	
	public TransportTravelHistory() {
		
	}


	public long getHistoryid() {
		return historyid;
	}


	public void setHistoryid(long historyid) {
		this.historyid = historyid;
	}


	public String getTodate() {
		return todate;
	}


	public void setTodate(String todate) {
		this.todate = todate;
	}


	public String getFromdate() {
		return fromdate;
	}


	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}


	public String getVisitedplace() {
		return visitedplace;
	}


	public void setVisitedplace(String visitedplace) {
		this.visitedplace = visitedplace;
	}


	@Override
	public String toString() {
		return "TransportTravelHistory [historyid=" + historyid + ", todate=" + todate + ", fromdate=" + fromdate
				+ ", visitedplace=" + visitedplace + "]";
	}
	

	
}
