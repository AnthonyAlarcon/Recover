package com.h4l9000.recover.modules;

import java.text.SimpleDateFormat;

public class ModFormatDate {
	
	public ModFormatDate(){
		
	}
	
	public String getFormatDate(String strDate){
		
		String resultat = "";
		
		if (strDate.length()==10){
			
			resultat = strDate.substring(6, 10) + "-" + strDate.substring(3, 5) + "-" + strDate.substring(0, 2);
		
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				sdf.setLenient(false);
				sdf.parse(resultat);
				
			} catch (Exception ex){
				resultat = "2060-01-01";
			}
			
		} else{
			resultat = "2070-01-01";
		}
		
		return resultat;
	}

}