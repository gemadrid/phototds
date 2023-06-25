package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;
import umu.tds.vista.utilidades.Colores;
import umu.tds.vista.utilidades.FontManager;

public class VentanaSubirFoto extends JDialog {
	
	private VentanaPrincipal ventana;
	
	private String path;
	
	//Campos
	JTextArea textComentario;
	
	//Botones
	private JButton btnSubir;
	private JButton btnCancelar;

	/**
	 * Create the dialog.
	 */
	public VentanaSubirFoto(VentanaPrincipal owner, String path) {
		super(owner, "Subir Foto", true);
		this.ventana = owner;
		this.path = path;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Colores.FONDO);
		
		crearLabelFoto();
		crearPanelComentario();
		crearPanelBotones();
		
		pack();
	}
	
	//JLabel con la foto
	private void crearLabelFoto() {
		JLabel lblFoto = new JLabel("");
		lblFoto.setBorder(new EmptyBorder(15, 15, 0, 15));
		getContentPane().add(lblFoto, BorderLayout.WEST);
		
		ImageIcon foto = redimensionarImagen(path);
		lblFoto.setIcon(foto);
	}
	
	//Panel comentario
	private void crearPanelComentario() {
		JPanel panelComentario = new JPanel();
		panelComentario.setBackground(Colores.FONDO);
		getContentPane().add(panelComentario, BorderLayout.EAST);
		panelComentario.setLayout(new BorderLayout(0, 10));
		panelComentario.setBorder(new EmptyBorder(15, 0, 0, 15));
		
		//Texto
		JLabel lblComentario = new JLabel("Escribe un comentario (máximo 200 caracteres)");
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setFont(FontManager.getFuenteBold(18f));
		panelComentario.add(lblComentario, BorderLayout.NORTH);
		
		//Área de texto
		textComentario = new JTextArea();
		textComentario.setLineWrap(true);
		textComentario.setWrapStyleWord(true);
		textComentario.setFont(FontManager.getFuente(18f));
		textComentario.setBackground(Colores.AREA_TEXTO);
		JScrollPane scrollPane = new JScrollPane(textComentario);
		scrollPane.setBorder(null);
		panelComentario.add(scrollPane, BorderLayout.CENTER);
		
		addManejadorLimitarCaracteres();
	}
	
	//Panel botones
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		panelBotones.setBackground(Colores.FONDO);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		//Botones
		btnSubir = new JButton("Subir foto");
		btnSubir.setForeground(Color.WHITE);
		btnSubir.setBorderPainted(false);
		btnSubir.setBackground(Colores.RESALTADO);
		btnSubir.setFont(FontManager.getFuenteBold(15f));
		panelBotones.add(btnSubir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(Colores.RESALTADO);
		btnCancelar.setFont(FontManager.getFuenteBold(15f));
		panelBotones.add(btnCancelar);
		
		addManejadorBotonSubir();
		addManejadorBotonCancelar();
		
		return panelBotones;
	}
	
	//Manejadores de botones
	private void addManejadorBotonSubir() {
		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Subimos la foto
				Controlador.INSTANCE.subirFoto(textComentario.getText(), path);
				dispose();
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
	
	//Funcionalidad para limitar el número de caracteres
	private void addManejadorLimitarCaracteres() {
		textComentario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textComentario.getText().length() >= 200) {
					e.consume();
				}
			}
		});
	}
	
	//Método para redimensionar la imagen
	private ImageIcon redimensionarImagen(String path) {
		try {
			BufferedImage foto = ImageIO.read(new File(path));
			//Ajustamos la imagen a la pantalla
			int ancho, alto;
			//Obtenemos el tamaño de la pantalla
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int anchoPantalla = screenSize.width;
			int altoPantalla = screenSize.height;
			//Si la foto es más ancha que alta
			if (foto.getWidth() >= foto.getHeight()) {
				ancho = (int)(anchoPantalla / 2.5);
				alto = foto.getHeight()*ancho/foto.getWidth();
			}
			//Si la foto es más alta que ancha
			else {
				alto = (int)(altoPantalla / 1.5);
				ancho = foto.getWidth()*alto/foto.getHeight();
			}
			Image fotoAjustada = foto.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
			ImageIcon fotoFinal = new ImageIcon(fotoAjustada);
			return fotoFinal;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
