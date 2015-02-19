package com.h4l9000.recover.structures;

public class StrucTexteLignes {
	
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
	}
	
	private String loadValue(String strFichier){
		
		String resultat = "";
		
		
		
		return resultat;
	}
	
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
}
