package in.co.srdt.unif.controllers.roleassignment;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.UserRoles;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.dbi.DBICreate;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.roleassignment.AutoMappingCriteria;
import in.co.srdt.unif.model.roleassignment.AutoMappingRole;
import in.co.srdt.unif.model.roleassignment.UserAutoRoleMapping;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/manageroleassign")
public class RoleAssignment {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	SmartValidator validator;
	
//	Login userlogin = null;
	
	// ************************* opens role assignment search page ************************* //	
	
	@RequestMapping("/searchrolemapping")
	public String searchRoleMapping(/* HttpServletRequest request,*/Model model )
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		return "fragments/roleassignment/RoleAssignmentSearch :: RoleAssignmentSearch";
	}
	
	
	// ************************* role assignment search  ************************* //	
		@ResponseBody
		@RequestMapping("/getRoleMappingData/{mappingname}")
		public UserAutoRoleMapping[] getRoleMappingData(@PathVariable("mappingname") String mappingname, HttpServletRequest request,Model model )
		{
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			UserAutoRoleMapping[] userAutoRoleMapping = null;
			String payload = "";
			
			if(mappingname.equals("x"))
			{
				payload = "{\"mappingname\": \"\"}";
			}
			else
			{
				payload = "{\"mappingname\": \""+mappingname+"\"}";
			}
			System.out.println("Payload ::: "+payload);
			
			String rolesearch_url = appgateway.getAppgatewaynbr()+ "/unifoauth/autorolemap/searchAutoRoleMapping";
			
			HttpEntity<String> rolerequest = new HttpEntity<String>(payload,headers);
			
			ResponseEntity<UserAutoRoleMapping[]> role_response = restTemplate.exchange(rolesearch_url, HttpMethod.POST, rolerequest,UserAutoRoleMapping[].class);
			
			if (role_response.getStatusCode() == HttpStatus.OK) {
				userAutoRoleMapping = role_response.getBody();
			} else {
				System.out.println("Request Failed");
			}
			
			return userAutoRoleMapping;
		}
	
	// ************************* opens role assignment page ************************* //
	
	@RequestMapping("/assignrole")
	public String getRoleAssignmentPage(/* HttpServletRequest request,*/Model model )
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		/* LOADING OF Condition Type Starts 09/05/2021  */
		DBICreate[] conditiontype = null;

		HttpEntity<String> conditiontyperequest = new HttpEntity<String>(headers);
		String urlconditiontype =  appgateway.getAppgateway() + "/corehr/Dbi/loadAllTitle";

		ResponseEntity<DBICreate[]> responseconditiontype = restTemplate.exchange(urlconditiontype, HttpMethod.GET, conditiontyperequest,DBICreate[].class);
		if (responseconditiontype.getStatusCode() == HttpStatus.OK) {
			conditiontype = responseconditiontype.getBody();
		}

		System.out.println("cond===> "+conditiontype.toString());
		UserRoles[] usrroles = null;
        String userRolesURL =appgateway.getAppgatewaynbr()+ "/api/role/getroles";

        
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<UserRoles[]> response = restTemplate.exchange(userRolesURL, HttpMethod.GET, request, UserRoles[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	usrroles = response.getBody();
          
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        
        
        UserAutoRoleMapping userautorolemapping = new UserAutoRoleMapping();
        
        System.out.println("INSIDE CREATE PAGE FN ===> MODEL DATA ::: "+userautorolemapping.toString());
        
        model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		
		return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
	}
	
	
	// ************************* gets criteria values from selected criteria ************************* //
	
	@ResponseBody
    @RequestMapping("/getCriteriavals/{criteria}")
	public CommonLOV[] getCriteriavalues(Model model, HttpServletRequest req,@PathVariable("criteria") String criteria) {
        CommonLOV[] critValues = null;

        HttpEntity<String> request = new HttpEntity<String>(headers);
        String urlCriteriaVals = appgateway.getAppgateway()+"/Eligibility/Lov/loadEligibilityCriteriaByTitle/"+criteria;
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlCriteriaVals, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response8.getStatusCode() == HttpStatus.OK) {
            critValues = response8.getBody();
			
		}
        model.addAttribute("critValues", critValues);
      //  System.out.println("gender"+critValues[0].getDescription());
    return critValues;
    }
	
	// ************************* Saves data from role assignment page ************************* //
	
	@PostMapping(value = "/saveRoleMapping")
	public String saveRoleMapping(@ModelAttribute("userautorolemapping") UserAutoRoleMapping userautorolemapping,
			HttpServletRequest req, Model model, BindingResult bindingResult) 
	{
		
		Login login = (Login) req.getSession().getAttribute("login");
		
		userautorolemapping.setModifyby(login.getEmplid());
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		
		/*  LOADING DATA ON PAGE STARTS */
		
		/* LOADING OF Condition Type Starts 09/05/2021  */
		DBICreate[] conditiontype = null;

		HttpEntity<String> conditiontyperequest = new HttpEntity<String>(headers);
		String urlconditiontype =  appgateway.getAppgateway() + "/corehr/Dbi/loadAllTitle";

		ResponseEntity<DBICreate[]> responseconditiontype = restTemplate.exchange(urlconditiontype, HttpMethod.GET, conditiontyperequest,
				DBICreate[].class);
		if (responseconditiontype.getStatusCode() == HttpStatus.OK) {
			conditiontype = responseconditiontype.getBody();
		}

		
		UserRoles[] usrroles = null;
        String userRolesURL =appgateway.getAppgatewaynbr()+ "/api/role/getroles";

        
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<UserRoles[]> response = restTemplate.exchange(userRolesURL, HttpMethod.GET, request, UserRoles[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	usrroles = response.getBody();
          
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        
        
        
        model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());

		validator.validate(userautorolemapping, bindingResult);
		
		for (AutoMappingCriteria crit : userautorolemapping.getAutomappcriteria()) {
			 validator.validate(crit, bindingResult);
			}
		
		for (AutoMappingRole role : userautorolemapping.getAutomappingrole()) {
			 validator.validate(role, bindingResult);
			}
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		model.addAttribute("bindingResult", bindingResult);
		System.out.println("ALL ERRORS"+bindingResult.getAllErrors());
		if (bindingResult.hasErrors()) {
			System.out.println("TRUE");
			return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
		}
				
		String url_roleassignment = appgateway.getAppgatewaynbr() + "/unifoauth/autorolemap/saveAutoRoleMappingData";
		
		SingleResponseModel srem = null; 
		
		HttpEntity<UserAutoRoleMapping> rolerequest = new HttpEntity<UserAutoRoleMapping>(userautorolemapping, headers);

		System.out.println("MODEL DATA : " + userautorolemapping.toString());
		
		ResponseEntity<SingleResponseModel> roleresponse = restTemplate.exchange(url_roleassignment, HttpMethod.POST, rolerequest,
				SingleResponseModel.class);
		// System.out.println("Response status =====> " + response.getStatusCode());
		
		if (roleresponse.getStatusCode() == HttpStatus.OK) {
			srem = roleresponse.getBody();
			System.out.println("Response Body : " + userautorolemapping.toString());
			System.out.println("Success in save" + srem.getMessage());
		} else {
			System.out.println("Request Failed");
			System.out.println(roleresponse.getStatusCode());
//			model.addAttribute("finalresult", "LOG_ERROR");
			return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
		}
		
		model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		
		
		model.addAttribute("roleassignresult", srem.getMessage());
		
		return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
	}
	
	@RequestMapping("/editRoleMapping/{automappid}/{display_mode}")
	public String editRoleMapping(@PathVariable("automappid") long automappid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		/* LOADING OF Condition Type Starts 09/05/2021  */
		DBICreate[] conditiontype = null;
		
		String urlconditiontype =  appgateway.getAppgateway() + "/corehr/Dbi/loadAllTitle";

		ResponseEntity<DBICreate[]> responseconditiontype = restTemplate.exchange(urlconditiontype, HttpMethod.GET, request,
				DBICreate[].class);
		if (responseconditiontype.getStatusCode() == HttpStatus.OK) {
			conditiontype = responseconditiontype.getBody();
		}
		
		UserRoles[] usrroles = null;
        String userRolesURL =appgateway.getAppgatewaynbr()+ "/api/role/getroles";

        ResponseEntity<UserRoles[]> response = restTemplate.exchange(userRolesURL, HttpMethod.GET, request, UserRoles[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	usrroles = response.getBody();
          
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        
        
        UserAutoRoleMapping userautorolemapping = null;
        
        String urlusrrolemapng = appgateway.getAppgatewaynbr() + "/unifoauth/autorolemap/getAutoRoleMappingById/"+automappid;

		ResponseEntity<UserAutoRoleMapping> roleresponse = restTemplate.exchange(urlusrrolemapng,
				HttpMethod.GET, request, UserAutoRoleMapping.class);

		if (roleresponse.getStatusCode() == HttpStatus.OK) {
			userautorolemapping = roleresponse.getBody();
			System.out.println("data received :::" + userautorolemapping.toString());
		} else {
			System.out.println("Request Failed with response code : " + roleresponse.getStatusCode());
		}
		
		System.out.println("INSIDE EDIT FN ===> CRITERIA SIZE ::: "+userautorolemapping.getAutomappcriteria().size());
		
		System.out.println("INSIDE EDIT FN ===> ROLE SIZE ::: "+userautorolemapping.getAutomappingrole().size());
		
		System.out.println("INSIDE EDIT FN ===> MODEL DATA ::: "+userautorolemapping.toString());
				
        model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		
		model.addAttribute("display_mode", display_mode);
		
		return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
	}
	
	@PostMapping(value = "/updateRoleMapping")
	public String updateRoleMapping(@ModelAttribute("userautorolemapping") UserAutoRoleMapping userautorolemapping,
			HttpServletRequest req, Model model, BindingResult bindingResult) 
	{
		
		Login login = (Login) req.getSession().getAttribute("login");
		
		userautorolemapping.setModifyby(login.getEmplid());
		
		System.out.println("MODEL DATA in UPDATE B4 validation =====> "+userautorolemapping.toString());
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		
		/*  LOADING DATA ON PAGE STARTS */
		
		/* LOADING OF Condition Type Starts 09/05/2021  */
		DBICreate[] conditiontype = null;

		HttpEntity<String> conditiontyperequest = new HttpEntity<String>(headers);
		String urlconditiontype =  appgateway.getAppgateway() + "/corehr/Dbi/loadAllTitle";

		ResponseEntity<DBICreate[]> responseconditiontype = restTemplate.exchange(urlconditiontype, HttpMethod.GET, conditiontyperequest,
				DBICreate[].class);
		if (responseconditiontype.getStatusCode() == HttpStatus.OK) {
			conditiontype = responseconditiontype.getBody();
		}

		
		UserRoles[] usrroles = null;
        String userRolesURL =appgateway.getAppgatewaynbr()+ "/api/role/getroles";

        
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<UserRoles[]> response = restTemplate.exchange(userRolesURL, HttpMethod.GET, request, UserRoles[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	usrroles = response.getBody();
          
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        
        
        
        model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());

		validator.validate(userautorolemapping, bindingResult);
		
		for (AutoMappingCriteria crit : userautorolemapping.getAutomappcriteria()) {
			 validator.validate(crit, bindingResult);
			}
		
		for (AutoMappingRole role : userautorolemapping.getAutomappingrole()) {
			 validator.validate(role, bindingResult);
			}
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		model.addAttribute("bindingResult", bindingResult);
		System.out.println("ALL ERRORS"+bindingResult.getAllErrors());
		if (bindingResult.hasErrors()) {
			System.out.println("TRUE");
			return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
		}
				
		String url_roleassignmentupdate = appgateway.getAppgatewaynbr() + "/unifoauth/autorolemap/correctAutoRoleMappingData";
		
		SingleResponseModel srem = null; 
		
		HttpEntity<UserAutoRoleMapping> rolerequest = new HttpEntity<UserAutoRoleMapping>(userautorolemapping, headers);

		System.out.println("MODEL DATA in UPDATE AFTER validation =====> "+userautorolemapping.toString());
		
		ResponseEntity<SingleResponseModel> roleresponse = restTemplate.exchange(url_roleassignmentupdate, HttpMethod.POST, rolerequest,
				SingleResponseModel.class);
		// System.out.println("Response status =====> " + response.getStatusCode());
		
		if (roleresponse.getStatusCode() == HttpStatus.OK) {
			srem = roleresponse.getBody();
			System.out.println("Response Body : " + userautorolemapping.toString());
			System.out.println("Success in save" + srem.getMessage());
		} else {
			System.out.println("Request Failed");
			System.out.println(roleresponse.getStatusCode());
//			model.addAttribute("finalresult", "LOG_ERROR");
			return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
		}
		
		model.addAttribute("usrroles", usrroles);
        
		model.addAttribute("condtype", conditiontype);
		
		model.addAttribute("status",Status.values());
		
		model.addAttribute("userautorolemapping", userautorolemapping);
		
		
		model.addAttribute("roleassignresult", srem.getMessage());
		
		return "fragments/roleassignment/RoleAssignmentCreate :: RoleAssignmentCreate";
	}
	
	
	@RequestMapping("/removecriteria/{index}")
    public @ResponseBody String removeCriteria(@ModelAttribute("userautorolemapping") UserAutoRoleMapping userautorolemapping, @PathVariable("index") int index)
    {
        System.out.println("index : " + index);

        System.out.println("CRITERIA SIZE ::: "+userautorolemapping.getAutomappcriteria().size());
        
        System.out.println("MODEL DATA ::: "+userautorolemapping.toString());
        
		//userautorolemapping.getAutomappcriteria().remove(index);
            //System.out.println("removed");

        for(int z=0; z<userautorolemapping.getAutomappcriteria().size();z++){
            System.out.println(userautorolemapping.getAutomappcriteria().get(z).toString());
        }
        return "criteria removed";
    }
	
	@RequestMapping("/removerole/{index}")
    public @ResponseBody String removeRole(@ModelAttribute("userautorolemapping") UserAutoRoleMapping userautorolemapping, @PathVariable("index") int index)
    {
		System.out.println("index : " + index);

        System.out.println("ROLE SIZE ::: "+userautorolemapping.getAutomappingrole().size());
        
        System.out.println("MODEL DATA ::: "+userautorolemapping.toString());
        
		//userautorolemapping.getAutomappcriteria().remove(index);
            //System.out.println("removed");

        for(int z=0; z<userautorolemapping.getAutomappingrole().size();z++){
            System.out.println(userautorolemapping.getAutomappingrole().get(z).toString());
        }

//		userautorolemapping.getAutomappingrole().remove(index);
           
        return "role removed";
    }
	
}
