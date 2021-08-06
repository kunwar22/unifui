package in.co.srdt.unif.controllers.notices;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.notices.Notice;
import in.co.srdt.unif.model.search.DepartmentSearch;
import in.co.srdt.unif.model.search.PersonRecordSearch;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/notices")
public class NoticesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appgateway;


    @RequestMapping("/managenotices")
    public String managenotices(Model model, HttpServletRequest req, String respon) {

        Login login = (Login) req.getSession().getAttribute("login");
        //System.out.println("fddfds "+login);

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        CommonLOV[] noticetype=null;
        String urlnottype = appgateway.getAppgateway()+"/General/loadNoticeType";
        ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlnottype, HttpMethod.GET, request, CommonLOV[].class);
        if(response.getStatusCode() == HttpStatus.OK) {
            noticetype = response.getBody();
        }
        model.addAttribute("noticetype", noticetype);

        DepartmentSearch[] departsearch = null;
        String urdept = appgateway.getAppgateway()+"/Departments/DepartmentsSearchList";
        String payLode = "{" +
                "\"name\"" + ":\"\"," +
                "\"code\"" + ":\"\"," +
                "\"dataset\"" + ":\"\"," +
                "\"status\"" + ":\"\"" +
                "}";
        HttpEntity<String> request1 = new HttpEntity<String>(payLode, headers);
        ResponseEntity<DepartmentSearch[]> response1 = restTemplate.exchange(urdept, HttpMethod.POST, request1, DepartmentSearch[].class);
        if(response.getStatusCode() == HttpStatus.OK) {
            departsearch = response1.getBody();
        }
        model.addAttribute("department",departsearch);

        Notice[] notices=null;
        String urlnotice = appgateway.getAppgatewaypyrl_sandhya()+"/publis";
        ResponseEntity<Notice[]> response2 = restTemplate.exchange(urlnotice, HttpMethod.GET, request, Notice[].class);
        if(response2.getStatusCode() == HttpStatus.OK) {
            notices = response2.getBody();
        }
        model.addAttribute("notices", notices);

        model.addAttribute("status", Status.values());

        model.addAttribute("res", respon);

        if(login==null) {
            return "login :: loginnotice";
        }
        else {
            return "fragments/notices/notice :: notice";
        }
    }

    @RequestMapping("/viewnotices")
    public String viewNotice(Model model, HttpServletRequest req) {
        return "fragments/notices/noticeview :: noticeview";
    }



    @ResponseBody
    @PostMapping(path="/getallnotices")
    public Notice[] getAllNotices(HttpServletRequest requestFromDT) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        Notice[] notices=null;
        String urlnotice = appgateway.getAppgatewaypyrl_sandhya()+"/publis";
        ResponseEntity<Notice[]> response2 = restTemplate.exchange(urlnotice, HttpMethod.GET, request, Notice[].class);
        if(response2.getStatusCode() == HttpStatus.OK) {
            notices = response2.getBody();
        }
        return notices;
    }

    @RequestMapping(value = "/saveNotice", method = RequestMethod.POST)
    public String saveNotice(@RequestParam("file") MultipartFile file, Notice noticeSave, HttpServletRequest request, Model model)
    {

        System.out.println("Inside Notice Save Controller "+ file);
        Login login=(Login)request.getSession().getAttribute("login");

        String location = "/EmployeeDocs/OfficeCirculars";
        String filePath = new File("").getAbsolutePath()+File.separator+"/EmployeeDocs/OfficeCirculars";
        String storePath=location;

        if(!file.isEmpty()) {
            //CREATE DIRECTORY IF NOT EXISTS
            File dir = new File(filePath);
            if( !dir.exists() )
            {
                dir.mkdirs();
            }

            //WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

            try
            {

                String empl=login.getEmplid();


                String extension =  FilenameUtils.getExtension( file.getOriginalFilename() );
                String ordernum = noticeSave.getOrderno().replace("/","-");
                String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"+"'"+ordernum+"."+extension+"'").format(new Date());
                //String generatedFileName ="test.pdf";
                File target = new File(filePath+File.separator+generatedFileName);
                int readByteCount = 0;
                byte[] buffer = new byte[20480];

                BufferedInputStream in= new BufferedInputStream(file.getInputStream());
                FileOutputStream out = new FileOutputStream(target);
                while( (readByteCount = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, readByteCount);
                }

                storePath += "/"+generatedFileName;


                noticeSave.setFileattachement(storePath);

            }
            catch(IOException e)
            {

                e.printStackTrace();
                model.addAttribute("res", "IOEXCEPTION");
                return managenotices(model,request,"IOEXCEPTION");
            }
            catch(IllegalArgumentException e)
            {

                e.printStackTrace();
                model.addAttribute("res", "ILLEGALARG");
                return managenotices(model,request,"ILLEGALARG");
            }
            catch(Exception e)
            {

                e.printStackTrace();
                model.addAttribute("res", "WRITE_ERROR");
                return managenotices(model,request,"WRITE_ERROR");
            }

        }else {
            noticeSave.setFileattachement(storePath);
        }
        noticeSave.setCreatedby(login.getEmplid());
        noticeSave.setOrderno(noticeSave.getOrderno().replace("/","-"));
        System.out.println(noticeSave.toString());
        String url =appgateway.getAppgatewaypyrl_sandhya()+"/publish";

        SingleResponseModel msg = null;
        HttpEntity<Notice> request1 = new HttpEntity<Notice>(noticeSave,headers);
        ResponseEntity<SingleResponseModel> response= restTemplate.exchange(url ,HttpMethod.POST,request1, SingleResponseModel.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            msg = response.getBody();
        } else {
            //  System.out.println(response.getStatusCode());
            return "LOG_ERROR";
        }

        storePath = location;
        //model.addAttribute("res", msg.getStatus());
        return managenotices(model,request,msg.getStatus());
    }

}
