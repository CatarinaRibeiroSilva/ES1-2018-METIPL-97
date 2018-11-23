package projecto;

import java.util.Date;

public class Publicacao implements Comparable <Publicacao>{
	private String tipo ;
	private String origem;
	private String mensagem;
	private String titulo;
	private Date data;

	public Publicacao (String tipo, String origem, String mensagem, Date data)  {
		this.tipo=tipo;
		this.origem=origem;
		this.mensagem=mensagem;
		this.data=data;
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
		return data;
	}

	@Override
	public int compareTo(Publicacao arg0) {
	
		return 0;
	}

}