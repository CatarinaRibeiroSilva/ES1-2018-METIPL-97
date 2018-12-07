package src.projecto.src.projecto;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Document;

public class BDA {
		/**
		 * 
		 * @param args
		 * @throws Exception
		 */
	
		//FacebookFeed facebookFeed = new FacebookFeed();
		//GUI window= new GUI(facebookFeed);
		
	public static void main (String[] args) throws Exception {
		// inicio teste bam
		
		XMLConfig config= new XMLConfig();
		GUI window= new GUI(config);
		
		//FacebookFeed facebook= new FacebookFeed (window);
		
		TwitterFeed t= new TwitterFeed("ISCTE",window);
		window.inicia();
		
		//Testes de Publicacoes
		Calendar data ;
		data=Calendar.getInstance();
		Date data_limite= new Date(data.get(Calendar.YEAR)-1900, data.get(Calendar.MONTH), data.get(Calendar.DATE)-1, data.get(Calendar.HOUR_OF_DAY), data.get(Calendar.MINUTE));
		Date d1 = new Date (117, 12 , 3,16,50,00);
		
		Date d2 =new Date (117,12,3,12,50,00);
		Date d3 =new Date (117,12,6,12,50,00);
		
		Publicacao p= new Publicacao ("Facebook" , "maria", "Boa tarde em anexo envio as datas dos testes....", d1);
		Publicacao p1= new Publicacao ("Facebook" , "manuel", "Engenharia de Software 1 , Dia 23/11/2018 o segundo sprint é as dsadasdas",d2);
		Publicacao p2= new Publicacao ("Facebook" , "margarida.domingues", "Bom dia Academiia, Hoje o ISCTE bla bla bla bla bla bla bla bla bla", data_limite);
		Publicacao p3= new Publicacao ("Facebook" , "ana", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d2);
		Publicacao p4= new Publicacao ("Twitter" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d1);
		Publicacao p5= new Publicacao ("Email" , "margarida", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xxxx xxxx xxx xxx xxxx ", d3);

		ArrayList<Publicacao> posts = new ArrayList<Publicacao>();
		posts.add(p);
		posts.add(p1);
		posts.add(p2);
		posts.add(p3);
		posts.add(p4);
		posts.add(p5);		
		
		window.update(posts,false);
	
	}
}