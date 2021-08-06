package in.co.srdt.unif.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import in.co.srdt.unif.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.co.srdt.unif.model.absence.create.AbsenceTypeMaster;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.reimbcda.CdaVehicleSearch;
import in.co.srdt.unif.model.rembtelephone.TelephoneSearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
public class PageFragmentsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	
	@Autowired
	HttpHeaders httpHeaders;

	@Autowired
	ApplicationGateway apiGateway;
	
	@Autowired
	private ApplicationGateway appGateway;
	
	@RequestMapping("/uniftools/security/userprofiles/userprofiles")
	public String getUserProfile() {
		
		return "forms/searchUserProfile :: userProfileSearch";
	}
	
	@RequestMapping("/page/dashbordFregment")
	public String goToHome() {
		return "forms/dashboard :: dashboard";
	}
	@RequestMapping("/uniftools/security/userprofiles/userprofile/edit/userprofile/{loginId}/{emplId}/{emailId}")
	public String editUserProfile(@PathVariable("loginId") String loginId,
			@PathVariable("emplId") String emplId,
			@PathVariable("emailId") String emailId, Model model) {
		
		String searchUserId = loginId;
		String searchEmplId = emplId;
		String searchEmailId = emailId;
		String jsonStr = "";
		
		UserId[] userId = null;
		UserRoles[] roleId = null;
		
		//System.out.println("searchUserId"+searchUserId+"searchEmplId"+searchEmplId+"searchEmailId"+searchEmailId);
		
		String url = appGateway.getAppgatewaynbr()+"/api/userlogin/search";
		String getRolesByLoginIdUrl = appGateway.getAppgatewaynbr()+"/api/role/getrolesbyloginid/" + loginId;
		
		String payLode = "{" +
				"\"loginid\"" + ":\"" +searchUserId+ "\"," +
				"\"emplid\"" + ":\"" +searchEmplId+ "\"," +
				"\"emailid\"" + ":\"" +searchEmailId+ "\"" +
				"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<UserId[]> response = restTemplate.exchange(url, HttpMethod.POST, request, UserId[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			userId = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		long id=userId[0].getId();
		model.addAttribute("userDetails", userId);
		
		HttpEntity<String> getRolesByLoginIdRequest = new HttpEntity<String>(headers);
		ResponseEntity<UserRoles[]> getRolesByLoginIdResponse = restTemplate.exchange(getRolesByLoginIdUrl, HttpMethod.GET, getRolesByLoginIdRequest, UserRoles[].class);

		if (getRolesByLoginIdResponse.getStatusCode() == HttpStatus.OK) {
			roleId = getRolesByLoginIdResponse.getBody();
			//System.out.println(roleId[0].getRolename());
		} else {
			System.out.println("Request Failed");
			System.out.println(getRolesByLoginIdResponse.getStatusCode());
		}
		
		ObjectMapper Obj = new ObjectMapper();
		try {
			jsonStr = Obj.writeValueAsString(roleId);
			//System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("rolesDeatil", jsonStr);
		//System.out.println(roleId[0].getRolename());
		
		/************added by rajat on 01-12-20 start here********************************************/
		
		PersonInformation resp = new PersonInformation();
		String urlperson = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + loginId;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(urlperson, HttpMethod.GET, req, PersonInformation.class);
		resp = response1.getBody();
		model.addAttribute("personInfo", resp);
		
		/************added by rajat on 01-12-20 end here**********************************************/
		return "forms/editUserProfile :: userProfileEdit";
	}
	
	@RequestMapping("/uniftools/security/userprofiles/userprofiles/{searchUserId}")
	public String searchUserProfilePage(@PathVariable("searchUserId") String searchUserId, Model model) {
		model.addAttribute("searchUserId", searchUserId);
		return "forms/searchUserProfile :: userProfileSearch";
	}
	
	
	 @ResponseBody
	    @GetMapping("/uniftools/security/userprofiles/userprofiles/getuserRoles")
	    public UserRoles[] getUserRoles(Model model) {
		    UserRoles[] userRoles = null;
	        String userRolesURL =appGateway.getAppgatewaynbr()+ "/api/role/getroles";

	        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);

	        ResponseEntity<UserRoles[]> response = restTemplate.exchange(userRolesURL, HttpMethod.GET, request, UserRoles[].class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	        	userRoles = response.getBody();
	          
	        } else {
	            System.out.println("Request Failed");
	            System.out.println(response.getStatusCode());
	        }
	        return userRoles;
	    }
	 
	 @PostMapping("/uniftools/security/userprofiles/userprofiles/addroles")
	 
	 public @ResponseBody SingleResponseModel AddRoles(@ModelAttribute ("rolemapping") Rolemapping rolemapping, Long[] actionids,String userid, HttpServletRequest req)
	 {
		 ArrayList<Long> actionidlist = new ArrayList<Long>(Arrays.asList(actionids));
		   
		 Login login = (Login) req.getSession().getAttribute("login");
			PersonInformation personInformation = null;
			//String url = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
			//ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
			//		PersonInformation.class);

			/*if (responsePerso.getStatusCode() == HttpStatus.OK) {
				personInformation = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}*/
			
			
		 String createdby=login.getEmplid();
		 rolemapping.setCreatedby(createdby);
		 rolemapping.setActionids(actionidlist);
		 
		 String urlrole = appGateway.getAppgatewaynbr()+"/api/rolemap/rolemapping";			
			System.out.println("payload data ::"+rolemapping.toString());
		 SingleResponseModel res = new SingleResponseModel();
			try {			
				headers.setContentType(MediaType.APPLICATION_JSON);			
				HttpEntity<Rolemapping> request = new HttpEntity<Rolemapping>(rolemapping, headers);				
				ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlrole,HttpMethod.POST,request, SingleResponseModel.class);
				if(response.getStatusCode() == HttpStatus.OK) {
					res = response.getBody();
					System.out.println(res.getMessage());
				} else {
					System.out.println("Request Failed");
					System.out.println(response.getStatusCode());
				}
			
			}
			catch (Exception e){
				e.printStackTrace();
				res.setMessage("Failed to assign  roles .");
			}
			
		 
		
		 return res;
	 }
	 
	 
