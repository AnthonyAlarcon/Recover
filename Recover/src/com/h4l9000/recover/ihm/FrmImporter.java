package com.h4l9000.recover.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.h4l9000.recover.modules.ModFiltre;
import com.h4l9000.recover.modules.ModTexteEntete;
import com.h4l9000.recover.modules.ModTexteLignes;
import com.h4l9000.recover.structures.StrucTexteEntete;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrmImporter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFermer = null;
	private JButton btnDemarrer = null;
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
							
							ModFiltre filtreTXT = new ModFiltre(new String[]{"txt","TXT"}, "Fichier texte (*.txt | *.TXT)");
							
							// --- Strucure du fichier texte ---
							ModTexteEntete header = new ModTexteEntete("DEFAUT");
							ModTexteLignes body = new ModTexteLignes("DEFAUT");
							
							// --- Ouverture de la fen�tre de s�lection du fichier ---
							JFileChooser fc = new JFileChooser();
							fc.setAcceptAllFileFilterUsed(false);					
							fc.setPreferredSize(new Dimension(600,400));
							fc.addChoosableFileFilter(filtreTXT);
							
							int returnVal = fc.showOpenDialog(FrmImporter.this);
							
							int ligne_academie = 0;
							
							
							// --- D�tecteur de position. On compte 4 position par feuille. ---
							boolean position1 = false;
							boolean position2 = false;
							boolean position3 = false;
							boolean position4 = false;
							
							// --- L'utilisateur valide son choix ---
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								
								try {
									setNewMessage("Ouverture du fichier");
									
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
										
										// --- Balayge des donn�es du vecteur vecLignes ---
										for (int i=0; i < vecLignes.size(); i++){
											ligne = vecLignes.get(i).toString();
											
											if (ligne.length() > 10){
												
												// --- D�tection ACAMEDIE ---
												if (ligne.substring(0, 10).compareTo(" ACADEMIE ")==0){
													
													String academie = header.getAcademie(ligne);
													String numero_page = header.getPage(ligne);
													
													System.out.println(academie + " / " + numero_page);
													
													// --- Affectation de l'indice I associ� � la ligne acad�mie ---
													ligne_academie = i;
													
													// --- Position1 OK ---
													position1 = true;
												}
											}
											
											// --- Traitement de la Position #1 ---
											if (position1 == true){
												
												if ((i==ligne_academie + 3) && (ligne_academie >0)){
													String corps = header.getCorps(ligne);
													//System.out.println("Corps ." + corps + ".");
												}												
												
												if ((i==ligne_academie + 4) && (ligne_academie >0)){
													String date_fichier = header.getDateFichier(ligne);
													String date_min_periode = header.getDateMinPeriode(ligne);
													//System.out.println("Date Fichier ." + date_fichier + ".  //  Date Min P�riode ." + date_min_periode + ".");
												}
												
												if ((i==ligne_academie + 5) && (ligne_academie >0)){
													String date_max_periode = header.getDateMaxPeriode(ligne);
													//System.out.println("Date Max P�riode ." + date_max_periode + ".");
												}
												
												if ((i==ligne_academie + 6) && (ligne_academie >0)){
													// ligne non r�cup�r�e (Projet)
												}
												
												if ((i==ligne_academie + 7) && (ligne_academie >0)){
													String echelon = header.getEchelon(ligne);
													//System.out.println("Echelon ." + echelon + ".");
												}
												
												
												// --- Ligne #1 ---
												if ((i==ligne_academie + 13) && (ligne_academie >0)){
													String nom = body.getNom(ligne);
													String rne = body.getRne(ligne);
													String type_etablissement = body.getTypeEtablissement(ligne);
													String ags = body.getAgs(ligne);
													String date_acces_ech = body.getDateAccesEch(ligne);
													String pro_gc = body.getProGc(ligne);
													String date_gc = body.getDateGc(ligne);
													String asa_gc = body.getAsaGc(ligne);
													
													System.out.println("Nom ." + nom + ".  /  Rne ." + rne + ".");
													System.out.println("TypeEtablissement ." + type_etablissement + ".  /  Ags ." + ags + ".");
													System.out.println("DateAccesEch ." + date_acces_ech + ".  /  ProGc ." + pro_gc + ".");
													System.out.println("DateGc ." + date_gc + ".  /  AsaGc ." + asa_gc + ".");
												}
												
												// --- Ligne #2 ---
												if ((i==ligne_academie + 14) && (ligne_academie >0)){
													
												}
											}
										}
										
									} else {
										setNewMessage("### Erreur ### Le fichier s�lectionn� ne peux pas �tre charg�");
									}
									
								} catch (Exception ex){
									System.out.println("[FrmImporter] Erreur : " + ex.toString());
								}
							}
							
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
