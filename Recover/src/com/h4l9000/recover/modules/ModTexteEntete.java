package com.h4l9000.recover.modules;

import com.h4l9000.recover.structures.StrucTexteEntete;

public class ModTexteEntete {

	private StrucTexteEntete entete = null;
		
	public ModTexteEntete(String strMode){
		entete = new StrucTexteEntete(strMode);	
	}
	
	public String getAcademie(String strEntete1){
		String resultat = "";
		
		resultat = strEntete1.substring(entete.getAcademieDebut(), entete.getAcademieFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getPage(String strEntete1){
		String resultat = "";
		
		for (int i=entete.getPageDebut(); i<strEntete1.length(); i++){
			resultat = resultat + strEntete1.charAt(i);
		}
		
		return resultat;
	}
	
	public String getCorps(String strEntete2){
		String resultat = "";
		
		resultat = strEntete2.substring(entete.getCorpsDebut(), entete.getCorpsFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateFichier(String strEntete3){
		String resultat = "";
		
		resultat = strEntete3.substring(entete.getDateFichierDebut(), entete.getDateFichierFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateMinPeriode(String strEntete3){
		String resultat = "";
		
		resultat = strEntete3.substring(entete.getPeriodeMinDebut(), entete.getPeriodeMinFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateMaxPeriode(String strEntete4){
		String resultat = "";
		
		resultat = strEntete4.substring(entete.getPeriodeMaxDebut(), entete.getPeriodeMaxFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getEchelon(String strEntete5){
		String resultat = "";
		
		resultat = strEntete5.substring(entete.getEchelonDebut(), entete.getEchelonFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
}