//	    @ResponseBody
//	    @GetMapping("/uniftools/security/userprofiles/userprofiles/getuserRoles")
//	    public Map<String, String> getUserRoles(Model model) {
//
//	        Map<String, String> res = new HashMap<>();
//	        //String employeeTableInfo = "";
//	        UserRoles[] userRoles = null;
//
//	        StringBuilder sb = new StringBuilder();
//
//	        String url  =appGateway.getAppgatewaynbr()+ "/api/role/getroles";
//
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//	        HttpEntity<String> request = new HttpEntity<String>(headers);
//	        ResponseEntity<UserRoles[]> response = restTemplate.exchange(url, HttpMethod.GET, request, UserRoles[].class);
//
//	        userRoles = response.getBody();
//
//	        if (userRoles.length > 0) {
//	            for (int i = 0; i < userRoles.length; i++) {
//	                System.out.println(userRoles[i].getRoleId());
//
//	                sb.append("<tr class='odd'>" +
//	                        "<td class='sorting_1'><input  type='checkbox'  class='w3-check commoncheckbox' name='roles' value='" + userRoles[i].getRoleId() + "' ></td>" +
//	                        "<td class='sorting_1'>" + userRoles[i].getRoleId() + "<input type='hidden' name='actionids' value='" + userRoles[i].getRoleId() + "'></td>" +
//	                        "<td class='sorting_1'>" + userRoles[i].getRolename() + "<input type='hidden' name='Rolemapping[" + i + "].personname' value='" + userRoles[i].getRolename() + "'></td>" +
//	                        
//	                        "</tr>");
//	            }
//	        } else {
//	            System.out.println("No Data");
//	        }
//	        res.put("message", sb.toString());
//	        return res;
//	    }
//	 
//	 
	 
	 
	 
	 
	 
	    @ResponseBody
		@RequestMapping(value = "/uniftools/security/userprofiles/userroles/loaduserRoles/{getUserId}")
		public UserRoles[] loadUserRoles(@PathVariable("getUserId") String getUserId,  Model model) {
			
	    	UserRoles[] userRoles = null;
			String urluser=appGateway.getAppgatewaynbr()+  "/api/role/getrolesbyloginid/" + getUserId;
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			ResponseEntity<UserRoles[]> response = restTemplate.exchange(urluser, HttpMethod.GET, request,
					UserRoles[].class);
			if (response.getStatusCode() == HttpStatus.OK) {
				userRoles = response.getBody();

			}
			
			return userRoles;
		}









	@ResponseBody
	@RequestMapping(value = "/uniftools/security/userprofiles/password/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String changePassword(HttpServletRequest requestFromDT, Model model) {

		String setmsg= "";

		String loginid = requestFromDT.getParameter("loginid");
		String oldpwd = "";
		String flag = "reset";
		String newpwd = requestFromDT.getParameter("confpass");

		String url = appGateway.getAppgatewaynbr()+"/api/userlogin/changepwd ";
		String payLode = "{" +

				"\"loginid\"" + ":\"" + loginid + "\"," +
				"\"oldpwd\"" + ":\"" + oldpwd + "\"," +
				"\"flag\""+ ":\"" + flag + "\"," +
				"\"newpwd\"" + ":\""+ newpwd + "\"" +
				"}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setmsg = response.getBody();
		}
		model.addAttribute("changemsg", setmsg);

		return setmsg;
	}


}
