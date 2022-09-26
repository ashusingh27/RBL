package com.stepDefinition.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StepDefiniation {
	public static void main(String[] args) {
		/*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
*/
		
		String numberStr="01205";
		String regex="^0+(?!$)";
		numberStr = numberStr.replaceAll("^0+(?!$)", ""); 
		  
        System.out.println(numberStr); 
	}
}
