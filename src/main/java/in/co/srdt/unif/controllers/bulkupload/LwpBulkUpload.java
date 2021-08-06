package in.co.srdt.unif.controllers.bulkupload;

import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.lwp.CreateLwpWrapper;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@SessionAttributes("pid")
@RequestMapping("/bulkupload")
public class LwpBulkUpload {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appgateway;

    LwpBulkUpload() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1500);
    }

    LwpBulkUpload(HttpHeaders headers, RestTemplate restTemplate) {
        this.headers = headers;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/lwp")
    public String manageaLwp(Model model, HttpServletRequest req) {
        return "fragments/bulkupload/LWPBulkUpload :: LwpBulkUpload";
    }


    @PostMapping(value = "/uploadLwp/BulkData/")
    public @ResponseBody
    SingleResponseModel uploadOTP(@ModelAttribute("createLwpWrapper") CreateLwpWrapper createLwpWrapper, HttpServletRequest req, Model model, @RequestParam("filename") MultipartFile filename) throws IOException {
        SingleResponseModel res = new SingleResponseModel();
        String url = appgateway.getAppgatewayabs() + "/Person/LWP/saveAllLeaveWithoutPay";

        List<Map<String, Object>> data = readLwpExcelTemplate(filename.getInputStream());
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            System.out.println("response" + res.getMessage());
            System.out.println("response" + res.getStatus());
        } else {
            System.out.println("Request Failed");
        }
        model.addAttribute("response", res);
        return res;
    }


    public List<Map<String, Object>> readLwpExcelTemplate(InputStream input) throws IOException {
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
