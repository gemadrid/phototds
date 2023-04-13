package umu.tds.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Publicacion;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
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
		
		//TODO Ver qué tamaño es mejor para la foto
		//Foto redimensionada
		//ImageIcon fotoRedimensionada = getImagenRedimensionada(Controlador.INSTANCE.getPathPublicacion(publicacion), 100, 50);
		ImageIcon fotoRedimensionada = getImagenRedimensionada("/umu/tds/resources/fotoperfil.jpg", 240, 135);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setIcon(fotoRedimensionada);
		add(lblFoto, BorderLayout.WEST);
		
		add(crearPanelInfo(), BorderLayout.CENTER);
		
		//setMaximumSize(getPreferredSize());
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
		//JLabel lblNumMegusta = new JLabel(Controlador.INSTANCE.getNumMegusta(publicacion) + " Me gusta");
		JLabel lblNumMegusta = new JLabel("x Me gusta");
		lblNumMegusta.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblNumMegusta.setForeground(Color.WHITE);
		panelBotones.add(lblNumMegusta);
		
		addManejadorBotonMegusta();
		addManejadorBotonComentario();
		
		return panelBotones;
	}
	
	//Línea 2 (foto y nombre de usuario)
	private JPanel crearPanelUsuario() {
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBackground(fondo);
		panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		
		//Obtenemos la foto de perfil y la redimensionamos
		//ImageIcon fotoPerfil = getImagenRedimensionada(Controlador.INSTANCE.getFotoUsuario(publicacion), 30, 30);
		
		//Label con el nombre de usuario y su foto
		JLabel lblUsuario = new JLabel();
		lblUsuario.setFont(new Font("Poppins Medium", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		//lblUsuario.setText(Controlador.INSTANCE.getNombreUsuario(publicacion));
		lblUsuario.setText("usuario");
		//lblUsuario.setIcon(fotoPerfil);
		panelUsuario.add(lblUsuario);
		
		return panelUsuario;
	}
	
	//Método para redimensionar imágenes
	private ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		URL url = this.getClass().getResource(path);
		ImageIcon icon;
		try {
			BufferedImage imagen = ImageIO.read(url);
			Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
			icon = new ImageIcon(imagenRedimensionada);
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Manejador botón megusta
	private void addManejadorBotonMegusta() {
		btnMegusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.darMegusta(publicacion);
			}
		});
	}
	
	//Manejador botón comentario
	private void addManejadorBotonComentario() {
		btnComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComentario comentario = new VentanaComentario(ventana, publicacion);
				comentario.setLocationRelativeTo(ventana);
				comentario.setVisible(true);
			}
		});
	}

}
