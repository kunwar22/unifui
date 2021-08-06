package in.co.srdt.unif.controllers.mss;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.NotificationModelWrapper;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.mss.ApprovalActions;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.utils.ApplicationGateway;



@Controller
@RequestMapping("/mss")
public class MSSController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;


	
	@RequestMapping("/manageapprovalactions")
	public String manageapprovalactions(Model model,HttpServletRequest request) {
		
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<String> req =  new HttpEntity<>(headers); 
		 
		 CommonLOV[] reimbursementType= null ;
		 String lovurl=appgateway.getAppgateway() + "/General/loadReimbursementNameLOV";
		 ResponseEntity<CommonLOV[]> responseLov=restTemplate.exchange(lovurl, HttpMethod.GET, req, CommonLOV[].class);
		 if(responseLov.getStatusCode()==HttpStatus.OK) {
			 reimbursementType=responseLov.getBody();
		 }
		
		 model.addAttribute("rembtype", reimbursementType);
		 //List<CommonLOV> reimbrs = Arrays.asList(reimbursementType).stream().filter(x-> (x.id != Long.parseLong("16"))&&(x.id != Long.parseLong("17"))).collect(Collectors.toList());
		 
		 //model.addAttribute("rembtype", reimbrs);
		 
		 String businessUnitPayload = "{" + "  \"code\": \"\"," + "  \"name\": \"\","
					+ "  \"status\": \"Active\"" + "}";
			
			String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
			
			BUsearchresult[] busunit = null;

			HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);

			ResponseEntity<BUsearchresult[]> busunitres = restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq,
					BUsearchresult[].class);

			if (busunitres.getStatusCode() == HttpStatus.OK) {
				busunit = busunitres.getBody();
			} else {
				System.out.println("Request Failed");
			}
			
			

			model.addAttribute("busunit", busunit);		 
		 
		return "fragments/mss/approvalactions :: approvalactions";
	}
	
	
	 @ResponseBody
	 @RequestMapping(value = "/getapprovalactions/{remtype}/{action}/{fromdate}/{todate}/{buid}", method = RequestMethod.GET)
	 public ApprovalActions[] getapprovalactions(HttpServletRequest requestt, @PathVariable("remtype")String remtype,@PathVariable("action")String action,@PathVariable("fromdate")String fromdate,@PathVariable("todate")String todate,@PathVariable("buid")String buid) {
		 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 ApprovalActions[] res =null;
		 Login login = (Login) requestt.getSession().getAttribute("login");
//		 String url = appgateway.getAppgateway()+"/ApprovalNotification/loadBualkApprovalDataBySearch/"+remtype+"/"+action+"/"+fromdate+"/"+todate+"/"+login.getEmplid()+"/"+buid;
		 
		 String url = appgateway.getAppgateway()+"/ApprovalNotification/loadBualkApprovalDataBySearch";

		 String payload ="" ;
		 
		 if(fromdate.equals("x") && todate.equals("x"))
		 {
			 payload = "{\"approverpersonnumber\": \""+login.getEmplid()+"\","
				 		+ "  \"buid\": "+buid+","
				 		+ "  \"fromdate\": \"\","
				 		+ "  \"moduleid\": "+remtype+","
				 		+ "  \"status\": \""+action+"\","
				 		+ "  \"todate\": \"\""
				 		+ "}";
		 }
		 else {
			 payload = "{\"approverpersonnumber\": \""+login.getEmplid()+"\","
				 		+ "  \"buid\": "+buid+","
				 		+ "  \"fromdate\": \""+fromdate+"\","
				 		+ "  \"moduleid\": "+remtype+","
				 		+ "  \"status\": \""+action+"\","
				 		+ "  \"todate\": \""+todate+"\""
				 		+ "}";
		 }
			 
		  
//		 System.out.println("Payload ==> "+payload);
		 
//		System.out.println("mss url"+url);
			HttpEntity<String> req = new HttpEntity<String>(payload,headers);

			ResponseEntity<ApprovalActions[]> response = restTemplate.exchange(url, HttpMethod.POST, req, ApprovalActions[].class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				System.out.println(response.getStatusCode());
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		return res;
	}
	 
	 
@RequestMapping(value="/submittedApprovalData/{status}", method = RequestMethod.POST)
	 
	 public @ResponseBody SingleResponseModel AddRoles( @ModelAttribute("notificationModelWrapper") NotificationModelWrapper notifWrapper  , HttpServletRequest requestt,Model model,@PathVariable("status") String status)
	 {
	//	 ArrayList<Long> actionidlist = new ArrayList<Long>(Arrays.asList(claimid));
		
		 SingleResponseModel res = new SingleResponseModel();
		 
		 
		 for(int i=0;i<notifWrapper.getNotificationModels().size();i++) {			
				
			 notifWrapper.getNotificationModels().get(i).setStatus(status);				
			}
		 
		     System.out.println("Data::"+notifWrapper.getNotificationModels().toString());
			String approvedurl = appgateway.getAppgateway() + "/Notification/submittedAprovelList";
			System.out.println("URL ::"+approvedurl);
			HttpEntity<List<NotificationModel>> request = new HttpEntity<List<NotificationModel>>(notifWrapper.getNotificationModels(), headers);
			ResponseEntity<SingleResponseModel> postapprovel = restTemplate.exchange(approvedurl, HttpMethod.POST, request,
					SingleResponseModel.class);
			if (postapprovel.getStatusCode() == HttpStatus.OK) {
				res = postapprovel.getBody();
			} else {
				System.out.println("Request Failed");
			}

			model.addAttribute("status"+status);

			
		 return res;
	 }




}
