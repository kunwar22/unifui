package in.co.srdt.unif.controllers.reports;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import in.co.srdt.unif.enums.BankType;
import in.co.srdt.unif.enums.Months;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.ExcelModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payroll.PayGroups;
import in.co.srdt.unif.model.reports.AllBankDetails;
import in.co.srdt.unif.model.reports.BankAdviceRembModel;
import in.co.srdt.unif.model.reports.BankSalaryAdvice;
import in.co.srdt.unif.model.reports.BankTypeReportModel;
import in.co.srdt.unif.model.reports.BirthDetailsModel;
import in.co.srdt.unif.model.reports.EPFModel;
import in.co.srdt.unif.model.reports.ModPayBillComModel;
import in.co.srdt.unif.model.reports.Mod_Payroll_RegisterModel;
import in.co.srdt.unif.model.reports.NPSModel;
import in.co.srdt.unif.model.reports.PersonalData;
import in.co.srdt.unif.model.reports.ReimbTravellingReport;
import in.co.srdt.unif.model.reports.VendorDetailsModel;
import in.co.srdt.unif.model.reports.VendorSummeryReport;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.reports.ExportFormat;
import in.co.srdt.unif.reports.JasperReportsService;
import in.co.srdt.unif.reports.ReportService;
import in.co.srdt.unif.utils.ApplicationGateway;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/mod")
public class PayrollReportsController {

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
	/* @RequestMapping("/hrreports") */
	@RequestMapping("/payrollRep")
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

		
		return "fragments/reports/Reports :: Reports";
	}	
	
	/* LOADING OF CALENDARS BY PAY GROUP ID STARTS */
	
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
	
	/*  UPDATED ON 19-MARCH-2021 STARTS */
	
	
	/* OPEN PAY ROLL MOD REPORT WITH DATA */
	@ResponseBody
	@RequestMapping(value="/getpayroll_regexcel/{calcode}/{bus_id}/{busname}", method = RequestMethod.GET)
	public String loadPayrollRegexcel(@PathVariable("calcode") String calcode, @PathVariable("bus_id") String bus_id,
			@PathVariable("busname") String busname, HttpServletResponse response) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		String payroll_url = appgateway.getAppgateway_payroll() + "/api/mod/getModPayrollData/" + calcode + "/" + bus_id;
		
		
//		System.out.println("PAYROLL MOD ===> "+payroll_url);
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
		String curmonth = payrolregmodel.getCur();
		String premonth = payrolregmodel.getPre();
		
