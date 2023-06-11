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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;
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
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaPerfil dialog = new VentanaPerfil(null, null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPerfil(JFrame owner, Usuario usuario) {
		super(owner, "Perfil", true);
		this.usuario = usuario;
		setSize(625, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
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
		
		//TODO Foto de perfil
		JLabel lblFotoPerfil = new JLabel("Foto de perfil");
		lblFotoPerfil.setForeground(Color.WHITE);
		lblFotoPerfil.setBorder(new EmptyBorder(0, 20, 0, 20));
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
		
		//Botón
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
		
		panelMatriz.add(crearPanelFotos(), "panel_fotos");
		panelMatriz.add(crearPanelAlbumes(), "panel_albumes");
	}
	
	private JPanel crearPanelFotos() {
		JPanel panelFotos = new JPanel();
		//TODO Crear un nuevo PanelPublicacion que contenga la publicacion
		
		//Ponemos un GridLayout con 3 columnas y una separación de 5 píxeles entre componentes
		panelFotos.setLayout(new GridLayout(0, 3, 5, 5));
		//TODO Añadimos todas las fotos
		
		return panelFotos;
	}
	
	private JPanel crearPanelAlbumes() {
		JPanel panelAlbumes = new JPanel();
		return panelAlbumes;
	}
	
	
	
	//Manejadores de botones
	private void addManejadorBotonEditar() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
	}
	
	private void addManejadorBotonSeguir() {
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.seguirUsuario(usuario);
				//TODO Hacer algo que indique que se ha seguido al usuario
			}
		});
	}
	
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
	
	

}
