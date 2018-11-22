package projecto;

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
//private FacebookFeed facebookFeed;

	//public GUI (FacebookFeed facebookFeed) {
		//this.facebookFeed = facebookFeed;
		
private JList<String> lista_posts;
private  DefaultListModel<String> model ;
private String selectedValue;
private ArrayList<Publicacao> lista_publicacoes;

public GUI () {
		
		frame = new JFrame ();
		
		frame.setLayout(new BorderLayout() );
	
		painel = new JSplitPane();
		
		desenha_painel_seleccao();
		desenha_painelPosts();
		
		painel.setRightComponent(scroll_posts);
	    painel.setLeftComponent(painel_seleccao);
		
		
		barra = new JTabbedPane();
		barra.addTab("BDA", painel);
		barra.addTab("Config", desenha_config());
		
		lista_publicacoes = new ArrayList<Publicacao>();
		
		frame.add(barra);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	
	}
		
//desenha o  painel das publicações
	private void desenha_painelPosts() {
		
		//facebookFeed.getTimeLinePost();
	  
	    //posts = new JTextArea ("          ");
	    //scroll_posts = new JScrollPane(posts);
	    //posts.setLineWrap(true);
	  
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

	private Component desenha_config() {
	
		JPanel config= new JPanel ();
		return config;
	}

//desenha o painel dos filtros para fazer a selecção das fontes a aparecer 
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

	public void inicia() {
		
		frame.pack();
		frame.setVisible(true);
				
	}

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
