package in.co.srdt.unif.controllers;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import in.co.srdt.unif.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


import in.co.srdt.unif.model.UserCredential;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.login.Role;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.AES;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;
import in.co.srdt.unif.utils.MailProperties;
import in.co.srdt.unif.utils.MailerInfo;
import in.co.srdt.unif.utils.Secret;


@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appGateway;

    @Autowired
	SpringTemplateEngine thymeleafTemplateEngine;
    
	@Autowired
	MailerInfo mailerInfo;
	
	@Autowired
	Secret secret;
	
	@Autowired
	MailProperties props;
	
	@Value("${in.co.srdt.unif.localurl}")
	private String mailsendingapi;

    @GetMapping("/login")
    public String userLogin(Model model) {
        UserCredential userCredential = new UserCredential();
        model.addAttribute("userCredential", userCredential);
        model.addAttribute("isInvalid", "N");
        return "login";
    }

    @PostMapping("/login")
    public String validateUser(UserCredential credential, HttpServletRequest request, Model model) {

        String requestedAccess = credential.getUsertype();
        //System.out.println(requestedAccess);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String access_token_url = appGateway.getAppgatewaynbr()+"/api/userlogin/validateuser";
        //System.out.println("url:::"+access_token_url);
        HttpEntity<UserCredential> credentialString = new HttpEntity<UserCredential>(credential, headers);
        ResponseEntity<Login> responseJson = restTemplate.exchange(access_token_url, HttpMethod.POST, credentialString, Login.class);
        Login login = responseJson.getBody();
        
        //System.out.println(login.getId()+"--"+login.getEmplid()+"--"+login.getAccess_tocken());

        if(login == null){
            model.addAttribute("isInvalid", "Y");
            return "login";
        }

        String getRolesUrl = appGateway.getAppgatewaynbr()+"/api/role/getrolesbyloginid/"+login.getLoginid()+"";
        AppConstants.ACCESS_TOKEN ="Bearer "+login.getAccess_tocken();
        headers.add("Authorization", "Bearer " + login.getAccess_tocken());
        
       // System.out.println("TOKEN : "+login.getAccess_tocken());
        
        //headers.setBasicAuth(login.getAccess_tocken());
        ///System.out.println("TOKEN ::"+login.getAccess_tocken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> rolereq = new HttpEntity<String>(headers);

        ResponseEntity<Role[]> roleres = restTemplate.exchange(getRolesUrl, HttpMethod.GET, rolereq, Role[].class);

        List<Role> roles = Arrays.asList(roleres.getBody());
/* comment by rajat on 11-11-2020 
        String roleNames = "";
        for (Role role: roles) {
            roleNames += roleNames + role.getRolename() + "$";
        }
        */
        
        /**ADDED BY UTSAV FOR SIDEBAR DISPLAY ACCORDING TO ROLES**/	  
        String roleNames = "";
        String rolesID="";	
        for (Role role: roles) {	
        	//System.out.println("ROLEID ::"+role.getRoleId());	
        	rolesID+=role.getRoleId()+",";	
            roleNames +=  role.getRolename() + ""	
            		+ "";	
        }	
        	
       // System.out.println("ROLES :: "+ roleNames);	
       // System.out.println("ROLESIDSTRING :: "+ rolesID);	
        	
      
        request.getSession().setAttribute("allroles", roles);	
        /*********************************************************/	

        request.getSession().setAttribute("roles", roleNames);
        request.getSession().setAttribute("login", login);

        boolean isSelfServiceUser = false;
        boolean isAdminUser = false;

        for(int i = 0; i <= roles.size()-1; i++){
            if(roles.get(i).getRolename().contentEquals("HP_SelfService")){
                isSelfServiceUser = true;

            }
            if(roles.get(i).getRolename().contentEquals("D_Admin")){
                isAdminUser = true;
            }
        }

        if(requestedAccess.equals("selfservice") && isSelfServiceUser){
            return "redirect:/selfservice";
        } else if(requestedAccess.equals("selfservice") && isSelfServiceUser){
            model.addAttribute("isInvalid", "Y");
            return "login";
        } else if(requestedAccess.equals("admin") && isAdminUser){
            return "redirect:/home";
        } else if(requestedAccess.equals("admin") && isAdminUser){
            model.addAttribute("isInvalid", "Y");
            return "login";
        } else {
            model.addAttribute("isInvalid", "Y");
            return "login";
        }
    }
    
    @RequestMapping("/logout")
    public String userlogout(HttpSession session, SessionStatus status, Model model) {
    	session.removeAttribute("roles");
    	session.removeAttribute("login");
    	session.invalidate();
    	status.setComplete();
    	
    	UserCredential userCredential=new UserCredential();
    	model.addAttribute("userCredential",userCredential);
    	model.addAttribute("flag",false);
    	return "redirect:/login";
    }
    
    
    /********************************code added by rajat start**************************************************/

    @RequestMapping("/changepwd")
	public String changePassword()
	{
		return "forms/login/changePwd :: changePwd";
	}
    
    @GetMapping("/forgotpass/{uname}/{who}")
	@ResponseBody
	public SingleResponseModel forgotPass(@PathVariable("uname") String uname, @PathVariable("who")String who)
	{
    	System.out.println("in forget"+uname+":::"+who);
		String superuser = "{\r\n" + 
				"	\"loginid\":\"E00000000001\",\r\n" +
				"	\"pwd\":\"123\"\r\n" + 
				"}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> restreq = new HttpEntity<String>(superuser,headers);
		String access_token_url = appGateway.getAppgatewaynbr() + "/api/userlogin/validateuser";
		System.out.println("access_token_url::"+access_token_url);
		ResponseEntity<Login> tokens = restTemplate.exchange(access_token_url, HttpMethod.POST, restreq, Login.class);
		Login login = tokens.getBody();
		
		
		if(who.equals("admin"))
		{
//			String facultysrch = "{\r\n" + 
//					"	\"facultyid\" : 0,\r\n" + 
//					"    \"facultycode\" : \"\",\r\n" + 
//					"    \"emplid\" : \""+uname+"\",\r\n" + 
//					"    \"designation\" : \"\",\r\n" + 
//					"    \"firstname\" : \"\",\r\n" + 
//					"    \"emailaddr\" : \"\",\r\n" + 
//					"    \"primarycontact\" : \"\"\r\n" + 
//					"}";
//			String userDataUrl = appGateway.getBaseUrl()+"/mg/api/faculty/search";
			String userDataUrl = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" +uname;
			System.out.println("userDataUrl::"+userDataUrl);
			headers.setBearerAuth(login.getAccess_tocken());
			//HttpEntity<String> restreq1 = new HttpEntity<String>(facultysrch,headers);
			
			//ResponseEntity<FacultyDetails[]> fac = restTemplate.exchange(userDataUrl, HttpMethod.POST, restreq1, FacultyDetails[].class);
			
			//FacultyDetails[] faculty = fac.getBody();
			
			PersonInformation personInfo =null;	
	        HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
			ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(userDataUrl,HttpMethod.GET, requestPesro,PersonInformation.class);
			
			if(responsePerso.getStatusCode() == HttpStatus.OK) {
				personInfo = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}
			
			
			SingleResponseModel res = new SingleResponseModel();
			try
			{
				System.out.println("personInfo Email id::"+personInfo.getEmailaid());
				//sendmail(uname, faculty[0].getEmailaddr());
				if(personInfo.getEmailaid()=="" || personInfo.getEmailaid()==null) {
					res.setMessage("EMAIL_NOT_FOUND");
					return res;
				}
				
				
				sendmail(uname, personInfo.getEmailaid());
			}
			catch (MessagingException | IOException e)
			{
				e.printStackTrace();
				res.setMessage("MAIL_FAIL");
				return res;
			}
			
			//res.setMessage(uname+"^"+faculty[0].getEmailaddr());
			res.setMessage(uname+"^"+personInfo.getEmailaid());
			return res;
		}
		else
		{
			System.out.println("else");
//			String studentsrch = "{\r\n" + 
//					"	\"studentid\" : 0,\r\n" + 
//					"    \"emplid\": \"\",\r\n" + 
//					"    \"applnbr\": \"\",\r\n" + 
//					"    \"campusid\": \""+uname+"\",\r\n" + 
//					"    \"firstname\": \"\",\r\n" + 
//					"    \"emailaddr\": \"\",\r\n" + 
//					"    \"primarycontact\": \"\"\r\n" + 
//					"}";
//			String userDataUrl = appGateway.getBaseUrl()+"/mg/api/student/search";
			String userDataUrl = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" +uname;
			headers.setBearerAuth(login.getAccess_tocken());
			//HttpEntity<String> restreq1 = new HttpEntity<String>(facultysrch,headers);
			
			//ResponseEntity<FacultyDetails[]> fac = restTemplate.exchange(userDataUrl, HttpMethod.POST, restreq1, FacultyDetails[].class);
			
			//FacultyDetails[] faculty = fac.getBody();
			
			PersonInformation personInfo =null;	
	        HttpEntity<String> requestPesro = new HttpEntity<String>(headers);		
			ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(userDataUrl,HttpMethod.GET, requestPesro,PersonInformation.class);
			
			if(responsePerso.getStatusCode() == HttpStatus.OK) {
				personInfo = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}
			
			
			SingleResponseModel res = new SingleResponseModel();
			try
			{
				//sendmail(uname, faculty[0].getEmailaddr());
				sendmail(uname, personInfo.getEmailaid());
			}
			catch (MessagingException | IOException e)
			{
				e.printStackTrace();
				res.setMessage("MAIL_FAIL");
				return res;
			}
			
			//res.setMessage(uname+"^"+faculty[0].getEmailaddr());
			res.setMessage(uname+"^"+personInfo.getEmailaid());
			return res;
		}
	}
    
  
    
    
    
    
    @GetMapping("/passwordreset/{emplid}")
	public String resetPass(@PathVariable("emplid") String emplid ,Model model)
	{
		emplid = emplid.replaceAll("EQUALS","=");
		emplid = emplid.replaceAll("BSLASH","/");
		emplid = emplid.replaceAll("PLUS","+");
		emplid = emplid.replaceAll("PERCENT","%");
		//System.out.println("RECEIVED "+emplid);
		emplid = AES.decrypt(emplid, secret.getPassResetKey());
		//System.out.println("DECRYPTED : "+emplid);
		
		if(emplid.equals(null) || emplid == null )
		{
			model.addAttribute("message1", "Error Occurred");
			model.addAttribute("message2", "Please contact the administrator.");
			return "forms/resetpassword/passResetMessage";
		}
		
		model.addAttribute("emplid", emplid);
		return "forms/resetpassword/reset";
	}

	@PostMapping("/resetresult")
	public String resetResult(UserId userId , HttpServletRequest request, Model model, RedirectAttributes redirectAttrs)
	{

		String payLoad = "{\"loginid\" : \""+request.getParameter("emplid")+"\","+
				"\"oldpwd\" : \"\","+
				"\"flag\" : \"reset\","+
				"\"newpwd\" : \""+request.getParameter("newpass")+"\"}";



		SingleResponseModel res = new SingleResponseModel();
		try
		{
			String superuser = "{\r\n" +
					"	\"loginid\":\"E00000000001\",\r\n" +
					"	\"pwd\":\"123\"\r\n" +
					"}";
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> restreq = new HttpEntity<String>(superuser,headers);
			String access_token_url = appGateway.getAppgatewaynbr()+ "/api/userlogin/validateuser";
			ResponseEntity<Login> tokens = restTemplate.exchange(access_token_url, HttpMethod.POST, restreq, Login.class);
			Login login = tokens.getBody();

			headers.setBearerAuth(login.getAccess_tocken());
			HttpEntity<String> request1 = new HttpEntity<String>(payLoad, headers);

			String url = appGateway.getAppgatewaynbr() + "/api/userlogin/changepwd";

			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(url,HttpMethod.POST,request1, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK)
			{
				res = response.getBody();
				//System.out.println("MSG1 : "+res.getMessage());
			}
			else
			{
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
				res.setMessage("ERROR");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			res.setMessage("ERROR");
		}

		if(res.getMessage().equals("failed"))
		{

			model.addAttribute("result",res.getMessage());
			return "forms/resetpassword/passResetMessage :: changeLoginPassword";
		}
		else if(res.getMessage().equals("ERROR"))
		{

			model.addAttribute("result",res.getMessage());
			return "forms/resetpassword/passResetMessage :: changeLoginPassword";
		}
		else
		{

			model.addAttribute("result",res.getMessage());
			System.out.println("msg:"+res.getMessage());

		}
		return "forms/resetpassword/passResetMessage :: changeLoginPassword";

	}

	
	private void sendmail(String uname, String emailaddr) throws AddressException, MessagingException, IOException
	{
		System.out.println(emailaddr);
		Session session = Session.getInstance(props.getProperties(), new javax.mail.Authenticator(){
			  protected PasswordAuthentication getPasswordAuthentication(){
				 return new PasswordAuthentication(mailerInfo.getMailid(), mailerInfo.getMailpass());
			  }
			});
		String encr = AES.encrypt(uname, secret.getPassResetKey());
		
		encr = encr.replaceAll("=", "EQUALS");
		encr = encr.replaceAll("/", "BSLASH");
		encr = encr.replaceAll("[+]", "PLUS");
		encr = encr.replaceAll("[%]", "PERCENT");
		
		String url= mailsendingapi+ "/passwordreset/"+encr;
		
		MimeMessage msg = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
	    
		Context thymeleafContext = new Context();
		thymeleafContext.setVariable("name", uname);
		thymeleafContext.setVariable("link", url);
	    String htmlBody = thymeleafTemplateEngine.process("mail/forgotpass.html", thymeleafContext);
	    
	    helper.setText(htmlBody,true);
	    //helper.addInline("mygurujilogo.png", new ClassPathResource("/static/images/mailtemplate/myGurujiLogo.png"));
	    
	    helper.setFrom(mailerInfo.getMailid());
	    helper.setTo(InternetAddress.parse(emailaddr));
	    //helper.setTo(InternetAddress.parse("shantanusrivastava.srdt@gmail.com"));
	    helper.setSubject("Password Reset Request");
	    helper.setSentDate(new Date());
	    
		Transport.send(msg);
	}
    /********************************code added by rajat end***************************************************/
}
