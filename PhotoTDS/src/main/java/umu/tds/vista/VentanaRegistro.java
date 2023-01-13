package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaRegistro extends JDialog {
	
	private JPanel panelRegistro;
	
	//Campos
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTextField textNombreUsuario;
	private JPasswordField textPassword;
	private JPasswordField textPasswordRepeat;
	private JTextArea textPresentacion;
	
	//Error
	private JLabel lblError;
	
	//Botones
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VentanaRegistro dialog = new VentanaRegistro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public VentanaRegistro(JFrame owner) {
		super(owner, "Registro", true);
		setSize(575, 600);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		crearPanelRegistro();
		crearPanelBotones();
		
	}
	
	private void crearPanelRegistro() {
		panelRegistro = new JPanel();
		panelRegistro.setBackground(fondo);
		getContentPane().add(panelRegistro, BorderLayout.CENTER);
		GridBagLayout gbl_panelRegistro_1 = new GridBagLayout();
		gbl_panelRegistro_1.columnWidths = new int[]{30, 0, 10, 0, 30, 0};
		gbl_panelRegistro_1.rowHeights = new int[]{20, 30, 20, 0, 0, 0, 0, 0, 0, 0, 0, 120, 10, 0, 0};
		gbl_panelRegistro_1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelRegistro_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRegistro.setLayout(gbl_panelRegistro_1);
		
		crearPanelFoto();
		
		crearLineaNombre();
		crearLineaApellidos();
		crearLineaEmail();
		crearLineaUsuario();
		crearLineaPassword();
		crearLineaPasswordRepeat();
		crearLineaFechaNacimiento();
		crearLineaPresentacion();
		crearLineaError();
		
	}
	
	//TODO Crear panel foto
	private void crearPanelFoto() {
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(fondo);
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
		textNombre.setBackground(areaTexto);
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
		textApellidos.setBackground(areaTexto);
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
		textEmail.setBackground(areaTexto);
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
	
	private void crearLineaUsuario() {
		JLabel lblUsuario = new JLabel("Nombre de usuario");
		lblUsuario.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 6;
		panelRegistro.add(lblUsuario, gbc_lblUsuario);
		
		//JTextField
		textNombreUsuario = new JTextField();
		textNombreUsuario.setBackground(areaTexto);
		textNombreUsuario.setBorder(null);
		textNombreUsuario.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textNombreUsuario = new GridBagConstraints();
		gbc_textNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textNombreUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombreUsuario.gridx = 3;
		gbc_textNombreUsuario.gridy = 6;
		panelRegistro.add(textNombreUsuario, gbc_textNombreUsuario);
		textNombreUsuario.setColumns(20);
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
		gbc_lblPassword.gridy = 7;
		panelRegistro.add(lblPassword, gbc_lblPassword);
		
		//JPasswordField
		textPassword = new JPasswordField();
		textPassword.setColumns(20);
		textPassword.setBackground(areaTexto);
		textPassword.setBorder(null);
		textPassword.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 7;
		panelRegistro.add(textPassword, gbc_passwordField);
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
		gbc_lblPasswordRepeat.gridy = 8;
		panelRegistro.add(lblPasswordRepeat, gbc_lblPasswordRepeat);
		
		//JPasswordField
		textPasswordRepeat = new JPasswordField();
		textPasswordRepeat.setColumns(20);
		textPasswordRepeat.setBackground(areaTexto);
		textPasswordRepeat.setBorder(null);
		textPasswordRepeat.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		GridBagConstraints gbc_passwordFieldRepeat = new GridBagConstraints();
		gbc_passwordFieldRepeat.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRepeat.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRepeat.gridx = 3;
		gbc_passwordFieldRepeat.gridy = 8;
		panelRegistro.add(textPasswordRepeat, gbc_passwordFieldRepeat);
	}
	
	//TODO Hacer sección fecha de nacimiento
	private void crearLineaFechaNacimiento() {
		//JLabel
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblFechaNacimiento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 9;
		panelRegistro.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
	}
	
	//TODO Comprobar que la presentación no pase de 200 caracteres
	private void crearLineaPresentacion() {
		//Jlabel
		JLabel lblPresentacion = new JLabel("Presentación (opcional)");
		lblPresentacion.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPresentacion.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPresentacion = new GridBagConstraints();
		gbc_lblPresentacion.anchor = GridBagConstraints.EAST;
		gbc_lblPresentacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblPresentacion.gridx = 1;
		gbc_lblPresentacion.gridy = 10;
		panelRegistro.add(lblPresentacion, gbc_lblPresentacion);
		
		//JTextArea
		textPresentacion = new JTextArea();
		textPresentacion.setTabSize(3);
		textPresentacion.setLineWrap(true);
		textPresentacion.setWrapStyleWord(true);
		textPresentacion.setRows(20);
		textPresentacion.setColumns(20);
		textPresentacion.setBackground(areaTexto);
		textPresentacion.setBorder(null);
		textPresentacion.setFont(new Font("Poppins", Font.PLAIN, 15));
		GridBagConstraints gbc_textPresentacion = new GridBagConstraints();
		gbc_textPresentacion.gridheight = 2;
		gbc_textPresentacion.insets = new Insets(0, 0, 5, 5);
		gbc_textPresentacion.fill = GridBagConstraints.BOTH;
		gbc_textPresentacion.gridx = 3;
		gbc_textPresentacion.gridy = 10;
		panelRegistro.add(textPresentacion, gbc_textPresentacion);
	}
	
	private void crearLineaError() {
		lblError = new JLabel("");
		lblError.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblError.setForeground(Color.WHITE);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 5;
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 13;
		panelRegistro.add(lblError, gbc_lblError);
		lblError.setVisible(false);
	}
	
	//Crear botones
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelBotones.setBackground(fondo);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(resaltado);
		btnAceptar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(resaltado);
		btnCancelar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnCancelar);
		
		addManejadorBotonAceptar();
		addManejadorBotonCancelar();
	}
	
	//TODO Poner JOptionPane más bonitos
	private void addManejadorBotonAceptar() {
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = comprobarCampos();
				if (check) {
					boolean registrado = Controlador.INSTANCE.registrarUsuario(
							textNombre.getText(),
							textApellidos.getText(),
							textEmail.getText(),
							textNombreUsuario.getText(),
							new String(textPassword.getPassword()),
							"");
					if (registrado) {
						JOptionPane.showMessageDialog(VentanaRegistro.this, "Usuario registrado correctamente.", "Registro", JOptionPane.INFORMATION_MESSAGE);
						VentanaLogin login = new VentanaLogin();
						login.mostrarVentana();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(VentanaRegistro.this, "No se ha podido llevar a cabo el registro.", "Registro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void addManejadorBotonCancelar() {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin login = new VentanaLogin();
				login.mostrarVentana();
				dispose();
			}
		});
	}
	
	private boolean comprobarCampos() {
		boolean check = true;
		lblError.setVisible(false);
		//Comprobar nombre
		if (textNombre.getText().trim().isEmpty()) {
			lblError.setText("Es obligatorio introducir un nombre.");
			check = false;
		}
		else if (textApellidos.getText().trim().isEmpty()) {
			lblError.setText("Es obligatorio introducir apellidos.");
			check = false;
		}
		else if (textEmail.getText().trim().isEmpty()) {
			lblError.setText("Es obligatorio introducir un email.");
			check = false;
		}
		//Comprobar usuario
		else if (textNombreUsuario.getText().trim().isEmpty()) {
			lblError.setText("Es obligatorio introducir un nombre de usuario.");
			check = false;
		}
		//Comprobar que no exista un usuario con el mismo nombre
		else if (Controlador.INSTANCE.esUsuarioRegistrado(textNombreUsuario.getText())) {
			lblError.setText("El nombre de usuario no está disponible.");
			check = false;
		}
		//Comprobar contraseña
		else {
			String password = new String(textPassword.getPassword());
			String passwordRepeat = new String(textPasswordRepeat.getPassword());
			if (password.isEmpty()) {
				lblError.setText("Es obligatorio introducir una contraseña.");
				check = false;
			}
			else if (!password.equals(passwordRepeat))  {
				lblError.setText("Las contraseñas no coinciden.");
				check = false;
			}
		}
		//Comprobar fecha de nacimiento
		//Comprobar presentación
		
		if (!check) lblError.setVisible(true);
		return check;
	}

}
