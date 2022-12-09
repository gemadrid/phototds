package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class VentanaLogin {

	private JFrame frameLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setTitle("PhotoTDS");
		
		frameLogin.getContentPane().setBackground(new Color(43, 44, 62));
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/resources/background.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frameLogin.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		//crearPanelIzq();
		crearPanelDer();
		
	}
	
	private void crearPanelIzq() {
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(new Color(234, 115, 182));
		GridBagConstraints gbc_panelIzq = new GridBagConstraints();
		gbc_panelIzq.gridheight = 3;
		gbc_panelIzq.insets = new Insets(0, 0, 0, 5);
		gbc_panelIzq.fill = GridBagConstraints.BOTH;
		gbc_panelIzq.gridx = 0;
		gbc_panelIzq.gridy = 0;
		frameLogin.getContentPane().add(panelIzq, gbc_panelIzq);
	}
	
	private void crearPanelDer() {
		JPanel panelDer = new JPanel();
		panelDer.setBackground(new Color(43, 44, 62));
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
		panelTitulo.setBackground(new Color(43, 44, 62));
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JLabel lblTitulo = new JLabel("Login");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Poppins", Font.BOLD, 30));
		panelTitulo.add(lblTitulo);
		
		return panelTitulo;
	}
	
	private JPanel crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(43, 44, 62));
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		panelLogin.add(crearPanelCampos(), BorderLayout.CENTER);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panelLogin;
	}
	
	private JPanel crearPanelCampos() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(new Color(43, 44, 62));
		panelCampos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Poppins", Font.BOLD, 20));
		panelCampos.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Poppins", Font.PLAIN, 20));
		textUsuario.setColumns(18);
		textUsuario.setBorder(null);
		textUsuario.setBackground(new Color(242, 242, 242));
		panelCampos.add(textUsuario);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Poppins", Font.BOLD, 20));
		panelCampos.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBorder(null);
		textPassword.setBackground(new Color(242, 242, 242));
		textPassword.setFont(new Font("Poppins", Font.PLAIN, 20));
		textPassword.setColumns(18);
		panelCampos.add(textPassword);
		
		return panelCampos;
	}
	
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(43, 44, 62));
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(new Color(255, 97, 96));
		btnLogin.setFont(new Font("Poppins", Font.BOLD, 18));
		btnLogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnLogin);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setForeground(Color.WHITE);
		btnRegistro.setBorderPainted(false);
		btnRegistro.setBackground(new Color(255, 97, 96));
		btnRegistro.setFont(new Font("Poppins", Font.BOLD, 18));
		btnRegistro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelBotones.add(btnRegistro);
		
		return panelBotones;
	}

}
