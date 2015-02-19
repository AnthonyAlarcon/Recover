package com.h4l9000.recover.modules;

import com.h4l9000.recover.structures.StrucTexteLignes;

public class ModTexteLignes {

	private StrucTexteLignes lignes = null;
	
	public ModTexteLignes(String strMode){
		lignes = new StrucTexteLignes(strMode);	
	}
	
	public String getNom(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getNomDebut(), lignes.getNomFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getRne(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getRneDebut(), lignes.getRneFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getTypeEtablissement(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getTypeEtablissementDebut(), lignes.getTypeEtablissementFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getAgs(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getAgsDebut(), lignes.getAgsFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateAccesEch(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getDateAccesEchDebut(), lignes.getDateAccesEchFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getProGc(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getProGcDebut(), lignes.getProGcFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateGc(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getDateGcDebut(), lignes.getDateGcFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getAsaGc(String strLigne1){
		String resultat = "";
		
		resultat = strLigne1.substring(lignes.getAsaGcDebut(), lignes.getAsaGcFin());
		resultat = resultat.trim();
		
		return resultat;
	}
}
