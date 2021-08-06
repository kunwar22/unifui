package in.co.srdt.unif.controllers.enterprise;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateJob;
import in.co.srdt.unif.model.search.DatasetListResult;
import in.co.srdt.unif.model.search.JobSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class JobController {

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@RequestMapping("/managejob")
	public String manageJobs(Model model) {
		
		//System.out.println("Inside Job");
		
     	CommonLOV[] datasets=null;
		
		String urlDatasets = appgateway.getAppgateway()+"/ReferenceDataSets/getAllDataSetsLOV";
		headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			datasets = response.getBody();
			
		} 
		model.addAttribute("datasets", datasets);
		model.addAttribute("status", Status.values());
			
		return "fragments/enterprise/manage/searchJob :: searchJob";
	}
	
	@ResponseBody
	@PostMapping(path="/searchJob/getJobId")
	public JobSearchResult[] jobSearchResult(HttpServletRequest requestFromDT) {
		
		
		String name = requestFromDT.getParameter("name");
		String code = requestFromDT.getParameter("code");
		String status = requestFromDT.getParameter("status");
		String dataSets = requestFromDT.getParameter("dataSets");
		
		JobSearchResult[] JobSearchResult = null;
		
		String urlJob = appgateway.getAppgateway()+"/Jobs/jobsSearchList";
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"code\"" + ":\"" +code+ "\"," +
						"\"datasets\"" + ":\"" +dataSets+ "\"," +
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		
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
	
	@RequestMapping("/loadJobPage")
	public String createJob(Model model) {		
		
		CreateJob CreateJob = new CreateJob();
	    CommonLOV[] jobfunction=null;
		
		String urlJOB = appgateway.getAppgateway()+"/JobFunction/getAllJobFunctionLOV";

		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestJob = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlJOB, HttpMethod.GET, requestJob, CommonLOV[].class);
		
		if(response1.getStatusCode() == HttpStatus.OK) {
			jobfunction = response1.getBody();
		} 
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		DatasetListResult[] datasetObj = null;
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				DatasetListResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			datasetObj = response.getBody();
			
		}
		
		model.addAttribute("datasetlist", datasetObj);
		
		model.addAttribute("jobfunction", jobfunction);
		model.addAttribute("status", Status.values());
		model.addAttribute("CreateJob", CreateJob);
		return "fragments/enterprise/create/createJob :: createJob";
	}
	
	
	@ResponseBody
	@PostMapping(value="/createJobResource")
	public String saveJob(HttpServletRequest requestFromDT) {
		
	
		String CR_JOB_NAME = requestFromDT.getParameter("CR_JOB_NAME");
		String CR_JOB_EFFDT = requestFromDT.getParameter("CR_JOB_EFFDT");
		String CR_JOBID = requestFromDT.getParameter("CR_JOBID");
		String CR_JOB_CODE = requestFromDT.getParameter("CR_JOB_CODE");
		String CR_JOB_DATASET = requestFromDT.getParameter("CR_JOB_DATASET");
		String CR_STATUS = requestFromDT.getParameter("CR_STATUS");
	
		String setJob="";
		String url = appgateway.getAppgateway()+"/Jobs/saveJobs";
		
		String payLode = "{" +
				"\"name\"" + ":\"" +CR_JOB_NAME+ "\"," +
				"\"effectstartdate\""  + ":\"" +CR_JOB_EFFDT+ "\"," +
				"\"jobfunction\"" + ":\"" +CR_JOBID+ "\"," +
				"\"code\"" + ":\"" +CR_JOB_CODE+ "\"," +
				"\"dataset\"" + ":\"" +CR_JOB_DATASET+ "\"," +
				"\"status\"" + ":\"" +CR_STATUS+ "\"" +
				
				"}";
		//System.out.println("PAYLOAD :: "+payLode);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setJob = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setJob;
	}
	
	@RequestMapping("edit/EditJobResource/{jobsid}/{effdt}")
	public String editJob(@PathVariable("jobsid") String jobsid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException {
		
		String Searchjobsid = jobsid;
		DataSetController dataset=new DataSetController(headers,restTemplate,appgateway);

		CreateJob CreateJob = null;
		
		String urrlJob= appgateway.getAppgateway()+"/Jobs/getJobs/" + Searchjobsid;
		URI urlJB = new URI(urrlJob);
		
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getJobRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateJob> getJobResponse = restTemplate.exchange(urlJB, HttpMethod.GET,
				getJobRequest, CreateJob.class);

		if (getJobResponse.getStatusCode() == HttpStatus.OK) {
			
			CreateJob = getJobResponse.getBody();
						
		} else {
			System.out.println("Request Failed");
			System.out.println(getJobResponse.getStatusCode());
		}
		requestFromDT.setAttribute("code","");
		requestFromDT.setAttribute("name","");
		model.addAttribute("datasetlist",dataset.getDatasetlist(requestFromDT));
		
		if(CreateJob.getEffectstartdate()!=null) {
			String effdtstart=CreateJob.getEffectstartdate();
			effdtstart=effdtstart.substring(0,10);
			CreateJob.setEffectstartdate(effdtstart);
		}
		
		CommonLOV[] jobfunction=null;
		
		String urlJOB = appgateway.getAppgateway()+"/JobFunction/getAllJobFunctionLOV";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestJob = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlJOB, HttpMethod.GET, requestJob, CommonLOV[].class);
		
		if(response1.getStatusCode() == HttpStatus.OK) {
			jobfunction = response1.getBody();
		} 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
		{
			java.util.Date date1 = sdf.parse(effdt);
			java.util.Date date2 = sdf.parse(CreateJob.getEffectstartdate());
		
				if (date1.compareTo(date2) <= 0)
				{ 
					//System.out.println("earlier"); 
					CreateJob.setAdditionalatr("Error");
				}
				else if(date1.compareTo(date2) > 0)	  
				{
					CreateJob.setAdditionalatr(effdt);
				}
		}
		else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
			CreateJob.setAdditionalatr("Correct");
		}
		
		
		model.addAttribute("jobfunction", jobfunction);
		
		model.addAttribute("status", Status.values());
		model.addAttribute("CreateJob", CreateJob);
		return "fragments/enterprise/create/createJob :: createJob";

	}
		
	@ResponseBody
	@PostMapping("/edit/EditJob/correctJob")
	public String correctJobData(HttpServletRequest requestFromDT) {
		
		String CR_JOB_ID = requestFromDT.getParameter("CR_JOB_ID");
		String CR_JOB_ACTIONID = requestFromDT.getParameter("CR_JOB_ACTIONID");
		
		String CR_JOB_NAME = requestFromDT.getParameter("CR_JOB_NAME");
		String CR_JOB_EFFDT = requestFromDT.getParameter("CR_JOB_EFFDT");
		String CR_JOBID = requestFromDT.getParameter("CR_JOBID");
		String CR_JOB_CODE = requestFromDT.getParameter("CR_JOB_CODE");
		String CR_JOB_DATASET = requestFromDT.getParameter("CR_JOB_DATASET");
		String CR_STATUS = requestFromDT.getParameter("CR_STATUS");
		
		String flag=requestFromDT.getParameter("FLAG");
		flag=flag.trim();
		String setJob="";
		
		String url="";
		
		if(flag.equals("Correct")) {
			url = appgateway.getAppgateway()+"/Jobs/correctJobs";
		
		}
		else if(flag!="Correct") {

			url = appgateway.getAppgateway()+"/Jobs/updateJobs";
		
		}
		
		String payLode = "{" +
				"\"actionid\"" + ":\"" +CR_JOB_ACTIONID+ "\"," +
				"\"jobsid\""  + ":\"" +CR_JOB_ID+ "\"," +
				"\"name\"" + ":\"" +CR_JOB_NAME+ "\"," +
				"\"effectstartdate\""  + ":\"" +CR_JOB_EFFDT+ "\"," +
				"\"jobfunction\"" + ":\"" +CR_JOBID+ "\"," +
				"\"code\"" + ":\"" +CR_JOB_CODE+ "\"," +
				"\"dataset\"" + ":\"" +CR_JOB_DATASET+ "\"," +
				"\"status\"" + ":\"" +CR_STATUS+ "\"" +
				
				"}";
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setJob = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setJob;
	}



	
}
