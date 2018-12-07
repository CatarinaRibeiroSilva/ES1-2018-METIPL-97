package src.projecto.src.projecto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/** Classe que gera informação de configuração que está no ficheiro "config.xml"
 * 
 * @author Catarina Silva
 *
 */
public class XMLConfig {
	
	public File inputFile = new File("config.xml");
	public DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    public DocumentBuilder dBuilder;
    public Document docConfig;
    /**
     * Inicialização da class XMLConfig 
     * @param file
     * File é ficheiro onde estão gravadas todas as configurações da App
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws Exception 
     */
    public XMLConfig() throws ParserConfigurationException, Exception, IOException {
		dBuilder = dbFactory.newDocumentBuilder();
		docConfig = dBuilder.parse(inputFile);
		System.out.println("\n A Carregar Configurações");
		docConfig.getDocumentElement().normalize(); 
	}
	
    /**
     * Lê as contas já existentes no xml, envia as mesmas para a interface
     * @param social
     * consoante a rede social em causa, irá devolver a conta correspondente
     * @return
     * devolve a conta
     * @throws XPathExpressionException
     * quando não consegue ler o xml
     */
    public String getUser(String social) throws XPathExpressionException {
		String account = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    if (social.equals("Facebook")) {
	    	XPathExpression socialMedia = xpath.compile("/XML/Facebook/@*");
	        NodeList nl = (NodeList) socialMedia.evaluate(docConfig, XPathConstants.NODESET);
	        account = nl.item(0).getFirstChild().getNodeValue();  	 
	    }
	    
	    if (social.equals("Twitter")) {
	    	XPathExpression socialMedia = xpath.compile("/XML/Twitter/@*");
	        NodeList nl = (NodeList) socialMedia.evaluate(docConfig, XPathConstants.NODESET);
	        account = nl.item(0).getFirstChild().getNodeValue();
	    }
	    
	    if (social.equals("Email")) {
	    	XPathExpression socialMedia = xpath.compile("/XML/Gmail/@*");
	        NodeList nl = (NodeList) socialMedia.evaluate(docConfig, XPathConstants.NODESET);
	        account = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return account;
	}

	public void setFiltro24(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setFiltro_pesquisa(String text) {
		// TODO Auto-generated method stub
	}
	

    /**
     * Guarda todas as configurações adicionadas ao xml
     */
	public void SaveXML() {
		try {
        // Save XML document
        //System.out.println("\nSave XML document.");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new FileOutputStream("config.xml"));
        DOMSource source = new DOMSource(docConfig);
        transformer.transform(source, result);
		}
		catch (Exception e) { e.printStackTrace(); }
     }

	public ArrayList<Publicacao> getPublicacoes() {
		// TODO Auto-generated method stub
		return null;
	}

}