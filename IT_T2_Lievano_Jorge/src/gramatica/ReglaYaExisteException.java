package gramatica;
/**
 * Excepcion producida al intentar agregar un id que no es unico
 * @author JORGE
 *
 */
public class ReglaYaExisteException extends Exception {
/**
 * constructor de la excepcion cuando una regla posee variable de identificacion repetido
 * @param regla cadena que contiene informacion para identificar la regla repetida
 */
	public ReglaYaExisteException(String regla) {
		super("Existen varias reglas nombradas como '"+regla+"'.\n Cada regla debe poseer un identifiador unico ");
		// TODO Auto-generated constructor stub
	}

	

	
	
}
