package com.h4l9000.recover.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.w3c.dom.NodeList;

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
					
					String url = "http://www.h4l9000.com/WsData.asmx";
					String namespace = "http://www.h4l9000.com/";
					String methode = "ListeGlobal";
					
					MyWsDataset ds = new MyWsDataset(url, namespace, methode, token);
					
					NodeList list = ds.getDocument().getElementsByTagName("nom");
					
					for (int i=0; i < list.getLength(); i++){
						System.out.println(i + " // " + list.item(i).getTextContent());
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
