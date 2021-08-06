package in.co.srdt.unif.controllers.Miscellaneous;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.Eligibility.Eligibility;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.Eligibility.search.EligibilitySearchResult;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@SessionAttributes("eligibility")
@Controller
@RequestMapping("/eligibility")
public class EligibilityController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
    private HttpHeaders headers;
	
	@Autowired
	SmartValidator validator;
    
    @RequestMapping("/manageEligibility")
	public String manageEnterprise(Model model,HttpServletRequest req) {
           
    //System.out.println("elig");
    return "fragments/Miscellaneous/manageEligibility :: manageeligibility1";
    }

    @ResponseBody
    @PostMapping(path="/searchEligibility/getEligibilityId")
    public EligibilitySearchResult[] eligibilitySearchResult(HttpServletRequest requestFromDT) {


        String name = requestFromDT.getParameter("name");
        String code = requestFromDT.getParameter("code");

       // System.out.println("EligibilitySearchResult:::::::::::::::::::"+name);

        EligibilitySearchResult[] EligibilitySearchResult = null;

        String urlEligibility = appgateway.getAppgatewayabs()+"/Eligibility/EligibilitySearchList";

        String payLode = "{" +
                "\"name\"" + ":\"" +name+ "\"," +
                "\"code\"" + ":\"" +code+ "\"" +
                "}";

        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

        ResponseEntity<EligibilitySearchResult[]> response = restTemplate.exchange(urlEligibility, HttpMethod.POST, request, EligibilitySearchResult[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            EligibilitySearchResult = response.getBody();


        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
       // System.out.println("ELig");

 
        return EligibilitySearchResult;
    }



    @RequestMapping("/createEligibility")
	public String createEnterprise(Model model, @ModelAttribute("Eligibility") Eligibility elig, HttpServletRequest req) {

    //System.out.println("createelig");
        model.addAttribute("eligible", new Eligibility());

    return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";
    }


    @RequestMapping("/editEligibility/{eligibilityid}")
    public String editEligibility(@PathVariable("eligibilityid") String eligibilityid, Model model) {

        Eligibility sd1=null;
        String urlp = appgateway.getAppgatewayabs()+"/Eligibility/getEligibilityById/" + eligibilityid;
 
      //  System.out.println(urlp);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Eligibility> response = restTemplate.exchange(urlp, HttpMethod.GET, request, Eligibility.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            sd1 = response.getBody();

        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        //System.out.println("Repeating Period::::::::::"+sd1[0].toString());

        model.addAttribute("eligible", sd1);

        return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";

    }

    @PostMapping("/setEligibility")
	public String postEligibility(@ModelAttribute("eligibilitydetails") Eligibility eligibilitydetails, Model model, BindingResult bindingResult) {
		// urlsave=appgateway.getAppgateway()+"/PersonData/PersonalRecord/savePersonalRecord";
        String urlsave = appgateway.getAppgatewayabs()+"/Eligibility/saveAllEligibility";
        model.addAttribute("eligible",eligibilitydetails);
       // System.out.println("Master Data :::::::::::::::::::" + eligibilitydetails.toString());
        SingleResponseModel res = new SingleResponseModel();
        

		validator.validate(eligibilitydetails, bindingResult);
		
		
        
        //System.out.println(bindingResult.getAllErrors());
		model.addAttribute("bindingResult", bindingResult);
		if (bindingResult.hasErrors()) {
			return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";
		}
        HttpEntity<Eligibility> request = new HttpEntity<Eligibility>(eligibilitydetails, headers);
        System.out.print("DATACollect:::::::::::::::::::" + urlsave);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request,
                SingleResponseModel.class);
       //System.out.println("eligibility body:::::"+response.);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.print("DATA:::::::::::::::::::" + res);
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        model.addAttribute("response",res);
        //System.out.println(res.getMessage());

        return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";

        }

    
    @PostMapping("/correctEligibility")
    public String correctEligibility(@ModelAttribute("eligibilitydetails") Eligibility eligibilitydetails, Model model, BindingResult bindingResult) {
        // urlsave=appgateway.getAppgateway()+"/PersonData/PersonalRecord/savePersonalRecord";
    	
        String urlsave = appgateway.getAppgatewayabs()+"/Eligibility/correctEligibility";
        model.addAttribute("eligible",eligibilitydetails);
       // System.out.println("Master Data :::::::::::::::::::" + eligibilitydetails.toString());
        SingleResponseModel res = new SingleResponseModel();
        
        validator.validate(eligibilitydetails, bindingResult);
        
       // System.out.println(bindingResult.getAllErrors());
		model.addAttribute("bindingResult", bindingResult);
		if (bindingResult.hasErrors()) {
			return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";
		}
		
        HttpEntity<Eligibility> request = new HttpEntity<Eligibility>(eligibilitydetails, headers);
        System.out.print("DATACollect:::::::::::::::::::" + urlsave);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.PUT, request,
                SingleResponseModel.class);
        //System.out.println("eligibility body:::::"+response.);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.print("DATA:::::::::::::::::::" + res);
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        model.addAttribute("response",res);
       // System.out.println(res.getMessage());

        return "fragments/Miscellaneous/eligibilityprofile :: createeligibility";

    }


    @ResponseBody
    @RequestMapping("/getCriteriavalues")
	public CommonLOV[] getCriteriavalues(Model model, HttpServletRequest req,String cmd) {
        CommonLOV[] critValues = null;

        HttpEntity<String> request = new HttpEntity<String>(headers);
       // System.out.println("criteria");
        String urlgender = appgateway.getAppgateway()+"/Eligibility/Lov/getEligibilityCriteria/"+cmd;
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response8.getStatusCode() == HttpStatus.OK) {
            critValues = response8.getBody();
			
		}
        model.addAttribute("critValues", critValues);
      //  System.out.println("gender"+critValues[0].getDescription());
    return critValues;
    }


    @GetMapping("/removechild/{index}")
    public @ResponseBody String removeChild(@SessionAttribute("eligibility") Eligibility eligibility, @PathVariable int index)
    {
        //System.out.println("index : " + index);

        eligibility.getEligibilitycriteria().remove(index);

        return "removed";
    }



}