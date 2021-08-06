package in.co.srdt.unif.controllers.approval;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.approval.ApprovalLOV;
import in.co.srdt.unif.model.approval.ApprovalSave;
import in.co.srdt.unif.model.approval.ApprovalSearch;
import in.co.srdt.unif.model.approval.ApprovalSetupMaster;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/approvalsetup")
public class ApprovalSetupController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	/************************************************************************
	 * Approval Setup Page starts
	 **********************************************/
	
	@RequestMapping("/manageapprovalsetup")
	public String approvalSetup(HttpSession session, HttpServletRequest request, Model model) 
	{
		return "fragments/approval/manageapprovalSetup :: manageapprovalSetup";
		//return "fragments/approval/approvalsetup :: approvalsetup";
	}
	
	@RequestMapping("/manageapprovalsetup/{module}")
	public String manageApprovalSetup(@PathVariable String module, Model model) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		model.addAttribute("module",module);
		CommonLOV[] reimburse_type = null;
		String url_reimburse_type = appgateway.getAppgateway() + "/General/loadReimbursementNameLOV";
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url_reimburse_type, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			reimburse_type = response.getBody();
		}
		/*for(int i=0; i<reimburse_type.length; i++) {
		System.out.println("REIMB TYPES :: "+reimburse_type[i]);
		}*/
		CommonLOV[] category = null;
		String url_category = appgateway.getAppgateway() + "/General/loadGender";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(url_category, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			category = response1.getBody();
		}
		
		/* LOADING ALL APPROVAL DATA STARTS -----------> ASMITA  */
		
		/* Loading Approvers start */
		
		ApprovalLOV[] sd = null;
		String approversurl = appgateway.getAppgateway()+"/Approval/getApprovalLabelLOV/AOR";// + category;
			//System.out.println("Approval URL  =============>  "+approversurl);

		ResponseEntity<ApprovalLOV[]> aprresponse = restTemplate.exchange(approversurl, HttpMethod.GET, request, ApprovalLOV[].class);

		if (aprresponse.getStatusCode() == HttpStatus.OK) {
			sd = aprresponse.getBody();
			//System.out.println("Showing Approvers =========> "+sd.toString());
			} else {
			System.out.println("Request Failed");
			System.out.println(aprresponse.getStatusCode());
		}
				
		model.addAttribute("approvers", sd);
		
		/* Loading Approvers start */		
		
		
		ApprovalSearch[] aprsrch = null;
		//String url_approvallist = appgateway.getAppgateway()+"/ApprovalDetails/getApprovalSetuplist";
		String url_approvallist = appgateway.getAppgateway()+"/ApprovalDetails/getApprovalSetuplistbyModuleName/"+module;
		ResponseEntity<ApprovalSearch[]> approvalresponse = restTemplate.exchange(url_approvallist,HttpMethod.GET,request,ApprovalSearch[].class);
		
		if(approvalresponse.getStatusCode() == HttpStatus.OK)
		{
			aprsrch = approvalresponse.getBody();
			//System.out.println("Approval Response ===============>   "+aprsrch.toString());
		}
		
		model.addAttribute("aprdata", aprsrch);
		
		/* LOADING ALL APPROVAL DATA ENDS -----------> ASMITA */
		
		
		model.addAttribute("category",category);
		model.addAttribute("reimburse_type",reimburse_type);
		
		return "fragments/approval/approvalsetup :: approvalsetup";
	}
	
	@ResponseBody
	@RequestMapping("/categorybind/{category}")
	public ApprovalLOV[] bindState(@PathVariable("category") String category, Model model) {

		//System.out.println("Inside Category Bind Controller ===>> "+category);
		ApprovalLOV[] sd = null;
		String url = appgateway.getAppgateway()+"/Approval/getApprovalLabelLOV/" + category;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<ApprovalLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, ApprovalLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		//System.out.println("Category"+sd);
		model.addAttribute("StateDetails", sd);

		return sd;

	}
	
	@RequestMapping(value = "/saveApproval", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel saveApproval(ApprovalSetupMaster approvalSetupMaster,Model model)
	{
		//System.out.println("approvalSetupMaster:: "+approvalSetupMaster.getApprovalsave().size());
		/*
		 * for(int i=0;i<approvalSetupMaster.getApprovalsave().size();i++) {
		 * System.out.println(approvalSetupMaster.getApprovalsave().get(i).toString());
		 * }
		 */
		SingleResponseModel res = new SingleResponseModel();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<List<ApprovalSave>> request = new HttpEntity<List<ApprovalSave>>(approvalSetupMaster.getApprovalsave(), headers);
			 String urlsave=appgateway.getAppgateway()+"/ApprovalDetails/saveandcorrectApprovalDetails";
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlsave,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			System.out.println("MESSAGE :::: "+res.getMessage());
			System.out.println("STATUS :::: "+res.getStatus());
			
			
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to save Approval.");
		}
		System.out.println(approvalSetupMaster.toString());
		model.addAttribute("result",res.getMessage());
		//return "fragments/approval/approvalsetup :: approvalsetup";
		return res;
		//return "Data Posted \n" + res.getMessage();
	}
	
	@RequestMapping(value="/deleteApproval/{approvalid}", method=RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel deleteApproval(@PathVariable("approvalid") long approvalid,Model model,HttpServletRequest request)
	{
		long aprid= approvalid;
		//System.out.println("Approval id====="+aprid);
		
		SingleResponseModel sresm = new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try
		{
			HttpEntity<ApprovalSave> aprrequest = new HttpEntity<ApprovalSave>(headers);
			
			String urlApprovalDelete = appgateway.getAppgateway()+"/ApprovalDetails/deleteApprovalSetupbyApprovalId/"+approvalid;
			//System.out.println("============ URL for deleting Data : "+urlApprovalDelete+" ===============");
			
			ResponseEntity<SingleResponseModel> delresponse = restTemplate.exchange(urlApprovalDelete,HttpMethod.DELETE,aprrequest,SingleResponseModel.class);
			
			if(delresponse.getStatusCode() == HttpStatus.OK)
			{
				sresm = delresponse.getBody();
				System.out.println("Response after delete ====> "+sresm.toString());
			}
			else
			{
				model.addAttribute("result", "LOG_ERROR");
				//System.out.println("Request Failed");
				//System.out.println(delresponse.getStatusCode());
			}
			
			//System.out.println("message ===> "+sresm.getMessage());
			//System.out.println("status ===> "+sresm.getStatus());
			
		}catch(Exception e)
		{
			e.printStackTrace();
			sresm.setMessage("Failed to delete Approval.");
		}
		model.addAttribute("result", sresm.getMessage());
		//return "fragments/approval/approvalsetup :: approvalsetup";
		//return "redirect:/approval/manageapprovalsetup";
		return sresm;
		//return sresm.getMessage();
	}
}
