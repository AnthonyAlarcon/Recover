package com.h4l9000.recover.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.h4l9000.recover.modules.ModFiltre;
import com.h4l9000.recover.modules.ModFormatDate;
import com.h4l9000.recover.modules.ModTexteEntete;
import com.h4l9000.recover.modules.ModTexteLignes;
import com.h4l9000.recover.ws.MyWsAjoutAgent;
import com.h4l9000.recover.ws.MyWsAjoutEchelon;
import com.h4l9000.recover.ws.MyWsAjoutInspection;
import com.h4l9000.recover.ws.MyWsAjoutRattachement;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrmImporter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFermer = null;
	private JButton btnDemarrer = null;
	private JProgressBar progressImport = null;
	private JScrollPane scrollMessages = null;
	private JList<String> listMessages = null;
	private DefaultListModel<String> modele_messages = null;
	
	private FrmMain parent = null;
	
	public FrmImporter(FrmMain frmParent) {
		super();
		initialize();
		
		parent = frmParent;
	}
	

	private void initialize() {
		this.setSize(800, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("Importer");
		this.setResizable(false);
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnDemarrer());
			jContentPane.add(getBtnFermer());
			jContentPane.add(getScrollMessages());
			jContentPane.add(getProgressImport());
		}
		return jContentPane;
	}
	
	private JScrollPane getScrollMessages() {
		if (scrollMessages == null) {
			scrollMessages = new JScrollPane();
			scrollMessages.setBounds(new Rectangle(20, 58, 750, 230));
			scrollMessages.setViewportView(getListMessages());
		}
		return scrollMessages;
	}
	
	private JList<String> getListMessages() {
		if (listMessages == null) {
			listMessages = new JList<String>();			
			listMessages.setFont(new Font("Arial", Font.PLAIN, 14));
			
			modele_messages = new DefaultListModel<String>();
			listMessages.setModel(modele_messages);
		}
		return listMessages;
	}
	
	private JButton getBtnDemarrer() {
		if (btnDemarrer == null) {			
			btnDemarrer = new JButton("D�marrer");
			btnDemarrer.setBounds(new Rectangle(420, 320, 170, 30));
			btnDemarrer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					Thread thread_importer = new Thread() {
						public void run() {
							
							verrouiller();
													
							// D�laration des variables
							String numero_page = "";
							String echelon = "";
							int position = 0;
							
							String date_min_periode = "";
							String date_max_periode = "";
							String academie = "";
							String corps = "";
							
							String nom = "";
							String prenom = "";
							String date_naissance = "";
							String sexe = "";
							
							String mode_acces_ech = "";
							String date_acces_ech = "";
							
							String note_inspection = "";
							String date_inspection = "";
							
							String rne = "";
							
							ModFiltre filtreTXT = new ModFiltre(new String[]{"txt","TXT"}, "Fichier texte (*.txt | *.TXT)");
							
							// --- Strucure du fichier texte ---
							ModTexteEntete header = new ModTexteEntete("DEFAUT");
							ModTexteLignes body = new ModTexteLignes("DEFAUT");
							
							ModFormatDate fd = new ModFormatDate();
														
							// --- Ouverture de la fen�tre de s�lection du fichier ---
							JFileChooser fc = new JFileChooser();
							fc.setAcceptAllFileFilterUsed(false);					
							fc.setPreferredSize(new Dimension(600,400));
							fc.addChoosableFileFilter(filtreTXT);
							
							int returnVal = fc.showOpenDialog(FrmImporter.this);
							
							// 
							int ligne_academie = -1;
							
							int ponderation_position = 0;
							
							int nb_agents = 0;
														
							// --- L'utilisateur valide son choix ---
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								
								try {
									setNewMessage("Ouverture du fichier");
									progressImport.setIndeterminate(false);
									
									File file = new File(fc.getSelectedFile().getPath());
									
									if (file.canRead()==true){
										
										BufferedReader reader = new BufferedReader(new FileReader(file));
										
										Vector<String> vecLignes = new Vector<String>();
										String ligne = reader.readLine();
										
										setNewMessage("Chargement en cours...");
										
										while(ligne != null){
											vecLignes.add(ligne);
											ligne = reader.readLine();
										}
										reader.close();
										
										setNewMessage("Chargement termin� : " + vecLignes.size() + " ligne(s) trouv�e(s)");
										
										setNewMessage("Traitement des donn�es...");
										
										
										
										numero_page = "";
										
										// --- Balayge des donn�es du vecteur vecLignes ---
										for (int i=0; i < vecLignes.size(); i++){
											ligne = vecLignes.get(i).toString();
											
											if (ligne.length() > 10){
												
												// --- D�tection ACAMEDIE ---
												if (ligne.substring(0, 10).compareTo(" ACADEMIE ")==0){
													
													academie = header.getAcademie(ligne);
													numero_page = header.getPage(ligne);
													
													//System.out.println(academie + " / " + numero_page);
													
													// --- Affectation de l'indice I associ� � la ligne acad�mie ---
													ligne_academie = i;
													
													// --- reset de la pond�ration ---
													position = 1;
													ponderation_position = 0;
												}
											}
											
											// --- Traitement ----
											
											if ((i==ligne_academie + 3) && (ligne_academie > -1)){
												corps = header.getCorps(ligne);
												//System.out.println("Corps ." + corps + ".");
											}												
											
											if ((i==ligne_academie + 4) && (ligne_academie > -1)){
												String date_fichier = header.getDateFichier(ligne);
												date_min_periode = header.getDateMinPeriode(ligne);
												//System.out.println("Date Fichier ." + date_fichier + ".  //  Date Min P�riode ." + date_min_periode + ".");
											}
											
											if ((i==ligne_academie + 5) && (ligne_academie > -1)){
												date_max_periode = header.getDateMaxPeriode(ligne);
												//System.out.println("Date Max P�riode ." + date_max_periode + ".");
											}
											
											if ((i==ligne_academie + 6) && (ligne_academie > -1)){
												// ligne non r�cup�r�e (Projet)
											}
											
											if ((i==ligne_academie + 7) && (ligne_academie > -1)){
												echelon = header.getEchelon(ligne);
												//System.out.println("Echelon ." + echelon + ".");
											}
											
											
											// --- Ligne #1 ---
											if ((i==ligne_academie + ponderation_position + 13) && (ligne_academie > -1)){
												
												nb_agents = nb_agents + 1;
												
												nom = body.getNom(ligne);
												rne = body.getRne(ligne);
												String type_etablissement = body.getTypeEtablissement(ligne);
												String ags = body.getAgs(ligne);
												date_acces_ech = body.getDateAccesEch(ligne);
												String pro_gc = body.getProGc(ligne);
												String date_gc = body.getDateGc(ligne);
												String asa_gc = body.getAsaGc(ligne);
												
													System.out.println(echelon + ";" + numero_page + ";" + position + ";" + nom + ";" + rne + ";");
//													System.out.println("TypeEtablissement ." + type_etablissement + ".  /  Ags ." + ags + ".");
//													System.out.println("DateAccesEch ." + date_acces_ech + ".  /  ProGc ." + pro_gc + ".");
//													System.out.println("DateGc ." + date_gc + ".  /  AsaGc ." + asa_gc + ".");
											}
											
											// --- Ligne #2 ---
											if ((i==ligne_academie + ponderation_position + 14) && (ligne_academie > -1)){
												prenom = body.getPrenom(ligne);
												date_naissance = body.getDateNaissance(ligne);
												note_inspection = body.getNoteInspection(ligne);
												date_inspection = body.getDateInspection(ligne);
												mode_acces_ech = body.getModeAccesEch(ligne);
												String report_anciennete = body.getReportAnciennete(ligne);
												String pro_ch = body.getProCh(ligne);
												String date_ch = body.getDateCh(ligne);
												String asa_ch = body.getAsaCh(ligne);
												
//													System.out.println("prenom ." + prenom + ".  /  date_naissance ." + date_naissance + ".");
//													System.out.println("note_inspection ." + note_inspection + ".  /  date_inspection ." + date_inspection + ".");
//													System.out.println("mode_acces_ech ." + mode_acces_ech + ".  /  report_anciennete ." + report_anciennete + ".");
//													System.out.println("pro_ch ." + pro_ch + ".  /  date_ch ." + date_ch + ".  /  asa_ch ." + asa_ch + ".");
											}
											
											// --- Ligne #3 ---
											if ((i==ligne_academie + ponderation_position + 15) && (ligne_academie > -1)){
												String code_discipline = body.getCodeDiscipline(ligne);
												String pro_an = body.getProAn(ligne);
												String date_an = body.getDateAn(ligne);
												String asa_an = body.getAsaAn(ligne);
												
//													System.out.println("code_discipline ." + code_discipline + ".  /  pro_an ." + pro_an + ".");
//													System.out.println("date_an ." + date_an + ".  /  asa_an ." + asa_an + ".");
											}
											
											// --- Ligne #5 ---
											if ((i==ligne_academie + ponderation_position + 17) && (ligne_academie > -11)){
												String note_admin = body.getNoteAdministrative(ligne);
												String note_peda = body.getNotePedagogique(ligne);
												String crit_anc_corps = body.getCritereAncienneteCorps(ligne);
												String crit_anc_ech = body.getCritereAncienneteEch(ligne);
												String crit_mode_acces_ech = body.getCritereModeAccesEch(ligne);
											}
											
											// --- Ligne #6 ---
											if ((i==ligne_academie + ponderation_position + 18) && (ligne_academie > -1)){
												String bareme = body.getBareme(ligne);
											}
											
											// --- Ligne #10 ---
											if ((i==ligne_academie + ponderation_position + 22) && (ligne_academie > -1)){
												
												// --- Fin de traitement d'une position---								
//												MyWsAjoutAgent agent = new MyWsAjoutAgent("http://www.h4l9000.com/WsDataImport.asmx", "http://www.h4l9000.com/", "AjoutAgent", parent.getToken(), nom, prenom, fd.getFormatDate(date_naissance), sexe);
//												String reponse_ajout_agent = agent.getSingle("AjoutAgentResult");
//												
//												if (reponse_ajout_agent.compareTo("OK")!=0){
//													setNewMessage("--> Erreur sur AGENT ." + echelon + ". ." + position + ".");
//													System.out.println("### AjoutAgent > " + i + "   ." + reponse_ajout_agent + ".");
//												}
//												
//												
//												MyWsAjoutEchelon ech = new MyWsAjoutEchelon("http://www.h4l9000.com/WsDataImport.asmx", "http://www.h4l9000.com/", "AjoutEchelon", parent.getToken(), date_min_periode + "_" + date_max_periode, academie, corps, nom, prenom, fd.getFormatDate(date_naissance), echelon, fd.getFormatDate(date_acces_ech), mode_acces_ech, numero_page, String.valueOf(position));
//												String reponse_ajout_ech = ech.getSingle("AjoutEchelonResult");
//												
//												if (reponse_ajout_ech.compareTo("OK")!=0){
//													setNewMessage("--> Erreur sur ECHELON ." + echelon + ". ." + position + ".");
//													System.out.println("### AjoutEchelon > " + i + "   ." + reponse_ajout_ech + ".");
//												}
												
												
//												MyWsAjoutInspection insp = new MyWsAjoutInspection("http://www.h4l9000.com/WsDataImport.asmx", "http://www.h4l9000.com/", "AjoutInspection", parent.getToken(), date_min_periode + "_" + date_max_periode, academie, corps, nom, prenom, fd.getFormatDate(date_naissance), note_inspection, fd.getFormatDate(date_inspection));
//												String reponse_ajout_insp = insp.getSingle("AjoutInspectionResult");
//												
//												if (reponse_ajout_insp.compareTo("OK")!=0){
//													setNewMessage("--> Erreur sur INSPECTION ." + echelon + ". ." + position + ".");
//													System.out.println("### AjoutInspection > " + i + "   ." + reponse_ajout_insp + ".");
//												}
												
												MyWsAjoutRattachement ratt = new MyWsAjoutRattachement("http://www.h4l9000.com/WsDataImport.asmx", "http://www.h4l9000.com/", "AjoutRattachement", parent.getToken(), date_min_periode + "_" + date_max_periode, academie, corps, nom, prenom, fd.getFormatDate(date_naissance), rne);
												String reponse_ajout_ratt = ratt.getSingle("AjoutRattachementResult");
												
												if (reponse_ajout_ratt.compareTo("OK")!=0){
													setNewMessage("--> Erreur sur RATTACHEMENT ." + echelon + ". ." + numero_page + ". ." + position + ".");
													System.out.println("### AjoutRattachement > " + i + "   ." + reponse_ajout_ratt + ".");
													System.out.println("### AjoutRattachement > " + date_min_periode + "_" + date_max_periode + "/" + academie + "/" + corps + "/" + nom + "/" + prenom + "/" + fd.getFormatDate(date_naissance) + "/" + rne);
													System.out.println((parent.getToken()));
												}
												
												
												String temoin = body.getTemoin(ligne);
												
												if(temoin.compareTo("!")==0){
													position = position + 1;
													ponderation_position = ponderation_position + 10;
												}
											}
										}
										
										setNewMessage("Traitement termin�");
										
									} else {
										setNewMessage("### Erreur ### Le fichier s�lectionn� ne peux pas �tre charg�");
									}
									
									progressImport.setIndeterminate(false);
									
								} catch (Exception ex){
									System.out.println("[FrmImporter] Erreur : " + ex.toString());
								}
							}
							
							System.out.println("COUNT = " + nb_agents);
							
							deverrouiller();
							
						}
					};
					
					//--- Lancement du Thread ---
					thread_importer.setDaemon(true);
					thread_importer.start();					
				}
			});
		}
		return btnDemarrer;
	}
	
	private JButton getBtnFermer() {
		if (btnFermer == null) {			
			btnFermer = new JButton("Fermer");
			btnFermer.setBounds(new Rectangle(600, 320, 170, 30));
			btnFermer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FrmImporter.this.dispose();
				}
			});
		}
		return btnFermer;
	}
	
	private JProgressBar getProgressImport(){
		if (progressImport==null){
			progressImport = new JProgressBar();
			progressImport.setBounds(new Rectangle(27, 320, 75, 30));
		}
		return progressImport;
	}
	
	private void verrouiller(){
		btnDemarrer.setEnabled(false);
	}
	
	private void deverrouiller(){
		btnDemarrer.setEnabled(true);
	}
	
	public void setNewMessage(String strTexte){

		final String texte_final = strTexte;
		
		SwingUtilities.invokeLater(
				new Runnable(){
			        public void run(){
			        	modele_messages.addElement(texte_final);
			        }
			    }
		);
	}
}
