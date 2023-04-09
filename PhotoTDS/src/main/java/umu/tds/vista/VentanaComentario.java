package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import umu.tds.modelo.Publicacion;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaComentario extends JDialog {
	
	private Publicacion publicacion;
	
	//TextArea
	private JTextArea textoComentario;
	
	//Botones
	private JButton btnComentar;
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
			VentanaComentario dialog = new VentanaComentario(null, null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaComentario(JFrame owner, Publicacion publicacion) {
		super(owner, "Comentario", true);
		this.publicacion = publicacion;
		setLocationRelativeTo(null);
		setSize(500, 400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		crearPanelComentario();
	}
	
	private void crearPanelComentario() {
		JPanel panelComentario = new JPanel();
		panelComentario.setBackground(fondo);
		panelComentario.setLayout(new BorderLayout(10, 10));
		panelComentario.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(panelComentario, BorderLayout.CENTER);
		
		//Texto de cabecera
		JLabel lblCabecera = new JLabel("Introduce un comentario (máximo 120 caracteres)");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblCabecera.setForeground(Color.WHITE);
		panelComentario.add(lblCabecera, BorderLayout.NORTH);
		
		//Area de texto
		textoComentario = new JTextArea();
		textoComentario.setLineWrap(true);
		textoComentario.setWrapStyleWord(true);
		textoComentario.setBackground(areaTexto);
		textoComentario.setFont(new Font("Poppins", Font.PLAIN, 15));
		panelComentario.add(textoComentario, BorderLayout.CENTER);
		
		//Botones
		panelComentario.add(crearPanelBotones(), BorderLayout.SOUTH);
	}
	
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		//Botón comentar
		btnComentar = new JButton("Comentar");
		btnComentar.setForeground(Color.WHITE);
		btnComentar.setBorderPainted(false);
		btnComentar.setBackground(resaltado);
		btnComentar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnComentar);
		
		//Botón cancelar
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(resaltado);
		btnCancelar.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnCancelar);
		
		addManejadorBotonComentar();
		addManejadorBotonCancelar();
		
		return panelBotones;
	}
	
	//Manejadores de botones
	private void addManejadorBotonComentar() {
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
