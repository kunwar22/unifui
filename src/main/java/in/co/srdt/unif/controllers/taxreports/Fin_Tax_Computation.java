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

import in.co.srdt.unif.model.fintaxreport.TaxReportModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.taxdeclaration.MedicalTaxModel;
import in.co.srdt.unif.model.taxdeclaration.PersonTaxEarning;
import in.co.srdt.unif.model.taxdeclaration.RFACommonModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/fin/tax")
public class Fin_Tax_Computation {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	String person_number = "";

	@RequestMapping("/manage/Finance/TaxComputation")
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
		
		return "forms/taxReport/fin_TaxComputationReport :: fin_tax_report";
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
		     String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalEarning/"+person_number+"/"+financialYear;
	        
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
		     String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalTotalPerquisites/"+person_number+"/"+financialYear;
	        
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
		     String urlHOME = appgateway.getAppgateway_payroll() + " /api/tax_report/getTaxCal80c/"+person_number;
	        
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
		     String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalChapter6a/"+person_number+"/"+financialYear;
	        
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
	    @GetMapping("/manage/TotalCalculation/{financialYear}/{pnum}")
	    public LinkedHashMap<String,String> TotalCalculation(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Calculation = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalPayroll80cChapter6aData/"+person_number+"/"+financialYear;
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
	        
	        return manage_Calculation;
	    }
	
	    
	    
	    @ResponseBody
	    @RequestMapping(value = "/manage/manageTaxCalElementDataByYear/{financialYear}/{elementid}/{type}")
	    public LinkedHashMap<String,String> TaxCalElementDataByYear(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("elementid") String elementid,@PathVariable("type") String type) {
			LinkedHashMap<String,String> manageTaxCalElementDataByYear = null;
			 Login login = (Login) request.getSession().getAttribute("login");
			 String urlTaxCal ="";
			 if(type.equals("A")) {
				    urlTaxCal = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalElementWiseActual/"+person_number+"/"+  financialYear+"/"+elementid; 
			 }else if(type.equals("P")) {
				    urlTaxCal = appgateway.getAppgateway_payroll() + "/api/tax_report/getTaxCalElementWiseProjected/"+person_number+"/"+  financialYear+"/"+elementid;
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
	    
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/salary_details/{financialYear}/{pnum}")
	    public LinkedHashMap<String,String> salary_details(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Salary_cal = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/taxreport/getTaxSalaryDetailsDuringFinancialYear/"+person_number+"/"+financialYear;
	        ///api/tax_report/getTaxCalPayroll80cChapter6aData
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("financialYear"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Salary_cal = response.getBody();
	        }
	         System.out.println("salary cal"+url);
	        return manage_Salary_cal;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/declaration_details/{financialYear}/{pnum}")
	    public LinkedHashMap<String,String> declaration_details(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Salary_cal = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/taxreport/getFinancialYearAmtBasisOnVerified/"+person_number+"/"+financialYear;
			// String url =  "http://10.8.20.35:8075/api/taxreport/getFinancialYearAmtBasisOnVerified/"+person_number+"/"+financialYear;
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("financialYear"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Salary_cal = response.getBody();
	        }
	         System.out.println("salary cal"+url);
	        return manage_Salary_cal;
	    }
	    
	    @ResponseBody
	    @GetMapping("/manage/SectionDescriptionAndAmmount/{fin_year}/{per_num}")
	    public TaxReportModel[] SectionDescription(@PathVariable("fin_year") String fin_year,@PathVariable("per_num") String per_num,HttpServletRequest requestFromDT) {
	    	TaxReportModel[] taxreport=null;
	        String url = appgateway.getAppgateway_payroll()+"/api/taxreport/getSectionDescriptionAndAmmountBy/"+per_num+"/"+fin_year;
		   
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>( headers);
	        ResponseEntity<TaxReportModel[]> response = restTemplate.exchange(url, HttpMethod.GET, request, TaxReportModel[].class);
	        
	        if (response.getStatusCode() == HttpStatus.OK) {
	        	taxreport = response.getBody();
	        }

	        return taxreport;
	    }
	    
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/lease_declaration_details/{financialYear}/{pnum}/{leaseid}")
	    public LinkedHashMap<String,String> lease_declaration_details(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum,@PathVariable("leaseid") String leaseid) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Salary_cal = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/taxreport/getmonthAndAmtFindBy/"+person_number+"/"+leaseid+"/"+financialYear;
			// String url =  "http://10.8.20.35:8075/api/taxreport/getFinancialYearAmtBasisOnVerified/"+person_number+"/"+financialYear;
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("lease"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Salary_cal = response.getBody();
	        }
	         System.out.println("salary cal"+url);
	        return manage_Salary_cal;
	    }
	    
	    @ResponseBody
	    @GetMapping("/manage/car_declaration_details/{financialYear}/{pnum}/{leaseid}")
	    public LinkedHashMap<String,String> car_declaration_details(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum,@PathVariable("leaseid") String leaseid) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Salary_cal = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/taxreport/getmonthAndAmtFindBy/"+person_number+"/"+leaseid+"/"+financialYear;
			// String url =  "http://10.8.20.35:8075/api/taxreport/getFinancialYearAmtBasisOnVerified/"+person_number+"/"+financialYear;
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("lease"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Salary_cal = response.getBody();
	        }
	         System.out.println("salary cal"+url);
	        return manage_Salary_cal;
	    }
	    
	    
	    @ResponseBody
	    @GetMapping("/manage/quarter_declaration_details/{financialYear}/{pnum}/{leaseid}")
	    public LinkedHashMap<String,String> quarter_declaration_details(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("pnum") String pnum,@PathVariable("leaseid") String leaseid) {
			person_number=pnum;
	    	LinkedHashMap<String,String> manage_Salary_cal = null;
			 Login login = (Login) request.getSession().getAttribute("login");
		     String url = appgateway.getAppgateway_payroll() + "/api/taxreport/getmonthAndAmtFindBy/"+person_number+"/"+leaseid+"/"+financialYear;
			// String url =  "http://10.8.20.35:8075/api/taxreport/getFinancialYearAmtBasisOnVerified/"+person_number+"/"+financialYear;
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request1 = new HttpEntity<String>(headers);	     
	        System.out.println("lease"+url);
	        ResponseEntity<LinkedHashMap<String,String>> response =restTemplate.exchange(url,
	                HttpMethod.GET, null, new ParameterizedTypeReference<LinkedHashMap<String,String>>() {
	        });
	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 manage_Salary_cal = response.getBody();
	        }
	         System.out.println("salary cal"+url);
	        return manage_Salary_cal;
	    }
	    
}
