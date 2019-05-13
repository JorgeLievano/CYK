package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import excepcionesGUI.CampoListaVacioException;

/**
 * Panel que representa una seccion de formulario con un cuerpo o lista de elementos
 * @author JORGE
 *
 */
public class PanelSeccion extends JPanel implements ActionListener{

	final static String AGREGAR_REGLA="Agregar Regla";
	
	private JButton agregar;
	private ArrayList<CampoRegla> reglas;
	private JPanel cuerpo;
	private JPanel panelBotones;
	
	/**
	 * Contructor del panel donde se incializan sus elementos
	 * @param nombre titulo o nombre que identifica la seccion 
	 */
	public PanelSeccion(String nombre){
		setBorder(new TitledBorder(nombre));
		setLayout(new BorderLayout());
		
		reglas=new ArrayList();
		
		cuerpo=new JPanel();
		cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
		panelBotones=new JPanel();
		
		
		agregar=new JButton(AGREGAR_REGLA);
		agregar.addActionListener(this);
		
		panelBotones.add(agregar);
		JScrollPane scroll=new JScrollPane(cuerpo);
		add(panelBotones,BorderLayout.SOUTH);
		add(scroll,BorderLayout.CENTER);
		agregarRegla();
	}
	
	/**
	 * agrega un nuevo campo para regla al cuerpo
	 */
	public void agregarRegla(){
		CampoRegla c=new CampoRegla(reglas.size(),this);
		cuerpo.add(c);
		reglas.add(c);
		updateUI();
	}

	/**
	 * retorna el valor contenido dentro del campo para id de la regla en la posicion indicada
	 * @param ind indice de la regla
	 * @return cadena obtenida del campo de texto de nombre de la regla en ind
	 * @throws CampoListaVacioException Excepcion cuando el campo es vacio
	 */
	public String darIdRegla(int ind) throws CampoListaVacioException{
		return reglas.get(ind).darId();
	}
	
	/**
	 *  retorna el valor contenido dentro del campo para las producciones de la regla en la posicion indicada
	 * @param ind indice de la regla
	 * @return cadena obtenida del campo de texto de producciones de la regla en ind
	 * @throws CampoListaVacioException Excepcion cuando el campo es vacio
	 */
	public String darProduccionesRegla(int ind) throws CampoListaVacioException{
		return reglas.get(ind).darProducciones();
	}
	
	/**
	 * retorna un arreglo de contenedores del tipo campos regla
	 * @return
	 */
	public CampoRegla[] darCamposReglas(){
		int n=reglas.size();
		CampoRegla[] c=new CampoRegla[n];
		for(int i=0;i<n;i++){
			c[i]=reglas.get(i);
		}
		return c;
	}
	
	
	/**
	 * elimina el campo en la posicion indicada y actualiza la interfaz
	 * @param n indice del campo a eliminar
	 */
	public void eliminar(int n){
		cuerpo.remove(reglas.get(n));
		reglas.remove(n);
		
		for(int i=n;i<reglas.size();i++){
			CampoRegla ref=reglas.get(i);
			ref.setNumero(ref.darNumero()-1);
		}
		updateUI();
	}
	
	/**
	 * retorna la cantidad elementos en el formulario(vistos como renglones)
	 * @return
	 */
	public int darCantidadLista(){
		return reglas.size();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a=e.getActionCommand();
		if(a.equals(AGREGAR_REGLA)){
			agregarRegla();
		}else{
			int n=Integer.parseInt(a);
			eliminar(n);
		}
		
		
	}
	
	
	
	
}
