package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Usuario;

import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public class VentanaBusquedaUsuario extends JDialog {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Texto de búsqueda
	private String busqueda;

	/**
	 * Create the dialog.
	 */
	public VentanaBusquedaUsuario(VentanaPrincipal ventana, String busqueda) {
		super(ventana, "Búsqueda de usuarios", false);
		this.ventana = ventana;
		this.busqueda = busqueda;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(300, 400);
		
		getContentPane().setLayout(new BorderLayout());
		
		crearPanelUsuarios();
	}
	
	//Panel con la lista de usuarios encontrados
	private void crearPanelUsuarios() {
		//JList
		JList<Usuario> listaUsuarios = new JList<Usuario>();
		DefaultListModel<Usuario> usuariosListModel = new DefaultListModel<Usuario>();
		listaUsuarios.setModel(usuariosListModel);
		listaUsuarios.setCellRenderer(crearListCellRenderer());
		listaUsuarios.addListSelectionListener(crearListSelectionListener(listaUsuarios));
		//Añadimos los usuarios
		Controlador.INSTANCE.buscarUsuarios(busqueda).forEach(u -> usuariosListModel.addElement(u));

		//JScrollPane
		JScrollPane scrollPane = new JScrollPane(listaUsuarios);
		scrollPane.setBackground(Colores.AREA_TEXTO);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
	
	//ListCellRenderer
	private ListCellRenderer<? super Usuario> crearListCellRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Usuario usuario = (Usuario) value;
					label.setIcon(Utilidades.getImagenRedimensionada(usuario.getFotoUsuario(), 40, 40));
					label.setText(usuario.getNombreCompleto());
					label.setFont(new Font("Poppins", Font.PLAIN, 13));
					//if (!isSelected) label.setBackground(fondo);
				}
				return c;
			}
		};
	}
	
	//ListSelectionListener
	private ListSelectionListener crearListSelectionListener(JList<Usuario> listaUsuarios) {
		return e -> {
			if (!e.getValueIsAdjusting()) {
				Usuario usuario = listaUsuarios.getSelectedValue();
				VentanaPerfil ventanaPerfil = new VentanaPerfil(ventana, usuario);
				ventanaPerfil.setLocationRelativeTo(ventana);
				ventanaPerfil.setVisible(true);
			}
		};
	}
	

}
