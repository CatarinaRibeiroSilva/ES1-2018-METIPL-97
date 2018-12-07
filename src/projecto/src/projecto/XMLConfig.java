package projecto.src.projecto;

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
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLConfig {
	
	public File inputFile;// = new File("config.xml");
	
	public DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    public DocumentBuilder dBuilder;
    public Document docConfig;
    
    public XMLConfig(File file) {
		this.inputFile=file;
	}
              
    public void OpenFile() throws Exception, IOException {
		dBuilder = dbFactory.newDocumentBuilder();
		docConfig = dBuilder.parse(inputFile);
		System.out.println("\n A Carregar Configura��es");
		docConfig.getDocumentElement().normalize();  
    }

	public void AddNewUserToConfig(String account, String passWord) {
        Element newElement = docConfig.createElement("User");
        newElement.setAttribute("Account", account);
        newElement.setAttribute("Password", passWord);
        System.out.println("Root element :" + docConfig.getDocumentElement().getNodeName());         
        Node n = docConfig.getDocumentElement();
        n.appendChild(newElement);
	}
	
	public void AddNewFilterToConfig(String filterName, String filterConfig) {
        Element newElement = docConfig.createElement("Filter");
        newElement.setAttribute("Name", filterName);
        newElement.setAttribute("FilterConfig", filterConfig);
        System.out.println("Root element :" + docConfig.getDocumentElement().getNodeName());         
        Node n = docConfig.getDocumentElement();
        n.appendChild(newElement);
	}
		
	public void AddNewPostToConfig(String socialMedia, String post, String date, String hour ) {
        Element newElement = docConfig.createElement("Filter");
        newElement.setAttribute("Social", socialMedia);
        newElement.setAttribute("Post", post);
        newElement.setAttribute("Date", date);
        newElement.setAttribute("Hour", hour);
        System.out.println("Root element :" + docConfig.getDocumentElement().getNodeName());         
        Node n = docConfig.getDocumentElement();
        n.appendChild(newElement);
	}
      
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
	
	//Devolve o username  de um determinado servi�o definido pela String . Exemplo : String=Facebook , devolve uma String com o username registado do Facebook
	public String getuser(String string) {
		// TODO Auto-generated method stub
		return "xxxx";
	}
//Define no ficheiro a pesquisa das 24horas. Isto � se receber true, altera activa o filtro para as publicacoes das ultimas 24hora. 
	public void setFiltro24(boolean b) {
		// TODO Auto-generated method stub
		
	}
//Define o filtro da pesquisa por palavra. 
	public void setFiltro_pesquisa(String text) {
		// TODO Auto-generated method stub
		
	} 
	
	//Devolve as publicacoes guardadas no ficheiro CONFIG 
	
		public ArrayList<Publicacao> getPublicacoes() {
			// TODO Auto-generated method stub
			ArrayList<Publicacao> p =new ArrayList<Publicacao>();
			return p; 
		}
	
}