package in.co.srdt.unif.controllers.payslip;

import javax.servlet.http.HttpServletRequest;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payslip.Elementwisepayroll;
import in.co.srdt.unif.model.payslip.PayrollData;
import in.co.srdt.unif.model.payslip.PayrollSummery;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;
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

@Controller
public class PaySlipController {

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway applicationGateway;



	/*
	 * @RequestMapping("/payslip/{cal}") public String payslip(Model model,
	 * HttpServletRequest req, @PathVariable("cal") String calendar) {
	 * 
	 * 
	 * Login login= (Login) req.getSession().getAttribute("login"); double deduction
	 * = 0, earning = 0, netPay = 0; int i; String months;
	 * 
	 * PersonInformation personInformation = null; Elementwisepayroll[]
	 * elementwisepayroll = null; //
	 * /api/runpayroll/getelementwisepayrollprocesseddata/ String url =
	 * applicationGateway.getAppgateway_payroll()+
	 * "/api/runpayroll/getelementwisepayrollslipdata/" + calendar + "/" +
	 * login.getEmplid(); headers.setContentType(MediaType.APPLICATION_JSON);
	 * HttpHeaders head = new HttpHeaders(); HttpEntity<String> request = new
	 * HttpEntity<String>("", head); ResponseEntity<Elementwisepayroll[]> response =
	 * restTemplate.exchange(url, HttpMethod.GET, request,
	 * Elementwisepayroll[].class);
	 * 
	 * if (response.getStatusCode() == HttpStatus.OK) { elementwisepayroll =
	 * response.getBody(); } else { System.out.println("Request Failed");
	 * System.out.println(response.getStatusCode()); }
	 * 
	 * String urlprson =
	 * applicationGateway.getAppgateway()+"/PersonDetails/getPersonInfo/" +
	 * login.getEmplid(); ResponseEntity<PersonInformation> responsePerso =
	 * restTemplate.exchange(urlprson, HttpMethod.GET, request,
	 * PersonInformation.class);
	 * 
	 * if (responsePerso.getStatusCode() == HttpStatus.OK) { personInformation =
	 * responsePerso.getBody(); } else { System.out.println("Request Failed");
	 * System.out.println(responsePerso.getStatusCode()); }
	 * 
	 * for (i = 0; i < elementwisepayroll.length; i++) { if
	 * (elementwisepayroll[i].getElementtype().equals("er")) { earning = earning +
	 * elementwisepayroll[i].getPayamt(); } if
	 * (elementwisepayroll[i].getElementtype().equals("de")) { deduction = deduction
	 * + elementwisepayroll[i].getPayamt(); } }
	 * 
	 * netPay = earning - deduction; months =
	 * elementwisepayroll[0].getCalendarcode();
	 * 
	 * model.addAttribute("payslip", elementwisepayroll);
	 * model.addAttribute("earning", earning); model.addAttribute("deduction",
	 * deduction); model.addAttribute("netPay", netPay);
	 * model.addAttribute("personInfo", personInformation);
	 * model.addAttribute("months", months.substring(4));
	 * 
	 * return "fragments/payslip/payslip :: payslip"; }
	 */

	@RequestMapping("/payslip/{bus_id}/{calid}/{paygrpid}")
	public String getPayslip(@PathVariable("bus_id") String bus_id, @PathVariable("calid") String calid,
			@PathVariable("paygrpid") String paygrpid, HttpServletRequest request, Model model) {
		// System.out.println("BU:" + bus_id + " CAL:" + calid + " PYGRP:" + paygrpid);
		
		Login login = (Login) request.getSession().getAttribute("login");
		String urlpaygroup = applicationGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/paygroup";
		
		CommonLOV commonLOVs=null; 
		HttpEntity < String > paygrouprep = new HttpEntity < String > (headers);
		ResponseEntity<CommonLOV> responsePaygroup = restTemplate.exchange(urlpaygroup, HttpMethod.GET, paygrouprep,
				CommonLOV.class);
        String pay="";
		if (responsePaygroup.getStatusCode() == HttpStatus.OK) {
			commonLOVs = responsePaygroup.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePaygroup.getStatusCode());
		}
		
		
		
		
		
		PayrollSummery[] payrlsum = null;
		
		/* Variables for POOJA's CONTROLLER */


		double deduction = 0;
		double earning = 0;
		double contribution = 0;
		double net = 0;
		double n1=0;
		double n2=0;

		String months = "";
		String paiddays = "";
		/* Variables for POOJA's CONTROLLER */
		
		String url = applicationGateway.getAppgateway_payroll() + "/api/runpayroll/getelementwisepayrollslipsummery";
		headers.setContentType(MediaType.APPLICATION_JSON);

		

		String paysummPayLoad = "{" 
		+ "\"buid\"" + ":\"" + "" + "\"," 
		+ "\"calid\"" + ":\"" + calid + "\","
		+ "\"paygroupid\"" + ":\"" + commonLOVs.getDescription() + "\"," 
		+ "\"personnumber\"" + ":\"" + login.getEmplid() + "\","
		+ "\"natureofemp\"" + ":\"" + "" + "\""
		+ "" + "}";
		 
		HttpEntity<String> payrep = new HttpEntity<String>(paysummPayLoad, headers);
		
		ResponseEntity<PayrollSummery[]> response = restTemplate.exchange(url, HttpMethod.POST, payrep,
				PayrollSummery[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			payrlsum = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		for (int i = 0; i < payrlsum.length; i++) {
			for (PayrollData data : payrlsum[i].getPayrolldata()) {

				if (data.getElementtype().equals("er")) {
					earning = earning + Double.parseDouble(data.getPayamt());
					months = payrlsum[0].getPayrolldata().get(0).getCalendarcode();
					paiddays = payrlsum[0].getPayrolldata().get(0).getPaiddays();
				} else if (data.getElementtype().equals("de")) {
					deduction = deduction + Double.parseDouble(data.getPayamt());
				} else if (data.getElementtype().equals("st") && (!data.getAbbreviation().equals("Total Earning"))
						&& (!data.getAbbreviation().equals("Total Deduction"))
						&& (!data.getAbbreviation().equals("Net Pay"))) {
					contribution = contribution + Double.parseDouble(data.getPayamt());
				}else if (data.getElementtype().equals("st") && (data.getAbbreviation().equals("Net Pay"))
						) {
					n1 = n1 + Double.parseDouble(data.getPayamt());
				}
				
			}
		}

		//System.out.println(months.substring(4));
		//net = n1 - n2;

		model.addAttribute("payrlsum", payrlsum);
		model.addAttribute("paiddays", paiddays);
		model.addAttribute("months", months.substring(4,12));
		model.addAttribute("earning", earning);
		model.addAttribute("netmonthlysalary", n1);
		model.addAttribute("deduction", deduction);
		model.addAttribute("contribution", contribution);

		return "fragments/payslip/payslip :: paySlip";

	}

}
