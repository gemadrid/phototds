package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Usuario;
import umu.tds.vista.utilidades.Colores;
import umu.tds.vista.utilidades.FontManager;
import umu.tds.vista.utilidades.Utilidades;

import javax.swing.border.EmptyBorder;

public class VentanaPerfil extends JDialog {
	
	private VentanaPrincipal ventana;
	
	private Usuario usuario;
	
	//Paneles
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JPanel panelMatriz;
	
	//Campos
	
	//Botones
	private JButton btnEditar;
	private JButton btnSeguir;
	private JButton btnFotos;
	private JButton btnAlbumes;
	private JButton btnNuevoAlbum;

	/**
	 * Create the dialog.
	 */
	public VentanaPerfil(VentanaPrincipal ventana, Usuario usuario) {
		super(ventana, "Perfil", true);
		this.ventana = ventana;
		this.usuario = usuario;
		setSize(625, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Colores.FONDO);
		
		crearPanelNorte();
		crearPanelCentro();
	}
	
	//Panel con la foto e información sobre el usuario
	private void crearPanelNorte() {
		panelNorte = new JPanel();
		panelNorte.setBackground(Colores.FONDO);
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		//Foto de perfil
		JLabel lblFotoPerfil = new JLabel();
		lblFotoPerfil.setForeground(Color.WHITE);
		lblFotoPerfil.setBorder(new EmptyBorder(0, 20, 0, 20));
		ImageIcon fotoPerfil = Utilidades.getImagenRedimensionada(usuario.getFotoUsuario(), 100, 100);
		lblFotoPerfil.setIcon(fotoPerfil);
		panelNorte.add(lblFotoPerfil, BorderLayout.WEST);
		
		crearPanelInfo();
	}
	
	//Panel con la información sobre el usuario
	private void crearPanelInfo() {
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Colores.FONDO);
		panelNorte.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		//Línea 1 (email usuario, botón editar/seguir)
		JPanel panel1 = new JPanel();
		panel1.setBackground(Colores.FONDO);
		panelInfo.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblEmail = new JLabel(usuario.getEmail());
		lblEmail.setFont(FontManager.getFuente(15f));
		lblEmail.setForeground(Color.WHITE);
		panel1.add(lblEmail);
		
		//Botón editar perfil / seguir
		if(Controlador.INSTANCE.esUsuarioActual(usuario)) {
			btnEditar = new JButton("Editar perfil");
			btnEditar.setForeground(Color.WHITE);
			btnEditar.setBorderPainted(false);
			btnEditar.setBackground(Colores.RESALTADO);
			btnEditar.setFont(FontManager.getFuenteBold(15f));
			panel1.add(btnEditar);
			addManejadorBotonEditar();
		}
		else {
			btnSeguir = new JButton("Seguir");
			btnSeguir.setForeground(Color.WHITE);
			btnSeguir.setBorderPainted(false);
			btnSeguir.setBackground(Colores.RESALTADO);
			btnSeguir.setFont(FontManager.getFuenteBold(15f));
			panel1.add(btnSeguir);
			addManejadorBotonSeguir();
		}
		
		//Línea 2 (n publicaciones, n seguidores, n seguidos)
		JPanel panel2 = new JPanel();
		panel2.setBackground(Colores.FONDO);
		panelInfo.add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblNPublicaciones = new JLabel(usuario.getNumPublicaciones() + " publicaciones");
		lblNPublicaciones.setFont(FontManager.getFuente(15f));
		lblNPublicaciones.setForeground(Color.WHITE);
		panel2.add(lblNPublicaciones);
		
		JLabel lblNSeguidores = new JLabel(usuario.getNumSeguidores() + " seguidores");
		lblNSeguidores.setFont(FontManager.getFuente(15f));
		lblNSeguidores.setForeground(Color.WHITE);
		panel2.add(lblNSeguidores);
		
		JLabel lblNSeguidos = new JLabel(Controlador.INSTANCE.getNumSeguidos(usuario) + " seguidos");
		lblNSeguidos.setFont(FontManager.getFuente(15f));
		lblNSeguidos.setForeground(Color.WHITE);
		panel2.add(lblNSeguidos);
		
