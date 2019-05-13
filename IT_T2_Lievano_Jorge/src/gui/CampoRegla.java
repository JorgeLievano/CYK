package gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import excepcionesGUI.CampoListaVacioException;

/**
 * Formulario con los campos necesarios para establecer una regla de produccion
 * @author JORGE
 *
 */
public class CampoRegla extends JPanel implements ActionListener{

	private JTextField producciones;
	private JFormattedTextField id;
	private JButton eliminar;
	private int num;
	private PanelSeccion pa;
	
	/**
	 * contructor del campo regla inicializando sus componenentes
	 * @param num indice o posisicion del campo
	 * @param p PanelSeccion que contiene los campos y servira de escucha de eventos
	 */
	public CampoRegla(int num,PanelSeccion p) {
		this.num=num;
		producciones=new JTextField();
		
		producciones.setPreferredSize(new Dimension(150,20));
		try {
			MaskFormatter mascara = new MaskFormatter("U");
			 id = new JFormattedTextField(mascara);
			 id.setPreferredSize(new Dimension(15,20));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pa=p;
		  eliminar=new JButton("Eliminar");
		  eliminar.addActionListener(this);
		 
		add(id);
		add(new JLabel("->"));
		add(producciones);
		add(eliminar);
		
	}
	
	
	/**
	 * contructor con todos los campos parametrizados
	 * @param name
	 * @param value
	 * @param n
	 */
	public CampoRegla(String name,double value,int n){
		producciones=new JTextField(name);
		producciones.setEnabled(false);
		producciones.setDisabledTextColor(Color.black);
		
		id=new JFormattedTextField();
		id.setEnabled(false);
		id.setDisabledTextColor(Color.BLACK);
		num=n;
	}
	
	/**
	 * retorna el valor contenido dentro del campo para las producciones de la regla
	 * @return cadena obtenida del campo de texto de producciones
	 * @throws CampoListaVacioException Excepcion cuando el campo es vacio
	 */
	public String darProducciones() throws CampoListaVacioException{
		String txtProducciones=producciones.getText();
		if(txtProducciones.equals("")) throw new CampoListaVacioException("Reglas","Producciones",num);
		return txtProducciones;
	}
	
	/**
	 * retorna el valor contenido dentro del campo para el nombre de la regla o variable
	 * @return cadena obtenida del campo de texto de nombre de variable
	 * @throws CampoListaVacioException Excepcion cuando el campo es vacio
	 */
	public String darId() throws CampoListaVacioException{
		String txtId=id.getText();
		if(txtId.equals("")) throw new CampoListaVacioException("Reglas","Nombre de la regla",num);
		return txtId;
	}
	
	/**
	 * retorna el indice del campo
	 * @return num ,indice del campo
	 */
	public int darNumero(){
		return num;
	}
	
	/**
	 * modifica el indice del campo
	 * @param n nuevo indice
	 */
	public void setNumero(int n){
		num=n;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String a=arg0.getActionCommand();
		switch(a){
		case "Eliminar":
			pa.eliminar(num);
			break;
		}
		
	}
	
}
