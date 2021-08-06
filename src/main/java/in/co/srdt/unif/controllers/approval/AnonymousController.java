package in.co.srdt.unif.controllers.approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/anonymous")
public class AnonymousController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	AnonymousController(){
		
	}
	
	AnonymousController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	@RequestMapping("/manageAnonymous")
	public String manageDepartment(Model model) {

		
		return "fragments/approval/createAnonymous :: createAnony";
	}
	
	

}
