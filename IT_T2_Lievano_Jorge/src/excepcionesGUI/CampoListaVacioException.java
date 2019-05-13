package excepcionesGUI;

public class CampoListaVacioException extends Exception{

	
	public CampoListaVacioException(String lista,String campo,int n){
		super("La lista "+lista+" posee el campo "+campo+" vacio en la posicion "+n
				+"\n"+"Por favor rellene los campos vacios");
	}
	
}
