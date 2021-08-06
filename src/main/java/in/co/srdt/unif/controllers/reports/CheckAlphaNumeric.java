package in.co.srdt.unif.controllers.reports;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckAlphaNumeric {

	boolean flag=true;
	public boolean isAlphaNumeric(String str)
	{
		str=str.replaceAll(" ", "");
		str=str.replaceAll(".", "");
		System.out.println("STR====> "+str);
		// Regex to check string is alphanumeric or not.
		String regex = "^(?=.*[a-zA-Z])\\.?(?=.*[0-9])\\.?[A-Za-z0-9]+$";
//		String regex = "^[A-Za-z][A-Za-z0-9]*(\\.[A-Za-z0-9]+)?$";
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the string is empty
		// return false
		if (str == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given string
		// and regular expression.
		Matcher m = p.matcher(str);

		// Return if the string
		// matched the ReGex
		return m.matches();
	}
	
	public boolean ifAlphaNumeric(String str)
	{
		char pnostr[] = str.toCharArray();
		
		for(char c : pnostr)
		{
			//System.out.println("Character ::: "+c);
			if(Character.isDigit(c))
			{
				flag =true;
//				System.out.println("FLAG ====>"+flag);
				break;
			}
			else
			{
				flag=false;
//				System.out.println("FLAG ====>"+flag);
				break;
			}
		}
//		System.out.println("FLAG :::: "+flag);
		return flag;
	}
}
