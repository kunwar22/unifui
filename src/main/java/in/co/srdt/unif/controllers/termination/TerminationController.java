package in.co.srdt.unif.controllers.termination;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import in.co.srdt.unif.controllers.persondetailsedit.EmploymentDetailsEditController;
import in.co.srdt.unif.model.employmentmanagement.EmployementInfoManageMaster;
import in.co.srdt.unif.model.employmentmanagement.JobData;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.termination.Termination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.user.corehr.Resignation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@SessionAttributes("pnum")
@RequestMapping("/termination")
public class TerminationController {


	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	SmartValidator validator;

	/*@Autowired(required = true)
	private EmploymentDetailsEditController empedit;
	*/
	
	
	@RequestMapping("/resignation")
	public String resignation(Termination termination, HttpServletRequest req, Model model,@ModelAttribute("pnum")String pnum,String resstatus) {

		model.addAttribute("result",resstatus);
		//model.addAttribute("bindingResult",bindingResult);
		//model.addAttribute("term",termination);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		Login login = (Login)req.getSession().getAttribute("login");

		CommonLOV[] action=null;
		String urlaction = appgateway.getAppgateway() + "/Action/loadResignationAction";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlaction, HttpMethod.GET, request, CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			action = response1.getBody();
		}

		//EmployementInfoManageMaster emplMaster=null;
		//String urlgetempldetails="";
		String urlgetempldetails = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getEmpInfoForTerminationByPersonNo/"+pnum;
		System.out.println("EMPLOYEE DETAILS URL :: "+urlgetempldetails);
		ResponseEntity<Termination> response = restTemplate.exchange(urlgetempldetails, HttpMethod.GET, request, Termination.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			termination = response.getBody();
		}

		model.addAttribute("term",termination);
		model.addAttribute("action", action);
		return "fragments/termination/termination :: termination";
	}


	@RequestMapping(value= "/savetermination", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String savetermination(Termination termination, Model model, HttpServletRequest req, String tab, @ModelAttribute("pnum")String pnum, BindingResult bindingResult)
	{
		//System.out.println("jobdata::"+jobdata.toString());
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Termination> request = new HttpEntity<Termination>(termination, headers);
		String url=appgateway.getAppgateway()+"/Employement/Manage/updateEndOfJobData";

		Login login = (Login)req.getSession().getAttribute("login");
		termination.setUpdatedby(login.getEmplid());

		System.out.println("Correct Data :: "+termination.toString());
		validator.validate(termination,bindingResult);
		System.out.println("ALL TERMINATION ERRORS :: "+bindingResult.getAllErrors().toString());
		if (bindingResult.hasErrors()){
			CommonLOV[] action=null;
			String urlaction = appgateway.getAppgateway() + "/Action/loadResignationAction";
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlaction, HttpMethod.GET, request, CommonLOV[].class);
			if (response1.getStatusCode() == HttpStatus.OK) {
				action = response1.getBody();
			}
			model.addAttribute("bindingResult",bindingResult);
			model.addAttribute("term",termination);
			model.addAttribute("action", action);
			return "fragments/termination/termination :: termination";
		}
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			//System.out.println("Correct Data :: "+res.toString());
		}

		return resignation(new Termination(), req, model,pnum, res.getStatus());
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}

}
