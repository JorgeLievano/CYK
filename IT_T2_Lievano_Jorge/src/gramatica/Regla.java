package gramatica;

/**
 * Clase que define una regla de Produccion de una gramatica con su respectivo nombre(id) y su conjunto de producciones contenidos en una cadena separados por '|'
 * @author JORGE
 *
 */
public class Regla {

	private char id;// id es el nombre de la regla o variable
	private String producciones;
	
	
/**
 * contructor de la clase regla donde se define su nombre y conjunto de producciones
 * @param id nombre que sera asignado a la regla, caracter de tipo literal en mayuscula
 * @param producciones cadena del conjunto de producciones en FNC separadas por '|'
 * @throws VariableMalNombradaException
 */
	public Regla(char id,String producciones) throws VariableMalNombradaException{
		if(!Character.isUpperCase(id)){
			throw new VariableMalNombradaException(id+" -> "+producciones);// por conveniencia las variables solo deben ser letras mayusculas
		}
		this.id=id;
		this.producciones=producciones;
	}
	
	/**
	 * Comprueba si una expresion se encuentra dentro de las producciones de la regla
	 * @param expresion producion del tipo AB o a, donde A y B son variables, a es un terminal
	 * @return true si la expresion se encuentra dentro del conjunto de producciones: false de lo contrario
	 */
	public boolean produce(String expresion){
		boolean ret=(producciones.contains(expresion))?true:false;// si la cadena de producciones d ela regla contiene la expresion se retorna verdadero, 
		return ret;
	}
	
	/**
	 * retorna el nombra o identificador de la regla, caracter de tipo literal mayuscula
	 * @return identificador de la regla
	 */
	public char getId(){
		return id;
	}
}
