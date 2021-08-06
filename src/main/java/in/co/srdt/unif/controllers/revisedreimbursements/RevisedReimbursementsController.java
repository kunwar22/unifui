package in.co.srdt.unif.controllers.revisedreimbursements;

import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.FinancialYearLOV;
import in.co.srdt.unif.model.revisedreimbursements.RemTemplate;
import in.co.srdt.unif.model.revisedreimbursements.RemTemplateWrapper;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/revisedreimbursements")
public class RevisedReimbursementsController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	SmartValidator validator;

	private RemTemplate[] remt;
	private String reimname[] = { "", "TELE", "TRANS", "", "", "ENTE", "", "CDA", "", "", "", "", "TADK" };

	@RequestMapping("/remtemplate/{rid}")
	public String remtemplate(Model model, @PathVariable("rid") String RID) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		BUsearchresult[] buobj = null;
		String urlbu = appgateway.getAppgateway() + "/BusinessUnit/BusinessUnitSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"," + "\"status\"" + ":\"\"" + "}";
		HttpEntity<String> request1 = new HttpEntity<String>(payload, headers);
		ResponseEntity<BUsearchresult[]> response = restTemplate.exchange(urlbu, HttpMethod.POST, request1,
				BUsearchresult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			buobj = response.getBody();
		}
		model.addAttribute("bures", buobj);

		FinancialYearLOV[] fyearlov = null;
		String urlfyearlov = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/getfinancialyeardata";
		ResponseEntity<FinancialYearLOV[]> response31 = restTemplate.exchange(urlfyearlov, HttpMethod.GET, request,
				FinancialYearLOV[].class);
		if (response31.getStatusCode() == HttpStatus.OK) {
			fyearlov = response31.getBody();
		}
		model.addAttribute("fyearr", fyearlov);

		model.addAttribute("remid", RID);
		return "fragments/revisedreimbursements/remteplate :: remteplate";
	}

	@ResponseBody
	@RequestMapping(value = "/searchremtemplempl/{reim}/{bu}/{fyear}", method = RequestMethod.GET)
	public RemTemplate[] getemtemplempl(@PathVariable("reim") String reim, @PathVariable("bu") String bu,
			@PathVariable("fyear") String fyear) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		RemTemplate[] res = null;
		String urlincr = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/getdata/" + reim + "/" + bu + "/"
				+ fyear;
	     System.out.println("url2:::::"+urlincr);
		// System.out.println("URL :: "+urlasgn);
		
		HttpEntity<String> req = new HttpEntity<String>(headers);
		ResponseEntity<RemTemplate[]> response = restTemplate.exchange(urlincr, HttpMethod.GET, req,
				RemTemplate[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(1024);
	}

	@ResponseBody
	@RequestMapping(value = "/saveremtempl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveautoincr(HttpServletRequest req, RemTemplateWrapper remTemplateWrapper) {
		Login login = (Login) req.getSession().getAttribute("login");
		String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/savetempdata";
		SingleResponseModel res = null;

		for (int x = 0; x < remTemplateWrapper.getRemTemplates().size(); x++) {
			remTemplateWrapper.getRemTemplates().get(x)
					.setReimname(reimname[(int) remTemplateWrapper.getRemTemplates().get(x).getReimid()]);
			remTemplateWrapper.getRemTemplates().get(x).setCreatedby(login.getEmplid());
			remTemplateWrapper.getRemTemplates().get(x).setUpdatedby(login.getEmplid());
		}

		System.out.println("rj:::"+remTemplateWrapper.toString());
		HttpEntity<List<RemTemplate>> request = new HttpEntity<List<RemTemplate>>(remTemplateWrapper.getRemTemplates(),
				headers);
		// System.out.println(request.getBody());
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
		}

		// System.out.println(res.toString());
		return res.getMessage();
	}

	@RequestMapping("/generateReimbValue")
	public String generateReimbValue() {
		return "fragments/revisedreimbursements/generatereimbvalue :: generatereimbvalue";
	}

	@RequestMapping("/approveReimbValue/{apprlevel}")
	public String approveReimbValue(@PathVariable("apprlevel") String apprlvl, Model model) {
		model.addAttribute("apprlvl", apprlvl);
		return "fragments/revisedreimbursements/approvereimbvalue :: approvereimbvalue";
	}

	@RequestMapping("/managegeneratetab/{actionmode}")
	public String managegeneratetab(Model model, @PathVariable("actionmode") String actionmode) {

		//System.out.println("ACT 01 :"+actionmode);
		model.addAttribute("actmode", actionmode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		BUsearchresult[] buobj = null;
		String urlbu = appgateway.getAppgateway() + "/BusinessUnit/BusinessUnitSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"," + "\"status\"" + ":\"\"" + "}";
		HttpEntity<String> request1 = new HttpEntity<String>(payload, headers);
		ResponseEntity<BUsearchresult[]> response = restTemplate.exchange(urlbu, HttpMethod.POST, request1,
				BUsearchresult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			buobj = response.getBody();
		}
		model.addAttribute("bures", buobj);
		return "fragments/revisedreimbursements/generatetab :: generatetab";
	}

	@ResponseBody
	@RequestMapping(value = "/searchgnrtempl/{reim}/{bu}/{monthh}/{yearr}/{actmode}", method = RequestMethod.GET)
	public RemTemplate[] getgnrtempl(@PathVariable("reim") String reim, @PathVariable("bu") String bu,
			@PathVariable("monthh") String monthh, @PathVariable("yearr") String yearr,
			@PathVariable("actmode") String actmode) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		RemTemplate[] res = null;
		String urlincr = "";
		// System.out.println("ACTMODE : "+actmode);
		if (actmode.equals("GEN")) {
			urlincr = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/getdatafromtemplate/" + reim + "/" + bu
					+ "/" + yearr + "/" + monthh;
		} else if (actmode.equals("APPR")) {
			urlincr = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/getdataforapprovel/" + reim + "/" + bu + "/"
					+ yearr + "/" + monthh;
		}
		System.out.println("url1:::::"+urlincr);
		HttpEntity<String> req = new HttpEntity<String>(headers);
		ResponseEntity<RemTemplate[]> response = restTemplate.exchange(urlincr, HttpMethod.GET, req,
				RemTemplate[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			remt = res;
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		/*
		 * for (int x=0;x<res.length;x++){
		 * System.out.println("IND :: "+x+" "+res[x].toString()); }
		 */
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/savegnrtvalue/{mode}", method = RequestMethod.POST)
	public String savegnrtvalue(HttpServletRequest req,RemTemplateWrapper remTemplateWrapper1, @PathVariable("mode") String mode) {

		Login login = (Login) req.getSession().getAttribute("login");
		String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/savedataOrupdate";
		if (mode.equals("APPR1")) {
			for (int y = 0; y < remt.length; y++) {
				remt[y].setDescription(remTemplateWrapper1.getRemTemplates().get(y).getDescription());
				remt[y].setAppr1status("Approved");
				remt[y].setCreatedby( login.getEmplid());
				remt[y].setUpdatedby( login.getEmplid());
				remt[y].setReimbursementamount(remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount());
				remt[y].setArrearamt(remTemplateWrapper1.getRemTemplates().get(y).getArrearamt());
				remt[y].setVaamt(remTemplateWrapper1.getRemTemplates().get(y).getVaamt());
				remt[y].setVaarramt(remTemplateWrapper1.getRemTemplates().get(y).getVaarramt());
				remt[y].setRecoveryamt(remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				remt[y].setTotalamt((remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount()
						+ remTemplateWrapper1.getRemTemplates().get(y).getArrearamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaarramt())
						- remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				// System.out.println("DEX "+y+" ::"+remt[y].toString());
			}
		}
		// added by rajat on 10-04-2021 start
		else if (mode.equals("SavedApprover1")) {
			for (int y = 0; y < remt.length; y++) {
				remt[y].setDescription(remTemplateWrapper1.getRemTemplates().get(y).getDescription());
				//remt[y].setAppr1status("Submitted");
				remt[y].setCreatedby( login.getEmplid());
				remt[y].setUpdatedby( login.getEmplid());
				remt[y].setReimbursementamount(remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount());
				remt[y].setArrearamt(remTemplateWrapper1.getRemTemplates().get(y).getArrearamt());
				remt[y].setVaamt(remTemplateWrapper1.getRemTemplates().get(y).getVaamt());
				remt[y].setVaarramt(remTemplateWrapper1.getRemTemplates().get(y).getVaarramt());
				remt[y].setRecoveryamt(remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				remt[y].setTotalamt((remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount()
						+ remTemplateWrapper1.getRemTemplates().get(y).getArrearamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaarramt())
						- remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				// System.out.println("DEX "+y+" ::"+remt[y].toString());
			}
		}
		
		else if (mode.equals("SavedApprover2")) {
			for (int y = 0; y < remt.length; y++) {
				remt[y].setDescription(remTemplateWrapper1.getRemTemplates().get(y).getDescription());
				remt[y].setAppr1status("Approved");
				remt[y].setCreatedby( login.getEmplid());
				remt[y].setUpdatedby( login.getEmplid());
				remt[y].setReimbursementamount(remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount());
				remt[y].setArrearamt(remTemplateWrapper1.getRemTemplates().get(y).getArrearamt());
				remt[y].setVaamt(remTemplateWrapper1.getRemTemplates().get(y).getVaamt());
				remt[y].setVaarramt(remTemplateWrapper1.getRemTemplates().get(y).getVaarramt());
				remt[y].setRecoveryamt(remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				remt[y].setTotalamt((remTemplateWrapper1.getRemTemplates().get(y).getReimbursementamount()
						+ remTemplateWrapper1.getRemTemplates().get(y).getArrearamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaamt()
						+ remTemplateWrapper1.getRemTemplates().get(y).getVaarramt())
						- remTemplateWrapper1.getRemTemplates().get(y).getRecoveryamt());
				// System.out.println("DEX "+y+" ::"+remt[y].toString());
			}
		}
		
		// added by rajat on 10-04-2021 end 
		else if (mode.equals("APPR2")) {
			for (int x = 0; x < remt.length; x++) {
				remt[x].setDescription(remTemplateWrapper1.getRemTemplates().get(x).getDescription());
				remt[x].setStatus("Approved");
				remt[x].setCreatedby( login.getEmplid());
				remt[x].setUpdatedby( login.getEmplid());
				remt[x].setReimbursementamount(remTemplateWrapper1.getRemTemplates().get(x).getReimbursementamount());
				remt[x].setArrearamt(remTemplateWrapper1.getRemTemplates().get(x).getArrearamt());
				remt[x].setVaamt(remTemplateWrapper1.getRemTemplates().get(x).getVaamt());
				remt[x].setVaarramt(remTemplateWrapper1.getRemTemplates().get(x).getVaarramt());
				remt[x].setRecoveryamt(remTemplateWrapper1.getRemTemplates().get(x).getRecoveryamt());
				remt[x].setTotalamt((remTemplateWrapper1.getRemTemplates().get(x).getReimbursementamount()
						+ remTemplateWrapper1.getRemTemplates().get(x).getArrearamt()
						+ remTemplateWrapper1.getRemTemplates().get(x).getVaamt()
						+ remTemplateWrapper1.getRemTemplates().get(x).getVaarramt())
						- remTemplateWrapper1.getRemTemplates().get(x).getRecoveryamt());
				// System.out.println("DEX "+x+" ::"+remt[x].toString());
			}
		} else {
			for (int z = 0; z < remTemplateWrapper1.getRemTemplates().size(); z++) {
				remt[z].setCreatedby( login.getEmplid());
				remt[z].setUpdatedby( login.getEmplid());
				
				remt[z].setDescription(remTemplateWrapper1.getRemTemplates().get(z).getDescription());
				remt[z].setReimbursementamount(remTemplateWrapper1.getRemTemplates().get(z).getReimbursementamount());
				remt[z].setArrearamt(remTemplateWrapper1.getRemTemplates().get(z).getArrearamt());
				remt[z].setVaamt(remTemplateWrapper1.getRemTemplates().get(z).getVaamt());
				remt[z].setRecoveryamt(remTemplateWrapper1.getRemTemplates().get(z).getRecoveryamt());
				remt[z].setStartdate(remTemplateWrapper1.getRemTemplates().get(z).getStartdate());
				remt[z].setEnddate(remTemplateWrapper1.getRemTemplates().get(z).getEnddate());
				remt[z].setVaarramt(remTemplateWrapper1.getRemTemplates().get(z).getVaarramt());
				remt[z].setStatus(mode);
				remt[z].setTotalamt((remTemplateWrapper1.getRemTemplates().get(z).getReimbursementamount()
						+ remTemplateWrapper1.getRemTemplates().get(z).getArrearamt()
						+ remTemplateWrapper1.getRemTemplates().get(z).getVaamt()
						+ remTemplateWrapper1.getRemTemplates().get(z).getVaarramt())
						- remTemplateWrapper1.getRemTemplates().get(z).getRecoveryamt());
				System.out.println("DEX "+z+" ::"+remt[z].toString());
			}
		}

		SingleResponseModel res = null;

		HttpEntity<RemTemplate[]> request = new HttpEntity<RemTemplate[]>(remt, headers);
		
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
		}

		return res.getMessage();
	}

	@RequestMapping("/managehistorytab")
	public String managehistorytab(Model model) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		BUsearchresult[] buobj = null;
		String urlbu = appgateway.getAppgateway() + "/BusinessUnit/BusinessUnitSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"," + "\"status\"" + ":\"\"" + "}";
		HttpEntity<String> request1 = new HttpEntity<String>(payload, headers);
		ResponseEntity<BUsearchresult[]> response = restTemplate.exchange(urlbu, HttpMethod.POST, request1,
				BUsearchresult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			buobj = response.getBody();
		}
		model.addAttribute("bures", buobj);

		return "fragments/revisedreimbursements/historytab :: historytab";
	}

	@ResponseBody
	@RequestMapping(value = "/searchgnrthstry/{remid}/{bu}/{strt}/{endd}", method = RequestMethod.GET)
	public RemTemplate[] searchgnrthstry(@PathVariable("remid") String remid, @PathVariable("bu") String bu,
			@PathVariable("strt") String strt, @PathVariable("endd") String endd) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		RemTemplate[] res = null;
		String urlhstry = appgateway.getAppgatewaypyrl_sandhya() + "/api/rembrs/getRemFinalAmtByBusinessUnit";
		String payload = "{\"businessunitid\":" + bu + ",\"enddate\":\"" + endd + "\",\"remid\":" + remid
				+ ",\"startdate\":\"" + strt + "\"}";
		 System.out.println(payload);
		HttpEntity<String> request1 = new HttpEntity<String>(payload, headers);
		ResponseEntity<RemTemplate[]> response = restTemplate.exchange(urlhstry, HttpMethod.POST, request1,
				RemTemplate[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}

}
