package in.co.srdt.unif.controllers.taxreports.financereport;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.controllers.reports.GenerateExcelFiles;
import in.co.srdt.unif.enums.BankType;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payroll.FinancialYearLOV;
import in.co.srdt.unif.model.payroll.PayGroups;
import in.co.srdt.unif.model.reports.BankSalaryAdvice;
import in.co.srdt.unif.model.reports.BankTypeReportModel;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.model.taxdeclaration.MedicalTaxModel;
import in.co.srdt.unif.model.taxdeclaration.RFACommonModel;
import in.co.srdt.unif.reports.ExportFormat;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/finreports")
public class FinanceTaxReport {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
String businessUnitPayload;
	
	private final GenerateExcelFiles genexfiles = new GenerateExcelFiles();

	@RequestMapping("/hraexemtion")
	public String manageTaxReport(Model model, HttpServletRequest req) {
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);	
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		return "forms/taxReport/financeTaxReport/hraTaxReport :: hrataxReport";
		
	}
	
	
	@RequestMapping("/chapter6a")
	public String managechapter6a(Model model, HttpServletRequest req) {
		
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
	
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		System.out.println("urlCalendars  ::"+urlCalendars);
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		
		
		return "forms/taxReport/financeTaxReport/chapeterVIAReport :: chapterVIAReport";
	}
	
	
	
	@RequestMapping("/interesthomeloan")
	public String manageinteresthomeloan(Model model, HttpServletRequest req) {		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		
		
		return "forms/taxReport/financeTaxReport/intresthomeloanReport :: intrestHomeLoadReport";
	}
	
	
	
	@RequestMapping("/prevemp")
	public String manageprevemp(Model model, HttpServletRequest req) {		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		return "forms/taxReport/financeTaxReport/prevempReport :: prevempReport";
	}
	
	@RequestMapping("/vehicle")
	public String managecarperq(Model model, HttpServletRequest req) {		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		String businessUnitPayload = "{" + "  \"code\": \"\"," + "  \"name\": \"\","
				+ "  \"status\": \"Active\"" + "}";
		
		String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
		
		BUsearchresult[] busunit = null;

		HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);

		ResponseEntity<BUsearchresult[]> busunitres = restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq,
				BUsearchresult[].class);

		if (busunitres.getStatusCode() == HttpStatus.OK) {
			busunit = busunitres.getBody();
		} else {
			System.out.println("Request Failed");
		}

		model.addAttribute("busunit", busunit);
		
		return "forms/taxReport/financeTaxReport/carperqReport :: carperqReport";
	}
	
	
	
	@RequestMapping("/lease")
	public String managelease(Model model, HttpServletRequest req) {		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		String businessUnitPayload = "{" + "  \"code\": \"\"," + "  \"name\": \"\","
				+ "  \"status\": \"Active\"" + "}";
		
		String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
		
		BUsearchresult[] busunit = null;

		HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);

		ResponseEntity<BUsearchresult[]> busunitres = restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq,
				BUsearchresult[].class);

		if (busunitres.getStatusCode() == HttpStatus.OK) {
			busunit = busunitres.getBody();
		} else {
			System.out.println("Request Failed");
		}

		model.addAttribute("busunit", busunit);
		
		
		
		 CommonLOV[] accmodationType = null;

	        String urlAccmodation = appgateway.getAppgateway() + "/General/loadAccmodationType";
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	       
	        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlAccmodation, HttpMethod.GET, request, CommonLOV[].class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	           accmodationType = response.getBody();
	        	
	        }
	        List<CommonLOV> accomo = Arrays.asList(accmodationType).stream().filter(x -> x.id != Long.parseLong("210")).collect(Collectors.toList());

	        model.addAttribute("accmodationType", accomo);
		
		return "forms/taxReport/financeTaxReport/leaseQuarteReport :: leaseQuarterReport";
	}
	
	
	@RequestMapping("/medicalreimb")
	public String managemedicalreimb(Model model, HttpServletRequest req) {		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		PayGroups[] pgrp = null;
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		
		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		String businessUnitPayload = "{" + "  \"code\": \"\"," + "  \"name\": \"\","
				+ "  \"status\": \"Active\"" + "}";
		
		String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
		
		BUsearchresult[] busunit = null;

		HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);

		ResponseEntity<BUsearchresult[]> busunitres = restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq,
				BUsearchresult[].class);

		if (busunitres.getStatusCode() == HttpStatus.OK) {
			busunit = busunitres.getBody();
		} else {
			System.out.println("Request Failed");
		}

		model.addAttribute("busunit", busunit);
		
		
		
		 CommonLOV[] accmodationType = null;

	        String urlAccmodation = appgateway.getAppgateway() + "/General/loadAccmodationType";
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	       System.out.println(urlAccmodation);
	        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlAccmodation, HttpMethod.GET, request, CommonLOV[].class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            accmodationType = response.getBody();

	        }
	        model.addAttribute("accmodationType", accmodationType);
		
		return "forms/taxReport/financeTaxReport/medicalTaxReport :: medicalTaxReport";
	}
	
	
	
	@ResponseBody
    @RequestMapping(value = "/manage/HRATaxReportData/{financialYear}/{paygroupId}")
    public List<LinkedHashMap<String,String>> getHraReport(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("paygroupId") String paygroupId) {
		List<LinkedHashMap<String,String>> manageHRAReport = null;
        String urlHRA = appgateway.getAppgateway_payroll() + "/api/tax_report/getHRAReport/"+financialYear+"/"+paygroupId;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request1 = new HttpEntity<String>(headers);
     
        System.out.println(urlHRA);
        ResponseEntity<List<LinkedHashMap<String,String>>> response =restTemplate.exchange(urlHRA,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<LinkedHashMap<String,String>>>() {
        });
         if (response.getStatusCode() == HttpStatus.OK) {
        	manageHRAReport = response.getBody();
        }
      
        return manageHRAReport;
    }
	
	
	
	@ResponseBody
    @RequestMapping(value = "/manage/chapterVIAReportData/{financialYear}/{paygroupId}")
    public List<LinkedHashMap<String,String>> chapterVIAReportData(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("paygroupId") String paygroupId) {
		List<LinkedHashMap<String,String>> managechapterVIAReportData = null;
        String urlVIA = appgateway.getAppgateway_payroll() + "/api/tax_report/getChapter6AReport/"+financialYear+"/"+paygroupId;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request1 = new HttpEntity<String>(headers);
        System.out.println("urlVIA::"+urlVIA);

        ResponseEntity<List<LinkedHashMap<String,String>>> response =restTemplate.exchange(urlVIA,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<LinkedHashMap<String,String>>>() {
        });
         if (response.getStatusCode() == HttpStatus.OK) {
        	 managechapterVIAReportData = response.getBody();
        }
      
        return managechapterVIAReportData;
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/manage/HOME_LOAN/{financialYear}/{paygroupId}")
    public List<LinkedHashMap<String,String>> HOME_LOANReportData(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("paygroupId") String paygroupId) {
		List<LinkedHashMap<String,String>> manageHOME_LOANReportData = null;
        String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getHomeLoanReport/"+financialYear+"/"+paygroupId;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request1 = new HttpEntity<String>(headers);
        
        ResponseEntity<List<LinkedHashMap<String,String>>> response =restTemplate.exchange(urlHOME,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<LinkedHashMap<String,String>>>() {
        });
         if (response.getStatusCode() == HttpStatus.OK) {
        	 manageHOME_LOANReportData = response.getBody();
        }
      
        return manageHOME_LOANReportData;
    }
	
	@ResponseBody
    @RequestMapping(value = "/manage/PREV_INCOME/{financialYear}/{paygroupId}")
    public List<LinkedHashMap<String,String>> PREV_INCOMEReportData(HttpServletRequest request, Model model,@PathVariable("financialYear") String financialYear,@PathVariable("paygroupId") String paygroupId) {
		List<LinkedHashMap<String,String>> managePREV_INCOMEReportData = null;
        String urlHOME = appgateway.getAppgateway_payroll() + "/api/tax_report/getPreviousEmploymentTaxReport/"+financialYear+"/"+paygroupId;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request1 = new HttpEntity<String>(headers);
     

        ResponseEntity<List<LinkedHashMap<String,String>>> response =restTemplate.exchange(urlHOME,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<LinkedHashMap<String,String>>>() {
        });
         if (response.getStatusCode() == HttpStatus.OK) {
        	 managePREV_INCOMEReportData = response.getBody();
        }
      
        return managePREV_INCOMEReportData;
    }
	
	
	
	 @ResponseBody
	    @GetMapping("/manage/carperq/{financialYear}/{businessunit}")
	    public RFACommonModel[] carperq(@PathVariable("financialYear") String financialYear,@PathVariable("businessunit") String businessunit,HttpServletRequest requestFromDT) {
		     RFACommonModel[] carperq=null;
	        String url = appgateway.getAppgateway_payroll()+"/api/rfa_report/getCarPerqReport/"+financialYear+"/"+businessunit;
		    // String url = "http://localhost:9127/api/rfa_report/getCarPerqReport/"+financialYear+"/"+businessunit;
	      
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>( headers);
	        ResponseEntity<RFACommonModel[]> response = restTemplate.exchange(url, HttpMethod.GET, request, RFACommonModel[].class);
	        
	        if (response.getStatusCode() == HttpStatus.OK) {
	        	carperq = response.getBody();
	        }

	        return carperq;
	    }
	 
	 
	 @ResponseBody
	    @GetMapping("/manage/leaseQuart/{financialYear}/{businessunit}/{rfatype}")
	    public RFACommonModel[] leaseQuarter(@PathVariable("financialYear") String financialYear,@PathVariable("businessunit") String businessunit,@PathVariable("rfatype") String rfatype,HttpServletRequest requestFromDT) {
		     RFACommonModel[] carperq=null;
	        String url = appgateway.getAppgateway_payroll() + "/api/rfa_report/getLeaseQtrReport/"+financialYear+"/"+rfatype+"/"+businessunit;
		     //String url = "http://localhost:9127/api/rfa_report/getLeaseQtrReport/"+financialYear+"/"+businessunit+"/"+rfatype;
	       
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>( headers);
	        ResponseEntity<RFACommonModel[]> response = restTemplate.exchange(url, HttpMethod.GET, request, RFACommonModel[].class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	        	carperq = response.getBody();
	        }

	        return carperq;
	    }
	 
	 @ResponseBody
	    @GetMapping("/manage/medicalreport/{financialYear}/{businessunit}/{paygroup}")
	    public MedicalTaxModel[] medicalreport(@PathVariable("financialYear") String financialYear,@PathVariable("businessunit") String businessunit,@PathVariable("paygroup") String paygroup,HttpServletRequest requestFromDT) {
		 MedicalTaxModel[] medicalrep=null;
	        String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/rmbrs_report/geMedicalRmbrsReport/"+financialYear+"/"+paygroup+"/"+businessunit;
		     //String url = "http://localhost:9127/api/rfa_report/getLeaseQtrReport/"+financialYear+"/"+businessunit+"/"+rfatype;
	       
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>( headers);
	        ResponseEntity<MedicalTaxModel[]> response = restTemplate.exchange(url, HttpMethod.GET, request, MedicalTaxModel[].class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	        	medicalrep = response.getBody();
	        }

	        return medicalrep;
	    }
	
	 @RequestMapping("/bankadvice")
	 public String manageBankReports(Model model, HttpServletRequest req) {
		 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 
		 businessUnitPayload = "{" + "  \"code\": \"\"," + "  \"name\": \"\","
					+ "  \"status\": \"Active\"" + "}";
			
			String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
			
			BUsearchresult[] busunit = null;

			HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);

			ResponseEntity<BUsearchresult[]> busunitres = restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq,
					BUsearchresult[].class);

			if (busunitres.getStatusCode() == HttpStatus.OK) {
				busunit = busunitres.getBody();
			} else {
				System.out.println("Request Failed");
			}

			model.addAttribute("busunit", busunit);
			
			
			HttpEntity<String> requestPesro = new HttpEntity<String>(headers);	
			PayGroups[] pgrp = null;
			String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
			
			ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, requestPesro,
					PayGroups[].class);

			if (pgres.getStatusCode() == HttpStatus.OK) {
				pgrp = pgres.getBody();
			} else {
				System.out.println("Request Failed");
			}
			/* LOADING OF PAYGROUP ENDS */
			
			model.addAttribute("pgrp", pgrp);
			
			FinancialYearLOV[] calperiod = null;
			String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
			HttpEntity<String> calreq = new HttpEntity<String>(headers);
			
			ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
					HttpMethod.GET, calreq, FinancialYearLOV[].class);
			if (calresponse.getStatusCode() == HttpStatus.OK) {
				calperiod = calresponse.getBody();
			}
			model.addAttribute("calperiod", calperiod);
			
			model.addAttribute("banktype", BankType.values());
			return "forms/taxReport/financeTaxReport/bankReports :: bankReports"; 
	 }
	 
		@ResponseBody
		@RequestMapping("/getCalendars/{pgrpid}")
		public CalendarPeriod[] getCalendarsbypaygroupid(@PathVariable("pgrpid") String pgrpid, HttpSession session,
				HttpServletRequest request, Model model) 
		{
			headers.setContentType(MediaType.APPLICATION_JSON);
			CalendarPeriod[] calperiod = null;
			String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getFinalizeCalendarsByPaygroupid/"+ pgrpid;
			HttpEntity<String> calreq = new HttpEntity<String>(headers);
			ResponseEntity<CalendarPeriod[]> calresponse = restTemplate.exchange(urlCalendars,
					HttpMethod.GET, calreq, CalendarPeriod[].class);
			if (calresponse.getStatusCode() == HttpStatus.OK) {
				calperiod = calresponse.getBody();
			}
			//System.out.println("Hospital Names : " + hosname.toString());

			return calperiod;
		}
		
		/* LOADING OF CALENDARS BY PAY GROUP ID ENDS */
	 
	 @RequestMapping(value = "/bankadviceexcel/{buid}/{bus_name}/{paygroupid}/{calid}/{cal_code}/{banktype}", method = RequestMethod.GET)
		public String loadBankAdviceExcel(@PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("calid") String calid, @PathVariable("cal_code") String cal_code, @PathVariable("banktype") String banktype, Model model, HttpServletResponse response) {

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity < String > salbil_req = new HttpEntity < >(headers);

//			String salbil_url = appgateway.getAppgateway_payroll()+"/api/mod/getBankSalaryAdviceByParamiter/" + cal_code + "/" + buid + "/" + paygroupid + "/" + banktype;
			
			String salbil_url = appgateway.getAppgateway_payroll()+"/api/mod/getBankSalaryAdviceNewByParamiter/" + cal_code + "/" + buid + "/" + paygroupid + "/" + banktype;

			
			BankSalaryAdvice payrollregmodel[] = null;

			ResponseEntity < BankSalaryAdvice [] > responsePerso = restTemplate.exchange(salbil_url, HttpMethod.GET, salbil_req, BankSalaryAdvice [].class);

			if (responsePerso.getStatusCode() == HttpStatus.OK) {
				payrollregmodel = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
			}

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int date = now.get(Calendar.DATE);
			String dt = date + "-" + month + "-" + year;

			String calmonth = cal_code.substring(cal_code.indexOf("-") + 1);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=LMRC_Bank_Advice_Register_Repo_" + calmonth + "_" + bus_name + "_" + banktype + ".xlsx");
			try {
				genexfiles.WriteBankAdviceRegister(payrollregmodel, dt, bus_name, calmonth, banktype, response.getOutputStream());
				response.flushBuffer();
			} catch(IOException e) {
				System.out.println("ERROR: " + e);

			}

			return null;
		}
		
	 @RequestMapping(value = "/bankwiseexcel/{buid}/{bus_name}/{paygroupid}/{cal_code}/{banktype}/{format}", method = RequestMethod.GET)
		public String loadBankWiseExcel(@PathVariable("format") ExportFormat format, @PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("cal_code") String cal_code, @PathVariable("banktype") String banktype, Model model, HttpServletResponse response) {

			BankTypeReportModel[] bankTypeReportModel = null;

			String urlbank = appgateway.getAppgateway() + "/api/report/getBankDataByParamiter/" + buid + "/" + paygroupid + "/" + cal_code + "/" + banktype;
//			System.out.println("URL ====> "+urlbank);

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity < String > bankrequest = new HttpEntity < String > (headers);

			ResponseEntity < BankTypeReportModel[] > responsePerso = restTemplate.exchange(urlbank, HttpMethod.GET, bankrequest, BankTypeReportModel[].class);

			if (responsePerso.getStatusCode() == HttpStatus.OK) {
				bankTypeReportModel = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
			}

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int date = now.get(Calendar.DATE);
			String dt = date + "/0" + month + "/" + year;

			cal_code = cal_code.substring(cal_code.indexOf("-") + 1);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=LMRC_Employee_Salary_Bank_Tran_" + cal_code + "_" + bus_name + "_" + banktype + ".xlsx");
			try {
				genexfiles.WriteEmpSalBankTransactionRegister(bankTypeReportModel, dt, bus_name, cal_code, response.getOutputStream());
				response.flushBuffer();
			} catch(IOException e) {
				System.out.println("ERROR: " + e);

			}

			return null;

		}

}

