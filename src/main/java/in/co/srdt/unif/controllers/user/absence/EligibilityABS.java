package in.co.srdt.unif.controllers.user.absence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.Position;
import in.co.srdt.unif.model.user.absence.AbsenceLOV;
import in.co.srdt.unif.model.user.absence.AllAbsenceType;
import in.co.srdt.unif.model.user.absence.AllEligibilityCriteria;
import in.co.srdt.unif.model.user.absence.AllHolidaysOfYear;
import in.co.srdt.unif.model.user.absence.AllWeeklyHolidays;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.model.user.absence.LeaveData;
import in.co.srdt.unif.model.user.absence.PersonEligibility;
import in.co.srdt.unif.utils.ApplicationGateway;

@Service
public class EligibilityABS {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	private String rep_period;
	private String abs_id;
	
	public List<AbsenceLOV> getEligibleAbsenceTypes(String pID) {
		
		//System.out.println("Person ID :: "+pID);
		
		List<AbsenceLOV> list=new ArrayList<>();
		
		/*CommonLOV a=new CommonLOV(1,"Casual Leave");
		CommonLOV b=new CommonLOV(2,"Half Day");
		list.add(a);
		list.add(b);*/
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		AllAbsenceType[] allabsence=null;
		
		String urlgetallabs = appgateway.getAppgatewayabs()+"/PersonEligibility/getAllAbsenceType";
		ResponseEntity<AllAbsenceType[]> response = restTemplate.exchange(urlgetallabs, HttpMethod.GET, request, AllAbsenceType[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			allabsence = response.getBody();
		}
		
		//System.out.println("Total Number of Absence Type:: "+allabsence.length);
		for(int z=0;z<allabsence.length;z++) {
		//System.out.println(allabsence[z].toString());
		}
		outer:
		for(int i=0;i<allabsence.length;i++) {
			
			AllEligibilityCriteria[] allcriteria=null;
			
			String urlgetallcriteria=appgateway.getAppgatewayabs()+"/PersonEligibility/getPersonEligibilityById/"+allabsence[i].getEligibilityid();
			ResponseEntity<AllEligibilityCriteria[]> response1 = restTemplate.exchange(urlgetallcriteria, HttpMethod.GET, request,AllEligibilityCriteria[].class );
			if (response1.getStatusCode() == HttpStatus.OK) {
				allcriteria = response1.getBody();
			}
			
			//System.out.println("\nTotal Number of Absence Criteria for "+allabsence[i].getAbsencetypename()+" :: "+allcriteria.length);
			
			inner:
			for(int j=0;j<allcriteria.length;j++) {
			//	 System.out.println(allcriteria[j].toString());
				 PersonEligibility personEligibility=null;
				 
				 String urlgetpersoneligibility=appgateway.getAppgateway()+"AbsencePersonData/getPersonDataByGivenParameter/"+pID+"/"+allcriteria[j].getEligibilitycriterianame();
					ResponseEntity<PersonEligibility> response2 = restTemplate.exchange(urlgetpersoneligibility, HttpMethod.GET, request,PersonEligibility.class );
					if (response2.getStatusCode() == HttpStatus.OK) {
						personEligibility = response2.getBody();
					}
					//System.out.println(personEligibility.toString());
					//System.out.println(allcriteria[j].getCriteriavalueid());
					//System.out.println(personEligibility.getDescription());
					//System.out.println("Checking Leave number:: "+(i+1)+"  Criteria Number :: "+(j+1));
					
					
					if(allcriteria[j].getCriteriavalueid().equals(personEligibility.getDescription())) {
						AbsenceLOV temp=new AbsenceLOV(allabsence[i].getAbsencetypeid(),allabsence[i].getAbsencetypename(),allabsence[i].getRepeatingperiodid());
						list.add(temp);
						//System.out.println("Criteria Match Successful");
						//break;
					}
					else {
						System.out.println("Criteria Mismatch");
						//break inner;
					}
					
					/* String[] res = allcriteria[j].getCriteriavaluename().split("[,]", 0);
				       for(String myStr: res) {
				    	   if(myStr.equals(personEligibility.getDescription())) {
								AbsenceLOV temp=new AbsenceLOV(allabsence[i].getAbsencetypeid(),allabsence[i].getAbsencetypename(),allabsence[i].getRepeatingperiodid());
								list.add(temp);
								System.out.println("Criteria Match Successful");
								break;
							}
							else {
								System.out.println("Criteria Mismatch");
								//break inner;
							}
				       } */
			}
			
		}
		
		return list;
	}
	
	
	
	public CommonDescription getDateValidity(String day,String week,String half, String rep,String abstypeid,String year, String date) {
		
		rep_period=rep;
		abs_id=abstypeid;
		
		//System.out.println("Inside Eligibility Date Validator");

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		CommonDescription weekoffstatus=null;
		CommonDescription msg=new CommonDescription();
		
		String urlweekoffstat = appgateway.getAppgatewayabs()+"/PersonEligibility/getAbsenceWeeklyOffStatus/"+abstypeid;
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlweekoffstat, HttpMethod.GET, request, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			weekoffstatus = response.getBody();
		}
		
		//System.out.println(weekoffstatus.getDescription());
		
		AllWeeklyHolidays[] allWeeklyHolidays=null;
		String urlallweekholiday = appgateway.getAppgatewayabs()+"/PersonEligibility/getAllWeeklyHolidayByRepPeriodId/"+rep;
		ResponseEntity<AllWeeklyHolidays[]> response1 = restTemplate.exchange(urlallweekholiday, HttpMethod.GET, request, AllWeeklyHolidays[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			allWeeklyHolidays = response1.getBody();
		}
		
		AllHolidaysOfYear[] allHolidaysOfYears=null;
		String urlallholidays=appgateway.getAppgateway()+"/Holiday/getAllHolidayByYear/"+year;
		ResponseEntity<AllHolidaysOfYear[]> response2=restTemplate.exchange(urlallholidays, HttpMethod.GET, request, AllHolidaysOfYear[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			allHolidaysOfYears = response2.getBody();
		}
		
		
		for(int m=0;m<allHolidaysOfYears.length;m++) {
		//System.out.println("Holiday check for "+allHolidaysOfYears[m]);
		if(allHolidaysOfYears[m].getHolidaydate().equals(date)) {
			//System.out.println("Inside Holiday Condition");
			msg.setDescription("Can't apply leave on holiday. Please change the Date.");
			return msg;
			
		}
		
		
		if(weekoffstatus.getDescription().equals("No")) {
			
			for(int i=0;i<allWeeklyHolidays.length;i++) {
			//System.out.println(allWeeklyHolidays[i].toString());
			if(allWeeklyHolidays[i].getDay().equals(day) && allWeeklyHolidays[i].getWeeks().contains(week) && allWeeklyHolidays[i].getYear().equals(year)) {
				if(allWeeklyHolidays[i].getHalfday().equals("Yes") && half.equals("Yes")) {
					msg.setDescription("");
					
				}else {
					msg.setDescription("Can't apply leave on holiday. Please change the Date.");
				}
				break;
			}
			else {
				msg.setDescription("");
			}
			}
			
			
		}
		}
		
		
		
		return msg;
	}


	public CommonDescription getCalculatedLeaves(List<LeaveData> leave_listData) {
		
		//System.out.println("Inside Eligibility Leave Calculator");
		
		//System.out.println(leave_listData);
		//System.out.println(leave_listData.size()+"||"+rep_period+"||"+abs_id);
		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		CommonDescription weekoffstatus=null;
		CommonDescription total_leave=new CommonDescription();
		
		String urlweekoffstat = appgateway.getAppgatewayabs()+"/PersonEligibility/getAbsenceWeeklyOffStatus/"+abs_id;
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlweekoffstat, HttpMethod.GET, request, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			weekoffstatus = response.getBody();
		}
		
		AllWeeklyHolidays[] allWeeklyHolidays=null;
		String urlallweekholiday = appgateway.getAppgatewayabs()+"/PersonEligibility/getAllWeeklyHolidayByRepPeriodId/"+rep_period;
		ResponseEntity<AllWeeklyHolidays[]> response1 = restTemplate.exchange(urlallweekholiday, HttpMethod.GET, request, AllWeeklyHolidays[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			allWeeklyHolidays = response1.getBody();
		}
		
		AllHolidaysOfYear[] allHolidaysOfYears=null;
		String urlallholidays=appgateway.getAppgateway()+"/Holiday/getAllHolidayByYear/"+leave_listData.get(0).getYear();
		ResponseEntity<AllHolidaysOfYear[]> response2=restTemplate.exchange(urlallholidays, HttpMethod.GET, request, AllHolidaysOfYear[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			allHolidaysOfYears = response2.getBody();
		}
		//List<LeaveData> leave_listData=new ArrayList<>();
		
		for(int x=0;x<leave_listData.size();x++) {
			for(int z=0;z<allHolidaysOfYears.length;z++) {
				if(leave_listData.get(x).getDate().equals(allHolidaysOfYears[z].getHolidaydate())) {
					leave_listData.remove(x);	
				}
			}
		}
		
		for(int f=0;f<leave_listData.size();f++) {
			//System.out.println(leave_listData.get(f));
		}
		
		
		
		//System.out.println(weekoffstatus.getDescription());
		String flg="";
		double sum=0;
		if(weekoffstatus.getDescription().equals("No")) {
			//System.out.println("\nWEEKLY OFF NOT INCLUDED");
			for(int i=0;i<leave_listData.size();i++) {
				for(int j=0;j<allWeeklyHolidays.length;j++) {
					System.out.print("\nRunning Case i="+i+" and j="+j+" and Date="+leave_listData.get(i).getDate()+" ");
					if(allWeeklyHolidays[j].getDay().equals(leave_listData.get(i).getDay()) && allWeeklyHolidays[j].getWeeks().contains(leave_listData.get(i).getWeek()) && allWeeklyHolidays[j].getYear().equals(leave_listData.get(i).getYear())) {
						if(allWeeklyHolidays[j].getHalfday().equals("Yes")) {
							sum=sum+0.5;
							System.out.print("Y 0.5");
						}
						flg="Y";
						break;
					}
					else {
						flg="N";
					}
					
					}
				if(flg.equals("N")) {
					if(leave_listData.get(i).getHalf().equals("Yes")) {
						sum=sum+0.5; 
						System.out.print("N 0.5");
					}else {
						sum=sum+1;
						System.out.print("N 1.0");
					}
				}
				
			}
		}else {
			//System.out.println("\n WEEKLY OFF INCLUDED");
			if(leave_listData.get(0).getHalf().equals("Yes")) {
				sum=sum+0.5;
			}else {
				sum=sum+1;
			}
			if(leave_listData.get(leave_listData.size()-1).getHalf().equals("Yes")) {
				sum=sum+0.5;
			}
			else {
				sum=sum+1;
			}
			for(int l=1;l<leave_listData.size()-1;l++) {
				sum=sum+1;
			}
			
		}
		
		total_leave.setDescription(Double.toString(sum));
		return total_leave;
	}
	

}
