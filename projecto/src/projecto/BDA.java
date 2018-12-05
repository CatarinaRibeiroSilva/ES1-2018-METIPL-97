package projecto.src.projecto;

import java.util.ArrayList;

import org.w3c.dom.Document;

public class BDA {

		//FacebookFeed facebookFeed = new FacebookFeed();
		//GUI window= new GUI(facebookFeed);
		
	public static void main (String[] args) throws Exception {
		// inicio teste bam
		GUI window= new GUI();
		
		
		//FacebookFeed facebook= new FacebookFeed (window);
		
		TwitterFeed t= new TwitterFeed("ISCTE",window);
		window.inicia();
		//Testes de Publicacoes
		/**Publicacao p= new Publicacao ("Facebook" , "maria", "Boa tarde em anexo envio as datas dos testes....", "21/11/2018");
		Publicacao p1= new Publicacao ("Facebook" , "manuel", "Engenharia de Software 1 , Dia 23/11/2018 o segundo sprint é as dsadasdas", "21/11/2018");
		Publicacao p2= new Publicacao ("Facebook" , "margarida.domingues", "Bom dia Academiia, Hoje o ISCTE bla bla bla bla bla bla bla bla bla", "21/11/2018");
		Publicacao p3= new Publicacao ("Facebook" , "ana", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", "21/11/2018");

		Publicacao p4= new Publicacao ("Twitter" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", "21/11/2018");
		Publicacao p5= new Publicacao ("Email" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", "21/11/2018");

		ArrayList<Publicacao> posts = new ArrayList<Publicacao>();
		posts.add(p);
		posts.add(p1);
		posts.add(p2);
		posts.add(p3);
		posts.add(p4);
		posts.add(p5);		
		*///window.update(posts);
	}
}