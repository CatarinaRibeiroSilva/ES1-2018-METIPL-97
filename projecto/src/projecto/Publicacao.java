package projecto.src.projecto;

import java.util.Date;

public class Publicacao implements Comparable <Publicacao>{
	private String tipo ;
	private String origem;
	private String mensagem;
	private String titulo;
	private String data;
	private Date date;

	public Publicacao (String tipo, String origem, String mensagem, Date date)  {
		this.tipo=tipo;
		this.origem=origem;
		this.mensagem=mensagem;
		this.date=date;
		titulo=mensagem.substring(0, 15);
		}



	public String getTipo() {
		return tipo;
	}

	public String getOrigem() {
		return origem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getData() {
		return date;
	}

	@Override
	public int compareTo(Publicacao arg0) {
		return getData().compareTo(arg0.getData());
	}

	
}