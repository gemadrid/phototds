package umu.tds.vista;

import java.awt.EventQueue;

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
import java.awt.Font;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;

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
					Controlador.INSTANCE.registrarUsuario("Gema", "Madrid Sánchez", "gema@gmail.com", "gemadrid", "algo", "");
					VentanaLogin window = new VentanaLogin();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		frameLogin.getContentPane().setBackground(fondo);
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
		panelDer.setBackground(fondo);
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
		panelTitulo.setBackground(fondo);
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JLabel lblTitulo = new JLabel("Login");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Poppins", Font.BOLD, 30));
		panelTitulo.add(lblTitulo);
		
		return panelTitulo;
	}
	
	private JPanel crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(fondo);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		panelLogin.add(crearPanelCampos(), BorderLayout.CENTER);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panelLogin;
	}
	
	private JPanel crearPanelCampos() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(fondo);
		panelCampos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Poppins", Font.BOLD, 20));
		panelCampos.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Poppins", Font.PLAIN, 20));
		textUsuario.setColumns(18);
		textUsuario.setBorder(null);
		textUsuario.setBackground(areaTexto);
		panelCampos.add(textUsuario);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Poppins", Font.BOLD, 20));
		panelCampos.add(lblPassword);
		
		//TODO Comprobar que la contraseña se vea bien
		textPassword = new JPasswordField();
		textPassword.setBorder(null);
		textPassword.setBackground(areaTexto);
		textPassword.setFont(new Font("Poppins", Font.PLAIN, 20));
		textPassword.setColumns(18);
		panelCampos.add(textPassword);
		
		return panelCampos;
	}
	
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(resaltado);
		btnLogin.setFont(new Font("Poppins", Font.BOLD, 18));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnLogin);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setForeground(Color.WHITE);
		btnRegistro.setBorderPainted(false);
		btnRegistro.setBackground(resaltado);
		btnRegistro.setFont(new Font("Poppins", Font.BOLD, 18));
		btnRegistro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnRegistro);
		
		addManejadorBotonLogin(btnLogin);
		addManejadorBotonRegistro(btnRegistro);
		
		return panelBotones;
	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.INSTANCE
						.loginUsuario(textUsuario.getText(), new String(textPassword.getPassword()));
				if (login) {
					VentanaPrincipal principal = new VentanaPrincipal();
					principal.mostrarVentana();
					frameLogin.dispose();
				} else
					//TODO Poner un poco más bonito
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
				frameLogin.dispose();
			}
		});
	}

}
