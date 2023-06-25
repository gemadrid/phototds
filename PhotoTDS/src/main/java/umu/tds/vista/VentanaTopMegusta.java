package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;
import umu.tds.vista.utilidades.Colores;
import umu.tds.vista.utilidades.FontManager;
import umu.tds.vista.utilidades.Utilidades;

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
					label.setIcon(Utilidades.getImagenRedimensionada(publicacion.getPath(), 240, 135));
					label.setText(publicacion.getMegusta() + " Me gusta");
					label.setFont(FontManager.getFuente(13f));
				}
				return c;
			}
		};
	}

}
