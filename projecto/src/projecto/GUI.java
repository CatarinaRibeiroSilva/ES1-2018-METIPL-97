package projecto.src.projecto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
private JPanel configPanel;
private  JScrollPane scroll_config;
private File inputFile; 
private JCheckBox ultimas;
private boolean pesquisa_kw;
private String palavra_chave;
private ArrayList<Publicacao> lista_publicacoes_filtradas;
private XMLConfig config ;


public GUI (XMLConfig config) throws Exception, Exception {
		
		this.config=config;
		
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
		barra.addTab("Config",configPanel);
		
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
		
		configPanel = new JPanel ();
		configPanel.setLayout(new GridLayout(3,0));
		
		//Parte referente aos dados dos utilizadores 
		JPanel config_dados = new JPanel ();
		config_dados.setLayout(new GridLayout(3,2));
		
		JLabel twitter = new JLabel("Twitter");
		
		JLabel user= new JLabel("Username");
		
		String user_twitter = config.getuser("Twitter");
		
		JTextArea user_text= new JTextArea(user_twitter);
		JLabel pass= new JLabel("Password");
		
		
		JPasswordField passwordtwitter = new JPasswordField(10);
		
		JPanel user_passt= new JPanel();
		user_passt.setLayout(new GridLayout(2,2));
		user_passt.add(user);
		user_passt.add(user_text);
		user_passt.add(pass);
		user_passt.add(passwordtwitter);
		
		
		JLabel facebook = new JLabel("Facebook");
		JLabel user_face= new JLabel("Username");
		JLabel pass_face= new JLabel("Password");

		JPasswordField passwordfacebook = new JPasswordField(10);
		String user_facebook = config.getuser("Facebook");
		JTextArea user_text_facebook= new JTextArea(user_facebook);

		JPanel user_passf= new JPanel();
		user_passf.setLayout(new GridLayout(2,2));
		user_passf.add(user_face);
		user_passf.add(user_text_facebook);
		user_passf.add(pass_face);
		user_passf.add(passwordfacebook);
		
		
		
		JLabel email = new JLabel("Email");
		JLabel user_email= new JLabel("Username");
		JLabel pass_email= new JLabel("Password");
		JPasswordField passwordemail = new JPasswordField(10);
		
		String username_email = config.getuser("Email");
		JTextArea user_text_email= new JTextArea(username_email);

		JPanel user_pass_email= new JPanel();
		user_pass_email.setLayout(new GridLayout(2,2));
		user_pass_email.add(user_email);
		user_pass_email.add(user_text_email);
		user_pass_email.add(pass_email);
		user_pass_email.add(passwordemail);
		

		config_dados.add(twitter);
		config_dados.add(user_passt);
		
		config_dados.add(facebook);
		config_dados.add(user_passf);

		config_dados.add(email);
		config_dados.add(user_pass_email);
		
		//Parte referente aos filtros
		JPanel config_filtros = new JPanel();
		config_filtros.setLayout(new GridLayout(3,2));
		
		JLabel filtros= new JLabel("Filtros: ");
		config_filtros.add(filtros);
		JLabel vazio=new JLabel("");
		config_filtros.add(vazio);
		
		JLabel filtro_palavra_chave= new JLabel("Palavra-chave");
		JTextArea palavra = new JTextArea();
		config_filtros.add(filtro_palavra_chave);
		config_filtros.add(palavra);
		
		JLabel ultimas_24h= new JLabel("Ultimas 24 horas: ");
		ultimas= new JCheckBox();
		config_filtros.add(ultimas_24h);
		config_filtros.add(ultimas);
		
		JPanel aplicar_panel= new JPanel();
		aplicar_panel.setLayout(null);
		JButton aplicar = new JButton ("Aplicar");
		aplicar.setSize(100, 50);
		//aplicar.addActionListener(new ActionListener())
		aplicar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ultimas.isSelected())
					config.setFiltro24(true);
				if(!palavra.getText().equals(null)) {
					config.setFiltro_pesquisa(palavra.getText());
					palavra_chave=palavra.getText();
					pesquisa_kw=true;
				}
				
				update(filtros(),true);

			}
			});
			
		aplicar_panel.add(aplicar, BorderLayout.CENTER);

		configPanel.add(config_dados);
		configPanel.add(config_filtros);
		configPanel.add(aplicar_panel);
	
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
	//Actualiza o painel de publicacões
	public void update(ArrayList<Publicacao> publicacoes, boolean b) {
		
		model = new DefaultListModel<>();
		lista_posts.setModel(model);
		//Collections.sort(publicacoes);
		if(b) {
			Collections.sort(publicacoes);
			for (Publicacao post : lista_publicacoes_filtradas){
				model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
				
			 	}
		}
	else {
		
		if(lista_publicacoes.size()==0) {
			lista_publicacoes=publicacoes;
			Collections.sort(publicacoes);

		}
		
		else {
			lista_publicacoes.addAll(publicacoes);
			Collections.sort(publicacoes);

		}
		
	for (Publicacao post : lista_publicacoes){
			model.addElement(post.getTipo() + " - " + post.getOrigem() + " - " +post.getTitulo() + " - " + post.getData() + "\n");
			
		 	}
	}
	}
		

	//Função que executa as configurações estabelecidas 
	public ArrayList<Publicacao> filtros() {
	
		lista_publicacoes_filtradas = new ArrayList<>();
		if(!ultimas.isSelected() && !pesquisa_kw) {
			System.out.println("AQUIII");
			update(lista_publicacoes,false);
			return lista_publicacoes;
		}
		if(ultimas.isSelected()) {
			for (Publicacao post : lista_publicacoes){
				
				Date d= post.getData();
				int post_horas=d.getHours();
				int post_minutos=d.getMinutes();
				int post_day=d.getDate();
				
				
				Calendar data;
				data=Calendar.getInstance();
				Date data_limite= new Date(data.get(Calendar.YEAR)-1900, data.get(Calendar.MONTH), data.get(Calendar.DATE)-1, data.get(Calendar.HOUR_OF_DAY), data.get(Calendar.MINUTE)) ;
				
				if(d.getMonth()==data_limite.getMonth()) {
				
					if(d.getDate()>=data_limite.getDate()) {
						System.out.println("Datas do dia anterior" + d.getDate());
						lista_publicacoes_filtradas.add(post);
							}
				}
			
				
				
		}
		
		}
			if(pesquisa_kw) {
				for (Publicacao post : lista_publicacoes){

					String texto= post.getMensagem();
					
					String [] palavras =texto.split("\\s+|\\.|,|;");
					int contador=0;
					
					if(palavra_chave.contains(" ")){
						
						for (int i =0 ; i<texto.length()-palavra_chave.length() ; i++){
							
							Character procura_primeiro = texto.charAt(i);
							Character procura_ultimo = texto.charAt(palavra_chave.length()+i-1);
							
							Character primeiro_caracter_palavra = palavra_chave.charAt(0);
							Character ultimo_caracter_palavra = palavra_chave.charAt(palavra_chave.length()-1);
							
							if(procura_primeiro.equals(primeiro_caracter_palavra) && procura_ultimo.equals(ultimo_caracter_palavra)){
								String str =texto.substring(i, i+palavra_chave.length());
								
									if(str.equals(palavra_chave))
											contador=contador+1;
									
							}
							if(contador>0 && i==texto.length()-palavra_chave.length()-1){
								if(!lista_publicacoes_filtradas.contains(post))
									lista_publicacoes_filtradas.add(post);
								
							}
							
							
						}
					}
					else{
					
						for (int i=0 ; i<palavras.length ; i++){
					
							String t=palavras[i];
							if(t.equals(palavra_chave)){
								contador=contador+1;
								}
							if(contador>0 && i==palavras.length-1){
								if(!lista_publicacoes_filtradas.contains(post))
									lista_publicacoes_filtradas.add(post);
							
								
								}
					
						}
					}
					
					
				}
							
				}				
			return lista_publicacoes_filtradas;

			}
			
}


