package in.co.srdt.unif.controllers.enterprise;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.search.DatasetListResult;
import in.co.srdt.unif.model.search.DepartmentSearch;
import in.co.srdt.unif.model.search.DeptByBUSetResult;
import in.co.srdt.unif.model.search.EntryStepSearchResult;
import in.co.srdt.unif.model.search.GradeSearchResult;
import in.co.srdt.unif.model.search.JobByBUSetResult;
import in.co.srdt.unif.model.search.JobSearchResult;
import in.co.srdt.unif.model.search.PositionSearchResult;
import in.co.srdt.unif.model.create.Position;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class PositionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;

	
	PositionController()
	{}
	
	PositionController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	
	
	@RequestMapping("/manageposition")
	public String managePosition(Model model) {

		model.addAttribute("status", Status.values());
		return "fragments/enterprise/manage/searchPosition :: searchPosition";
	}
	
	@RequestMapping("/createposition")
	public String createPosition(Model model) {
		
		model.addAttribute("response", new SingleResponseModel());
		
		Position position=new Position();
		DatasetListResult[] datasetObj = null;
		CommonLOV[] assignCat=null;
		CommonLOV[] regortemp=null;
		CommonLOV[] fullorpart=null;
		CommonLOV[] type=null;
		CommonLOV[] probperiod=null;
		CommonLOV[] noticeperiod=null;
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);
		
		String urlassignCat = appgateway.getAppgateway()+"/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request1, CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			assignCat = response1.getBody();
		}
		
		String urlregortemp = appgateway.getAppgateway()+"/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
		}
		String urlrfullorpart = appgateway.getAppgateway()+"/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			fullorpart = response3.getBody();
		}
		String urltype = appgateway.getAppgateway()+"/General/loadType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request1, CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			type = response4.getBody();
		}
		
		String urlprobperiod = appgateway.getAppgateway()+"/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
		}
		
		String urlnoticeperiod = appgateway.getAppgateway()+"/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
		}
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);
		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url,HttpMethod.POST, request, DatasetListResult[].class);
		
		if (response.getStatusCode() == HttpStatus.OK) { 
			datasetObj = response.getBody(); 
		}
		
		
		CommonLOV[] cd=null;
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request1, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		
		model.addAttribute("CountryDetails", cd);
		model.addAttribute("CreatePosition", position);
		model.addAttribute("assignCat", assignCat);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("type", type);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("datasetlist", datasetObj);
		model.addAttribute("status", Status.values());
		
		
		return "fragments/enterprise/create/createPosition :: createPosition";
	
	
	}
	
	@ResponseBody
	@RequestMapping("/entrygradebind/{gradesId}")
	public EntryStepSearchResult[] bindEntryStep(@PathVariable("gradesId") String gradesId, Model model) {

		EntryStepSearchResult[] sd = null;
		String url = appgateway.getAppgateway()+"/Grades/getGradeStepsByGradeId/" + gradesId;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<EntryStepSearchResult[]> response = restTemplate.exchange(url, HttpMethod.GET, request, EntryStepSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("EntrySteps", sd);

		return sd;

	}
	
	
	@ResponseBody
	@RequestMapping("/departmentbind/{datasetId}")
	public DeptByBUSetResult[] bindDept(@PathVariable("datasetId") String datasetId, Model model) {

		DeptByBUSetResult[] sd = null;
		String url = appgateway.getAppgateway()+"/Departments/getDepartmentsByBUSetId/" + datasetId;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<DeptByBUSetResult[]> response = restTemplate.exchange(url, HttpMethod.GET, request, DeptByBUSetResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("deptByBUSet", sd);

		return sd;

	}
	
	
	@ResponseBody
	@RequestMapping("/jobbind/{datasetId}")
	public JobByBUSetResult[] bindJob(@PathVariable("datasetId") String datasetId, Model model) {

		JobByBUSetResult[] sd = null;
		String url = appgateway.getAppgateway()+"/Jobs/getJobsByBUSetId/" + datasetId;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<JobByBUSetResult[]> response = restTemplate.exchange(url, HttpMethod.GET, request, JobByBUSetResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("jobByBUSet", sd);

		return sd;

	}
		
	@RequestMapping(value = "/savePosition", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public  String savePosition( @Valid Position position, BindingResult bindingResult, Model model)
	{
		model.addAttribute("response", new SingleResponseModel());
		
		SingleResponseModel res = new SingleResponseModel();
		
		
		DatasetListResult[] datasetObj = null;
		CommonLOV[] assignCat=null;
		CommonLOV[] regortemp=null;
		CommonLOV[] fullorpart=null;
		CommonLOV[] type=null;
		CommonLOV[] probperiod=null;
		CommonLOV[] noticeperiod=null;
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);
		
		String urlassignCat = appgateway.getAppgateway()+"/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request1, CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			assignCat = response1.getBody();
		}
		
		String urlregortemp = appgateway.getAppgateway()+"/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
		}
		String urlrfullorpart = appgateway.getAppgateway()+"/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			fullorpart = response3.getBody();
		}
		String urltype = appgateway.getAppgateway()+"/General/loadType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request1, CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			type = response4.getBody();
		}
		
		String urlprobperiod = appgateway.getAppgateway()+"/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
		}
		
		String urlnoticeperiod = appgateway.getAppgateway()+"/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
		}
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);
		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url,HttpMethod.POST, request, DatasetListResult[].class);
		
		if (response.getStatusCode() == HttpStatus.OK) { 
			datasetObj = response.getBody(); 
		}
		CommonLOV[] cd=null;
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request1, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		
		model.addAttribute("CountryDetails", cd);
		
		model.addAttribute("CreatePosition", position);
		model.addAttribute("assignCat", assignCat);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("type", type);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("datasetlist", datasetObj);
		model.addAttribute("status", Status.values());
		
		
		
		//System.out.println("ALL ERRORS :: "+ bindingResult.getAllErrors());
		model.addAttribute("CreatePosition",position);
		model.addAttribute("bindingResult",bindingResult);
		if (bindingResult.hasErrors()) {
			return "fragments/enterprise/create/createPosition :: createPosition";	
		}
		
		
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
		//	System.out.println("POSITION ::"+ position.toString());
			
			HttpEntity<Position> request2 = new HttpEntity<Position>(position, headers);
			
			ResponseEntity<SingleResponseModel> responsesave= restTemplate.exchange(appgateway.getAppgateway()+"/Position/savePosition",HttpMethod.POST,request2, SingleResponseModel.class);
			if(responsesave.getStatusCode() == HttpStatus.OK) {
				res = responsesave.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsesave.getStatusCode());
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		model.addAttribute("response",res);
		return "fragments/enterprise/create/createPosition :: createPosition";
	}
	
	@ResponseBody
	@PostMapping("/searchGradePos")
	public GradeSearchResult[] getGradeListpos(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("statusGrade");
		
		String url = appgateway.getAppgateway()+"/Grades/gradesSearchList";
		GradeSearchResult[] gradeObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<GradeSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				GradeSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			gradeObj = response.getBody();
		}
		
		return gradeObj;

	}
	
	@RequestMapping(value = "/correctPosition/{flag}", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String correctPosition(@Valid Position position, BindingResult br, @PathVariable("flag") String flag, Model model)
	{
		
		model.addAttribute("response", new SingleResponseModel());
		
		SingleResponseModel res = new SingleResponseModel();
		
		
		DatasetListResult[] datasetObj = null;
		CommonLOV[] assignCat=null;
		CommonLOV[] regortemp=null;
		CommonLOV[] fullorpart=null;
		CommonLOV[] type=null;
		CommonLOV[] probperiod=null;
		CommonLOV[] noticeperiod=null;
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);
		
		String urlassignCat = appgateway.getAppgateway()+"/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request1, CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			assignCat = response1.getBody();
		}
		
		String urlregortemp = appgateway.getAppgateway()+"/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
		}
		String urlrfullorpart = appgateway.getAppgateway()+"/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			fullorpart = response3.getBody();
		}
		String urltype = appgateway.getAppgateway()+"/General/loadType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request1, CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			type = response4.getBody();
		}
		
		String urlprobperiod = appgateway.getAppgateway()+"/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
		}
		
		String urlnoticeperiod = appgateway.getAppgateway()+"/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
		}
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);
		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url,HttpMethod.POST, request, DatasetListResult[].class);
		
		if (response.getStatusCode() == HttpStatus.OK) { 
			datasetObj = response.getBody(); 
		}
		CommonLOV[] cd=null;
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request1, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		
		model.addAttribute("CountryDetails", cd);		
		model.addAttribute("CreatePosition", position);
		model.addAttribute("assignCat", assignCat);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("type", type);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("datasetlist", datasetObj);
		model.addAttribute("status", Status.values());
		
		
		
		//System.out.println("ERROR :: " + br.getAllErrors().toString());
		model.addAttribute("bindingResult",br);
		if (br.hasErrors()) {
		//	System.out.println("INSIDE BIND RESULT");
			return "fragments/enterprise/create/createPosition :: createPosition";	
		}
		
		String urll="";
		
		if(flag.equals("Correct")) {
			urll = appgateway.getAppgateway()+"/Position/correctPosition";
			
		
		}
		else if(flag!="Correct") {

			urll = appgateway.getAppgateway()+"/Position/updatePosition";
		
			}
		
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<Position> requests = new HttpEntity<Position>(position, headers);
			
			
			ResponseEntity<SingleResponseModel> responses= restTemplate.exchange(urll,HttpMethod.PUT,requests, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = responses.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Position.");
		}
		
		//System.out.println(res.toString());
		model.addAttribute("response",res);
		return "fragments/enterprise/create/createPosition :: createPosition";
	}
	
	@ResponseBody
	@PostMapping("/searchPosition")
	public PositionSearchResult[] getPositionList(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("statusGrade");
		
		String url = appgateway.getAppgateway()+"/Position/positionSearchList";
		PositionSearchResult[] posObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<PositionSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				PositionSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			posObj = response.getBody();
		}
		
		for(int i=0;i<posObj.length;i++) {
			if(posObj[i].getEffectenddate()==null) {
				posObj[i].setEffectenddate("");
			}
		}

		return posObj;

	}
		
	@RequestMapping("edit/EditPosition/{positionid}/{effdt}")
	public String editPosition(@PathVariable("positionid") String positionid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException {
		model.addAttribute("response", new SingleResponseModel());
		String Searchpositionid = positionid;
			
		
		Position Position=null;
		DatasetListResult[] datasetObj = null;
		CommonLOV[] assignCat=null;
		CommonLOV[] regortemp=null;
		CommonLOV[] fullorpart=null;
		CommonLOV[] type=null;
		CommonLOV[] probperiod=null;
		CommonLOV[] noticeperiod=null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);
		
		String urlassignCat = appgateway.getAppgateway()+"/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request1, CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			assignCat = response1.getBody();
		}
		
		String urlregortemp = appgateway.getAppgateway()+"/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
		}
		String urlrfullorpart = appgateway.getAppgateway()+"/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request1, CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			fullorpart = response3.getBody();
		}
		String urltype = appgateway.getAppgateway()+"/General/loadType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request1, CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			type = response4.getBody();
		}
		
		String urlprobperiod = appgateway.getAppgateway()+"/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
		}
		
		String urlnoticeperiod = appgateway.getAppgateway()+"/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request1, CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
		}
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);
		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url,HttpMethod.POST, request, DatasetListResult[].class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			datasetObj = response.getBody(); 
		}
		CommonLOV[] cd=null;
		String urlcountry = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlcountry, HttpMethod.GET, request1, CommonLOV[].class);	
		if(response7.getStatusCode() == HttpStatus.OK) {
			cd = response7.getBody();
			
		} 
		
		model.addAttribute("CountryDetails", cd);
		
		String urrlPosition= appgateway.getAppgateway()+"/Position/getPositionById/" + Searchpositionid;
		
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> getPositionRequest = new HttpEntity<String>(headerss);
		
		ResponseEntity<Position> getPositionResponse = restTemplate.exchange(urrlPosition, HttpMethod.GET,
				getPositionRequest, Position.class);

		if (getPositionResponse.getStatusCode() == HttpStatus.OK) {
			Position = getPositionResponse.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(getPositionResponse.getStatusCode());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
		{
			java.util.Date date1 = sdf.parse(effdt);
			java.util.Date date2 = sdf.parse(Position.getEffectstartdate());
		
				if (date1.compareTo(date2) <= 0)
				{ 
					//System.out.println("earlier"); 
					Position.setAdditionalatr("Error");
				}
				else if(date1.compareTo(date2) > 0)	  
				{
					Position.setAdditionalatr(effdt);
				}
		}
		else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
			Position.setAdditionalatr("Correct");
		}
		

		String flag=Position.getEffectstartdate();
		if(flag!=null) {
		flag=flag.substring(0,10);	
		Position.setEffectstartdate(flag);
		}
		
		String flag1=Position.getEffectenddate();
		if(flag1!=null) {
		flag1=flag1.substring(0,10);	
		Position.setEffectenddate(flag1);
		}
		// ****start  bind****//
		bindEntryStep(Long.toString(Position.getGradestepsid()), model);
		// ****end  bind****//
		
		
		model.addAttribute("CreatePosition", Position);
		model.addAttribute("assignCat", assignCat);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("type", type);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("datasetlist", datasetObj);
		model.addAttribute("status", Status.values());
		
		
		return "fragments/enterprise/create/createPosition:: createPosition";

	}
			
	@ResponseBody
	@RequestMapping("/possearchbyid/{posId}")
	public Position posByPosId(@PathVariable("posId") String posId, Model model) {

		Position sd1 = new Position();
		String urlp = appgateway.getAppgateway()+"/Position/getPositionById/" + posId;
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<Position> response = restTemplate.exchange(urlp, HttpMethod.GET, request, Position.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("posById", sd1);

		return sd1;

	}	
	
	
	@ResponseBody
	@PostMapping(path="/position/getDepartmentByBUId")
	public DepartmentSearch[] departmentSearchByBUId(HttpServletRequest requestFromDT) {
		
		
		String name = requestFromDT.getParameter("name");
		String code = requestFromDT.getParameter("code");
		String status = requestFromDT.getParameter("status");
		String dataSets = requestFromDT.getParameter("dataSets");
		
		DepartmentSearch[] DepartmentSearch = null;
		
		String urlJob = appgateway.getAppgateway()+"/Departments/getDepartmentsByBUSetId";
		
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"code\"" + ":\"" +code+ "\"," +
						"\"datasetsid\"" + ":\"" +dataSets+ "\"," +						
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		
		//System.out.println("Payload for Department Search in Position ::"+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<DepartmentSearch[]> response = restTemplate.exchange(urlJob, HttpMethod.POST, request, DepartmentSearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			DepartmentSearch = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return DepartmentSearch;
	}
	
	@ResponseBody
	@PostMapping(path="/position/getJobsByBUId")
	public JobSearchResult[] jobSearchResult(HttpServletRequest requestFromDT) {
		
		
		String name = requestFromDT.getParameter("name");
		String code = requestFromDT.getParameter("code");
		String status = requestFromDT.getParameter("status");
		String dataSets = requestFromDT.getParameter("dataSets");
		
		JobSearchResult[] JobSearchResult = null;
		
		String urlJob = appgateway.getAppgateway()+"/Jobs/getJobsByBUSetId";
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"code\"" + ":\"" +code+ "\"," +
						"\"datasetsid\"" + ":\"" +dataSets+ "\"," +
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		//System.out.println("Payload for Job Search in Position ::"+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<JobSearchResult[]> response = restTemplate.exchange(urlJob, HttpMethod.POST, request, JobSearchResult[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			JobSearchResult = response.getBody();
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return JobSearchResult;
	}
	

}
