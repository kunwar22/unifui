package in.co.srdt.unif.controllers.aor;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.search.AreaOfResponsibilityResult;
import in.co.srdt.unif.model.create.CreateAor;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;




@Controller
@SessionAttributes("pnum")

@RequestMapping("/aor")
public class AORController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	SmartValidator validator;

	
	AORController(){
		
	}
	
	AORController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	@RequestMapping("/manageAor")
	public String manageAreaOfResponsibility(Model model, HttpServletRequest req) {
		Login login= (Login) req.getSession().getAttribute("login");
		CommonLOV[] Responsibility=null;
		String urlResponsibility = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		String urlCountry = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
      	
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlResponsibility, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Responsibility = response.getBody();
			
		} 
		
				
		model.addAttribute("Responsibility", Responsibility);	
		return "fragments/aor/manageAreaOfRes :: searchAreaOfRes";
	}

	@RequestMapping("/loadAorPage")
	public String createAreaOfResponsibility(@ModelAttribute("areaOfResponsibility") CreateAor areaOfResponsibility,
			Model model, HttpServletRequest req, @ModelAttribute("pnum")String pnum) {		
			
		//System.out.println("createabsencetype::::::::::::::::::::");
		model.addAttribute("areaOfResponsibility", new CreateAor());
		
		Login login= (Login) req.getSession().getAttribute("login");
		
		//model.addAttribute("pno",login.getEmplid());
		
		CommonLOV[] Responsibility=null;
		CommonLOV[] cd=null;
		
		String urlResponsibility = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		String urlCountry = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
      	
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlResponsibility, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Responsibility = response.getBody();
			
		} 
		
		
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		model.addAttribute("Personnumber", pnum);
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("Responsibility", Responsibility);	
		model.addAttribute("areaOfResponsibility", areaOfResponsibility);
		return "fragments/aor/createAreaofRes :: createAreaOfRes";
	}
	
	
	
	//@PostMapping(value = "/saveAreaOfResponsibility")
	@RequestMapping(value = "/saveAreaOfResponsibility", method = RequestMethod.POST)
	public String saveAreaOfResponsibility(@ModelAttribute("areaOfResponsibility") CreateAor areaOfResponsibility, BindingResult bindingResult,Model model ,@ModelAttribute("pnum")String pnum)
	{
		
		model.addAttribute("areaOfResponsibility", new CreateAor());
		
		//Login login= (Login) req.getSession().getAttribute("login");
		
		//model.addAttribute("pno",login.getEmplid());
		
		CommonLOV[] Responsibility=null;
		CommonLOV[] cd=null;
		
		String urlResponsibility = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		String urlCountry = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
      	
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlResponsibility, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Responsibility = response.getBody();
			
		} 
		
		
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("Responsibility", Responsibility);	
		model.addAttribute("areaOfResponsibility", areaOfResponsibility);
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("Personnumber", pnum);
		validator.validate(areaOfResponsibility, bindingResult);
		System.out.println(bindingResult.toString());
		if (bindingResult.hasErrors()) {
			model.addAttribute("result","errrrrr");
			
			return "fragments/aor/createAreaofRes :: createAreaOfRes";
		}
		
		String urlAor=appgateway.getAppgateway()+"/PersonManagement/AOR/saveAreaOfResponsibility";
		System.out.println("url aor"+urlAor);
		System.out.println("AOR ::"+ areaOfResponsibility.toString());
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			//System.out.println("AOR ::"+ areaOfResponsibility.toString());

			
			HttpEntity<CreateAor> request1 = new HttpEntity<CreateAor>(areaOfResponsibility, headers);
			
			ResponseEntity<SingleResponseModel> response1= restTemplate.exchange(urlAor,HttpMethod.POST,request1, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response1.getBody();
				System.out.println("save areaOfResponsibility ::"+areaOfResponsibility.toString());
			} else {
				System.out.println("Request Failed");
				System.out.println(response1.getStatusCode());
			}
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Area of Responsibility.");
		}
		model.addAttribute("result",res.getStatus());
		model.addAttribute("Personnumber", pnum);
		
		return "fragments/aor/createAreaofRes :: createAreaOfRes";
	}
	
	
	
	@ResponseBody
	@PostMapping(path="/searchAor/getAorId")
	public AreaOfResponsibilityResult[] areaOfResponsibilityResults(HttpServletRequest requestFromDT, @ModelAttribute("pnum")String pnum ) {
		
		
		String AOR_NAME = requestFromDT.getParameter("AOR_NAME");
		String AOR_TYPE = requestFromDT.getParameter("AOR_TYPE");
		//String AOR_PNUM = requestFromDT.getParameter("AOR_PNUM");
		
		//System.out.println("Area of respo"+AOR_NAME+""+AOR_TYPE);
		AreaOfResponsibilityResult[] areaOfResponsibilityResults  = null;
		
		String urlAOR = appgateway.getAppgateway()+"/PersonManagement/AOR/loadAreaOfResponsibilityBySearch";
		
		String payLode = "{" +
						"\"responsibilityname\"" + ":\"" +AOR_NAME+ "\"," +
						"\"personnumber\"" + ":\"" +pnum+ "\"," +
						"\"responsibilitytype\"" + ":\"" +AOR_TYPE+ "\"" +

						"}";
		System.out.println(urlAOR+""+payLode);
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<AreaOfResponsibilityResult[]> response = restTemplate.exchange(urlAOR, HttpMethod.POST, request, AreaOfResponsibilityResult[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			areaOfResponsibilityResults = response.getBody();
		//	System.out.println("Sucess"+areaOfResponsibilityResults.toString());
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return areaOfResponsibilityResults;
	}
	
	
	//@PostMapping(value = "/correctAreaOfResponsibility")
	
	@RequestMapping(value = "/correctAreaOfResponsibility", method = RequestMethod.POST)
	public String correctAreaOfResponsibility(@ModelAttribute("areaOfResponsibility") CreateAor areaOfResponsibility,HttpServletRequest req,Model model, BindingResult bindingResult,@ModelAttribute("pnum")String pnum)
	{
		model.addAttribute("areaOfResponsibility", new CreateAor());
		
		//Login login= (Login) req.getSession().getAttribute("login");
		
		//model.addAttribute("pno",login.getEmplid());
		
		CommonLOV[] Responsibility=null;
		CommonLOV[] cd=null;
		
		String urlResponsibility = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		String urlCountry = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
      	
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlResponsibility, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Responsibility = response.getBody();
			
		} 
		
		
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("Responsibility", Responsibility);	
		model.addAttribute("areaOfResponsibility", areaOfResponsibility);
		model.addAttribute("bindingResult", bindingResult);
		
		validator.validate(areaOfResponsibility, bindingResult);
		model.addAttribute("Personnumber", pnum);
		if (bindingResult.hasErrors()) {
			model.addAttribute("result","errrrrr");
			
			return "fragments/aor/createAreaofRes :: createAreaOfRes";
		}
		
		String urlAor=appgateway.getAppgateway()+"/PersonManagement/AOR/correctAreaOfResponsibility";
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			
			
			HttpEntity<CreateAor> request2 = new HttpEntity<CreateAor>(areaOfResponsibility, headers);
			
			ResponseEntity<SingleResponseModel> response2= restTemplate.exchange(urlAor,HttpMethod.PUT,request2, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response2.getBody();
				System.out.println("AOR correct ::"+ areaOfResponsibility.toString());
				System.out.println("success"+response2.getStatusCode());
			} else {
				System.out.println("Request Failed");
				System.out.println(response2.getStatusCode());
			}
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to Correct Area of Responsibility.");
		}
		model.addAttribute("result",res.getStatus());
		model.addAttribute("Personnumber", pnum);
		return "fragments/aor/createAreaofRes :: createAreaOfRes";
	}
	
	
	
	
	@RequestMapping("/edit/editAreaOfRes/correctAOR/{aorid}")
	public String editAreaOfResponceById(@PathVariable("aorid") String aorid, Model model,HttpServletRequest req)
			throws URISyntaxException {
		Login login= (Login) req.getSession().getAttribute("login");		
		model.addAttribute("pno",login.getEmplid());
		String SearchaorId = aorid;
		
		CreateAor areaOfResponsibility=null;
		
		String urrlAOR= appgateway.getAppgateway()+"/PersonManagement/AOR/getAreaOfResponsibilityById/" + SearchaorId;
		URI urlAOR = new URI(urrlAOR);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);
		
		//System.out.println(urrlAOR+"");

		HttpEntity<String>getAreaOfResponsibilityRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateAor> getAreaOfResponsibilityResponse = restTemplate.exchange(urlAOR, HttpMethod.GET,
				getAreaOfResponsibilityRequest, CreateAor.class);

		if (getAreaOfResponsibilityResponse.getStatusCode() == HttpStatus.OK) {
			areaOfResponsibility = getAreaOfResponsibilityResponse.getBody();
			//System.out.println("data"+areaOfResponsibility.toString());
			//System.out.println("data1"+areaOfResponsibility.getPersonnumber());
		} 
		else {
			System.out.println("Request Failed");
			System.out.println(getAreaOfResponsibilityResponse.getStatusCode());
				
		}
		
        model.addAttribute("areaOfResponsibility", new CreateAor());
		
		CommonLOV[] Responsibility=null;
		CommonLOV[] cd=null;
		
		String urlResponsibility = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		String urlCountry = appgateway.getAppgateway()+"/General/loadResponsibilityType";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
      	
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlResponsibility, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Responsibility = response.getBody();
			
		} 
		
		
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("Responsibility", Responsibility);
		
		
		model.addAttribute("areaOfResponsibility", areaOfResponsibility);
	
		return "fragments/aor/createAreaofRes :: createAreaOfRes";

		}
	
}