package projecto;

import java.util.ArrayList;
import java.util.Date;

public class BDA {


	public static void main (String[] args) {
		// inicio teste bam

		GUI window= new GUI();
		window.inicia();
		
		//FacebookFeed facebook= new FacebookFeed (window);

		//Testes de Publicacoes
		Date d = new Date ();
		Publicacao p= new Publicacao ("Facebook" , "maria", "Boa tarde em anexo envio as datas dos testes....", d);
		Publicacao p1= new Publicacao ("Facebook" , "manuel", "Engenharia de Software 1 , Dia 23/11/2018 o segundo sprint é as dsadasdas", d);
		Publicacao p2= new Publicacao ("Facebook" , "margarida.domingues", "Bom dia Academiia, Hoje o ISCTE bla bla bla bla bla bla bla bla bla", d);
		Publicacao p3= new Publicacao ("Facebook" , "ana", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d);

		Publicacao p4= new Publicacao ("Twitter" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d);
		Publicacao p5= new Publicacao ("Email" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d);

		ArrayList<Publicacao> posts = new ArrayList<Publicacao>();
		posts.add(p);
		posts.add(p1);
		posts.add(p2);
		posts.add(p3);
		posts.add(p4);
		posts.add(p5);
		
		window.update(posts);
	}
}
//