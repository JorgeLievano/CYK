package gramatica;
/**
 * excepcion relacionada a operaciones con una GNFC sin reglas de produccion  establecidas
 * @author JORGE
 *
 */
public class GramaticaVaciaException extends Exception {

	/**
	 * constructor de la excepcion donde se indica que la gramatica esta vacia
	 */
	public GramaticaVaciaException(){
		super("La gramatica no contiene ninguna regla de produccion");
	}
	
}
