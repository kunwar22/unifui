package in.co.srdt.unif.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailerInfo
{
	@Value("${in.co.srdt.unif.mailid}")
	private String mailid;
	
	@Value("${in.co.srdt.unif.mailpass}")
	private String mailpass;

	public String getMailid()
	{
		return mailid;
	}
	public String getMailpass()
	{
		return mailpass;
	}
}