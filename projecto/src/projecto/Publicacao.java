package projecto.src.projecto;

import java.util.Date;

public class Publicacao implements Comparable <Publicacao>{
	private String tipo ;
	private String origem;
	private String mensagem;
	private String titulo;
	private String data;
	private Date date;
/** Classe que define as Publicacoes 
 * @param String tipo - fonte da publicação (Ex. Facebook, Twitter, Email)
 * @param String origem - autor da publicação
 * @param String mensagem - Mensagem da publicação
 * @param Date date- data da Publicacao
 */
	public Publicacao (String tipo, String origem, String mensagem, Date date)  {
		this.tipo=tipo;
		this.origem=origem;
		this.mensagem=mensagem;
		this.date=date;
		titulo=mensagem.substring(0, 15);
		}

/** Método que retorna o tipo da publicação (Ex. Facebook, Twitter, Email)
 * @return String- tipo
 */

	public String getTipo() {
		return tipo;
	}
	/** Método que retorna o autor da publicação 
	 * @return String- origem
	 */

	public String getOrigem() {
		return origem;
	}

	/** Método que retorna a mensagem da publicacao
	 * @return String- mensagem 
	 */
	public String getMensagem() {
		return mensagem;
	}
	/** Método que retorna o titulo da mensagem
	 * @return String- titulo 
	 */
	
	public String getTitulo() {
		return titulo;
	}
	/** Método que retorna a data da mensagem 
	 * @return Date- date
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