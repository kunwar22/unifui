package in.co.srdt.unif.controllers.reimbursement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.reimbapprovalhistory.ApprovalHistory;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/approvalhistory")
public class ApprovalHistoryController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	

	@ResponseBody
	@PostMapping(path="/viewapprovalhistory")
	public ApprovalHistory[] getApprovalHistory(HttpServletRequest req) {
		
		
		String rid = req.getParameter("rid");
		String claimid = req.getParameter("claimid");
		
		ApprovalHistory[] approval = null;
		
		String urlappr = appgateway.getAppgateway()+"/ApprovalNotification/getApprovaleMsgAmt/"+rid+"/"+claimid;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<ApprovalHistory[]> response = restTemplate.exchange(urlappr, HttpMethod.GET, request, ApprovalHistory[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			approval = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return approval;
	}
	
	
	
	
	
	
	


}
