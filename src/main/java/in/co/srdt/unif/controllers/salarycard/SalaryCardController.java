package in.co.srdt.unif.controllers.salarycard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.controllers.reports.GenerateExcelFiles;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.reports.PayrollSalaryCards;
import in.co.srdt.unif.model.salarycard.PayrollSalCardElements;
import in.co.srdt.unif.model.salarycard.PayrollSalaryCard;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
public class SalaryCardController {

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appGateway;
	private final GenerateExcelFiles genexfiles = new GenerateExcelFiles();


	@RequestMapping("/salarycard/{calcodefrom}/{calcodeto}")
	public String sal(@PathVariable("calcodefrom") String calcodefrom, @PathVariable("calcodeto") String calcodeto, 
			Model model, HttpServletRequest req, HttpServletResponse response) {

//		double earPrev = 0;
//		double earCur = 0;
//		double earTot = 0;
//		double dedPrev = 0;
//		double dedCur = 0;
//		double dedTot = 0;
//		double empPrev = 0;
//		double empCur = 0;
//		double empTot = 0;
//		double perPrev = 0;
//		double perCur = 0;
//		double perTot = 0;
//		double netPrev = 0;
//		double netCur = 0;
//		double netTot = 0;

		headers.setContentType(MediaType.APPLICATION_JSON);
		Login login = (Login) req.getSession().getAttribute("login");

		PayrollSalaryCards[] pyrlsalcards = null;
		CommonLOV commonLOV = null;
		String businessunit = "", paygroup = "";

		String buurl = appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/" + login.getEmplid() + "/businessunit";
		
		String pgurl = appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/" + login.getEmplid() + "/paygroup";
		
		HttpEntity<String> bupgrequest = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV> buresponse = restTemplate.exchange(buurl, HttpMethod.GET, bupgrequest, CommonLOV.class);

		ResponseEntity<CommonLOV> pgresponse = restTemplate.exchange(pgurl, HttpMethod.GET, bupgrequest, CommonLOV.class);
		
		if (buresponse.getStatusCode() == HttpStatus.OK) {
			commonLOV = buresponse.getBody();
			businessunit = commonLOV.getDescription();
		} else {
			System.out.println("Request Failed");
			System.out.println(buresponse.getStatusCode());
		}
		
		if (pgresponse.getStatusCode() == HttpStatus.OK) {
			commonLOV = pgresponse.getBody();
			paygroup = commonLOV.getDescription();
		} else {
			System.out.println("Request Failed");
			System.out.println(pgresponse.getStatusCode());
		}
				
		
		
//		String paysummPayLoad = "{" + "\"buid\"" + ":\"" + businessunit + "\"," + "\"calcode\"" + ":\"" + calcode + "\","
//				+ "\"paygroupid\"" + ":\"" + paygroup + "\"," + "\"personnumber\"" + ":\"" + login.getEmplid() + "\"" + "}";
//		
//
//		System.out.println("paysummPayLoad ::  " + paysummPayLoad);
//		// String description=forBusinessunit.getDescription();

		String url =appGateway.getAppgateway_payroll() + "/api/mod/getsalarycard/"+calcodefrom+"/"+calcodeto+"/"+businessunit+"/"+login.getEmplid()+"/"+paygroup;
//		String url = appGateway.getAppgateway_payroll() + "/api/mod/getsalarycard/" + calcode + "/"
//				+ commonLOV.getDescription() + "/" + login.getEmplid();
		/*
		 * String paysummPayLoad = "{" + "\"buid\"" + ":\"" + "" + "\"," + "\"calcode\""
		 * + ":\"" + calcode + "\"," + "\"paygroupid\""+ ":\"" + ""+ "\"," +
		 * "\"personnumber\"" + ":\"" + login.getEmplid() + "\"" + "}";
		 */

		//System.out.println("URL  ::" + url);

		 HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<PayrollSalaryCards[]> response1 = restTemplate.exchange(url, HttpMethod.GET, request,
				PayrollSalaryCards[].class);

		//System.out.println(paysummPayLoad);

		if (response1.getStatusCode() == HttpStatus.OK) {
			pyrlsalcards = response1.getBody();
			// System.out.println("data::" + payrollSalaryCards.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response1.getStatusCode());
		}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=LMRC_SALARY_CARD_" + calcodefrom+"_to_"+calcodeto+".xlsx");
		try {
			genexfiles.WriteEmployeeSalaryCard(pyrlsalcards,calcodefrom,calcodeto,response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}

		
		
//		for (int i = 0; i < pyrlsalcards.length; i++) {
//
//			for (PayrollSalCardElements data : pyrlsalcards[i].getElem()) {
//				if (data.getElement_type().equals("er")) {
//					earPrev = earPrev + Double.parseDouble(data.getPrev_amt());
//					earCur = earCur + Double.parseDouble(data.getCur_amt());
//					earTot = earTot + Double.parseDouble(data.getTot_amt());
//				} else if (data.getElement_type().equals("de")) {
//					// System.out.println("ELEMENT NAME ::: "+data.getElement_name()+"\t Previous
//					// Amount ::: "+data.getPrev_amt()+"\tCurrent Amount ::: "+data.getCur_amt());
//					dedPrev = dedPrev + Double.parseDouble(data.getPrev_amt());
//					dedCur = dedCur + Double.parseDouble(data.getCur_amt());
//					dedTot = dedTot + Double.parseDouble(data.getTot_amt());
//				} else if (data.getElement_type().equals("st") && (!data.getPrev_amt().equals("0.0"))
//						&& (!data.getCur_amt().equals("0.0")) && (!data.getTot_amt().equals("0.0"))
//
//						&& (!data.getElement_name().equals("Total Earning"))
//						&& (!data.getElement_name().equals("Total Deduction"))
//						&& (!data.getElement_name().equals("Net Pay"))) {
//					
//					empPrev = empPrev + Double.parseDouble(data.getPrev_amt());
//					empCur = empCur + Double.parseDouble(data.getCur_amt());
//					empTot = empTot + Double.parseDouble(data.getTot_amt());
//					
//				} else if (data.getElement_type().equals("sp")) {
//					perPrev = perPrev + Double.parseDouble(data.getPrev_amt());
//					perCur = perCur + Double.parseDouble(data.getCur_amt());
//					perTot = perTot + Double.parseDouble(data.getTot_amt());
//				}
//			}
//		}
//
//		netPrev = earPrev - dedPrev;
//		netCur = earCur - dedCur;
//		netTot = earTot - dedTot;
//
//		model.addAttribute("sum_earn_cur_pay", earCur);
//		model.addAttribute("sum_earn_pre_pay", earPrev);
//		model.addAttribute("sum_earn_tot_pay", earTot);
//		model.addAttribute("sum_ded_cur_pay", dedCur);
//		model.addAttribute("sum_ded_pre_pay", dedPrev);
//		model.addAttribute("sum_ded_tot_pay", dedTot);
//		model.addAttribute("sum_emp_cur_pay", empCur);
//		model.addAttribute("sum_emp_pre_pay", empPrev);
//		model.addAttribute("sum_emp_tot_pay", empTot);
//		model.addAttribute("sum_per_cur_pay", perCur);
//		model.addAttribute("sum_per_pre_pay", perPrev);
//		model.addAttribute("sum_per_tot_pay", perTot);
//		model.addAttribute("sum_netPrev_pay", netPrev);
//		model.addAttribute("sum_netCur_pay", netCur);
//		model.addAttribute("sum_netTot_pay", netTot);
//
//		model.addAttribute("salarycard", payrollSalaryCards);
//
//		return "fragments/salarycard/salaryCard :: salaryCard";

		return null;
	}


}
