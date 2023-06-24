package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VentanaPremium extends JDialog {
	
	//Botones
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	//Panel
	private JPanel panelPremium;
	
	//ComboBox
	private JComboBox<String> comboBox;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the dialog.
	 */
	public VentanaPremium(JFrame owner) {
		super(owner, "Hacerse premium", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		panelPremium = new JPanel();
		panelPremium.setBackground(fondo);
		panelPremium.setLayout(new BorderLayout(10, 10));
		panelPremium.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panelPremium, BorderLayout.CENTER);
		
		crearPanelDescuentos();
		crearPanelBotones();
		
		pack();
	}
	
	private void crearPanelDescuentos() {
		//Label descuentos
		JLabel lblDescuentos = new JLabel("Descuentos disponibles");
		lblDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescuentos.setForeground(Color.WHITE);
		lblDescuentos.setFont(new Font("Poppins SemiBold", Font.PLAIN, 15));
		panelPremium.add(lblDescuentos, BorderLayout.NORTH);
		
		//Lista con los descuentos
		List<String> descuentos = Controlador.INSTANCE.getDescuentosAplicables();
		comboBox = new JComboBox<String>();
		comboBox.setBackground(areaTexto);
		comboBox.setFont(new Font("Poppins", Font.PLAIN, 15));
		descuentos.forEach(d -> comboBox.addItem(d));
		panelPremium.add(comboBox, BorderLayout.CENTER);
		
	}
	
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelPremium.add(panelBotones, BorderLayout.SOUTH);
		
		//Botón comentar
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(resaltado);
		btnAceptar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnAceptar);
		
		//Botón cancelar
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(resaltado);
		btnCancelar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnCancelar);
		
		addManejadorBotonComentar();
		addManejadorBotonCancelar();
	}
	
	//Manejadores de botones
	private void addManejadorBotonComentar() {
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descuentoEscogido = (String) comboBox.getSelectedItem();
				if (descuentoEscogido == null) descuentoEscogido = "Null";
				double precio = Controlador.INSTANCE.hacersePremium(descuentoEscogido);
				JOptionPane.showMessageDialog(VentanaPremium.this, "Cantidad a pagar: " + precio + " euros", "Precio", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void addManejadorBotonCancelar() {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
