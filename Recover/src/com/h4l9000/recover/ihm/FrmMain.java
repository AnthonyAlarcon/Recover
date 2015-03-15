package com.h4l9000.recover.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.w3c.dom.NodeList;

import com.h4l9000.recover.modules.ModFiltre;
import com.h4l9000.recover.modules.ModGeneral;
import com.h4l9000.recover.threads.ThreadMaintenance;
import com.h4l9000.recover.ws.MyWsDataset;

public class FrmMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnImporter = null;
	private JButton btnExtraire = null;
	private JLabel labVersion = null;
	private JLabel labMaintenance = null;
	
	private String token = "";

	private ModGeneral gen = null;
	
	private DlgNotificationMaintenance notif_maintenance = null;
	private DlgNotificationUpdate notif_update = null;
	
	public FrmMain(String strCurrentUser, String strToken) {
		super();
		initialize();

		// --- Initialisation du statut de maintenance ---
		setMaintenance("NON");
		setUpdate("NON");
		
		// --- Affectation du Token ---
		token = strToken;
		
		// --- Initialisation du module général ---
		gen = new ModGeneral();
		labVersion.setText("Version " + gen.getCurrentVersion());
		
		// --- Thread Maintenace ---
		ControleMaintenance();
	}

	private void initialize() {
		this.setSize(458, 505);
		this.setContentPane(getJContentPane());
		this.setTitle("Recover");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnImporter(), null);
			jContentPane.add(getBtnExtraire(), null);
			labVersion = new JLabel("Version ");
			labVersion.setBounds(new Rectangle(10, 446, 90, 20));
			labVersion.setFont(new Font("Arial", Font.BOLD, 10));
			jContentPane.add(labVersion, null);
			labMaintenance = new JLabel("");
			labMaintenance.setBounds(new Rectangle(121, 446, 90, 20));
			labMaintenance.setFont(new Font("Arial", Font.BOLD, 10));
			labMaintenance.setHorizontalAlignment(SwingConstants.CENTER);
			labMaintenance.setOpaque(true);
			labMaintenance.setForeground(Color.white);
			labMaintenance.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jContentPane.add(labMaintenance, null);
		}
		return jContentPane;
	}
	
	private JButton getBtnImporter() {
		if (btnImporter == null) {
			btnImporter = new JButton("Importer");
			btnImporter.setBounds(new Rectangle(27, 37, 206, 49));
			btnImporter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FrmImporter import_texte = new FrmImporter(FrmMain.this);
					import_texte.setLocationRelativeTo(null);
					import_texte.setVisible(true);
				}
			});
		}
		return btnImporter;
	}
	
	private JButton getBtnExtraire() {
		if (btnExtraire == null) {
			btnExtraire = new JButton("Extraire");
			btnExtraire.setBounds(new Rectangle(27, 103, 206, 49));
			btnExtraire.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try {
						// Paramétrage du FileChooser
						JFileChooser fc = new JFileChooser();
						fc.setAcceptAllFileFilterUsed(false);					
						fc.setPreferredSize(new Dimension(600,400));
																				
						// Filtre
						ModFiltre filtreCSV = new ModFiltre(new String[]{"csv","CSV"},"Fichier CSV (*.csv | *.CSV)");
						fc.addChoosableFileFilter(filtreCSV);
						fc.setSelectedFile(new File("C:/Export.csv"));
						
						int returnVal = fc.showSaveDialog(FrmMain.this);
						
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							String strFichier = fc.getSelectedFile().getAbsolutePath();
							
							String url = "http://www.h4l9000.com/WsData.asmx";
							String namespace = "http://www.h4l9000.com/";
							String methode = "ListeGlobal";
							
							MyWsDataset ds = new MyWsDataset(url, namespace, methode, token);
							
							NodeList list_nom = ds.getDocument().getElementsByTagName("nom");
							NodeList list_prenom = ds.getDocument().getElementsByTagName("prenom");
							NodeList list_date_naissance = ds.getDocument().getElementsByTagName("date_naissance");
							NodeList list_sexe = ds.getDocument().getElementsByTagName("sexe");
							NodeList list_echelon = ds.getDocument().getElementsByTagName("echelon");
							NodeList list_page = ds.getDocument().getElementsByTagName("page");
							NodeList list_position = ds.getDocument().getElementsByTagName("position");
							
							NodeList list_rne = ds.getDocument().getElementsByTagName("rne");
							NodeList list_departement = ds.getDocument().getElementsByTagName("departement");
							NodeList list_type_etablissement = ds.getDocument().getElementsByTagName("type_etablissement");
							
							NodeList list_date_acces_ech = ds.getDocument().getElementsByTagName("date_acces_ech");
							NodeList list_mode_acces_ech = ds.getDocument().getElementsByTagName("mode_acces_ech");
							NodeList list_note_insp = ds.getDocument().getElementsByTagName("note_insp");
							NodeList list_date_insp = ds.getDocument().getElementsByTagName("date_insp");
							NodeList list_utilite = ds.getDocument().getElementsByTagName("utilite");
							NodeList list_note_admin = ds.getDocument().getElementsByTagName("note_admin");
							NodeList list_note_peda = ds.getDocument().getElementsByTagName("note_peda");
							
							NodeList list_crit_anc_corps = ds.getDocument().getElementsByTagName("crit_anc_corps");
							NodeList list_crit_anc_ech = ds.getDocument().getElementsByTagName("crit_anc_ech");
							NodeList list_crit_mode_acces_ech = ds.getDocument().getElementsByTagName("crit_mode_acces_ech");
							NodeList list_bareme = ds.getDocument().getElementsByTagName("bareme");
							NodeList list_syn_date_pro = ds.getDocument().getElementsByTagName("syn_date_pro");
							NodeList list_syn_mode_pro = ds.getDocument().getElementsByTagName("syn_mode_pro");
							
							// Création du fichier
							PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(strFichier))));
							out.println("nom;prenom;date_naissance;sexe;echelon;page;position;rne;departement;type_etablissement;date_acces_ech;mode_acces_ech;note_insp;date_insp;utilite;note_admin;note_peda;delta_peda;crit_anc_corps;crit_anc_ech;crit_mode_acces_ech;bareme;syn_date_pro;syn_mode_pro");
							
							String export = "";
							
							String note_insp = "";
							String note_peda = "";
							float delta_peda = 0f;
							String result_delta_peda = "";
							
							for (int i=0; i < list_nom.getLength(); i++){
																
								// --- Reset des variables ---
								note_insp = "";
								note_peda = "";
								delta_peda = 0f;
								result_delta_peda = "";
								
								export = list_nom.item(i).getTextContent() + ";";
								export = export + list_prenom.item(i).getTextContent() + ";";
								export = export + list_date_naissance.item(i).getTextContent().substring(0, 10) + ";";
								export = export + list_sexe.item(i).getTextContent() + ";";
								export = export + list_echelon.item(i).getTextContent() + ";";
								export = export + list_page.item(i).getTextContent() + ";";
								export = export + list_position.item(i).getTextContent() + ";";
								
								export = export + list_rne.item(i).getTextContent() + ";";
								export = export + list_departement.item(i).getTextContent() + ";";
								export = export + list_type_etablissement.item(i).getTextContent() + ";";
																
								export = export + list_date_acces_ech.item(i).getTextContent().substring(0, 10) + ";";
								export = export + list_mode_acces_ech.item(i).getTextContent() + ";";
								
								note_insp = list_note_insp.item(i).getTextContent();
								export = export + note_insp + ";";
								
								export = export + list_date_insp.item(i).getTextContent().substring(0, 10) + ";";
								export = export + list_utilite.item(i).getTextContent() + ";";
								export = export + list_note_admin.item(i).getTextContent() + ";";
								
								note_peda = list_note_peda.item(i).getTextContent();
								export = export + note_peda + ";";
								
								// --- Calcul du delta de note pédagogique ---
								if (note_insp.compareTo("-1.00")!=0){
									delta_peda = Float.valueOf(note_peda) - Float.valueOf(note_insp);
									result_delta_peda = String.valueOf(delta_peda);
								} else {
									result_delta_peda = "NA";
								}
								export = export + result_delta_peda + ";";
								
								export = export + list_crit_anc_corps.item(i).getTextContent() + ";";
								export = export + list_crit_anc_ech.item(i).getTextContent() + ";";
								export = export + list_crit_mode_acces_ech.item(i).getTextContent() + ";";
								export = export + list_bareme.item(i).getTextContent() + ";";
								export = export + list_syn_date_pro.item(i).getTextContent().substring(0, 10) + ";";
								export = export + list_syn_mode_pro.item(i).getTextContent() + ";";
								
								out.println(export);
							}
							
							// Fermeture du fichier
							out.close();
							
							JOptionPane.showMessageDialog(FrmMain.this, "Extraction terminée", "Recover", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(FrmMain.this, "Erreur Extraction : " + ex.toString(), "Recover", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
		}
		return btnExtraire;
	}
	
	public String getToken(){
		return token;
	}
	
	private void ControleMaintenance(){
		
		// --- Lancement ---
		try {
			
			ThreadMaintenance maintenance = new ThreadMaintenance(FrmMain.this, token);
			maintenance.start();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(FrmMain.this, "Erreur sur le contrôle de Maintenance : " + ex.toString(), "Recover", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void setMaintenance(String strStatut){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				if(strStatut.compareTo("NON")==0){
					labMaintenance.setText("CONNECTE");
					labMaintenance.setBackground(Color.GREEN);
				} else {
					if(strStatut.compareTo("OUI")==0){
						labMaintenance.setText("MAINTENANCE");
						labMaintenance.setBackground(Color.red);
						
						// --- Affichage du panneau de notification ---
						notif_maintenance = new DlgNotificationMaintenance();
						notif_maintenance.setVisible(true);
					}
				}
				
			}
		});		
	}
	
	public void setUpdate(String strStatut){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				// --- Création de l'instance ---
				if (notif_update==null){
					notif_update = new DlgNotificationUpdate();
				}
				
				if(strStatut.compareTo("NON")==0){	
					notif_update.setVisible(false);				
				} else {
					if(strStatut.compareTo("OUI")==0){						
						notif_update.setVisible(true);
					}
				}
			}
		});
	}
}
