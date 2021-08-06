package in.co.srdt.unif.model.reimbursementsetup;

import java.util.ArrayList;
import java.util.List;


public class RembursementTypeMaster {

	private long rembursementtypeid;
	
	private long actionid;

	private List<RembursementType> rembursementtype;

	private List<RembursementCeilingLimit> rembursementceilinglimit;

		
	public RembursementTypeMaster() {
		
		rembursementtype = new ArrayList<>();
		rembursementtype.add(new RembursementType());
		
		rembursementceilinglimit = new ArrayList<>();
		rembursementceilinglimit.add(new RembursementCeilingLimit());
	}


	public long getRembursementtypeid() {
		return rembursementtypeid;
	}


	public void setRembursementtypeid(long rembursementtypeid) {
		this.rembursementtypeid = rembursementtypeid;
	}


	public List<RembursementType> getRembursementtype() {
		return rembursementtype;
	}


	public void setRembursementtype(List<RembursementType> rembursementtype) {
		this.rembursementtype = rembursementtype;
	}


	public List<RembursementCeilingLimit> getRembursementceilinglimit() {
		return rembursementceilinglimit;
	}


	public void setRembursementceilinglimit(List<RembursementCeilingLimit> rembursementceilinglimit) {
		this.rembursementceilinglimit = rembursementceilinglimit;
	}
	

	public long getActionid() {
		return actionid;
	}


	public void setActionid(long actionid) {
		this.actionid = actionid;
	}


	@Override
	public String toString() {
		return "RembursementTypeMaster [rembursementtypeid=" + rembursementtypeid + ", actionid=" + actionid
				+ ", rembursementtype=" + rembursementtype + ", rembursementceilinglimit=" + rembursementceilinglimit
				+ "]";
	}

	
	
	
}
