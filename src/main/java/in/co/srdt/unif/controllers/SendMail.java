package in.co.srdt.unif.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import in.co.srdt.unif.model.approvalnotification.ApproverDetails;
import in.co.srdt.unif.utils.AES;
import in.co.srdt.unif.utils.ApplicationGateway;
import in.co.srdt.unif.utils.MailProperties;
import in.co.srdt.unif.utils.MailerInfo;
import in.co.srdt.unif.utils.Secret;

@Service
public class SendMail {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
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
	
	public void sendnotificationmail(ApproverDetails apprdetails, String uname, String upno, String reimbname) throws AddressException, MessagingException, IOException
		{
			//System.out.println("Inside Notification Sendmail");

			if(apprdetails.getSubmittedtopersonemail()==null || apprdetails.getSubmittedtopersonemail()=="" || apprdetails.getSubmittedtopersonemail().equals("")){
				return;
			}

			Session session = Session.getInstance(props.getProperties(), new javax.mail.Authenticator(){
				  protected PasswordAuthentication getPasswordAuthentication(){
					 return new PasswordAuthentication(mailerInfo.getMailid(), mailerInfo.getMailpass());
				  }
				});

			/*System.out.println(apprdetails.getSubmittedtopersonemail());
			System.out.println(apprdetails.getSubmittedtopersonname());
			System.out.println(uname);
			System.out.println(upno);
			System.out.println(reimbname);*/
			//String encr = AES.encrypt(uname, secret.getPassResetKey());
			
			//encr = encr.replaceAll("=", "EQUALS");
			//encr = encr.replaceAll("/", "BSLASH");
			//encr = encr.replaceAll("[+]", "PLUS");
			//encr = encr.replaceAll("[%]", "PERCENT");
			
			String url= mailsendingapi+ "/selfservice";
			
			MimeMessage msg = new MimeMessage(session);
			MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		    
			Context thymeleafContext = new Context();
			thymeleafContext.setVariable("apprname", apprdetails.getSubmittedtopersonname());
			thymeleafContext.setVariable("name", uname);
			thymeleafContext.setVariable("link", url);
			thymeleafContext.setVariable("userperson", upno);
			thymeleafContext.setVariable("reimbname", reimbname);
		    String htmlBody = thymeleafTemplateEngine.process("mail/approvalnotification.html", thymeleafContext);
		    
		    helper.setText(htmlBody,true);
		    //helper.addInline("mygurujilogo.png", new ClassPathResource("/static/images/mailtemplate/myGurujiLogo.png"));
		    
		    helper.setFrom(mailerInfo.getMailid());
		    helper.setTo(InternetAddress.parse(apprdetails.getSubmittedtopersonemail()));
		    //helper.setTo(InternetAddress.parse("shantanusrivastava.srdt@gmail.com"));
		    helper.setSubject("Your approval required.");
		    helper.setSentDate(new Date());
			Transport.send(msg);
	}

	public void sendrejectionmail(String emailid, String uname, long claimid, String reimbname) throws AddressException, MessagingException, IOException
	{
		//System.out.println("Inside Notification Sendmail");

		if(emailid==null || emailid.equals("")){
			return;
		}

		Session session = Session.getInstance(props.getProperties(), new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(mailerInfo.getMailid(), mailerInfo.getMailpass());
			}
		});

		String url= mailsendingapi+ "/selfservice";

		MimeMessage msg = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

		Context thymeleafContext = new Context();
		//thymeleafContext.setVariable("apprname", apprdetails.getSubmittedtopersonname());
		thymeleafContext.setVariable("name", uname);
		thymeleafContext.setVariable("link", url);
		thymeleafContext.setVariable("claimid", claimid);
		thymeleafContext.setVariable("reimbname", reimbname);
		String htmlBody = thymeleafTemplateEngine.process("mail/rejectnotification.html", thymeleafContext);

		helper.setText(htmlBody,true);
		//helper.addInline("mygurujilogo.png", new ClassPathResource("/static/images/mailtemplate/myGurujiLogo.png"));

		helper.setFrom(mailerInfo.getMailid());
		helper.setTo(InternetAddress.parse(emailid));
		//helper.setTo(InternetAddress.parse("shantanusrivastava.srdt@gmail.com"));
		helper.setSubject("Your reimbursement request rejected.");
		helper.setSentDate(new Date());
		Transport.send(msg);
	}
}