		//Línea 3 (nombre de usuario)
		JPanel panel3 = new JPanel();
		panel3.setBackground(Colores.FONDO);
		panelInfo.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblNombre = new JLabel(usuario.getNombreCompleto());
		lblNombre.setFont(FontManager.getFuente(15f));
		lblNombre.setForeground(Color.WHITE);
		panel3.add(lblNombre);
	}
	
	//Panel que muestra las fotos y los álbumes
	private void crearPanelCentro() {
		panelCentro = new JPanel();
		panelCentro.setBackground(Colores.FONDO);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		//Línea Fotos/Álbumes
		JPanel panelBotones = new JPanel();
		panelCentro.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setBackground(Colores.FONDO);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		//Botones
		btnFotos = new JButton("Fotos");
		btnFotos.setForeground(Color.WHITE);
		btnFotos.setBorderPainted(false);
		btnFotos.setBackground(Colores.RESALTADO);
		btnFotos.setFont(FontManager.getFuenteBold(15f));
		panelBotones.add(btnFotos);
		
		btnAlbumes = new JButton("Álbumes");
		btnAlbumes.setForeground(Color.WHITE);
		btnAlbumes.setBorderPainted(false);
		btnAlbumes.setBackground(Colores.RESALTADO);
		btnAlbumes.setFont(FontManager.getFuenteBold(15f));
		panelBotones.add(btnAlbumes);
		
		//Botón para añadir un álbum
		if(Controlador.INSTANCE.esUsuarioActual(usuario)) {
			btnNuevoAlbum = new JButton("A+");
			btnNuevoAlbum.setForeground(Color.WHITE);
			btnNuevoAlbum.setBorderPainted(false);
			btnNuevoAlbum.setBackground(Colores.RESALTADO);
			btnNuevoAlbum.setFont(FontManager.getFuenteBold(15f));
			panelBotones.add(btnNuevoAlbum);
			addManejadorBotonNuevoAlbum();
		}
		
		addManejadorBotonFotos();
		addManejadorBotonAlbumes();
		
		crearPanelMatriz();
	}
	
	//Panel que muestra la matriz de fotos/álbumes
	private void crearPanelMatriz() {
		panelMatriz = new JPanel();
		panelCentro.add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new CardLayout(0, 0));
		panelMatriz.setBackground(Colores.FONDO);
		
		panelMatriz.add(new PanelMatriz(VentanaPerfil.this, ventana, usuario, 0), "panel_fotos");
		panelMatriz.add(new PanelMatriz(VentanaPerfil.this, ventana, usuario, 1), "panel_albumes");
	}
	
	
	
	//Manejadores de botones
	private void addManejadorBotonEditar() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarPerfil();
			}
		});
	}
	
	//Mostrar ventana de registro modificada para editar el perfil
	private void editarPerfil() {
		VentanaRegistro ventanaEditar = new VentanaRegistro(null);
		ventanaEditar.modificarParaEdicion(usuario);
		ventanaEditar.setLocationRelativeTo(this);
		ventanaEditar.setVisible(true);
	}
	
	private void addManejadorBotonSeguir() {
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.seguirUsuario(usuario);
				//TODO Hacer algo que indique que se ha seguido al usuario
			}
		});
	}
	
	//Botones para cambiar entre fotos y álbumes
	private void addManejadorBotonFotos() {
		btnFotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelMatriz.getLayout());
				cl.show(panelMatriz, "panel_fotos");
			}
		});
	}
	
	private void addManejadorBotonAlbumes() {
		btnAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelMatriz.getLayout());
				cl.show(panelMatriz, "panel_albumes");
			}
		});
	}
	
	//Botón para crear un álbum
	private void addManejadorBotonNuevoAlbum() {
		btnNuevoAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloAlbum;
				boolean esTituloValido = false;
				//Mientras no se escoja un título válido
				while (!esTituloValido) {
					tituloAlbum = JOptionPane.showInputDialog("Introduce un título para el álbum");
					//Si se ha pulsado cancelar, salimos
					if (tituloAlbum == null) {
						break;
					}
					esTituloValido = Controlador.INSTANCE.esTituloAlbumValido(tituloAlbum);
					if (esTituloValido) {
						crearAlbum(tituloAlbum);
					}
					else {
						JOptionPane.showMessageDialog(null, "Título vacío o ya existe un álbum con ese título");
					}
				}
			}
		});
	}
	
	//Pantalla para subir la primera foto
	private void crearAlbum(String tituloAlbum) {
		//Indicamos que vamos a crear un álbum
		Controlador.INSTANCE.modoAlbum();
		//Ventana de diálogo para subir la primera foto
		JDialog subirFoto = new JDialog(VentanaPerfil.this, "Subir foto", true);
		subirFoto.add(new PanelSubir(ventana));
		subirFoto.setSize(900, 500);
		subirFoto.setResizable(false);
		subirFoto.setLocationRelativeTo(VentanaPerfil.this);
		subirFoto.setVisible(true);
		subirFoto.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//Creamos el álbum cuando se cierre la ventana de subir la primera foto
		Controlador.INSTANCE.crearAlbum(tituloAlbum);
	}
	

}
