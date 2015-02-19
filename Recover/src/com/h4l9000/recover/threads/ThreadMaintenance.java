package com.h4l9000.recover.threads;

import com.h4l9000.recover.ihm.FrmMain;
import com.h4l9000.recover.ws.MyWsSingleWithToken;

public class ThreadMaintenance extends Thread {

	private String maintenance = "";
	private String update = "";
	
	private FrmMain frmParent = null;
	private String token = "";
	
	public ThreadMaintenance(FrmMain parent, String strToken){
		this.setDaemon(true);
		
		frmParent = parent;
		token = strToken;
	}
	
	public void run(){
		
		// --- initialisation des variables ---
		maintenance = "NON";
		update = "NON";
		
		System.out.println(" ---> Lancement du Thread");
		
		try {
			while(maintenance.compareTo("NON")==0){
				
				maintenance = getMaintenance(token);
				update = getUpdate(token);
				
				System.out.println("Statut Maintenance = [" + maintenance + "] | Statut Update = [" + update + "]");
				
				// --- Comportement de l'appplication lors d'une maintenance ---
				if (maintenance.compareTo("OUI")==0){
					
					frmParent.setMaintenance(maintenance);
					
					// --- D�compte des 60 secondes ---
					Thread.sleep(60000);
					System.exit(0);
				}
				
				// --- Comportement de l'appplication lors d'une mise � jour ---
				frmParent.setUpdate(update);
				
				// Contr�le toutes les minutes du statut de maintenance
				Thread.sleep(60000);
			}
		} catch (Exception ex){
			//System.out.println("ERREUR : " + ex.toString());
		}
	}
	
	private String getMaintenance(String strToken){
		
		String reponse_maintenance = "";
		
		try {
			MyWsSingleWithToken statut = new MyWsSingleWithToken("http://www.h4l9000.com/WsMaintenance.asmx", "http://www.h4l9000.com/", "CheckMaintenance", strToken);
			reponse_maintenance = statut.getSingle("CheckMaintenanceResult");
		} catch (Exception ex) {
			reponse_maintenance = "ERREUR";
		}
						
		return reponse_maintenance;
		
	}
	
	private String getUpdate(String strToken){
		
		String reponse_update = "";
		
		try {
			MyWsSingleWithToken statut = new MyWsSingleWithToken("http://www.h4l9000.com/WsMaintenance.asmx", "http://www.h4l9000.com/", "CheckUpdate", strToken);
			reponse_update = statut.getSingle("CheckUpdateResult");
		} catch (Exception ex) {
			reponse_update = "ERREUR";
		}
		
		return reponse_update;
		
	}
	
}
