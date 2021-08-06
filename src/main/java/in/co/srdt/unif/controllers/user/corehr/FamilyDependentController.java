package in.co.srdt.unif.controllers.user.corehr;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import in.co.srdt.unif.model.user.absence.CommonDescription;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateLocation;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.user.corehr.PersonDependentDetails;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/personManagement")
public class FamilyDependentController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	SmartValidator validator;

	@Autowired
	private ApplicationGateway appgateway;
	
	FamilyDependentController(){
		
	}
	
	FamilyDependentController(HttpHeaders headers, RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	@RequestMapping("/managePerson")
	public String createPerson(@ModelAttribute("PersonDependentDetails") PersonDependentDetails PersonDependentDetails,
				Model model, HttpServletRequest req) {	
		CreateLocation locationget = new CreateLocation();
	
		//System.out.println("hello Loc");
		CommonLOV[] cd = null;
		CommonLOV[] phonetype = null;
		CommonLOV[] at = null;
		CommonLOV[] countrycode = null;
		CommonLOV[] gender = null;
		CommonLOV[] titleLov = null;
		CommonLOV[] contacttype = null;
		CommonLOV[] state = null;

		Login login= (Login) req.getSession().getAttribute("login");
		
		PersonInformation personInformation =null;			
		String urlperson = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();    	
    	//System.out.println("URL: "+url);
    	
    	headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlperson,HttpMethod.GET, requestPesro,PersonInformation.class);
		
		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
		String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
		String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
		String urlgender = appgateway.getAppgateway() + "/General/loadGender";
		String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
		String urlcontacttype = appgateway.getAppgateway() + "/General/loadContactType";
		String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/"+1;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		ResponseEntity<CommonLOV[]> responseaddress = restTemplate.exchange(urlAddress, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request,
				CommonLOV[].class);
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlcontacttype, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlcontacttype, HttpMethod.GET, request,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response15 = restTemplate.exchange(urlstate, HttpMethod.GET, request,
				CommonLOV[].class);
		
		if (response2.getStatusCode() == HttpStatus.OK) {
			contacttype = response2.getBody();
			
		}
		
		if (response8.getStatusCode() == HttpStatus.OK) {
			gender = response8.getBody();
			
		}
		
		if (response9.getStatusCode() == HttpStatus.OK) {
			titleLov = response9.getBody();
			
		}

		if (response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			at = responseaddress.getBody();
		}
		if (response12.getStatusCode() == HttpStatus.OK) {
			phonetype = response12.getBody();
			
		}
		if (response11.getStatusCode() == HttpStatus.OK) {
			countrycode = response11.getBody();
			
		}
		if (response14.getStatusCode() == HttpStatus.OK) {
			countrycode = response14.getBody();
			
		}
		if (response15.getStatusCode() == HttpStatus.OK) {
			state = response15.getBody();
			
		}

		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("contacttype", contacttype);
		model.addAttribute("title", titleLov);
		model.addAttribute("gender", gender);
		model.addAttribute("countrycode", countrycode);
		model.addAttribute("locationDetails", locationget);
		model.addAttribute("phonetype", phonetype);
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		model.addAttribute("Identifying", Identifying.values());
		model.addAttribute("address", at);
		model.addAttribute("PersonDependentDetails", PersonDependentDetails);
		model.addAttribute("personInfo",personInformation);
		model.addAttribute("state",state);
		
		
		
		model.addAttribute("disability",Identifying.values());
		model.addAttribute("twins", Identifying.values());
		CommonLOV CurrentAddress = null;
		String urlcurrentAddress = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/loadPermanetAddresByPersonNo/"+login.getEmplid();
	//	System.out.println(urlcurrentAddress);
		HttpEntity<String> requestaddr = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV> responsecurrentAddress = restTemplate.exchange(urlcurrentAddress, HttpMethod.GET, requestaddr,CommonLOV.class);
		if (responsecurrentAddress.getStatusCode() == HttpStatus.OK) {
			CurrentAddress = responsecurrentAddress.getBody();
			
		}
		model.addAttribute("currentAdd",CurrentAddress);
		
		return "fragments/user/personManagement :: personManagementInfo";
	}
	
	
	@ResponseBody
	@RequestMapping("/statebind/{countryId}")
	public CommonLOV[] bindState(@PathVariable("countryId") String countryId, Model model) {

		//System.out.println("statebind Country Id"+countryId);
		CommonLOV[] sd = null;
		String url = appgateway.getAppgateway()+"/State/getAllStateByCountry/" + countryId;
		//String url = "http://192.200.12.83:8182/State/getAllStateByCountry/" + countryId;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			//System.out.println("statedata ::::::::::::::::::::" + sd[0].getDescription());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("StateDetails", sd);

		return sd;

	}
	
	
	//@PostMapping(value = "/savePersonManagement")
	@RequestMapping(value = "/savePersonManagement", method = RequestMethod.POST)
	public String savePersonManagement(@ModelAttribute("PersonDependentDetails") PersonDependentDetails PersonDependentDetails,
			//@RequestParam(value="No", required=false) String CR_PM_DEFT_ADD ,
			//@RequestParam(value="twins", required=false) String CR_PM_TWINS,
			//@RequestParam(value="disability", required=false) String CR_PM_DISABILITY ,
			HttpServletRequest req,Model model, BindingResult bindingResult)
	{
		Login login= (Login) req.getSession().getAttribute("login");
		//System.out.println("Hello twins"+CR_PM_TWINS);
//		 if(CR_PM_TWINS==null) {
//			 PersonDependentDetails.setTwins("No");
//		 }
//		 if(CR_PM_DISABILITY==null) {
//			 PersonDependentDetails.setDisability("No");
//		 }
//		 if(CR_PM_DEFT_ADD==null) {
//			 PersonDependentDetails.setSameaspersonaddressdsc("No");
//		 }
		
		//System.out.println("before Sucess::"+PersonDependentDetails.toString());
		 /**********************************************************************************************/
		 CreateLocation locationget = new CreateLocation();
		 PersonInformation personInformation =null;			
			String urlperson = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();    	
	    	//System.out.println("URL: "+url);
	    	
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	         HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
			ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlperson,HttpMethod.GET, requestPesro,PersonInformation.class);
			
			if(responsePerso.getStatusCode() == HttpStatus.OK) {
				personInformation = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}
			
			//System.out.println("hello Loc");
			CommonLOV[] cd = null;
			CommonLOV[] phonetype = null;
			CommonLOV[] at = null;
			CommonLOV[] countrycode = null;
			CommonLOV[] gender = null;
			CommonLOV[] titleLov = null;
			CommonLOV[] contacttype = null;
			String url = appgateway.getAppgateway()+"/Country/getAllCountry";
			String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
			String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
			String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
			String urlgender = appgateway.getAppgateway() + "/General/loadGender";
			String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
			String urlcontacttype = appgateway.getAppgateway() + "/General/loadContactType";
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request13 = new HttpEntity<String>(headers);
			
			ResponseEntity<CommonLOV[]> response13 = restTemplate.exchange(url, HttpMethod.GET, request13, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlAddress, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request13,
					CommonLOV[].class);
			
			ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlcontacttype, HttpMethod.GET, request13,
					CommonLOV[].class);
			
			if (response2.getStatusCode() == HttpStatus.OK) {
				contacttype = response2.getBody();
				
			}
			
			if (response9.getStatusCode() == HttpStatus.OK) {
				titleLov = response9.getBody();
				
			}
			
			if (response8.getStatusCode() == HttpStatus.OK) {
				gender = response8.getBody();
				
			}
			if (response11.getStatusCode() == HttpStatus.OK) {
				countrycode = response11.getBody();
				
			}
			
			if (response12.getStatusCode() == HttpStatus.OK) {
				phonetype = response12.getBody();
			}
			if (response14.getStatusCode() == HttpStatus.OK) {
				at = response14.getBody();
				
			}
			if (response13.getStatusCode() == HttpStatus.OK) {
				cd = response13.getBody();
				
			}
			//////////Added for Profile Pic _Utsav\\\\\\\\\\
			CommonDescription comdes=null;
			String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
			ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
			if (response007.getStatusCode() == HttpStatus.OK) {
				comdes = response007.getBody();
			}
			model.addAttribute("propic",comdes);

			model.addAttribute("contacttype", contacttype);
			model.addAttribute("title", titleLov);
			model.addAttribute("gender", gender);
			model.addAttribute("countrycode", countrycode);
			model.addAttribute("locationDetails", locationget);
			model.addAttribute("phonetype", phonetype);
			model.addAttribute("CountryDetails", cd);
			model.addAttribute("status", Status.values());
			model.addAttribute("Identifying", Identifying.values());
			model.addAttribute("address", at);
			model.addAttribute("PersonDependentDetails", PersonDependentDetails);
			model.addAttribute("personInfo",personInformation);
			//System.out.println("hi");
			
			model.addAttribute("disability",Identifying.values());
			model.addAttribute("twins", Identifying.values());
			
			CommonLOV CurrentAddress = null;
			String urlcurrentAddress = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/loadPermanetAddresByPersonNo/"+login.getEmplid();
			//System.out.println(urlcurrentAddress);
			HttpEntity<String> requestaddr = new HttpEntity<String>(headers);
			ResponseEntity<CommonLOV> responsecurrentAddress = restTemplate.exchange(urlcurrentAddress, HttpMethod.GET, requestaddr,CommonLOV.class);
			if (responsecurrentAddress.getStatusCode() == HttpStatus.OK) {
				CurrentAddress = responsecurrentAddress.getBody();
				
			}
			model.addAttribute("currentAdd",CurrentAddress);
		 /*********************************************************************************************/
			//System.out.println("Sucess before validation::"+PersonDependentDetails.toString());
		    validator.validate(PersonDependentDetails, bindingResult);
			model.addAttribute("bindingResult", bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("result","errrrrr");
				return "fragments/user/personManagement :: personManagementInfo";
			}
	
		String urlPerson=appgateway.getAppgateway()+"/PersonManagement/DependentDetails/savePersonDependentDetails";
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			
			HttpEntity<PersonDependentDetails> request = new HttpEntity<PersonDependentDetails>(PersonDependentDetails, headers);
			System.out.println("Sucess::"+PersonDependentDetails.toString());
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlPerson,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				//
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		
			/*NEW CODE*/
			//OLD CODE//res = restTemplate.postForObject("http://192.200.12.83:9194/api/courseplan/create", coursePlanWrapper, SingleResponseModel.class);
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Person .");
		}
		
		//return res;
		model.addAttribute("result",res.getStatus());
		return "fragments/user/personManagement :: personManagementInfo";
	}
	
	
