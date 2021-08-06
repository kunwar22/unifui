package in.co.srdt.unif.controllers.payroll;

import in.co.srdt.unif.model.ExcelModel;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Controller
public class ManageOTPController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    @Autowired
    ApplicationGateway appgateway;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1500);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping("/downloadopttemplate/{calid}")
    public String downloadOTPTemplate(@PathVariable("calid") String calid, HttpServletResponse response){

        HttpEntity<String> request = new HttpEntity<String>(headers);
        String url = appgateway.getAppgateway_payroll()+"/api/runpayroll/getotpregister/"+calid;

        //Map data[] = null;
        ExcelModel workbook = null;
        ResponseEntity<ExcelModel> res = restTemplate.exchange(url, HttpMethod.GET,request, ExcelModel.class);

        if(res.getStatusCode() == HttpStatus.OK) {
            workbook = res.getBody();
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=otpregister_"+ calid + ".xlsx");
        try {
            WriteRegister(workbook.getData(),response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }

        return  null;
    }


    public void WriteRegister(List<Map<String, Object>> data,ServletOutputStream stream) throws IOException {
        Set<String> keysets = data.get(0).keySet();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet 1");


        int rowNum = 0;
        int colNum = 0;
        Row row = sheet.createRow(rowNum++);


        for (String str : keysets) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(str);
        }


        for (Map<String, Object> dt : data) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            keysets = dt.keySet();
            for (String str : keysets) {
                Cell cell = row.createCell(colNum);

                // colNum == 4 || colNum == 5 || colNum == 6
                if(colNum == 0 || colNum == 1 || colNum == 2 )
                {
                    cell.setCellValue(dt.get(str).toString());
                }
                else
                {
                    cell.setCellType(CellType.NUMERIC);

                    double d=Double.parseDouble(dt.get(str).toString());
                    cell.setCellValue(d);
                    //System.out.println("value: "+d);
                }
                colNum++;
            }
        }
        workbook.write(stream);
    }

    public List<Map<String, Object>> readExcelFile(InputStream input) throws IOException {
        List<String> headers = new ArrayList<>();
        headers.clear();
        List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
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
                if(hflag) {
                    headers.add(cell.getStringCellValue());
                } else {
                    if(cell.getCellType() == CellType.NUMERIC) {
                        //System.out.println("pre");
                        map.put(headers.get(counter).replace(" ", ""), cell.getNumericCellValue());
                        //System.out.println("Post"  +  cell.getNumericCellValue());
                    }
                    else
                        map.put(headers.get(counter).replace(" ",""), cell.getStringCellValue());
                    counter++;
                }

            }
            if(!hflag)
                data.add(map);
            hflag = false;
        }
        return data;
    }

    @ResponseBody
    @PostMapping(value = "/payrollprocessing/uploadopt/{calid}")
    public String uploadOTP(@PathVariable("calid") String calid, @RequestParam("filename") MultipartFile filename) throws IOException {

        if(filename.isEmpty()) {
            return "failed";
        }

//        System.out.println(filename.getOriginalFilename());

        List<Map<String, Object>> data = readExcelFile(filename.getInputStream());

        Map<String, String> res = new HashMap<>();
        String successMessage = "Employee Identified Successfully.";
        String errorMessage = "There was and error in employee identification.";

        String url = appgateway.getAppgateway_payroll()+"/api/calculation/uploadotpdata/" + calid;
        HttpEntity<List<Map<String, Object>>> request  = new HttpEntity<List<Map<String, Object>>>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            res.put("message", successMessage);
        } else {
            res.put("message", errorMessage);
        }
        return "success";
    }

}
