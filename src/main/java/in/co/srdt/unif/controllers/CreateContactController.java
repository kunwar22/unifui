package in.co.srdt.unif.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprisesetup")
public class CreateContactController {
	
	
	
	@RequestMapping("/CreateContactDetails/manageCreateContact")
	public String createContact(Model model) {
		
		//System.out.println("hello create User");
		
		return "fragments/createContact :: createContact";
	}

}
