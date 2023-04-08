package umu.tds.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class PanelPrincipalFoto extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Foto
	//TODO ¿Pasar URL en vez de la publicación directamente?
	private Publicacion publicacion;
	
	//Botones
	private JButton btnMegusta;
	private JButton btnComentario;
	
	//Colores
	private Color fondo = new Color(43, 44, 62);
	private Color resaltado = new Color(235, 110, 96);
	private Color areaTexto = new Color(242, 242, 242);

	/**
	 * Create the panel.
	 */
	public PanelPrincipalFoto(VentanaPrincipal v, Publicacion p) {
		ventana = v;
		publicacion = p;
		crearPanelPrincipalFoto();
	}
	
	private void crearPanelPrincipalFoto() {
		setBackground(fondo);
		setLayout(new BorderLayout(0, 0));
		
		//Foto
		JLabel lblFoto = new JLabel("Aquí va la foto redimensionada");
		lblFoto.setForeground(resaltado);
		add(lblFoto, BorderLayout.WEST);
		
		add(crearPanelInfo(), BorderLayout.CENTER);
	}
	
	//Panel con los botones, número de megusta e información del usuario
	private JPanel crearPanelInfo() {
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(fondo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		panelInfo.add(crearPanelBotones(), BorderLayout.NORTH);
		panelInfo.add(crearPanelUsuario(), BorderLayout.CENTER);
		
		return panelInfo;
	}
	
	//Panel 1 (botones y número de megusta)
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		
		//Botón megusta
		btnMegusta = new JButton();
		btnMegusta.setIcon(new ImageIcon(PanelPrincipalFoto.class.getResource("/umu/tds/resources/megusta.png")));
		btnMegusta.setBorderPainted(false);
		btnMegusta.setBackground(resaltado);
		panelBotones.add(btnMegusta);
		
		//Botón comentario
		btnComentario = new JButton();
		btnComentario.setIcon(new ImageIcon(PanelPrincipalFoto.class.getResource("/umu/tds/resources/comentario.png")));
		btnComentario.setBorderPainted(false);
		btnComentario.setBackground(resaltado);
		panelBotones.add(btnComentario);
		
		//Número de megusta
		JLabel lblNumMegusta = new JLabel("x Me gusta");
		lblNumMegusta.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNumMegusta.setForeground(Color.WHITE);
		panelBotones.add(lblNumMegusta);
		
		return panelBotones;
	}
	
	//Línea 2 (foto y nombre de usuario)
	private JPanel crearPanelUsuario() {
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBackground(fondo);
		panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		
		//Obtenemos la foto de perfil y la redimensionamos
		
		//Label con el nombre de usuario y su foto
		JLabel lblUsuario = new JLabel();
		lblUsuario.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setText("Aquí va el nombre de usuario");
		//lblUsuario.setIcon(null);
		panelUsuario.add(lblUsuario);
		
		return panelUsuario;
	}

}
