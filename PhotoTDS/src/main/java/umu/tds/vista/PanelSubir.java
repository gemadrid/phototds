package umu.tds.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelSubir extends JEditorPane {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//Botones
	private JButton btnSubirFoto;

	/**
	 * Create the panel.
	 */
	public PanelSubir(VentanaPrincipal v) {
		this.ventana = v;
		crearPanelSubir();
	}
	
	private void crearPanelSubir() {
		setBackground(Colores.FONDO);
		GridBagLayout gbl_panelSubir = new GridBagLayout();
		gbl_panelSubir.columnWidths = new int[]{0};
		gbl_panelSubir.rowHeights = new int[]{0, 0, 30, 0, 60};
		gbl_panelSubir.columnWeights = new double[]{0.0};
		gbl_panelSubir.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gbl_panelSubir);
		
		//Textos
		JLabel lblSubir = new JLabel("Subir Foto");
		lblSubir.setForeground(Color.WHITE);
		lblSubir.setFont(new Font("Poppins", Font.BOLD, 30));
		GridBagConstraints gbc_lblSubir = new GridBagConstraints();
		gbc_lblSubir.anchor = GridBagConstraints.WEST;
		gbc_lblSubir.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubir.gridx = 0;
		gbc_lblSubir.gridy = 0;
		add(lblSubir, gbc_lblSubir);
		
		JLabel lblSubirTexto = new JLabel("<html>Sube una foto para compartirla con tus amigos.<br>Puedes arrastrar el fichero aquí o seleccionarlo desde el explorador de archivos.");
		lblSubirTexto.setForeground(Color.WHITE);
		lblSubirTexto.setFont(new Font("Poppins", Font.PLAIN, 20));
		GridBagConstraints gbc_lblSubirTexto = new GridBagConstraints();
		gbc_lblSubirTexto.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubirTexto.anchor = GridBagConstraints.WEST;
		gbc_lblSubirTexto.gridx = 0;
		gbc_lblSubirTexto.gridy = 1;
		add(lblSubirTexto, gbc_lblSubirTexto);
		
		//Botón para subir desde el ordenador
		btnSubirFoto = new JButton("Subir foto de tu ordenador");
		btnSubirFoto.setForeground(Color.WHITE);
		btnSubirFoto.setBorderPainted(false);
		btnSubirFoto.setBackground(Colores.RESALTADO);
		btnSubirFoto.setFont(new Font("Poppins", Font.BOLD, 20));
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.gridx = 0;
		gbc_btnSubirFoto.gridy = 3;
		add(btnSubirFoto, gbc_btnSubirFoto);
		
		addManejadorBotonSubirFoto();
		
		//Funcionalidad drag-and-drop
		setEditable(false);
		setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles) {
						//System.out.println(file.getPath());
						subirFoto(file.getPath());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	//Método para subir fotos
	private void subirFoto(String path) {
		VentanaSubirFoto subirFoto = new VentanaSubirFoto(ventana, path);
		subirFoto.setLocationRelativeTo(this);
		subirFoto.setVisible(true);
	}
	
	//Manejador de evento BotónSubirFoto
	private void addManejadorBotonSubirFoto() {
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				//Filtramos por imágenes (con los formatos soportados por el Sistema Operativo)
				FileFilter imageFilter = new FileNameExtensionFilter("Archivos de imagen", ImageIO.getReaderFileSuffixes());
				chooser.addChoosableFileFilter(imageFilter);
				chooser.setAcceptAllFileFilterUsed(false);
				int seleccion = chooser.showOpenDialog(ventana);
				//Si se ha seleccionado una foto, llamamos a subirFoto
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File currentFile = chooser.getSelectedFile();
					subirFoto(currentFile.getPath());
				}
			}
		});
	}

}
