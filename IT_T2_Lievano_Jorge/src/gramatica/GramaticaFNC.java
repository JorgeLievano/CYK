package gramatica;

import java.util.ArrayList;
/**
 * clase que define una Gramatica en FNC con sus conjunto de reglas de produccion y un simbolo  inicial
 * 
 * @author JORGE
 *
 */
public class GramaticaFNC {

	private ArrayList<Regla> reglas;
	private String inicial;
	
	/**
	 * constructor de la regla con un simbolo inicial definido e inicializacion del conjunto de reglas vacio
	 * @param inicial simbolo inicial de la gramatica
	 */
	public GramaticaFNC(String inicial){
		this.inicial=inicial;
		reglas=new ArrayList<Regla>();
	}
	
	/**
	 * constructor de la gramatica sin simbolo inicial definido e inicializacion conjunto de reglas vacio
	 */
	public GramaticaFNC(){
		reglas=new ArrayList<Regla>();
	}
	
	/**
	 * Agrega un regla que cumpla con sus producciones en FNC y con simbolo de id no repetido dentro del conjunto de reglas
	 * @param id simbolo de la variable o nombre de la regla. caracter literal mayuscula
	 * @param producciones cadena del conjunto de producciones en FNC separadas por '|'
	 * @throws VariableMalNombradaException excepcion produccida cuando una variable no es un caracter literal mayuscula
	 * @throws ProduccionNoCumpleFNCException excepcion producida cuando una produccion no se encuentra en FNC
	 * @throws ReglaYaExisteException excepcion producida cuando ya existe una regla con el mismo simbolo identificador de variable o nombre
	 */
	public void agregarRegla(char id,String producciones) throws VariableMalNombradaException, ProduccionNoCumpleFNCException, ReglaYaExisteException{
		if(reglas.stream().anyMatch(r->r.getId()==id)) throw new ReglaYaExisteException(""+id);
		comprobarProduccionesFNC(id,producciones);
		Regla r=new Regla(id,producciones);
		reglas.add(r);
	}
	
	/**
	 * retorna una matriz de cadenas como resultado de realizar el algoritmo CYK
	 * @param w cadena de terminales a comporbar
	 * @return matriz de cadenas cuya primer fila y columna(indice 0) contienen los encabezados de iteraccion. Apartir del indice 1 contiene los conjuntos generadores de cada paso
	 * @throws GramaticaVaciaException excepcion lanzada cuando la gramatica se encuentra sin reglas establecidas
	 */
	public String[][] tablaCYK(String w) throws GramaticaVaciaException{
		if(reglas.isEmpty())throw new GramaticaVaciaException();//comprueba que existan reglas
	
		int n=w.length();
		String[][] ret=crearMatrizCYK(n);// se crea la matriz con las dimensiones apropiadas teniendo encuenta los encabezados
		inicializarCYK(w,ret); // primer paso del algoritmo, los que generan cada terminal J=1
		iteraccionCYK(ret,n);// paso iterativo hasta J=n
		return ret;
	}
	
	/**
	 * comprueba si una matriz m de resultado contiene en su posicion m[1][n] el simbolo de variable inicial 
	 * @param matriz matriz de cadenas resultante del algoritmo CYK sobre una cadena 
	 * @return true si la varieble inicial se encuentra en la posicion m[1][n] : false de lo contrario;
	 * @throws GramaticaVaciaException excepcion lanzada cuando la gramatica se encuentra sin reglas establecidas
	 */
	public boolean produce(String matriz[][]) throws GramaticaVaciaException{
		if(reglas.isEmpty())throw new GramaticaVaciaException();
		inicial=(inicial.equals(""))?""+reglas.get(0).getId():inicial;// si no se ha definido un inicial se asume como la primer regla de la lista
		return(matriz[1][matriz[1].length-1].contains(inicial))?true:false;
	}
	
	/**
	 * primer paso del algortimo CYK para inicializar la matriz con los conjunos de simbolos que producen cada terminal de la cadena w
	 * @param w cadena de terminales
	 * @param matriz matriz m donde cada variable A en m[i][1] es tal A->a, a es un terminal en la posicion i de w 
	 */
	private void inicializarCYK(String w, String[][] matriz){
		char[] wTerminales=w.toCharArray();// se parte la cadena en caracteres
		// se halla el conjunto que genera cada caracter y se agrega a la matriz en J=1 para cada caracter en posicion i
		for(int i=0;i<w.length();i++){
			matriz[0][i+1]="j= "+(i+1);
			matriz[i+1][0]=wTerminales[i]+" i="+(i+1);
			matriz[i+1][1]=conjuntoGenera(""+wTerminales[i]);
		}
	}
	
