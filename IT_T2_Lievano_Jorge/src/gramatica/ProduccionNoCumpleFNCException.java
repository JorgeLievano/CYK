package gramatica;
/**
 * excepcion asociada al cumplimineto de la FNC en una produccion de una regla
 * @author JORGE
 *
 */
public class ProduccionNoCumpleFNCException extends Exception{

	/**
	 * constructor de la excecpcion cuando una produccion no cumple FNC
	 * @param c cadena que contiene informacion para identificar la regla
	 * @param i indice de la produccion dentro de la regla
	 * @param caso cadena que describe el error en la produccion 
	 */
	public ProduccionNoCumpleFNCException(String c,int i, String caso) {
		super("La produccion "+c+" no cumple con FNC, por favor Verifique"+"\n"+
	"La produccion en la posicion "+i+" "+ caso);
	}
	
}
