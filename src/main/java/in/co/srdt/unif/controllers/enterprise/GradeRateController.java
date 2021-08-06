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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;


import in.co.srdt.unif.model.create.GradeRate;
import in.co.srdt.unif.model.search.GradeRateSearchResult;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class GradeRateController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@RequestMapping("/managegraderate")
	public String searchGradeRate(Model model) {

		//System.out.println("Hello Grade Rate Controller");
		CommonLOV[] rateTypeObj = null;
		
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response.getBody();
			
		}
		
		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/manage/searchGradeRate :: searchGradeRate";
	}

	@RequestMapping("/createGradeRate")
	public String createSalaryGrade(Model model) {

		CommonLOV[] rateTypeObj = null;
		CommonLOV[] frequencyObj = null;
		CommonLOV[] currencyObj = null;
		
		String urlRateType = appgateway.getAppgateway()+"/General/loadRateType";
		String urlFreq = appgateway.getAppgateway()+"/General/loadFrequncy";
		String urlCurrency = appgateway.getAppgateway()+"/Country/getAllCountryCurrency";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlRateType, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			rateTypeObj = response.getBody();
		}
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlFreq, HttpMethod.GET, request, CommonLOV[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			frequencyObj = response1.getBody();
			
		}
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlCurrency, HttpMethod.GET, request, CommonLOV[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
			currencyObj = response2.getBody();
			
		}

		model.addAttribute("ratetype", rateTypeObj);
		model.addAttribute("frequency", frequencyObj);
		model.addAttribute("currency", currencyObj);
		model.addAttribute("status", Status.values());
		return "fragments/enterprise/create/createGradeRate :: createGradeRate";
	}
	
	
	@RequestMapping(value = "/saveGradeRate", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String saveGradeRate(GradeRate graderate)
	{
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<GradeRate> request = new HttpEntity<GradeRate>(graderate, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(appgateway.getAppgateway()+"/GradeRate/saveGradeRateAndGradeRateValue",HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
			/*NEW CODE*/
			//OLD CODE//res = restTemplate.postForObject("http://192.200.12.83:9194/api/courseplan/create", coursePlanWrapper, SingleResponseModel.class);
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		return "Data Posted \n" + res.getMessage();
	}
	
	
	@ResponseBody
	@PostMapping("/searchGradeRate")
	public GradeRateSearchResult[] getGradeRateList(HttpServletRequest requestFromDT) {

		String searchratetype = requestFromDT.getParameter("ratetype");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("status");
		
		String url = appgateway.getAppgateway()+"/GradeRate/gradeRateSearchList";
		GradeRateSearchResult[] gradeRateObj = null;
		String payload = "{" + "\"name\"" + ":\"" + searchname + "\"," + "\"ratetype\"" + ":\"" + searchratetype + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<GradeRateSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				GradeRateSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			gradeRateObj = response.getBody();
			}
		
		return gradeRateObj;

	}
	
	

	

}
