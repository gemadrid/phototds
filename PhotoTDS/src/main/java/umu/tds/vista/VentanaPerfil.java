package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollBar;

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
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

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
		getContentPane().setBackground(fondo);
		
		crearPanelNorte();
		crearPanelCentro();
	}
	
	//Panel con la foto e información sobre el usuario
	private void crearPanelNorte() {
		panelNorte = new JPanel();
		panelNorte.setBackground(fondo);
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		//Foto de perfil
		JLabel lblFotoPerfil = new JLabel();
		lblFotoPerfil.setForeground(Color.WHITE);
		lblFotoPerfil.setBorder(new EmptyBorder(0, 20, 0, 20));
		ImageIcon fotoPerfil = getImagenRedimensionada(usuario.getFotoUsuario(), 100, 100);
		lblFotoPerfil.setIcon(fotoPerfil);
		panelNorte.add(lblFotoPerfil, BorderLayout.WEST);
		
		crearPanelInfo();
	}
	
	//Panel con la información sobre el usuario
	private void crearPanelInfo() {
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(fondo);
		panelNorte.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		//Línea 1 (email usuario, botón editar/seguir)
		JPanel panel1 = new JPanel();
		panel1.setBackground(fondo);
		panelInfo.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblEmail = new JLabel(usuario.getEmail());
		lblEmail.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblEmail.setForeground(Color.WHITE);
		panel1.add(lblEmail);
		
		//Botón editar perfil / seguir
		//TODO ¿Factorizar?
		if(Controlador.INSTANCE.esUsuarioActual(usuario)) {
			btnEditar = new JButton("Editar perfil");
			btnEditar.setForeground(Color.WHITE);
			btnEditar.setBorderPainted(false);
			btnEditar.setBackground(resaltado);
			btnEditar.setFont(new Font("Poppins", Font.BOLD, 15));
			panel1.add(btnEditar);
			addManejadorBotonEditar();
		}
		else {
			btnSeguir = new JButton("Seguir");
			btnSeguir.setForeground(Color.WHITE);
			btnSeguir.setBorderPainted(false);
			btnSeguir.setBackground(resaltado);
			btnSeguir.setFont(new Font("Poppins", Font.BOLD, 15));
			panel1.add(btnSeguir);
			addManejadorBotonSeguir();
		}
		
		//Línea 2 (n publicaciones, n seguidores, n seguidos)
		JPanel panel2 = new JPanel();
		panel2.setBackground(fondo);
		panelInfo.add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblNPublicaciones = new JLabel(usuario.getNumPublicaciones() + " publicaciones");
		lblNPublicaciones.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNPublicaciones.setForeground(Color.WHITE);
		panel2.add(lblNPublicaciones);
		
		JLabel lblNSeguidores = new JLabel(usuario.getNumSeguidores() + " seguidores");
		lblNSeguidores.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNSeguidores.setForeground(Color.WHITE);
		panel2.add(lblNSeguidores);
		
		JLabel lblNSeguidos = new JLabel(Controlador.INSTANCE.getNumSeguidos(usuario) + " seguidos");
		lblNSeguidos.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNSeguidos.setForeground(Color.WHITE);
		panel2.add(lblNSeguidos);
		
		//Línea 3 (nombre de usuario)
		JPanel panel3 = new JPanel();
		panel3.setBackground(fondo);
		panelInfo.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		//Texto
		JLabel lblNombre = new JLabel(usuario.getNombreCompleto());
		lblNombre.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNombre.setForeground(Color.WHITE);
		panel3.add(lblNombre);
	}
	
	//Panel que muestra las fotos y los álbumes
	private void crearPanelCentro() {
		panelCentro = new JPanel();
		panelCentro.setBackground(fondo);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		//Línea Fotos/Álbumes
		JPanel panelBotones = new JPanel();
		panelCentro.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		//Botones
		btnFotos = new JButton("Fotos");
		btnFotos.setForeground(Color.WHITE);
		btnFotos.setBorderPainted(false);
		btnFotos.setBackground(resaltado);
		btnFotos.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnFotos);
		
		btnAlbumes = new JButton("Álbumes");
		btnAlbumes.setForeground(Color.WHITE);
		btnAlbumes.setBorderPainted(false);
		btnAlbumes.setBackground(resaltado);
		btnAlbumes.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnAlbumes);
		
		//Botón para añadir un álbum
		if(Controlador.INSTANCE.esUsuarioActual(usuario)) {
			btnNuevoAlbum = new JButton("A+");
			btnNuevoAlbum.setForeground(Color.WHITE);
			btnNuevoAlbum.setBorderPainted(false);
			btnNuevoAlbum.setBackground(resaltado);
			btnNuevoAlbum.setFont(new Font("Poppins", Font.BOLD, 15));
			panelBotones.add(btnNuevoAlbum);
			addManejadorBotonNuevoAlbum();
		}
		
		addManejadorBotonFotos();
		addManejadorBotonAlbumes();
		
		crearPanelMatriz();
	}
	
	//TODO
	//Panel que muestra la matriz de fotos/álbumes
	private void crearPanelMatriz() {
		panelMatriz = new JPanel();
		panelCentro.add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new CardLayout(0, 0));
		panelMatriz.setBackground(fondo);
		
		panelMatriz.add(new PanelMatriz(VentanaPerfil.this, ventana, usuario, 0), "panel_fotos");
		panelMatriz.add(new PanelMatriz(VentanaPerfil.this, ventana, usuario, 1), "panel_albumes");
	}
	
	
	
	//Manejadores de botones
	private void addManejadorBotonEditar() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarPerfil();
				//TODO Recargar ventana principal
				//TODO Recargar ventana de perfil
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
	
	//Método para redimensionar imágenes
	private ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		try {
			//Leemos la imagen
			BufferedImage imagen = ImageIO.read(new File(path));
			//Redimensionamos la imagen
			Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_FAST);
			return new ImageIcon(imagenRedimensionada);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
