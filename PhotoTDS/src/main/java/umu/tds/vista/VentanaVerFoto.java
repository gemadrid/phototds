package umu.tds.vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import umu.tds.modelo.Publicacion;
import umu.tds.vista.utilidades.Colores;

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
