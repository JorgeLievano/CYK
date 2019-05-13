package gramatica;
/**
 * Excepcion relacionada al mal nombramiento de variables en una gramatica
 * @author JORGE
 *
 */
public class VariableMalNombradaException extends Exception {
/**
 * contructor de la excepcion para variables mal nombradas
 * @param c cadena que que brinda la informacion para identificar la variable mal nombrada
 */
	public VariableMalNombradaException(String c){
		super("El nombre de la regla "+c+" debe ser una caracter en mayuscula");
	}
	
}
