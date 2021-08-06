package in.co.srdt.unif.controllers.team;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.team.ReporteeList;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;


	
	@RequestMapping(value="/manageteam",method =RequestMethod.GET)
	public String manageteam(Model model,HttpServletRequest request) {
		
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 Login login = (Login) request.getSession().getAttribute("login");
		 
		 String reporteeurl =appgateway.getAppgateway()+"/PersonManagement/getReporteeList/"+login.getEmplid();
		 
		 ReporteeList[] rlist = null;
		 HttpEntity<String> req =  new HttpEntity<>(headers); 
		 
		 ResponseEntity<ReporteeList[]> resp_reportees = restTemplate.exchange(reporteeurl, HttpMethod.GET,req,ReporteeList[].class);
		 
		 if(resp_reportees.getStatusCode() == HttpStatus.OK)
		 {
			 rlist = resp_reportees.getBody();
			 //System.out.println("reportee_list ===========> "+rlist);
		 }
		 model.addAttribute("reportees", rlist);
		 return "fragments/team/manageTeam :: manageTeam";
	}
}
