package gui;

import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 * Panel cuyo objetivo es presesntar informacion en forma de texto
 * @author JORGE
 *
 */
public class PanelEscritura extends JPanel{

	private JTextArea entrada;
	
	/**
	 * constructor del panel de escritura
	 * @param t titulo otorgado al panel para su identificacion visible
	 * @param dimpref dimensiones preferidas otorgadas al panel
	 */
	public PanelEscritura(String t,Dimension dimpref){
		
		setBorder(new TitledBorder(t));
		setLayout(null);
		setPreferredSize(dimpref);
		
		entrada=new JTextArea();
		entrada.setEnabled(false);
		entrada.setDisabledTextColor(Color.black);
		
		
		JScrollPane scroll=new JScrollPane(entrada);
		scroll.setBounds(20,20,dimpref.width-30,dimpref.height-30);
		add(scroll);
		
	}
	
	
	/**
	 * Elimina todo el contenido en la zona de texto
	 */
	public void borrar(){
		entrada.setText(null);
	}
	
	/**
	 * sustituye la informacion actual por una nueva
	 * @param e cadena de informacion nueva
	 */
	public void pintarEntrada(String e){
		borrar();
		entrada.append(e);
	}
	
}
