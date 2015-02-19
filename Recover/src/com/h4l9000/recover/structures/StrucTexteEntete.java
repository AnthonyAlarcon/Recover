package com.h4l9000.recover.structures;

public class StrucTexteEntete {

	private int academie_debut = 0;
	private int academie_fin = 0;
	
	private int page_debut = 0;
	
	private int corps_debut = 0;
	private int corps_fin = 0;
	
	private int date_fichier_debut = 0;
	private int date_fichier_fin = 0;
	
	private int periode_min_debut = 0;
	private int periode_min_fin = 0;
	
	private int periode_max_debut = 0;
	private int periode_max_fin = 0;
	
	private int echelon_debut = 0;
	private int echelon_fin = 0;
		
	
	public StrucTexteEntete(String strModeAffectationValeurs){
		
		if(strModeAffectationValeurs.compareTo("DEFAUT")==0){
			setValue();
		} else {
			String result = loadValue(strModeAffectationValeurs);
			System.out.println(result);
		}
	}
	
	private void setValue(){
		
		// --- Entete 1 ---
		academie_debut = 12;
		academie_fin = 37;
		
		page_debut = 126;
		
		// --- Entete 2 ---
		corps_debut = 44;
		corps_fin = 48;
		
		// --- Entete 3 ---
		date_fichier_debut = 8;
		date_fichier_fin = 18;
		
		periode_min_debut = 114;
		periode_min_fin = 124;
		
		// --- Entete 4 ---
		periode_max_debut = 114;
		periode_max_fin = 124;
		
		// --- Entete 5 ---
		echelon_debut = 119;
		echelon_fin = 121;
		
	}
	
	private String loadValue(String strFichier){
		
		String resultat = "";
		
		
		
		return resultat;
	}
		
	public int getAcademieDebut(){
		return academie_debut;
	}
	
	public int getAcademieFin(){
		return academie_fin;
	}
	
	public int getPageDebut(){
		return page_debut;
	}
	
	public int getCorpsDebut(){
		return corps_debut;
	}
	
	public int getCorpsFin(){
		return corps_fin;
	}
	
	public int getDateFichierDebut(){
		return date_fichier_debut;
	}
	
	public int getDateFichierFin(){
		return date_fichier_fin;
	}
	
	public int getPeriodeMinDebut(){
		return periode_min_debut;
	}
	
	public int getPeriodeMinFin(){
		return periode_min_fin;
	}
	
	public int getPeriodeMaxDebut(){
		return periode_max_debut;
	}
	
	public int getPeriodeMaxFin(){
		return periode_max_fin;
	}
	
	public int getEchelonDebut(){
		return echelon_debut;
	}
	
	public int getEchelonFin(){
		return echelon_fin;
	}
}
