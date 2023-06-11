package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import umu.tds.modelo.Publicacion;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import javax.swing.JList;

public class VentanaPremium extends JDialog {
	
	//TextArea
	private JTextArea textoComentario;
	
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
	public static void main(String[] args) {
		try {
			VentanaPremium dialog = new VentanaPremium(null);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPremium(JFrame owner) {
		super(owner, "Hacerse premium", true);
		setSize(400, 500);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		crearPanelDescuentos();
		crearPanelBotones();
	}
	
	private void crearPanelDescuentos() {
		//TODO Poner espacio
		JLabel lblDescuentos = new JLabel("Descuentos disponibles");
		lblDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescuentos.setForeground(Color.WHITE);
		lblDescuentos.setFont(new Font("Poppins SemiBold", Font.PLAIN, 15));
		getContentPane().add(lblDescuentos, BorderLayout.NORTH);
		
		//TODO Lista con los descuentos
	}
	
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelBotones.setBorder(new EmptyBorder(0, 0, 10, 0));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
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
				//TODO
				//Controlador.INSTANCE.publicarComentario(publicacion);
				//Ventana de diálogo confirmando que se ha publicado el comentario
				//dispose();
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
