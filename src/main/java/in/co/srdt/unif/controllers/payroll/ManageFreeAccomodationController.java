package in.co.srdt.unif.controllers.payroll;


import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.AccomodationRent;
import in.co.srdt.unif.model.payroll.PersonSearch;
import in.co.srdt.unif.model.search.DepartmentSearch;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManageFreeAccomodationController {

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    String pnum="";
    
    @GetMapping("/manageaccommodation")
    public String getManageAccomodationPage(Model model) {

    	
        CommonLOV[] accmodationType = null;

        String urlAccmodation = appgateway.getAppgateway() + "/General/loadAccmodationType";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
     
        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlAccmodation, HttpMethod.GET, request, CommonLOV[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accmodationType = response.getBody();

        }
        
        
        model.addAttribute("accmodationType", accmodationType);
//        return "forms/payroll/transactions/manageAccomodation :: freeAccomodtion";
    	return "forms/payroll/transactions/searchRFA :: searchRfa";
    }
    
    
    @ResponseBody
	@PostMapping(path="/accomodation/search/personaccomodation")
	public AccomodationRent[] getPersonAccomodationSearch(HttpServletRequest requestFromDT) {
		
		
		String personnumber = requestFromDT.getParameter("personnumber");
		String personname = requestFromDT.getParameter("personname");
		String rfatype = requestFromDT.getParameter("rfatype");
		String fromdate = requestFromDT.getParameter("fromdate");
		String todate = requestFromDT.getParameter("todate");
		
		
		AccomodationRent[] accomodationRent = null;
		
		String url = appgateway.getAppgateway_payroll()+"/api/payleaseperk/getleaseperksearchbydatetype";
		
		String payLode = "{" +
						"\"personnumber\"" + ":\"" +personnumber+ "\"," +
						"\"personname\"" + ":\"" +personname+ "\"," +
						"\"rfatype\"" + ":\"" +rfatype+ "\"," +						
						"\"fromdate\"" + ":\"" +fromdate+ "\"," +
						"\"todate\"" + ":\"" +todate+ "\"" +						
						
						"}";
		System.out.println("payload::"+payLode +"  url::"+url);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<AccomodationRent[]> response = restTemplate.exchange(url, HttpMethod.POST, request, AccomodationRent[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			accomodationRent = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return accomodationRent;
	}
    
    
    

   //@GetMapping("/accomodation/rent/createaccommodation")
    @RequestMapping("/accomodation/rent/createaccommodation/{leaseid}/{personId}")
    public String createAccomodationPage(@PathVariable("leaseid") String leaseid, 
			@PathVariable("personId") String personId,@ModelAttribute("accomodation") AccomodationRent acco, Model model, HttpServletRequest req) {

        System.out.println("leaseid:"+leaseid+"personId:"+personId);
        pnum=personId;
        
        
        CommonLOV[] accmodationType = null;
        String urlDatasets = appgateway.getAppgateway() + "/General/loadAccmodationType";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accmodationType = response.getBody();

        }

        model.addAttribute("accomo", accmodationType);
        model.addAttribute("accmodationType", accmodationType);
        
        PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + personId;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println(responsePerso.getStatusCode());
		}
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+personId+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		
		
        return "forms/payroll/transactions/createAccomodation :: createfreeAccomodtion";
    }
    
    
    
    
    @RequestMapping("/rfa/createnew/personRfa")
    public String createAccomodationNewPerson(@ModelAttribute("accomodation") AccomodationRent acco, Model model, HttpServletRequest req) {

        CommonLOV[] accmodationType = null;
        String urlDatasets = appgateway.getAppgateway() + "/General/loadAccmodationType";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accmodationType = response.getBody();

        }

        model.addAttribute("accomo", accmodationType);
        model.addAttribute("accmodationType", accmodationType);
        System.out.println("in:::");
        return "forms/payroll/transactions/manageNewPersonRfa";
    }
    
    

    @ResponseBody
    @PostMapping("/seach/accomodation/searchRentAccomodation")
    public AccomodationRent[] getAccomodationRentSearch(HttpServletRequest requestFromDT) {

        String personnumber = pnum;// requestFromDT.getParameter("personnumber");
        String rfaid = requestFromDT.getParameter("rfaid");
        String startdt = requestFromDT.getParameter("startdt");

        String url = appgateway.getAppgateway_payroll() + "/api/payleaseperk/getleaseperksearch";
        AccomodationRent[] accomodationRent = null;
        
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(startdt.isEmpty()||startdt==""||startdt==null){
            startdt="";
        }
        String payload = "{" +
                "\"personnumber\"" + ":\"" + personnumber + "\"," +
                "\"startdt\"" + ":\"" + startdt + "\"," +
                "\"rfaid\"" + ":\"" + rfaid + "\"" +
                "}";
        System.out.println("payload::"+payload);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(payload, headers);
        ResponseEntity<AccomodationRent[]> response = restTemplate.exchange(url, HttpMethod.POST, request, AccomodationRent[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accomodationRent = response.getBody();
        }

        return accomodationRent;
    }
    
    
    @ResponseBody
    @PostMapping("/seach/accomodation/searchRentAccomodation/addnewperson")
    public AccomodationRent[] getAccomodationNewPerson(HttpServletRequest requestFromDT) {

        String personnumber =  requestFromDT.getParameter("personnumber");
        String rfaid = requestFromDT.getParameter("rfaid");
        String startdt = requestFromDT.getParameter("startdt");

        String url = appgateway.getAppgateway_payroll() + "/api/payleaseperk/getleaseperksearch";
        AccomodationRent[] accomodationRent = null;
        
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(startdt.isEmpty()||startdt==""||startdt==null){
            startdt="";
        }
        String payload = "{" +
                "\"personnumber\"" + ":\"" + personnumber + "\"," +
                "\"startdt\"" + ":\"" + startdt + "\"," +
                "\"rfaid\"" + ":\"" + rfaid + "\"" +
                "}";
        System.out.println("payload::"+payload);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(payload, headers);
        ResponseEntity<AccomodationRent[]> response = restTemplate.exchange(url, HttpMethod.POST, request, AccomodationRent[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accomodationRent = response.getBody();
        }

        return accomodationRent;
    }
    
    
    
    @ResponseBody
    @GetMapping("/accomodation/calculatemount/rfacalculate/{personId}/{accomodationId}")
    public Double calculaterfa(@PathVariable("personId") String personId,@PathVariable("accomodationId") String accomodationId,HttpServletRequest requestFromDT) {

        String url = appgateway.getAppgateway_payroll() + "/api/payleaseperk/getcallculateamt/"+personId+"/"+accomodationId;
        Double cd = null;
        
       System.out.println("url cal::"+url);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>( headers);
        ResponseEntity<Double> response = restTemplate.exchange(url, HttpMethod.GET, request, Double.class);

        if (response.getStatusCode() == HttpStatus.OK) {
        	cd = response.getBody();
        }

        return cd;
    }


    @ResponseBody
    @PostMapping(path = "/accomodation/searchAccomodation/gePersonId")
    public PersonSearch[] PersonSearch(HttpServletRequest requestFromDT) {

       

        String personNumber = requestFromDT.getParameter("personNumber");
        String name = requestFromDT.getParameter("name");
        String personId = requestFromDT.getParameter("personId");
        String noe = requestFromDT.getParameter("noe");
        headers.setContentType(MediaType.APPLICATION_JSON);

        PersonSearch[] PersonSearch = null;
        String urlLegal = appgateway.getAppgateway_payroll() + "/api/payleaseperk/getpersondetails";

        String payLode = "{" +
                "\"personNumber\"" + ":\"" + personNumber + "\"," +
                "\"name\"" + ":\"" + name + "\"," +
                "\"personId\"" + ":\"" + personId + "\"," +
                "\"noe\"" + ":\"" + noe + "\"" +
                "}";

        //headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
        ResponseEntity<PersonSearch[]> response = restTemplate.exchange(urlLegal, HttpMethod.POST, request, PersonSearch[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            PersonSearch = response.getBody();
           } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        return PersonSearch;
    }

    @RequestMapping(value = "/accomodation/saveAccomodation/{mode}", method = RequestMethod.POST)
    public String saveAccomodation(@PathVariable("mode") String mode,@ModelAttribute("accomodation") AccomodationRent accomodationRent, Model model,HttpServletRequest req) {

    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        String urlAcco ="";
        CommonLOV[] accmodationType = null;
        
        accomodationRent.setOverrideamt(0);
        accomodationRent.setCalculateamt(0);
        
        String urlDatasets = appgateway.getAppgateway() + "/General/loadAccmodationType";
       

        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            accmodationType = response.getBody();

        }
       // PersonInformation personInformation = null;
        Login login = (Login) req.getSession().getAttribute("login");
        
        accomodationRent.setCreatedBy(login.getEmplid());
        
        
//        String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
//        HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
//        ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
//                PersonInformation.class);
//
//        if (responsePerso.getStatusCode() == HttpStatus.OK) {
//            personInformation = responsePerso.getBody();
//        } else {
//            System.out.println("Request Failed");
//            System.out.println(responsePerso.getStatusCode());
//        }
        model.addAttribute("accomo", accmodationType);


//        model.addAttribute("personInfo", personInformation);
        model.addAttribute("accmodationType", accmodationType);

        if(mode.equals("edit")) {
        	urlAcco = appgateway.getAppgateway_payroll() + "/api/payleaseperk/editLeasePerk";
        }
        if(mode.equals("add")) {
        	urlAcco = appgateway.getAppgateway_payroll() + "/api/payleaseperk/addleaseperk";
        } 
        

        System.out.println("payload::"+accomodationRent.toString()+":::"+mode);
        SingleResponseModel res = new SingleResponseModel();
        try {
            HttpEntity<AccomodationRent> request1 = new HttpEntity<AccomodationRent>(accomodationRent, headers);
            ResponseEntity<SingleResponseModel> response1 = restTemplate.exchange(urlAcco, HttpMethod.POST, request1, SingleResponseModel.class);
            if (response1.getStatusCode() == HttpStatus.OK) {
                res = response1.getBody();
                System.out.println(res.getMessage());
            } else {
                System.out.println("Request Failed");
                System.out.println(response1.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("Failed to save Accomodation.");
        }
        model.addAttribute("finalres", res.getMessage());
        return "forms/payroll/transactions/createAccomodation :: createfreeAccomodtion";
    }


}