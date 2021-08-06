package in.co.srdt.unif.controllers.synchronize;

import java.util.List;

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
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.NotificationModelWrapper;
import in.co.srdt.unif.model.UserRoleMapping;
import in.co.srdt.unif.model.UserRoleMappingWrapper;
import in.co.srdt.unif.model.create.DoubleResponseModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.roleassignment.UserAutoRoleMapping;
import in.co.srdt.unif.model.synchronize.UserLogin;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/managesynchronization")
public class SynchronizeProcessController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	SmartValidator validator;
	
	@RequestMapping("/processSync")
	public String processSync(HttpServletRequest request,Model model )
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		
		UserAutoRoleMapping[] mappingname = null;
		
		String payload = "{\"mappingname\": \"\"}";
				
//		System.out.println("Payload ::: "+payload);
		
		String rolesearch_url = appgateway.getAppgatewaynbr()+ "/unifoauth/autorolemap/searchAutoRoleMapping";
		
		HttpEntity<String> rolerequest = new HttpEntity<String>(payload,headers);
		
		ResponseEntity<UserAutoRoleMapping[]> role_response = restTemplate.exchange(rolesearch_url, HttpMethod.POST, rolerequest,UserAutoRoleMapping[].class);
		
		if (role_response.getStatusCode() == HttpStatus.OK) {
			mappingname = role_response.getBody();
		} else {
			System.out.println("Request Failed");
		}
				
		model.addAttribute("mappingname", mappingname);
				
		
		/* ====================== LOGIN SYNC GET =============================== */
		
		Login login = (Login) request.getSession().getAttribute("login");
		
		UserLogin[] userdetails = null;
		
		String usersearch_url = appgateway.getAppgateway()+ "/api/report/loadUserSyncLogins";
		
		HttpEntity<String> usrrequest = new HttpEntity<String>(payload,headers);
		
		ResponseEntity<UserLogin[]> usr_response = restTemplate.exchange(usersearch_url, HttpMethod.GET, usrrequest,UserLogin[].class);
		
		if (usr_response.getStatusCode() == HttpStatus.OK) {
			userdetails = usr_response.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		model.addAttribute("userdetails", userdetails);
		model.addAttribute("createdby", login.getEmplid());
		
		
		
		return "fragments/synchronize/Synchome :: Synchome";
	}
		
	@ResponseBody
	@RequestMapping("/syncRole/{mapname}")
	public UserRoleMapping[] syncRole(@PathVariable("mapname") String mapname,HttpServletRequest request)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		
		DoubleResponseModel rolesync = null;
		
		String payload = "{\"mappingname\" : \""+mapname+"\"}";
		
//		System.out.println("Payload ::: "+payload);
		String urlsyncRole = appgateway.getAppgateway() +"/api/report/mappingRoleToPerson";		
		HttpEntity<String> syncrolerequest = new HttpEntity<String>(payload,headers);		
		ResponseEntity<DoubleResponseModel> syncrole_response = restTemplate.exchange(urlsyncRole, HttpMethod.POST, syncrolerequest,DoubleResponseModel.class);
		UserRoleMapping[] userrolemapping = null;
		if (syncrole_response.getStatusCode() == HttpStatus.OK) {
			rolesync = syncrole_response.getBody();			
			
			String allotedrole_url = appgateway.getAppgatewaynbr()+ "/unifoauth/autorolemap/getAllUserRolesAuto";
			
			HttpEntity<String> allotedrolerequest = new HttpEntity<String>(headers);
			
			ResponseEntity<UserRoleMapping[]> allotedrole_response = restTemplate.exchange(allotedrole_url, HttpMethod.GET, allotedrolerequest,UserRoleMapping[].class);
			
			if (allotedrole_response.getStatusCode() == HttpStatus.OK) {
				userrolemapping = allotedrole_response.getBody();
				
			} else {
				System.out.println("Request Failed");
			}
					
			return userrolemapping;
			
		} else {
			System.out.println("Request Failed");
		}
		
		return userrolemapping;
		
	}
	
	@RequestMapping(value="/submitRoles", method = RequestMethod.POST)
	 public @ResponseBody String SaveRoles(@ModelAttribute("userrolemapping") UserRoleMappingWrapper userrolemapping,HttpServletRequest requestt,Model model)
	 {
		 Login login = (Login) requestt.getSession().getAttribute("login");
		for (int i = 0; i < userrolemapping.getCreateUserRole().size(); i++) {
		      System.out.println("count::: " + i);

		      userrolemapping.getCreateUserRole().get(i).setCreatedby(login.getEmplid());
		}
		 SingleResponseModel res = new SingleResponseModel();
		 
		 String rolesurl = appgateway.getAppgatewaynbr() + "/unifoauth/autorolemap/autoRoleMappingCreate";
		 HttpEntity<List<UserRoleMapping>> request = new HttpEntity<List<UserRoleMapping>>(userrolemapping.getCreateUserRole(), headers);
		 
		 ResponseEntity<SingleResponseModel> postrole = restTemplate.exchange(rolesurl, HttpMethod.POST, request,
					SingleResponseModel.class);
			if (postrole.getStatusCode() == HttpStatus.OK) {
				res = postrole.getBody();
			} else {
				System.out.println("Request Failed");
			}
			
			return res.getMessage();
	 }
	
	@ResponseBody
	@RequestMapping("/syncLogin/{id}/{loginid}/{emplid}/{emailid}/{createdby}")
	public SingleResponseModel[] syncLogin(@PathVariable("id") String[] id,@PathVariable("loginid") String[] loginid, 
			@PathVariable("emplid") String[] emplid, @PathVariable("emailid") String[] emailid, @PathVariable("createdby") String[] createdby)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		
		SingleResponseModel[] usrloginsync = null;
		
		String urlsyncusrlogin = appgateway.getAppgatewaynbr() +"api/userlogin/syncloginwithdefultrole";
		
		String payload = "[";
		
		for(int i=0;i<id.length;i++)
		{
			if(id.length == 1)
			{
				payload+="{\"id\": "+id[i]+","
						+"\"loginid\": \""+loginid[i]+"\","
						+"\"emplid\": \""+emplid[i]+"\","
						+"\"emailid\": \""+emailid[i]+"\","
						+"\"createdby\": \""+createdby[i]+"\"}";
			}
			else
			{
				payload+="{\"id\": "+id[i]+","
						+"\"loginid\": \""+loginid[i]+"\","
						+"\"emplid\": \""+emplid[i]+"\","
						+"\"emailid\": \""+emailid[i]+"\","
						+"\"createdby\": \""+createdby[i]+"\"},";
			}
			
		}
		
		int payldlen = payload.length();
		if(payload.charAt(payldlen-1) == ',')
		{
			payload = payload.substring(0, payldlen-1);
		}
		
		payload+="]"; 
		
		
		
		HttpEntity<String> usrloginsyncrequest = new HttpEntity<String>(payload,headers);
		
		ResponseEntity<SingleResponseModel[]> usrLoginSync_response = restTemplate.exchange(urlsyncusrlogin, HttpMethod.POST, usrloginsyncrequest,SingleResponseModel[].class);
		
		if (usrLoginSync_response.getStatusCode() == HttpStatus.OK) {
			usrloginsync = usrLoginSync_response.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		return usrloginsync;
		
	}
}
