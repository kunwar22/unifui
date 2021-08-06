package in.co.srdt.unif.controllers.enterprise;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateJurisdictions;
import in.co.srdt.unif.model.search.JurisdictionSearch;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class JurisdictionsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	
	@RequestMapping("/managelegaljurisdiction")
	public String managejurisdiction(Model model) {
		
		//System.out.println("Inside Jurisdiction");		
		CommonLOV[] cd=null;
        
        
        String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
		} 
		
		model.addAttribute("CountryDetails", cd);        
		model.addAttribute("status", Status.values());
		model.addAttribute("Identifying", Identifying.values());
				
		
		return "fragments/enterprise/manage/searchJurisdictions :: searchJurisdictions";
	}
	
	@ResponseBody
	@PostMapping(path="/searchJurisdiction/getJurisdictionId")
	public JurisdictionSearch[] searchJurisdiction(HttpServletRequest requestFromDT) {
	
		String name = requestFromDT.getParameter("name");
		String country = requestFromDT.getParameter("country");
		String identifying = requestFromDT.getParameter("identifying");
		String status = requestFromDT.getParameter("status");
		
		JurisdictionSearch[] JurisdictionSearch = null;
		
		String urlJuri = appgateway.getAppgateway()+"/LegalJurisdictions/legalJurisdictionsSearchList";
		
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"country\"" + ":\"" +country+ "\"," +
						"\"identifying\"" + ":\"" +identifying+ "\"," +						
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<JurisdictionSearch[]> response = restTemplate.exchange(urlJuri, HttpMethod.POST, request, JurisdictionSearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			JurisdictionSearch = response.getBody();
			//System.out.println("if "+JurisdictionSearch);
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return JurisdictionSearch;
	}
	
	@RequestMapping("/loadJurisdictionPage")
	public String createJurisdiction(Model model) {		
		
		CreateJurisdictions createJurisdictions=new CreateJurisdictions();
	
		CommonLOV[] cd=null;
		CommonLOV[] le=null;
		CommonLOV[] lc=null;
		
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		
		String urlLegalEntityCode = appgateway.getAppgateway()+"/LegalEntityRegnCode/getAllLegalEntityRegnCode";
		String urlLegislativeCategories = appgateway.getAppgateway()+"/LegislativeCategories/getAllLegislativeCategories";
	
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		
		ResponseEntity<CommonLOV[]> requestLegalEntity = restTemplate.exchange(urlLegalEntityCode, HttpMethod.GET, request, CommonLOV[].class);
		
		ResponseEntity<CommonLOV[]> requestLegislative = restTemplate.exchange(urlLegislativeCategories, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
			
		} 
		
		if(response.getStatusCode() == HttpStatus.OK) {
			le = requestLegalEntity.getBody();
			
		} 
		
		if(response.getStatusCode() == HttpStatus.OK) {
			lc = requestLegislative.getBody();
			
		} 
		
		model.addAttribute("createJuri", createJurisdictions);
		
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("requestLegalEntity", le);
		model.addAttribute("requestLegislative", lc);
		model.addAttribute("status", Status.values());
		model.addAttribute("Identifying", Identifying.values());
	
		return "fragments/enterprise/create/createJurisdiction :: createJuri";
	}
	
	@ResponseBody
	@PostMapping(value="/createJurisdictionResource")
	public String saveJurisdiction(HttpServletRequest requestFromDT) {
		
		String CR_JUDIS_EFFDT = requestFromDT.getParameter("CR_JUDIS_EFFDT");
		String CR_JUDIS_EFFT_ENDDATE = requestFromDT.getParameter("CR_JUDIS_EFFT_ENDDATE");
		String CR_JUDIS_LEG_CATOG = requestFromDT.getParameter("CR_JUDIS_LEG_CATOG");
		String CR_JUDIS_NAME = requestFromDT.getParameter("CR_JUDIS_NAME");
		String CR_JUDIS_LEGISLATIVE_CODE = requestFromDT.getParameter("CR_JUDIS_LEGISLATIVE_CODE");
		String CR_JUDIS_IDENTIFYING = requestFromDT.getParameter("CR_JUDIS_IDENTIFYING");
		String CR_JUDIS_COUNTRY = requestFromDT.getParameter("CR_JUDIS_COUNTRY");
		String CR_JUDIS_STATUS = requestFromDT.getParameter("CR_JUDIS_STATUS");
		
		String setJuri="";
		
		String urlJuri = appgateway.getAppgateway()+"/LegalJurisdictions/saveLegalJurisdictions";
		
		int actionid=0;
		
		String payLode = "{" +
				"\"effectstartdate\"" + ":\"" +CR_JUDIS_EFFDT+ "\"," +
				"\"effectenddate\""  + ":\"" +CR_JUDIS_EFFT_ENDDATE+ "\"," +
				"\"legislativecategories\"" + ":\"" +CR_JUDIS_LEG_CATOG+ "\"," +
				"\"name\"" + ":\"" +CR_JUDIS_NAME+ "\"," +
				"\"legalentityregncode\""  + ":\"" +CR_JUDIS_LEGISLATIVE_CODE+ "\"," +
				"\"identifying\"" + ":\"" +CR_JUDIS_IDENTIFYING+ "\"," +
				"\"country\"" + ":\"" +CR_JUDIS_COUNTRY+ "\"," +
				"\"actionid\"" + ":\"" +actionid+ "\"," +
				"\"legaljurisdictionid\"" + ":\"" +actionid+ "\"," +
				"\"status\"" + ":\"" +CR_JUDIS_STATUS+ "\"" +
				
				"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(urlJuri, HttpMethod.POST, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setJuri = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setJuri;
	}
	
	
	
	


	@RequestMapping("edit/EditJurisdiction/{legaljurisdictionid}")
	public String editJurisdiction(@PathVariable("legaljurisdictionid") String legaljurisdictionid, Model model)
			throws URISyntaxException 
	{
		String Searchlegaljurisdictionid = legaljurisdictionid;

		CreateJurisdictions CreateJurisdictions = null;
		String urlCreateJurisdictions= appgateway.getAppgateway()+"/LegalJurisdictions/getLegalJurisdictions/" + Searchlegaljurisdictionid;
		URI urlJURI = new URI(urlCreateJurisdictions);
		
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getJurisdictionsRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateJurisdictions> getJurisdictionsResponse = restTemplate.exchange(urlJURI, HttpMethod.GET,
				getJurisdictionsRequest, CreateJurisdictions.class);

		if (getJurisdictionsResponse.getStatusCode() == HttpStatus.OK) {
			//System.out.println("Print data by id::::::::::::::::::::::::::");
			CreateJurisdictions = getJurisdictionsResponse.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(getJurisdictionsResponse.getStatusCode());
		}
		
		CommonLOV[] le=null;
		String urlLegalEntityCode = appgateway.getAppgateway()+"/LegalEntityRegnCode/getAllLegalEntityRegnCode";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestLegalEntityCode = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> requestLegalEntity = restTemplate.exchange(urlLegalEntityCode, HttpMethod.GET, requestLegalEntityCode, CommonLOV[].class);
			
		if(requestLegalEntity.getStatusCode() == HttpStatus.OK) {
					le = requestLegalEntity.getBody();
					
				} 
		model.addAttribute("requestLegalEntity", le);		
		CommonLOV[] cd=null;
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
			
		} 
		
		
		CommonLOV[] lc=null;
		String urlLegislativeCategories = appgateway.getAppgateway()+"/LegislativeCategories/getAllLegislativeCategories";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestLegislativeCategories = new HttpEntity<String>(headers);
		
			ResponseEntity<CommonLOV[]> requestLegislative = restTemplate.exchange(urlLegislativeCategories, HttpMethod.GET, requestLegislativeCategories, CommonLOV[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			lc = requestLegislative.getBody();
			
		} 
			
		model.addAttribute("Identifying", Identifying.values());			
		model.addAttribute("requestLegislative", lc);			
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		model.addAttribute("createJuri", CreateJurisdictions);
		return "fragments/enterprise/create/createJurisdiction :: createJuri";

	}

		
	@ResponseBody
	@RequestMapping("edit/EditJurisdiction/correctJurisdiction")
	public String setJurisdiction(HttpServletRequest requestFromDT) {
		
		String CR_JUDIS_ID = requestFromDT.getParameter("CR_JUDIS_ID");
		String CR_JUDIS_ACTIONID = requestFromDT.getParameter("CR_JUDIS_ACTIONID");

		String CR_JUDIS_EFFDT = requestFromDT.getParameter("CR_JUDIS_EFFDT");
		String CR_JUDIS_EFFT_ENDDATE = requestFromDT.getParameter("CR_JUDIS_EFFT_ENDDATE");
		String CR_JUDIS_LEG_CATOG = requestFromDT.getParameter("CR_JUDIS_LEG_CATOG");
		String CR_JUDIS_NAME = requestFromDT.getParameter("CR_JUDIS_NAME");
		String CR_JUDIS_LEGISLATIVE_CODE = requestFromDT.getParameter("CR_JUDIS_LEGISLATIVE_CODE");
		String CR_JUDIS_IDENTIFYING = requestFromDT.getParameter("CR_JUDIS_IDENTIFYING");
		String CR_JUDIS_COUNTRY = requestFromDT.getParameter("CR_JUDIS_COUNTRY");
		String CR_JUDIS_STATUS = requestFromDT.getParameter("CR_JUDIS_STATUS");
		
				
		String setJuri="";
		
		String urlJuri = appgateway.getAppgateway()+"/LegalJurisdictions/correctLegalJurisdictions";
		
		String payLode = "{" +
		
				
				"\"effectstartdate\"" + ":\"" +CR_JUDIS_EFFDT+ "\"," +
				"\"effectenddate\""  + ":\"" +CR_JUDIS_EFFT_ENDDATE+ "\"," +
				"\"legislativecategories\"" + ":\"" +CR_JUDIS_LEG_CATOG+ "\"," +
				"\"name\"" + ":\"" +CR_JUDIS_NAME+ "\"," +
				"\"legalentityregncode\""  + ":\"" +CR_JUDIS_LEGISLATIVE_CODE+ "\"," +
				"\"identifying\"" + ":\"" +CR_JUDIS_IDENTIFYING+ "\"," +
				"\"country\"" + ":\"" +CR_JUDIS_COUNTRY+ "\"," +
				"\"actionid\"" + ":\"" +CR_JUDIS_ACTIONID+ "\"," +
				"\"legaljurisdictionid\"" + ":\"" +CR_JUDIS_ID+ "\"," +
				"\"status\"" + ":\"" +CR_JUDIS_STATUS+ "\"" +
				
				"}";
		//System.out.println("PAYLOAD :: "+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(urlJuri, HttpMethod.PUT, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setJuri = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setJuri;
	}

}
