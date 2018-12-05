package projecto.src.projecto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

import twitter4j.UserList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class GUI {
private JFrame frame;
private JSplitPane painel ;
private JPanel painel_seleccao;
private JScrollPane scroll_posts; 
private JCheckBox check_todos;
private JCheckBox check_twitter;
private JCheckBox check_email;
private JCheckBox check_facebook;
private JTabbedPane barra;
private JTextArea posts;
private JList<String> lista_posts;
private  DefaultListModel<String> model ;
private String selectedValue;
private ArrayList<Publicacao> lista_publicacoes;
private JPanel configPanel= new JPanel ();
private  JScrollPane scroll_config;
private File inputFile; 
private JTextArea configText;

public GUI () throws Exception, Exception {
		
		frame = new JFrame ();
		frame.setLayout(new BorderLayout() );
		painel = new JSplitPane();
		desenha_painel_seleccao();
		desenha_painelPosts();
		desenha_config();
		
		painel.setRightComponent(scroll_posts);
	    painel.setLeftComponent(painel_seleccao);
		
		barra = new JTabbedPane();
		barra.addTab("BDA", painel);
		barra.addTab("Config", configPanel);
		
		lista_publicacoes = new ArrayList<Publicacao>();
		
		frame.add(barra);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
		
//desenha o  painel das publicações e selecciona as publicações a apresentar 
	private void desenha_painelPosts() {
		lista_posts = new JList<String>();
	       lista_posts.addListSelectionListener(new ListSelectionListener() {
    	@Override
		public void valueChanged(ListSelectionEvent e) {
				   if(!e.getValueIsAdjusting()) {
		                 selectedValue = lista_posts.getSelectedValue();
		                  
		                  for (Publicacao post : lista_publicacoes){
		                	  String n= post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n";
			                  int post_seleccionado =selectedValue.compareTo(n);
			                  if(post_seleccionado==0) {
			                	  post.getMensagem();
			                	  DesenhaPost(post) ;// Post = new DesenhaPost(post);
			                	  }
		                  }
		                }
    	}
    	});
	       scroll_posts = new JScrollPane(lista_posts);
	       posts=new JTextArea();
	       posts.setLineWrap(true);
	}
	 //Desenha a publicação desejada numa nova janela 
	protected void DesenhaPost(Publicacao post) {
		JFrame janela = new JFrame ("POST");
		janela.setLayout(new BorderLayout() );

		JTextArea cabecalho= new JTextArea(post.getTipo() + " - " + post.getData() + " - " + post.getOrigem());
		JTextArea texto = new JTextArea(post.getMensagem());
		
		janela.add(texto, BorderLayout.CENTER);
		janela.add(cabecalho, BorderLayout.NORTH);
		
		Dimension d = new Dimension (400,400);
		janela.setSize(d);
		janela.setVisible(true);	
	}
	 //Desenha o painel de Configurações 
	private void desenha_config() {
		try {
		inputFile = new File("config.xml");
		
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document docConfig = dBuilder.parse(inputFile);
        docConfig.getDocumentElement().normalize();         
        System.out.println("\n A Carregar Configurações");
		
	    	
	    XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    XPathExpression expr = xpath.compile("/XML/Service/@*");
	    NodeList nl = (NodeList) expr.evaluate(docConfig, XPathConstants.NODESET);
	      for (int i = 0; i < nl.getLength(); i++) {
	    	  System.out.print(nl.item(i).getNodeName()  + ": ");
	          System.out.println(nl.item(i).getFirstChild().getNodeValue());
	        }
	        
	        // Directorias    
	        expr = xpath.compile("/XML/Paths/docPath");
	        String str = (String) expr.evaluate(docConfig, XPathConstants.STRING);
	        JTextArea  pathList = new JTextArea("docPath: " + str);
	        System.out.println("docPath: " + str);
	        
	        scroll_config = new JScrollPane(configText);
	       //configText.add(usersHeader);
	       //configText.add(usersList);
	       //configText.add(pathHeader);
	       //configText.add(pathList);  
	        SaveXML(docConfig);
		}
		catch (Exception e) { e.printStackTrace(); }
	}

	
	private void AddNewConfig(Document doc) {
		
        Element newElement1 = doc.createElement("Service");
        newElement1.setAttribute("Protocol", "smtp");
        newElement1.setAttribute("Account", "manuel@iscte-iul.pt");
        newElement1.setAttribute("Password", "xyzw");
        
        // Adding new element OtherNewTag to the XML document (root node)
        System.out.println("----- Adding new element <OtherNewTag> to the XML document -----");

        Element newElement2 = doc.createElement("OtherNewTag");
        newElement2.setTextContent("More new data"); 
        
        // Add new nodes to XML document (root element)
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());         
        Node n = doc.getDocumentElement();
        n.appendChild(newElement1);
        n.appendChild(newElement2);   
    }      
      
	private void SaveXML(Document doc) {
		try {
        // Save XML document
        //System.out.println("\nSave XML document.");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new FileOutputStream("config.xml"));
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
		}
		catch (Exception e) { e.printStackTrace(); }
     } 
	

	 //desenha o painel dos filtros para fazer a selecção das fontes a aparecer , implementa os filtros do Twitter,Facebook e Email 	
	private void desenha_painel_seleccao() {
	
		painel_seleccao= new JPanel() ;
		painel_seleccao.setLayout(new GridLayout(4,2));
		
		JLabel todos= new JLabel("Seleccionar todos");
		JLabel twitter= new JLabel("Twitter");
		JLabel email= new JLabel("Email");
		JLabel facebook= new JLabel("Facebook");

		check_todos = new JCheckBox ();
		check_twitter = new JCheckBox ();
		check_email = new JCheckBox ();
		check_facebook = new JCheckBox();
	
		ItemListener listener = new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		    Object source = e.getItemSelectable();
		    if(check_facebook.isSelected() && !check_twitter.isSelected() && !check_email.isSelected()) {
		    	model.clear();
		    	for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Facebook" ) {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		      }
		    	
		      if(check_twitter.isSelected() && !check_facebook.isSelected() && !check_email.isSelected()) {
		    	  model.clear();
		    	  for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Twitter") {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		      }
		    	
		      if(check_email.isSelected() && !check_facebook.isSelected() && !check_twitter.isSelected()) {
		    	  model.clear();
		    	  for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Email") {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		      }
		     
		      //2 COMBINAÇÕES
		      if(check_facebook.isSelected() && check_twitter.isSelected() && !check_email.isSelected()) {
		    	 model.clear();
		    	  for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Facebook" || post.getTipo()=="Twitter") {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		    	  	System.out.println("FACEBOOK e Twitter");
			      }
		      if(check_facebook.isSelected() && !check_twitter.isSelected() && check_email.isSelected()){
		    	  model.clear();
		    	  for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Facebook" || post.getTipo()=="Email") {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		      }
		      if(check_twitter.isSelected() && !check_facebook.isSelected() && check_email.isSelected() ) {
		    	  model.clear();
		    	  for (Publicacao post : lista_publicacoes){
		    		  if(post.getTipo()=="Email" || post.getTipo()=="Twitter") {
		    			 model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
		    		  	}
		    	  }
		      }
		      
		      if((check_facebook.isSelected() && check_twitter.isSelected() && check_email.isSelected()) || check_todos.isSelected()) {
		    		 model.clear();
		    		
			    	 for (Publicacao post : lista_publicacoes){
			    		  model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
			    		  	}
			    		  
			    	  }
		    	  
		    	
		    }
		};
		check_todos.addItemListener(listener);
		check_twitter.addItemListener(listener);
		check_email.addItemListener(listener);
		check_facebook.addItemListener(listener);

		
		painel_seleccao.add(todos);
		painel_seleccao.add(check_todos);
		painel_seleccao.add(twitter);
		painel_seleccao.add(check_twitter);

		painel_seleccao.add(email);
		painel_seleccao.add(check_email);

		painel_seleccao.add(facebook);
		painel_seleccao.add(check_facebook);
}
	  //Inicia o programa 
	public void inicia() {
		frame.pack();
		frame.setVisible(true);
	}
	//Faz o update das publicações publicadas por Facebook, Twitter e Email
	public void update(ArrayList<Publicacao> publicacoes) {
		
		model = new DefaultListModel<>();
		lista_posts.setModel(model);
		//Collections.sort(publicacoes);
		
		if(lista_publicacoes.size()==0) {
			lista_publicacoes=publicacoes;
		}
		else {
			lista_publicacoes.addAll(publicacoes);
		}
		
	for (Publicacao post : lista_publicacoes){
			model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
			
		 	}
	
	}
		
}
