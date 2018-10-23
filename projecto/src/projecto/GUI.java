package projecto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


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
		
		frame.add(barra);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	
	}
	
	

	private void desenha_painelPosts() {
		
		
	  
	    posts = new JTextArea ("          ");
	    scroll_posts = new JScrollPane(posts);
	    posts.setLineWrap(true);
	    
	  

	
	}



	private Component desenha_config() {
	
		JPanel config= new JPanel ();
		
		
		return config;
	}



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
		check_facebook = new JCheckBox ();

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

	
	
}
