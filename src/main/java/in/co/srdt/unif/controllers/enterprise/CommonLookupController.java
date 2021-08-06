package in.co.srdt.unif.controllers.enterprise;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.LookupSave;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.search.GeneralByLookupTypeResult;

import in.co.srdt.unif.model.search.LookupSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class CommonLookupController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	@RequestMapping("/managecommonlookup")
	public String commonLookup(Model model) {
		
		//System.out.println("Hello Common Lookup Controller");
		model.addAttribute("poplookup", new GeneralByLookupTypeResult());
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/manage/commonLookup :: commonLookup ";
	
	
	}

	
	@ResponseBody
	@PostMapping("/lookupSearch")
	public LookupSearchResult[] getLookupList(HttpServletRequest requestFromDT) {

		String loouptype = requestFromDT.getParameter("lookuptype");
		String lookupdescr = requestFromDT.getParameter("lookuptypedescription");
		
		if(loouptype==null)
		{
			loouptype="";
		}
		if(lookupdescr==null)
		{
			lookupdescr="";
		}
		
		//System.out.println("Inside LookupSearch Controller");

		String url = appgateway.getAppgateway()+"/General/generalSearchList";
		LookupSearchResult[] lookupObj = null;
		String payload = "{" + "\"lookuptype\"" + ":\"" + loouptype + "\"," + "\"lookuptypedescription\"" + ":\"" + lookupdescr + "\"}";
		//System.out.println("payload::::::::" + payload);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<LookupSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				LookupSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			lookupObj = response.getBody();
		}
		// model.addAttribute("CountryDetails", datasetObj);

		return lookupObj;

	}
	
	@ResponseBody
	@RequestMapping("/lookupCodes/{lookuptypeselected}")
	public GeneralByLookupTypeResult[] getGeneralByLookupType(@PathVariable("lookuptypeselected") String lookuptypeselected, Model model) {

		//System.out.println("Hello General by Lookup Type");

		GeneralByLookupTypeResult[] sd = null;
		String url = appgateway.getAppgateway()+"/General/getGeneralByLookupType/" + lookuptypeselected;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<GeneralByLookupTypeResult[]> response = restTemplate.exchange(url, HttpMethod.GET, request, GeneralByLookupTypeResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			//System.out.println("GeneralLookup  ::::::::::::::::::::" + sd.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("generallookup", sd);

		return sd;

	}
	@RequestMapping(value = "/saveLookup", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String saveLookup(LookupSave lookup)
	{
		//System.out.println("Inside Lookup Controller");
		//System.out.println("Lookup : "+ lookup.toString());
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<LookupSave> request = new HttpEntity<LookupSave>(lookup, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(appgateway.getAppgateway()+"/General/saveGeneralLookup", HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println(res.getMessage());
			}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		return "Data Posted \n" + res.getMessage();
	}
	
	
	@ResponseBody
	@RequestMapping("/getLookupById/{id}")
	public GeneralByLookupTypeResult getLookupByID(@PathVariable("id") String id, Model model) {

		//System.out.println("Hello Lookup Popup");

		GeneralByLookupTypeResult sd = null;
		String url = appgateway.getAppgateway()+"/General/getGeneralById/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<GeneralByLookupTypeResult> response = restTemplate.exchange(url, HttpMethod.GET, request, GeneralByLookupTypeResult.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			//System.out.println("Lookup  ::::::::::::::::::::" + sd.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("poplookup", sd);

		return sd;

	}
	
	@RequestMapping(value = "/editLookup", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String editLookup(LookupSave lookup)
	{
		//System.out.println("Inside Lookup Controller");
		//System.out.println("EditLookup : "+ lookup.toString());
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<LookupSave> request = new HttpEntity<LookupSave>(lookup, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(appgateway.getAppgateway()+"/General/correctGeneralLookup", HttpMethod.PUT, request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println(res.getMessage());
			}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		return res.getMessage();
	}
	
}
