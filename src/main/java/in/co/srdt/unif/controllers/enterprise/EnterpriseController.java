package in.co.srdt.unif.controllers.enterprise;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.CreateEnterprise;
//import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class EnterpriseController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	@RequestMapping("/manageenterprise")
	public String createEnterprise(Model model) {
		
		//System.out.println("Inside Enterprise");
		
		CreateEnterprise createEnterprise=null;
		String urlgetEnterprise = appgateway.getAppgateway()+"Enterprise/getEnterpriseForView";
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<CreateEnterprise> response = restTemplate.exchange(urlgetEnterprise, HttpMethod.GET, request, CreateEnterprise.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			createEnterprise = response.getBody();
		} 
		
		model.addAttribute("enterprise", createEnterprise);		
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/create/createEnterprise :: createEnt";
	}
	
	@ResponseBody
	@PostMapping(value="/entpriseSaveCorrectUpdateLocation")
	public String setEnterprise(HttpServletRequest requestFromDT) {
		//System.out.println("Inside Enterprise Save/Correct/Update");
		String name = requestFromDT.getParameter("name");
		String location = requestFromDT.getParameter("location");
		String status = requestFromDT.getParameter("status");
		String effectStartDate = requestFromDT.getParameter("effectStartDate");
		String effectEndDate = requestFromDT.getParameter("effectEndDate");
		String enterpriseid = requestFromDT.getParameter("enterpriseid");
		String actionid = requestFromDT.getParameter("actionid");
		String savetype = requestFromDT.getParameter("savetype");
		
		String setEnterprise="";
		String urlEnterprise="";
		
		
		
		String payLode = "{" +
				"\"enterpriseid\"" + ":\"" +enterpriseid+ "\"," +
				"\"actionid\"" + ":\"" +actionid+ "\"," +
				"\"name\"" + ":\"" +name+ "\"," +
				"\"location\""  + ":\"" +location+ "\"," +
				"\"status\""  + ":\"" +status+ "\"," +				
				"\"effectstartdate\"" + ":\"" +effectStartDate+ "\"," +				
				"\"effectenddate\"" + ":\"" +effectEndDate+ "\"" +				
				"}";
		
		//System.out.println("PAYLOAD :: "+payLode);
		
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);		
		
		if(savetype.equals("Save")) {
		urlEnterprise = appgateway.getAppgateway()+"/Enterprise/saveEnterprise";
		ResponseEntity<String> responseSave = restTemplate.exchange(urlEnterprise, HttpMethod.POST, request, String.class);
		
		if(responseSave.getStatusCode() == HttpStatus.OK) {
			 setEnterprise = responseSave.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseSave.getStatusCode());
		}
		
		return setEnterprise;
		}
		
		else if(savetype.equals("Correct")) {
			urlEnterprise = appgateway.getAppgateway()+"/Enterprise/correctEnterprise";
		}
		else if(savetype.equals("Update")) {
			urlEnterprise = appgateway.getAppgateway()+"/Enterprise/updateEnterprise";
		}
	
		
		ResponseEntity<String> response = restTemplate.exchange(urlEnterprise, HttpMethod.PUT, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			 setEnterprise = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return setEnterprise;
		
	}
	
	
	
	
	
	
}
