package in.co.srdt.unif.controllers.reports;

public class ConvertNumToWord {
	  private final String[] units = {
	    "",
	    " One",
	    " Two",
	    " Three",
	    " Four",
	    " Five",
	    " Six",
	    " Seven",
	    " Eight",
	    " Nine"
	  }; 
	  private final String[] twoDigits = {
	    " Ten",
	    " Eleven",
	    " Twelve",
	    " Thirteen",
	    " Fourteen",
	    " Fifteen",
	    " Sixteen",
	    " Seventeen",
	    " Eighteen",
	    " Nineteen"
	  };
	  private final String[] tenMultiples = {
	    "",
	    "",
	    " Twenty",
	    " Thirty",
	    " Forty",
	    " Fifty",
	    " Sixty",
	    " Seventy",
	    " Eighty",
	    " Ninety"
	  };
	  private final String[] placeValues = {
	    "",
	    " Thousand",
	    " lakh",
	    " crore",
	    " arab",
	    " kharab"
	  };
	        
	  public String convertNumber(long number) {    
	    String word = "";    
	    int index = 0;
	    boolean firstIteration = true;
	    int divisor;
	    do {
	      divisor = firstIteration ? 1000 : 100;
	      // take 3 or 2 digits based on iteration
	      int num = (int)(number % divisor);          
	      if (num != 0){
	        String str = ConversionForUptoThreeDigits(num);
	        word = str + placeValues[index] + word;
	      }
	      index++;
	      // next batch of digits
	      number = number/divisor;
	      firstIteration = false;
	    } while (number > 0);
	    return word;
	  }
	    
	  private String ConversionForUptoThreeDigits(int number) {
	    String word = "";    
	    int num = number % 100;
	    if(num < 10){
	      word = word + units[num];
	    }
	    else if(num < 20){
	      word = word + twoDigits[num%10];
	    }else{
	      word = tenMultiples[num/10] + units[num%10];
	    }
	    
	    word = (number/100 > 0)? units[number/100] + " hundred" + word : word;
	    return word;
	  }
	    
//	  public static void main(String[] args) {
//	    System.out.println("1234123456789- " + convertNumber(1234123456789L));
//	    System.out.println("123456789- " + convertNumber(123456789));
//	    System.out.println("37565820- " + convertNumber(37565820));
//	    System.out.println("9341947- " + convertNumber(9341947));
//	    System.out.println("37000- " + convertNumber(37000));
//	    System.out.println("1387- " + convertNumber(1387));
//	    System.out.println("10- " + convertNumber(10));
//	    System.out.println("41- " + convertNumber(41));
//	  }
	}
