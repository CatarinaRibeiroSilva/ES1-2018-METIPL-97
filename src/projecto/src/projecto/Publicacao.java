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
	 * String- Define a fonte da publica��o (Ex. Facebook, Twitter, Email)
	 * @param origem
	 * String  - Define o autor da publica��o
	 * @param  mensagem
	 * String - Define a Mensagem da publica��o
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


/** M�todo que retorna o tipo da publica��o 
 * @return  tipo
 * Devolve uma String com o tipo da publica��o ((Ex. Facebook, Twitter, Email))
 */

	public String getTipo() {
		return tipo;
	}
	/** M�todo que retorna o autor da publica��o 
	 * @return  origem
	 * String com o autor da publica��o
	 */

	public String getOrigem() {
		return origem;
	}

	/** M�todo que retorna a mensagem da publicacao
	 * @return mensagem 
	 * Devolve a String correspondente a mensagem 
	 */

	public String getMensagem() {
		return mensagem;
	}
	/** M�todo que retorna o titulo da mensagem
	 * @return titulo
	 * String com o titulo da mensagem  
	 */

	
	public String getTitulo() {
		return titulo;
	}
	/** M�todo que retorna a data da mensagem 
	 * @return date
	 * Date
	 */

	public Date getData() {
		return date;
	}

	/**M�todo que compara duas publica��es de modo a ordena-las
	 * return int 
	 */

	@Override
	public int compareTo(Publicacao arg0) {
		return getData().compareTo(arg0.getData());
	}

	
}