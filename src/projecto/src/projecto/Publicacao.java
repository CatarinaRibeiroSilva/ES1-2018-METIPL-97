package src.projecto.src.projecto;

import java.util.Date;

public class Publicacao implements Comparable <Publicacao>{
	private String tipo ;
	private String origem;
	private String mensagem;
	private String titulo;
	private String data;
	private Date date;
	/** Classe que define as Publicacoes 
	 * @param  tipo 
	 * String- Define a fonte da publicação (Ex. Facebook, Twitter, Email)
	 * @param origem
	 * String  - Define o autor da publicação
	 * @param  mensagem
	 * String - Define a Mensagem da publicação
	 * @param  date
	 * Date -Define data da Publicacao
	 *
	 */

	public Publicacao (String tipo, String origem, String mensagem, Date date)  {
		this.tipo=tipo;
		this.origem=origem;
		this.mensagem=mensagem;
		this.date=date;
		titulo=mensagem.substring(0, 15);
		}


/** Método que retorna o tipo da publicação 
 * @return  tipo
 * Devolve uma String com o tipo da publicação ((Ex. Facebook, Twitter, Email))
 */

	public String getTipo() {
		return tipo;
	}
	/** Método que retorna o autor da publicação 
	 * @return  origem
	 * String com o autor da publicação
	 */

	public String getOrigem() {
		return origem;
	}

	/** Método que retorna a mensagem da publicacao
	 * @return mensagem 
	 * Devolve a String correspondente a mensagem 
	 */

	public String getMensagem() {
		return mensagem;
	}
	/** Método que retorna o titulo da mensagem
	 * @return titulo
	 * String com o titulo da mensagem  
	 */

	
	public String getTitulo() {
		return titulo;
	}
	/** Método que retorna a data da mensagem 
	 * @return date
	 * Date
	 */

	public Date getData() {
		return date;
	}

	/**Método que compara duas publicações de modo a ordena-las
	 * return int 
	 */

	@Override
	public int compareTo(Publicacao arg0) {
		return getData().compareTo(arg0.getData());
	}

	
}