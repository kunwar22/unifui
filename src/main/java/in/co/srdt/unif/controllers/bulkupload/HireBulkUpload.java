package in.co.srdt.unif.controllers.bulkupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.model.bulkupload.BulkUploadHireWrapper;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller

@RequestMapping("/Hirebulkupload")
public class HireBulkUpload {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appgateway;

    HireBulkUpload() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1500);
    }

    HireBulkUpload(HttpHeaders headers, RestTemplate restTemplate) {
        this.headers = headers;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/EmiUpload")
    public String manageaEMI(Model model, HttpServletRequest req) {
        return "fragments/bulkupload/ESIBulkUpload :: EsiBulkUpload";
    }
    
    @RequestMapping("/PFNumberUpload")
    public String manageaPF(Model model, HttpServletRequest req) {
        return "fragments/bulkupload/PFBulkUpload :: PFBulkUpload";
    }
    
    @RequestMapping("/PRANNumberUpload")
    public String manageaPRANNumber(Model model, HttpServletRequest req) {
        return "fragments/bulkupload/PRANNumberBulkUpload :: PRANBulkUpload";
    }
    
    @RequestMapping("/UANNumberUpload")
    public String manageaUANNumber(Model model, HttpServletRequest req) {
        return "fragments/bulkupload/UANBulkUpload :: UANBulkUpload";
    }

    @PostMapping(value = "/uploadEmi/BulkData")
    public @ResponseBody
    SingleResponseModel saveEMI(@ModelAttribute("BulkUploadHireWrapper") BulkUploadHireWrapper bulkUploadHireWrapper, HttpServletRequest req, Model model, @RequestParam("filename") MultipartFile filename) throws IOException {
        String url = appgateway.getAppgateway() + "/corehr/bankupload/additional/uploadEsiNumberDetails";
        
        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<bulkUploadHireWrapper.getBulkUploadHire().size();i++) {		
        	bulkUploadHireWrapper.getBulkUploadHire().get(i).setModifyby(login.getEmplid());
		}
        System.out.println("EMI" );
        SingleResponseModel res = new SingleResponseModel();
        List<Map<String, Object>> data = readNHAExcelTemplate(filename.getInputStream());
       
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.println("response msg" + res.getMessage());
            System.out.println("response status" + res.getStatus());
        } else {
            System.out.println("Request Failed");
        }
        model.addAttribute("result", res.getStatus());

        return res;
    }
    
    
    @PostMapping(value = "/uploaduploadPF/BulkData")
    public @ResponseBody
    SingleResponseModel savePF(@ModelAttribute("BulkUploadHireWrapper") BulkUploadHireWrapper bulkUploadHireWrapper, HttpServletRequest req, Model model, @RequestParam("filename") MultipartFile filename) throws IOException {
        String url = appgateway.getAppgateway() + "/corehr/bankupload/additional/uploadPfNoDetails";
        
        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<bulkUploadHireWrapper.getBulkUploadHire().size();i++) {		
        	bulkUploadHireWrapper.getBulkUploadHire().get(i).setModifyby(login.getEmplid());
		}
        System.out.println("EMI" );
        SingleResponseModel res = new SingleResponseModel();
        List<Map<String, Object>> data = readNHAExcelTemplate(filename.getInputStream());
       
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.println("response msg" + res.getMessage());
            System.out.println("response status" + res.getStatus());
        } else {
            System.out.println("Request Failed");
        }
        model.addAttribute("result", res.getStatus());

        return res;
    }

    
    @PostMapping(value = "/uploadPRANNumber/BulkData")
    public @ResponseBody
    SingleResponseModel savePRANNumber(@ModelAttribute("BulkUploadHireWrapper") BulkUploadHireWrapper bulkUploadHireWrapper, HttpServletRequest req, Model model, @RequestParam("filename") MultipartFile filename) throws IOException {
        String url = appgateway.getAppgateway() + "/corehr/bankupload/additional/uploadPranNoDetails";
        
        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<bulkUploadHireWrapper.getBulkUploadHire().size();i++) {		
        	bulkUploadHireWrapper.getBulkUploadHire().get(i).setModifyby(login.getEmplid());
		}
        System.out.println("PRAN" );
        SingleResponseModel res = new SingleResponseModel();
        List<Map<String, Object>> data = readNHAExcelTemplate(filename.getInputStream());
       
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.println("response msg" + res.getMessage());
            System.out.println("response status" + res.getStatus());
        } else {
            System.out.println("Request Failed");
        }
        model.addAttribute("result", res.getStatus());

        return res;
    }
    
    
    
    @PostMapping(value = "/uploadUANumber/BulkData")
    public @ResponseBody
    SingleResponseModel saveUAN(@ModelAttribute("BulkUploadHireWrapper") BulkUploadHireWrapper bulkUploadHireWrapper, HttpServletRequest req, Model model, @RequestParam("filename") MultipartFile filename) throws IOException {
        String url = appgateway.getAppgateway() + "/corehr/bankupload/additional/uploadUanNoDetails";
        
        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<bulkUploadHireWrapper.getBulkUploadHire().size();i++) {		
        	bulkUploadHireWrapper.getBulkUploadHire().get(i).setModifyby(login.getEmplid());
		}
        System.out.println("UAN" );
        SingleResponseModel res = new SingleResponseModel();
        List<Map<String, Object>> data = readNHAExcelTemplate(filename.getInputStream());
       
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.println("response msg" + res.getMessage());
            System.out.println("response status" + res.getStatus());
        } else {
            System.out.println("Request Failed");
        }
        model.addAttribute("result", res.getStatus());

        return res;
    }


    public List<Map<String, Object>> readNHAExcelTemplate(InputStream input) throws IOException {
        List<String> headers = new ArrayList<>();
        headers.clear();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.clear();
        Workbook workbook = new XSSFWorkbook(input);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        boolean hflag = true;
        int counter = 0;

        while (iterator.hasNext()) {
            counter = 0;
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            Map<String, Object> map = new HashMap<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (hflag) {
                    headers.add(cell.getStringCellValue());
                } else {
                    if (cell.getCellType() == CellType.NUMERIC)
                        map.put(headers.get(counter).replace(" ", ""), cell.getNumericCellValue());
                    else
                        map.put(headers.get(counter).replace(" ", ""), cell.getStringCellValue());
                    counter++;
                }

            }
            if (!hflag)
                data.add(map);
            hflag = false;
        }
        return data;
    }


}
