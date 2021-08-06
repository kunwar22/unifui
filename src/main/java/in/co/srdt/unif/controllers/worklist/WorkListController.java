package in.co.srdt.unif.controllers.worklist;

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

import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.worklist.NotificationWorklistModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/tasks")
public class WorkListController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	Login userlogin = null;
	
	@RequestMapping("/myWorklist")
	public String getMyWorkList(HttpServletRequest request,Model model)
	{
		
		return "fragments/worklist/MyWorklist :: MyWorklist";
	}
	
	@RequestMapping("/completedWorklist")
	public String getCompletedWorkList(HttpServletRequest request,Model model)
	{
//		NotificationWorklistModel notificationWorklistModel = new NotificationWorklistModel();
//		System.out.println("FROM DATE :::: "+notificationWorklistModel.getFromdate());
//		System.out.println("TO DATE :::: "+notificationWorklistModel.getTodate());
//		model.addAttribute("notificationWorklistModel", notificationWorklistModel);
		
		return "fragments/worklist/CompletedWorklist :: CompletedWorklist";
	}
	
	@RequestMapping("/pendingWorklist")
	public String getPendingWorkList(HttpServletRequest request,Model model)
	{
//		NotificationWorklistModel notificationWorklistModel = new NotificationWorklistModel();
//		System.out.println("FROM DATE :::: "+notificationWorklistModel.getFromdate());
//		System.out.println("TO DATE :::: "+notificationWorklistModel.getTodate());
//		model.addAttribute("notificationWorklistModel", notificationWorklistModel);
		return "fragments/worklist/PendingWorklist :: PendingWorklist";
	}
	
	@ResponseBody
	@RequestMapping(value="/getWorklistTasks/{fdt}/{status}/{tdt}",method=RequestMethod.GET)
	public NotificationWorklistModel[] getPendingWorklist(@PathVariable("fdt") String fdt,@PathVariable("status") String status,@PathVariable("tdt") String tdt,HttpServletRequest request,Model model) {
	  String returnurl="";
	  headers.setContentType(MediaType.APPLICATION_JSON);
	  
	  userlogin = (Login) request.getSession().getAttribute("login");
	  
	  String worklist_url = appgateway.getAppgateway()+"/Notification/getWorkListNotificationByApprover/"+fdt+"/"+tdt+"/"+status+"/"+userlogin.getEmplid();
	  
		/* System.out.println("URL==============> "+worklist_url); */
	  
	  NotificationWorklistModel wrkList[] = null;
		/* HttpEntity<String> wrkList_request = new HttpEntity<String>(headers); */
	  HttpEntity<String> wrkList_request = new HttpEntity<>(headers);
	  
	  ResponseEntity<NotificationWorklistModel[]> wrklist_response = restTemplate.exchange(worklist_url, HttpMethod.GET,wrkList_request,NotificationWorklistModel[].class);
		if(wrklist_response.getStatusCode() == HttpStatus.OK)
		{
			wrkList = wrklist_response.getBody();
//				System.out.println("NOTIFICATION RESPONSE ============ > "+wrklist_response.getBody());
		}
		else
		{
			System.out.println("Request Failed");
			System.out.println(wrklist_response.getStatusCode());
		}
		//model.addAttribute("worklist", wrkList);
//		if(status.equals("Unread"))
//		{
//			returnurl="fragments/worklist/PendingWorklist :: PendingWorklist";
//		}
//		else if(status.equals("Read"))
//		{
//			returnurl="fragments/worklist/CompletedWorklist :: CompletedWorklist";
//		}
		return wrkList;
  }
	 
}
