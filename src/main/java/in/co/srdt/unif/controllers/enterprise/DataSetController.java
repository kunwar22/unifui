package in.co.srdt.unif.controllers.enterprise;


import java.net.URI;
import java.net.URISyntaxException;
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
import in.co.srdt.unif.model.search.DatasetListResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class DataSetController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;	

	@RequestMapping("/managedataset")
	public String searchdataset(Model model) {
		
		return "fragments/enterprise/manage/managedataset :: searchdataset";
	}
	
	DataSetController()
	{
		
	}
	
	DataSetController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	DataSetController(HttpHeaders headers,RestTemplate restTemplate,ApplicationGateway api){
		this.headers=headers;
		this.restTemplate=restTemplate;
		this.appgateway=api;
	}

	@RequestMapping("/createdataset")
	public String createdataset(Model model) {
		DatasetListResult DatasetListResult=new DatasetListResult();
		
		model.addAttribute("DataList", DatasetListResult);
		return "fragments/enterprise/create/createDataset :: createdataset";
	}

	@ResponseBody
	@PostMapping("/saveDataset")
	public String saveDataset(HttpServletRequest requestFromDT, Model model) {

		String setDataset = "";

		String strcode = requestFromDT.getParameter("code");
		String strname = requestFromDT.getParameter("name");
		String strdescr = requestFromDT.getParameter("description");

		String url = appgateway.getAppgateway()+"/ReferenceDataSets/saveReferenceDataSets";

		String payLode = "{" +

				"\"code\"" + ":\"" + strcode + "\"," + "\"description\"" + ":\"" + strdescr + "\"," + "\"name\"" + ":\""
				+ strname + "\"" +

				"}";
		//System.out.println("PAYLOAD :: "+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setDataset = response.getBody();
		}
		model.addAttribute("legalAddressDetails", setDataset);
		//System.out.println("RESPONSE :: "+setDataset);
		return setDataset;
	}

	@ResponseBody
	@PostMapping("/searchdataset")
	public DatasetListResult[] getDatasetlist(HttpServletRequest requestFromDT) throws URISyntaxException  {
		

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		
		if(searchcode==null)
		{
			searchcode="";
		}
		
		if(searchname==null)
		{
			searchname="";
		}
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		
		DatasetListResult[] datasetObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				DatasetListResult[].class);
		

		if (response.getStatusCode() == HttpStatus.OK) {
			datasetObj = response.getBody();
		}

		return datasetObj;
	}

	@RequestMapping("/edit/editDataset/correctdataset/{datasetsid}")
	public String editDataset(@PathVariable("datasetsid") String datasetsid, Model model)
			throws URISyntaxException {

		String SearchdatasetId = datasetsid;
		
		DatasetListResult DatasetListResult=null;
		
		String urrlDatasetList= appgateway.getAppgateway()+"/ReferenceDataSets/getReferenceDataSets/" + SearchdatasetId;
		URI urlDS = new URI(urrlDatasetList);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getDatasetListRequest = new HttpEntity<String>(headerss);

		ResponseEntity<DatasetListResult> getDatasetLisResponse = restTemplate.exchange(urlDS, HttpMethod.GET,
				getDatasetListRequest, DatasetListResult.class);

		if (getDatasetLisResponse.getStatusCode() == HttpStatus.OK) {
			DatasetListResult = getDatasetLisResponse.getBody();

		} 
		else {
			System.out.println("Request Failed");
			System.out.println(getDatasetLisResponse.getStatusCode());
		}
		model.addAttribute("DataList", DatasetListResult);
	
		return "fragments/enterprise/create/createDataset :: createdataset";

		}
	
	
	
	@ResponseBody
	@PostMapping("/editdataset/correctdataset/savedataset")
	public String correctDataset(HttpServletRequest requestFromDT, Model model) {

		String setDataset = "";
		
		String strdatasetactionid = requestFromDT.getParameter("actionid");
		String strdatasetid= requestFromDT.getParameter("datasetsid");
		String strcode = requestFromDT.getParameter("code");
		String strname = requestFromDT.getParameter("name");
		String strdescr = requestFromDT.getParameter("description");

		String urlDS = appgateway.getAppgateway()+"/ReferenceDataSets/correctReferenceDataSets";

		String payLode = "{" +
				"\"actionid\"" + ":\"" + strdatasetactionid + "\"," + 
				"\"datasetsid\"" + ":\"" + strdatasetid + "\"," + 
				"\"code\"" + ":\"" + strcode + "\"," + 
				"\"name\"" + ":\"" + strname + "\"," +
				"\"description\"" + ":\""+ strdescr + "\"" +

				"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<String> response = restTemplate.exchange(urlDS, HttpMethod.PUT, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			setDataset = response.getBody();
		}
		model.addAttribute("legalAddressDetails", setDataset);

		return setDataset;
	}
	
	
}
