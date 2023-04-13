package umu.tds.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class PanelPrincipal extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the panel.
	 */
	public PanelPrincipal(VentanaPrincipal v) {
		ventana = v;
		crearPanelPrincipal();
	}
	
	private void crearPanelPrincipal() {
		setBackground(fondo);
		
		JLabel lblPrincipal = new JLabel("Panel Principal");
		lblPrincipal.setForeground(Color.WHITE);
		lblPrincipal.setFont(new Font("Poppins Black", Font.PLAIN, 20));
		//add(lblPrincipal);
	}

}
