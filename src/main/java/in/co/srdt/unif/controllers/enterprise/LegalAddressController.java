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
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateLegalAddress;
import in.co.srdt.unif.model.search.LegalAddressSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class LegalAddressController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;

	@ResponseBody
	@PostMapping("/searchlegaladdress")
	public LegalAddressSearchResult[] searchlegaladdress(HttpServletRequest requestFromDT) {

		String searchcountry = requestFromDT.getParameter("country");
		String searchstatus = requestFromDT.getParameter("status");

		String url = appgateway.getAppgateway()+"/LegalAddress/legalAddressSearchList";
		LegalAddressSearchResult[] legaddr = null;
		String payload = "{" + "\"country\"" + ":\"" + searchcountry + "\"," + "\"status\"" + ":\"" + searchstatus
				+ "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<LegalAddressSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				LegalAddressSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			legaddr = response.getBody();

		}
		
		return legaddr;
	}

	@RequestMapping("/createlegaladdress")
	public String createlegaladdress(Model model) {

		CreateLegalAddress createLegalAddress=new CreateLegalAddress();
		//System.out.println("Inside Leagl Address Create");
		CommonLOV[] cd = null;
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
			
		}
		model.addAttribute("LegalAddress", createLegalAddress);
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/create/createLegalAddress :: createlegaladdress";
	}

	@ResponseBody
	@PostMapping("/saveLegalAddress")
	public String saveLegalAddress(HttpServletRequest requestFromDT, Model model) {

		String setlegalAddr = "";
		
		String legaladdressid = requestFromDT.getParameter("legaladdressid");
		String actionid = requestFromDT.getParameter("actionid");

		String addr1 = requestFromDT.getParameter("addressLine1");
		String addr2 = requestFromDT.getParameter("addressLine2");
		String addr3 = requestFromDT.getParameter("addressLine3");
		String country = requestFromDT.getParameter("country");
		String state = requestFromDT.getParameter("state");
		String cityTown = requestFromDT.getParameter("cityTown");
		String pinCode = requestFromDT.getParameter("pinCode");
		String status = requestFromDT.getParameter("status");
		
		String timeZone = requestFromDT.getParameter("timeZone");
		
		String url = appgateway.getAppgateway()+"/LegalAddress/saveLegalAddress";

		String payLode = "{" +
		
				"\"legaladdressid\"" + ":\"" + legaladdressid + "\"," +
				"\"actionid\"" + ":\"" + actionid + "\","+

				"\"addressline1\"" + ":\"" + addr1 + "\"," +
				"\"addressline2\"" + ":\"" + addr2 + "\","+
				"\"addressline3\"" + ":\"" + addr3 + "\"," +
				"\"citytown\"" + ":\"" + cityTown + "\"," + 
				"\"country\""+ ":\"" + country + "\"," +
				"\"pincode\"" + ":\"" + pinCode + "\"," +
				"\"state\"" + ":\"" + state	+ "\"," + 
				"\"status\"" + ":\"" + status + "\"," + 
				"\"timezone\"" + ":\"" + timeZone + "\"" +

				"}";
		
	//	System.out.println("PAYLOAD ::"+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setlegalAddr = response.getBody();
			
		}
		model.addAttribute("legalAddressDetails", setlegalAddr);

		return setlegalAddr;
	}
	

	@RequestMapping("/managelegaladdress")
	public String manageLegalAddress(Model model) {

		
		CommonLOV[] cd = null;
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
			
		}
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/manage/manageLegalAddress :: searchlegaladdress";
	}
	
	
	@RequestMapping("edit/legaladdress/correctlegaladdress/{legaladdressid}")
	public String editLegalAddress(@PathVariable("legaladdressid") String legaladdressid, Model model)
				throws URISyntaxException {

		LocationController loc=new LocationController(headers,restTemplate,appgateway);
			
		String Searchlegaladdressid = legaladdressid;
			
		CreateLegalAddress CreateLegalAddress=new CreateLegalAddress();
			
	   	String urrlLegalAddress= appgateway.getAppgateway()+"/LegalAddress/getLegalAddress/" + Searchlegaladdressid;
		URI urlLA = new URI(urrlLegalAddress);
			
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getLegalAddressRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateLegalAddress> getLegalAddressSResponse = restTemplate.exchange(urlLA, HttpMethod.GET,
					getLegalAddressRequest, CreateLegalAddress.class);

		if (getLegalAddressSResponse.getStatusCode() == HttpStatus.OK) {
				
			CreateLegalAddress = getLegalAddressSResponse.getBody();
	
		} else {
			System.out.println("Request Failed");
			System.out.println(getLegalAddressSResponse.getStatusCode());
			}
			
		// start country bind//
		CommonLOV[] cd = null;

		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlcountry, HttpMethod.GET, request,
					CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();

		}
		// ****end country bind****//
			
		
		// ****start state bind****//
		loc.bindState(Long.toString(CreateLegalAddress.getCountry()), model);
			
		// ****end state bind****//

		// ****start- timezone bind****//

		loc.timezone(Long.toString(CreateLegalAddress.getCountry()), model);
		// ****end time zone bind****//

			
		model.addAttribute("Countrydetails", cd);
		model.addAttribute("status", Status.values());
		model.addAttribute("LegalAddress", CreateLegalAddress);
		
		return "fragments/enterprise/create/createLegalAddress :: createlegaladdress";	

	}
		
	@ResponseBody
	@PostMapping("edit/legaladdress/correctlegalAddress")
	public String correctlegalAddress(HttpServletRequest requestFromDT, Model model) {

		String setlegalAddr = "";
		
		String legaladdressid = requestFromDT.getParameter("legaladdressid");
		String actionid = requestFromDT.getParameter("actionid");

		String addr1 = requestFromDT.getParameter("addressLine1");
		String addr2 = requestFromDT.getParameter("addressLine2");
		String addr3 = requestFromDT.getParameter("addressLine3");
		String country = requestFromDT.getParameter("country");
		String state = requestFromDT.getParameter("state");
		String cityTown = requestFromDT.getParameter("cityTown");
		String pinCode = requestFromDT.getParameter("pinCode");
		String status = requestFromDT.getParameter("status");
		String timeZone = requestFromDT.getParameter("timeZone");
		
		String url = appgateway.getAppgateway()+"/LegalAddress/correctLegalAddress";

		String payLode = "{" +
		
				"\"legaladdressid\"" + ":\"" + legaladdressid + "\"," +
				"\"actionid\"" + ":\"" + actionid + "\","+

				"\"addressline1\"" + ":\"" + addr1 + "\"," +
				"\"addressline2\"" + ":\"" + addr2 + "\","+
				"\"addressline3\"" + ":\"" + addr3 + "\"," +
				"\"citytown\"" + ":\"" + cityTown + "\"," + 
				"\"country\""+ ":\"" + country + "\"," +
				"\"pincode\"" + ":\"" + pinCode + "\"," +
				"\"state\"" + ":\"" + state	+ "\"," + 
				"\"status\"" + ":\"" + status + "\"," + 
				"\"timezone\"" + ":\"" + timeZone + "\"" +

				"}";
		//System.out.println("PAYLOAD ::"+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setlegalAddr = response.getBody();
			//System.out.println("countrydata ::::::::::::::::::::" + setlegalAddr);
		}
		model.addAttribute("legalAddressDetails", setlegalAddr);

		return setlegalAddr;
		
	}

}
