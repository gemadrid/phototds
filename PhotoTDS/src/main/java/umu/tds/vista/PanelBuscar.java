package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class PanelBuscar extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Panel búsqueda publicaciones
	private JPanel panelCentral;
	private JPanel panelBusquedaPublicaciones;
	private DefaultListModel<String> hashtagListModel;
	private JList<String> listaHashtag;
	
	//TextField para introducir la búsqueda
	private JTextField textBuscar;

	/**
	 * Create the panel.
	 */
	public PanelBuscar(VentanaPrincipal v) {
		ventana = v;
		crearPanelBuscar();
	}
	
	private void crearPanelBuscar() {
		setBackground(Colores.FONDO);
		setLayout(new BorderLayout(0, 0));
		
		//Panel de búsqueda
		JPanel panelBuscarNorte = new JPanel();
		panelBuscarNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBuscarNorte.setBackground(Colores.FONDO);
		add(panelBuscarNorte, BorderLayout.NORTH);
		
		//Texto Buscar
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Poppins", Font.BOLD, 25));
		panelBuscarNorte.add(lblBuscar);
		
		//Área buscar
		textBuscar = new JTextField();
		textBuscar.setFont(new Font("Poppins", Font.PLAIN, 20));
		textBuscar.setBackground(Colores.AREA_TEXTO);
		textBuscar.setBorder(null);
		textBuscar.setColumns(25);
		panelBuscarNorte.add(textBuscar);
		
		addManejadorTextBuscar();
		
		crearPanelBusquedaPublicaciones();
	}
	
	private void crearPanelBusquedaPublicaciones() {
		panelCentral = new JPanel();
		panelCentral.setBackground(Colores.AREA_TEXTO);
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{200, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panel);
		
		//Lista con los hashtags
		listaHashtag = new JList<String>();
		hashtagListModel = new DefaultListModel<String>();
		listaHashtag.setModel(hashtagListModel);
		listaHashtag.setBackground(Colores.AREA_TEXTO);
		//JScrollPane
		JScrollPane scrollHashtag = new JScrollPane(listaHashtag);
		scrollHashtag.setBorder(null);
		scrollHashtag.setBackground(Colores.FONDO);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panelCentral.add(scrollHashtag, gbc_panel_1);
		
		//Panel búsqueda publicaciones
		//panelPublicaciones --> Para evitar que estire los paneles de las publicaciones
		JPanel panelPublicaciones = new JPanel(new BorderLayout());
		panelPublicaciones.setBackground(Colores.FONDO);
		panelBusquedaPublicaciones = new JPanel(new GridLayout(0, 1, 0, 10));
		panelBusquedaPublicaciones.setBackground(Colores.FONDO);
		panelPublicaciones.add(panelBusquedaPublicaciones, BorderLayout.NORTH);
		//JScrollPane
		JScrollPane scrollPublicaciones = new JScrollPane(panelPublicaciones);
		scrollPublicaciones.setBorder(null);
		scrollPublicaciones.setBackground(Colores.FONDO);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panelCentral.add(scrollPublicaciones, gbc_panel_2);
		
		listaHashtag.addListSelectionListener(crearListSelectionListener());
	}
	
	//Cargar hashtags a partir de la búsqueda
	private void buscarHashtag() {
		hashtagListModel.clear();
		List<String> hashtags = Controlador.INSTANCE.buscarHashtags(textBuscar.getText());
		hashtags.forEach(h -> hashtagListModel.addElement(h));
	}
	
	//ListSelectionListener
	private ListSelectionListener crearListSelectionListener() {
		return e -> {
			if (!e.getValueIsAdjusting()) {
				String hashtag = listaHashtag.getSelectedValue();
				actualizarPanelPublicaciones(hashtag);
			}
		};
	}
	
	//Actualizar panel de publicaciones
	private void actualizarPanelPublicaciones(String hashtag) {
		panelBusquedaPublicaciones.removeAll();
		List<Publicacion> publicaciones = Controlador.INSTANCE.buscarPublicaciones(hashtag);
		publicaciones.forEach(p -> panelBusquedaPublicaciones.add(new PanelPrincipalFoto(ventana, p)));
		panelBusquedaPublicaciones.revalidate();
		panelBusquedaPublicaciones.repaint();
		panelCentral.revalidate();
		panelCentral.repaint();
		this.revalidate();
		this.repaint();
	}
	
	//Manejador del campo de búsqueda
	private void addManejadorTextBuscar() {
		textBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaBusquedaUsuario busquedaUsuario = new VentanaBusquedaUsuario(ventana, textBuscar.getText());
				busquedaUsuario.setLocationRelativeTo(ventana);
				busquedaUsuario.setVisible(true);
				buscarHashtag();
			}
		});
	}

}
