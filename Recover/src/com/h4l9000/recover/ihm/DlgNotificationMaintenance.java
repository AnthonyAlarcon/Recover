package com.h4l9000.recover.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DlgNotificationMaintenance extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel labTitre = null;
	private JLabel labMessage = null;
	
	public DlgNotificationMaintenance() {
		super();
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		initialize();
		
		// Affectation du message
		labTitre.setText("Notification : Maintenance");
		labMessage.setText("Une opération de maintenance est en cours. L'application se fermera automatiquement dans 60 secondes.");
	}
	
	private void initialize() {
		this.setSize(630, 100);
		this.setLocation(200, 200);
		this.setMinimumSize(new Dimension(630, 100));
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setTitle("Recover");
		this.setContentPane(getJContentPane());
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.ORANGE);
			jContentPane.setLayout(null);
			
			labTitre = new JLabel();
			labTitre.setFont(new Font("Arial", Font.BOLD, 16));
			labTitre.setBounds(new Rectangle(10, 10, 574, 30));
			jContentPane.add(labTitre, null);
			
			labMessage = new JLabel();
			labMessage.setFont(new Font("Arial", Font.PLAIN, 12));
			labMessage.setBounds(new Rectangle(10, 35, 604, 30));
			jContentPane.add(labMessage, null);
		}
		return jContentPane;
	}

}
