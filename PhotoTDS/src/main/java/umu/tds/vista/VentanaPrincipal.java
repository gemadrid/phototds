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
	
	private JButton btnSubirFoto;
	
	//TextField
	private JTextField textBuscar;
	
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
		
		panelCentro.add(crearPanelPrincipal(), "panel_principal");
		panelCentro.add(crearPanelSubir(), "panel_subir");
		panelCentro.add(crearPanelBuscar(), "panel_buscar");
		
		//Añadimos manejadores de eventos de botones
		addManejadorBotonPrincipal();
		addManejadorBotonSubir();
		addManejadorBotonBuscar();
	}
	
	//Crear panel principal
	private JPanel crearPanelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(fondo);
		
		JLabel lblPrincipal = new JLabel("Panel Principal");
		lblPrincipal.setForeground(Color.WHITE);
		lblPrincipal.setFont(new Font("Poppins Black", Font.PLAIN, 20));
		panelPrincipal.add(lblPrincipal);
		
		return panelPrincipal;
	}
	
	//Crear panel subir
	private JEditorPane crearPanelSubir() {
		JEditorPane panelSubir = new JEditorPane();
		panelSubir.setBackground(fondo);
		GridBagLayout gbl_panelSubir = new GridBagLayout();
		gbl_panelSubir.columnWidths = new int[]{0};
		gbl_panelSubir.rowHeights = new int[]{0, 0, 30, 0, 60};
		gbl_panelSubir.columnWeights = new double[]{0.0};
		gbl_panelSubir.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panelSubir.setLayout(gbl_panelSubir);
		
		//Textos
		JLabel lblSubir = new JLabel("Subir Foto");
		lblSubir.setForeground(Color.WHITE);
		lblSubir.setFont(new Font("Poppins", Font.BOLD, 30));
		GridBagConstraints gbc_lblSubir = new GridBagConstraints();
		gbc_lblSubir.anchor = GridBagConstraints.WEST;
		gbc_lblSubir.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubir.gridx = 0;
		gbc_lblSubir.gridy = 0;
		panelSubir.add(lblSubir, gbc_lblSubir);
		
		JLabel lblSubirTexto = new JLabel("<html>Sube una foto para compartirla con tus amigos.<br>Puedes arrastrar el fichero aquí o seleccionarlo desde el explorador de archivos.");
		lblSubirTexto.setForeground(Color.WHITE);
		lblSubirTexto.setFont(new Font("Poppins", Font.PLAIN, 20));
		GridBagConstraints gbc_lblSubirTexto = new GridBagConstraints();
		gbc_lblSubirTexto.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubirTexto.anchor = GridBagConstraints.WEST;
		gbc_lblSubirTexto.gridx = 0;
		gbc_lblSubirTexto.gridy = 1;
		panelSubir.add(lblSubirTexto, gbc_lblSubirTexto);
		
		//Botón para subir desde el ordenador
		btnSubirFoto = new JButton("Subir foto de tu ordenador");
		btnSubirFoto.setForeground(Color.WHITE);
		btnSubirFoto.setBorderPainted(false);
		btnSubirFoto.setBackground(resaltado);
		btnSubirFoto.setFont(new Font("Poppins", Font.BOLD, 20));
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.gridx = 0;
		gbc_btnSubirFoto.gridy = 3;
		panelSubir.add(btnSubirFoto, gbc_btnSubirFoto);
		
		addManejadorBotonSubirFoto();
		
		//Funcionalidad drag-and-drop
		panelSubir.setEditable(false);
		panelSubir.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles) {
						//System.out.println(file.getPath());
						subirFoto(file.getPath());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		return panelSubir;
	}
	
	//Crear panel búsqueda
	private JPanel crearPanelBuscar() {
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBackground(fondo);
		panelBuscar.setLayout(new BorderLayout(0, 0));
		
		//Panel de búsqueda
		JPanel panelBuscarNorte = new JPanel();
		panelBuscarNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBuscarNorte.setBackground(fondo);
		panelBuscar.add(panelBuscarNorte, BorderLayout.NORTH);
		
		//Texto Buscar
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Poppins", Font.BOLD, 25));
		panelBuscarNorte.add(lblBuscar);
		
		//Área buscar
		textBuscar = new JTextField();
		textBuscar.setFont(new Font("Poppins", Font.PLAIN, 20));
		textBuscar.setBackground(areaTexto);
		textBuscar.setBorder(null);
		textBuscar.setColumns(25);
		panelBuscarNorte.add(textBuscar);
		
		//TODO Panel de resultados de búsqueda (separados por usuarios / hashtags)
		
		return panelBuscar;
	}
	
	//Método para subir fotos
	private void subirFoto(String path) {
		//TODO Comprobar validez de la foto
		VentanaSubirFoto subirFoto = new VentanaSubirFoto(this, path);
		subirFoto.setLocationRelativeTo(this);
		subirFoto.setVisible(true);
	}
	
	//Manejadores de eventos de botones (norte)
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
	
	//Manejadores de eventos de botones (pantalla principal)
	private void addManejadorBotonSubirFoto() {
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Poner JFileChooser más bonito
				JFileChooser chooser = new JFileChooser();
				//Filtramos por imágenes (con los formatos soportados por el Sistema Operativo)
				FileFilter imageFilter = new FileNameExtensionFilter("Archivos de imagen", ImageIO.getReaderFileSuffixes());
				chooser.addChoosableFileFilter(imageFilter);
				chooser.setAcceptAllFileFilterUsed(false);
				int seleccion = chooser.showOpenDialog(VentanaPrincipal.this);
				//Si se ha seleccionado una foto, llamamos a subirFoto
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File currentFile = chooser.getSelectedFile();
					subirFoto(currentFile.getPath());
				}
			}
		});
	}

}
