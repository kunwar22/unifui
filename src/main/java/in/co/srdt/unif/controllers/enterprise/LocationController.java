package in.co.srdt.unif.controllers.enterprise;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import in.co.srdt.unif.model.create.CreateLocation;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class LocationController {
	

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	private HttpHeaders headers;
	CommonLOV sd = new CommonLOV();
	
	LocationController()
	{
		
	}
	
	LocationController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	LocationController(HttpHeaders headers,RestTemplate restTemplate,ApplicationGateway api){
		this.headers=headers;
		this.restTemplate=restTemplate;
		this.appgateway=api;
	}
	

	@RequestMapping("/managelocation")
	public String searchlocation(Model model) {
	//	System.out.println("hello caleed");
		
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
		
		return "fragments/enterprise/manage/managelocation :: searchlocation";
	}

	@RequestMapping("/createlocation")
	public String createlocation(Model model) {

		CreateLocation locationget = new CreateLocation();

		//System.out.println("Inside Location Create");
		CommonLOV[] cd = null;
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";

		CommonLOV[] at = null;
		String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);

		ResponseEntity<CommonLOV[]> responseaddress = restTemplate.exchange(urlAddress, HttpMethod.GET, request,
				CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			at = responseaddress.getBody();
		}

		model.addAttribute("locationDetails", locationget);

		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		model.addAttribute("address", at);
		return "fragments/enterprise/create/createLocation :: createLoc";
	}

	@ResponseBody
	@RequestMapping("/statebind/{countryId}")
	public CommonLOV[] bindState(@PathVariable("countryId") String countryId, Model model) {

		CommonLOV[] sd = null;
		String url = appgateway.getAppgateway()+"/State/getAllStateByCountry/" + countryId;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("StateDetails", sd);

		return sd;

	}


	//*********Start: Time Zone********//
	
	@ResponseBody
	@RequestMapping("/timezone/{countryId}")
	public CommonLOV[] timezone(@PathVariable("countryId") String countryId, Model model) {


		CommonLOV[] sd = null;
		String urltimezone = appgateway.getAppgateway()+"/TimeZone/getAllTimeZoneByCountry/" + countryId;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urltimezone, HttpMethod.GET, request,
				CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			//System.out.println("timezoneData ::::::::::::::::::::" + sd[0].getDescription());
		}
		model.addAttribute("timezoneDetails", sd);

		return sd;

	}

	//*********END: Time Zone********//


	//*********Start: search Enterprisse Location********//

	@ResponseBody
	@PostMapping(path = "edit/enterpriseLocation/searchEnterpriseLocation")
	public LocationSearchResult[] getLocation(HttpServletRequest requestFromDT) {

		//System.out.println("inside contoller");
		String searchName = requestFromDT.getParameter("name");
		String searchCode = requestFromDT.getParameter("code");
		String searchCountry = requestFromDT.getParameter("country");
		String searchStatus = requestFromDT.getParameter("status");

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";
		
		//System.out.println("PAYLOAD :: "+payLode);

		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}

		return locationId;
	}

//*********END: search Enterprisse Location********//


