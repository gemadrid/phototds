package umu.tds.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPerfilPublicacion extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Foto
	private Publicacion publicacion;
	
	//Label
	JLabel lblFoto;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the panel.
	 */
	public PanelPerfilPublicacion(VentanaPrincipal v, Publicacion p) {
		ventana = v;
		publicacion = p;
		crearPanelPerfilPublicacion();
	}
	
	private void crearPanelPerfilPublicacion() {
		setBackground(fondo);
		
		lblFoto = new JLabel("HOLA");
		lblFoto.setForeground(Color.WHITE);
		//Redimensionamos la foto para que quepa 
		add(lblFoto);

	}
	
	//Método para redimensionar imágenes
	/*private ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		URL url = this.getClass().getResource(path);
		ImageIcon icon;
		try {
			BufferedImage imagen = ImageIO.read(url);
			Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
			icon = new ImageIcon(imagenRedimensionada);
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	private ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		return null;
	}
	
	//Manejador botón megusta
	private void addManejadorFotoClickDerecho() {
		lblFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

}
