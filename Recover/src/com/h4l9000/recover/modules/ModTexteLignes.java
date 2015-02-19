package com.h4l9000.recover.modules;

import com.h4l9000.recover.structures.StrucTexteLignes;

public class ModTexteLignes {

	private StrucTexteLignes lignes = null;
	
	public ModTexteLignes(String strMode){
		lignes = new StrucTexteLignes(strMode);	
	}
	
	// --- Ligne 1 ---
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
	
	// --- Ligne 2 ---
	public String getPrenom(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getPrenomDebut(), lignes.getPrenomFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateNaissance(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getDateNaissanceDebut(), lignes.getDateNaissanceFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getNoteInspection(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getNoteInspectionDebut(), lignes.getNoteInspectionFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateInspection(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getDateInspectionDebut(), lignes.getDateInspectionFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getModeAccesEch(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getModeAccesEchDebut(), lignes.getModeAccesEchFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getReportAnciennete(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getReportAncienneteDebut(), lignes.getReportAncienneteFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getProCh(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getProChDebut(), lignes.getProChFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateCh(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getDateChDebut(), lignes.getDateChFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getAsaCh(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getAsaChDebut(), lignes.getAsaChFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	// --- Ligne 3 ---
	public String getCodeDiscipline(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getCodeDisciplineDebut(), lignes.getCodeDisciplineFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getProAn(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getProAnDebut(), lignes.getProAnFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getDateAn(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getDateAnDebut(), lignes.getDateAnFin());
		resultat = resultat.trim();
		
		return resultat;
	}
	
	public String getAsaAn(String strLigne2){
		String resultat = "";
		
		resultat = strLigne2.substring(lignes.getAsaAnDebut(), lignes.getAsaAnFin());
		resultat = resultat.trim();
		
		return resultat;
	}
}
