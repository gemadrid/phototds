package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class VentanaRegistro extends JDialog {
	
	private JPanel panelRegistro;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaRegistro dialog = new VentanaRegistro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaRegistro() {
		setSize(575, 525);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(43, 44, 62));
		
		crearPanelRegistro();
		crearPanelBotones();
		
	}
	
	private void crearPanelRegistro() {
		panelRegistro = new JPanel();
		panelRegistro.setBackground(new Color(43, 44, 62));
		getContentPane().add(panelRegistro, BorderLayout.CENTER);
		GridBagLayout gbl_panelRegistro_1 = new GridBagLayout();
		gbl_panelRegistro_1.columnWidths = new int[]{30, 0, 10, 0, 30, 0};
		gbl_panelRegistro_1.rowHeights = new int[]{20, 30, 20, 0, 0, 0, 0, 0, 0, 0, 100, 0};
		gbl_panelRegistro_1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelRegistro_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRegistro.setLayout(gbl_panelRegistro_1);
		
		crearPanelFoto();
		
		crearLineaNombre();
		crearLineaApellidos();
		crearLineaEmail();
		crearLineaPassword();
		crearLineaPasswordRepeat();
		crearLineaFechaNacimiento();
		crearLineaPresentacion();
		
	}
	
	private void crearPanelFoto() {
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(new Color(43, 44, 62));
		GridBagConstraints gbc_panelFoto = new GridBagConstraints();
		gbc_panelFoto.gridwidth = 5;
		gbc_panelFoto.insets = new Insets(0, 0, 5, 0);
		gbc_panelFoto.fill = GridBagConstraints.BOTH;
		gbc_panelFoto.gridx = 0;
		gbc_panelFoto.gridy = 1;
		panelRegistro.add(panelFoto, gbc_panelFoto);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/umu/tds/resources/fotoperfil.jpg")));
		panelFoto.add(lblFoto);
	}
	
	private void crearLineaNombre() {
		//JLabel
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNombre.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		panelRegistro.add(lblNombre, gbc_lblNombre);
		
		//JTextField
		textNombre = new JTextField();
		textNombre.setBackground(new Color(242, 242, 242));
		textNombre.setBorder(null);
		textNombre.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 3;
		gbc_textNombre.gridy = 3;
		panelRegistro.add(textNombre, gbc_textNombre);
		textNombre.setColumns(20);
	}
	
	private void crearLineaApellidos() {
		//JLabel
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblApellidos.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 4;
		panelRegistro.add(lblApellidos, gbc_lblApellidos);
		
		//JTextField
		textApellidos = new JTextField();
		textApellidos.setBackground(new Color(242, 242, 242));
		textApellidos.setBorder(null);
		textApellidos.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 3;
		gbc_textApellidos.gridy = 4;
		panelRegistro.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(20);
	}
	
	private void crearLineaEmail() {
		//JLabel
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblEmail.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 5;
		panelRegistro.add(lblEmail, gbc_lblEmail);
		
		//JTextField
		textEmail = new JTextField();
		textEmail.setBackground(new Color(242, 242, 242));
		textEmail.setBorder(null);
		textEmail.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 3;
		gbc_textEmail.gridy = 5;
		panelRegistro.add(textEmail, gbc_textEmail);
		textEmail.setColumns(20);
	}
	
	private void crearLineaPassword() {
		//JLabel
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPassword.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 6;
		panelRegistro.add(lblPassword, gbc_lblPassword);
		
		//JPasswordField
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setBackground(new Color(242, 242, 242));
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		panelRegistro.add(passwordField, gbc_passwordField);
	}
	
	private void crearLineaPasswordRepeat() {
		//JLabel
		JLabel lblPasswordRepeat = new JLabel("Contraseña (repetir)");
		lblPasswordRepeat.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPasswordRepeat.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPasswordRepeat = new GridBagConstraints();
		gbc_lblPasswordRepeat.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordRepeat.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordRepeat.gridx = 1;
		gbc_lblPasswordRepeat.gridy = 7;
		panelRegistro.add(lblPasswordRepeat, gbc_lblPasswordRepeat);
		
		//JPasswordField
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setColumns(20);
		passwordFieldRepeat.setBackground(new Color(242, 242, 242));
		passwordFieldRepeat.setBorder(null);
		passwordFieldRepeat.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		GridBagConstraints gbc_passwordFieldRepeat = new GridBagConstraints();
		gbc_passwordFieldRepeat.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRepeat.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRepeat.gridx = 3;
		gbc_passwordFieldRepeat.gridy = 7;
		panelRegistro.add(passwordFieldRepeat, gbc_passwordFieldRepeat);
	}
	
	private void crearLineaFechaNacimiento() {
		//JLabel
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblFechaNacimiento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 8;
		panelRegistro.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
	}
	
	private void crearLineaPresentacion() {
		//Jlabel
		JLabel lblPresentacion = new JLabel("Presentación (opcional)");
		lblPresentacion.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPresentacion.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPresentacion = new GridBagConstraints();
		gbc_lblPresentacion.anchor = GridBagConstraints.EAST;
		gbc_lblPresentacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblPresentacion.gridx = 1;
		gbc_lblPresentacion.gridy = 9;
		panelRegistro.add(lblPresentacion, gbc_lblPresentacion);
		
		//JTextArea
		JTextArea textPresentacion = new JTextArea();
		textPresentacion.setTabSize(3);
		textPresentacion.setLineWrap(true);
		textPresentacion.setWrapStyleWord(true);
		textPresentacion.setRows(20);
		textPresentacion.setColumns(20);
		textPresentacion.setBackground(new Color(242, 242, 242));
		textPresentacion.setBorder(null);
		textPresentacion.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textPresentacion = new GridBagConstraints();
		gbc_textPresentacion.gridheight = 2;
		gbc_textPresentacion.insets = new Insets(0, 0, 0, 5);
		gbc_textPresentacion.fill = GridBagConstraints.BOTH;
		gbc_textPresentacion.gridx = 3;
		gbc_textPresentacion.gridy = 9;
		panelRegistro.add(textPresentacion, gbc_textPresentacion);
	}
	
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelBotones.setBackground(new Color(43, 44, 62));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(new Color(255, 97, 96));
		btnAceptar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(new Color(255, 97, 96));
		btnCancelar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnCancelar);
	}

}
