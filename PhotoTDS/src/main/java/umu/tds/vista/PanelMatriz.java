package umu.tds.vista;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Foto;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMatriz extends JPanel {
	
	//Ventana principal
	private VentanaPerfil ventana;
	
	//Usuario
	private Usuario usuario;
	
	//Lista
	JList<Publicacion> listaFotos;
	
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
	public PanelMatriz(VentanaPerfil v, Usuario u) {
		ventana = v;
		usuario = u;
		
		setBackground(fondo);
		setLayout(new BorderLayout());
		
		crearPanelMatriz();
	}
	
	private void crearPanelMatriz() {
		//JList con la matriz de publicaciones
		listaFotos = new JList<Publicacion>();
		DefaultListModel<Publicacion> fotosListModel = new DefaultListModel<Publicacion>();
		listaFotos.setModel(fotosListModel);
		usuario.getFotos().forEach(f -> fotosListModel.addElement(f));
		listaFotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaFotos.setVisibleRowCount(-1);
		listaFotos.ensureIndexIsVisible(getHeight());
		listaFotos.setCellRenderer(crearListCellRenderer());
		listaFotos.setBackground(fondo);
		
		//JScrollPane
		JScrollPane panelMatriz = new JScrollPane(listaFotos);
		panelMatriz.setBorder(null);
		panelMatriz.setBackground(fondo);
		add(panelMatriz, BorderLayout.CENTER);
		
		//Menú contextual
		popupMenu = new JPopupMenu();
		
		//Opción eliminar
		mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.setFont(new Font("Poppins", Font.PLAIN, 13));
		
		popupMenu.add(mntmEliminar);
		
		addManejadorFotoClick();
		addManejadorBotonEliminar();
	}
	
	//ListCellRenderer
	private ListCellRenderer<? super Publicacion> crearListCellRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Publicacion publicacion = (Publicacion) value;
					label.setIcon(getImagenRedimensionada(publicacion));
					label.setText("");
					if (!isSelected)
						label.setBackground(fondo);
				}
				return c;
			}
		};
	}
		
	
	//Manejador click izquierdo y derecho en la foto
	private void addManejadorFotoClick() {
		listaFotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int celda = listaFotos.locationToIndex(e.getPoint());
				listaFotos.setSelectedIndex(celda);
				//Si se ha pulsado el botón izquierdo abrimos la foto
				if (SwingUtilities.isLeftMouseButton(e)) {
					Publicacion publicacion = listaFotos.getSelectedValue();
					VentanaVerFotoComentario verFotoComentario = new VentanaVerFotoComentario(ventana, publicacion);
					verFotoComentario.setLocationRelativeTo(ventana);
					verFotoComentario.setVisible(true);
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
				Publicacion publicacion = listaFotos.getSelectedValue();
				//TODO Controlador.INSTANCE.eliminarPublicacion(publicacion);
				//TODO Notificar a VentanaPerfil de que se ha eliminado la publicación para recargarla
			}
		});
	}
	
	
	private ImageIcon getImagenRedimensionada(Publicacion publicacion) {
		try {
			//Leemos la imagen
			BufferedImage img = ImageIO.read(new File(publicacion.getPath()));
			//TODO Ver qué tamaño es mejor
			//Obtenemos el tamaño de la imagen
			int ancho = getWidth() / 3 - 10;
			//int alto = ancho * img.getHeight() / img.getWidth();
			int alto = ancho * 10 / 16;
			//Reescalamos la imagen
			Image imgNueva = img.getScaledInstance(ancho, alto, Image.SCALE_FAST);
			//Devolvemos la imagen
			return new ImageIcon(imgNueva);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
