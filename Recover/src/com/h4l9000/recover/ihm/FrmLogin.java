package com.h4l9000.recover.ihm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.h4l9000.recover.modules.ModGeneral;
import com.h4l9000.recover.ws.MyWsAuthentification;
import com.h4l9000.recover.ws.MyWsSingleWithToken;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnConnexion = null;
	private JLabel labIdentifiant = null;
	private JLabel labMotDePasse = null;
	private JTextField tfIdentifiant = null;
	private JPasswordField pfMotDePasse = null;
	private JLabel labSousTitre = null;
	private JLabel labVersion = null;
	private JLabel labContexte = null;
	
	private String token = "";
	private String current_user = "";
	
	private ModGeneral gen = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin login = new FrmLogin();
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.setLocationRelativeTo(null);
					login.setVisible(true);
				} catch (Exception ex) {
					System.out.println("### FrmLogin ### " + ex.toString());
				}
			}
		});
	}

	public FrmLogin() {
		super();
		initialize();
		
		// --- Initialisation du module général ---
		gen = new ModGeneral();
		labVersion.setText("Version " + gen.getCurrentVersion());
		
		FrmLogin.this.getRootPane().setDefaultButton(btnConnexion);
		
//		String url = "http://www.h4l9000.com/WsData.asmx";
//		String namespace = "http://www.h4l9000.com/";
//		String methode = "ListeCycle";
		
		
		
//		MyWsDataset ds = new MyWsDataset(url, namespace, methode);
//		
//		NodeList list = ds.getDocument().getElementsByTagName("nom");
//		
//		for (int i=0; i < list.getLength(); i++){
//			System.out.println(i + " // " + list.item(i).getTextContent());
//		}
		
		
		
		// --- Authentification ----
//		url = "http://www.h4l9000.com/ws1.asmx";
//		namespace = "http://www.h4l9000.com/";
//		methode = "HelloWorld";
//		
//		MyWsSingleWithToken test = new MyWsSingleWithToken(url, namespace, methode, token);
//		
//		MyWsSingleWithToken test2 = new MyWsSingleWithToken(url, namespace, methode, "9bad8512-5b6d-4d37-a664-9960ccdb3388");
				
//        System.out.println(list.item(0).getTextContent());
//        System.out.println(list.item(1).getTextContent());
//        System.out.println(list.item(2).getTextContent());
		
		
//		try {
//            // Create SOAP Connection
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//
//            // Send SOAP Message to SOAP Server
//            String url = "http://www.h4l9000.com/WsData.asmx";
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
//            
//            // Process the SOAP Response
//            printSOAPResponse(soapResponse);
//
//            soapConnection.close();
//        } catch (Exception e) {
//            System.err.println("Error occurred while sending SOAP Request to Server");
//            e.printStackTrace();
//        }
	}
	
	private void initialize() {
		this.setSize(458, 245);
		this.setContentPane(getJContentPane());
		this.setTitle("Recover");
		this.setResizable(false);
		//this.setUndecorated(true);
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnConnexion(), null);
			labMotDePasse = new JLabel();
			labMotDePasse.setFont(new Font("Arial", Font.PLAIN, 12));
			labMotDePasse.setBounds(new Rectangle(20, 80, 60, 20));
			labMotDePasse.setText("Password");
			jContentPane.add(labMotDePasse, null);
			jContentPane.add(getTfIdentifiant(), null);
			jContentPane.add(getPfMotDePasse(), null);
			labSousTitre = new JLabel();
			labSousTitre.setBounds(new Rectangle(20, 20, 230, 20));
			labSousTitre.setText("RECOVER");
			labSousTitre.setFont(new Font("Arial", Font.BOLD, 16));
			jContentPane.add(labSousTitre, null);
			labContexte = new JLabel("SE-UNSA", SwingConstants.RIGHT);
			labContexte.setBounds(new Rectangle(280, 20, 90, 20));
			labContexte.setFont(new Font("Arial", Font.BOLD, 10));
			jContentPane.add(labContexte, null);
			labIdentifiant = new JLabel();
			labIdentifiant.setFont(new Font("Arial", Font.PLAIN, 12));
			labIdentifiant.setBounds(new Rectangle(20, 50, 60, 20));
			labIdentifiant.setText("Login");
			jContentPane.add(labIdentifiant, null);
			labVersion = new JLabel("DEV");
			labVersion.setBounds(new Rectangle(20, 182, 90, 20));
			labVersion.setFont(new Font("Arial", Font.BOLD, 10));
			jContentPane.add(labVersion, null);
		}
		return jContentPane;
	}
	
	private JButton getBtnConnexion() {
		if (btnConnexion == null) {
			btnConnexion = new JButton("Connexion");
			btnConnexion.setFont(new Font("Arial", Font.PLAIN, 12));
			btnConnexion.setBounds(new Rectangle(230, 176, 120, 30));
			btnConnexion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					String username = tfIdentifiant.getText();
					String password = getPassword(pfMotDePasse.getPassword());
					
					String reponse_login = "";
					String reponse_version = "";
					String reponse_maintenance = "";
					
					// --- Contrôle de saisie ---
					if ((username.compareTo("")!=0) || (password.compareTo("")!=0)){
						
						// --- Authentification ----											
						MyWsAuthentification aut = new MyWsAuthentification("http://www.h4l9000.com/WsIdentification.asmx", "http://www.h4l9000.com/", "Login", username, password);
						
						reponse_login = aut.getToken("LoginResult");
												
						if (reponse_login.compareTo("KO")!=0){
							token = reponse_login;
							current_user = username;
									
							System.out.println(current_user + " " + token);
														
							MyWsSingleWithToken version = new MyWsSingleWithToken("http://www.h4l9000.com/WsMaintenance.asmx", "http://www.h4l9000.com/", "CheckVersion", token);
							reponse_version = version.getSingle("CheckVersionResult");
							System.out.println(reponse_version);
							
							MyWsSingleWithToken statut = new MyWsSingleWithToken("http://www.h4l9000.com/WsMaintenance.asmx", "http://www.h4l9000.com/", "CheckMaintenance", token);
							reponse_maintenance = statut.getSingle("CheckMaintenanceResult");
							System.out.println(reponse_maintenance);
							
							if (reponse_maintenance.compareTo("OUI")==0){
								JOptionPane.showMessageDialog(FrmLogin.this, "Maintenance en cours. Veuillez vous reconnecter plus tard.", "Recover", JOptionPane.WARNING_MESSAGE);
							} else {
								if (reponse_version.compareTo(gen.getCurrentVersion())==0){
									
									System.out.println("Lancement du panneau principal...");
									
									FrmMain principal = new FrmMain(current_user, token);
									principal.setLocation(100, 100);
									principal.setVisible(true);
									
									// --- On masque le panneau de Login ---
									FrmLogin.this.setVisible(false);
									
								} else {
									JOptionPane.showMessageDialog(FrmLogin.this, "Identifiants incorrects", "Recover", JOptionPane.WARNING_MESSAGE);
								}
							}
							
						} else {
							JOptionPane.showMessageDialog(FrmLogin.this, "Veuillez saisir vos identifiants", "Recover", JOptionPane.WARNING_MESSAGE);
							pfMotDePasse.setText("");
						}
						
						
					} else {
						JOptionPane.showMessageDialog(FrmLogin.this, "Veuillez saisir vos identifiants", "Recover", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnConnexion;
	}
	
	private JTextField getTfIdentifiant() {
		if (tfIdentifiant == null) {
			tfIdentifiant = new JTextField();
			tfIdentifiant.setBounds(new Rectangle(90, 50, 160, 20));
		}
		return tfIdentifiant;
	}

	private JPasswordField getPfMotDePasse() {
		if (pfMotDePasse == null) {
			pfMotDePasse = new JPasswordField();
			pfMotDePasse.setBounds(new Rectangle(90, 80, 160, 20));
		}
		return pfMotDePasse;
	}
	
	private String getPassword(char[] password_char){
		return String.valueOf(password_char);
	}
	
	
    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://www.h4l9000.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        //SOAPBody soapBody = envelope.getBody();
//        SOAPElement soapBodyElem = soapBody.addChildElement("ListeCycle", "example");
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Identifiant", "example");
//        soapBodyElem1.addTextNode("TENA");
//        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("MotDePasse", "example");
//        soapBodyElem2.addTextNode("montredon");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "ListeCycle");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        
        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult(outWriter);
        
        transformer.transform(sourceContent, result);
        
        StringBuffer sb = outWriter.getBuffer(); 
        String xml = sb.toString();
                
        //String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><IdentificationResponse xmlns=\"http://www.h4l9000.com/\"><IdentificationResult>1</IdentificationResult></IdentificationResponse></soap:Body></soap:Envelope>";
                
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        
        Document doc = builder.parse(is);
        NodeList list = doc.getElementsByTagName("nom");
        System.out.println(list.item(0).getTextContent());
        System.out.println(list.item(1).getTextContent());
        System.out.println(list.item(2).getTextContent());
    }

}