	/**
	 * paso iterativo de CYK donde se establecen los conjuntos generadores para las variables A->BC que pertencen a X[i][j], donde B pertenece a X[i][k] y C pertences a X[i+k][j-k]
	 * para cada j. 1<j<n (j=1 ya se realizao en el paso1) con 1<=i<=n-j  en cada i considerando cada k,  1<=k<=j-i
	 * @param matriz 
	 * @param n numero de filas de la matriz, longitud de w
	 */
	private void iteraccionCYK(String[][]matriz,int n){
		for(int j=2;j<=n;j++){
			
			for(int i=1;i<=n-j+1;i++){
				String r="";// cadena que representa el conjunto de variables que genera las expresiones binarias resultantes de las siguientes interacciones en k
				for(int k=1;k<j;k++){
					
					String[] alfas=matriz[i][k].split(",");// conjunto de variables en m[i][k]
					String[] betas=matriz[i+k][j-k].split(",");//conjunto de variables en m[i+k][j-k]
					// se hacen las combinaciones para obetener expresiones binarias de variables 
					for(int indA=0;indA<alfas.length;indA++){
						for(int indB=0;indB<betas.length;indB++){
							String alfa=alfas[indA];
							String beta=betas[indB];
							String parte=(alfa.equals("")|beta.equals(""))?"":conjuntoGenera(alfa+beta);// se busca las vafriables que generan alguna de las combinaciones binarias, pueden haber repetidos
							String[] partes=parte.split(",");
							for(int pind=0;pind<partes.length;pind++){
								if(!r.contains(partes[pind]))r=r+partes[pind]+",";// se agregan a la cadena que representa el conjunto de generadores sin repetidos
							}
						
						}
					}
				}
				matriz[i][j]=(r.length()>0)?r.substring(0,r.length()-1):r; // se agrega el conjunto de generadores en la matriz en m[i][j]
			}
		}
	}
	
	/**
	 * Devuelve el conjunto de variables que producen la expresion 
	 * @param expresion cadena conformada por dos variables o un terminal
	 * @return cadena que representa el conjunto de variables que producen la expresion separadas por ','
	 */
	public String conjuntoGenera(String expresion){
		String conjunto="";
		for(int i=0;i<reglas.size();i++){// para cada regla se comprueba si contiene la expresion 
			Regla r=reglas.get(i);
			conjunto=(r.produce(expresion))?conjunto+r.getId()+",":conjunto;// a la cadena de salida se agrega el id de la regla o variable si genera la expresion
		}
		return (conjunto.length()>0)?conjunto.substring(0, conjunto.length()-1):conjunto;// si el conjunto no es vacio se borra la coma al final y retorna , si es vacio se retorna 
	}
	
	/**
	 * crea matriz de cadenas y le asigna las dimensiones apropiadas a cada fila para optimizar recursos teniendo en cuenta los encabezados de la interaccion
	 * @param n tamaño de la cadena w
	 * @return matriz cuya primera fila y clumna poseen los emcabezados de la interaccion y las siguientes poseen dimensiones i reduciendose de n hasta 1, n>i>1
	 */
	private String[][] crearMatrizCYK(int n){
		int tam=n+1; // crea la matriz de forma que se agregan los encabezados y apartir de la segunda fila con dimension n , la dimension disminuye, solo por forma y posible optimizacion de recursos
		String[][] ret=new String[tam][];
		ret[0]=new String[tam];
		for(int i=0;i<n;i++){
			ret[i+1]=new String[tam-i];
		}
		ret[0][0]="";
		return ret;
	}
	
	/**
	 * sustituye el valor de la variable inicial
	 * @param inicial simbolo de la varible inicial(puede ser null)
	 * @throws VariableMalNombradaException Excepcion lanzada cuando el param inicial es una cadena no vacia diferente de un literal mayuscula
	 */
	public void setInicial(String inicial) throws VariableMalNombradaException{
		if(inicial.length()>1||inicial.length()==1&&!Character.isUpperCase(inicial.charAt(0))) {
			throw new VariableMalNombradaException(" o variable inicial");
		}else {
			this.inicial=inicial;
		}
	}
	
	/**
	 * retorna el valor del simbolo inicial actual
	 * @return simbolo incial actual
	 */
	public String getInicial(){
		return inicial;
	}
	
	
	/**
	 * Comprueba que cada produccion de una regla con FNC, es decir sean de la forma, A->BC o A->a,donde A,B,C son variables y a es un terminal;
	 * @param id variable que identifica la regla
	 * @param producciones cadena de producciones de la regla sepradas por '|'
	 * @throws ProduccionNoCumpleFNCException Excepcion cuando una produccion de la regla no cumple FNC
	 */
	private void comprobarProduccionesFNC(char id,String producciones) throws ProduccionNoCumpleFNCException{
		String c=id+" -> "+producciones;
		String[] p=producciones.split("\\|");
		// se comprueban los rasgos general de la FNC y se lanzan excepciones pertinentes con posibles recomendaciones
		for(int i=0;i<p.length;i++){
			String pro=p[i];
			if(pro.length()>2){
				throw new ProduccionNoCumpleFNCException(c,i,"No es una Expresion binaria de variables o un terminal");
			}else if(pro.length()==2){
				if( !  Character.isUpperCase(pro.charAt(0)) || !Character.isUpperCase(pro.charAt(1))  ) throw new ProduccionNoCumpleFNCException(c,i,"La expresion binaria no puede contener terminales");
			}else{
				if(Character.isUpperCase(pro.charAt(0)))throw new ProduccionNoCumpleFNCException(c,i,"La expresion No binaria debe ser terminal");
			}
		}
	}
}
