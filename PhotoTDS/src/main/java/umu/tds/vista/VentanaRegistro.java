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

public class VentanaRegistro extends JDialog {

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
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(43, 44, 62));
		
		crearPanelRegistro();
		crearPanelBotones();
		
	}
	
	private void crearPanelRegistro() {
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(new Color(43, 44, 62));
		getContentPane().add(panelRegistro, BorderLayout.CENTER);
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWidths = new int[]{30, 0, 0};
		gbl_panelRegistro.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelRegistro.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRegistro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRegistro.setLayout(gbl_panelRegistro);
		
		//Foto
		
		crearLineaNombre(panelRegistro);
		crearLineaApellidos(panelRegistro);
		crearLineaEmail(panelRegistro);
		crearLineaPassword(panelRegistro);
		crearLineaPasswordRepeat(panelRegistro);
		crearLineaFechaNacimiento(panelRegistro);
		
		//Texto de presentación
		
	}
	
	private void crearPanelFoto(JPanel panelRegistro) {
		
	}
	
	private void crearLineaNombre(JPanel panelRegistro) {
		//JLabel
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNombre.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panelRegistro.add(lblNombre, gbc_lblNombre);
	}
	
	private void crearLineaApellidos(JPanel panelRegistro) {
		//JLabel
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblApellidos.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panelRegistro.add(lblApellidos, gbc_lblApellidos);
	}
	
	private void crearLineaEmail(JPanel panelRegistro) {
		//JLabel
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblEmail.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		panelRegistro.add(lblEmail, gbc_lblEmail);
	}
	
	private void crearLineaPassword(JPanel panelRegistro) {
		//JLabel
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPassword.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panelRegistro.add(lblPassword, gbc_lblPassword);
	}
	
	private void crearLineaPasswordRepeat(JPanel panelRegistro) {
		//JLabel
		JLabel lblPasswordRepeat = new JLabel("Contraseña (repetir)");
		lblPasswordRepeat.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblPasswordRepeat.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPasswordRepeat = new GridBagConstraints();
		gbc_lblPasswordRepeat.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordRepeat.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasswordRepeat.gridx = 1;
		gbc_lblPasswordRepeat.gridy = 5;
		panelRegistro.add(lblPasswordRepeat, gbc_lblPasswordRepeat);
	}
	
	private void crearLineaFechaNacimiento(JPanel panelRegistro) {
		//JLabel
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblFechaNacimiento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 6;
		panelRegistro.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
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
