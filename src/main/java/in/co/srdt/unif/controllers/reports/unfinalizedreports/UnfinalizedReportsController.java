package in.co.srdt.unif.controllers.reports.unfinalizedreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.controllers.reports.GenerateExcelFiles;
import in.co.srdt.unif.enums.BankType;
import in.co.srdt.unif.enums.Months;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.ExcelModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payroll.PayGroups;
import in.co.srdt.unif.model.reports.EPFModel;
import in.co.srdt.unif.model.reports.ModPayBillComModel;
import in.co.srdt.unif.model.reports.ModPayBillModel;
import in.co.srdt.unif.model.reports.Mod_Payroll_RegisterModel;
import in.co.srdt.unif.model.reports.NPSModel;
import in.co.srdt.unif.model.reports.PayrollSalaryCards;
import in.co.srdt.unif.model.reports.VendorDetailsModel;
import in.co.srdt.unif.model.reports.VendorSummeryReport;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.reports.ExportFormat;
import in.co.srdt.unif.reports.JasperReportsService;
import in.co.srdt.unif.reports.ReportService;
import in.co.srdt.unif.utils.ApplicationGateway;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/unfinalized")
public class UnfinalizedReportsController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	Login userlogin = null;
	
	String businessUnitPayload;
	String paysummPayLoad;
	
	private final ReportService reportService = new JasperReportsService();
	
	private final GenerateExcelFiles genexfiles = new GenerateExcelFiles();
	/* Variables for POOJA's CONTROLLER  */
	
	double earPrev = 0;
	double earCur = 0;
	double earTot = 0;
	double dedPrev = 0;
	double dedCur = 0;
	double dedTot = 0;
	double empPrev = 0;
	double empCur = 0;
	double empTot = 0; 
	double perPrev = 0; 
	double perCur = 0; 
	double perTot = 0; 
	double netPrev = 0; 
	double netCur = 0; 
	double netTot = 0;
	
	double deduction = 0;
	double earning = 0;
	double contribution = 0;
	double net=0;
	
	String months="";
	String paiddays="";
	/* Variables for POOJA's CONTROLLER  */
	
	/* OPENS MAIN HR REPORT TAB PAGE */
	 @RequestMapping("/ufreports") 
	public String getHrReports(HttpServletRequest request,Model model)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);

		/* LOADING OF BUSINESS UNITS STARTS */

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

		/* LOADING OF BUSINESS UNITS ENDS */
		
		/* LOADING OF PAYGROUP STARTS */

		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";

		PayGroups[] pgrp = null;

		HttpEntity<String> pgreq = new HttpEntity<String>(headers);

		ResponseEntity<PayGroups[]> pgres = restTemplate.exchange(urlpaygroup, HttpMethod.GET, pgreq,
				PayGroups[].class);

		if (pgres.getStatusCode() == HttpStatus.OK) {
			pgrp = pgres.getBody();
		} else {
			System.out.println("Request Failed");
		}
		/* LOADING OF PAYGROUP ENDS */
		
		model.addAttribute("pgrp", pgrp);

		/* LOADING OF NATURE OF EMPLOYMENT STARTS */
		CommonLOV[] nature_of_employement = null;

		HttpEntity<String> natrequest = new HttpEntity<String>(headers);
		String urlnatofempl =  appgateway.getAppgateway() + "/General/loadNatureOfEmployement";

		ResponseEntity<CommonLOV[]> responsenature = restTemplate.exchange(urlnatofempl, HttpMethod.GET, natrequest,
				CommonLOV[].class);
		if (responsenature.getStatusCode() == HttpStatus.OK) {
			nature_of_employement = responsenature.getBody();
		}

		model.addAttribute("natemp", nature_of_employement);

		/* LOADING OF NATURE OF EMPLOYMENT ENDS */

		model.addAttribute("month", Months.values());
		model.addAttribute("banktype", BankType.values());


		/* LOADING OF Vendor Starts 23/4/2021  */
		CommonLOV[] vendormonth = null;

		HttpEntity<String> vendorequest = new HttpEntity<String>(headers);
		String urlvendormnth =  appgateway.getAppgateway() + "/General/loadMonthYearLookup";

		ResponseEntity<CommonLOV[]> responsevenmnth = restTemplate.exchange(urlvendormnth, HttpMethod.GET, vendorequest,
				CommonLOV[].class);
		if (responsevenmnth.getStatusCode() == HttpStatus.OK) {
			vendormonth = responsevenmnth.getBody();
		}

		model.addAttribute("vendormonth", vendormonth);

		/* LOADING OF Vendor Details ENDS */
		
		return "fragments/reports/unfinalizedreports/UnFinalizedReports :: UnFinalizedReports";
	}	
	
	/* LOADING OF CALENDARS BY PAY GROUP ID STARTS */
	
	@ResponseBody
	@RequestMapping("/getCalendars/{pgrpid}")
	public CalendarPeriod[] getCalendarsbypaygroupid(@PathVariable("pgrpid") String pgrpid, HttpSession session,
			HttpServletRequest request, Model model) 
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		CalendarPeriod[] calperiod = null;
//		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/reportelementwise/getpayrolllockcalendarsbypaygroupid/"+ pgrpid;
		String urlCalendars = appgateway.getAppgateway_payroll() +"/api/reportelementwise/getelementwiescalendarslist/"+ pgrpid;
		//System.out.println(urlCalendars);
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
	
	@ResponseBody
	@RequestMapping("/salarysummarybill/{bus_id}/{bus_name}/{calid}/{cal_code}/{paygrpid}/{natureemp}/{natemp}/{perno}")
	public String getSalaryBill(@PathVariable("bus_id") String bus_id, @PathVariable("bus_name") String bus_name,
			@PathVariable("calid") String calid, @PathVariable("cal_code") String cal_code,
			@PathVariable("paygrpid") String paygrpid, @PathVariable("natureemp") String natureemp,@PathVariable("natemp") String natemp, @PathVariable("perno") String perno, HttpServletRequest request,
			Model model)
	{	
		
		HttpEntity<String> salbil_req = new HttpEntity<>(paysummPayLoad,headers);
		
		String pyrlsumm ="";
//		String salbil_url =appgateway.getAppgateway_payroll()+"/api/reportelementwise/getPayrollSlipSummeryByElementWise";
//		String salbil_report_url =appgateway.getAppgateway_payroll()+"/api/reportelementwise/getPayrollSlipSummerySumByElementWise";
				
//		System.out.println("SAL URL ===> "+salbil_url);
		String salbil_url = appgateway.getUnfinalizedsalbillURL()+"/invokejob/"+bus_id+"/"+bus_name+"/"+calid+"/"+cal_code
				+"/"+paygrpid+"/"+natureemp+"/"+natemp+"/"+perno;
		
		//System.out.println("SAL URL ===> "+salbil_url);
		
		ResponseEntity<String> salbill_response = restTemplate.exchange(salbil_url, HttpMethod.GET,salbil_req,String.class);
		
		if(salbill_response.getStatusCode() == HttpStatus.OK)
		{			
			pyrlsumm = salbill_response.getBody();
//			System.out.println("DATA RECEIVED :: "+pyrlsumm);
		}
		else
		{
			System.out.println("Request Failed");
		}
		if(pyrlsumm.equals("Batch job has been invoked"))
			return "success";
		else 
			return "error";
	}
	/*  UPDATED ON 25-05-2021 */
		
	
	/* FUNCTION FOR EPF REPORT STARTS 24-03-2021 */
	@RequestMapping("/getepfreport/{bus_id}/{busname}/{calcode}/{natemp}/{paygroupid}")
	public String getEPFReport(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname, @PathVariable("calcode") String calcode, @PathVariable("natemp") String natemp, @PathVariable("paygroupid") String paygroupid, HttpServletResponse response) {

	headers.setContentType(MediaType.APPLICATION_JSON);

	String epf_url = appgateway.getAppgateway_payroll() + "/api/reportelementwise/getEpfReportByElementWise/" + calcode + "/" + bus_id + "/" + natemp + "/" + paygroupid;
//		System.out.println("EPF URL ==="+epf_url);

		EPFModel epfmodel[] = null;

		HttpEntity < String > epf_request = new HttpEntity < >(headers);

		ResponseEntity < EPFModel[] > epf_response = restTemplate.exchange(epf_url, HttpMethod.GET, epf_request, EPFModel[].class);

		if (epf_response.getStatusCode() == HttpStatus.OK) {
			epfmodel = epf_response.getBody();
		} else {
			System.out.println("Request Failed");

		}


		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=LMRC__HR_EPF_Report_" + calcode.substring(calcode.indexOf("-") + 1, calcode.lastIndexOf("-")) + "_" + busname + ".xlsx");
		try {
			genexfiles.WriteEPFReport(epfmodel, busname, calcode, response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}
		return null;
	}

	/* FUNCTION FOR NPS REPORT STARTS 25-03-2021 updated on 22-04-2021 */
	@RequestMapping("/getnpsreport/{bus_id}/{busname}/{calcode}/{natemp}/{paygroupid}")
	public String getNPSReport(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname, @PathVariable("calcode") String calcode, @PathVariable("natemp") String natemp, @PathVariable("paygroupid") String paygroupid, HttpServletResponse response) 
	{
	
		headers.setContentType(MediaType.APPLICATION_JSON);

		//payroll getway+  /api/mod/getNpfReportByParameter/{calcode}/{buId}/{natureofemplymnt}/{paygroupid}
		String payrollregister_url = appgateway.getAppgateway_payroll() + "/api/reportelementwise/getNpfReportByElementWise/" +calcode+"/"+bus_id+"/"+natemp+"/"+paygroupid;
//		System.out.println("NPS URL ===> "+payrollregister_url);
		NPSModel npsmodel[] = null;

		HttpEntity < String > nps_request = new HttpEntity < >(headers);

		ResponseEntity < NPSModel[] > nps_response = restTemplate.exchange(payrollregister_url, HttpMethod.GET, nps_request, NPSModel[].class);

		if (nps_response.getStatusCode() == HttpStatus.OK) {
			npsmodel = nps_response.getBody();
		} else {
			System.out.println("Request Failed");

		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=LMRC__HR_NPS_Report_" + calcode.substring(calcode.indexOf("-") + 1, calcode.lastIndexOf("-")) + "_" + busname + ".xlsx");
		try {
			genexfiles.WriteNPSReport(npsmodel, busname, calcode, response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}
		return null;
	}
	
	
	
	/* Vendor Details Report starts here 23/04/2021 */ 
	
	@RequestMapping(value = "/getvendordetails/{paygroupid}/{cal_code}", method = RequestMethod.GET)
	public String getVendorDetails(@PathVariable("paygroupid") String paygroupid,@PathVariable("cal_code") String cal_code, HttpServletResponse response) {

		VendorDetailsModel[] vendorModel = null;
		VendorSummeryReport[] vendorSummary =null;
		String urlvendor = appgateway.getAppgateway_payroll() + "/api/reportelementwise/getVendorDedutnRecvyByElementWise/" + cal_code+"/"+paygroupid;

		String urlvendorsumm = appgateway.getAppgateway_payroll() + "/api/reportelementwise/getVendorSummeryReportByElementWise/" + cal_code+"/"+paygroupid;
		
//		System.out.println("VEndor URL ===> "+urlvendor);
		//System.out.println("VEndor Summary URL ===> "+urlvendorsumm);
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity < String > vendorrequest = new HttpEntity < String > (headers);

		//ResponseEntity < VendorDetailsModel> responseVendor = restTemplate.exchange(urlvendor, HttpMethod.GET, vendorrequest, VendorDetailsModel.class);
		ResponseEntity < VendorDetailsModel[]> responseVendor = restTemplate.exchange(urlvendor, HttpMethod.GET, vendorrequest, VendorDetailsModel[].class);
		
		ResponseEntity < VendorSummeryReport[]> responseVendorSumm = restTemplate.exchange(urlvendorsumm, HttpMethod.GET, vendorrequest, VendorSummeryReport[].class);

		if (responseVendor.getStatusCode() == HttpStatus.OK) {
			vendorModel = responseVendor.getBody();
		} else {
			System.out.println("Request Failed");
		}

		
		if (responseVendorSumm.getStatusCode() == HttpStatus.OK) {
			vendorSummary = responseVendorSumm.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=LMRC_HR_Vendor_Wise_Deduction_" + cal_code +".xlsx");
		try {
			genexfiles.WriteVendorwiseDeductionReport(vendorModel, vendorSummary, cal_code,response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}

		return null;
	}
	/* Vendor Details Report ends here 29/04/2021 */
	
	/* CURRENT MONTH PAYROLL REGISTER MOD-3 */
	@ResponseBody
	@RequestMapping("/getufpayroll_regexcel/{calcode}/{bus_id}/{busname}")
	public String loadPayrollRegexcel(@PathVariable("calcode") String calcode, @PathVariable("bus_id") String bus_id,
			@PathVariable("busname") String busname, HttpServletResponse response) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		String payroll_url = appgateway.getAppgateway_payroll() + "/api/mod/getModPayrollData/" + calcode + "/" + bus_id;
		
		//System.out.println("PAYROLL MOD ===> "+payroll_url);
		Mod_Payroll_RegisterModel payrolregmodel = null;

		HttpEntity<String> payrol_request = new HttpEntity<>(headers);

		ResponseEntity<Mod_Payroll_RegisterModel> payroll_response = restTemplate.exchange(payroll_url, HttpMethod.GET,
				payrol_request, Mod_Payroll_RegisterModel.class);

		if (payroll_response.getStatusCode() == HttpStatus.OK) {

			payrolregmodel = payroll_response.getBody();
		} else {
			System.out.println("Request Failed");

		}

		List<LinkedHashMap<String, String>> strdata = payrolregmodel.getData();
		String curmonth = payrolregmodel.getCur().substring(4);
		String premonth = payrolregmodel.getPre().substring(4);
		
//		System.out.println("curmonth :: "+curmonth);
//		System.out.println("premonth :: "+premonth);
		
		String calper_current = "",calper_previous = "";
		switch (calcode.substring(4,7)) {
		case "JAN":
			calper_current = "10";
			calper_previous = "9";
			break;
		case "FEB":
			calper_current = "11";
			calper_previous = "10";
			break;
		case "MAR":
			calper_current = "12";
			calper_previous = "11";
			break;
		case "APR":
			calper_current = "1";
			calper_previous = "12";
			break;
		case "MAY":
			calper_current = "2";
			calper_previous = "1";
			break;
		case "JUN":
			calper_current = "3";
			calper_previous = "2";
			break;
		case "JUL":
			calper_current = "4";
			calper_previous = "3";
			break;
		case "AUG":
			calper_current = "5";
			calper_previous = "4";
			break;
		case "SEP":
			calper_current = "6";
			calper_previous = "5";
			break;
		case "OCT":
			calper_current = "7";
			calper_previous = "6";
			break;
		case "NOV":
			calper_current = "8";
			calper_previous = "7";
			break;
		case "DEC":
			calper_current = "9";
			calper_previous = "8";
			break;
		}
		double curamt= 0,preamt=0,diffamt = 0;
//		System.out.println("STRDATA ==== >>  "+strdata);

//		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-disposition",
//				"attachment; filename=Payroll_MOD3_" + calcode + "_" + busname + ".xlsx");
//		try {
//			genexfiles.WritePayrollRegisterMOD(strdata,curmonth,premonth, response.getOutputStream());
//			response.flushBuffer();
//		} catch (IOException e) {
//			System.out.println("ERROR: " + e);
//		}

		String html_text = "<html><head><style type='text/css'>body{font-family:calibri;}"
				+ ".page-break{page-break-before: always;}table, td, th {border: 1px solid black;}"
				+ "table {width: 100%;border-collapse: collapse;}"
				+ ".noborder {border: 1px solid white;text-align: left;}"
				+ ".tablefont {font-size: 10px;width: 100%;border-collapse: collapse;}</style></head>"
				+ "<body><table><tr><td style='background-color:orange;'></td><td>Earnings</td></tr>"
				+ "<tr><td style='background-color:green;'></td><td>Deductions</td></tr>"
				+ "<tr><td style='background-color:cyan;'></td><td>Contributions</td></tr></table>"
				+ "<h2>Memorandum of Difference's "+curmonth+": Payroll Register - Employee + Earning Level</h2>"
				+ "<table><tr><th style='text-align:right;'>Current Month</th><th style='text-align:left;'>"+curmonth+"</th>"
				+ "<th style='text-align:right;'>Previous Month</th><th style='text-align:left;'>"+premonth+"</th><th style='text-align:right;'>Report For</th><th>All Employees</th></tr>"
				+ "<tr><th style='text-align:right;'>Current Mth Payroll Run</th><th style='text-align:left;'>"+calper_current+" "+curmonth+" Calendar Month</th><th style='text-align:right;'>Prev Mth Payroll Rum</th>"
				+ "<th style='text-align:left;'>"+calper_previous+" "+premonth+" Calendar Month</th><th style='text-align:right;'>Report Type</th><th style='text-align:left;'>All Employees</th></tr></table><br/><br/>"
				+ "<table><tr><th style='background-color:gray;'>S. NO</th>";
		
		Set<String> allkeysets = null;
		if(strdata.size()!=0)
		{
			allkeysets = strdata.get(0).keySet();
			
//			System.out.println(allkeysets);
			
			for (String str : allkeysets) 
			{				
				if(str.equals("Person Number"))
				{
					html_text+="<th style='background-color:gray;'>EMP ID</th>";
				}
				
				else if(str.equals("Name"))
				{
					html_text+="<th style='background-color:gray;'>EMP NAME</th>";
				}
				else if(str.equals("Department"))
				{
					html_text+="<th style='background-color:gray;'>DEPARTMENT</th>";
				}
				else if(str.equals("Attendance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:gray;'>ATTENDANCE_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";					
				}
				else if(str.equals("Attendance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:gray;'>ATTENDANCE_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
				}
				else if(str.equals("Basic Salary_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>BASIC SALARY_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Basic Salary_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>BASIC SALARY_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>BASIC PAY-DIFF</th>";
				}
				else if(str.equals("ARR_Basic Salary_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_BASIC SALARY_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";					
				}
				else if(str.equals("ARR_Basic Salary_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_BASIC SALARY_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>BASIC ARR-DIFF</th>";					
				}
				else if(str.equals("Grade Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Grade Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Grade Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Grade Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Grade Pay-Diff</th>";					
				}
				else if(str.equals("Personal Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Personal Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Personal Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Personal Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Personal Pay-Diff</th>";
				}
				else if(str.equals("Special Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Special Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Special Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Special Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Special Pay-Diff</th>";
				}
				else if(str.equals("Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Dearness Allowance-Diff</th>";
				}
				else if(str.equals("ARR_Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";					
				}
				else if(str.equals("ARR_Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Dearness Allowance-Diff</th>";
				}
				else if(str.equals("House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>House Rent Allowance-Diff</th>";
				}
				else if(str.equals("ARR_House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_House Rent Allowance-Diff</th>";
				}
				else if(str.equals("Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";					
				}
				else if(str.equals("Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Deputation Allowance-Diff</th>";
				}
				else if(str.equals("ARR_Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Deputation Allowance-Diff</th>";
				}
				else if(str.equals("National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>National Holiday Allowance-Diff</th>";
				}
				else if(str.equals("ARR_National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_National Holiday Allowance-Diff</th>";
				}
				else if(str.equals("Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Cafeteria Allowance-Diff</th>";
				}
				else if(str.equals("ARR_Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Cafeteria Allowance-Diff</th>";
				}
				else if(str.equals("Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Transport Allowance-Diff</th>";
				}
				else if(str.equals("ARR_Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Transport Allowance-Diff</th>";
				}
				else if(str.equals("Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Medical Benefit-Diff</th>";
				}
				else if(str.equals("ARR_Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Medical Benefit-Diff</th>";
				}
				else if(str.equals("Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Honorarium Pay-Diff</th>";
				}
				else if(str.equals("ARR_Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Honorarium Pay-Diff</th>";
				}
				else if(str.equals("Family Planning Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Family Planning Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Family Planning Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Family Planning Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Family Planning Allowance-Diff</th>";
				}
				else if(str.equals("Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Consultant Fee-Diff</th>";
				}
				else if(str.equals("ARR_Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("ARR_Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>ARR_Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>ARR_Consultant Fee-Diff</th>";
				}
				else if(str.equals("Miscellaneous Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Miscellaneous Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Miscellaneous Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Miscellaneous Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Miscellaneous Payment-Diff</th>";
				}
				else if(str.equals("Leave Encashment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Leave Encashment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Leave Encashment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Leave Encashment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Leave Encashment-Diff</th>";
				}
				else if(str.equals("Quarter Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Quarter Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Quarter Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Quarter Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Quarter Payment-Diff</th>";
				}
				else if(str.equals("Total Earning_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Total Earning_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Total Earning_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:orange;'>Total Earning_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:orange;'>Total Earning-Diff</th>";
				}
				else if(str.equals("Empl SPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl SPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl SPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl SPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl SPF Contribution-Diff</th>";
				}
				else if(str.equals("Empl SNPS Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl SNPS Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl SNPS Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl SNPS Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl SNPS Contribution-Diff</th>";
				}
				else if(str.equals("Empl GPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl GPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl GPF Contribution-Diff</th>";
				}
				else if(str.equals("Electricity Bill Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Electricity Bill Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Electricity Bill Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Electricity Bill Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Electricity Bill Deduction-Diff</th>";
				}
				else if(str.equals("Electricity Charge_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Electricity Charge_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Electricity Charge_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Electricity Charge_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Electricity Charge-Diff</th>";
				}
				else if(str.equals("Lease Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Lease Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Lease Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Lease Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Lease Recovery-Diff</th>";
				}
				else if(str.equals("Loan Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Loan Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Loan Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Loan Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Loan Advance Recovery-Diff</th>";
				}
				else if(str.equals("Veh Excess Use Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Veh Excess Use Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Veh Excess Use Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Veh Excess Use Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Veh Excess Use Recv-Diff</th>";
				}
				else if(str.equals("Vehicle Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Vehicle Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Vehicle Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Vehicle Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Vehicle Recv-Diff</th>";
				}
				else if(str.equals("Miscellaneous Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Miscellaneous Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Miscellaneous Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Miscellaneous Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Miscellaneous Recovery-Diff</th>";
				}
				else if(str.equals("Arrear GPF_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Arrear GPF_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Arrear GPF_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Arrear GPF_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Arrear GPF-Diff</th>";
				}
				else if(str.equals("Quarter Rent Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Quarter Rent Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Quarter Rent Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Quarter Rent Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Quarter Rent Recovery-Diff</th>";
				}
				else if(str.equals("HRA Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>HRA Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("HRA Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>HRA Recoveryg_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>HRA Recovery-Diff</th>";
				}
				else if(str.equals("Income Tax_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))) || str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Income Tax_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Income Tax_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))) || str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Income Tax_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Income Tax-Diff</th>";
				}
				else if(str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))) || str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))) || str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>TDS-Diff</th>";
				}
				else if(str.equals("Empl Vol PF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl Vol PF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl Vol PF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl Vol PF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl Vol PF Contribution-Diff</th>";
				}
				else if(str.equals("Empl GIS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GIS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl GIS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GIS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl GIS Cont-Diff</th>";
				}
				else if(str.equals("Empl GSLI Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GSLI Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empl GSLI Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Empl GSLI Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Empl GSLI Contribution-Diff</th>";
				}
				else if(str.equals("Food Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Food Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Food Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Food Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Food Recovery-Diff</th>";
				}
				else if(str.equals("LMRC Vehicle Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>LMRC Vehicle Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("LMRC Vehicle Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>LMRC Vehicle Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>LMRC Vehicle Advance Recovery-Diff</th>";
				}
				else if(str.equals("Total Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Total Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Total Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:green;'>Total Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:green;'>Total Deduction-Diff</th>";
				}
				else if(str.equals("Empr PF Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr PF Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empr PF Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr PF Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Empr PF Cont-Diff</th>";
				}
				else if(str.equals("Empr EPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr EPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empr EPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr EPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Empr EPS Cont-Diff</th>";
				}
				else if(str.equals("Empr NPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr NPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empr NPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr NPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Empr NPS Cont-Diff</th>";
				}
				else if(str.equals("Pension Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Pension Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Pension Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Pension Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Pension Cont-Diff</th>";
				}
				else if(str.equals("Empr GSLI Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr GSLI Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empr GSLI Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr GSLI Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Empr GSLI Cont-Diff</th>";
				}
				else if(str.equals("Empr Leave Salary Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr Leave Salary Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Empr Leave Salary Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Empr Leave Salary Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Empr Leave Salary Cont-Diff</th>";
				}
				else if(str.equals("Total Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Total Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))+"</th>";
				}
				else if(str.equals("Total Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
				{
					html_text+="<th style='background-color:cyan;'>Total Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))+"</th>";
					html_text+="<th style='background-color:cyan;'>Total Contribution-Diff</th>";
				}
				
			}
//			System.out.println("html_text==> "+html_text);
			int i=1;
			for (Map<String, String> dt : strdata) 
			{
				
				html_text+="<tr><td>"+ i++ +"</td>";
				allkeysets = dt.keySet();
				for(String str : allkeysets)
				{
					if(str.equals("Person Number"))
					{
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					
					else if(str.equals("Name"))
					{
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Department"))
					{
						if(dt.get(str)!=null)
						{
							html_text+="<td>"+dt.get(str).toString()+"</td>";
						}
						else
						{
							html_text+="<td>&nbsp;</td>";
						}
					}
					else if(str.equals("Attendance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						html_text+="<td>"+dt.get(str).toString()+"</td>";					
					}
					else if(str.equals("Attendance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Basic Salary_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Basic Salary_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Basic Salary_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";					
					}
					else if(str.equals("ARR_Basic Salary_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";					
					}
					else if(str.equals("Grade Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Grade Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";					
					}
					else if(str.equals("Personal Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Personal Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Special Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Special Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Dearness Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";					
					}
					else if(str.equals("ARR_Dearness Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_House Rent Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_House Rent Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";					
					}
					else if(str.equals("Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Deputation Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_Deputation Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_National Holiday Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_National Holiday Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Cafeteria Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Cafeteria Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Transport Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_Transport Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Medical Benefit_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_Medical Benefit_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Honorarium Pay_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_Honorarium Pay_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Family Planning Allowance_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Family Planning Allowance_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("ARR_Consultant Fee_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("ARR_Consultant Fee_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Miscellaneous Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Miscellaneous Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Leave Encashment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Leave Encashment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Quarter Payment_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Quarter Payment_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Total Earning_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Total Earning_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl SPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl SPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl SNPS Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl SNPS Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl GPF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl GPF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Electricity Bill Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Electricity Bill Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Electricity Charge_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Electricity Charge_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Lease Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Lease Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Loan Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Loan Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Veh Excess Use Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Veh Excess Use Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Vehicle Recv_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Vehicle Recv_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Miscellaneous Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Miscellaneous Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Arrear GPF_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Arrear GPF_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Quarter Rent Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Quarter Rent Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("HRA Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("HRA Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Income Tax_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))) || str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Income Tax_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))) || str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))) || str.equals("TDS_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))) || str.equals("TDS_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl Vol PF Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl Vol PF Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl GIS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl GIS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empl GSLI Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empl GSLI Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Food Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Food Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("LMRC Vehicle Advance Recovery_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("LMRC Vehicle Advance Recovery_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Total Deduction_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Total Deduction_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empr PF Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empr PF Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empr EPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empr EPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empr NPS Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empr NPS Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Pension Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Pension Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empr GSLI Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empr GSLI Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Empr Leave Salary Cont_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Empr Leave Salary Cont_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
					else if(str.equals("Total Contribution_".concat(curmonth.substring(0,3).concat("_").concat(curmonth.substring(4)))))
					{
						curamt = Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
					}
					else if(str.equals("Total Contribution_".concat(premonth.substring(0,3).concat("_").concat(premonth.substring(4)))))
					{
						preamt= Double.parseDouble(dt.get(str).toString());
						html_text+="<td>"+dt.get(str).toString()+"</td>";
						diffamt = curamt - preamt;
						html_text+="<td>"+diffamt+"</td>";
					}
				}
				html_text+="</tr>";
			}
		}
//		System.out.println("\n html_text==> "+html_text);
		String htmlfileName = appgateway.getPayrollmod3InputFilePath()+"/UnfinalizedPayrollRegisterMOD3_"+calcode.substring(4)+"_"+busname+".html";
		String excelFileName = appgateway.getPayrollmod3OutputFilePath()+"/UnfinalizedPayrollRegisterMOD3_"+calcode.substring(4)+"_"+busname+".xls";
		try{    
            FileOutputStream fout=new FileOutputStream(htmlfileName);     
            byte b[]=html_text.getBytes();//converting string into byte array    
            fout.write(b);    
            fout.close();    
            //System.out.println("success in creating html file ...");
            
            
            InputStream is = new FileInputStream(htmlfileName);
            OutputStream os = new FileOutputStream(excelFileName);
            int c;
            while ((c = is.read()) != -1) {
//              System.out.print((char) c);
              os.write(c);
            }
            is.close();
            os.close();

            //System.out.println("success in creating xls file ...");
           }catch(Exception e){System.out.println(e);}       


		return "Success";
	}
	
	@ResponseBody
	@RequestMapping("/getufpayrollregister/{bus_id}/{busname}/{calcode}/{natemp}/{natureemp}")
	public String getPayrollRegister(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname,
			@PathVariable("calcode") String calcode, @PathVariable("natemp") int natemp,
			@PathVariable("natureemp") String natureemp,HttpServletResponse response) throws Exception {


		String html_text = "";
		int count = 0;
		headers.setContentType(MediaType.APPLICATION_JSON);

		String payrollregister_url = "";
//		String payrollregister_url = appgateway.getAppgateway_payroll() + "/api/mod/getPayrollRegister/" + calcode + "/"
//				+ bus_id + "/" + natemp + "/" + paygroupid; /* check here */
		
//		http://localhost:9127/api/mod/getPayrollRegister/LPY-APR-2021/20001/IDA
		if(natemp==0)
		{
			//payrollregister_url = "http://localhost:9127/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natemp;
			payrollregister_url = appgateway.getAppgateway_payroll() +"/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natemp;
		}
		else
		{
			//payrollregister_url = "http://localhost:9127/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natureemp; /* check here */
			payrollregister_url = appgateway.getAppgateway_payroll() +"/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natureemp; /* check here */
		}
		
		//System.out.println("URL ===> "+payrollregister_url);

		ExcelModel payrollregworkbook = null;

		
		HttpEntity<String> payrollreg_request = new HttpEntity<>(headers);

		ResponseEntity<ExcelModel> payrollreg_response = restTemplate.exchange(payrollregister_url,
				HttpMethod.GET, payrollreg_request, ExcelModel.class);

		if (payrollreg_response.getStatusCode() == HttpStatus.OK) 
		{
			payrollregworkbook = payrollreg_response.getBody();
		}
		html_text+= "<html><head><style type='text/css'>body{font-family:calibri;}.page-break{page-break-before: always;}table, td, th {border: 1px solid black;}th{background-color:grey;}table {width: 100%;border-collapse: collapse;}.noborder {border: 1px solid white;text-align: left;}.tablefont {font-size: 10px;width: 100%;border-collapse: collapse;}</style></head><body><table><tr><td style='background-color:orange;'></td><td>Earnings</td></tr><tr><td style='background-color:green;'></td><td>Deductions</td></tr><tr><td style='background-color:cyan;'></td><td>Contributions</td></tr></table><h2>UPMRC Payroll Register for Employees </h2><table><tr><th style='background-color:gray;color:white'>S. No</th>";

String bgcolor = "";
String fontcolor="";
        
        Set<String> keysets = payrollregworkbook.getData().get(0).keySet();
		
		if(payrollregworkbook.getData()!=null)
		{
			
			for(String str : keysets)
			{
				if(str.contains("_er")) {
					bgcolor = "orange";
					fontcolor = "black";
					str = str.substring(0,str.length()-3).replace('_',' ').toUpperCase();
				}else if(str.contains("_de")) {
					bgcolor = "green";
					fontcolor = "white";
					str = str.substring(0,str.length()-3).replace('_',' ').toUpperCase();
				}else if(str.contains("_st")) {
					bgcolor="cyan";
					fontcolor = "black";
					str = str.substring(0,str.length()-3).replace('_',' ').toUpperCase();
				}else {
					bgcolor="gray";
					fontcolor = "white";}
				html_text+="<th style='background-color:"+bgcolor+";color:"+fontcolor+";'>"+str.replace('_',' ').toUpperCase()+"</th>";		
			}
			html_text+="</tr>";
			
			for (Map<String, Object> dt : payrollregworkbook.getData()) {
	            keysets = dt.keySet();
	            html_text+="<tr><td>"+ (++count) +"</td>";
	            for (String str : keysets)
	            {
	            	html_text+="<td>"+dt.get(str).toString()+"</td>";
	            }
			}
			
			html_text+="</table></body></html>";
		}
		//System.out.println(html_text);
        
//		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-disposition", "attachment; filename=PayrollRegister_"
//				+ calcode.substring(calcode.indexOf("-") + 1) + "_" + busname + ".xlsx");
//		try {
//			genexfiles.WritePayrollRegister(payrollregworkbook.getData(), busname, calcode, natureemp, response.getOutputStream());
//			response.flushBuffer();
//		} catch (IOException e) {
//			System.out.println("ERROR: " + e);
//
//		}
		
		String htmlfileName = appgateway.getPayrollregInputFilePath()+"/UnfinalizedPayrollRegister_"+calcode.substring(4)+"_"+busname+".html";
		String excelFileName = appgateway.getPayrollregOutputFilePath()+"/UnfinalizedPayrollRegister_"+calcode.substring(4)+"_"+busname+".xls";
		try{    
            FileOutputStream fout=new FileOutputStream(htmlfileName);     
            byte b[]=html_text.getBytes();//converting string into byte array    
            fout.write(b);    
            fout.close();    
            //System.out.println("success in creating html file ...");
            
            
            InputStream is = new FileInputStream(htmlfileName);
            OutputStream os = new FileOutputStream(excelFileName);
            int c;
            while ((c = is.read()) != -1) {
//              System.out.print((char) c);
              os.write(c);
            }
            is.close();
            os.close();

            //System.out.println("success in creating xls file ...");
           }catch(Exception e){System.out.println(e);}       


		File file = new File(excelFileName);    
		if (file.exists()) {
		    return "Success";
		}
		else {
		return "Error";
		}
	}

	/* Employee Salary card starts here 17/05/2021 */ 

	@ResponseBody
	@RequestMapping(value = "/salarycard/{cal_code}/{tocalcode}/{bus_id}/{bus_name}/{paygroupid}/{per_no}", method = RequestMethod.GET)
	public String getSalaryCard(@PathVariable("cal_code") String cal_code, @PathVariable("tocalcode") String tocalcode ,@PathVariable("bus_id") String bus_id, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("per_no") String per_no, HttpServletResponse response) {
			
		HttpEntity < String > salcardrequest = new HttpEntity < String > (headers);
		
		String pyrlsalcards = null;

		String salcard_url = appgateway.getSalcardURL() +"/createSalaryCard/"+bus_id+"/"+cal_code+"/"+tocalcode+"/"+paygroupid+"/"+per_no;
//		System.out.println("URL ==> "+salcard_url);
		ResponseEntity <String> responsepaysalcards = restTemplate.exchange(salcard_url, HttpMethod.GET, salcardrequest, String.class);
		

		if (responsepaysalcards.getStatusCode() == HttpStatus.OK) {
			pyrlsalcards = responsepaysalcards.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		if(pyrlsalcards.equals("Batch job has been invoked"))
			return "success";
		else 
			return "error";
	}
	/* Employee Salary card ends here 18/05/2021 */
	
/* PAYROLL MOD EXCEL REPORT UPDATED ON 13-MAY-2021 */
	
	@ResponseBody
	@RequestMapping("/payrollmodsummaryexcel/{calcode}/{calper}/{bus_id}/{bus_name}")
	public String getPayrollMODSummaryExcel(@PathVariable("calcode") String calcode, @PathVariable("calper") int calper,@PathVariable("bus_id") String bus_id,
			@PathVariable("bus_name") String bus_name, HttpServletRequest request,HttpServletResponse response, Model model) {

		String paybill_url = appgateway.getPayrollMODUrl() + "/createPayrollMod/"+bus_id+"/"+bus_name+"/"+calcode+"/"+calper;
		

		//System.out.println("URL ====>>> "+paybill_url);
		String paybillmodel = null;

		HttpEntity<String> paybill_request = new HttpEntity<>(headers);

		ResponseEntity<String> paybill_response = restTemplate.exchange(paybill_url, HttpMethod.GET,
				paybill_request, String.class);

		if (paybill_response.getStatusCode() == HttpStatus.OK) {
			paybillmodel = paybill_response.getBody();
		} else {
			System.out.println("Request Failed");
//			System.out.println(paybill_response.getStatusCode());
		}

		//System.out.println("paybillmodel ===> "+paybillmodel);
		if(paybillmodel.equals("Batch job has been invoked"))
		{
			return "success";
		}
		else
		{
			return "error";
		}
		
}
	
	/* PAYROLL MOD-III PDF REPORT STARTS ON 15-FEB-2021 */

	@RequestMapping("/payrollmodsummarypdf/{calcode}/{bus_id}/{bus_name}/{format}")
	public ResponseEntity<byte[]> getPayrollMODSummaryPdf(@PathVariable("format") ExportFormat format,
			@PathVariable("calcode") String calcode, @PathVariable("bus_id") String bus_id,
			@PathVariable("bus_name") String bus_name, HttpServletRequest request, Model model) {

		headers.setContentType(MediaType.APPLICATION_JSON);

		String paybill_url = appgateway.getAppgateway_payroll() + "/api/mod/getpaybill/" + calcode + "/" + bus_id;

		ModPayBillComModel paybillmodel = new ModPayBillComModel();

		HttpEntity<String> paybill_request = new HttpEntity<>(headers);

		ResponseEntity<ModPayBillComModel> paybill_response = restTemplate.exchange(paybill_url, HttpMethod.GET,
				paybill_request, ModPayBillComModel.class);

		if (paybill_response.getStatusCode() == HttpStatus.OK) {
			paybillmodel = paybill_response.getBody();

		} else {
			System.out.println("Request Failed");
//			System.out.println(paybill_response.getStatusCode());
		}

		List<ModPayBillComModel> bdm2 = Arrays.asList(paybillmodel);
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(bdm2);

		JRBeanCollectionDataSource jrb1 = new JRBeanCollectionDataSource(paybillmodel.getPaybill());
		JRBeanCollectionDataSource jrb2 = new JRBeanCollectionDataSource(paybillmodel.getPaybill());

		Map<String, Object> params = new HashMap<>();
		params.put("mod3ModelData", jrb);
		params.put("cur_month", paybillmodel.getCur_month());
		params.put("pre_month", paybillmodel.getPre_month());
		params.put("cur_emp", paybillmodel.getCur_emp());
		params.put("pre_emp", paybillmodel.getPre_emp());
		params.put("diff_emp", paybillmodel.getDiff_emp());
		params.put("bus_name", bus_name.toUpperCase());
		params.put("subdatasource", jrb1);
		params.put("subdatasource1", jrb2);

		String contentType = null;

		if (format == ExportFormat.PDF)
			contentType = "application/pdf";
		else if (format == ExportFormat.XLSX)
			contentType = "application/vnd.ms-excel";

		byte[] bytes = reportService.generatePDFReport(format, "MOD_3", params);

		ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
				.filename("PayrollMOD_3Report_" + bus_name + "_" + calcode + "." + format).build();

		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", contentType + "; charset=UTF-8")
				// Tell browser to display PDF if it can
				.headers(headers).body(bytes);
	}

	/* OPEN PAY ROLL MOD REPORT WITH DATA */
	
}
