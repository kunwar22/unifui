
package in.co.srdt.unif.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.UserId;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
public class SearchLocationController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	@ResponseBody
	@PostMapping(path="/searchLocation")
	public UserId[] getUsers(HttpServletRequest requestFromDT) {
		String searchUserId = requestFromDT.getParameter("userid");
		//String searchEmplId = requestFromDT.getParameter("emplid");
		//String searchEmailId = requestFromDT.getParameter("emailid");
		
		
		//System.out.println("Inside controller "+searchUserId);
		UserId[] userId = null;
		
		String url = appgateway.getAppgateway()+"/Location/locationSearchList";
		
		String payLode = "{" +
						"\"name\"" + ":\"" +searchUserId+ "\"," +
						"\"code\"" + ":\"" +""+ "\"," +
						"\"country\"" + ":\"" +""+ "\"" +
						"\"status\"" + ":\"" +""+ "\"" +
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
		//System.out.print("userId"+userId);
		return null;
	}

}
