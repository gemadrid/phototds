package umu.tds.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.modelo.Foto;
import java.awt.BorderLayout;

public class PanelPrincipalFoto extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Foto
	private Foto foto;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the panel.
	 */
	public PanelPrincipalFoto(VentanaPrincipal v, Foto f) {
		ventana = v;
		foto = f;
		crearPanelFoto();
	}
	
	private void crearPanelFoto() {
		setBackground(fondo);
		setLayout(new BorderLayout(0, 0));
	}

}
