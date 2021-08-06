package in.co.srdt.unif.model.approval;

import java.util.ArrayList;
import java.util.List;



public class ApprovalSetupMaster {
	
	
	private List<ApprovalSave> approvalsave;
	
	public ApprovalSetupMaster() {
		approvalsave = new ArrayList<>();
		approvalsave.add(new ApprovalSave());
	}

	public List<ApprovalSave> getApprovalsave() {
		return approvalsave;
	}

	public void setApprovalsave(List<ApprovalSave> approvalsave) {
		this.approvalsave = approvalsave;
	}

	@Override
	public String toString() {
		return "ApprovalSetupMaster [approvalsave=" + approvalsave + "]";
	}
	
}