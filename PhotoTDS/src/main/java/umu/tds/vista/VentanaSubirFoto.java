package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

public class VentanaSubirFoto extends JDialog {
	
	private VentanaPrincipal ventana;
	
	private String path;
	
	//Campos
	JTextArea textComentario;
	
	//Botones
	private JButton btnSubir;
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
			VentanaSubirFoto dialog = new VentanaSubirFoto(null, "C:\\Users\\Gema\\Documents\\Gema\\3º Curso\\acceso_aulas_aulario_norte.jpeg");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaSubirFoto(VentanaPrincipal owner, String path) {
		super(owner, "Subir Foto", true);
		this.ventana = owner;
		this.path = path;
		
		setSize(1100, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		crearLabelFoto();
		crearPanelComentario();
		crearPanelBotones();
	}
	
	//JLabel con la foto
	private void crearLabelFoto() {
		JLabel lblFoto = new JLabel("");
		lblFoto.setBorder(new EmptyBorder(0, 20, 0, 0));
		getContentPane().add(lblFoto, BorderLayout.WEST);
		
		ImageIcon foto = redimensionarImagen(path);
		lblFoto.setIcon(foto);
	}
	
	//Panel comentario
	private void crearPanelComentario() {
		JPanel panelComentario = new JPanel();
		panelComentario.setBackground(fondo);
		getContentPane().add(panelComentario, BorderLayout.EAST);
		panelComentario.setLayout(new BorderLayout(0, 10));
		panelComentario.setBorder(new EmptyBorder(15, 0, 0, 15));
		
		//Texto
		JLabel lblComentario = new JLabel("Escribe un comentario (máximo 200 caracteres)");
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setFont(new Font("Poppins", Font.BOLD, 18));
		panelComentario.add(lblComentario, BorderLayout.NORTH);
		
		//Área de texto
		textComentario = new JTextArea();
		textComentario.setLineWrap(true);
		textComentario.setWrapStyleWord(true);
		textComentario.setFont(new Font("Poppins", Font.PLAIN, 18));
		textComentario.setBackground(areaTexto);
		panelComentario.add(textComentario, BorderLayout.CENTER);
		
		addManejadorLimitarCaracteres();
	}
	
	//Panel botones
	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		panelBotones.setBackground(fondo);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		//Botones
		btnSubir = new JButton("Subir foto");
		btnSubir.setForeground(Color.WHITE);
		btnSubir.setBorderPainted(false);
		btnSubir.setBackground(resaltado);
		btnSubir.setFont(new Font("Poppins", Font.BOLD, 15));
		panelBotones.add(btnSubir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(resaltado);
		btnCancelar.setFont(new Font("Poppins", Font.BOLD, 15));
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
				//TODO Avisar de que la foto se ha podido subir correctamente
				//Actualizamos la ventana principal
				ventana.actualizarSubirFoto();
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
	
	//Método para redimensionar la imagen
	//TODO Ajustar mejor la imagen a la ventana
	//TODO ¿Null?
	private ImageIcon redimensionarImagen(String path) {
		try {
			BufferedImage foto = ImageIO.read(new File(path));
			//Ajustamos la imagen a la ventana
			int tamanoX = getWidth()/2;
			int tamanoY = foto.getHeight()*tamanoX/foto.getWidth();
			//TODO ¿Y si la imagen es más alta que la ventana?
			//TODO ¿Cambiar método de escalado de imagen?
			Image fotoAjustada = foto.getScaledInstance(tamanoX, tamanoY, Image.SCALE_AREA_AVERAGING);
			ImageIcon fotoFinal = new ImageIcon(fotoAjustada);
			return fotoFinal;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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

}
