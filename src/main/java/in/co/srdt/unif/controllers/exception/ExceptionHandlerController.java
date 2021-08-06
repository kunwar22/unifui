package in.co.srdt.unif.controllers.exception;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.thymeleaf.spring5.SpringTemplateEngine;

import in.co.srdt.unif.model.UserCredential;

@ControllerAdvice
@Scope("session")
public class ExceptionHandlerController {
	 @Autowired
	 SpringTemplateEngine thymeleafTemplateEngine;

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandlerGeneric(HttpSession session, SessionStatus status, Model model) {
		session.removeAttribute("roles");
    	session.removeAttribute("login");
    	session.invalidate();
    	status.setComplete();
    	
    	UserCredential userCredential=new UserCredential();
    	model.addAttribute("userCredential",userCredential);
    	model.addAttribute("flag",false);
    	return "redirect:/login";
    	
	}
		
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=NullPointerException.class)
	public String exceptionHandlerNull(HttpSession session, SessionStatus status, Model model) {
		session.removeAttribute("roles");
    	session.removeAttribute("login");
    	session.invalidate();
    	status.setComplete();
    	
    	UserCredential userCredential=new UserCredential();
    	model.addAttribute("userCredential",userCredential);
    	model.addAttribute("flag",false);
    	return "redirect:/login";
    	
	}
	
}
