package in.co.srdt.unif.controllers.Miscellaneous;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.Eligibility.Eligibility;
import in.co.srdt.unif.model.Miscellaneous.RepeatingPeriod;
import in.co.srdt.unif.model.Miscellaneous.RepeatingPeriodSearch;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;


@Controller
@RequestMapping("/repeatingperiod")
public class RepeatingperiodController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private HttpHeaders headers;
    
    @Autowired
	SmartValidator validator;

    @RequestMapping("/manageRepeatingperiod")
    public String manageRepeating(Model model, @ModelAttribute("eligibilitydetails") Eligibility eligibilitydetails, HttpServletRequest req) {

       // System.out.println("Inside Repeating Period Controller");
        model.addAttribute("status", Status.values());
        return "fragments/Miscellaneous/manageRepeatingperiod :: manageRepeatingperiod";
    }
    
   

    @RequestMapping("/createRepeatingperiod")
    public String createRepeating(Model model, HttpServletRequest req) {
    	   
    	   model.addAttribute("repeating", new RepeatingPeriod());	
    	   model.addAttribute("response",new SingleResponseModel());
    	
    	 //  System.out.println("Inside Create");
   		
		  	CommonLOV[] loadPeriodLength=null;
		  	CommonLOV[] loadPeriodType=null;
			
			String urlPeriodLength = appgateway.getAppgatewayabs()+"/General/loadPeriodLength";
			String urlPeriodType = appgateway.getAppgatewayabs()+"/General/loadPeriodType";
			
			
			headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);	
	        
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlPeriodLength, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlPeriodType, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				loadPeriodLength = response.getBody();
				
			} 
			if(response1.getStatusCode() == HttpStatus.OK) {
				loadPeriodType = response1.getBody();
				
			} 
			
	
		model.addAttribute("loadPeriodLength", loadPeriodLength);			
		model.addAttribute("loadPeriodType", loadPeriodType);
		model.addAttribute("status", Status.values());
    	

        return "fragments/Miscellaneous/repeatingperiod :: createrepeatingperiod";
    }
    
    
    @RequestMapping(value = "/saveRepeating", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveRepeating(RepeatingPeriod repeatingPeriod, BindingResult bindingResult, Model model)
	{
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			validator.validate(repeatingPeriod, bindingResult);
			
			CommonLOV[] loadPeriodLength=null;
		  	CommonLOV[] loadPeriodType=null;
			
			String urlPeriodLength = appgateway.getAppgatewayabs()+"/General/loadPeriodLength";
			String urlPeriodType = appgateway.getAppgatewayabs()+"/General/loadPeriodType";
			
			
			headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);	
	        
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlPeriodLength, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlPeriodType, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				loadPeriodLength = response.getBody();
				
			} 
			if(response1.getStatusCode() == HttpStatus.OK) {
				loadPeriodType = response1.getBody();
				
			} 
			
	
			model.addAttribute("loadPeriodLength", loadPeriodLength);			
			model.addAttribute("loadPeriodType", loadPeriodType);
			model.addAttribute("status", Status.values());
			model.addAttribute("repeating", repeatingPeriod);
			
			//System.out.println(bindingResult.getAllErrors());
			
			model.addAttribute("bindingResult", bindingResult);
			if (bindingResult.hasErrors()) {
				return "fragments/Miscellaneous/repeatingperiod :: createrepeatingperiod";
			}
			
			//System.out.println("Repeating Save ::"+ repeatingPeriod.toString());
			
			HttpEntity<RepeatingPeriod> request1 = new HttpEntity<RepeatingPeriod>(repeatingPeriod, headers);
			
			ResponseEntity<SingleResponseModel> response2= restTemplate.exchange(appgateway.getAppgatewayabs()+"/RepeatingPeriod/saveRepeatingPeriod",HttpMethod.POST,request1, SingleResponseModel.class);
			if(response2.getStatusCode() == HttpStatus.OK) {
				res = response2.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response2.getStatusCode());
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Repeating .");
		}
		model.addAttribute("response",res);
		return "fragments/Miscellaneous/repeatingperiod :: createrepeatingperiod";
	}
    
    
    
    @ResponseBody
	@PostMapping(path="/searchRepeating/getRepeatingId")
	public RepeatingPeriodSearch[] repeatingPeriodSearchs(HttpServletRequest requestFromDT) {
		
    	// System.out.println("Inside Search");
		String name = requestFromDT.getParameter("name");		
		String code = requestFromDT.getParameter("code");
		String status = requestFromDT.getParameter("status");
		
		RepeatingPeriodSearch[] repeatingPeriodSearchs = null;
		
		String urlRepeating = appgateway.getAppgatewayabs()+"/RepeatingPeriod/RepeatingPeriodSearchList";
		
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"code\"" + ":\"" +code+ "\"," +
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		//System.out.println("Search Payload ::"+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<RepeatingPeriodSearch[]> response = restTemplate.exchange(urlRepeating, HttpMethod.POST, request, RepeatingPeriodSearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			repeatingPeriodSearchs = response.getBody();	
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		
		
		return repeatingPeriodSearchs;
	}
    
}