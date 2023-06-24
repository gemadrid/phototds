package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;

public class VentanaVerFoto extends JDialog {
	
	private Publicacion publicacion;

	/**
	 * Create the dialog.
	 */
	public VentanaVerFoto(JFrame owner, Publicacion publicacion) {
		super(owner, publicacion.getTitulo(), true);
		this.publicacion = publicacion;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Colores.FONDO);
		
		crearPanelFoto();
		
		pack();
	}
	
	private void crearPanelFoto() {
		String pathFoto = publicacion.getPath();
		ImageIcon imagen = new ImageIcon(pathFoto);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setIcon(imagen);
		
		JScrollPane scrollPane = new JScrollPane(lblFoto);
		scrollPane.setBorder(null);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
