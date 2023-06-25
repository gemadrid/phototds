package umu.tds.vista;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.controlador.Controlador;
import umu.tds.vista.utilidades.Colores;
import umu.tds.vista.utilidades.FontManager;
import umu.tds.vista.utilidades.Utilidades;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	//Paneles
	private JPanel panelCentro;
	private PanelPrincipal panelPrincipal;
	private PanelBuscar panelBuscar;
	
	//Botones
	private JButton btnPrincipal;
	private JButton btnSubir;
	private JButton btnBuscar;
	private JButton btnUsuario;
	private JButton btnOpciones;
	
	//Pulsador
	Luz pulsador;
	
	//Menú opciones
	private JPopupMenu popupMenu;
	private JMenuItem mntmPremium;
	private JMenuItem mntmPdf;
	private JMenuItem mntmExcel;
	private JMenuItem mntmTop;
	private JMenuItem mntmLogout;

	
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
		contentPane.setBackground(Colores.FONDO);
		
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
		panelLogo.setBackground(Colores.FONDO);
		
		JLabel lblLogo = new JLabel("PhotoTDS");
		lblLogo.setFont(FontManager.getFuenteBold(25f));
		lblLogo.setForeground(Colores.RESALTADO);
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoLogo.png")));
		panelLogo.add(lblLogo);
		
		return panelLogo;
	}
	
	//Crear panel botones
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBotones.setBackground(Colores.FONDO);
		
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
		
		//Pulsador
		pulsador = new Luz();
		pulsador.setColor(Colores.RESALTADO);
		panelBotones.add(pulsador);
		
		addManejadorBotonPrincipal();
		addManejadorBotonSubir();
		addManejadorBotonBuscar();
		
		addManejadorPulsador();
		
		return panelBotones;
	}
	
	//Crear panel usuario
	private JPanel crearPanelUsuario() {
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		panelUsuario.setBackground(Colores.FONDO);
		
		//Foto de perfil del usuario
		ImageIcon iconoPerfil = Utilidades.getImagenRedimensionada(Controlador.INSTANCE.getFotoUsuarioActual(), 40, 40);
		btnUsuario = new JButton("");
		btnUsuario.setIcon(iconoPerfil);
		btnUsuario.setBorder(null);
		btnUsuario.setContentAreaFilled(false);
		panelUsuario.add(btnUsuario);
		
		//Botón de opciones
		btnOpciones = new JButton("");
		btnOpciones.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/resources/IconoOpciones.png")));
		btnOpciones.setBorder(null);
		btnOpciones.setContentAreaFilled(false);
		panelUsuario.add(btnOpciones);
		
		//Menú que aparece al pulsar el botón de opciones
		popupMenu = new JPopupMenu();
		panelUsuario.add(popupMenu);
		
		//Items del menú
		mntmPremium = new JMenuItem("Premium");
		mntmPremium.setFont(FontManager.getFuente(13f));
		mntmPdf = new JMenuItem("Generar PDF");
		mntmPdf.setFont(FontManager.getFuente(13f));
		mntmExcel = new JMenuItem("Generar Excel");
		mntmExcel.setFont(FontManager.getFuente(13f));
		mntmTop = new JMenuItem("Top Me Gusta");
		mntmTop.setFont(FontManager.getFuente(13f));
		mntmLogout = new JMenuItem("Cerrar sesión");
		mntmLogout.setFont(FontManager.getFuente(13f));
		
		//Si el usuario actual no es premium, deshabilitar los botones premium
		if (!Controlador.INSTANCE.isPremiumUsuarioActual()) {
			mntmPdf.setEnabled(false);
			mntmExcel.setEnabled(false);
			mntmTop.setEnabled(false);
		}
		
		popupMenu.add(mntmPremium);
		popupMenu.add(mntmPdf);
		popupMenu.add(mntmExcel);
		popupMenu.add(mntmTop);
		popupMenu.add(mntmLogout);
		
		addManejadorBotonUsuario();
		addManejadorBotonOpciones();
		
		addManejadorMenuPremium();
		addManejadorMenuPdf();
		addManejadorMenuExcel();
		addManejadorMenuTop();
		addManejadorMenuLogout();
		
		return panelUsuario;
	}
	
	//Crear panel centro
	private void crearPanelCentro() {
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new CardLayout(0, 0));
		panelCentro.setBackground(Colores.FONDO);
		
		panelPrincipal = new PanelPrincipal(VentanaPrincipal.this);
		panelBuscar = new PanelBuscar(VentanaPrincipal.this);
		
		panelCentro.add(panelPrincipal, "panel_principal");
		panelCentro.add(new PanelSubir(VentanaPrincipal.this), "panel_subir");
		panelCentro.add(panelBuscar, "panel_buscar");
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
	
	private void addManejadorPulsador() {
		pulsador.addEncendidoListener(new IEncendidoListener() {
			@Override
			public void enteradoCambioEncendido(EventObject e) {
				JFileChooser chooser = new JFileChooser();
				//Filtramos para que solo aparezcan archivos XML
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Ficheros XML (*.xml)", "xml");
				chooser.setFileFilter(xmlFilter);
				chooser.setAcceptAllFileFilterUsed(false);
				//Opción seleccionada
				int seleccion = chooser.showOpenDialog(VentanaPrincipal.this);
				//Si se ha seleccionado una foto, obtenemos el path
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getPath();
					Controlador.INSTANCE.cargarFotos(path);
				}
			}
		});
	}
	
	private void addManejadorBotonUsuario() {
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPerfil perfil = new VentanaPerfil(VentanaPrincipal.this, Controlador.INSTANCE.getUsuarioActual());
				perfil.setLocationRelativeTo(VentanaPrincipal.this);
				perfil.setVisible(true);
			}
		});
	}
	
	private void addManejadorBotonOpciones() {
		btnOpciones.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	//Manejadores de eventos del menú de opciones
	private void addManejadorMenuPremium() {
		mntmPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPremium ventanaPremium = new VentanaPremium(VentanaPrincipal.this);
				ventanaPremium.setLocationRelativeTo(VentanaPrincipal.this);
				ventanaPremium.setVisible(true);
				//Si el usuario se ha hecho premium, habilitamos los botones
				if (Controlador.INSTANCE.isPremiumUsuarioActual()) {
					mntmPdf.setEnabled(true);
					mntmExcel.setEnabled(true);
					mntmTop.setEnabled(true);
				}
			}
		});
	}
	
	private void addManejadorMenuPdf() {
		mntmPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.generarPDF();
			}
		});
	}
	
	private void addManejadorMenuExcel() {
		mntmExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.generarExcel();
			}
		});
	}
	
	private void addManejadorMenuTop() {
		mntmTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaTopMegusta ventanaTop = new VentanaTopMegusta(VentanaPrincipal.this);
				ventanaTop.setLocationRelativeTo(VentanaPrincipal.this);
				ventanaTop.setVisible(true);
			}
		});
	}
	
	private void addManejadorMenuLogout() {
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.logout();
				VentanaLogin ventana = new VentanaLogin();
				ventana.mostrarVentana();
				dispose();
			}
		});
	}

}
