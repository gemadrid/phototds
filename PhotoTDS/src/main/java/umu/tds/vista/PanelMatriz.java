package umu.tds.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Album;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;

import java.awt.BorderLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMatriz extends JPanel {
	
	//Tipo de publicación
	private int tipo;
	public static final int FOTO = 0;
	public static final int ALBUM = 1;
	
	//Ventana principal
	private VentanaPrincipal ventanaPrincipal;
	private VentanaPerfil ventana;
	
	//Usuario
	private Usuario usuario;
	
	//Lista
	JList<Publicacion> listaFotos;
	DefaultListModel<Publicacion> fotosListModel;
	
	//Album actual
	Publicacion albumActual;
	
	//Paneles
	JPanel panelBotones;
	
	//Botones
	JButton btnAddFoto;
	JButton btnRetroceder;
	
	//Menú contextual
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;

	/**
	 * Create the panel.
	 */
	public PanelMatriz(VentanaPerfil v, VentanaPrincipal vp, Usuario u, int tipo) {
		this.ventana = v;
		this.ventanaPrincipal = vp;
		this.usuario = u;
		this.tipo = tipo;
		
		setBackground(Colores.FONDO);
		setLayout(new BorderLayout());
		
		crearPanelNorte();
		crearPanelMatriz();
	}
	
	//Panel con botones atrás / añadir foto (álbum)
	private void crearPanelNorte() {
		panelBotones = new JPanel();
		panelBotones.setBackground(Colores.FONDO);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(panelBotones, BorderLayout.NORTH);
		
		//Botones
		btnRetroceder = new JButton("<");
		btnRetroceder.setForeground(Color.WHITE);
		btnRetroceder.setBorderPainted(false);
		btnRetroceder.setBackground(Colores.RESALTADO);
		btnRetroceder.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnRetroceder);
		
		addManejadorBotonRetroceder();
		
		//Si es el usuario actual añadimos la opción de añadir fotos al álbum
		if (Controlador.INSTANCE.esUsuarioActual(usuario)) { 
			btnAddFoto = new JButton("+");
			btnAddFoto.setForeground(Color.WHITE);
			btnAddFoto.setBorderPainted(false);
			btnAddFoto.setBackground(Colores.RESALTADO);
			btnAddFoto.setFont(new Font("Poppins", Font.BOLD, 15));
			panelBotones.add(btnAddFoto);
		
			addManejadorBotonAddFoto();
		}
		
		panelBotones.setVisible(false);
	}
	
	//Panel con la matriz de fotos / álbumes
	private void crearPanelMatriz() {
		//JList con la matriz de publicaciones
		listaFotos = new JList<Publicacion>();
		fotosListModel = new DefaultListModel<Publicacion>();
		listaFotos.setModel(fotosListModel);
		
		//Obtenemos la lista de publicaciones según tipo y las añadimos
		if (tipo == FOTO)
			usuario.getFotos().forEach(f -> fotosListModel.addElement(f));
		else usuario.getAlbumes().forEach(f -> fotosListModel.addElement(f));
		
		listaFotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaFotos.setVisibleRowCount(-1);
		listaFotos.ensureIndexIsVisible(getHeight());
		listaFotos.setCellRenderer(crearListCellRenderer());
		listaFotos.setBackground(Colores.FONDO);
		
		//JScrollPane
		JScrollPane panelMatriz = new JScrollPane(listaFotos);
		panelMatriz.setBorder(null);
		panelMatriz.setBackground(Colores.FONDO);
		add(panelMatriz, BorderLayout.CENTER);
		
		//Si es el usuario actual añadimos la opción de eliminar publicaciones
		if (Controlador.INSTANCE.esUsuarioActual(usuario)) { 
			//Menú contextual
			popupMenu = new JPopupMenu();
			
			//Opción eliminar
			mntmEliminar = new JMenuItem("Eliminar");
			mntmEliminar.setFont(new Font("Poppins", Font.PLAIN, 13));
		
			popupMenu.add(mntmEliminar);
		
			addManejadorBotonEliminar();
		}
		
		addManejadorFotoClick();
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
					int ancho = PanelMatriz.this.getWidth() / 3 - 10;
					int alto = ancho * 10 / 16;
					label.setIcon(Utilidades.getImagenRedimensionada(publicacion.getPath(), ancho, alto));
					label.setText("");
					if (!isSelected)
						label.setBackground(Colores.FONDO);
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
				//Si se ha pulsado el botón izquierdo abrimos la foto / álbum
				if (SwingUtilities.isLeftMouseButton(e)) {
					Publicacion publicacion = listaFotos.getSelectedValue();
					if (tipo == FOTO) {
						//Mostramos la foto
						mostrarFoto(publicacion);
					}
					else {
						//Mostramos el álbum
						mostrarAlbum(publicacion);
					}
				}
				//Si se ha pulsado el botón derecho
				else if (SwingUtilities.isRightMouseButton(e)) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
	
	//Mostrar foto
	private void mostrarFoto(Publicacion publicacion) {
		VentanaVerFotoComentario verFotoComentario = new VentanaVerFotoComentario(ventana, publicacion);
		verFotoComentario.setLocationRelativeTo(ventana);
		verFotoComentario.setVisible(true);
	}
	
	//Mostrar album
	private void mostrarAlbum(Publicacion publicacion) {
		//Cambiamos las miniaturas de los álbumes por las miniaturas de las fotos del álbum
		fotosListModel.clear();
		//Obtenemos las fotos del álbum
		((Album)publicacion).getFotos().forEach(f -> fotosListModel.addElement(f));
		//Indicamos que los objetos se abrirán como fotos
		tipo = FOTO;
		//Especificamos el album actual
		albumActual = publicacion;
		//Mostramos los botones asociados al álbum
		panelBotones.setVisible(true);
	}
	
	//Manejador botón eliminar
	private void addManejadorBotonEliminar() {
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicacion publicacion = listaFotos.getSelectedValue();
				Controlador.INSTANCE.eliminarPublicacion(publicacion);
			}
		});
	}
	
	//Manejador botón retroceder
	private void addManejadorBotonRetroceder() {
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Volvemos a mostrar los álbumes
				fotosListModel.clear();
				usuario.getAlbumes().forEach(f -> fotosListModel.addElement(f));
				tipo = ALBUM;
				panelBotones.setVisible(false);
			}
		});
	}
	
	//Manejador botón addFoto
	private void addManejadorBotonAddFoto() {
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFotoAlbum();
			}
		});
	}
	
	private void addFotoAlbum() {
		//Indicamos que vamos a añadir una foto al álbum
		Controlador.INSTANCE.modoAlbum();
		//Ventana de diálogo para subir la foto
		JDialog subirFoto = new JDialog(ventana, "Subir foto", true);
		subirFoto.add(new PanelSubir(ventanaPrincipal));
		subirFoto.setSize(900, 500);
		subirFoto.setResizable(false);
		subirFoto.setLocationRelativeTo(ventana);
		subirFoto.setVisible(true);
		subirFoto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//Añadimos la foto al álbum
		Controlador.INSTANCE.addPublicacionAlbum(albumActual);	
	}
	

}
