package in.co.srdt.unif.controllers.itadmin;


import in.co.srdt.unif.model.search.PersonRecordSearch;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/managePersaon")
public class ChangePsdController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	ChangePsdController(){

	}

	ChangePsdController(HttpHeaders headers, RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	@RequestMapping("/changePassword")
	public String managePersonalRecord(Model model) {

		return "fragments/itadmin/changePassword :: searchPassword";
	}
	
	
	@ResponseBody
	@PostMapping(path="/searchPersonal/getpersonalChangePassword")
	public PersonRecordSearch[] personRecordSearchs(HttpServletRequest requestFromDT) {
		
		
		String PERSON_DEPT = requestFromDT.getParameter("PERSON_DEPT");
//		String PERSON_ID = requestFromDT.getParameter("PERSON_ID");
		String PERSON_LEGAL_ENTITY = requestFromDT.getParameter("PERSON_LEGAL_ENTITY");
		String PERSON_NAME = requestFromDT.getParameter("PERSON_NAME");
		String PERSON_NUMBER = requestFromDT.getParameter("PERSON_NUMBER");
		String PERSON_ID="";
		//System.out.print("\n hi searchPersonalRecord"+PERSON_DEPT+" "+PERSON_LEGAL_ENTITY+" "+PERSON_NAME+" "+  PERSON_NUMBER+"  END");
		
		PersonRecordSearch[] personRecordSearchs = null;
		
		String urlPerson = appgateway.getAppgateway()+"/PersonData/PersonalRecord/getPersonalRecordBySearch";
		
		
		
		String payLode = "{" +
						"\"persondepartment\"" + ":\"" +PERSON_DEPT+ "\"," +
						"\"personlegalentity\"" + ":\"" +PERSON_LEGAL_ENTITY+ "\"," +
						"\"personid\"" + ":\"" +PERSON_ID+ "\"," +
						"\"personname\"" + ":\"" +PERSON_NAME+ "\"," +
						"\"personnumber\"" + ":\"" +PERSON_NUMBER+ "\"" +
						"}";
		
		//System.out.println("\nin "+urlPerson +"payLode" +payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<PersonRecordSearch[]> response = restTemplate.exchange(urlPerson, HttpMethod.POST, request, PersonRecordSearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			personRecordSearchs = response.getBody();
			//System.out.println(personRecordSearchs.toString());
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return personRecordSearchs;
	}

}
