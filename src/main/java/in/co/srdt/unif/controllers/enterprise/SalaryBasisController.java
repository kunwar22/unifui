package in.co.srdt.unif.controllers.enterprise;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SalaryBases;
import in.co.srdt.unif.model.search.SalaryBasesSearchResult;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;


@Controller
@RequestMapping("/enterprisesetup")
public class SalaryBasisController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	
	@Autowired
	private ApplicationGateway appgateway;
	
	
	@RequestMapping("/searchSalaryBasis")
	public String manageSalaryBasis(Model model) {
		
		//System.out.println("hello Salary search");
		
		return "fragments/enterprise/manage/searchSalaryBasis :: searchSalaryBasis";

	}
	
	
	@RequestMapping("/createSalaryBasis")
	public String createSalaryBasis(Model model) {		
		model.addAttribute("response", new SingleResponseModel());
		SalaryBases salaryBases=new SalaryBases();
			
		//System.out.println("hello Salary Plan populate");		
		
		String urlFreq = appgateway.getAppgateway()+"/General/loadFrequncy";
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";
		String urlsalryType = appgateway.getAppgateway()+"/General/loadSalaryType";

	     CommonLOV[] salrytype = null;
		
		
	
		
	

		CommonLOV[] frequencyObj = null;	
		CommonLOV[] rateTypeObj = null;
        headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
	
	
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlFreq, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			frequencyObj = response.getBody();
			
		}
		
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response1.getBody();
		}
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlsalryType, HttpMethod.GET, request, CommonLOV[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
		salrytype = response2.getBody();
		}
		
		model.addAttribute("salrytype", salrytype);
		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("frequency", frequencyObj);
		model.addAttribute("salaryBases",salaryBases);
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/create/createSalaryBasis :: createSalaryBasis";
	}
	

	@RequestMapping(value = "/saveSalaryBasis", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public  String saveSalaryBasis( @Valid SalaryBases salaryBases , BindingResult bindingResult , Model model)
	{
		model.addAttribute("response", new SingleResponseModel());

		String urlFreq = appgateway.getAppgateway()+"/General/loadFrequncy";
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";

		CommonLOV[] frequencyObj = null;	
		CommonLOV[] rateTypeObj = null;
		CommonLOV[] salrytype=null;
		
		String urlsalryType = appgateway.getAppgateway()+"/General/loadSalaryType";
        headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlsalryType, HttpMethod.GET, request, CommonLOV[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
		salrytype = response2.getBody();
		}
		
		model.addAttribute("salrytype", salrytype);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlFreq, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			frequencyObj = response.getBody();
			
		}
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response1.getBody();
			
		}
		
		
		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("frequency", frequencyObj);
		model.addAttribute("salaryBases",salaryBases);
		model.addAttribute("status", Status.values());
		
		
		
		
		model.addAttribute("salaryBases",new SalaryBases());
		//System.out.println("Salary Basis : "+ salaryBases.toString());
		
		//System.out.println("ERROR :: " + bindingResult.getAllErrors().toString());
		if(bindingResult.getFieldError("name")!=null) {
		//System.out.println("Field Error :: "+bindingResult.getFieldError("name").getDefaultMessage());
		}
		SingleResponseModel res = new SingleResponseModel();
		
		model.addAttribute("salaryBases",salaryBases);
		model.addAttribute("bindingResult",bindingResult);
		if (bindingResult.hasErrors()) {
		//	System.out.println("INSIDE BIND RESULT");
			return "fragments/enterprise/create/createSalaryBasis :: createSalaryBasis";	
		}
		
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<SalaryBases> request5 = new HttpEntity<SalaryBases>(salaryBases, headers);
			
			ResponseEntity<SingleResponseModel> response5= restTemplate.exchange( appgateway.getAppgateway()+"/SalaryBases/saveSalaryBases",HttpMethod.POST,request5, SingleResponseModel.class);
			if(response5.getStatusCode() == HttpStatus.OK) {
				res = response5.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response5.getStatusCode());
			}
			//System.out.println(res.getMessage());
			
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		model.addAttribute("response",res);
		return "fragments/enterprise/manage/searchSalaryBasis :: searchSalaryBasis";
	}
	
	
	
	
	@RequestMapping(value = "/correctSalaryBasis", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public  String correctSalaryBasis(@Valid SalaryBases salaryBases , BindingResult bindingResult , Model model)
	{
		model.addAttribute("response", new SingleResponseModel());
		//System.out.println("Correct Salary Basis : "+ salaryBases.toString());
		
		String urlFreq = appgateway.getAppgateway()+"/General/loadFrequncy";
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";
		String urlsalryType = appgateway.getAppgateway()+"/General/loadSalaryType";
		
		CommonLOV[] frequencyObj = null;	
		CommonLOV[] rateTypeObj = null;
		CommonLOV[] salrytype=null;
        headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlsalryType, HttpMethod.GET, request, CommonLOV[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
		salrytype = response2.getBody();
		}
		
		model.addAttribute("salrytype", salrytype);
		
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlFreq, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			frequencyObj = response.getBody();
			
		}
		
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response1.getBody();
			
		}
		
		
		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("frequency", frequencyObj);
		model.addAttribute("salaryBases",salaryBases);
		model.addAttribute("status", Status.values());
		
		
		
		
		model.addAttribute("salaryBases",new SalaryBases());
		//System.out.println("Salary Basis : "+ salaryBases.toString());
		
		//System.out.println("ERROR :: " + bindingResult.getAllErrors().toString());
		if(bindingResult.getFieldError("name")!=null) {
		//System.out.println("Field Error :: "+bindingResult.getFieldError("name").getDefaultMessage());
		}
		SingleResponseModel res = new SingleResponseModel();
		
		model.addAttribute("salaryBases",salaryBases);
		model.addAttribute("bindingResult",bindingResult);
		if (bindingResult.hasErrors()) {
		//	System.out.println("INSIDE BIND RESULT");
			return "fragments/enterprise/create/createSalaryBasis :: createSalaryBasis";	
		}
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<SalaryBases> request5 = new HttpEntity<SalaryBases>(salaryBases, headers);
			
			ResponseEntity<SingleResponseModel> response5= restTemplate.exchange( appgateway.getAppgateway()+"/SalaryBases/correctSalaryBases",HttpMethod.PUT,request5, SingleResponseModel.class);
			if(response5.getStatusCode() == HttpStatus.OK) {
				res = response5.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response5.getStatusCode());
			}
			//System.out.println(res.getMessage());
			
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		model.addAttribute("response",res);
		return "fragments/enterprise/manage/searchSalaryBasis :: searchSalaryBasis";
	}
	
	
	
	
	
	
	@ResponseBody
	@PostMapping("/searchSalaryBasis")
	public SalaryBasesSearchResult[] getSalaryBasisList(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("status");
		
		//System.out.println("Inside SALARY BASE Controller");

		String url =  appgateway.getAppgateway()+"/SalaryBases/salaryBasesSearchList";
		SalaryBasesSearchResult[] salaryBasisObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		//System.out.println("payload::::::::" + payload);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<SalaryBasesSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SalaryBasesSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			salaryBasisObj = response.getBody();
			}
		
		return salaryBasisObj;

	}
	
	
	
	
	@RequestMapping("edit/EditSalarybasis/{salarybasesid}")
	public String editSalarybasis(@PathVariable("salarybasesid") String salarybasesid, Model model)
			throws URISyntaxException 
	{
		model.addAttribute("response", new SingleResponseModel());
		//System.out.println("in");
		String Searchsalarybasesid= salarybasesid;

	
		SalaryBases salaryBases=null;
		String urlsalaryBases= appgateway.getAppgateway()+"/SalaryBases/getSalaryBasesById/" + Searchsalarybasesid;
		URI urlSALRY = new URI(urlsalaryBases);
		
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getSalaryRequest = new HttpEntity<String>(headerss);

		ResponseEntity<SalaryBases> getSalaryResponse = restTemplate.exchange(urlSALRY, HttpMethod.GET,
				getSalaryRequest, SalaryBases.class);

		if (getSalaryResponse.getStatusCode() == HttpStatus.OK) {
			//System.out.println("Print data by id::::::::::::::::::::::::::");
			salaryBases = getSalaryResponse.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(getSalaryResponse.getStatusCode());
		}
		
		
			
		String urlFreq = appgateway.getAppgateway()+"/General/loadFrequncy";
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";
		String urlsalryType = appgateway.getAppgateway()+"/General/loadSalaryType";
		
		CommonLOV[] frequencyObj = null;	
		CommonLOV[] rateTypeObj = null;
		CommonLOV[] salrytype=null;
        headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlsalryType, HttpMethod.GET, request, CommonLOV[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
		salrytype = response2.getBody();
		}
		
		model.addAttribute("salrytype", salrytype);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlFreq, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			frequencyObj = response.getBody();
			
		}
		
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response1.getBody();
			
		}
		
		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("frequency", frequencyObj);
		model.addAttribute("status", Status.values());
		model.addAttribute("salaryBases",salaryBases);
		return "fragments/enterprise/create/createSalaryBasis :: createSalaryBasis";

	}

	
	
	
	
}
