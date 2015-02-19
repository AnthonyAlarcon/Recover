package com.h4l9000.recover.structures;

public class StrucTexteLignes {
	
	// --- Ligne 1 ---
	private int nom_debut = 0;
	private int nom_fin = 0;
	
	private int rne_debut = 0;
	private int rne_fin = 0;
	
	private int type_etablissement_debut = 0;
	private int type_etablissement_fin = 0;
	
	private int ags_debut = 0;
	private int ags_fin = 0;
	
	private int date_acces_ech_debut = 0;
	private int date_acces_ech_fin = 0;
	
	private int pro_gc_debut = 0;
	private int pro_gc_fin = 0;
	
	private int date_gc_debut = 0;
	private int date_gc_fin = 0;
	
	private int asa_gc_debut = 0;
	private int asa_gc_fin = 0;
	
	// --- Ligne 2 ---
	private int prenom_debut = 0;
	private int prenom_fin = 0;
	
	private int date_naissance_debut = 0;
	private int date_naissance_fin = 0;
	
	private int note_inspection_debut = 0;
	private int note_inspection_fin = 0;
	
	private int date_inspection_debut = 0;
	private int date_inspection_fin = 0;
	
	private int mode_acces_ech_debut = 0;
	private int mode_acces_ech_fin = 0;
	
	private int report_anciennete_debut = 0;
	private int report_anciennete_fin = 0;
	
	private int pro_ch_debut = 0;
	private int pro_ch_fin = 0;
	
	private int date_ch_debut = 0;
	private int date_ch_fin = 0;
	
	private int asa_ch_debut = 0;
	private int asa_ch_fin = 0;
	
	// --- Ligne 3 ---
	private int code_discipline_debut = 0;
	private int code_discipline_fin = 0;
	
	private int pro_an_debut = 0;
	private int pro_an_fin = 0;
	
	private int date_an_debut = 0;
	private int date_an_fin = 0;
	
	private int asa_an_debut = 0;
	private int asa_an_fin = 0;

	public StrucTexteLignes(String strModeAffectationValeurs){
		
		if(strModeAffectationValeurs.compareTo("DEFAUT")==0){
			setValue();
		} else {
			String result = loadValue(strModeAffectationValeurs);
			System.out.println(result);
		}
	}
	
	private void setValue(){
		
		// --- Ligne 1 ---
		nom_debut = 1;
		nom_fin = 31;
		
		rne_debut = 38;
		rne_fin = 46;
		
		type_etablissement_debut = 55;
		type_etablissement_fin = 58;
		
		ags_debut = 79;
		ags_fin = 88;
		
		date_acces_ech_debut = 96;
		date_acces_ech_fin = 106;
		
		pro_gc_debut = 110;
		pro_gc_fin = 113;
		
		date_gc_debut = 117;
		date_gc_fin = 127;
		
		asa_gc_debut = 127;
		asa_gc_fin = 131;
		
		// --- Ligne 2 ---
		prenom_debut = 8;
		prenom_fin = 25;
		
		date_naissance_debut = 26;
		date_naissance_fin = 36;
		
		note_inspection_debut = 74;
		note_inspection_fin = 79;
		
		date_inspection_debut = 82;
		date_inspection_fin = 92;
		
		mode_acces_ech_debut = 94;
		mode_acces_ech_fin = 97;
		
		report_anciennete_debut = 98;
		report_anciennete_fin = 107;
		
		pro_ch_debut = 110;
		pro_ch_fin = 113;
		
		date_ch_debut = 117;
		date_ch_fin = 127;
		
		asa_ch_debut = 127;
		asa_ch_fin = 131;
		
		// --- Ligne 3 ---
		code_discipline_debut = 75;
		code_discipline_fin = 80;
		
		pro_an_debut = 110;
		pro_an_fin = 113;
		
		date_an_debut = 117;
		date_an_fin = 127;
		
		asa_an_debut = 127;
		asa_an_fin = 131;
	}
	
	private String loadValue(String strFichier){
		
		String resultat = "";
		
		
		
		return resultat;
	}
	
	// --- Ligne 1 ---
	public int getNomDebut(){
		return nom_debut;
	}
	
	public int getNomFin(){
		return nom_fin;
	}
	
	public int getRneDebut(){
		return rne_debut;
	}
	
	public int getRneFin(){
		return rne_fin;
	}
	
	public int getTypeEtablissementDebut(){
		return type_etablissement_debut;
	}
	
	public int getTypeEtablissementFin(){
		return type_etablissement_fin;
	}
	
	public int getAgsDebut(){
		return ags_debut;
	}
	
	public int getAgsFin(){
		return ags_fin;
	}
	
	public int getDateAccesEchDebut(){
		return date_acces_ech_debut;
	}
	
	public int getDateAccesEchFin(){
		return date_acces_ech_fin;
	}
	
	public int getProGcDebut(){
		return pro_gc_debut;
	}
	
	public int getProGcFin(){
		return pro_gc_fin;
	}
	
	public int getDateGcDebut(){
		return date_gc_debut;
	}
	
	public int getDateGcFin(){
		return date_gc_fin;
	}
	
	public int getAsaGcDebut(){
		return asa_gc_debut;
	}
	
	public int getAsaGcFin(){
		return asa_gc_fin;
	}
	
	// --- Ligne 2 ---
	public int getPrenomDebut(){
		return prenom_debut;
	}
	
	public int getPrenomFin(){
		return prenom_fin;
	}
	
	public int getDateNaissanceDebut(){
		return date_naissance_debut;
	}
	
	public int getDateNaissanceFin(){
		return date_naissance_fin;
	}
	
	public int getNoteInspectionDebut(){
		return note_inspection_debut;
	}
	
	public int getNoteInspectionFin(){
		return note_inspection_fin;
	}
	
	public int getDateInspectionDebut(){
		return date_inspection_debut;
	}
	
	public int getDateInspectionFin(){
		return date_inspection_fin;
	}
	
	public int getModeAccesEchDebut(){
		return mode_acces_ech_debut;
	}
	
	public int getModeAccesEchFin(){
		return mode_acces_ech_fin;
	}
	
	public int getReportAncienneteDebut(){
		return report_anciennete_debut;
	}
	
	public int getReportAncienneteFin(){
		return report_anciennete_fin;
	}
	
	public int getProChDebut(){
		return pro_ch_debut;
	}
	
	public int getProChFin(){
		return pro_ch_fin;
	}
	
	public int getDateChDebut(){
		return date_ch_debut;
	}
	
	public int getDateChFin(){
		return date_ch_fin;
	}
	
	public int getAsaChDebut(){
		return asa_ch_debut;
	}
	
	public int getAsaChFin(){
		return asa_ch_fin;
	}
	
	// --- Ligne 3 ---
	public int getCodeDisciplineDebut(){
		return code_discipline_debut;
	}
	
	public int getCodeDisciplineFin(){
		return code_discipline_fin;
	}
	
	public int getProAnDebut(){
		return pro_an_debut;
	}
	
	public int getProAnFin(){
		return pro_an_fin;
	}
	
	public int getDateAnDebut(){
		return date_an_debut;
	}
	
	public int getDateAnFin(){
		return date_an_fin;
	}
	
	public int getAsaAnDebut(){
		return asa_an_debut;
	}
	
	public int getAsaAnFin(){
		return asa_an_fin;
	}
}
