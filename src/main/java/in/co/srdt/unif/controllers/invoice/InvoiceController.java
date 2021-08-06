package in.co.srdt.unif.controllers.invoice;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.invoice.RmbrsEbsSyncTable;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.utils.ApplicationGateway;
import in.co.srdt.unif.utils.GeneratePdfReport;

@Controller
@RequestMapping("/get")
public class InvoiceController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	String businessUnitPayload;
	
	@RequestMapping("/invoicestatus")
	public String getInvoiceReport(HttpServletRequest request,Model model)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);

		/* LOADING OF BUSINESS UNITS STARTS */
		
		businessUnitPayload = "{\r\n"
				+ "  \"code\": \"\",\r\n"
				+ "  \"name\": \"\",\r\n"
				+ "  \"status\": \"Active\"\r\n"
				+ "}";
		
		String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
		
		BUsearchresult[] busunit=null;
		
		HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);
		
		ResponseEntity<BUsearchresult[]> busunitres= restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq, BUsearchresult[].class);
		
		if(busunitres.getStatusCode() == HttpStatus.OK)
		{
			busunit = busunitres.getBody();			
		}
		else
		{
			System.out.println("Request Failed");
		}
		
		model.addAttribute("busunit",busunit);
		
		/* LOADING OF BUSINESS UNITS ENDS */
		
		/* LOADING OF Reimbursements STARTS */
		
		String urlremid=appgateway.getAppgateway()+"/General/loadReimbursementNameLOV";
		
		CommonLOV[] remburse=null;
		
		HttpEntity<String> remreq = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> remres= restTemplate.exchange(urlremid, HttpMethod.GET, remreq, CommonLOV[].class);
		
		if(remres.getStatusCode() == HttpStatus.OK)
		{
			remburse = remres.getBody();			
		}
		else
		{
			System.out.println("Request Failed");
		}
		
		model.addAttribute("remburse",remburse);
		
		/* LOADING OF Reimbursements ENDS */
		
		
		return "fragments/invoice/invoicestatus :: invoicestatus";
	}
	
	@ResponseBody
	@RequestMapping("/getInvoices/{stdtval}/{edtval}/{buid}/{remid}")
	public RmbrsEbsSyncTable[] getInvoices(@PathVariable("stdtval") String stdtval,@PathVariable("edtval") String edtval,@PathVariable("buid") String buid,@PathVariable("remid") String remid, HttpServletRequest request,Model model)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		RmbrsEbsSyncTable[] invsearchmodel = null;
		
		String urlInvoiceDetails = appgateway.getAppgatewaypyrl_sandhya() + "/api/common/getAllRmbrsEbsByBuid/"+stdtval+"/"+edtval+"/"+buid+"/"+remid;
		
		//System.out.println("INVOICE URL ::: "+urlInvoiceDetails);
		
		HttpEntity<String> invrequest = new HttpEntity<String>(headers);
		
		ResponseEntity<RmbrsEbsSyncTable[]> invoiceResponse =
				  restTemplate .exchange(urlInvoiceDetails, HttpMethod.GET, invrequest,
						  RmbrsEbsSyncTable[].class);
		
		if (invoiceResponse.getStatusCode() == HttpStatus.OK) {
			invsearchmodel = invoiceResponse.getBody();
		}
		
		
		return invsearchmodel;
				  
	}

	/* CODE FOR GENERATING REPORT IN PDF FORMAT STARTS */

	 @RequestMapping(value = "/pdfreport/{stdtval}/{edtval}/{buid}/{remid}", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<InputStreamResource> invoiceReport(@PathVariable("stdtval") String stdtval,@PathVariable("edtval") String edtval,@PathVariable("buid") String buid,@PathVariable("remid") String remid, HttpServletRequest request,Model model) {

		 	RmbrsEbsSyncTable[] invsearchmodel = null;
			
			String urlInvoiceDetails = appgateway.getAppgatewaypyrl_sandhya() + "/api/common/getAllRmbrsEbsByBuid/"+stdtval+"/"+edtval+"/"+buid+"/"+remid;
			
			//System.out.println("INVOICE URL ::: "+urlInvoiceDetails);
			
			HttpEntity<String> invrequest = new HttpEntity<String>(headers);
			
			ResponseEntity<RmbrsEbsSyncTable[]> invoiceResponse =
					  restTemplate .exchange(urlInvoiceDetails, HttpMethod.GET, invrequest,
							  RmbrsEbsSyncTable[].class);
			
			if (invoiceResponse.getStatusCode() == HttpStatus.OK) {
				invsearchmodel = invoiceResponse.getBody();
			}
       
			ByteArrayInputStream bis = null;
			//String imgpath="../unif/src/main/resources/static/images/UPMRCL.png";
			try
			{
				bis= GeneratePdfReport.invoiceReport(invsearchmodel);
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}

       
       headers.add("Content-Disposition", "inline; filename=invoice_report.pdf");

       return ResponseEntity
               .ok()
               .headers(headers)
               .contentType(MediaType.APPLICATION_PDF)
               .body(new InputStreamResource(bis));
   }
	 
	  
	
	/* CODE FOR GENERATING REPORT IN PDF FORMAT ENDS */
	 
}
