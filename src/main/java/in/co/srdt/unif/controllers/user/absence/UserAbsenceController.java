package in.co.srdt.unif.controllers.user.absence;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.co.srdt.unif.enums.Half_Full_Day;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.user.absence.AbsenceLOV;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.model.user.absence.LeaveData;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/user")
public class UserAbsenceController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;

	@Autowired(required=true)
	private EligibilityABS eligibilityabs;
	

	@RequestMapping("/addabsence")
	public String addabsence(Model model, HttpServletRequest req) {
		
		//System.out.println("Hello Add Absence");
		Login login = (Login) req.getSession().getAttribute("login");
		String PID=login.getEmplid();
		
		List<AbsenceLOV> absencetype=  eligibilityabs.getEligibleAbsenceTypes(PID);
		
		//List<CommonLOV> absencetype=  new Eligibility().getEligibleAbsenceTypes(PID);
		
		model.addAttribute("absencetype",absencetype);
		
		//System.out.println("Eligible Leave Types :: "+absencetype);
		//eligibility.gettemp();
		model.addAttribute("halffullday", Half_Full_Day.values());
		
		return "fragments/user/absence/addabsence :: addabsence";
	}
	
	
	
	

	
	
	
	@ResponseBody
	@RequestMapping("/dateValidator")
	public CommonDescription validateDate(HttpServletRequest requestFromDT) {
		
		String week = requestFromDT.getParameter("week");
		String day = requestFromDT.getParameter("day");
		String rep = requestFromDT.getParameter("rep");
		String half = requestFromDT.getParameter("half");
		String abstypeid = requestFromDT.getParameter("abstype");
		String year = requestFromDT.getParameter("year");
		String date = requestFromDT.getParameter("date");
		//System.out.println("WEEK "+week+", DAY "+day+", Half "+half+", Rep "+rep+", Abs Type Id "+abstypeid+" Year "+year+" Date is "+date);
		
		CommonDescription message=eligibilityabs.getDateValidity(day,week,half,rep,abstypeid,year,date);
		//System.out.println(message);
		return message;

	}	
	
	@ResponseBody
	@RequestMapping("/leaveCalculator")
	public CommonDescription leaveCalculator(@RequestBody String json) throws Exception {
		
		String decode_leave = java.net.URLDecoder.decode(json,"UTF-8");
		ObjectMapper mapper=new ObjectMapper();
		List<LeaveData> leave_listData=mapper.readValue(decode_leave, new TypeReference<List<LeaveData>>(){});

		CommonDescription message=eligibilityabs.getCalculatedLeaves(leave_listData);
		//System.out.println("\nTotal Leaves "+message.getDescription());
		return message;

	}	
	
}
