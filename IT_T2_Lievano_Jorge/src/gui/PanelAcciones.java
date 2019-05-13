package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Panel con los comandos para reazlizar las acciones principales de la aplicacion
 * @author JORGE
 *
 */
public class PanelAcciones extends JPanel implements ActionListener{

	final static String DESARROLLAR_CYK= "Desarrollar CYK";
	final static String ESTABLECER_GRAMATICA="Establecer Gramatica";
	
	private JButton btnCYK,btnEstablecerGramatica;
	private VentanaPrincipal gui;
	
	/**
	 * constructor del panel de acciones donde se incializan los elementos
	 * @param gui referencia de conexion con el main
	 */
	public PanelAcciones(VentanaPrincipal gui){
		
		this.gui=gui;
		
		btnCYK = new JButton(DESARROLLAR_CYK);
		btnCYK.addActionListener(this);
		
		btnEstablecerGramatica=new JButton(ESTABLECER_GRAMATICA);
		btnEstablecerGramatica.addActionListener(this);
		
		
		add(btnEstablecerGramatica);
		add(btnCYK);
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String accion=e.getActionCommand();
		
		switch (accion){
		case DESARROLLAR_CYK:
			gui.CYK();
			break;
		case ESTABLECER_GRAMATICA:
			gui.establecerGramatica();
			break;
		}
	}

}
