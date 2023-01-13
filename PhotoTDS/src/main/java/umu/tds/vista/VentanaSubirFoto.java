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
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;

public class VentanaSubirFoto extends JDialog {
	
	private String path;
	
	//Campos
	
	//Botones
	JButton btnSubir;
	JButton btnCancelar;
	
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
	public VentanaSubirFoto(JFrame owner, String path) {
		super(owner, "Subir Foto", true);
		this.path = path;
		
		setSize(1100, 600);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(fondo);
		
		crearPanelFoto();
		crearPanelComentario();
		crearPanelBotones();
	}
	
	//Panel foto
	private void crearPanelFoto() {
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(fondo);
		getContentPane().add(panelFoto, BorderLayout.WEST);
		GridBagLayout gbl_panelFoto = new GridBagLayout();
		gbl_panelFoto.columnWidths = new int[]{20, 0};
		gbl_panelFoto.rowHeights = new int[]{0};
		gbl_panelFoto.columnWeights = new double[]{0.0, 0.0};
		gbl_panelFoto.rowWeights = new double[]{0.0};
		panelFoto.setLayout(gbl_panelFoto);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblFoto.gridx = 1;
		gbc_lblFoto.gridy = 0;
		panelFoto.add(lblFoto, gbc_lblFoto);
		
		//TODO ¿Pasar directamente un File en vez de el path de la foto?
		try {
			BufferedImage foto = ImageIO.read(new File(path));
			//Ajustamos la imagen a la ventana
			int tamanoX = getWidth()/2;
			int tamanoY = foto.getHeight()*tamanoX/foto.getWidth();
			//TODO ¿Cambiar método de escalado de imagen?
			Image fotoAjustada = foto.getScaledInstance(tamanoX, tamanoY, Image.SCALE_DEFAULT);
			ImageIcon fotoFinal = new ImageIcon(fotoAjustada);
			lblFoto.setIcon(fotoFinal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Panel comentario
	private void crearPanelComentario() {
		JPanel panelComentario = new JPanel();
		panelComentario.setBackground(fondo);
		getContentPane().add(panelComentario, BorderLayout.EAST);
		GridBagLayout gbl_panelComentario = new GridBagLayout();
		gbl_panelComentario.columnWidths = new int[]{0, 20, 0};
		gbl_panelComentario.rowHeights = new int[]{20, 0, 0, 10};
		gbl_panelComentario.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelComentario.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE, 0.0};
		panelComentario.setLayout(gbl_panelComentario);
		
		//Texto
		JLabel lblComentario = new JLabel("Escribe un comentario (máximo 200 caracteres)");
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setFont(new Font("Poppins", Font.BOLD, 18));
		GridBagConstraints gbc_lblComentario = new GridBagConstraints();
		gbc_lblComentario.insets = new Insets(0, 0, 5, 5);
		gbc_lblComentario.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblComentario.gridx = 0;
		gbc_lblComentario.gridy = 1;
		panelComentario.add(lblComentario, gbc_lblComentario);
		
		//Área de texto
		JTextArea textPane = new JTextArea();
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		textPane.setFont(new Font("Poppins", Font.PLAIN, 18));
		textPane.setBackground(areaTexto);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 2;
		panelComentario.add(textPane, gbc_textPane);
	}
	
	//Panel botones
	private void crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
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
	}
	
	//Manejadores de botones
	private void addManejadorBotonSubir() {
		
	}
	
	private void addManejadorBotonCancelar() {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
