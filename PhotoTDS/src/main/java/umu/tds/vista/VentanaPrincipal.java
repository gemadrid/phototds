package umu.tds.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	//Paneles
	private JPanel panelCentro;
	
	//Botones
	private JButton btnPrincipal;
	private JButton btnSubir;
	private JButton btnBuscar;
	private JButton btnUsuario;
	private JButton btnOpciones;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Mostrar ventana
	public void mostrarVentana() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("PhotoTDS");
		setSize(1000, 750);
		setMinimumSize(new Dimension(1000, 750));
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(fondo);
		
		//Creamos los diferentes paneles
		crearPanelNorte();
		crearPanelCentro();
	}
	
	//Crear panel norte
	private void crearPanelNorte() {
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelNorte.add(crearPanelLogo(), BorderLayout.WEST);
		panelNorte.add(crearPanelBotones(), BorderLayout.CENTER);
		panelNorte.add(crearPanelUsuario(), BorderLayout.EAST);
	}
	
	//Crear panel logo
	private JPanel crearPanelLogo() {
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		panelLogo.setBackground(fondo);
		
		JLabel lblLogo = new JLabel("PhotoTDS");
		lblLogo.setFont(new Font("Poppins Black", Font.PLAIN, 20));
		lblLogo.setForeground(resaltado);
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoLogo.png")));
		panelLogo.add(lblLogo);
		
		return panelLogo;
	}
	
	//Crear panel botones
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBotones.setBackground(fondo);
		
		//Botones
		btnPrincipal = new JButton("");
		btnPrincipal.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoPrincipal.png")));
		btnPrincipal.setBorder(null);
		btnPrincipal.setContentAreaFilled(false);
		panelBotones.add(btnPrincipal);
		
		btnSubir = new JButton("");
		btnSubir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoSubir.png")));
		btnSubir.setBorder(null);
		btnSubir.setContentAreaFilled(false);
		panelBotones.add(btnSubir);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setContentAreaFilled(false);
		panelBotones.add(btnBuscar);
		
		return panelBotones;
	}
	
	//Crear panel usuario
	private JPanel crearPanelUsuario() {
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		panelUsuario.setBackground(fondo);
		
		//TODO Poner de icono la foto de perfil del usuario
		ImageIcon iconoPerfil = new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/fotoperfil.jpg"));
		Image fotoPerfil = iconoPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon perfil = new ImageIcon(fotoPerfil);
		btnUsuario = new JButton("");
		btnUsuario.setIcon(perfil);
		btnUsuario.setBorder(null);
		btnUsuario.setContentAreaFilled(false);
		panelUsuario.add(btnUsuario);
		
		btnOpciones = new JButton("");
		btnOpciones.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoOpciones.png")));
		btnOpciones.setBorder(null);
		btnOpciones.setContentAreaFilled(false);
		panelUsuario.add(btnOpciones);
		
		return panelUsuario;
	}
	
	//Crear panel centro
	private void crearPanelCentro() {
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new CardLayout(0, 0));
		panelCentro.setBackground(fondo);
		
		panelCentro.add(new PanelPrincipal(VentanaPrincipal.this), "panel_principal");
		panelCentro.add(new PanelSubir(VentanaPrincipal.this), "panel_subir");
		panelCentro.add(new PanelBuscar(VentanaPrincipal.this), "panel_buscar");
		
		//Añadimos manejadores de eventos de botones
		addManejadorBotonPrincipal();
		addManejadorBotonSubir();
		addManejadorBotonBuscar();
	}
	
	//Manejadores de eventos de botones (panel norte)
	private void addManejadorBotonPrincipal() {
		btnPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelCentro.getLayout());
				cl.show(panelCentro, "panel_principal");
			}
		});
	}
	
	private void addManejadorBotonSubir() {
		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelCentro.getLayout());
				cl.show(panelCentro, "panel_subir");
			}
		});
	}
	
	private void addManejadorBotonBuscar() {
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelCentro.getLayout());
				cl.show(panelCentro, "panel_buscar");
			}
		});
	}

}
