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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPerfilPublicacion extends JPanel {
	
	//Ventana principal
	private VentanaPerfil ventana;
	
	//Foto
	private Publicacion publicacion;
	
	//Label
	JLabel lblFoto;
	
	//Menú contextual
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the panel.
	 */
	public PanelPerfilPublicacion(VentanaPerfil v, Publicacion p) {
		ventana = v;
		publicacion = p;
		crearPanelPerfilPublicacion();
		setMaximumSize(getPreferredSize());
	}
	
	private void crearPanelPerfilPublicacion() {
		setBackground(fondo);
		
		lblFoto = new JLabel();
		lblFoto.setForeground(Color.WHITE);
		//Redimensionamos la foto para que quepa
		ImageIcon imagen = getImagenRedimensionada();
		lblFoto.setIcon(imagen);
		add(lblFoto);
		
		//Menú contextual
		popupMenu = new JPopupMenu();
		add(popupMenu);
		
		//Opción eliminar
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		popupMenu.add(mntmEliminar);
		
		addManejadorFotoClick();
		addManejadorBotonEliminar();
	}
	
	private ImageIcon getImagenRedimensionada() {
		try {
			//Leemos la imagen
			BufferedImage img = ImageIO.read(new File(publicacion.getPath()));
			//Obtenemos el tamaño de la imagen
			int ancho = ventana.getWidth() / 3;
			int alto = ancho * img.getHeight() / img.getWidth();
			//Reescalamos la imagen
			Image imgNueva = img.getScaledInstance(ancho, alto, Image.SCALE_FAST);
			//Devolvemos la imagen
			return new ImageIcon(imgNueva);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Manejador click izquierdo y derecho en la foto
	private void addManejadorFotoClick() {
		lblFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Si se ha pulsado el botón izquierdo
				if (SwingUtilities.isLeftMouseButton(e)) {
					
				}
				//Si se ha pulsado el botón derecho
				else if (SwingUtilities.isRightMouseButton(e)) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
	
	//Manejador botón eliminar
	private void addManejadorBotonEliminar() {
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.eliminarPublicacion(publicacion);
				//TODO Notificar a VentanaPerfil de que se ha eliminado la publicación para recargarla
				//dispose();
			}
		});
	}

}