// page populate with data on basis of location Id Start here

	@RequestMapping("edit/enterpriseLocation/{locationId}/{effdt}")
	public String editEnterpriseLocation(@PathVariable("locationId") String locationId,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException,ParseException {

		String SearchlocationId = locationId;
		
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

	// ****start addressType bind****//
		CommonLOV[] at = null;
		String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestaddress = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseaddress = restTemplate.exchange(urlAddress, HttpMethod.GET, requestaddress,
				CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			at = responseaddress.getBody();
		}
	// ****end addressType bind****//

		CreateLocation locationget = null;
		String urrlLocation = appgateway.getAppgateway()+"/Location/getLocation/" + SearchlocationId;
		URI url = new URI(urrlLocation);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getLocationByIdRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateLocation> getLocationByIdResponse = restTemplate.exchange(url, HttpMethod.GET,
				getLocationByIdRequest, CreateLocation.class);

		if (getLocationByIdResponse.getStatusCode() == HttpStatus.OK) {
			//System.out.println("Print data by id::::::::::::::::::::::::::");
			locationget = getLocationByIdResponse.getBody();
			
		} 
		else {
			System.out.println("Request Failed");
			System.out.println(getLocationByIdResponse.getStatusCode());
		}
		
		String effstartdate=locationget.getEffectstartdate();
		effstartdate=effstartdate.substring(0,10);
		locationget.setEffectstartdate(effstartdate);
		
	// ****start state bind****//
		bindState(Long.toString(locationget.getCountry()), model);
	// ****end state bind****//

	// ****start- timezone bind****//

		timezone(Long.toString(locationget.getCountry()), model);
	// ****end time zone bind****//

		model.addAttribute("status", Status.values());
		model.addAttribute("locationDetails", locationget);
		model.addAttribute("CountryDetails", cd);

		model.addAttribute("address", at);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
		{
			java.util.Date date1 = sdf.parse(effdt);
			java.util.Date date2 = sdf.parse(locationget.getEffectstartdate());
		
				if (date1.compareTo(date2) <= 0)
				{ 
					//System.out.println("earlier"); 
					locationget.setAdditionalatr("Error");
				}
				else if(date1.compareTo(date2) > 0)	  
				{
					locationget.setAdditionalatr(effdt);
				}
		}
		else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
			locationget.setAdditionalatr("Correct");
		}
		
		return "fragments/enterprise/create/createLocation :: createLoc";

	}
	
	
	@ResponseBody
	@PostMapping(value = "/enterpriseCreateLocation")
	public String setLocation(HttpServletRequest requestFromDT) {

		String CR_LOC_NAME = requestFromDT.getParameter("CR_LOC_NAME");
		String CR_LOC_CODE = requestFromDT.getParameter("CR_LOC_CODE");
		String CR_EFFDT = requestFromDT.getParameter("CR_EFFDT");
		String CR_EMAIL = requestFromDT.getParameter("CR_EMAIL");
		//String CR_FAX = requestFromDT.getParameter("CR_FAX");
		String CR_TELEPHONE = requestFromDT.getParameter("CR_TELEPHONE");
		String CR_selectCountryId = requestFromDT.getParameter("CR_selectCountryId");
		String CR_selectStateId = requestFromDT.getParameter("CR_selectStateId");
		String CR_selectTimeZoneId = requestFromDT.getParameter("CR_selectTimeZoneId");

		String CR_STATUS = requestFromDT.getParameter("CR_STATUS");
		String CR_selectAddressTypeId = requestFromDT.getParameter("CR_selectAddressTypeId");
		String CR_ADDRESS1 = requestFromDT.getParameter("CR_ADDRESS1");
		String CR_ADDRESS3 = requestFromDT.getParameter("CR_ADDRESS3");
		String CR_ADDRESS2 = requestFromDT.getParameter("CR_ADDRESS2");
		String CR_CITY = requestFromDT.getParameter("CR_CITY");
		String CR_PIN = requestFromDT.getParameter("CR_PIN");

		//System.out.println("hi-1" + CR_LOC_NAME + " " + CR_LOC_CODE + " " + CR_EFFDT + " " + CR_EMAIL + " " + CR_FAX + " end");
		//System.out.println("hi-4" + CR_ADDRESS1 + " " + CR_ADDRESS3 + " " + CR_ADDRESS2 + " " + CR_selectAddressTypeId + " end");
		//System.out.println("hi-2" + CR_TELEPHONE + " " + CR_selectCountryId + " " + CR_selectStateId + " "+ CR_selectTimeZoneId + " end");
		//System.out.println("hi-3" + CR_STATUS + " " + CR_selectAddressTypeId + " end");
		String setloc = "";

		String url = appgateway.getAppgateway()+"/Location/saveLocation";

		String payLode = "{" + "\"name\"" + ":\"" + CR_LOC_NAME + "\"," + "\"code\"" + ":\"" + CR_LOC_CODE + "\","
				+ "\"description\"" + ":\"\"," +

				"\"country\"" + ":\"" + CR_selectCountryId + "\"," + "\"state\"" + ":\"" + CR_selectStateId + "\","
				+ "\"timezone\"" + ":\"" + CR_selectTimeZoneId + "\"," + "\"effectstartdate\"" + ":\"" + CR_EFFDT
				+ "\"," + "\"effectenddate\"" + ":\"\"," + "\"addresstype\"" + ":\"" + CR_selectAddressTypeId + "\","
				+ "\"citytown\"" + ":\"" + CR_CITY + "\"," + "\"phoneno\"" + ":\"" + CR_TELEPHONE + "\","
				+ "\"emailid\"" + ":\"" + CR_EMAIL + "\"," + 
				"\"pincode\"" + ":\"\"," + 
				"\"addressline3\"" + ":\""
				+ CR_ADDRESS3 + "\"," + "\"addressline2\"" + ":\"" + CR_ADDRESS2 + "\"," + "\"addressline1\"" + ":\""
				+ CR_ADDRESS1 + "\","+"\"pincode\"" + ":\"" + CR_PIN + "\"," + "\"status\"" + ":\"" + CR_STATUS + "\"" +

				"}";
		//System.out.println(payLode);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setloc = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return setloc;
	}

	@ResponseBody
	@PostMapping(value = "/enterpriseCorrectLocation")
	public String correctLocation(HttpServletRequest requestFromDT) {

		String CR_LOC_NAME = requestFromDT.getParameter("CR_LOC_NAME");
		String CR_LOC_CODE = requestFromDT.getParameter("CR_LOC_CODE");
		String CR_EFFDT = requestFromDT.getParameter("CR_EFFDT");
		String CR_EMAIL = requestFromDT.getParameter("CR_EMAIL");

		String CR_TELEPHONE = requestFromDT.getParameter("CR_TELEPHONE");
		String CR_selectCountryId = requestFromDT.getParameter("CR_selectCountryId");
		String CR_selectStateId = requestFromDT.getParameter("CR_selectStateId");
		String CR_selectTimeZoneId = requestFromDT.getParameter("CR_selectTimeZoneId");

		String CR_STATUS = requestFromDT.getParameter("CR_STATUS");
		String CR_selectAddressTypeId = requestFromDT.getParameter("CR_selectAddressTypeId");

		String CR_ADDRESS1 = requestFromDT.getParameter("CR_ADDRESS1");
		String CR_ADDRESS3 = requestFromDT.getParameter("CR_ADDRESS3");
		String CR_ADDRESS2 = requestFromDT.getParameter("CR_ADDRESS2");
		String CR_CITY = requestFromDT.getParameter("CR_CITY");
		String CR_PIN = requestFromDT.getParameter("CR_PIN");

		String CR_ACTIONID = requestFromDT.getParameter("CR_ACTIONID");
		String CR_LOCATIONID = requestFromDT.getParameter("CR_LOCATIONID");
		
		
		
		String flag=requestFromDT.getParameter("FLAG");
		flag=flag.trim();
		

		String setloc = "";
		String url="";
		
		
		
		if(flag.equals("Correct")) {
			url = appgateway.getAppgateway()+"/Location/correctLocation";
			
		}
		else if(flag!="Correct") {

			url = appgateway.getAppgateway()+"/Location/updateLocation";
		
			}
		

		String payLode = "{" + 
		        "\"name\"" + ":\"" + CR_LOC_NAME + "\"," + 
				"\"code\"" + ":\"" + CR_LOC_CODE + "\","+ 
		        "\"description\"" + ":\"\"," +
				"\"country\"" + ":\"" + CR_selectCountryId + "\"," + 
				"\"state\"" + ":\"" + CR_selectStateId + "\","+ 
				"\"timezone\"" + ":\"" + CR_selectTimeZoneId + "\"," + 
				"\"effectstartdate\"" + ":\"" + CR_EFFDT+ "\"," +
				"\"effectenddate\"" + ":\"\"," + 
				"\"addresstype\"" + ":\"" + CR_selectAddressTypeId + "\","+ 
				"\"citytown\"" + ":\"" + CR_CITY + "\"," +
				"\"phoneno\"" + ":\"" + CR_TELEPHONE + "\","+ 
				"\"emailid\"" + ":\"" + CR_EMAIL + "\"," +
				"\"pincode\"" + ":\"\"," + 
				"\"addressline3\"" + ":\""+ CR_ADDRESS3 + "\"," +
				"\"addressline2\"" + ":\"" + CR_ADDRESS2 + "\"," +
				"\"addressline1\"" + ":\""+ CR_ADDRESS1 + "\"," + 
				"\"status\"" + ":\"" + CR_STATUS + "\"," +
				"\"actionid\"" + ":\"" + CR_ACTIONID+ "\"," + 
				"\"pincode\"" + ":\"" + CR_PIN + "\","+
				"\"locationid\"" + ":\"" + CR_LOCATIONID + "\""
				+ "}";
		//System.out.println(payLode);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setloc = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return setloc;
	}
	
	//** Edit business unit start here**//
	
	
	
	
	
	
	
}
