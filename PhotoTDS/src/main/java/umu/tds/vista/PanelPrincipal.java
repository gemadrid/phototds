package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import javax.swing.BoxLayout;

public class PanelPrincipal extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;

	/**
	 * Create the panel.
	 */
	public PanelPrincipal(VentanaPrincipal v) {
		ventana = v;
		setBackground(Colores.FONDO);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		crearPanelPrincipal();
	}
	
	private void crearPanelPrincipal() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Colores.FONDO);
		
		//Obtenemos las 20 notificaciones más recientes
		List<Publicacion> publicaciones = Controlador.INSTANCE.getPublicacionesNotificaciones(20);
		
		//Panel que contiene las publicaciones
		JPanel panelPublicaciones = new JPanel(new GridLayout(0, 1, 0, 10));
		panelPublicaciones.setBackground(Colores.FONDO);
		//Añadimos las publicaciones
		publicaciones.forEach(p -> panelPublicaciones.add(new PanelPrincipalFoto(ventana, p)));
		
		panelPrincipal.add(panelPublicaciones, BorderLayout.NORTH);
		
		//ScrollPane (que contiene el panelPrincipal)
		JScrollPane scrollPane = new JScrollPane(panelPrincipal);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Colores.FONDO);
		add(scrollPane);
	}
	
	public void actualizar() {
		this.removeAll();
		crearPanelPrincipal();
		this.revalidate();
		this.repaint();
	}

}
