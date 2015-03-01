package com.h4l9000.recover.modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ModUtiliteInspection {

	private String date_inspection = "";
	private String date_acces_ech = "";
	private String date_pro = "";
		
	public ModUtiliteInspection(String strDateInspection, String strDateAccesEch, String strDatePro){
		
		date_inspection = strDateInspection;
		date_acces_ech = strDateAccesEch;
		date_pro = strDatePro;
		
	}
	
	public String getUtilite(){
		
		String resultat = "";
		
		String rentree_acces_ech = "";
		String rentree_pro = "";
		
		try {
			if ((date_inspection.compareTo("")!=0) && (date_acces_ech.compareTo("")!=0) && (date_pro.compareTo("")!=0)){
				
				// Gestion de la date du jour
				GregorianCalendar calendar = new GregorianCalendar(Locale.FRANCE);
				calendar.setFirstDayOfWeek(Calendar.MONDAY);
				calendar.setMinimalDaysInFirstWeek(4);
				
				ModFormatDate fd = new ModFormatDate();
				String format_date_insp = fd.getFormatDate(date_inspection);
				
//				System.out.println("Inspection : " + format_date_insp);
				
				Date format_date_acces_ech = new SimpleDateFormat("dd/MM/yyyy").parse(date_acces_ech);
				Date format_date_pro = new SimpleDateFormat("dd/MM/yyyy").parse(date_pro);
				
				// --- Traitement de la date d'accès ech ---
				calendar.setTime(format_date_acces_ech);
				
				if (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER){
					calendar.add(Calendar.YEAR, -1);				
				}
				
				rentree_acces_ech = calendar.get(Calendar.YEAR) + "-09-01";
				
//				System.out.println("Date Acces Ech = " + date_acces_ech + " / Rentrée " + rentree_acces_ech);
				
				// --- Traitement de la date de promotion
				calendar.setTime(format_date_pro);
				
				if (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER){
					calendar.add(Calendar.YEAR, -1);				
				}
				
				rentree_pro = calendar.get(Calendar.YEAR) + "-09-01";
				
//				System.out.println("Date Pro = " + date_pro + " / Rentrée " + rentree_pro);
				
				if ((rentree_acces_ech.compareTo(format_date_insp)<0) && (rentree_pro.compareTo(format_date_insp)>0)){
					resultat = "OUI";
				} else {
					resultat = "NON";
				}
				
//				System.out.println("Comparaison ." + rentree_acces_ech + ".  ." + format_date_insp + ".  ." + rentree_pro + ".");
				
			} else {
				resultat = "NA";
			}
			
		} catch (Exception ex){
			System.out.println("### Erreur ModeYtiliteInspection " + ex.toString());
		}
		
		return resultat;
	}
}
