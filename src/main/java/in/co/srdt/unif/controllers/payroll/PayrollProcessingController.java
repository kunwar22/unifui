package in.co.srdt.unif.controllers.payroll;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.controllers.reports.CheckAlphaNumeric;
import in.co.srdt.unif.model.ExcelModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payroll.Element;
import in.co.srdt.unif.model.payroll.IdentifiedEmployees;
import in.co.srdt.unif.model.payroll.IdentifiedEmployeesWrapper;
import in.co.srdt.unif.model.payroll.IdentifiedPaygroupCalendar;
import in.co.srdt.unif.model.payroll.PayGroupCalendarModal;
import in.co.srdt.unif.model.payroll.PayGroups;
import in.co.srdt.unif.model.payroll.PayrollProcessedData;
import in.co.srdt.unif.model.payroll.PayrollRun;
import in.co.srdt.unif.model.payroll.PersonByOTPElement;
import in.co.srdt.unif.model.payroll.PersonByOTPElementWrapper;
import in.co.srdt.unif.model.payroll.SelectedPaygroupCalendar;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
public class PayrollProcessingController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    @Autowired
    ApplicationGateway appgateway;
    
    private final CheckAlphaNumeric alphanum = new CheckAlphaNumeric();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(768);
    }

    @GetMapping("/payrollprocessing/runpayroll")
    public String getRunPayrollPage() {
        return "forms/payroll/transactions/runPayroll :: runPayroll";
    }

    @GetMapping("/payrollprocessing/selectPaygroup")
    public String getPayrollGroupPage() {
        return "forms/payroll/transactions/selectPaygroup :: selectPaygroup";
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/selectPaygroup/paygroupcalendar")
    public PayGroupCalendarModal getPaygroupCalendar() {
        PayGroupCalendarModal payGroupCalendarModal = new PayGroupCalendarModal();

        PayGroups[] payGroups = null;

        String paygroupList = "<option>--Select Pay-Group--</option>";
        String calendarList = "<option>--First Select Pay-Group--</option>";

        String url = appgateway.getAppgateway_payroll() + "/api/payrollsetup/getpaygroups";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayGroups[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayGroups[].class);

        payGroups = response.getBody();
        for (int i = 0; i < payGroups.length; i++) {
            paygroupList += "<option value='" + payGroups[i].getPaygroupid() + "'>" + payGroups[i].getName() + "</option>";
        }

        payGroupCalendarModal.setPaygroupString(paygroupList);
        payGroupCalendarModal.setCalendarString(calendarList);

        return payGroupCalendarModal;
    }


    @ResponseBody
    @GetMapping("/payrollprocessing/selectPaygroup/paygroupcalendarbypaygroupid/{pygrpid}")
    public PayGroupCalendarModal getPaygroupCalendarByPaygroupId(@PathVariable("pygrpid")String pygrpid) {
        PayGroupCalendarModal payGroupCalendarModal = new PayGroupCalendarModal();

        CalendarPeriod[] calendarPeriods = null;

        String calendarList = "<option>--Select Calendar--</option>";

        String calurl = appgateway.getAppgateway_payroll() + "/api/paycalendar/getpayrollcalendarsbypaygroupid/"+pygrpid;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<CalendarPeriod[]> response1 = restTemplate.exchange(calurl, HttpMethod.GET, request, CalendarPeriod[].class);

        calendarPeriods = response1.getBody();
        for (int i = 0; i < calendarPeriods.length; i++) {
            calendarList += "<option value='" + calendarPeriods[i].getCalendarid() + "'>" + calendarPeriods[i].getCalendarcode() + "</option>";
        }

        payGroupCalendarModal.setCalendarString(calendarList);

        return payGroupCalendarModal;
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/selectPaygroup/_paygroupcalendar")
    public PayGroupCalendarModal _getPaygroupCalendar() {
        PayGroupCalendarModal payGroupCalendarModal = new PayGroupCalendarModal();

        PayGroups[] payGroups = null;
        CalendarPeriod[] calendarPeriods = null;

        SelectedPaygroupCalendar selectedPaygroupCalendar = new SelectedPaygroupCalendar();

        String paygroupList = "<option>--Select Pay-Group--</option>";
        String calendarList = "<option>--Select Calendar--</option>";

        String url = appgateway.getAppgateway_payroll() + "/api/payrollsetup/getpaygroups";
        String calurl = appgateway.getAppgateway_payroll() + "/api/paycalendar/getpayrollcalendars";

        String _calurl = appgateway.getAppgateway_payroll() + "/api/calculation/getcurrentcalendar";

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<PayGroups[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayGroups[].class);
        ResponseEntity<CalendarPeriod[]> response1 = restTemplate.exchange(calurl, HttpMethod.GET, request, CalendarPeriod[].class);
        ResponseEntity<SelectedPaygroupCalendar> response2 = restTemplate.exchange(_calurl, HttpMethod.POST, request, SelectedPaygroupCalendar.class);

        selectedPaygroupCalendar = response2.getBody();

        payGroups = response.getBody();
        for (int i = 0; i < payGroups.length; i++) {
            if (selectedPaygroupCalendar.getPaygroupid() != Long.parseLong(payGroups[i].getPaygroupid())) {
                paygroupList += "<option value='" + payGroups[i].getPaygroupid() + "'>" + payGroups[i].getName() + "</option>";
            } else {
                paygroupList += "<option selected value='" + payGroups[i].getPaygroupid() + "'>" + payGroups[i].getName() + "</option>";
            }
        }
        calendarPeriods = response1.getBody();
        for (int i = 0; i < calendarPeriods.length; i++) {
            if (selectedPaygroupCalendar.getCalendarid() != calendarPeriods[i].getCalendarid()) {
                calendarList += "<option value='" + calendarPeriods[i].getCalendarid() + "'>" + calendarPeriods[i].getCalendarcode() + "</option>";
            } else {
                calendarList += "<option selected value='" + calendarPeriods[i].getCalendarid() + "'>" + calendarPeriods[i].getCalendarcode() + "</option>";
            }
        }

        payGroupCalendarModal.setPaygroupString(paygroupList);
        payGroupCalendarModal.setCalendarString(calendarList);

        return payGroupCalendarModal;
    }

    @ResponseBody
    @PostMapping("/payrollprocessing/selectPaygroup/paygroupcalendar")
    public String addNewSalaryComponentTypes(IdentifiedPaygroupCalendar identifiedPaygroupCalendar) {
        HttpEntity<IdentifiedPaygroupCalendar> request = new HttpEntity<IdentifiedPaygroupCalendar>(identifiedPaygroupCalendar, headers);
        String url = appgateway.getAppgateway_payroll() + "/api/calculation/setcurrentcalendar";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Success";
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return "Error";
        }
    }


    @GetMapping("/payrollprocessing/identifyEmployee")
    public String getIdentifyEmployeePage() {
        return "forms/payroll/transactions/identifyEmployee :: identifyEmployee";
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/identifyEmployee/getIdentifiedEmployee/{payrollgroup}/{calid}")
    public IdentifiedEmployees[] getIdentifiedEmployee(@PathVariable("payrollgroup") long payrollgroup, @PathVariable("calid") long calid) {

        Map<String, String> res = new HashMap<>();
        //String employeeTableInfo = "";
        IdentifiedEmployees[] identifiedEmployees = null;

        StringBuilder sb = new StringBuilder();

        String url = appgateway.getAppgateway() + "/PearsonData/Pyaroll/loadAllPersonByPayGroupAndCalCode/" + payrollgroup + "/" + calid;

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<IdentifiedEmployees[]> response = restTemplate.exchange(url, HttpMethod.GET, request, IdentifiedEmployees[].class);

        identifiedEmployees = response.getBody();
        ///////////EDITED BY UTSAV TO DISPLAY DATA IN DATATABLE INSTEAD OF NORMAL TABLE\\\\\\\\\\\\\\
        /*if (identifiedEmployees.length > 0) {
            for (int i = 0; i < identifiedEmployees.length; i++) {

                sb.append("<tr>" +
                        "<td><input onclick='handleClick(this)' type='checkbox' class='w3-check' name='identifiedEmployees[" + i + "].status' value='on' checked></td>" +
                        "<td>" + identifiedEmployees[i].getPersonnumber() + "<input type='hidden' name='identifiedEmployees[" + i + "].personnumber' value='" + identifiedEmployees[i].getPersonnumber() + "'></td>" +
                        "<td>" + identifiedEmployees[i].getPersonname() + "<input type='hidden' name='identifiedEmployees[" + i + "].personname' value='" + identifiedEmployees[i].getPersonname() + "'></td>" +
                        "<td>" + identifiedEmployees[i].getHrstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].hrstatus' value='" + identifiedEmployees[i].getHrstatus() + "'></td>" +
                        "<td>" + identifiedEmployees[i].getPayrollstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].payrollstatus' value='" + identifiedEmployees[i].getPayrollstatus() + "'></td>" +
                        "<td>" + identifiedEmployees[i].getJob() + "<input type='hidden' name='identifiedEmployees[" + i + "].job' value='" + identifiedEmployees[i].getJob() + "'></td>" +
                        "<td>" + identifiedEmployees[i].getPosition() + "<input type='hidden' name='identifiedEmployees[" + i + "].position' value='" + identifiedEmployees[i].getPosition() + "'>" +
                        "<input type='hidden' name='identifiedEmployees[" + i + "].calendarid' value='" + calid + "'>" +
                        "</td>" +
                        "</tr>");
            }
        } else {
            System.out.println("No Data");
        }
        res.put("message", sb.toString());*/
        return identifiedEmployees;
    }

    @ResponseBody
    @RequestMapping(value = "/payrollprocessing/identifyEmployee/postIdentifiedEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> postIdentifiedEmployee(IdentifiedEmployeesWrapper identifiedEmployees) {
        Map<String, String> res = new HashMap<>();

        String successMessage = "Employee Identified Successfully.";
        String errorMessage = "There was and error in employee identification.";

        String url = appgateway.getAppgateway_payroll() + "/api/calculation/identifiedperson";

        //System.out.println(identifiedEmployees.getIdentifiedEmployees().toString());
        HttpEntity<List<IdentifiedEmployees>> request = new HttpEntity<List<IdentifiedEmployees>>(identifiedEmployees.getIdentifiedEmployees(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);
        } else {
            res.put("message", errorMessage);
        }

        return res;
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/identifyEmployee/_getIdentifiedEmployee/{calid}")
    public IdentifiedEmployees[] _getIdentifiedEmployee(@PathVariable("calid") long calid) {

        Map<String, String> res = new HashMap<>();
        String appendString = "";

        IdentifiedEmployees[] identifiedEmployees = null;

        StringBuilder sb = new StringBuilder();

        String url = appgateway.getAppgateway_payroll() + "/api/calculation/identifiedpersonbycalendarid/" + calid;

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<IdentifiedEmployees[]> response = restTemplate.exchange(url, HttpMethod.GET, request, IdentifiedEmployees[].class);

        identifiedEmployees = response.getBody();
        ///////////EDITED BY UTSAV TO DISPLAY DATA IN DATATABLE INSTEAD OF NORMAL TABLE\\\\\\\\\\\\\\
        /*
        if (identifiedEmployees.length > 0) {
            for (int i = 0; i < identifiedEmployees.length; i++) {
                if (identifiedEmployees[i].getStatus().equals("on")) {
                    appendString = "<tr>" +
                            "<td><input onclick='handleClick(this)' type='checkbox' class='w3-check' name='identifiedEmployees[" + i + "].status' value='on' checked></td>" +
                            "<td>" + identifiedEmployees[i].getPersonnumber() + "<input type='hidden' name='identifiedEmployees[" + i + "].personnumber' value='" + identifiedEmployees[i].getPersonnumber() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPersonname() + "<input type='hidden' name='identifiedEmployees[" + i + "].personname' value='" + identifiedEmployees[i].getPersonname() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getHrstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].hrstatus' value='" + identifiedEmployees[i].getHrstatus() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPayrollstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].payrollstatus' value='" + identifiedEmployees[i].getPayrollstatus() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getJob() + "<input type='hidden' name='identifiedEmployees[" + i + "].job' value='" + identifiedEmployees[i].getJob() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPosition() + "<input type='hidden' name='identifiedEmployees[" + i + "].position' value='" + identifiedEmployees[i].getPosition() + "'>" +
                            "<input type='hidden' name='identifiedEmployees[" + i + "].calendarid' value='" + calid + "'>" +
                            "</td>" +
                            "</tr>";
                } else {
                    appendString = "<tr>" +
                            "<td><input onclick='handleClick(this)' type='checkbox' class='w3-check' name='identifiedEmployees[" + i + "].status'></td>" +
                            "<td>" + identifiedEmployees[i].getPersonnumber() + "<input type='hidden' name='identifiedEmployees[" + i + "].personnumber' value='" + identifiedEmployees[i].getPersonnumber() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPersonname() + "<input type='hidden' name='identifiedEmployees[" + i + "].personname' value='" + identifiedEmployees[i].getPersonname() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getHrstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].hrstatus' value='" + identifiedEmployees[i].getHrstatus() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPayrollstatus() + "<input type='hidden' name='identifiedEmployees[" + i + "].payrollstatus' value='" + identifiedEmployees[i].getPayrollstatus() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getJob() + "<input type='hidden' name='identifiedEmployees[" + i + "].job' value='" + identifiedEmployees[i].getJob() + "'></td>" +
                            "<td>" + identifiedEmployees[i].getPosition() + "<input type='hidden' name='identifiedEmployees[" + i + "].position' value='" + identifiedEmployees[i].getPosition() + "'>" +
                            "<input type='hidden' name='identifiedEmployees[" + i + "].calendarid' value='" + calid + "'>" +
                            "</td>" +
                            "</tr>";
                }
                sb.append(appendString);
            }
        } else {
            System.out.println("No Data");
        }*/
        res.put("message", sb.toString());
        return identifiedEmployees;
    }

    @GetMapping("/payrollprocessing/manageOTP")
    public String getManageOtpPage() {
        return "forms/payroll/transactions/manageOtp :: manageOtp";
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/getOTPElements/{calid}/{flag}")
    public Map<String, String> getOtpElements(@PathVariable("calid") String calid, @PathVariable("flag") String flag) {
        Map<String, String> res = new HashMap<>();
        String elementString = "";

        Element[] element = null;

        StringBuilder sb = new StringBuilder();

        String url = appgateway.getAppgateway_payroll() + "/api/element/getoptelement/" + calid + '/' + flag;

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Element[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Element[].class);

        element = response.getBody();
        if (element.length > 0) {
            for (int i = 0; i < element.length; i++) {
                if (element[i].getCode().equals("true")) {
                    elementString = "<li onclick='otpEmployeeSelect(" + element[i].getElementid() + ",\"" + element[i].getName() + "\")' class='my-li-border' style='cursor: pointer' id='personalDetails'>" +
                            "<div class='w3-row'>" +
                            "<div id='check" + element[i].getElementid() + "' class='w3-col w3-right' style='width: 50px; display: inline-block;'><p class='my-p-margin' style='text-align: right;'>&#x2705;</p></div>" +
                            "<div id='uncheck" + element[i].getElementid() + "' class='w3-col w3-right' style='width: 50px; display: none'><p class='my-p-margin' style='text-align: right;'>&#x2753;</p></div>" +
                            "<div class='w3-rest'><p class='my-p-margin'>" + element[i].getName() + "</p></div>" +
                            "</div>" +
                            "</li>";
                } else {
                    elementString = "<li onclick='otpEmployeeSelect(" + element[i].getElementid() + ",\"" + element[i].getName() +  "\")' class='my-li-border' style='cursor: pointer' id='personalDetails'>" +
                            "<div class='w3-row'>" +
                            "<div id='check" + element[i].getElementid() + "' class='w3-col w3-right' style='width: 50px; display: none'><p class='my-p-margin' style='text-align: right;'>&#x2705;</p></div>" +
                            "<div id='uncheck" + element[i].getElementid() + "' class='w3-col w3-right' style='width: 50px; display: inline-block;'><p class='my-p-margin' style='text-align: right;'>&#x2753;</p></div>" +
                            "<div class='w3-rest'><p class='my-p-margin'>" + element[i].getName() + "</p></div>" +
                            "</div>" +
                            "</li>";
                }
                sb.append(elementString);
            }
        } else {
            System.out.println("No Data");
        }
        res.put("message", sb.toString());
        return res;
    }


    @ResponseBody
    @GetMapping("/payrollprocessing/getidentifiedpersonbyoptelement/{elementid}/{calendarid}")
    public Map<String, String> getidentifiedpersonbyoptelement(@PathVariable("elementid") String elementid, @PathVariable("calendarid") String calendarid) {

        Map<String, String> res = new HashMap<>();
        String appendString = "";

        PersonByOTPElement[] personByOTPElements = null;

        StringBuilder sb = new StringBuilder();

        String url = appgateway.getAppgateway_payroll() + "/api/calculation/getidentifiedpersonbyoptelement/" + elementid + "/" + calendarid + "/0";

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PersonByOTPElement[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PersonByOTPElement[].class);

        personByOTPElements = response.getBody();

        if (personByOTPElements.length > 0) {
            for (int i = 0; i < personByOTPElements.length; i++) {
                appendString = "<tr>" +
                        "<td><input type='hidden' name='personByOTPElements[" + i + "].personnumber' value='" + personByOTPElements[i].getPersonnumber() + "'>" + personByOTPElements[i].getPersonnumber() + "</td>" +
                        "<td><input type='hidden' name='personByOTPElements[" + i + "].personname' value='" + personByOTPElements[i].getPersonname() + "'>" + personByOTPElements[i].getPersonname() + "</td>" +
                        "<td>" +
                        "<input type='hidden' name='personByOTPElements[" + i + "].elementid' value='" + elementid + "'>" +
                        "<input type='hidden' name='personByOTPElements[" + i + "].calendarid' value='" + calendarid + "'>" +
                        "<input class='w3-text w3-border' style='text-align: right;' name='personByOTPElements[" + i + "].otpamt' value='" + personByOTPElements[i].getOtpamt() + "'>" +
                        "</td>" +
                        "</tr>";
                sb.append(appendString);
            }
        } else {
            System.out.println("No Data");
        }
        res.put("message", sb.toString());
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/payrollprocessing/setidentifiedpersonbyoptelement", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> postidentifiedpersonbyoptelement(PersonByOTPElementWrapper personByOTPElementWrapper) {
        Map<String, String> res = new HashMap<>();

        String successMessage = "Employee Identified Successfully.";
        String errorMessage = "There was and error in employee identification.";

        String url = appgateway.getAppgateway_payroll() + "/api/calculation/setotpdata";

        HttpEntity<List<PersonByOTPElement>> request = new HttpEntity<List<PersonByOTPElement>>(personByOTPElementWrapper.getPersonByOTPElements(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);
        } else {
            res.put("message", errorMessage);
        }
        return res;
    }


    @GetMapping("/payrollprocessing/executePayroll")
    public String getExecutePayrollPage() {
        return "forms/payroll/transactions/executePayroll :: executePayroll";
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/executePayroll/runpayroll/{calid}")
    public Map<String, String> runPayroll(@PathVariable("calid") String calid) {
        Map<String, String> res = new HashMap<>();
        String url = appgateway.getAppgateway_payroll() + "/api/runpayroll/run/" + calid;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayrollRun> response = restTemplate.exchange(url, HttpMethod.GET, request, PayrollRun.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("runcontrolid", response.getBody().getRunid());
        } else {
            res.put("runcontrolid", "Request Failed");
        }
        return res;
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/executePayroll/getrunstatus/{runcontrolid}/{calcodee}")
    public PayrollRun getrunstatus(@PathVariable("runcontrolid") String runcontrolid, @PathVariable("calcodee") String calcodee) {
        //Map<String, String> res = new HashMap<>();
        PayrollRun res=null;
        String url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getpayrollrunningstatus/" + runcontrolid+"/"+calcodee;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayrollRun> response = restTemplate.exchange(url, HttpMethod.GET, request, PayrollRun.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res= response.getBody();
        } else {
            res.setMessage("Request Failed");
            //res.put("runstatus", "    Request Failed");
        }
        if(res.getMessage().equals("success")){
            res.setMessage("Success");
        }
        //System.out.println("PayrollStatus "+res.toString());
        return res;
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/executePayroll/getpayrollprocesseddata/{calid}")
    public PayrollProcessedData[] getpayrollprocesseddata(@PathVariable("calid") String calid) {
        PayrollProcessedData[] payrollProcessedData = null;
        String url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getpayrollprocesseddata/" + calid;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayrollProcessedData[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayrollProcessedData[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            payrollProcessedData = response.getBody();
        }
        return payrollProcessedData;
    }

    @ResponseBody
    @GetMapping("/payrollprocessing/executePayroll/getelementwisepayrollprocesseddata/{personnumber}/{calid}")
    public Map<String, String> getelementwisepayrollprocesseddata(@PathVariable("personnumber") String personnumber, @PathVariable("calid") String calid) {
        Map<String, String> res = new HashMap<>();
        String elementValueString = "";
        PayrollProcessedData[] payrollProcessedData = null;
        StringBuilder sb = new StringBuilder();

        String url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getelementwisepayrollprocesseddata/" + calid + "/" + personnumber;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayrollProcessedData[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayrollProcessedData[].class);

        payrollProcessedData = response.getBody();
        if (payrollProcessedData.length > 0) {
            for (int i = 0; i < payrollProcessedData.length; i++) {
                if (payrollProcessedData[i].getPersonnumber().equals(personnumber)) {
                    elementValueString = "<tr>" +
                            "<td style='vertical-align: middle'>" + payrollProcessedData[i].getAbbreviation() + "</td>" +
                            "<td style='text-align: right;'>" +
                            "<input style='text-align: right; padding-top: 3px; padding-bottom: 3px;' class='w3-input w3-border' name='basicsalary' value='" + payrollProcessedData[i].getPayamt() + "'>" +
                            "</td>" +
                            "</tr>";
                    sb.append(elementValueString);
                    res.put("name", payrollProcessedData[i].getPersonname());
                }
            }
        }
        res.put("elementvalue", sb.toString());

        return res;
    }

    @GetMapping("/payrollprocessing/confirmPayroll")
    public String getConfirmPayrollPage() {
        return "forms/payroll/transactions/confirmPayroll :: confirmPayroll";
    }

    @GetMapping("/payrollprocessing/confirmPayroll/downloadpayrollregister/{calid}/{calcode}/{downloadfor}")
    public String getPayrollRegister(@PathVariable("calid") String calid,  @PathVariable("calcode") String calcode,@PathVariable("downloadfor") String downloadfor, HttpServletResponse response) {
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
       System.out.println("downloadfor:::"+downloadfor);
        String url="";
        if(downloadfor.equals("HR")) {        	
        	url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getpayrollregisterhr/" + calid;
        }else if(downloadfor.equals("FINANCE")) {
        	url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getpayrollregister/" + calid;
        	
        }
        
        System.out.println(url);

        //Map data[] = null;
        ExcelModel workbook = null;
        ResponseEntity<ExcelModel> res = restTemplate.exchange(url, HttpMethod.GET, request, ExcelModel.class);

        if (res.getStatusCode() == HttpStatus.OK) {
            workbook = res.getBody();
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=Payroll Register: " + calcode + ".xlsx");
        try {
            WriteRegister(workbook.getData(), response.getOutputStream(), calcode);
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }

    public void WriteRegister(List<Map<String, Object>> data, ServletOutputStream stream, String calcode) throws IOException {
        Set<String> keysets = data.get(0).keySet();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet 1");

        int rowNum = 0;
        int colNum = 0;
        System.out.println("Creating excel");
        Row row = sheet.createRow(rowNum++);

        XSSFFont TitleFont = workbook.createFont();
        TitleFont.setBold(true);
        TitleFont.setFontHeight(15);
        TitleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(TitleFont);

        Cell TitleCell = row.createCell(0);
        TitleCell.setCellValue("UPMRC Payroll Register For Calendar - " + calcode);

        TitleCell.setCellStyle(titleStyle);
        
        
        CellStyle redstyle = workbook.createCellStyle();
        redstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        redstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));
        
        XSSFFont headerFont = workbook.createFont();
        
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setFontHeight(10);
        CellStyle style = workbook.createCellStyle();

        row = sheet.createRow(rowNum++);

        Cell snocell = row.createCell(colNum++); // Done by asmita on 4-May-2021
        snocell.setCellValue("Sr. No");
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(headerFont);
        snocell.setCellStyle(style); // Done by asmita on 4-May-2021
        int i = 0;
        int cn=0; //Done By ASMITA 23-03-2021
        for (String str : keysets) {
        	sheet.setColumnWidth(i, 4000);

            if (str.contains("Projected") || str.contains("Perquisites")) {
                style.setFillForegroundColor(IndexedColors.ORCHID.getIndex());
                sheet.setColumnWidth(i, 4500);
            } else {
                style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            }

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
            style.setWrapText(true);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFont(headerFont);


            Cell cell = row.createCell(colNum++);
            cell.setCellValue(str);
            /* Done by Asmita 23-03-2021 STARTS */
            if(str.contains("Net Pay"))
            {
               cn=colNum-1;
            }
            /* Done by Asmita 23-03-2021 ENDS */
            cell.setCellStyle(style);
            i++;
        }
        
        CellStyle style1 = workbook.createCellStyle();
        style1.setBorderRight(BorderStyle.THIN);
        style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
        

        int cnt = 0;
        for (Map<String, Object> dt : data) {
            row = sheet.createRow(rowNum++);
            cnt++;
            colNum = 0;
            keysets = dt.keySet();
            
            Cell sncell = row.createCell(colNum++);// Done by asmita on 4-May-2021
            sncell.setCellValue(cnt);// Done by asmita on 4-May-2021
            
            for (String str : keysets) {
                XSSFFont cellFont = workbook.createFont();
                cellFont.setFontHeight(10);
                Cell cell = row.createCell(colNum);
                //cell.setCellValue(dt.get(str).toString());
                //|| colNum == 4  || colNum == 5 || colNum == 6
                /* Done by Asmita on 07-May-2021 Starts */
                if(colNum == 1)
                	// if(colNum == 0 || colNum == 1 || colNum == 2)
                {
					/*
					 * cell.setCellType(CellType.NUMERIC);
					 * cell.setCellValue(Integer.parseInt(dt.get(str).toString()));
					 */
                	/* done by Asmita on July 01 starts */
                	String coldata = dt.get(str).toString();
                	if (alphanum.ifAlphaNumeric(coldata)) {
						int pno = Integer.parseInt(coldata);
						cell.setCellType(CellType.NUMERIC);
						cell.setCellValue(pno);
					} else {
						String pno = coldata;
						cell.setCellType(CellType.STRING);
						cell.setCellValue(pno);
					}
					
					/* done by Asmita on July 01 ends */
                }
                else if(colNum == 2 || colNum == 3 || colNum == 4 || colNum == 5)
                	// if(colNum == 0 || colNum == 1 || colNum == 2)
                {
                	cell.setCellType(CellType.STRING);
                    cell.setCellValue(dt.get(str).toString());
                }
                else
                {
                    cell.setCellType(CellType.NUMERIC);

                    double d=Double.parseDouble(dt.get(str).toString());
                    cell.setCellValue(d);
                    //System.out.println("value: "+d);
                }
                /* Done by Asmita on 07-May-2021 Ends */
//                if(colNum == 0 || colNum == 1 || colNum == 2 || colNum == 3 || colNum == 4)
//                	// if(colNum == 0 || colNum == 1 || colNum == 2)
//                {
//                    cell.setCellValue(dt.get(str).toString());
//                }
//                else
//                {
//                    cell.setCellType(CellType.NUMERIC);
//
//                    double d=Double.parseDouble(dt.get(str).toString());
//                    cell.setCellValue(d);
//                    //System.out.println("value: "+d);
//                }
                /* DONE BY ASMITA 23-03-2021 STARTS */
              

                if(colNum==cn && cell.getNumericCellValue()<0)
                {
                    cell.setCellStyle(redstyle);
                }
                /* Done by Asmita 23-03-2021 ENDS */
                colNum++;
                /*if(cnt % 2 == 0){
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.LIME.index);
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
                    cell.setCellStyle(style);
                } else {*/
                style1.setFont(cellFont);
                cell.setCellStyle(style1);
                //}
            }
        }
        workbook.write(stream);
        System.out.println("File write successfully");
    }
    
    
    @GetMapping("/payrollprocessing/finalizePayroll")
    public String getFinalizePayrollPage() {
        return "forms/payroll/transactions/finalizePayroll :: finalizePayroll";
    }




    /****************added by rajat on 01-12-20*******start*********************/
    @ResponseBody
    @GetMapping("/payrollprocessing/confirmPayroll/lockpayroll/{calid}")
    public Map<String, String> lockPayroll(@PathVariable("calid") String calid, Model model) {
        Map<String, String> res = new HashMap<>();
        SingleResponseModel status = new SingleResponseModel();
        String urldates = appgateway.getAppgateway_payroll() + "/api/runpayroll/lockcalendar/" + calid;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urldates, HttpMethod.GET, request,
                SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("lockStatus", response.getBody().getMessage());
        }
        System.out.println("locked calender::");
        return res;
        //return "forms/payroll/transactions/confirmPayroll :: confirmPayroll";
    }


    @ResponseBody
    @GetMapping("/payrollprocessing/getPayrollStatus")
    public Map<String, String> lockPayrollStatus( Model model) {
        Map<String, String> res = new HashMap<>();
        SingleResponseModel status = new SingleResponseModel();
        String urldates = appgateway.getAppgateway_payroll() + "/api/runpayroll/getcalenderstatus";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urldates, HttpMethod.GET, request,
                SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("lockStatus", response.getBody().getMessage());
        }
        return res;

    }

/*****************added by rajat on 01-12-20*******end**********************/

}