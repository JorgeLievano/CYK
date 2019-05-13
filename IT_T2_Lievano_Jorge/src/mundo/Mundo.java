package mundo;

import gramatica.*;

/**
 * Clase que intermediaria entre la GUI  y la represesntacion de la GFNC. Gestiona el uso adecuado de los metodos de la GFNC para conectarse con la GUI en esta solucion 
 * @author JORGE
 *
 */
public class Mundo {

	private GramaticaFNC g;
	
	/**
	 * Contructor del mundo, iniciailiza la GNFC
	 */
	public Mundo(){
		g=new GramaticaFNC();
	}
	
	/**
	 * agrega una regla a la GNFC
	 * @param id variable que identifica la regla
	 * @param producciones cadena de producciones de la regla sepradas por '|'
	 * @throws VariableMalNombradaException
	 * @throws ProduccionNoCumpleFNCException Excepcion cuando una produccion de la regla no cumple FNC
	 * @throws ReglaYaExisteException excepcion producida cuando ya existe una regla con el mismo simbolo identificador de variable o nombre
	 */
	public void agregarRegla(char id, String producciones) throws VariableMalNombradaException, ProduccionNoCumpleFNCException, ReglaYaExisteException{
		g.agregarRegla(id, producciones);
	}
	
	
	/**
	 * Desarrolla el algortimo cyk con la gramatica actual sobre una cadena de terminales w y comprueba si la matriz resultante contiene el simbolo inicial en la posicion M[1][n]
	 * @param w cadena de simbolos terminales
	 * @return cadena con los parametros de entrada, la matriz del procedimiento y la respuesta
	 * @throws GramaticaVaciaException  excepcion lanzada cuando la gramatica se encuentra sin reglas establecidas
	 */
	public String darResultado(String w) throws GramaticaVaciaException{
		String retorno="";
		
		String[][] matriz=g.tablaCYK(w); // realizo el CYK y obtengo la matriz
		StringBuilder sb=new StringBuilder();
	
		//recorro la matriz para crear una cadena que la represente
		for(int f=0;f<matriz.length;f++){
			for(int c=0;c<matriz[f].length;c++){
				sb.append(matriz[f][c]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		String tabla=sb.toString(); // cadena que contiene la matriz
		sb.setLength(0); // limpio el stringBuilder
		String respuesta=(g.produce(matriz))?"La gramatica si genera la cadena w":"La gramatica no genera la cadena w";// verifica si genera y da la respuesta
		
		// concateno las partes para dar un solo string a la gui
		sb.append("Cadena w = ");
		sb.append(w);
		sb.append("\n");
		sb.append("Estado inicial = ");
		String ini=g.getInicial();
		sb.append(ini);
		sb.append("\n");
		sb.append(tabla);
		sb.append(respuesta);
		retorno=sb.toString();
		return retorno;
	}
	
	/**
	 * cambia el simbolo inicial de la gramatica actual
	 * @param inicial nuevo simbolo inicial
	 * @throws VariableMalNombradaException excepcion produccida cuando una variable no es un caracter literal mayuscula
	 */
	public void cambiarInicial(String inicial) throws VariableMalNombradaException{
		g.setInicial(inicial);
	}
	
	/**
	 * retorna el simbolo inciial de la gramatica actual
	 * @return simbolo inicial
	 */
	public String darEstadoInicialActual(){
		return g.getInicial();
	}
	
}