//		System.out.println("curmonth ===> "+curmonth);
//		System.out.println("premonth ===> "+premonth);
		
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
//		double curamt= 0,preamt=0,diffamt = 0;
		System.out.println("STRDATA ==== >>  "+strdata);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment; filename=Payroll_MOD3_" + calcode + "_" + busname + ".xlsx");
		try {
			genexfiles.WritePayrollRegisterMOD(strdata,curmonth,premonth,response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
		}

		return null;		
	}

	
	/* PAYROLL MOD EXCEL REPORT UPDATED ON 13-MAY-2021 */
	@ResponseBody
	@RequestMapping("/payrollmodsummaryexcel/{calcode}/{calper}/{bus_id}/{bus_name}")
	public String getPayrollMODSummaryExcel(@PathVariable("calcode") String calcode, @PathVariable("calper") int calper,@PathVariable("bus_id") String bus_id,
			@PathVariable("bus_name") String bus_name, HttpServletRequest request,HttpServletResponse response, Model model) {

		
String paybill_url = appgateway.getPayrollMODUrl() + "/createPayrollMod/"+bus_id+"/"+bus_name+"/"+calcode+"/"+calper;
		

//		System.out.println("URL ====>>> "+paybill_url);
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
	
	
	/* employee mod excel */

	@ResponseBody
	@RequestMapping("/employeemodexcel/{calcode}/{buid}/{busname}/{paygroupid}")
	public String getEmployeeMODReportExcel(@PathVariable("calcode") String calcode, @PathVariable("buid") String buid,
			@PathVariable("busname") String busname, @PathVariable("paygroupid") String paygroupid, HttpServletResponse response,Model model) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> psc_req = new HttpEntity<>(paysummPayLoad, headers);
		String empmodurl = appgateway.getAppgateway_payroll() + "/api/mod/getModPayrollData/" + calcode + "/" + buid;
		

		Mod_Payroll_RegisterModel empmod = null;

		ResponseEntity<Mod_Payroll_RegisterModel> empmod_response = restTemplate.exchange(empmodurl, HttpMethod.GET, psc_req,
				Mod_Payroll_RegisterModel.class);

		if (empmod_response.getStatusCode() == HttpStatus.OK) {
			empmod = empmod_response.getBody();
		} else {
			System.out.println("Request Failed");
		}

		String curmonth = empmod.getCur().substring(4);
		String premonth = empmod.getPre().substring(4);
				
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
		
		List<LinkedHashMap<String, String>> strdata = empmod.getData();
		
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=Employee_MOD_"
					+ calcode.substring(calcode.indexOf("-") + 1) + "_" + busname + ".xlsx");
			try {
				genexfiles.WriteEmpModRegister(strdata, busname, empmod.getCur(),calper_current,empmod.getPre(),calper_previous,response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				System.out.println("ERROR: " + e);
			}
		return null;
		
	}
	
	
	/* EMPLOYEE DETAILS EXCEL STARTS on 10-FEB-2021 */

	
	@RequestMapping("/empdataexcel")
	public String loadEmpdataExcel(HttpServletResponse response) {
		
		PersonalData[] personalDatas = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getpersonRecordData";/* check here */
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<PersonalData[]> empresponse = restTemplate.exchange(url, HttpMethod.GET, request,
				PersonalData[].class);

		if (empresponse.getStatusCode() == HttpStatus.OK) {
			personalDatas = empresponse.getBody();
//			 System.out.println("data::"+personalDatas.toString());
		} else {
			System.out.println("Request Failed");
			// System.out.println(response.getStatusCode());
		}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition","attachment; filename=Employee_Details.xlsx");
		try {
			genexfiles.WriteEmployeeDetails(personalDatas, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
		}
		
		return null;
		
		
		

	}

	/* EMPLOYEE DETAILS EXCEL ENDS ON 10-FEB-2021 */
	
	/* Bank Details Report PDF STARTS ON 9-FEB-2021 */

	
	@RequestMapping(value = "/bankmodpdf/{bus_id}/{bus_name}/{natureemp}/{natemp}/{banktype}/{format}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> loadBankmodpdf(@PathVariable("format") ExportFormat format,
			@PathVariable("bus_id") int bus_id, @PathVariable("bus_name") String bus_name, @PathVariable("natureemp") String natureemp, @PathVariable("natemp") String natemp, @PathVariable("banktype") String banktype, HttpServletRequest request) {

		AllBankDetails[] allBankDetails = null;

		String urlbank = appgateway.getAppgateway() + "/api/report/getAllBankDetails/" + bus_id+"/"+natureemp+"/"+banktype; /* check here */

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> bankrequest = new HttpEntity<String>(headers);

		ResponseEntity<AllBankDetails[]> responsePerso = restTemplate.exchange(urlbank, HttpMethod.GET, bankrequest,
				AllBankDetails[].class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			allBankDetails = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			// System.out.println(responsePerso.getStatusCode());
		}

		List<AllBankDetails> bdm2 = Arrays.asList(allBankDetails);
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(bdm2);

		Map<String, Object> params = new HashMap<>();
		params.put("bus_name", bus_name.toUpperCase());
		params.put("noe", natemp.toUpperCase());
		params.put("bank", banktype.toUpperCase());
		params.put("bankmodelData", jrb);
		// params.put("imgpath",img_path+"UPMRCL.png");

		String contentType = null;

		if (format == ExportFormat.PDF)
			contentType = "application/pdf";
		else if (format == ExportFormat.XLSX)
			contentType = "application/vnd.ms-excel";

		byte[] bytes = reportService.generatePDFReport(format, "BankDetailsReport", params);

		ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
				.filename("BankDetailsReport_MOD_" + bus_name +"_"+banktype+"_"+natemp+"."+ format).build();

		headers.setContentDisposition(contentDisposition);

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", contentType + "; charset=UTF-8")
				// Tell browser to display PDF if it can
				.headers(headers).body(bytes);

	}

	/* Bank Details Report PDF ENDS ON 9-FEB-2021 */

	/* Bank Details Report EXCEL STARTS ON 15-FEB-2021 */

	@ResponseBody
	@RequestMapping(value = "/bankmodexcel/{bus_id}/{bus_name}/{natureemp}/{natemp}/{banktype}", method = RequestMethod.GET)
	public String loadBankmodexcel(@PathVariable("bus_id") int bus_id, @PathVariable("bus_name") String bus_name, @PathVariable("natureemp") String natureemp, @PathVariable("natemp") String natemp, @PathVariable("banktype") String banktype,HttpServletResponse response) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		AllBankDetails[] allBankDetails = null;		
		String urlbank = appgateway.getAppgateway() + "/api/report/getAllBankDetails/" + bus_id+"/"+natureemp+"/"+banktype; /* check here */	
				
		//System.out.println("Bank MOD URL ===> "+urlbank);
		
		HttpEntity<String> bankrequest = new HttpEntity<String>(headers);

		ResponseEntity<AllBankDetails[]> responsePerso = restTemplate.exchange(urlbank, HttpMethod.GET, bankrequest,
				AllBankDetails[].class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			allBankDetails = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			// System.out.println(responsePerso.getStatusCode());
		}
		

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=BankDetailsReport_MOD_" + bus_name +"_"+banktype+"_"+natemp+".xlsx");
		try {
			genexfiles.WriteBankModRegister(allBankDetails, bus_name, banktype, natemp, response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);
		}
		
		return null;
}
	
	/* Birthday Report PDF STARTS ON 6-FEB-2021 */

	@RequestMapping(value = "/loadBirthdayspdf/{bus_name}/{bus_id}/{mnth}/{format}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> loadBirthdayspdf(@PathVariable("format") ExportFormat format,
			@PathVariable("bus_name") String bus_name, @PathVariable("bus_id") int bus_id,
			@PathVariable("mnth") String mnth, Model model, HttpServletRequest request) {

		BirthDetailsModel[] bdm = null;
		

		String urlBirthDetails = appgateway.getAppgateway() + "/PersonDetails/getAllPersonBirthday/" + bus_id
				+ "/" + mnth;

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> birthdayrequest = new HttpEntity<String>(headers);

		ResponseEntity<BirthDetailsModel[]> birthDetailResponse = restTemplate.exchange(urlBirthDetails, HttpMethod.GET,
				birthdayrequest, BirthDetailsModel[].class);

		if (birthDetailResponse.getStatusCode() == HttpStatus.OK) {
			bdm = birthDetailResponse.getBody();
		}

		List<BirthDetailsModel> bdm2 = Arrays.asList(bdm);
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(bdm2);

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
//		String img_path = "/EmployeeDocs/";
		Map<String, Object> params = new HashMap<>();
		params.put("Month", mnth);
		params.put("Year", year);
		params.put("BusinessUnit", bus_name.toUpperCase());
		params.put("modelData", jrb);

//		params.put("imgpath", img_path + "UPMRCL.png");

		String contentType = null;

		if (format == ExportFormat.PDF)
			contentType = "application/pdf";
		else if (format == ExportFormat.XLSX)
			contentType = "application/vnd.ms-excel";

		byte[] bytes = reportService.generatePDFReport(format, "BirthdayReport", params);

		ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
				.filename("BirthdayReport." + format).build();

		headers.setContentDisposition(contentDisposition);

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", contentType + "; charset=UTF-8")
				// Tell browser to display PDF if it can
				.headers(headers).body(bytes);

	}
	@RequestMapping(value = "/loadBirthdaysexcel/{bus_name}/{bus_id}/{mnth}/{format}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> loadBirthdaysexcel(@PathVariable("format") ExportFormat format,
			@PathVariable("bus_name") String bus_name, @PathVariable("bus_id") int bus_id,
			@PathVariable("mnth") String mnth, Model model, HttpServletRequest request) {

		BirthDetailsModel[] bdm = null;

		String urlBirthDetails = appgateway.getAppgateway() + "/PersonDetails/getAllPersonBirthday/" + bus_id + "/" + mnth;

		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> birthdayrequest = new HttpEntity<String>(headers);

		ResponseEntity<BirthDetailsModel[]> birthDetailResponse = restTemplate.exchange(urlBirthDetails, HttpMethod.GET,
				birthdayrequest, BirthDetailsModel[].class);

		if (birthDetailResponse.getStatusCode() == HttpStatus.OK) {
			bdm = birthDetailResponse.getBody();
		}

		List<BirthDetailsModel> bdm2 = Arrays.asList(bdm);
		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(bdm2);

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
//		String img_path = "/EmployeeDocs/";
		Map<String, Object> params = new HashMap<>();
		params.put("Month", mnth);
		params.put("Year", year);
		params.put("BusinessUnit", bus_name.toUpperCase());
		params.put("modelData", jrb);

//		params.put("imgpath", img_path + "UPMRCL.png");

		String contentType = null;

		if (format == ExportFormat.PDF)
			contentType = "application/pdf";
		else if (format == ExportFormat.XLSX)
			contentType = "application/vnd.ms-excel";

		byte[] bytes = reportService.generatePDFReport(format, "BirthdayReport", params);

		ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
				.filename("BirthdayReport." + format).build();

		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", contentType + "; charset=UTF-8")
				// Tell browser to display PDF if it can
				.headers(headers).body(bytes);
	}
	
	/* Birthday Report EXCEL ENDS ON 9-FEB-2021 */
	
	
	/* FUNCTION FOR PAYROLL REGISTER STARTS 01-03-2021 */
	@ResponseBody
	@RequestMapping("/payrollregister/{bus_id}/{busname}/{calcode}/{natemp}/{natureemp}")
	public String getPayrollRegister(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname,
			@PathVariable("calcode") String calcode, @PathVariable("natemp") int natemp,
			@PathVariable("natureemp") String natureemp,HttpServletResponse response) throws Exception {
		
		int count = 0;
		headers.setContentType(MediaType.APPLICATION_JSON);

		String payrollregister_url = "";
		
//		http://localhost:9127/api/mod/getPayrollRegister/LPY-APR-2021/20001/IDA
		if(natemp==0)
		{			
			payrollregister_url = appgateway.getAppgateway_payroll() +"/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natemp;
		}
		else
		{
			payrollregister_url = appgateway.getAppgateway_payroll() +"/api/mod/getPayrollRegister/" + calcode + "/"+ bus_id + "/" + natureemp; /* check here */
		}
		
		System.out.println("URL ===> "+payrollregister_url);

		ExcelModel payrollregworkbook = null;

		
		HttpEntity<String> payrollreg_request = new HttpEntity<>(headers);

		ResponseEntity<ExcelModel> payrollreg_response = restTemplate.exchange(payrollregister_url,
				HttpMethod.GET, payrollreg_request, ExcelModel.class);

		if (payrollreg_response.getStatusCode() == HttpStatus.OK) 
		{
			payrollregworkbook = payrollreg_response.getBody();
		}
				
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=PayrollRegister_"
				+ calcode.substring(calcode.indexOf("-") + 1) + "_" + busname + ".xlsx");
		try {
			genexfiles.WritePayrollRegister(payrollregworkbook.getData(), busname, calcode, natureemp, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);

		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/lwpreport/{fdt}/{tdt}", method = RequestMethod.GET)
	public String lwpreport(@PathVariable("fdt") String fdt, @PathVariable("tdt") String tdt) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity < String > lwprequest = new HttpEntity < String > (headers);
		
		String lwpReport = null;
		
		String urllwpDetails = appgateway.getLwpURL() + "/invokejob/" + fdt + "/" + tdt; /* check here */
//		String urllwpDetails = "http://localhost:7074/absence/Person/LWP/getLwpReportList/"+fdt+"/"+tdt;

		ResponseEntity <String> responselwp = restTemplate.exchange(urllwpDetails, HttpMethod.GET, lwprequest, String.class);
		

		if (responselwp.getStatusCode() == HttpStatus.OK) {
			lwpReport = responselwp.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		System.out.println("lwp response ::: "+responselwp.getBody());
		if(responselwp.getBody().equals("Batch job has been invoked"))
			return "success";
		else 
			return "error";
	}

	/* LWP PDF Function Starts here */
//	@RequestMapping(value = "/loadLwppdf/{fdt}/{tdt}/{format}", method = RequestMethod.GET)
//	public ResponseEntity<byte[]> loadLwppdf(@PathVariable("format") ExportFormat format,
//			@PathVariable("fdt") String fdt, @PathVariable("tdt") String tdt, Model model, HttpServletRequest request) {
//
//		LwpReport[] lwpmodel = null;
//
//		String urllwpDetails = appgateway.getAppgatewayabs() + "/Person/LWP/getLwpReportList/" + fdt + "/" + tdt;
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> lwprequest = new HttpEntity<String>(headers);
//
//		ResponseEntity<LwpReport[]> lwp_response = restTemplate.exchange(urllwpDetails, HttpMethod.GET, lwprequest,
//				LwpReport[].class);
//		if (lwp_response.getStatusCode() == HttpStatus.OK) {
//			lwpmodel = lwp_response.getBody();
//		} else {
//			System.out.println("Request Failed");
//		}
//
//		List<LwpReport> bdm2 = Arrays.asList(lwpmodel);
//		JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(bdm2);
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("lwpmodeldata", jrb);
//
//		String contentType = null;
//
//		if (format == ExportFormat.PDF)
//			contentType = "application/pdf";
//		else if (format == ExportFormat.XLSX)
//			contentType = "application/vnd.ms-excel";
//
//		byte[] bytes = reportService.generatePDFReport(format, "LWPReport", params);
//
//		ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename("LWPReport." + format)
//				.build();
//
//		headers.setContentDisposition(contentDisposition);
//
//		return ResponseEntity.ok()
//				
//				.header("Content-Type", contentType + "; charset=UTF-8")
//				
//				.headers(headers).body(bytes);
//
//	}

	/* LWP PDF Export Function Ends Here */

	/* LWP xlsx Function Starts here */
//	@RequestMapping(value = "/loadLwpxls/{fdt}/{tdt}", method = RequestMethod.GET)
//	public String loadLwpxls(@PathVariable("fdt") String fdt, @PathVariable("tdt") String tdt,HttpServletResponse response) {
//
//		LwpReport[] lwpmodel = null;
//
//		String urllwpDetails = appgateway.getAppgatewayabs() + "/Person/LWP/getLwpReportList/" + fdt + "/" + tdt;
////		String urllwpDetails = "http://localhost:7074/absence/Person/LWP/getLwpReportList/"+fdt+"/"+tdt;
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> lwprequest = new HttpEntity<String>(headers);
//
//		ResponseEntity<LwpReport[]> lwp_response = restTemplate.exchange(urllwpDetails, HttpMethod.GET, lwprequest,
//				LwpReport[].class);
//		if (lwp_response.getStatusCode() == HttpStatus.OK) {
//			lwpmodel = lwp_response.getBody();
//		} else {
//			System.out.println("Request Failed");
////			System.out.println(lwp_response.getStatusCode());
//		}
//
//		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-disposition", "attachment; filename=LWP_Report.XLSX");
//		try {
//			genexfiles.WriteLWPReport(lwpmodel, response.getOutputStream());
//			response.flushBuffer();
//		} catch (IOException e) {
//			System.out.println("ERROR: " + e);
//		}
//		return null;
//
//	}

	/* LWP Xlsx Export Function Ends Here */

	
	
	
	
	/*  UPDATED ON 19-MARCH-2021 ENDS */
	
		
	/* OPENS SALARY SUMMARY BILL */
	
/*	@RequestMapping("/salarysummarybillexcel/{bus_id}/{bus_name}/{calid}/{cal_code}/{paygrpid}/{perno}")
	public String getSalaryBillExcel(@PathVariable("bus_id") String bus_id, @PathVariable("bus_name") String bus_name,
			@PathVariable("calid") String calid, @PathVariable("cal_code") String cal_code,
			@PathVariable("paygrpid") String paygrpid, @PathVariable("perno") String perno, HttpServletResponse response)
	{	
		double sum_earn[];
		double sum_ded[];
		double sum_contri[];
		double net_pay[];
		
		
		//System.out.print(false);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//System.out.println("Person Number :: "+perno);
		if(perno.equals("x"))
		{
			paysummPayLoad ="{"
					+ "  \"buid\": \""+bus_id+"\","
					+ "  \"calid\": \""+calid+"\","
					+ "  \"paygroupid\": \""+paygrpid+"\","
					+ "  \"personnumber\": \"\""
					+ "}";
			
		}
		else
		{
			paysummPayLoad ="{"
					+ "  \"buid\": \""+bus_id+"\","
					+ "  \"calid\": \""+calid+"\","
					+ "  \"paygroupid\": \""+paygrpid+"\","
					+ "  \"personnumber\": \""+perno+"\""
					+ "}";
			
		}
		System.out.println("PAY SUMMARY PAY LOAD :::: "+paysummPayLoad);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int date = now.get(Calendar.DATE);
		String dt = date + "/0" + month + "/" + year;
		
		HttpEntity<String> salbil_req = new HttpEntity<>(paysummPayLoad,headers);
		
		String salbil_url =appgateway.getAppgateway_payroll()+"/api/runpayroll/getelementwisepayrollslipsummery";
				
		System.out.println("SAL URL ===> "+salbil_url);
		
		PayrollSummery[] pyrlsumm = null;
		
		
		ResponseEntity<PayrollSummery[]> salbill_response = restTemplate.exchange(salbil_url, HttpMethod.POST,salbil_req,PayrollSummery[].class);
		
		if(salbill_response.getStatusCode() == HttpStatus.OK)
		{			
			pyrlsumm = salbill_response.getBody();
			System.out.println("PAY ROLL SUMMARY LENGTH ===>"+pyrlsumm.length);
		}
		else
		{
			System.out.println("Request Failed");
			//System.out.println(salbill_response.getStatusCode());
		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment; filename=SalaryBillSummaryReport_" + cal_code.substring(cal_code.indexOf("-") + 1) + "_" + bus_name + ".xls");
		try {
			genexfiles.WriteSalarySummaryBill(pyrlsumm,bus_name,cal_code,dt, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
		}

		return null;
		
	}*/

	@ResponseBody
	@RequestMapping("/salarysummarybill/{bus_id}/{bus_name}/{calid}/{cal_code}/{paygrpid}/{natureemp}/{natemp}/{perno}")
	public String getSalaryBill(@PathVariable("bus_id") String bus_id, @PathVariable("bus_name") String bus_name,
			@PathVariable("calid") String calid, @PathVariable("cal_code") String cal_code,
			@PathVariable("paygrpid") String paygrpid, @PathVariable("natureemp") String natureemp,@PathVariable("natemp") String natemp, @PathVariable("perno") String perno, HttpServletRequest request,
			Model model)
	{	
			
		HttpEntity<String> salbil_req = new HttpEntity<>(paysummPayLoad,headers);
		String pyrlsumm ="";
		String salbil_url = appgateway.getSalbillURL()+"/invokejob/"+bus_id+"/"+bus_name+"/"+calid+"/"+cal_code+"/"+paygrpid+"/"+natureemp+"/"+natemp+"/"+perno;
		
		System.out.println("SAL URL ===> "+salbil_url);
		
		ResponseEntity<String> salbill_response = restTemplate.exchange(salbil_url, HttpMethod.GET,salbil_req,String.class);
		
		if(salbill_response.getStatusCode() == HttpStatus.OK)
		{			
			pyrlsumm = salbill_response.getBody();
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
	
	/* Bank Advise Report EXCEL STARTS ON 13-MAR-2021 */
	@RequestMapping(value = "/bankadviceexcel/{buid}/{bus_name}/{paygroupid}/{calid}/{cal_code}/{banktype}", method = RequestMethod.GET)
	public String loadBankAdviceExcel(@PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("calid") String calid, @PathVariable("cal_code") String cal_code, @PathVariable("banktype") String banktype, Model model, HttpServletResponse response) {

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity < String > salbil_req = new HttpEntity < >(headers);

//		String salbil_url = appgateway.getAppgateway_payroll()+"/api/mod/getBankSalaryAdviceByParamiter/" + cal_code + "/" + buid + "/" + paygroupid + "/" + banktype;
		
		String salbil_url = appgateway.getAppgateway_payroll()+"/api/mod/getBankSalaryAdviceNewByParamiter/" + cal_code + "/" + buid + "/" + paygroupid + "/" + banktype;

		//System.out.println("URL ====> "+salbil_url);
		BankSalaryAdvice payrollregmodel[] = null;

		ResponseEntity < BankSalaryAdvice[] > responsePerso = restTemplate.exchange(salbil_url, HttpMethod.GET, salbil_req, BankSalaryAdvice[].class);

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
	
	/* Bank wise Employee Details Report EXCEL STARTS ON 12-MAR-2021 */

	@RequestMapping(value = "/bankwiseexcel/{buid}/{bus_name}/{paygroupid}/{cal_code}/{banktype}/{format}", method = RequestMethod.GET)
	public String loadBankWiseExcel(@PathVariable("format") ExportFormat format, @PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("cal_code") String cal_code, @PathVariable("banktype") String banktype, Model model, HttpServletResponse response) {

		BankTypeReportModel[] bankTypeReportModel = null;

		String urlbank = appgateway.getAppgateway() + "/api/report/getBankDataByParamiter/" + buid + "/" + paygroupid + "/" + cal_code + "/" + banktype;
//		System.out.println("URL ====> "+urlbank);

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
	
	/* FUNCTION FOR EPF REPORT STARTS 24-03-2021 */
	@RequestMapping("/getepfreport/{bus_id}/{busname}/{calcode}/{natemp}/{paygroupid}")
	public String getEPFReport(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname, @PathVariable("calcode") String calcode, @PathVariable("natemp") String natemp, @PathVariable("paygroupid") String paygroupid, HttpServletResponse response) {

	headers.setContentType(MediaType.APPLICATION_JSON);

	String epf_url = appgateway.getAppgateway_payroll() + "/api/mod/getEpfReportByParameter/" + calcode + "/" + bus_id + "/" + natemp + "/" + paygroupid;
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
		String payrollregister_url = appgateway.getAppgateway_payroll() + "/api/mod/getNpfReportByParameter/" +calcode+"/"+bus_id+"/"+natemp+"/"+paygroupid;
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
	
	
	/* Bank wise Employee Details Report EXCEL STARTS ON 12-MAR-2021 */

	@RequestMapping(value = "/bankadviceempremb/{buid}/{bus_name}/{paygroupid}/{cal_code}/{startdate}/{enddate}/{banktype}", method = RequestMethod.GET)
	public String loadBankAdviceEmpRembExcel(@PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid,@PathVariable("cal_code") String cal_code, @PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate, @PathVariable("banktype") String banktype, HttpServletResponse response) {

		BankAdviceRembModel[] bankAdviceRembModel = null;

		String urlbank = appgateway.getAppgatewaypyrl_sandhya() + "/reimbursmentreport/getReimbBankdataReport/" + startdate + "/" + enddate + "/" + buid + "/" + paygroupid + "/" + banktype;
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity < String > bankrequest = new HttpEntity < String > (headers);

		ResponseEntity < BankAdviceRembModel[] > responsePerso = restTemplate.exchange(urlbank, HttpMethod.GET, bankrequest, BankAdviceRembModel[].class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			bankAdviceRembModel = responsePerso.getBody();
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
		response.setHeader("Content-disposition", "attachment; filename=LMRC_Employee_Reimbursement_Bank_Tran_" + cal_code + "_" + bus_name + "_" + banktype + ".xlsx");
		try {
			genexfiles.WriteEmpRembBankTransactionRegister(bankAdviceRembModel, dt, bus_name, cal_code, response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}

		return null;
	}
	
	/* IDA_CDA wise Employee Payslip STARTS ON 05-APRIL-2021 */

	@ResponseBody
	@RequestMapping(value = "/getpayslip/{buid}/{bus_name}/{paygroupid}/{cal_code}/{fromcal}/{natureemp}/{natemp}/{per_no}", method = RequestMethod.GET)
	public String loadPaySlip(@PathVariable("buid") int buid, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid,
			@PathVariable("cal_code") String cal_code, @PathVariable("fromcal") String fromcal, @PathVariable("natureemp") String natureemp, 
			@PathVariable("natemp") String natemp, @PathVariable("per_no") String per_no,Model model) 
	{		

		String url = "";
		
		if(per_no.equals("x"))
		{
			if(natureemp.equals("0"))
			{						
			url = appgateway.getPayslipAllUrl() + "/invokejob/"+buid+"/"+bus_name+"/"+fromcal+"/"+cal_code+"/"+paygroupid+"/x/x/"+natemp;
			}else {
				url = appgateway.getPayslipAllUrl() + "/invokejob/"+buid+"/"+bus_name+"/"+fromcal+"/"+cal_code+"/"+paygroupid+"/x/"+natureemp+"/"+natemp;	
			}
		}
		else if(!per_no.equals("x"))
		{
			url = appgateway.getPayslipUrl() + "/invokejob/"+buid+"/"+fromcal+"/"+paygroupid+"/"+per_no+"/"+natureemp;
		}
		//System.out.println("URL ==> "+url);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String payrlsum ="";

		//System.out.println(paysummPayLoad);
		HttpEntity<String> payrep = new HttpEntity<String>(paysummPayLoad, headers);

		ResponseEntity<String> payslipresponse = restTemplate.exchange(url, HttpMethod.GET, payrep,String.class);

		if (payslipresponse.getStatusCode() == HttpStatus.OK) {
			payrlsum = payslipresponse.getBody();
		} else {
			System.out.println("Request Failed");
		}
		
		if(payrlsum.equals("Batch job has been invoked"))
			return "success";
		else 
			return "error";
		
	
	}
	
	/* Vendor Details Report starts here 23/04/2021 */ 
	
	@RequestMapping(value = "/getvendordetails/{paygroupid}/{cal_code}", method = RequestMethod.GET)
	public String getVendorDetails(@PathVariable("paygroupid") String paygroupid,@PathVariable("cal_code") String cal_code, HttpServletResponse response) {

		VendorDetailsModel[] vendorModel = null;
		VendorSummeryReport[] vendorSummary =null;
		String urlvendor = appgateway.getAppgateway_payroll() + "/api/vendorreport/getVendorDedutnRecvyById/" + cal_code+"/"+paygroupid;

		String urlvendorsumm = appgateway.getAppgateway_payroll() + "/api/vendorreport/getVendorSummeryReport/" + cal_code+"/"+paygroupid;
		
//		System.out.println("VEndor URL ===> "+urlvendor);
//		System.out.println("VEndor Summary URL ===> "+urlvendorsumm);
		
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
	
	/* Employee Salary card starts here 31/05/2021 */ 

	
	/* Employee Salary card starts here 17/05/2021 */ 
	@ResponseBody
	@RequestMapping(value = "/salarycard/{cal_code}/{tocalcode}/{bus_id}/{bus_name}/{paygroupid}/{per_no}", method = RequestMethod.GET)
	public String getSalaryCard(@PathVariable("cal_code") String cal_code, @PathVariable("tocalcode") String tocalcode ,@PathVariable("bus_id") String bus_id, @PathVariable("bus_name") String bus_name, @PathVariable("paygroupid") String paygroupid, @PathVariable("per_no") String per_no, HttpServletResponse response) {
		
HttpEntity < String > salcardrequest = new HttpEntity < String > (headers);
		
		String pyrlsalcards = null;

		String salcard_url = appgateway.getSalcardURL() +"/createSalaryCard/"+bus_id+"/"+cal_code+"/"+tocalcode+"/"+paygroupid+"/"+per_no;
		System.out.println("URL ==> "+salcard_url);
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
	
	//CURRENT PAYROLL REGISTER
	
	/* FUNCTION FOR CURRENT PAYROLL REGISTER STARTS 01-03-2021 */
	
	@RequestMapping("/curentpayrollregister/{bus_id}/{busname}/{staticcal}/{staticcalcode}/{natemp}")
	public String getcurentPayrollRegister(@PathVariable("bus_id") String bus_id, @PathVariable("busname") String busname,
			@PathVariable("staticcal") String staticcal,@PathVariable("staticcalcode") String staticcalcode, @PathVariable("natemp") String natemp,
			HttpServletResponse response, Model model) throws IOException {


		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		String payrollregister_url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getpayrollregisterhrforNOE/"+staticcal+"/"+natemp;
		 
//		System.out.println("URL ===> "+payrollregister_url);
		
		ExcelModel workbook = null;
		ResponseEntity<ExcelModel> res = restTemplate.exchange(payrollregister_url, HttpMethod.GET, request, ExcelModel.class);
        if (res.getStatusCode() == HttpStatus.OK) 
        {
            workbook = res.getBody();
        }
    	response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=Payroll Register: " +busname+"_"+ staticcalcode.substring(4) + ".xlsx");
        genexfiles.WriteCurrentPayrollRegister(workbook.getData(), response.getOutputStream(), staticcalcode);
        response.flushBuffer();	 
        
return null;
	}
	
	@ResponseBody
	@RequestMapping("/getTravelReport/{bus_id}/{bus_name}/{fdt}/{tdt}")
	public String getTravelReport(@PathVariable("bus_id") String bus_id,
			@PathVariable("bus_name") String bus_name,
			@PathVariable("fdt") String fdt,
			@PathVariable("tdt") String tdt,HttpServletRequest requestt,HttpServletResponse response ,Model model) throws IOException
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Login login = (Login) requestt.getSession().getAttribute("login");
		
		String travelreporturl = appgateway.getAppgatewaypyrl_sandhya() + "/addons/reimbursmentreport/getReimbsTravellingReport/"+login.getEmplid()+"/"+bus_id+"/"+fdt+"/"+tdt;
		 
		System.out.println("URL ===> "+travelreporturl);
		
		ReimbTravellingReport[] traveldata = null;
		
		ResponseEntity<ReimbTravellingReport[]> res = restTemplate.exchange(travelreporturl, HttpMethod.GET, request, ReimbTravellingReport[].class);
		
        if (res.getStatusCode() == HttpStatus.OK) 
        {
        	traveldata = res.getBody();
        }
        
        
        Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int date = now.get(Calendar.DATE);
		String dt = date + "/0" + month + "/" + year;
		
//		model.addAttribute("traveldata", traveldata);
//		model.addAttribute("date", dt);
		int count = 0;
		
		String html_text = "<html><head><style type='text/css'>body{font-family : Calibri; font-size:12px;}</style></head><body><table border='1'><tr><th colspan='18'>Uttar Pradesh Metro Rail Corporation Ltd.</th></tr>"
				+ "<tr><th colspan='6'>No...HR/74-2(A) Vol.-II/2015 </th>"
				+ "<th colspan='12'>Date: "+dt+"</th></tr>"
				+ "<tr><th colspan='18'>Note</th></tr><tr><th colspan='18'>Sub:- Reimbursement of Travelling, allowance, Comp TA, Fare, Cont. & Hotel Charges etc.</th></tr>"
				+ "<tr><th colspan='18'>The undernoted staff and officers of Lucknow of  UPMRC have claimed reimbursement of TA, Cont, fare and Hotel Charges on account of performing their outstation duties. The details are as under.</th></tr>"
				+ "<tr><th>S.No.</th><th>Emp ID</th><th>Employee Name</th><th>Designation</th><th>Claim Date</th><th>Travel Start Date</th>"
				+ "<th>Travel End Date</th><th>Visiting City1</th><th>Visiting City2</th><th>Approved Date</th><th>Approver Comments</th>"
				+ "<th>Travel Date</th><th>Expenses Type</th><th>Expenses Description</th><th>Actual Amount Paid</th><th>Total Claim Amount</th>"
				+ "<th>Approved Amount</th><th>Total Approved Amount</th></tr>";
		
		int rowspan = 0;
		double totalclaim = 0,totalapproved = 0;
		for(int i=0;i<traveldata.length;i++)
		{			
			rowspan = traveldata[i].getTravelingExpense().size();
			if(rowspan == 1)
			{
				html_text+="<tr>";
				html_text+="<td>"+ ++count+"</td>";
				html_text +="<td>"+ traveldata[i].getEmployeenumber()+"</td>";
				html_text +="<td>"+ traveldata[i].getEmployeename()+"</td>";
				html_text +="<td>"+ traveldata[i].getDesignation()+"</td>";
				html_text +="<td>"+ traveldata[i].getClaimdate()+"</td>";
				html_text +="<td>"+ traveldata[i].getTravelstartdate()+"</td>";
				html_text +="<td>"+ traveldata[i].getTravelenddate()+"</td>";
				html_text +="<td>"+ traveldata[i].getVisitingcity1()+"</td>";
				html_text +="<td>"+ traveldata[i].getVisitingcity2()+"</td>";
//				html_text +="<td>"+ traveldata[i].getApinvoiceno()+"</td>";				
				html_text +="<td>"+ traveldata[i].getApproveddate()+"</td>";
				html_text +="<td>"+ traveldata[i].getApprovercomments()+"</td>";
//				html_text +="<td>"+ traveldata[i].getTotalapprovedamount()+"</td>";
				
				for(int j = 0; j<traveldata[i].getTravelingExpense().size();j++)
				{
					totalclaim= totalclaim+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getActualamountpaid());
					totalapproved= totalapproved+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getApprovedamount());
				}
				
				for(int j = 0; j<traveldata[i].getTravelingExpense().size();j++)
				{
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getTraveldate()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getExpensestype()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getExpensesdescription()+"</td>";
				}
				html_text +="<td>"+ totalclaim +"</td>";
				for(int j = 0; j<traveldata[i].getTravelingExpense().size();j++)
				{
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getActualamountpaid()+"</td>";
				}
				
				for(int j = 0; j<traveldata[i].getTravelingExpense().size();j++)
				{
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getApprovedamount()+"</td>";				
				}
				html_text +="<td>"+ totalapproved +"</td>";
				
				
				
				html_text +="</tr>";
			}
			else
			{
				html_text+="<tr>";
				html_text+="<td rowspan = '"+rowspan+"'>"+ ++count+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getEmployeenumber()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getEmployeename()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getDesignation()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getClaimdate()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getTravelstartdate()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getTravelenddate()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getVisitingcity1()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getVisitingcity2()+"</td>";
//				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getApinvoiceno()+"</td>";
				
				
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getApproveddate() +"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getApprovercomments()+"</td>";
//				html_text +="<td rowspan = '"+rowspan+"'>"+ traveldata[i].getTotalapprovedamount()+"</td>";
				html_text +="<td>"+ traveldata[i].getTravelingExpense().get(0).getTraveldate()+"</td>";
				html_text +="<td>"+ traveldata[i].getTravelingExpense().get(0).getExpensestype()+"</td>";
				html_text +="<td>"+ traveldata[i].getTravelingExpense().get(0).getExpensesdescription()+"</td>";
				
				for(int j = 0; j<traveldata[i].getTravelingExpense().size();j++)
				{
					totalclaim= totalclaim+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getActualamountpaid());
					totalapproved= totalapproved+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getApprovedamount());
				}			
				
				html_text +="<td>"+ traveldata[i].getTravelingExpense().get(0).getActualamountpaid()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ totalclaim +"</td>";
				
				html_text +="<td>"+ traveldata[i].getTravelingExpense().get(0).getApprovedamount()+"</td>";
				html_text +="<td rowspan = '"+rowspan+"'>"+ totalapproved +"</td>";
				html_text +="</tr>";
				for(int j = 1; j<traveldata[i].getTravelingExpense().size();j++)
				{
					totalclaim= totalclaim+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getActualamountpaid());
					totalapproved= totalapproved+Double.parseDouble(traveldata[i].getTravelingExpense().get(j).getApprovedamount());
					html_text+="<tr>";					
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getTraveldate()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getExpensestype()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getExpensesdescription()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getActualamountpaid()+"</td>";
					html_text +="<td>"+ traveldata[i].getTravelingExpense().get(j).getApprovedamount()+"</td>";
					html_text+="</tr>";
				}				
			}
			
			totalclaim = 0;
			totalapproved = 0;
		}
		
		html_text+="</table></body></html>";
		
		//System.out.println("html ===> "+html_text);
		
		String htmlfileName = appgateway.getTravelInputFilePath()+"/TravelReimbursement"+"_"+bus_name+".html";
		String excelFileName = appgateway.getTravelOutputFilePath()+"/TravelReimbursement"+"_"+bus_name+".xls";
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
//              //System.out.print((char) c);
              os.write(c);
            }
            is.close();
            os.close();

            //System.out.println("success in creating xls file ...");
           }catch(Exception e){System.out.println(e);}       


		
		
//		response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-disposition", "attachment; filename=TravelReimbursement_" +bus_name+ ".xlsx");
//        genexfiles.WriteTravelReimbursementReport(traveldata, dt,response.getOutputStream());
//        response.flushBuffer();	 
        
//return null;
		return "success";
	}
	
	
////////////////////////////code for generating reports starts 14-07-2021 //////////////////////////
	
@RequestMapping("/generateReports")
public String generateReports(Model model)
{
System.out.println("generateReports");

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

return "fragments/reports/generateReports :: generateReports";

}

@ResponseBody
//url = "/mod/creategenerateurl/"+reporttype+"/"+bus_id+"/"+bus_name+"/"+paygroupid+"/"+calid+"/"+calcode+"/"+natureemp+"/"+natemp+"/"+per_no;
@RequestMapping("/creategenerateurl/{reporttype}/{bus_id}/{bus_name}/{paygroupid}/{calid}/{calcode}/{natureemp}/{natemp}/{per_no}")
public String createGenerationURL(@PathVariable("reporttype") String reporttype,
@PathVariable("bus_id") String bus_id,
@PathVariable("bus_name") String bus_name,
@PathVariable("paygroupid") String paygroupid,
@PathVariable("calid") String calid,
@PathVariable("calcode") String calcode,
@PathVariable("natureemp") String natureemp,
@PathVariable("natemp") String natemp,
@PathVariable("per_no") String per_no)
{

//System.out.println("Report Type :: "+reporttype);
//System.out.println("bus id :: "+bus_id);
//System.out.println("bus_name :: "+bus_name);
//System.out.println("paygroupid :: "+paygroupid);
//System.out.println("calid :: "+calid);
//System.out.println("calcode :: "+calcode);
//System.out.println("natureemp :: "+natureemp);
//System.out.println("natemp :: "+natemp);
//System.out.println("per_no :: "+per_no);

String url = "";



switch(reporttype)
{
case "1":
if(per_no.equals("x") && natureemp.equals("0"))
{
System.out.println("YES");
url = appgateway.getPayslipUrl()+"/invokejob/"+bus_id+"/"+calid+"/"+paygroupid+"/x/x";
}
else
{
url = appgateway.getPayslipUrl()+"/invokejob/"+bus_id+"/"+calid+"/"+paygroupid+"/"+per_no+"/"+natureemp;
}			
break;
case "2":			
if(natureemp.equals("0"))
{						
url = appgateway.getPayslipAllUrl() + "/invokejob/"+bus_id+"/"+bus_name+"/"+calid+"/"+calcode+"/"+paygroupid+"/x/x/"+natemp;
}else {
url = appgateway.getPayslipAllUrl() + "/invokejob/"+bus_id+"/"+bus_name+"/"+calid+"/"+calcode+"/"+paygroupid+"/x/"+natureemp+"/"+natemp;	
}
break;
case "3":
url = appgateway.getSalbillURL()+"/invokejob/"+bus_id+"/"+bus_name+"/"+calid+"/"+calcode+"/"+paygroupid+"/"+natureemp+"/x";
break;
}
//System.out.println("url ::: "+url);
return url;
}
//////////////////////////code for generating reports ends 15-07-2021 //////////////////////////
}
