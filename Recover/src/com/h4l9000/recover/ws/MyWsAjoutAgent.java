package com.h4l9000.recover.ws;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MyWsAjoutAgent {

	private SOAPConnectionFactory soapConnectionFactory  = null;
	private SOAPConnection soapConnection = null;
	
	private String url = "";
	private static String namespace = "";
    private static String methode = "";
    
    private static String token = "";
    
    private static String nom = "";
    private static String prenom = "";
    private static String date_naissance = "";
    private static String sexe = "";
    
    
    private Document docReponse = null;
    
    public MyWsAjoutAgent(String strURL, String strNameSpace, String strMethodeWeb, String strToken, String strNom, String strPrenom, String strDateNaissance, String strSexe){
    	try {
			// --- Enregistrement des variables ---
			url = strURL;
			namespace = strNameSpace;
			methode = strMethodeWeb;
			
			token = strToken;
			
			nom = strNom;
			prenom = strPrenom;
			date_naissance = strDateNaissance;
			sexe = strSexe;
			
			// --- Cr�ation de la connexion SOAP ---
	        soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        soapConnection = soapConnectionFactory.createConnection();

	        // --- Envoi du message SOAP au serveur ---    
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(token, nom, prenom, date_naissance, sexe), url);
	        
	        // --- On transforme la r�ponse SOAP en chaine de caract�res ---
	        String reponse_xml = getReponse(soapResponse);
	        
	        //System.out.println(reponse_xml);
	        
	        docReponse = generateDocument(reponse_xml);
	        		
		} catch (Exception ex){
			System.out.println("### MyWsAuthentification ### Erreur Envoi : " + ex.toString());
		}
    }
    
    private static SOAPMessage createSOAPRequest(String strAuthenticatedToken, String strNom, String strPrenom, String strDateNaissance, String strSexe) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = namespace;

        // --- Cr�ation de l'enveloppe SOAP ---
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);
        
        //System.out.println("Token = " + strAuthenticatedToken);
        
        SOAPHeader soapHead = envelope.getHeader();
        SOAPElement soapHeadElem = soapHead.addChildElement("SecuredWebServiceHeader", "example");
        SOAPElement soapBodyElem1 = soapHeadElem.addChildElement("AuthenticatedToken", "example");
        soapBodyElem1.addTextNode(strAuthenticatedToken);
        
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem_var = soapBody.addChildElement("AjoutAgent", "example");
        SOAPElement soapBodyElem_var1 = soapBodyElem_var.addChildElement("strNom", "example");
        soapBodyElem_var1.addTextNode(strNom);
        SOAPElement soapBodyElem_var2 = soapBodyElem_var.addChildElement("strPrenom", "example");
        soapBodyElem_var2.addTextNode(strPrenom);
        SOAPElement soapBodyElem_var3 = soapBodyElem_var.addChildElement("strDateNaissance", "example");
        soapBodyElem_var3.addTextNode(strDateNaissance);
        SOAPElement soapBodyElem_var4 = soapBodyElem_var.addChildElement("strSexe", "example");
        soapBodyElem_var4.addTextNode(strSexe);

        
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

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + methode);

        soapMessage.saveChanges();

        return soapMessage;
    }
    
    private String getReponse(SOAPMessage soapResponse){
		
		String xml = "";
		
		try {
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        Source sourceContent = soapResponse.getSOAPPart().getContent();
	        
	        StringWriter outWriter = new StringWriter();
	        StreamResult result = new StreamResult(outWriter);
	        
	        transformer.transform(sourceContent, result);
	        
	        StringBuffer sb = outWriter.getBuffer(); 
	        xml = sb.toString();
	        
		} catch (Exception ex){
			System.out.println("### MyWsAuthentification ### Erreur getReponse : " + ex.toString());
		}
        
        return xml;
	}
    
    private Document generateDocument(String strXML){
		
		Document doc = null;
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        InputSource is = new InputSource(new StringReader(strXML));
	        
	        doc = builder.parse(is);
	        
		} catch (Exception ex){
			System.out.println("### MyWsDataset ### Erreur getDocument : " + ex.toString());
		}

        return doc;
	}
	
	public Document getDocument(){
		return docReponse;
	}
	
	public String getSingle(String strTagReponse){
		NodeList list = docReponse.getElementsByTagName(strTagReponse);
		
		return list.item(0).getTextContent().toString();
	}
    
    
}
