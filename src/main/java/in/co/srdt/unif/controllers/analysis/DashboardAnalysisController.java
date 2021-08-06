package in.co.srdt.unif.controllers.analysis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import in.co.srdt.unif.model.user.absence.CommonDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.analysis.ClaimedReimbursementData;
import in.co.srdt.unif.model.analysis.HolidayData;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.lwp.CreateLwp;
import in.co.srdt.unif.model.lwp.CreateLwpWrapper;
import in.co.srdt.unif.model.rembcontingent.AdhocSearchModel;
import in.co.srdt.unif.model.search.PersonRecordSearch;
import in.co.srdt.unif.utils.ApplicationGateway;

import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/mydashboard")
public class DashboardAnalysisController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders httpHeaders;
	
	@Autowired
	private ApplicationGateway apiGateway;
	
	DashboardAnalysisController(){
		
	}
	
	DashboardAnalysisController(HttpHeaders httpHeaders,RestTemplate restTemplate){
		this.httpHeaders=httpHeaders;
		this.restTemplate=restTemplate;
	}


	@RequestMapping("/loaddashboardfrag")
	public String loaddashboardfrag()
	{
		return "fragments/analytics/fragdashboard :: fragdashboard";
	}
	
	@RequestMapping("/claimedreimbursement")
	public String claimreimbDashboard(Model model,HttpServletRequest req)
	{
		return "fragments/analytics/selfserviceanalytics :: selfserviceanalytics";
	}
	
	@RequestMapping("/managereimbursement")
	public String managereimbDashboard(Model model,HttpServletRequest req)
	{
		return "fragments/analytics/selfserviceanalytics :: managereimbursement";
	}
	
	@RequestMapping("/holiday")
	public String holidayDashboard(Model model,HttpServletRequest req)
	{
		return "fragments/analytics/selfserviceanalytics :: holidayanalytics";
	}
	
	@ResponseBody
	@RequestMapping(value="/getHolidayData", method = RequestMethod.GET)
	public HolidayData[] getHolidayDataDashboard(Model model,HttpServletRequest req)
	{
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(httpHeaders);

		Login login= (Login) req.getSession().getAttribute("login");
		CommonDescription wrkloc= null;
		String urlpygrp= apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/WorkLocation";
		ResponseEntity<CommonDescription> pygrpresponse = restTemplate.exchange(urlpygrp, HttpMethod.GET, request, CommonDescription.class);
		if(pygrpresponse.getStatusCode() == HttpStatus.OK) {
			wrkloc = pygrpresponse.getBody();
		}
		HolidayData[] hd=null;
    	String url = apiGateway.getAppgateway()+"/DashboardCoreHR/getHolidayLocationList/"+wrkloc.getDescription();
		ResponseEntity<HolidayData[]> response = restTemplate.exchange(url,HttpMethod.GET, request ,HolidayData[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			hd = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return hd;
	}
	
	@ResponseBody
	@RequestMapping(value="/getClaimedReimb", method = RequestMethod.GET)
	public ClaimedReimbursementData[] getClaimedReimbDashboard(Model model,HttpServletRequest req)
	{
		ClaimedReimbursementData[] hd=null;
		Login login = (Login) req.getSession().getAttribute("login");
    	String url = apiGateway.getAppgateway()+"/DashboardCoreHR/getClimedReimStatus/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		ResponseEntity<ClaimedReimbursementData[]> response = restTemplate.exchange(url,HttpMethod.GET, request ,ClaimedReimbursementData[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			hd = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return hd;
	}
	
	@ResponseBody
	@RequestMapping(value="/getManageReimb", method = RequestMethod.GET)
	public ClaimedReimbursementData[] getManageReimbDashboard(Model model,HttpServletRequest req)
	{
		ClaimedReimbursementData[] hd=null;
		Login login = (Login) req.getSession().getAttribute("login");
    	String url = apiGateway.getAppgateway()+"/DashboardCoreHR/getManagerApprovelStatus/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		ResponseEntity<ClaimedReimbursementData[]> response = restTemplate.exchange(url,HttpMethod.GET, request ,ClaimedReimbursementData[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			hd = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return hd;
	}
}