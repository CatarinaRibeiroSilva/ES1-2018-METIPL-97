package projecto;

public class BDA {

	public static void main (String[] args) {
		FacebookFeed facebookFeed = new FacebookFeed();
		GUI window= new GUI(facebookFeed);
		window.inicia();
	}
}
