package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class VentanaTopMegusta extends JDialog {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Texto de búsqueda
	private String busqueda;

	/**
	 * Create the dialog.
	 */
	public VentanaTopMegusta(VentanaPrincipal ventana) {
		super(ventana, "Top 10 Publicaciones", true);
		this.ventana = ventana;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Colores.FONDO);
		
		crearPanelPublicaciones();
		
		setSize(400, 400);
	}
	
	//Panel con la lista de usuarios encontrados
	private void crearPanelPublicaciones() {
		//JList
		JList<Publicacion> listaPublicaciones = new JList<Publicacion>();
		DefaultListModel<Publicacion> publicacionesListModel = new DefaultListModel<Publicacion>();
		listaPublicaciones.setModel(publicacionesListModel);
		listaPublicaciones.setCellRenderer(crearListCellRenderer());
		//Añadimos las publicaciones
		Controlador.INSTANCE.getTopMeGusta().forEach(p -> publicacionesListModel.addElement(p));

		//JScrollPane
		JScrollPane scrollPane = new JScrollPane(listaPublicaciones);
		scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
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
					label.setIcon(getImagenRedimensionada(publicacion.getPath(), 240, 135));
					label.setText(publicacion.getMegusta() + " Me gusta");
					label.setFont(new Font("Poppins", Font.PLAIN, 13));
				}
				return c;
			}
		};
	}
	
	//Imagen redimensionada
	private ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		try {
			//Leemos la imagen
			BufferedImage img = ImageIO.read(new File(path));
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
