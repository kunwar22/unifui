package in.co.srdt.unif.controllers.emailconfiguration;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.emailconfig.EmailConfigurationModel;
import in.co.srdt.unif.model.emailconfig.EmailConfigurationSearchModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembtadk.TADKModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/email")
public class EmailConfigurationController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appGateway;

	@Autowired
	SmartValidator validator;
	
	Login userlogin = null;
	
	
	@RequestMapping(value="/searchConfigEmail",method =RequestMethod.GET)
	public String searchEmailConfiguration(Model model,HttpServletRequest request) {
		userlogin = (Login) request.getSession().getAttribute("login");
		model.addAttribute("createdby",userlogin.getEmplid());
		model.addAttribute("updatedby",userlogin.getEmplid());
		
		return "fragments/emailConfiguration/emailConfigSearch :: emailConfigSearch";
	}
	
	@RequestMapping(value="/configEmail",method =RequestMethod.GET)
	public String configureEmail(@ModelAttribute("emailConfigs") EmailConfigurationModel emailConfigs ,Model model,HttpServletRequest request) {
		userlogin = (Login) request.getSession().getAttribute("login");
		
		model.addAttribute("createdby",userlogin.getEmplid());
		model.addAttribute("updatedby",userlogin.getEmplid());
		model.addAttribute("status",Status.values());
		model.addAttribute("emailConfigs",emailConfigs);
		return "fragments/emailConfiguration/emailConfig :: emailConfig";
	}
	
	@RequestMapping(value="/saveEmailConfigs",method=RequestMethod.POST)
	public String saveEmailConfiguration(@ModelAttribute("emailConfigs") EmailConfigurationModel emailConfigs, BindingResult bindingResult,HttpServletRequest request,Model model)
	{
		userlogin = (Login) request.getSession().getAttribute("login");
		
		validator.validate(emailConfigs, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
//		model.addAttribute("createdby",userlogin.getEmplid());
//		model.addAttribute("updatedby",userlogin.getEmplid());
		model.addAttribute("status",Status.values());
		model.addAttribute("emailConfigs",emailConfigs);
		//model.addAttribute("emailresult", "Failed");

		if (bindingResult.hasErrors()) {
			System.out.println("in error ===> "+bindingResult.getFieldErrors());
			//model.addAttribute("result","Validation Failed");
			return "fragments/emailConfiguration/emailConfig :: emailConfig";
		}
		
		
		String url_emailconfig = appGateway.getAppgateway() +"/EmailConfiguration/saveAndCorrectEmailConfiguration"; 
		
		SingleResponseModel srespmodel = null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<EmailConfigurationModel> email_config_request = new HttpEntity<EmailConfigurationModel>(emailConfigs, headers);
		
		ResponseEntity<SingleResponseModel> email_config_response= restTemplate.exchange(url_emailconfig,HttpMethod.POST,email_config_request, SingleResponseModel.class);
		
		if(email_config_response.getStatusCode() == HttpStatus.OK) {
			srespmodel = email_config_response.getBody();
			System.out.println("response DATA ::"+ emailConfigs.toString());
			System.out.println("response status ::"+ srespmodel.getStatus());
		} else {
			System.out.println("Request Failed");
			System.out.println(email_config_response.getStatusCode());
		}
		
		/* model.addAttribute("createdby",userlogin.getEmplid()); */
		
//		model.addAttribute("createdby",userlogin.getEmplid());
//		model.addAttribute("updatedby",userlogin.getEmplid());
		model.addAttribute("status",Status.values());
		model.addAttribute("emailConfigs",emailConfigs);
		model.addAttribute("emailresult", srespmodel.getStatus());
		
		return "fragments/emailConfiguration/emailConfigSearch :: emailConfigSearch";
	}
	
	@ResponseBody
	@RequestMapping("/getEmailConfigs")
	public EmailConfigurationSearchModel[] emailConfigSearchModel(HttpSession session, HttpServletRequest request, Model model)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		EmailConfigurationSearchModel[] emailconfigsearch=null;
		
		String urlemailconfigSearch = appGateway.getAppgateway()+ "/EmailConfiguration/getAllEmailConfiguration";
		
		HttpEntity<String> email_search_request = new HttpEntity<String>(headers);
		
		ResponseEntity<EmailConfigurationSearchModel[]> email_config_response = restTemplate.exchange(urlemailconfigSearch,
				HttpMethod.GET, email_search_request, EmailConfigurationSearchModel[].class);
		
		if (email_config_response.getStatusCode() == HttpStatus.OK) {
			emailconfigsearch = email_config_response.getBody();
//			System.out.println("Email Config Search Model : " + email_config_response.getBody());
//			System.out.println("Response Status ::: "+email_config_response.getStatusCode());
		}
		else
		{
			System.out.println(":::: Error in getting response ::::");
		}
		model.addAttribute("createdby",userlogin.getEmplid());
		model.addAttribute("updatedby",userlogin.getEmplid());
		return emailconfigsearch;
	}
	
	@RequestMapping("/editEmailConfig/{configid}/{display_mode}")
	public String editEmailConfiguration(@PathVariable("configid") int configid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest request) 
	{
		System.out.println("Config ID : " + configid);
		System.out.println("Display Mode : " + display_mode);
		
		model.addAttribute("createdby",userlogin.getEmplid());
		model.addAttribute("updatedby",userlogin.getEmplid());
		
		userlogin = (Login) request.getSession().getAttribute("login");
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		EmailConfigurationModel emailConfigs = null;
		
		String urlemailconfigdetails = appGateway.getAppgateway() + "/EmailConfiguration/getEmailConfigurationEditById/" + configid;

		HttpEntity<String> get_emailconfig_request = new HttpEntity<String>(headers);

		ResponseEntity<EmailConfigurationModel> emailconfigresponse = restTemplate.exchange(urlemailconfigdetails, HttpMethod.GET, get_emailconfig_request,
				EmailConfigurationModel.class);

		if (emailconfigresponse.getStatusCode() == HttpStatus.OK) {
			emailConfigs = emailconfigresponse.getBody();
			System.out.println("Email Config data::" + emailConfigs);
		} else {
			System.out.println("Request Failed with response code : " + emailconfigresponse.getStatusCode());
		}
		
		model.addAttribute("status",Status.values());
		model.addAttribute("createdby",userlogin.getEmplid());
		model.addAttribute("updatedby",userlogin.getEmplid());
		model.addAttribute("emailConfigs",emailConfigs);
		model.addAttribute("display_mode", display_mode);
		
		return "fragments/emailConfiguration/emailConfig :: emailConfig";
	}
}
	
	
	
