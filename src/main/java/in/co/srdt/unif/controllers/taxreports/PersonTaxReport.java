package in.co.srdt.unif.controllers.taxreports;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.taxdeclaration.MedicalTaxModel;
import in.co.srdt.unif.model.taxdeclaration.PersonTaxEarning;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/person/tax/")
public class PersonTaxReport {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@RequestMapping("/managetaxReprot")
	public String manageTaxReport(Model model, HttpServletRequest req) {
		
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		
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
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		
		CommonDescription paygroup=null;
		String urlpaygroup= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/paygroup";
		ResponseEntity<CommonDescription> responsePaygroup= restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (responsePaygroup.getStatusCode() == HttpStatus.OK) {
			paygroup = responsePaygroup.getBody();
		}
		
		CalendarPeriod[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getFinalizeCalendarsByPaygroupid/"+ paygroup.getDescription();
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<CalendarPeriod[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, CalendarPeriod[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		return "forms/taxReport/manageTaxReport :: taxReport";
	}
	
	
//	    @ResponseBody
//	    @GetMapping("/manage/TaxCalTotalEarning/{financialYear}")
//	    public PersonTaxEarning[] TaxCalTotalEarning(@PathVariable("financialYear") String financialYear,HttpServletRequest req) {
//		 PersonTaxEarning[] earning=null;
//		 Login login = (Login) req.getSession().getAttribute("login");
//	        String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalEarning/"+login.getEmplid()+"/"+financialYear;
//		     //String url = "http://localhost:9127/api/rfa_report/getLeaseQtrReport/"+financialYear+"/"+businessunit+"/"+rfatype;
//	       System.out.println("url::"+url);
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//	        HttpEntity<String> request = new HttpEntity<String>( headers);
//	        ResponseEntity<PersonTaxEarning[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PersonTaxEarning[].class);
//
//	        if (response.getStatusCode() == HttpStatus.OK) {
//	        	earning = response.getBody();
//	        }
//
//	        return earning;
//	    }
//	    
	    
//	    @ResponseBody
//	    @GetMapping("/manage/TaxCalTotalPerquisites/{financialYear}")
//	    public PersonTaxEarning[] TaxCalTotalPerquisites(@PathVariable("financialYear") String financialYear,HttpServletRequest req) {
//		 PersonTaxEarning[] earning=null;
//		 Login login = (Login) req.getSession().getAttribute("login");
//	        String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalPerquisites/"+login.getEmplid()+"/"+financialYear;
//		     //String url = "http://localhost:9127/api/rfa_report/getLeaseQtrReport/"+financialYear+"/"+businessunit+"/"+rfatype;
//	       System.out.println("url::"+url);
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//	        HttpEntity<String> request = new HttpEntity<String>( headers);
//	        ResponseEntity<PersonTaxEarning[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PersonTaxEarning[].class);
//
//	        if (response.getStatusCode() == HttpStatus.OK) {
//	        	earning = response.getBody();
//	        }
//
//	        return earning;
//	    }
	
	    @ResponseBody
	    @GetMapping("/manage/TaxCalTotalEarning/{financialYear}")
	    public LinkedHashMap<String,String> TaxCalTotalEarning(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear) {
			LinkedHashMap<String,String> manage_earning_element = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalEarning/"+login.getEmplid()+"/"+financialYear;
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     

	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(urlHOME,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_earning_element = response.getBody();
	        }
	     
	        return manage_earning_element;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/TaxCalTotalPerquisites/{financialYear}")
	    public LinkedHashMap<String,String> TaxCalTotalPerquisites(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear) {
			LinkedHashMap<String,String> manage_Perquisites = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalPerquisites/"+login.getEmplid()+"/"+financialYear;
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     

	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(urlHOME,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Perquisites = response.getBody();
	        }
	     
	        return manage_Perquisites;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/TaxCal80c")
	    public LinkedHashMap<String,String> TaxCal80c(HttpServletRequest request, Model model) {
			LinkedHashMap<String,String> manage_Perquisites = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String urlHOME = appgateway.getAppgateway_payroll() + " /api/tax_report/getTaxCal80c/"+login.getEmplid();
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     

	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(urlHOME,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Perquisites = response.getBody();
	        }
	     
	        return manage_Perquisites;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/chapter6A/{financialYear}")
	    public LinkedHashMap<String,String> chapter6A(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear) {
			LinkedHashMap<String,String> manage_Perquisites = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalChapter6a/"+login.getEmplid()+"/"+financialYear;
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     

	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Perquisites = response.getBody();
	        }
	   
	        return manage_Perquisites;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/TotalCalculation/{financialYear}")
	    public LinkedHashMap<String,String> TotalCalculation(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear) {
			LinkedHashMap<String,String> manage_Calculation = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalPayroll80cChapter6aData/"+login.getEmplid()+"/"+financialYear;
	        ///api/tax_report/getTaxCalPayroll80cChapter6aData
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("financialYear"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Calculation = response.getBody();
	        }
	         System.out.println("tax cal"+url);
	        return manage_Calculation;
	    }
	
	    
	    
	    @ResponseBody
	    @RequestMapping(value = "/manage/manageTaxCalElementDataByYear/{financialYear}/{elementid}/{type}")
	    public LinkedHashMap<String,String> TaxCalElementDataByYear(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("elementid") String elementid,@PathVariable("type") String type) {
			LinkedHashMap<String,String> manageTaxCalElementDataByYear = null;
			 Login login = (Login) request.getSession().getAttribute("login");
			 String urlTaxCal ="";
			 if(type.equals("A")) {
				    urlTaxCal = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalElementWiseActual/"+login.getEmplid()+"/"+  financialYear+"/"+elementid; 
			 }else if(type.equals("P")) {
				    urlTaxCal = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalElementWiseProjected/"+login.getEmplid()+"/"+  financialYear+"/"+elementid;
			 }
	     System.out.println(urlTaxCal);
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);
	        
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(urlTaxCal,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manageTaxCalElementDataByYear = response.getBody();
	        }
	      
	        return manageTaxCalElementDataByYear;
	    }
}
