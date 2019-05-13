package gui;

import java.awt.*;
import javax.swing.*;

import excepcionesGUI.CampoListaVacioException;
import gramatica.*;
import mundo.Mundo;



/**
 * clase que dispone el hilo principal de la aplicacion con todos los elementos graficos y logicos. Ademas de la conexion entre estos
 * @author JORGE
 *
 */
public class VentanaPrincipal  extends JFrame {

	private PanelEscritura panelGramatica;//representa la gramatica sobre la que trabaja la aplicacion independientemente d elos cambios que se realicen en el panel de reglas
	private PanelEscritura panelSalida;
	private PanelSeccion panelReglas;// las modificaciones de las reglas en este panel solo tendran efecto al usar el comando de establecer gramatica
	private PanelAcciones panelAcciones;
	private JTextField cadenaW;
	private JTextField inicial;
	
	private Mundo mundo;
	/**
	 * Constructor con la incializacion de los componenetes d ela ventana inicial
	 */
	public VentanaPrincipal(){
		setSize(820,520);
		setResizable(false);
		setTitle("CYK::Lievano Jorge");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dimpref=new Dimension(400,200);
		panelGramatica=new PanelEscritura("Gramatica Actual",dimpref);
		
		panelSalida=new PanelEscritura("Salida",dimpref);
		
		panelReglas=new PanelSeccion("Reglas");
		panelReglas.setPreferredSize(dimpref);
		
		JPanel aux1=new JPanel();
		aux1.setLayout(new BoxLayout(aux1, BoxLayout.PAGE_AXIS));
		aux1.add(panelGramatica);
		aux1.add(panelReglas);
		
		
		JPanel aux2=new JPanel();
		JLabel lbW=new JLabel("Cadena de terminales w =");
		cadenaW=new JTextField();
		cadenaW.setPreferredSize(new Dimension(100,20));
		JLabel lbInicial=new JLabel("Produccion Inicial");
		inicial=new JTextField();
		inicial.setPreferredSize(new Dimension(15,20));
		aux2.add(lbW);
		aux2.add(cadenaW);
		aux2.add(lbInicial);
		aux2.add(inicial);
		
		panelAcciones=new PanelAcciones(this);
		
		mundo=new Mundo();
		
		
		add(aux1,BorderLayout.WEST);
		add(panelSalida,BorderLayout.EAST);
		add(aux2,BorderLayout.NORTH);
		add(panelAcciones,BorderLayout.SOUTH);
	}
	
	/**
	 * Toma la informacion agregada en el panel de lista de campos para las reglas para agregar las respectivas instancias de reglas de produccion a la graamtica y mostrarla de forma textual en el panel de gramatica actual
	 */
	public void establecerGramatica(){
		int n=panelReglas.darCantidadLista();
		if(n==0){
			JOptionPane.showMessageDialog(this, "Por favor agregue las reglas de produccion y luego establezca la gramatica", "No se han agregado reglas", JOptionPane.INFORMATION_MESSAGE);
		}else{
			StringBuilder sb=new StringBuilder();
			mundo=new Mundo();
			for(int i=0;i<n;i++){
				try {
					String id=panelReglas.darIdRegla(i);
					String producciones=panelReglas.darProduccionesRegla(i);
					sb.append(id);
					sb.append(" --> ");
					sb.append(producciones);
					sb.append("\n");
					mundo.agregarRegla(id.charAt(0), producciones);
					panelGramatica.pintarEntrada(sb.toString());
				} catch (CampoListaVacioException | VariableMalNombradaException | ProduccionNoCumpleFNCException | ReglaYaExisteException e){
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e.getMessage(), "Corregir", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Toma los parametros de la cadena w y simbolo incial de los campos correpondieste y procede a solicitartar la cadena de resultado del algotirmo cyk al mundo, luego imprime el resultado en el panel de salida
	 * El resultado consta de los parametros, la matriz del proceso y la respuesta si w es producida o no por la gramatica
	 * <b>pre:</b> la gramatica no es vacia
	 * <b>pre:</b>
	 */
	public void CYK(){
		String w=cadenaW.getText();
		String ini=inicial.getText();
			
		if(w.equals("")){
			JOptionPane.showMessageDialog(this, "Si desea verificar la cadena vacia: \n Debe representarla con un simbolo de su eleccion en el campo de la cadena y en la produccion inicial","Recomendacion",JOptionPane.INFORMATION_MESSAGE);
		}else{
			try {
				mundo.cambiarInicial(ini);
				String resultado=mundo.darResultado(w);
				StringBuilder sb=new StringBuilder();
				sb.append(resultado);
			
				panelSalida.pintarEntrada(sb.toString());
			} catch (GramaticaVaciaException | VariableMalNombradaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e.getMessage(), "Faltan Argumentos", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaPrincipal v=new VentanaPrincipal();
		v.setVisible(true);
		
	}

}
