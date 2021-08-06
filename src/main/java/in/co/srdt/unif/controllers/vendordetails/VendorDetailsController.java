package in.co.srdt.unif.controllers.vendordetails;

import java.net.URISyntaxException;
import java.util.List;

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
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.DoubleResponseModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.Element;
import in.co.srdt.unif.model.vendordetails.VendorDetails;
import in.co.srdt.unif.model.vendordetails.VendorDetailsWrapper;
import in.co.srdt.unif.model.vendordetails.VendorElementMapping;
import in.co.srdt.unif.model.vendordetails.VendorPersonMapping;
import in.co.srdt.unif.model.vendordetails.VendorPersonMappingWrapper;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/vendordetails")
public class VendorDetailsController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	SmartValidator validator;

	@RequestMapping("/createVendor")
	public String createVendor(Model model) {
		VendorDetails vendorDetails = new VendorDetails();
		model.addAttribute("vendorDetails", vendorDetails);
		model.addAttribute("status", Status.values());
		return "fragments/vendordetails/createVendor :: createVendor";
	}

	@RequestMapping("/searchVendor")
	public String searchVendor() {
		return "fragments/vendordetails/vendorListSearch :: VendorListSearch";
	}

	@ResponseBody
	@RequestMapping(value = "/getAllVenderSearchListData", method = RequestMethod.GET)
	public VendorDetails[] VendorDetailsData(HttpServletRequest request, Model model) {

		
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", AccessToken.getAccessToken());
//		headers.setContentType(MediaType.APPLICATION_JSON);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		VendorDetails[] res = null;
		String url = appgateway.getAppgateway_payroll() +"/api/ebsvendordetails/getAllVendorDetails";
		
		HttpEntity<String> req = new HttpEntity<>(headers);
		ResponseEntity<VendorDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, req,VendorDetails[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}

	@RequestMapping(value = "/saveVendor", method = RequestMethod.POST)
	public String saveVendor(@ModelAttribute("vendor") VendorDetails vendor, Model model,HttpServletRequest req) {

		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Login login = (Login) req.getSession().getAttribute("login");
		
		vendor.setCreatedby(login.getEmplid());
		vendor.setModifiedby(login.getEmplid());

		String url = appgateway.getAppgateway_payroll() +"/api/ebsvendordetails/saveVendorDetailsInEbs";	
		DoubleResponseModel res = new DoubleResponseModel();

		HttpEntity<VendorDetails> request = new HttpEntity<VendorDetails>(vendor, headers);

		ResponseEntity<DoubleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,	DoubleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			System.out.println(res.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("result", res);
		//model.addAttribute("result", "Success");
		return "fragments/vendordetails/createVendor :: createVendor";

	}

	@RequestMapping("/searchVendorElement")
	public String vendor(HttpServletRequest req, Model model) {

		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);

		Element[] elementList = null;
		VendorDetails[] vendorDetails = null;
		String elementURL = appgateway.getAppgateway_payroll() + "/api/element/getelements";
		String vendorURL = appgateway.getAppgateway_payroll() +"/api/ebsvendordetails/getAllVendorDetails";
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<Element[]> response = restTemplate.exchange(elementURL, HttpMethod.GET, request,	Element[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			elementList = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		ResponseEntity<VendorDetails[]> response2 = restTemplate.exchange(vendorURL, HttpMethod.GET, request,VendorDetails[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
			vendorDetails = response2.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response2.getStatusCode());
		}
		model.addAttribute("vendorDetails", vendorDetails);
		model.addAttribute("vendor", vendorDetails);
		model.addAttribute("elementList", elementList);
		return "fragments/vendordetails/vendorElementMapping :: vendorElementMapping";
	}

	@ResponseBody
	@RequestMapping(value = "/searchVendorElement/{vendorid}", method = RequestMethod.GET)
	public VendorElementMapping[] searchVendor(HttpServletRequest req, Model model,
			@PathVariable("vendorid") String vendorid) {

		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);

		VendorElementMapping[] elemMapping = null;
		String url = appgateway.getAppgateway_payroll() +"/api/ebsvendorelementmapping/getVendorElementMappingByVendorId/" + vendorid;
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<VendorElementMapping[]> response = restTemplate.exchange(url, HttpMethod.GET, request,
				VendorElementMapping[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			elemMapping = response.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return elemMapping;
	}

	@PostMapping("/saveVendorMapping")
	public @ResponseBody SingleResponseModel saveVendorMapping(VendorDetailsWrapper vendorDetailsWrapper, Model model,
			 HttpServletRequest req) {

		Login login = (Login) req.getSession().getAttribute("login");
		String VenderElementMappingurl = appgateway.getAppgateway_payroll() +"/api/ebsvendorelementmapping/saveVendorElementMapping";

		for (int i = 0; i < vendorDetailsWrapper.getVendorMapping().size(); i++) {
			vendorDetailsWrapper.getVendorMapping().get(i).setCreatedby(login.getEmplid());
			vendorDetailsWrapper.getVendorMapping().get(i).setModifiedby(login.getEmplid());
			vendorDetailsWrapper.getVendorMapping().get(i).setIsactive("Y");
		}
		SingleResponseModel res = new SingleResponseModel();
		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<List<VendorElementMapping>> request = new HttpEntity<List<VendorElementMapping>>(
				vendorDetailsWrapper.getVendorMapping(), headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(VenderElementMappingurl, HttpMethod.POST,
				request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			System.out.println("res:"+res.toString());		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		//model.addAttribute("result", res.getStatus());
		System.out.println("res:"+res.toString());		
		model.addAttribute("result", res);
		return res;
	}

	@RequestMapping("/edit/editVendor/correctVendor/{vendorId}")
	public String editvendor(@PathVariable("vendorId") String vendorId, Model model) throws URISyntaxException {

		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);

		VendorDetails vendorDetails = null;
		String urlVendor = appgateway.getAppgateway_payroll() +"/api/ebsvendordetails/getVendorDetailsById/" + vendorId;
		System.out.println(urlVendor);
		HttpEntity<VendorDetails> request = new HttpEntity<VendorDetails>(headers);
		ResponseEntity<VendorDetails> response = restTemplate.exchange(urlVendor, HttpMethod.GET, request,
				VendorDetails.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			vendorDetails = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("status", Status.values());
		model.addAttribute("vendorDetails", vendorDetails);
		return "fragments/vendordetails/createVendor :: createVendor";

	}

	@RequestMapping("/searchVendorPerson")
	public String searchVendorPerson(HttpServletRequest req, Model model) {
		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);

	//	Element[] elementList = null;
		VendorDetails[] vendorDetails = null;
		//String elementURL = appgateway.getAppgateway() + "/api/element/getelements";
		String vendorURL = appgateway.getAppgateway_payroll() +"/api/ebsvendordetails/getAllVendorDetails";
		HttpEntity<String> request = new HttpEntity<String>(headers);

//		ResponseEntity<Element[]> response = restTemplate.exchange(elementURL, HttpMethod.GET, request,	Element[].class);
//
//		if (response.getStatusCode() == HttpStatus.OK) {
//			elementList = response.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(response.getStatusCode());
//		}
		ResponseEntity<VendorDetails[]> response2 = restTemplate.exchange(vendorURL, HttpMethod.GET, request,VendorDetails[].class);

		if (response2.getStatusCode() == HttpStatus.OK) {
			vendorDetails = response2.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response2.getStatusCode());
		}
		model.addAttribute("vendorDetails", vendorDetails);
		model.addAttribute("vendor", vendorDetails);
		//model.addAttribute("elementList", elementList);+
		return "fragments/vendordetails/vendorPersonMapping :: vendorPersonMapping";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/searchVendorPersonMapping/{vendorid}", method = RequestMethod.GET)
	public VendorPersonMapping[] searchVendorPerson(HttpServletRequest req, Model model,@PathVariable("vendorid") String vendorid) {

		//HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);

		VendorPersonMapping[]  vendorPersonMapping= null;
		String url = appgateway.getAppgateway_payroll() +"/api/ebsvendorpersonmapping/getVendorPersonMappingByVendorId/" + vendorid;
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<VendorPersonMapping[]> response = restTemplate.exchange(url, HttpMethod.GET, request,VendorPersonMapping[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			vendorPersonMapping = response.getBody();
			System.out.println(response.getStatusCode());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());			
		}
		return vendorPersonMapping;
   }
	
	
	@PostMapping("/saveVendorPersonMapping")
	public @ResponseBody SingleResponseModel saveVendorPersonMapping(VendorPersonMappingWrapper vendorPersonMappingWrapper , Model model,
			 HttpServletRequest req) {
		Login login = (Login) req.getSession().getAttribute("login");
		String VenderPersonMappingurl = appgateway.getAppgateway_payroll() +"/api/ebsvendorpersonmapping/saveVendorPersonMappingInEbs";
		for (int i = 0; i < vendorPersonMappingWrapper.getVendorPersonMapping().size(); i++) {
			vendorPersonMappingWrapper.getVendorPersonMapping().get(i).setCreatedby(login.getEmplid());
			vendorPersonMappingWrapper.getVendorPersonMapping().get(i).setModifiedby(login.getEmplid());
			vendorPersonMappingWrapper.getVendorPersonMapping().get(i).setIsactive("Y");
		}
		SingleResponseModel res = new SingleResponseModel();
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", AccessToken.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<List<VendorPersonMapping>> request = new HttpEntity<List<VendorPersonMapping>>(vendorPersonMappingWrapper.getVendorPersonMapping(), headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(VenderPersonMappingurl, HttpMethod.POST,
				request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			System.out.println("res:::"+res.toString());			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		//model.addAttribute("result", res.getStatus());
		model.addAttribute("result", res);
		return res;
	}
	
	
	
}
