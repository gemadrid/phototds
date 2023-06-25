package umu.tds.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;
import umu.tds.vista.utilidades.Colores;
import umu.tds.vista.utilidades.FontManager;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin {

	private JFrame frameLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}
	
	public void mostrarVentana() {
		frameLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setTitle("PhotoTDS");
		
		frameLogin.getContentPane().setBackground(Colores.FONDO);
		frameLogin.setResizable(false);
		frameLogin.setSize(850, 550);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameLogin.setLocationRelativeTo(null);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{425, 0, 0};
		gridBagLayout.rowHeights = new int[]{75, 0, 75, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frameLogin.getContentPane().setLayout(gridBagLayout);
		
		//Crear label del logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/resources/background.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.gridheight = 3;
		gbc_lblLogo.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		frameLogin.getContentPane().add(lblLogo, gbc_lblLogo);
		
		//Crear panel derecho
		crearPanelDer();
	}
	
	private void crearPanelDer() {
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Colores.FONDO);
		GridBagConstraints gbc_panelDer = new GridBagConstraints();
		gbc_panelDer.insets = new Insets(0, 0, 5, 0);
		gbc_panelDer.fill = GridBagConstraints.BOTH;
		gbc_panelDer.gridx = 1;
		gbc_panelDer.gridy = 1;
		frameLogin.getContentPane().add(panelDer, gbc_panelDer);
		panelDer.setLayout(new BorderLayout(0, 0));
		
		panelDer.add(crearPanelTitulo(), BorderLayout.NORTH);
		panelDer.add(crearPanelLogin(), BorderLayout.CENTER);
	}
	
	private JPanel crearPanelTitulo() {
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Colores.FONDO);
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JLabel lblTitulo = new JLabel("Login");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(FontManager.getFuenteBold(30f));
		panelTitulo.add(lblTitulo);
		
		return panelTitulo;
	}
	
	private JPanel crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Colores.FONDO);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		panelLogin.add(crearPanelCampos(), BorderLayout.CENTER);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panelLogin;
	}
	
	private JPanel crearPanelCampos() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(Colores.FONDO);
		panelCampos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(FontManager.getFuenteBold(20f));
		panelCampos.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setFont(FontManager.getFuente(20f));
		textUsuario.setColumns(21);
		textUsuario.setBorder(null);
		textUsuario.setBackground(Colores.AREA_TEXTO);
		panelCampos.add(textUsuario);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(FontManager.getFuenteBold(20f));
		panelCampos.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBorder(null);
		textPassword.setBackground(Colores.AREA_TEXTO);
		textPassword.setFont(FontManager.getFuente(20f));
		textPassword.setColumns(21);
		panelCampos.add(textPassword);
		
		return panelCampos;
	}
	
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Colores.FONDO);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(Colores.RESALTADO);
		btnLogin.setFont(FontManager.getFuenteBold(18f));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnLogin);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setForeground(Color.WHITE);
		btnRegistro.setBorderPainted(false);
		btnRegistro.setBackground(Colores.RESALTADO);
		btnRegistro.setFont(FontManager.getFuenteBold(18f));
		btnRegistro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnRegistro);
		
		addManejadorBotonLogin(btnLogin);
		addManejadorBotonRegistro(btnRegistro);
		
		return panelBotones;
	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE.loginUsuario(textUsuario.getText(), new String(textPassword.getPassword()));
				if (login) {
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					frameLogin.dispose();
				} else
					JOptionPane.showMessageDialog(frameLogin, "Nombre de usuario o contraseña no valido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro registro = new VentanaRegistro(frameLogin);
				registro.setLocationRelativeTo(frameLogin);
				registro.setVisible(true);
			}
		});
	}

}