//	  @GetMapping("/removechild/{index}")
//		public @ResponseBody String removeChild(@SessionAttribute("PersonDependentDetails") PersonDependentDetails PersonDependentDetails, @PathVariable int index)
//		{
//			//System.out.println("index : " + index);
//
//			PersonDependentDetails.getPhonedetails().remove(index);    
//				
//
//			
//			
//			return "removed";
//		}
	
	@ResponseBody
	@RequestMapping(value = "/getDependentDetails", method = RequestMethod.GET)
	public PersonDependentDetails[] personDependentDetails(HttpServletRequest request, Model model) {

		Login login = (Login) request.getSession().getAttribute("login");
		PersonDependentDetails[] res = null;
		String url = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/getDependentDetailsById/" + login.getEmplid();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<PersonDependentDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, req, PersonDependentDetails[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			System.out.println(response.getStatusCode());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
					
		return res;
	}
	
	
	@RequestMapping("/SearchDependent")
	public String searchDependent(Model model, HttpServletRequest req) {		
		
		
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation res1 = null;

		String url2 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println(url2);
		//System.out.println("URL: " + url2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reque = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url2, HttpMethod.GET, reque, PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, reque, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", res1);
		return "fragments/user/personManagementSearch :: dependentSearch";
		
	}

	@RequestMapping("/getviewdependentData/{dependentId}/{mode}")
	public String viewdependentData(@PathVariable("dependentId") String dependentId, @PathVariable("mode") String mode, Model model,
		HttpServletRequest request) throws URISyntaxException {
		Login login = (Login) request.getSession().getAttribute("login");

		
		PersonInformation res1 = null;

		String url2 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reque = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url2, HttpMethod.GET, reque, PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, reque, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", res1);

		String SearchdependentIdId = dependentId;

		PersonDependentDetails personDependentDetails = new PersonDependentDetails();
		String urrpersonDependent = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/getPersonDependentDetailsEditById/" + SearchdependentIdId;

		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> gettravelRequest = new HttpEntity<String>(headerss);

		ResponseEntity<PersonDependentDetails> getdependentResponse = restTemplate.exchange(urrpersonDependent, HttpMethod.GET,
				gettravelRequest, PersonDependentDetails.class);

		if (getdependentResponse.getStatusCode() == HttpStatus.OK) {
			personDependentDetails = getdependentResponse.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(getdependentResponse.getStatusCode());
		}

		model.addAttribute("PersonDependentDetails", personDependentDetails);
		model.addAttribute("mode", mode);
		
		
		
		
		CreateLocation locationget = new CreateLocation();
		
		//System.out.println("hello Loc");
		CommonLOV[] cd = null;
		CommonLOV[] phonetype = null;
		CommonLOV[] at = null;
		CommonLOV[] countrycode = null;
		CommonLOV[] gender = null;
		CommonLOV[] titleLov = null;
		CommonLOV[] contacttype = null;
		CommonLOV[] state = null;

		
		
		PersonInformation personInformation =null;			
		String urlperson = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();    	
    	//System.out.println("URL: "+url);
    	
    	headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlperson,HttpMethod.GET, requestPesro,PersonInformation.class);
		
		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		

		String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
		String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
		String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
		String urlgender = appgateway.getAppgateway() + "/General/loadGender";
		String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
		String urlcontacttype = appgateway.getAppgateway() + "/General/loadContactType";
		String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/"+1;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request13 = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response13 = restTemplate.exchange(url, HttpMethod.GET, request13, CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlAddress, HttpMethod.GET, request13,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request13,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request13,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request13,
				CommonLOV[].class);
		ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request13,
				CommonLOV[].class);
		
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlcontacttype, HttpMethod.GET, request13,
				CommonLOV[].class);

		ResponseEntity<CommonLOV[]> response15 = restTemplate.exchange(urlstate, HttpMethod.GET, request13,
				CommonLOV[].class);
		
		if (response2.getStatusCode() == HttpStatus.OK) {
			contacttype = response2.getBody();
			
		}
		
		if (response9.getStatusCode() == HttpStatus.OK) {
			titleLov = response9.getBody();
			
		}
		
		if (response8.getStatusCode() == HttpStatus.OK) {
			gender = response8.getBody();
			
		}
		if (response11.getStatusCode() == HttpStatus.OK) {
			countrycode = response11.getBody();
			
		}
		
		if (response12.getStatusCode() == HttpStatus.OK) {
			phonetype = response12.getBody();
		}
		if (response14.getStatusCode() == HttpStatus.OK) {
			at = response14.getBody();
			
		}
		if (response13.getStatusCode() == HttpStatus.OK) {
			cd = response13.getBody();
			
		}
		if (response15.getStatusCode() == HttpStatus.OK) {
			state = response15.getBody();
			
		}
		
		model.addAttribute("disability",Identifying.values());
		model.addAttribute("twins", Identifying.values());

		
		model.addAttribute("contacttype", contacttype);
		model.addAttribute("title", titleLov);
		model.addAttribute("gender", gender);
		model.addAttribute("countrycode", countrycode);
		model.addAttribute("locationDetails", locationget);
		model.addAttribute("phonetype", phonetype);
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("status", Status.values());
		model.addAttribute("Identifying", Identifying.values());
		model.addAttribute("address", at);
		model.addAttribute("state", state);
		//model.addAttribute("PersonDependentDetails", PersonDependentDetails);
		model.addAttribute("personInfo",personInformation);
		
		
		CommonLOV CurrentAddress = null;
		String urlcurrentAddress = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/loadPermanetAddresByPersonNo/"+login.getEmplid();
		//System.out.println(urlcurrentAddress);
		HttpEntity<String> requestaddr = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV> responsecurrentAddress = restTemplate.exchange(urlcurrentAddress, HttpMethod.GET, requestaddr,CommonLOV.class);
		if (responsecurrentAddress.getStatusCode() == HttpStatus.OK) {
			CurrentAddress = responsecurrentAddress.getBody();
			
		}
		model.addAttribute("currentAdd",CurrentAddress);
	//	System.out.println("add"+personDependentDetails.toString());
		return "fragments/user/personManagement :: personManagementInfo";

	}
	
	
	
	
	
	
	@RequestMapping(value = "/updatePersonManagement", method = RequestMethod.POST)
	public String updatePersonManagement(@ModelAttribute("PersonDependentDetails") PersonDependentDetails PersonDependentDetails,
			//@RequestParam(value="No", required=false) String CR_PM_DEFT_ADD ,
			//@RequestParam(value="No", required=false) String CR_PM_TWINS,
			//@RequestParam(value="disability", required=false) String CR_PM_DISABILITY ,
			
			Model model,BindingResult bindingResult,HttpServletRequest req)
	{
//		System.out.println("CR_PM_DISABILITY ::"+ CR_PM_DISABILITY);
		String mode = "edit";
		Login login= (Login) req.getSession().getAttribute("login");
//		 if(CR_PM_TWINS==null) {
//			 PersonDependentDetails.setTwins("No");
//		 }
//		 if(CR_PM_DISABILITY==null) {
//			 PersonDependentDetails.setDisability("No");
//		 }
//		 if(CR_PM_DEFT_ADD==null) {
//			 PersonDependentDetails.setSameaspersonaddressdsc("No");
//		 }
		 CreateLocation locationget = new CreateLocation();
   		 PersonInformation personInformation =null;			
			String urlperson = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();    	
	    	//System.out.println("URL: "+url);
	    	
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	         HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
			ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlperson,HttpMethod.GET, requestPesro,PersonInformation.class);
			
			if(responsePerso.getStatusCode() == HttpStatus.OK) {
				personInformation = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}
		//	System.out.println("Request Failed666");
			//System.out.println("hello Loc");
			CommonLOV[] cd = null;
			CommonLOV[] phonetype = null;
			CommonLOV[] at = null;
			CommonLOV[] countrycode = null;
			CommonLOV[] gender = null;
			CommonLOV[] titleLov = null;
			CommonLOV[] contacttype = null;
			CommonLOV[] state = null;
			String url = appgateway.getAppgateway()+"/Country/getAllCountry";
			String urlAddress = appgateway.getAppgateway()+"/General/loadAddressTypeObject";
			String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
			String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
			String urlgender = appgateway.getAppgateway() + "/General/loadGender";
			String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
			String urlcontacttype = appgateway.getAppgateway() + "/General/loadContactType";
			String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/"+1;
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request13 = new HttpEntity<String>(headers);
			
			ResponseEntity<CommonLOV[]> response13 = restTemplate.exchange(url, HttpMethod.GET, request13, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlAddress, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request13,
					CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request13,
					CommonLOV[].class);
			
			ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlcontacttype, HttpMethod.GET, request13,
					CommonLOV[].class);
			
			ResponseEntity<CommonLOV[]> response15 = restTemplate.exchange(urlstate, HttpMethod.GET, request13,
					CommonLOV[].class);
			
			if (response2.getStatusCode() == HttpStatus.OK) {
				contacttype = response2.getBody();
				
			}
			
			if (response9.getStatusCode() == HttpStatus.OK) {
				titleLov = response9.getBody();
				
			}
			
			if (response8.getStatusCode() == HttpStatus.OK) {
				gender = response8.getBody();
				
			}
			if (response11.getStatusCode() == HttpStatus.OK) {
				countrycode = response11.getBody();
				
			}
			
			if (response12.getStatusCode() == HttpStatus.OK) {
				phonetype = response12.getBody();
			}
			if (response14.getStatusCode() == HttpStatus.OK) {
				at = response14.getBody();
				
			}
			if (response13.getStatusCode() == HttpStatus.OK) {
				cd = response13.getBody();
				
			}
			if (response15.getStatusCode() == HttpStatus.OK) {
				state = response15.getBody();
				
			}
			//////////Added for Profile Pic _Utsav\\\\\\\\\\
			CommonDescription comdes=null;
			String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
			ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
			if (response007.getStatusCode() == HttpStatus.OK) {
				comdes = response007.getBody();
			}
			model.addAttribute("propic",comdes);

			model.addAttribute("state", state);
			model.addAttribute("contacttype", contacttype);
			model.addAttribute("title", titleLov);
			model.addAttribute("gender", gender);
			model.addAttribute("countrycode", countrycode);
			model.addAttribute("locationDetails", locationget);
			model.addAttribute("phonetype", phonetype);
			model.addAttribute("CountryDetails", cd);
			model.addAttribute("status", Status.values());
			model.addAttribute("Identifying", Identifying.values());
			model.addAttribute("address", at);
			model.addAttribute("PersonDependentDetails", PersonDependentDetails);
			model.addAttribute("personInfo",personInformation);
			model.addAttribute("mode", mode);
			
			model.addAttribute("disability",Identifying.values());
			model.addAttribute("twins", Identifying.values());
			
			
			CommonLOV CurrentAddress = null;
			String urlcurrentAddress = appgateway.getAppgateway() + "/PersonManagement/DependentDetails/loadPermanetAddresByPersonNo/"+login.getEmplid();
			//System.out.println(urlcurrentAddress);
			HttpEntity<String> requestaddr = new HttpEntity<String>(headers);
			ResponseEntity<CommonLOV> responsecurrentAddress = restTemplate.exchange(urlcurrentAddress, HttpMethod.GET, requestaddr,CommonLOV.class);
			if (responsecurrentAddress.getStatusCode() == HttpStatus.OK) {
				CurrentAddress = responsecurrentAddress.getBody();
				
			}
			model.addAttribute("currentAdd",CurrentAddress);
			
		 /*********************************************************************************************/
		    validator.validate(PersonDependentDetails, bindingResult);
			model.addAttribute("bindingResult", bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("result","errrrrr");
				return "fragments/user/personManagement :: personManagementInfo";
			}
	
			//System.out.println("PersonDependentDetails after validation::"+ PersonDependentDetails.toString());

		//	System.out.println("Request Failed 6");
		 
		String urlPerson=appgateway.getAppgateway()+"/PersonManagement/DependentDetails/correctPersonDependentDetails";
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			
			HttpEntity<PersonDependentDetails> request = new HttpEntity<PersonDependentDetails>(PersonDependentDetails, headers);
		//	System.out.println("Request Failed 7");
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlPerson,HttpMethod.PUT,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				//System.out.println("PersonDependentDetails update::"+ PersonDependentDetails.toString());
				
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		
			/*NEW CODE*/
			//OLD CODE//res = restTemplate.postForObject("http://192.200.12.83:9194/api/courseplan/create", coursePlanWrapper, SingleResponseModel.class);
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Person .");
		}
		
		//return res;
		model.addAttribute("result",res.getStatus());
		return "fragments/user/personManagement :: personManagementInfo";
	}
	
	
	
	
}
